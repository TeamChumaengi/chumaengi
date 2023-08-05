var main = {
    init: function () {
        var _this = this;
        $('#user-login').on('click', function () {
            _this.login();
        });
    },
    login: function (){
        var data = {
            email: $('#loginID').val(),
            password: $('#loginPW').val()
        };
        if(!data.email||data.email.trim()==""){
            alert("아이디(이메일)를 입력해주세요");
            return false;
        }
        else if(!data.password||data.password.trim()==""){
            alert("비밀번호를 입력해주세요");
            return false;
        }
        else{
            $.ajax({
                type: 'POST',
                url: '/users/login',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(function (data) {
                if(data == true){
                    window.location.href = '/';
                }else{
                    alert("아이디와 비밀번호를 확인해주세요.");
                    return false;
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    }
}

main.init();