<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
  request.setCharacterEncoding("utf-8");
  String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>微信群发管理</title>
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
	  <div style="margin-top:20px;"></div>
      <!-- Main component for a primary marketing message or call to action -->
      <h4>编辑群发文字：</h4>
      <div class="form-group">
          <textarea class="form-control" id="bulkContent"></textarea>
      </div>
      <div id="sendTextResult" style="color:red;font-size: 12px;margin-bottom: 10px;margin-top: -10px;"></div>
      <button id="sendTextBtn" class="btn btn-lg btn-primary btn-block">群发文字</button>

      <h4>上传图片：</h4>
      <div class="form-group">
	      <div class="row">
	      	<div class="col-md-8 col-lg-8 col-sm-8 col-xs-8">
	      		<input type="file" class="form-control" id="imageUpLoad"></input>
	      		<div class="hide" id="imageMediaId"></div> 
	      	</div>
	      	<div class="col-md-4 col-lg-4 col-sm-4 col-xs-4">
	      		<button class="btn btn-sm btn-primary btn-block" id="imageSub">上传</button>
	      	</div>
	      </div>
      </div>
      <div id="upLoadResult" style="color:red;font-size: 12px;margin-bottom: 10px;margin-top: -10px;"></div>
      <button id="sendBtn" class="btn btn-lg btn-primary btn-block">群发图片</button>
      
      <h4>编辑文章：</h4>
      <div class="form-group">
          <div class="row">
            <div class="col-md-4 col-lg-4 col-sm-4 col-xs-4" style="text-align: right;">
              标题：
            </div>
            <div class="col-md-8 col-lg-8 col-sm-8 col-xs-8">
              <input type="text" class="form-control" id="articTitle" placeholder="标题"></input>
            </div>
          </div>
          <div class="row">
            <div class="col-md-4 col-lg-4 col-sm-4 col-xs-4" style="text-align: right;">
              正文：
            </div>
            <div class="col-md-8 col-lg-8 col-sm-8 col-xs-8">
              <textarea class="form-control" id="articDesc"></textarea>
            </div>
          </div>
      </div>
      <div id="sendArticResult" style="color:red;font-size: 12px;margin-bottom: 10px;margin-top: -10px;"></div>
      <button id="sendArticBtn" class="btn btn-lg btn-primary btn-block" style="margin-bottom: 15px;">群发文章</button>
   
    </div> <!-- /container -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=basePath%>/resources/widget/Flat-UI/dist/js/vendor/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath%>/resources/widget/Flat-UI/dist/js/flat-ui.min.js"></script>
    
    <script src="<%=basePath%>/resources/js/buldMessage.js"></script>
  </body>
</html>
