var main = {
    init: function () {
        var _this = this;
        $('#member_update').on('click',function (){
            _this.update();
        });
        $('#member_delete').on('click',function (){
            _this.delete();
        });
    },

    update: function (){
        var memberId = $('#inputId').val();

        var data = {
            name: $('#inputName').val(),
            nickname: $('#inputNickname').val(),
            email: $('#inputUserID').val(),
            password: $('#inputPassword').val()
        };
        if(!data.nickname||data.nickname.trim()==""){
            alert("닉네임을 입력해주세요");
            return false;
        }
        else if(!data.password||data.password.trim()==""){
            alert("비밀번호를 입력해주세요")
            return false;
        }
        else{
            $.ajax({
                type: 'PATCH',
                url: '/api/users/'+memberId,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(function (data) {
                if(data == true){
                    alert("회원 정보 수정 완료");
                    window.location.href = '/api/users/detail/'+memberId;
                }else{
                    alert("회원 정보 수정에 실패했습니다");
                    return false;
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    },

    delete: function (){
        var memberId = $('#inputId').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/users/'+memberId,
            contentType: 'application/json; charset=utf-8',
        }).done(function (data) {
            if(data == true) {
                alert("회원 탈퇴 완료");
                location.href = '/users/login';
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
}

main.init();