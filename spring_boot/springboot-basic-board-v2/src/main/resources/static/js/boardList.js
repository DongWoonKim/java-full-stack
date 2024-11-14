
$(document).ready(() => {
    checkToken();
    setupAjax();
    // getUserInfo()가 데이터를 반환하면 처리하는 부분
    getUserInfo().then((userInfo) => {
        // 받은 userInfo로 필요한 작업을 수행
        $('#welcome-message').text(userInfo.userName + '님 환영합니다!');
        $('#hiddenUserId').val(userInfo.userId);
        $('#hiddenUserName').val(userInfo.userName);
    }).catch((error) => {
        console.error('Error while fetching user info:', error);
    });
    getBoards();
});

let getBoards = () => {
    let currentPage = 1;
    const pageSize = 10; // 한 페이지에 보여줄 게시글 수

    // 초기 게시글 로드
    loadBoard(currentPage, pageSize);

    // 다음 페이지 버튼 클릭 이벤트
    $('#nextPage').on('click', () => {
        currentPage++;
        loadBoard(currentPage, pageSize);
    });

    // 이전 페이지 버튼 클릭 이벤트
    $('#prevPage').on('click', function() {
        if (currentPage > 1) {
            currentPage--;
            loadBoard(currentPage, pageSize);
        }
    });
}

// 게시글 데이터를 로드하는 함수
let loadBoard = (page, size) => {
    $.ajax({
        type: 'GET',
        url: '/api/board',
        data: {
            page: page,
            size: size
        },
        success: (response) => {
            console.log('res :: ', response)
            $('#boardContent').empty(); // 기존 게시글 내용 비우기
            if (response.boards.length <= 0) {
                // 게시글이 없는 경우 메시지 출력
                $('#boardContent').append(
                    `<tr>
                        <td colspan="4" style="text-align: center;">글이 존재하지 않습니다.</td>
                    </tr>`
                );
            } else {
                response.boards.forEach((item) => {
                    $('#boardContent').append(
                        `
                    <tr>
                        <td>${item.id}</td>
                        <td><a href="/detail?id=${item.id}">${item.title}</a></td>
                        <td>${item.userId}</td>
                        <td>${item.created}</td>
                    </tr>
                    `
                    );
                });

            }
            // 페이지 정보 업데이트
            $('#pageInfo').text(page);

            // 이전/다음 버튼 상태 설정
            $('#prevPage').prop('disabled', page === 1);
            $('#nextPage').prop('disabled', response.last);
        },
        error: (xhr) => {
            if (xhr.status === 401) {
                // Refresh Token을 통해 Access Token 재발급 요청
                handleTokenExpiration();
            } else if (xhr.status === 403) {
                window.location.href = '/access-denied';
            } else {
                // 다른 오류 처리
                console.error('요청 오류 발생:', xhr);
            }
        }
    });
}



let logout = () => {
    // refresh token 제거
    $.ajax({
        type: 'POST',
        url: '/logout', // 서버의 엔드포인트 URL
        contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
        success: (response) => {
            // 성공 시 실행될 콜백 함수
            alert('로그아웃 되었습니다.')
            // access token 제거
            localStorage.removeItem('accessToken');
            // 성공 후 로그인 페이지로 이동.
            window.location.href = '/member/login';
        },
        error: (error) => {
            // 실패 시 실행될 콜백 함수
            console.error('오류 발생:', error);
            alert('로그아웃 중 오류가 발생했습니다.');
        }
    });
}