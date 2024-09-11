package notice;

public class Start {
    public static void main(String[] args) {
        Notice notice = new NoticeImpl(new NoticeDAO());

        while (true) {
            int choice = notice.printMenu();
            switch (choice) {
                case 1:
                    notice.signIn();
                    break;
                case 2:
                    notice.signUp();
                    break;
                case 3:
                    notice.getList();
                    break;
                case 4:
                    notice.newNotice();
                    break;
                case 5:
                    notice.updateNotice();
                    break;
                case 6:
                    notice.deleteNotice();
                    break;
                case 7:
                    notice.signOut();
                    break;
                case 8:
                    notice.leave();
                    break;
                case 9:
                    System.out.println("다음에 또 만나요!");
                    return;
            }
        }


    }
}
