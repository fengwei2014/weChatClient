<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
  request.setCharacterEncoding("utf-8");
  String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>微信管理登陆</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
    <!-- Loading Bootstrap -->
    <link href="<%=basePath%>/resources/widget/Flat-UI/dist/css/vendor/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="<%=basePath%>/resources/widget/Flat-UI/dist/css/flat-ui.css" rel="stylesheet">

    <link rel="shortcut icon" href="<%=basePath%>/resources/widget/Flat-UI/dist/img/favicon.ico">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
      <script src="<script src="../assets/js/application.js"></script>/dist/js/vendor/html5shiv.js"></script>
      <script src="<script src="../assets/js/application.js"></script>/dist/js/vendor/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <div class="container">

      <!-- Main component for a primary marketing message or call to action -->
        <h5>管理员登陆</h5>
        <div style="margin-top:50px;">
            <div class="form-group">
              <input type="text" class="form-control input-lg" name="username" id="username" placeholder="用户名">
            </div>
            <div class="form-group">
              <input type="password" class="form-control input-lg" name="password" id="password" placeholder="密码">
            </div>
            <div class="form-group" id="loginResult" style="color:red;font-size: 12px;">
             
            </div>
          </div>
        <p>
          <button id="loginBtn" class="btn btn-lg btn-primary btn-block">登陆</button>
        </p>

    </div> <!-- /container -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=basePath%>/resources/widget/Flat-UI/dist/js/vendor/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath%>/resources/widget/Flat-UI/dist/js/flat-ui.min.js"></script>
    
    <script src="<%=basePath%>/resources/js/login.js"></script>
  </body>
</html>
