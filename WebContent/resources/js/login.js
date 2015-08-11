(function(){
	var userName = $("#username");
	var passWord = $("#password");
	var loginBtn = $("#loginBtn");
	var rootPath =window.location.href;

 	userName.on('change', validateUserName);
 	passWord.on('change', validatePassWord);

 	loginBtn.on('click', function(){
 		if(ValidationNotNull()){
			var data = JSON.stringify({userName: userName.val(),passWord:passWord.val()});
 			$.ajax({
 			url: 'web/login',
 			type: 'POST',
 			dataType: 'json',
 			contentType: 'application/json',
 			data: data
 		})
 		.success(function(data) {
 			if (data.success) {
 				window.location.href = "buldMessage.jsp";
 			} else{
 				$("#loginResult").html(data.msg);
 			};
 		})
 		.error(function(error) {
 			console.log(error);
 		})
 		.complete(function() {
 			console.log("complete");
 		});
		}
 	});

 	/**
	*用户名密码非空验证 
	*/
	var ValidationNotNull = function(){
		if (userName.val().length == 0) {
			alert("请输入用户名");
				return false;
			}else if(passWord.val().length == 0){
				alert("请输入密码");
				return false;
			}else{
				return true;
			}
	};

	/***
		验证用户框输入
	*/
	var validateUserName = function(){

	};

	/***
		验证密码框输入
	*/
	var validatePassWord = function(){
		
	}

})();
	