<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
  request.setCharacterEncoding("utf-8");
  String basePath = request.getContextPath();
%>
<html>
  <title>给活点地图留言</title>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <!-- Loading Bootstrap -->
  <link href="<%=basePath%>/resources/widget/Flat-UI/dist/css/vendor/bootstrap.min.css" rel="stylesheet">
  <!-- Loading Flat UI -->
  <link href="<%=basePath%>/resources/widget/Flat-UI/dist/css/flat-ui.css" rel="stylesheet">
  <link rel="shortcut icon" href="<%=basePath%>/resources/widget/Flat-UI/dist/img/favicon.ico">
  <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
  <!--[if lt IE 9]>
  <script src="<script src="<%=basePath%>/resources/widget/Flat-UI/assets/js/application.js"></script>
  /dist/js/vendor/html5shiv.js">
</script>
<script src="<script src="<%=basePath%>/resources/widget/Flat-UI/assets/js/application.js"></script>
/dist/js/vendor/respond.min.js">
</script>
<![endif]-->
</head>
<body>

<div class="container">
	<div class="bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="false">
	<div class="modal-dialog modal-sm">
	  <div class="modal-content">
	    <div class="modal-header">
	      <h4 class="modal-title" id="exampleModalLabel">留言</h4>
	    </div>
	    <div class="modal-body">
	      <form>
	       
	        <div class="form-group">
	          <label for="feedBackContent" class="control-label">内容：</label>
	          <textarea class="form-control" id="feedBackContent"></textarea>
	        </div>
	      </form>
	    </div>
	    <div class="modal-footer">
	      <button type="button" class="btn btn-default" id="clearBtn">清除</button>
	      <button type="button" class="btn btn-primary" id="saveBtn">提交</button>
	    </div>
	  </div>
	</div>
	</div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=basePath%>/resources/widget/Flat-UI/dist/js/vendor/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>/resources/widget/Flat-UI/dist/js/flat-ui.min.js"></script>

<script src="<%=basePath%>/resources/js/feedBack.js"></script>

</body>
</html>