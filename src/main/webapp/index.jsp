<%--
  Created by IntelliJ IDEA.
  User: qxqzx
  Date: 2019-08-15
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>考勤APP接口api</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <style>
    .main {
      width: 1000px;
      height: 600px;
      margin: 100px auto;
    }
    .jumbotron {
      height: 600px;
      padding: 50px;
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
    }
  </style>
  <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
  <script>
    function sub() {
        $.ajax({
            type: 'post',
            dataType: 'json',
            url: 'http://192.168.1.103:8080/user/login.do',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify({
                'username': "18110501122",
                "password": "123456"
            }),
            success: function (resData) {
                console.log(resData);
            }
        })
    }
  </script>
</head>
<body>
<div class="main">
  <div class="jumbotron">
    <h1>考勤APP接口api</h1>
    <br><br>
    <br><br>
    <h2>假装是个界面</h2>
    <br><br>
    <br><br>
  </div>
</div>


<button onclick="sub()">${pageContext.request.contextPath}惦记我</button>
</body>
</html>
