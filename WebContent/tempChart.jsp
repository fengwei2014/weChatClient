<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
  request.setCharacterEncoding("utf-8");
  String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <script type="text/javascript">
      window.contextRoot = "${pageContext.request.contextPath}";
      window.actionPath = window.contextRoot;
    </script>
    <meta charset="utf-8">
    <title>历史轨迹和温度曲线</title>
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
 <div id="result" style="text-align: center;font-size:12px;color:red;"></div>
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          历史轨迹
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body" style="padding:0px;">
        <div id="l-map" style="height:400px"></div>
      </div>
    </div>
  </div>
  <div class="panel panel-default" style="margin-top:0px;">
    <div class="panel-heading" role="tab" id="headingTwo">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          温度曲线
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
      <div class="panel-body" style="padding:0px;">
      <div class="row">
        <div class="col-lg-12 col-sm-12 col--md-12 col-xs-12">
           <div id="main" style="height:380px"></div>
        </div>
      </div>
      </div>
    </div>
  </div>
</div>




    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=basePath%>/resources/widget/Flat-UI/dist/js/vendor/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath%>/resources/widget/Flat-UI/dist/js/flat-ui.min.js"></script>
    <!-- echarts -->
    <script src="<%=basePath%>/resources/widget/echarts-2.2.3/build/dist/echarts.js"></script>
    <script src="<%=basePath%>/resources/js/tempChart.js"></script>
    
    <script type="text/javascript" src="http://api.map.baidu.com/api?type=quick&ak=6UcbcaBTk6Dm5lauUlljqlAA&v=1.0"></script> 
   <script type="text/javascript">


   		var radius=6378.137,
		interval=2,
		x_pi = Math.PI * 3000.0 / 180.0,
		a =6378245.0,
		ee = 0.00669342162296594323;

	//didn't use for consideration of performance , because always in China
		var outOfChina = function (lat, lng) {
			if ((lng < 72.004) || (lng > 137.8347)) {
				return true;
			}
			if ((lat < 0.8293) || (lat > 55.8271)) {
				return true;
			}
			return false;
		};

		var transformLat = function (x, y) {
			var ret = -100.0 + 2.0*x + 3.0*y + 0.2*y*y + 0.1*x*y + 0.2*Math.sqrt(Math.abs(x));
			ret += (20.0*Math.sin(6.0*x*Math.PI) + 20.0*Math.sin(2.0*x*Math.PI)) * 2.0 / 3.0;
			ret += (20.0*Math.sin(y*Math.PI) + 40.0*Math.sin(y/3.0*Math.PI)) * 2.0 / 3.0;
			ret += (160.0*Math.sin(y/12.0*Math.PI) + 320*Math.sin(y*Math.PI/30.0)) * 2.0 / 3.0;
			return ret;
		};

		var transformLon = function (x, y) {
			var ret = 300.0 + x + 2.0*y + 0.1*x*x + 0.1*x*y + 0.1*Math.sqrt(Math.abs(x));
			ret += (20.0*Math.sin(6.0*x*Math.PI) + 20.0*Math.sin(2.0*x*Math.PI)) * 2.0 / 3.0;
			ret += (20.0*Math.sin(x*Math.PI) + 40.0*Math.sin(x/3.0*Math.PI)) * 2.0 / 3.0;
			ret += (150.0*Math.sin(x/12.0*Math.PI) + 300.0*Math.sin(x/30.0*Math.PI)) * 2.0 / 3.0;
			return ret;
		};

		var delta = function (lat, lng) {

			var dLat = this.transformLat(lng-105.0, lat-35.0);
			var dLng = this.transformLon(lng-105.0, lat-35.0);
			var radLat = lat / 180.0 * Math.PI;
			var magic = Math.sin(radLat);
			magic = 1 - this.ee*magic*magic;
			var sqrtMagic = Math.sqrt(magic);
			dLat = (dLat * 180.0) / ((this.a * (1 - this.ee)) / (magic * sqrtMagic) * Math.PI);
			dLng = (dLng * 180.0) / (this.a / sqrtMagic * Math.cos(radLat) * Math.PI);
			return {"lat": dLat, "lng": dLng};
		};

		var wgs2gcj = function (wgsLat, wgsLng) {
			var d = this.delta(wgsLat, wgsLng);
			return {"lat": wgsLat + d.lat, "lng": wgsLng + d.lng};
		};


		var gcj2wgs = function (gcjLat, gcjLng) {

			var d = this.delta(gcjLat, gcjLng);
			return {"lat": gcjLat - d.lat, "lng": gcjLng - d.lng};
		};

		var gcj2bd = function (lat, lng)  
		{         

			var z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * this.x_pi);  
			var theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * this.x_pi);  
			var bd_lon = z * Math.cos(theta) + 0.0065;  
			var bd_lat = z * Math.sin(theta) + 0.006;  
			return {"lat": bd_lat, "lng": bd_lon};
		};


		var bd2gcj = function ( bd_lat,  bd_lon )
		{  

			var x = bd_lon - 0.0065, y = bd_lat - 0.006;  
			var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * this.x_pi);  
			var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * this.x_pi);  
			var gg_lon = z * Math.cos(theta);  
			var gg_lat = z * Math.sin(theta);  
			return {"lat": gg_lat, "lng": gg_lon};
		};



		//GPS(wgs-84) -> 百度（bd-09）
		var wgs2bd = function (wgsLat,wgsLng){
			var gcj = this.wgs2gcj(wgsLat,wgsLng);
			return this.gcj2bd(gcj.lat,gcj.lng);
		};

		//百度（bd-09）-> GPS(wgs-84)
		var bd2wgs = function (lat,lng){
			var gcj=bd2gcj(lat,lng);
			return gcj2wgs(gcj.lat,gcj.lng);	
		};

		//GPS(wgs-84) -> 百度（bd-09）
		var wgs2bds = function (points){
			var ret=new Array();
			for(var index in points){
				ret.push(wgs2bd(points[index].lat,points[index].lng));
			}
			return ret;	
		};

		//百度（bd-09）-> GPS(wgs-84)
		var bd2wgss = function (points){
			var ret=new Array();
			for(var index in points){
				ret.push(bd2wgs(points[index].lat,points[index].lng));
			}
			return ret;	
		};



		var gcj2wgs_exact = function (gcjLat, gcjLng) {
			var initDelta = 0.01;
			var threshold = 0.000001;
			var dLat = initDelta, dLng = initDelta;
			var mLat = gcjLat-dLat, mLng = gcjLng-dLng;
			var pLat = gcjLat+dLat, pLng = gcjLng+dLng;
			var wgsLat, wgsLng;
			for (var i = 0; i < 30; i++) {
				wgsLat = (mLat+pLat)/2;
				wgsLng = (mLng+pLng)/2;
				var tmp = this.wgs2gcj(wgsLat, wgsLng)
				dLat = tmp.lat-gcjLat;
				dLng = tmp.lng-gcjLng;
				if ((Math.abs(dLat) < threshold) && (Math.abs(dLng) < threshold)) {
					return {"lat": wgsLat, "lng": wgsLng};
				}
				if (dLat > 0) {
					pLat = wgsLat;
				} else {
					mLat = wgsLat;
				}
				if (dLng > 0) {
					pLng = wgsLng;
				} else {
					mLng = wgsLng;
				}
			}
			return {"lat": wgsLat, "lng": wgsLng};
		};

   var filePath = window.location.origin+window.contextRoot+"/";
    $.ajax({
        url: 'web/history',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json'
    })
    .done(function(data) {
        console.log("success");
        if (data.pojo.obj.length > 0) {
          showLineOnMap(data.pojo);
        } else{
          $("#result").html("历史数据为空，请及时留言反馈给我们");
        };
        
    })
    .fail(function() {
        console.log("error");
    })
    .always(function() {
        console.log("complete");
    });

   var searchArray  = window.location.search.split("&");


   var indexNo = searchArray[0].indexOf(",");
   var lon = searchArray[0].substring(1,indexNo);
   var lat = searchArray[0].substring(indexNo+1,searchArray[0].length);
   // var vehicleNo = decodeURIComponent(searchArray[1].substring(3,searchArray[1].length));
   // var address = decodeURIComponent(searchArray[2].substring(6,searchArray[2].length));

   var map = new BMap.Map("l-map");
   map.addControl(new BMap.ZoomControl()); //添加地图缩放控件

   //在地图上画线
   var showLineOnMap = function(pointObj){

      var pointList = pointObj.obj;
      // var startLebel = pointObj.startPlaceName;
      // var endLebel = pointObj.endPlaceName;
      var lastPoint = new BMap.Point(pointList[pointList.length-1].longitude,pointList[pointList.length-1].latitude);
      map.centerAndZoom(lastPoint, 8);
      

      var startIcon = new BMap.Icon(filePath+"resources/images/p_start.png", new BMap.Size(40, 40));
      var endIcon = new BMap.Icon(filePath+"resources/images/p_end.png", new BMap.Size(40, 40));
      // 设置起点
      // var marker1 = new BMap.Marker(new BMap.Point(pointList[0].lon02, pointList[0].lat02),{icon: startIcon});  //创建标注
      // var marker2 = new BMap.Marker(new BMap.Point(pointList[pointList.length-1].lon02, pointList[pointList.length-1].lat02),{icon: endIcon});
      // map.addOverlay(marker1);
      // map.addOverlay(marker2);                   // 将标注添加到地图中
      // //创建信息窗口
      // var infoWindow1 = new BMap.InfoWindow(startLebel);
      // var infoWindow2 = new BMap.InfoWindow(endLebel);
      // marker1.addEventListener("click", function(){this.openInfoWindow(infoWindow1);});
      // marker2.addEventListener("click", function(){this.openInfoWindow(infoWindow2);});

      var points = [];
      for (var i = 0; i < pointList.length; i++) {
      	if (pointList[i].latitude > 0){
				var offsetPoint = wgs2bd(pointList[i].latitude , pointList[i].longitude);
				var point = new BMap.Point(offsetPoint.lng,offsetPoint.lat);

		        points.push(point);
      	}
		
      };

      var polyline = new BMap.Polyline(points, {strokeColor:"red", strokeWeight:6, strokeOpacity:0.9});
      map.addOverlay(polyline);
      map.setViewport(points);

   }



   </script>
   

  </body>
</html>

