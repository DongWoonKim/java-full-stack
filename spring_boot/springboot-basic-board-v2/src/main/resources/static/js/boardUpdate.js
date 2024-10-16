let selectedFile = null; // 파일은 1개만 선택 가능

$(document).ready(() => {
    checkSession();
    loadBoardDetail();

    $('#hiddenFileFlag').val(false);

    // 파일 선택 시 이벤트
    $('#file').on('change', function(e) {
        const file = e.target.files[0]; // 첫 번째 파일만 선택

        selectedFile = file; // 선택된 파일을 변수에 저장

        $('#hiddenFileFlag').val(true);
        updateFileList(); // 파일 목록 업데이트
    });

    $('#submitBtn').on('click', (event) => {
        event.preventDefault();

        // FormData 객체 생성
        let formData = new FormData($('#writeForm')[0]);

        $.ajax({
            type: 'PUT',
            url: '/api/board',
            data: formData,
            processData: false, // 파일 전송을 위해 필수
            contentType: false, // 파일 전송을 위해 필수
            success: (response) => {
                alert('게시글이 성공적으로 수정되었습니다!');
                window.location.href = '/'; // 성공 후 목록 페이지로 이동
            },
            error: (xhr, status, error) => {
                alert('게시글 수정 중 오류가 발생했습니다.');
                console.error(error);
            }
        });
    });

});

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

let loadBoardDetail = () => {

    let hId = $('#hiddenId').val();

    $.ajax({
        type: 'GET',
        url: '/api/board/' + hId,
        success: (response) => {
            $('#title').val(response.title);
            $('#content').text(response.content);
            $('#userId').text(response.userId);

            // 파일 목록이 있는 경우, 파일 다운로드 링크 추가
            if (response.filePath && response.filePath.length > 0) {
                let filePath = response.filePath;
                $('#hiddenFilePath').val(filePath)
                let fileName = filePath.substring(filePath.lastIndexOf('/') + 1); // 파일명 추출
                let fileElement = `
                    <li>
                        ${fileName} <button type="button" class="remove-btn">X</button>
                    </li>`;
                $('#fileList').append(fileElement);

                // X 버튼 클릭 시 파일 제거
                $('.remove-btn').on('click', function() {
                    selectedFile = null; // 선택된 파일 제거
                    $('#file').val(''); // 파일 input 초기화
                    updateFileList(); // 파일 목록 갱신
                });
            } else {
                $('#fileList').append('<li>첨부된 파일이 없습니다.</li>');
            }

        },
        error: function (error) {
            console.error('오류 발생:', error);
            alert('상세 데이터를 불러오는데 오류가 발생했습니다.');
        }
    });
}