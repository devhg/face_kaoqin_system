// $(function () {
//
//     const Toast = Swal.mixin({
//         toast: true,
//         position: 'top-end',
//         showConfirmButton: false,
//         timer: 3000
//     })
//
//     Toast.fire({
//         type: 'success',
//         title: 'Signed in successfully'
//     })

//
//     $("#login").click(async function () {
//         window.location.href = "errors.html";

// Swal.mixin({
//     input: 'password',
//     confirmButtonText: '下一步',
//     showCancelButton: true,
//     cancelButtonText: '取 消',
//     progressSteps: ['1', '2', '3']
//   }).queue([
//     {
//       title: '修改账户密码',
//       text: '请输入原密码'
//     },
//     '请输入新密码',
//     '请确认输入新密码'
//   ]).then((result) => {
//     if (result.value) {
//       Swal.fire({
//         title: '输入的值分别是',
//         html:
//             JSON.stringify(result.value),
//         confirmButtonText: '提交'
//       })
//     }
//   })


// const { value: formValues } = await Swal.fire({
//     title: '修改账户密码',
//     width: '400px',
//     html:
//         '<form>' +
//         '<div class="form-group">' +
//         '<label for="swal-input1">Email address</label>' +
//         '<input type="email" class="form-control" id="swal-input1" aria-describedby="emailHelp" placeholder="Enter email">' +
//         '<small id="emailHelp" class="form-text text-muted">请确认密码</small>' +
//         '</div>' +
//         '<div class="form-group">' +
//         '<label for="swal-input2">Password</label>' +
//         '<input type="password" class="form-control" id="swal-input2" placeholder="Password">' +
//         '</div>' +
//         '</form>',
//     focusConfirm: false,
//     preConfirm: () => {
//         return [
//             $("#swal-input1").val(),
//             $("#swal-input2").val()
//         ]
//     }
// })

// if (formValues) {
//     Swal.fire(JSON.stringify(formValues))
// }
//     });
// });
// $(document).ready(function(){
$(function () {
    $(this).keydown(function (e) {
        if (e.which == "13") {
            //your code
            $("#login").trigger("click");
        }
    })
    $("#login").click(function () {

        var username = $("#username").val();
        var password = $("#password").val();
        var passhash = CryptoJS.MD5(password).toString();
        //console.log(passhash);
        // $.ajax({
        //     type: "post",
        //     // 编写json格式，设置属性和值
        //     url: "user/test",//后台的请求方法
        //     contentType: "application/json;charset=UTF-8",
        //     // data: JSON.stringify({usename: username, password: passhash}),//json数据，将会传到服务器
        //     dataType: "json",
        //     success: function (data) {
        //
        //         console.log(JSON.stringify(data));
        //         // data服务器端响应的json的数据，进行解析
        //         Swal.fire(JSON.stringify(data));
        //     }
        // });
        $.ajax({
            type: "POST",
            url: "admin/login.do",
            data: JSON.stringify({username: username, password: passhash}),
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            success: function (data) {
                if (data.mode) {
                    window.location.href = "index.html";
                    //发送session
                    const Toast = Swal.mixin({
                        toast: true,
                        position: 'top-end',
                        showConfirmButton: false,
                        timer: 3000
                    })

                    Toast.fire({
                        type: 'success',
                        title: 'Signed in successfully'
                    })

                } else {
                    Swal.fire('请输入正确的用户名和密码',
                        '您输入的用户名或者密码不正确',
                        'error'
                    );
                }
                /*
                Swal.fire('<font color="#ff0000">您还没有登录</font>',
                '请您前往登陆',
                'question'
                );
                 */

            },
        });

    });
    $("#updatePassword").click(function () {
        const {value: formValues} = await
        Swal.fire({
            title: 'Multiple inputs',
            html:
                '<input id="swal-input1" class="swal2-input">' +
                '<input id="swal-input2" class="swal2-input">',
            focusConfirm: false,
            preConfirm: () => {
                return [
                    document.getElementById('swal-input1').value,
                    document.getElementById('swal-input2').value
                ]
            }
        })

        if (formValues) {
            Swal.fire(JSON.stringify(formValues))
        }
    });
    $("#addUser").click(function () {
        var username = $("#addName").val();
        var classname = $("#addClass").val();
        $.ajax({
            type: "POST",
            url: "admin/insertUser.do",
            data: JSON.stringify({username: username, className: classname}),
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            success: function (data) {
                if (data.mode) {
                    window.location.href = "index.html";
                    //发送session
                    const Toast = Swal.mixin({
                        toast: true,
                        position: 'top-end',
                        showConfirmButton: false,
                        timer: 3000
                    })

                    Toast.fire({
                        type: 'success',
                        title: '添加成功'
                    })

                }
            },
        });
    });
});
