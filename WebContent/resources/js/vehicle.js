(function(){
	var PROJECT_NAME = "http://119.254.101.149/TT_WeiXin";
	// var PROJECT_NAME = "http://tt.tunnel.mobi/TT_WeiXin";
	var vehicleNo = $("#vehicleNo");
	var searchBtn = $("#searchBtn");
	var rootPath =window.location.href;

	//获取全部车辆
	var getAllVehicles = function(){
		$.ajax({
			url: 'resources/json/vehicles.json',
			type: 'GET',
			dataType: 'json',
			success: function(data){
				console.log("success");
				if(data.pojo.obj.length != 0){
					showVehicleList(data.pojo.obj);
				}else{
					alert("No vehicles now!");
				}
			},
			error:function (error) {
				console.log(error);
				console.log("error");
			}
		});
	};

	//获取车辆数据
	getAllVehicles();
	
 	// 车牌格式验证
 	vehicleNo.on('change', validateVehicleNo);
 	
 	//搜索车辆
 	searchBtn.on('click', function(){
 		
		//清除之前的车辆列表
 		if($("#vehicleList").children().length != 0){
 			$("#vehicleList").children().remove();
 		};
 		searchVehicle(vehicleNo.val(),this.id);
	 	
 		
 	});

 	//搜索车辆
 	var searchVehicle = function(number,node){
 		
		$.ajax({
 			url: 'vehicle',
 			type: 'POST',
 			dataType: 'json',
 			data: {vehicleNo: number}
 		})
 		.success(function(data) {
 			console.log("success");
 			if (data.success) {
 				if(node == "searchBtn"){
 					showSearchResult(data.location);
 				}else{
 					showOrHidevehicleDetail(data,node);
 				}
 			} else{
 				$("#loginResult").html(data.msg);
 			};
 		})
 		.error(function(error) {
 			console.log(error);
 		})
		
 	};


 	// 展示请求数据
 	var showSearchResult = function(data) {
 		var vehiclesHtml = "";
 		vehiclesHtml += "<hr style=\"margin:0px;\">"+
					           "<div>"+
					           		"<img src=\""+PROJECT_NAME+"/resources/images/car.gif\"/><label style=\"color: #1abc9c;\" class=\"js-vehicleNo\">"+vehicleNo.val()+"</label><a href=\"javascript:void(0)\" style=\"float:right\" class=\"js-showMore\">&gt;</a>"+
						           "<div class=\"container\" style=\"background-color: #EAEAEA;\"></div>"+
					           "</div>";
		document.getElementById('vehicleList').innerHTML = vehiclesHtml;
 		bindShowMoreEvent();

 	};

 	//展示车辆数据
 	var showVehicleList = function(data){
 		var vehiclesHtml = "";
 		for (var i = 0; i < data.length; i++) {
 			vehiclesHtml += "<hr style=\"margin:0px;\">"+
					           "<div>"+
					           		"<img src=\""+PROJECT_NAME+"/resources/images/car.gif\"/><label style=\"color: #1abc9c;\" class=\"js-vehicleNo\">"+data[i].vehicleNo+"</label><a href=\"javascript:void(0)\" style=\"float:right\" class=\"js-showMore\">&gt;</a>"+
						           "<div class=\"container\" style=\"background-color: #EAEAEA;\"></div>"+
					           "</div>";
 		};
 		document.getElementById('vehicleList').innerHTML = vehiclesHtml;

 		bindShowMoreEvent();

 	};

 	var bindShowMoreEvent = function() {
 		//对车牌号绑定
 		$(".js-vehicleNo").on("click",function(){
 			var number = this.parentNode.getElementsByTagName('label')[0].innerHTML;
 			console.log("vehicleNo: "+number);
 			if (this.parentNode.getElementsByClassName("container")[0].children.length != 0) {
 				$(this.parentNode.getElementsByClassName("container")[0]).children().remove();
 			} else{
 				searchVehicle(number,this);
 			};
 		});

 		//对后置的小标志绑定
 		$(".js-showMore").on("click",function(){
 			var number = this.parentNode.getElementsByTagName('label')[0].innerHTML;
 			console.log("vehicleNo: "+number);
 			if (this.parentNode.getElementsByClassName("container")[0].children.length != 0) {
 				$(this.parentNode.getElementsByClassName("container")[0]).children().remove();
 			} else{
 				searchVehicle(number,this);
 			};
 		});
 	};

 	//展示或者隐藏车辆详细的面板
 	var showOrHidevehicleDetail = function(data,node){
 		var _vehicleNo = "";
 		if (node.className =="js-showMore") {
 			_vehicleNo = node.parentNode.getElementsByClassName('js-vehicleNo')[0].innerHTML;
 		} else{
 			_vehicleNo = node.innerHTML;
 		};
 		var detailPalntHtml = "<hr style=\"margin:0px;\">"+
							  "<p style=\"font-size:12px;margin-bottom:0px;\">地址：浙江省宁波市鄞州区院士路(宁波洲际酒店南482米)</p>"+
							  "<hr style=\"margin:0px;\">"+
							  "<img src=\""+PROJECT_NAME+"/resources/images/car.gif\"/>"+
							  "<a href=\""+PROJECT_NAME+"/location.jsp?"+data.location.lng+","+data.location.lat+"&No="+_vehicleNo+"&label="+data.location.address+"\">位置</a>"+
							  "<img src=\""+PROJECT_NAME+"/resources/images/car.gif\"/>"+
							  "<a href=\""+PROJECT_NAME+"/location.jsp?"+data.location.lng+","+data.location.lat+"&No="+_vehicleNo+"&label="+data.location.address+"\">轨迹</a>"+
							  "<img src=\""+PROJECT_NAME+"/resources/images/car.gif\"/><a>报警</a>"+
							  "<img src=\""+PROJECT_NAME+"/resources/images/car.gif\"/><a>报表</a>";
 		// $("#vehicleList").append(detailPalntHtml);
 		node.parentNode.childNodes[3].innerHTML = detailPalntHtml;
 	}
 	/**
	*车牌号非空验证 
	*/
	var ValidationNotNull = function(){
		if (vehicleNo.val().length == 0) {
			alert("请输入想要查询的车牌号");
				return false;
			}else{
				return true;
			}
	};


	/***
		验证车牌输入
	*/
	var validateVehicleNo = function(){

	};


})();
