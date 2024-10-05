let selectedFile = null; // 파일은 1개만 선택 가능

$(document).ready(() => {
    checkSession();

    // 파일 선택 시 이벤트
    $('#file').on('change', function(e) {
        const file = e.target.files[0]; // 첫 번째 파일만 선택

        selectedFile = file; // 선택된 파일을 변수에 저장
        updateFileList(); // 파일 목록 업데이트
    });

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

// 파일 목록 업데이트 함수 (파일 하나만)
let updateFileList = () => {
    $('#fileList').empty(); // 기존 목록 비우기

    if (selectedFile) {
        $('#fileList').append(`
                    <li>
                        ${selectedFile.name} <button type="button" class="remove-btn">X</button>
                    </li>
                `);

        // X 버튼 클릭 시 파일 제거
        $('.remove-btn').on('click', function() {
            selectedFile = null; // 선택된 파일 제거
            $('#file').val(''); // 파일 input 초기화
            updateFileList(); // 파일 목록 갱신
        });
    }
}