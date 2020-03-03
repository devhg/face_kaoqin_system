<%--
  Created by IntelliJ IDEA.
  User: Xrafece
  Date: 2019/9/22
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试页面</title>
</head>
<body>

<a href="/admin/clockList.do">签到列表</a>
<a href="/admin/unsignList.do">未签到列表</a>
<a href="/admin/leaveList.do">请假列表</a>
<table
        data-toggle="table"
        date-height="406"
        data-url="admin/clockList.do">
    <thead>
    <tr>
        <th data-field="username">Item ID</th>
        <th data-field="date">Item Name</th>
        <th data-field="isClock">Item Price</th>
        <th data-field="reason">Item Pce</th>
    </tr>
    </thead>
</table>
</body>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.css">
<script src="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.js"></script>
<script>

</script>
</html>
