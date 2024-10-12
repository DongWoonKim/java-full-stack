let handleTokenExpiration = () => {
    $.ajax({
        type: 'POST',
        url: '/refresh-token', // 새로운 Access Token 요청을 처리하는 엔드포인트
        contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
        dataType: 'json', // 서버에서 받을 데이터의 타입
        xhrFields: {
            withCredentials: true // 쿠키를 포함한 요청을 보냄
        },
        success: (response) => {
            // 새로운 Access Token을 로컬스토리지에 저장
            localStorage.setItem('accessToken', response.token);
        },
        error: (error) => {
            localStorage.removeItem('accessToken');
            alert('로그인이 필요합니다. 다시 로그인해주세요.');

            // 실패 시 기본 동작
            window.location.href = '/member/login'; // 실패 시 로그인 페이지로 이동
        }
    });
}