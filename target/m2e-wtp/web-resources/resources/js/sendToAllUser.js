(function(){
	
	var bindBtn = $("#sendBtn");
	var bulkContent = $("#bulkContent");
	var rootPath =window.location.href;
	
 	bindBtn.on('click', function(){
 		var data = bulkContent.val();
 		if (data != null) {
 			if(confirm("您要群发的内容是："+data)){
				$.ajax({
		 			url: 'sendToAllUser',
		 			type: 'POST',
		 			dataType: 'text/html',
		 			data:{content:data}
		 		})
		 		.success(function(data) {
		 			console.log("success!")
		 		})
		 		.error(function(error) {
		 			console.log(error);
		 		})
		 		.complete(function() {
		 			console.log("complete");
		 		});
 			}	
 		}else{
 			alert("请编辑群发内容");
 		};
		
 	});

 	// 上传文件
 	var upLoadFile = function() {
	   $('#imageFile').submit(function () {              
	   var formdata = new FormData();              
	   var fileObj = document.getElementById("imageUpLoad").files;              
	                       
	       formdata.append("file", fileObj[0]);              
	       $.ajax({                  
	           type: 'POST',                  
	           url: 'testImage',                  
	           data: {formdata:formdata},                  
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
	 		  console.log("success!");
 			  var obj = document.getElementById('imageUpLoad'); 
				obj.outerHTML=obj.outerHTML; 
	 		})
	 		.error(function(error) {
	 			console.log(error);
	 		})
	 		.complete(function() {
	 			console.log("complete");
	 			var obj = document.getElementById('imageUpLoad'); 
				obj.outerHTML=obj.outerHTML; 
	 		});           
	       return false;          
	   });
 	}

 	
	$("#imageSub").on('click', function(event) {
		upLoadFile();
	});

})();
	