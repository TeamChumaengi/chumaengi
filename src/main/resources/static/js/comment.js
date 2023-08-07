var main = {
    init: function () {
        var _this = this;
        $('#comment_save').on('click',function (){
            _this.comment_save();
        });
        $('#comment_delete').on('click',function (){
            _this.comment_delete();
        });
    },

    comment_save: function (){
        var memberId = $('#inputId').val();
        var boardId = $('#inputBoardId').val();

        var data = {
            content: $('#inputComment').val()
        };
        if(!data.content||data.content.trim()==""){
            alert("내용을 입력해주세요");
            return false;
        }
        else{
            $.ajax({
                type: 'POST',
                url: '/api/comments/'+memberId+'/'+boardId,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(function (data) {
                if(data == true){
                    alert("등록 완료");
                    location.href = '/api/boards/detail/'+boardId;
                }else{
                    alert("댓글 등록에 실패했습니다");
                    return false;
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    },

    comment_delete: function (){
        var memberId = $('#inputId').val();
        var boardId = $('#inputBoardId').val();
        var commentId = $('#inputCommentId').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/comments/'+memberId+'/'+commentId,
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert("삭제 완료");
            location.href = '/api/boards/detail/'+boardId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
}

main.init();