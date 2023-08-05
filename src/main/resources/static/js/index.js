
var main = {
    init: function () {
        var _this = this;
        $('#user-save').on('click', function () {
            _this.save();
        });
        $('#user-cancel').on('click',function (){
            location.href="/user/login";
        });

    },

    save: function () {
        var getCheck = RegExp(/^[a-zA-Z0-9]{4,12}$/);
        var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);

        var data = {
            name: $('#userName').val(),
            nickname: $('#userNickname').val(),
            email: $('#userID').val(),
            password: $('#userPW').val()
        };

        //이름
        if (!data.name || data.name.trim() == "") {
            alert("이름을 입력해주세요!");
            return false;
        }
        //닉네임
        else if (data.nickname.length < 2) {
            alert("닉네임은 2글자이상 입력해주세요");
            return false;
        }
        //이메일
        else if (!data.email || data.email.trim() == "") {
            alert("아이디(이메일)을 입력해주세요");
            return false;
        }
        //이메일
        else if (!getMail.test(data.email)) {
            alert("이메일 형식에 맞춰 입력해주세요.")
            return false;
        }
        //비밀번호
        else if (!data.password || data.password.trim() == "") {
            alert("비밀번호를 입력해주세요");
            return false;
        } else if (!getCheck.test(data.password)) {
            alert("비밀번호는 영문대소문자,숫자로 4자~11자이내로 입력해주세요.")
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: "/users/signup",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (data) {
                if (data == true) {
                    alert("회원가입에 성공했습니다.");
                    //회원가입에 성공하면 로그인페이지으로 이동
                    window.location.href = '/user/login';
                }else{
                    alert("이메일이나 닉네임이 이미 존재합니다.");
                    return false;
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })

        }

    }
}



main.init();