var main = {
    init: function () {
        var _this = this;
        $('#boardNotice_list').on('click', function () {
            location.href="/api/boards/list/3";
        });
        $('#boardNotice_save').on('click', function () {
            _this.boardNotice_save();
        });
    },

    boardNotice_save: function (){
        var memberId = $('#inputId').val();

        var data = {
            category: $('#inputCategory').val(),
            title: $('#inputTitle').val(),
            content: $('#inputContent').val()
        };
        if(!data.category||data.category.trim()==""){
            alert("카테고리를 입력해주세요");
            return false;
        }
        else if(!data.title||data.title.trim()==""){
            alert("제목을 입력해주세요");
            return false;
        }
        else if(!data.content||data.content.trim()==""){
            alert("내용을 입력해주세요");
            return false;
        }
        else{
            $.ajax({
                type: 'POST',
                url: '/api/boards/'+memberId,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(function (data) {
                if(data == true){
                    location.href = '/api/boards/list/3';
                }else{
                    alert("게시글 등록에 실패했습니다");
                    return false;
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    }
}

main.init();