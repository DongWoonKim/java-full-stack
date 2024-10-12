
$(document).ready(() => {
    checkToken();

    // 모든 Ajax 요청에 JWT Access Token을 포함
    $.ajaxSetup({
        beforeSend: function(xhr) {
            let token = localStorage.getItem('accessToken'); // 저장된 Access Token 가져오기
            console.log('Access Token:', token);
            if (token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + token); // Authorization 헤더에 Access Token 추가
            }
        }
    });

    console.log(localStorage.getItem('accessToken'));
    // 토큰 검증

    $('#signin').click(() => {

        let userId = $('#user_id').val();
        let password = $('#password').val();

        let formData = {
           username : userId,
           password : password
        }

        $.ajax({
            type: 'POST',
            url: '/login', // 서버의 엔드포인트 URL
            data: JSON.stringify(formData), // 데이터를 JSON 형식으로 변환
            contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
            // data: $.param(formData), // 데이터를 URL 인코딩된 형식으로 변환
            // contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            dataType: 'json', // 서버에서 받을 데이터의 타입
            success: (response) => {
                localStorage.setItem('accessToken', response.token);
                // 로그인 성공 시 '/' 경로로 이동
                window.location.href = '/';
            },
            error: (xhr) => {
                if (xhr.status === 401) {
                    // Refresh Token을 통해 Access Token 재발급 요청
                    handleTokenExpiration();
                } else {
                    // 다른 오류 처리
                    console.error('요청 오류 발생:', xhr);
                }
            }

        });

    });

});

let checkToken = () => {
    let token = localStorage.getItem('accessToken');
    if (token != null && token.trim() !== '') {
        window.location.href = "/";
    }
}