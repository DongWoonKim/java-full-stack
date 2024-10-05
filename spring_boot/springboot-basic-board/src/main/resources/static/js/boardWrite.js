$(document).ready(() => {

    checkSession();

    $('#submitBtn').on('click', (event) => {
        event.preventDefault();

        // FormData 객체 생성
        let formData = new FormData($('#writeForm')[0]);

        $.ajax({
            type: 'POST',
            url: '/api/board',
            data: formData,
            processData: false, // 파일 전송을 위해 필수
            contentType: false, // 파일 전송을 위해 필수
            success: (response) => {
                alert('게시글이 성공적으로 등록되었습니다!');
                window.location.href = '/'; // 성공 후 목록 페이지로 이동
            },
            error: (xhr, status, error) => {
                alert('게시글 등록 중 오류가 발생했습니다.');
                console.error(error);
            }
        });
    });

});

let checkSession = () => {
    let hUserId = $('#hiddenUserId').val();
    if (hUserId == null || hUserId === '')
        window.location.href = "/member/login";
}