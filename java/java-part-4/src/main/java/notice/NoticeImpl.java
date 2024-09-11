package notice;

import java.util.List;
import java.util.Scanner;

public class NoticeImpl implements Notice {

    private final NoticeDAO noticeDAO;
    private boolean status = false;
    private String userName = null;
    private String userId = null;

    public NoticeImpl(NoticeDAO noticeDAO) {
        this.noticeDAO = noticeDAO;
    }

    @Override
    public int printMenu() {
        System.out.println("=========================== [My Notice] ===========================");
        System.out.println(status == false ? "로그인을 해주세요." : userName + "님! 반갑습니다.");
        System.out.println("------------------------------------------------------------------");
        System.out.println("[1]로그인 [2]회원가입 [3]글목록보기 [4]글등록 [5]글수정 [6]글삭제");
        System.out.println("[7]로그아웃 [8]회원탈퇴 [9]프로그램종료");
        System.out.println("==================================================================");
        System.out.println("번호를 입력하세요.");

        return new Scanner(System.in).nextInt();
    }

    @Override
    public void signIn() {
        System.out.println("[로그인]");
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID를 입력하세요.");
        String userId = scanner.nextLine();
        System.out.println("비밀번호를 입력하세요.");
        String password = scanner.nextLine();

        SignInResponseDTO signInResponseDTO = noticeDAO.signInExc(userId, password);
        if (signInResponseDTO == null) {
            System.out.println("비밀번호를 잘 못 입력하였거나 없는 회원입니다.");
            return;
        }
        setUserInfo(signInResponseDTO.isStatus(), signInResponseDTO.getUserName(), userId);
    }

    @Override
    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID를 입력하세요.");
        String userId = scanner.nextLine();
        System.out.println("비밀번호를 입력하세요.");
        String password = scanner.nextLine();
        System.out.println("사용자 이름을 입력하세요.");
        String userName = scanner.nextLine();

        if (noticeDAO.checkUserId(userId)) {
            System.out.println("사용할 수 없는 ID입니다.");
            return;
        }

        noticeDAO.signUpExc(userId, password, userName);
    }

    @Override
    public void signOut() {
        if (!this.status) {
            System.out.println("이미 로그아웃 상태입니다.");
            return;
        }

        setUserInfo(false, null, null);
    }

    @Override
    public void leave() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("삭제할 ID를 입력하세요.");
        String userId = scanner.nextLine();
        if(!noticeDAO.checkUserId(userId)) {
            System.out.println("삭제할 회원이 없습니다.");
            return;
        }

        if(noticeDAO.leaveExc(userId)) {
            noticeDAO.deleteContentExc(userId);
            System.out.println("정상적으로 삭제 되었습니다.");
        }
    }

    @Override
    public void newNotice() {
        if (!this.status) {
            System.out.println("로그인을 먼저 해주세요.");
            return;
        }
        System.out.println("작성할 글을 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        String content = scanner.nextLine();

        if (noticeDAO.newNotice(userId, content) ) {
            System.out.println("글이 등록 되었습니다.");
        }
    }

    @Override
    public void getList() {
        List<String> list = noticeDAO.getList();
        list.forEach(System.out::println);
    }

    @Override
    public void updateNotice() {
        if (!this.status) {
            System.out.println("로그인을 먼저 해주세요.");
            return;
        }

        List<String> list = noticeDAO.getListByUserId(userId);
        if (list.size() == 0) {
            System.out.println("수정할 글이 존재하지 않습니다.");
            return;
        }
        list.forEach(System.out::println);

        System.out.println("[수정] 글 번호를 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        int contentId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("수정할 내용을 입력하세요.");
        String content = scanner.nextLine();

        boolean result = noticeDAO.updateNotice(contentId, content);
        if (result) {
            System.out.println("수정이 완료되었습니다.");
        }
    }

    @Override
    public void deleteNotice() {
        if (!this.status) {
            System.out.println("로그인을 먼저 해주세요.");
            return;
        }

        List<String> list = noticeDAO.getListByUserId(userId);
        if (list.size() == 0) {
            System.out.println("삭제할 글이 존재하지 않습니다.");
            return;
        }
        list.forEach(System.out::println);

        System.out.println("[삭제] 글 번호를 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        int contentId = scanner.nextInt();

        boolean result = noticeDAO.deleteNotice(contentId);
        if (result) {
            System.out.println("삭제가 완료되었습니다.");
        }
    }

    private void setUserInfo(boolean status, String userName, String userId) {
        this.status = status;
        this.userName = userName;
        this.userId = userId;
    }


}
