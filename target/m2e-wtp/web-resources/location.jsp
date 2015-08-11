<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
  request.setCharacterEncoding("utf-8");
  String basePath = request.getContextPath();
%>
 
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" /> 
<link href="<%=basePath%>/resources/images/weixinImg.ico" rel="Shortcut Icon">
  <!-- css文件 -->
<link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap.css">
<style type="text/css">  
body, html,#l-map {width: 100%;height: 100%;overflow: hidden;margin:0;}  
</style>  
<script type="text/javascript" src="http://api.map.baidu.com/api?type=quick&ak=6UcbcaBTk6Dm5lauUlljqlAA&v=1.0"></script>  
<title>车辆位置</title>  
</head>  
<body>
<div id="l-map"></div>
</body>
<script type="text/javascript">
var searchArray  = window.location.search.split("&");


var indexNo = searchArray[0].indexOf(",");
var lon = searchArray[0].substring(1,indexNo);
var lat = searchArray[0].substring(indexNo+1,searchArray[0].length);
var vehicleNo = decodeURIComponent(searchArray[1].substring(3,searchArray[1].length));
var address = decodeURIComponent(searchArray[2].substring(6,searchArray[2].length));

var map = new BMap.Map("l-map");
var point = new BMap.Point(lon, lat);  
map.centerAndZoom(point, 14);  
var zoomControl=new BMap.ZoomControl();  
map.addControl(zoomControl);//添加缩放控件 

var marker = new BMap.Marker(new BMap.Point(lon, lat)); // 创建标注      
map.addOverlay(marker);


// var myIcon = new BMap.Icon("http://api.map.baidu.com/mapCard/img/location.gif",   
// 	new BMap.Size(14, 23), {      
// 	// 指定定位位置。     
// 	// 当标注显示在地图上时，其所指向的地理位置距离图标左上      
// 	// 角各偏移7像素和25像素。您可以看到在本例中该位置即是     
// 	// 图标中央下端的尖角位置。      
// 	anchor: new BMap.Size(7, 25),        
// }); 
// var marker = new BMap.Marker(point, {icon: myIcon});      
// map.addOverlay(marker); 

// 定义自定义覆盖物的构造函数    
  function SquareOverlay(center, length, width, color){      
    this._center = center;      
    this._length = length; 
    this._width = width;     
    this._color = color;      
  }      
// 继承API的BMap.Overlay      
  SquareOverlay.prototype = new BMap.Overlay();

  // 实现初始化方法    
SquareOverlay.prototype.initialize = function(map){      
// 保存map对象实例     
    this._map = map;          
// 创建div元素，作为自定义覆盖物的容器     
    var div = document.createElement("div");      
    div.style.position = "absolute";          
// 可以根据参数设置元素外观     
    div.style.width = this._width + "px";      
    div.style.height = this._length + "px";      
    div.style.background = this._color;
    div.style.opacity = 0.9;
    div.style.borderRadius = "10px";

    var pTitle = document.createElement("label");
    pTitle.setAttribute("id","vehicleNoTitle");
    pTitle.style.marginLeft = "10px";
    pTitle.style.marginTop = "10px";
    pTitle.style.marginBottom = "0px";
   
    var pAddress = document.createElement("p");
    pAddress.setAttribute("id","vehicleAddress");
    pAddress.style.marginLeft = "10px";
    pAddress.style.marginBottom = "0px";
    pAddress.style.marginTop = "2px";
    pAddress.style.fontSize = "12px";
   
    var closeSpan = document.createElement("span");
    closeSpan.style.float = "right";
    closeSpan.setAttribute("id","closeLabel");
    closeSpan.style.marginRight = "10px";
    closeSpan.style.marginTop = "7px";
    closeSpan.style.fontSize = "16px";
    closeSpan.innerHTML = "×";
  
    pTitle.innerHTML = vehicleNo;
    pAddress.innerHTML = address;
   
    div.appendChild(pTitle);
    // div.appendChild(closeSpan);
    div.appendChild(document.createElement("br"));
    div.appendChild(pAddress);
// 将div添加到覆盖物容器中     
    map.getPanes().markerPane.appendChild(div);        
// 保存div实例     
    this._div = div;        
// 需要将div元素作为方法的返回值，当调用该覆盖物的show、     
// hide方法，或者对覆盖物进行移除时，API都将操作此元素。     
    return div;      
}

// 实现绘制方法     
SquareOverlay.prototype.draw = function(){      
// 根据地理坐标转换为像素坐标，并设置给容器      
   var position = this._map.pointToOverlayPixel(this._center);      
   this._div.style.left = position.x - this._length + "px";      
   this._div.style.top = position.y - this._length -25 + "px";      
}

// 实现显示方法      
SquareOverlay.prototype.show = function(){      
    if (this._div){      
        this._div.style.display = "";      
    }      
}        
// 实现隐藏方法    
SquareOverlay.prototype.hide = function(){      
    if (this._div){      
        this._div.style.display = "none";      
    }      
}

// 添加自定义覆盖物     
 var mySquare = new SquareOverlay(map.getCenter(), 100, 200, "#FFF");      
 map.addOverlay(mySquare);
// var opts = {      
//     width : 100,     // 信息窗口宽度      
//     height: 100,     // 信息窗口高度      
//     title : vehicleNo  // 信息窗口标题     
// }      
// var infoWindow = new BMap.InfoWindow(address, opts);  // 创建信息窗口对象 
// map.openInfoWindow(infoWindow, marker.getPosition());      // 打开信息窗口

//给标注添加点击事件
marker.addEventListener("click", function(){
	if (mySquare._div.style.display == "none") {
		mySquare.show();
	} else{
		mySquare.hide();
	};
});

// document.getElementById('closeLabel').addEventListener("click",function(){
// 	mySquare.hide();
// })

</script>
<script src="<%=basePath%>/resources/js/jquery-1.11.1.js"></script>
</html>