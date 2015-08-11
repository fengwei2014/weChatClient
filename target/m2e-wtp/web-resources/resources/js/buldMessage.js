(function(){
	
	var sendBtn = $("#sendBtn");
	var sendTextBtn = $("#sendTextBtn");
	var sendArticBtn = $("#sendArticBtn");
	var bulkContent = $("#bulkContent");
	var imageSub = $("#imageSub");
	var imageFile = $("#imageUpLoad");
	var videoFile = $("#videoUpLoad");
	var rootPath =window.location.href;
	// var APP_ID = "wxe8859d088819a3d8";
	// var APP_SECRET = "97da3c6bbb982f78a558e077859c236a";
	// var tokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
	// var upLoadUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload";
	// var sendToUserUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send";

	//点击上传文件
 	imageSub.on('click', function(){
		$("#upLoadResult").html("");
		$("#imageMediaId").html("");
		upLoadFile();
 	});

 	//发送文件给用户
 	sendBtn.on('click', function() {
 		sendToUser();
 	});

 	//发送文本消息
 	sendTextBtn.on('click', function() {
 		$("#sendTextResult").html("");
 		sendText();
 	});

 	sendArticBtn.on('click', function() {
 		$("#sendArticResult").html("");
 		sendArtic();
 	});

 	/*
	*上传文件
 	*/
 	var upLoadFile = function(){
 		var imageFiles = document.getElementById("imageUpLoad").files;
 		var formdata = new FormData(); 

 		if (imageFiles.length != 0) {
 			formdata.append("file", imageFiles[0]);
		       $.ajax({                  
		           type: 'POST',                  
		           url: 'web/uploadmedia',                  
		           data: formdata,                  
		           /**                  
		            *必须false才会自动加上正确的Content-Type                  
		            */                 
		           contentType: false,                  
		           /**                  
		           * 必须false才会避开jQuery对 formdata 的默认处理                  
		           * XMLHttpRequest会对 formdata 进行正确的处理                  
		           */                 
		           processData: false             
		       })
		       .success(function(data) {
		 		  var obj = document.getElementById('imageUpLoad'); 
				  obj.outerHTML=obj.outerHTML;
		 		  console.log("success!");
		 		  if(data.success){
		 		  	alert(data.msg);
		 		  	upLoadImageCallBack(data);
		 		  }
		 		})
		 		.error(function(error) {
		 			console.log(error);
		 			alert("文件上传失败：文件过大或者网络异常");
		 		})
		 		.complete(function() {
		 			console.log("complete");
		 			var obj = document.getElementById('imageUpLoad'); 
					obj.outerHTML=obj.outerHTML; 
		 		});
 		} else{
 			alert("请先选择上传文件");
 		};
 		
 	};

 	var upLoadImageCallBack = function(data) {
 		var media_id = data.media_id;
 		var media_type = data.media_type;
 		$("#upLoadResult").html(data.msg);
 		$("#imageMediaId").html(media_id+","+media_type);
 	};

 	//发送给用户
 	var sendToUser = function(){
 		var media = $("#imageMediaId").html();
 		var media_id = media.split(",")[0];
 		var media_type = media.split(",")[1];
 		if (media.split(",")[0] !="") {
 			var postData = JSON.stringify({media_id: media_id,media_type: media_type});
 			$.ajax({
 				url: 'web/posttouser',
 				type: 'POST',
 				dataType: 'json',
 				contentType: 'application/json',
 				data: postData
 			})
 			.done(function(data) {
 				console.log("success");
 				$("#imageMediaId").html("");
 				$("#upLoadResult").html(data.msg);
 			})
 			.fail(function(result) {
 				console.log("error");
 			})
 			.always(function() {
 				console.log("complete");
 			});
 		} else{
 			alert("请先上传群发素材");
 		};
 	}

 	var sendText = function(){
 		var textMsg = $("#bulkContent").val();
 		var textMsgVal = JSON.stringify({textMsg: textMsg});
 		if (textMsg != "") {
 			$.ajax({
 				url: 'web/sendmsg',
 				type: 'POST',
 				dataType: 'json',
 				contentType: 'application/json',
 				data: textMsgVal
 			})
 			.done(function(data) {
 				console.log("success");
 				console.log(data);
 				$("#sendTextResult").html(data.msg);
 			})
 			.fail(function() {
 				console.log("error");
 			})
 			.always(function() {
 				console.log("complete");
 			});
 			
 		} else{
 			alert("请填写消息");
 		};
 	}


 	var sendArtic = function(){
 		var articTitle = $("#articTitle").val();
 		var articDesc = $("#articDesc").val();
 		if (articTitle == "") {
 			alert("请填写标题");
 		} else if (articDesc == "") {
 			alert("请填写文章描述");
 		}else {
 			var postData = JSON.stringify({articTitle:articTitle,articDesc:articDesc});
 			$.ajax({
 				url: 'web/sendartic',
 				type: 'POST',
 				dataType: 'json',
 				contentType: 'application/json',
 				data: postData,
 			})
 			.done(function(data) {
 				console.log("success");
 				console.log(data);
 				$("#sendArticResult").html(data.msg);
 			})
 			.fail(function() {
 				console.log("error");
 			})
 			.always(function() {
 				console.log("complete");
 			});
 		}
 		
 	}


})();
	