// 路径配置
require.config({
    paths: {
        echarts: 'http://echarts.baidu.com/build/dist'
    }
});

// 使用
require(
    [
        'echarts',
        'echarts/chart/line' // 使用折线图图就加载line模块
    ],
    function (ec) {
       
        //日期转换格式转换----函数
        var formatDate = function (date,format){
              var o = {
                "M+" : date.getMonth()+1, //month
                "d+" : date.getDate(),    //day
                "h+" : date.getHours(),   //hour
                "m+" : date.getMinutes(), //minute
                "s+" : date.getSeconds(), //second
                "q+" : Math.floor((date.getMonth()+3)/3),  //quarter
                "S" : date.getMilliseconds() //millisecond
              }
              if(/(y+)/.test(format)) format=format.replace(RegExp.$1,(date.getFullYear()+"").substr(4 - RegExp.$1.length));
              for(var k in o) if(new RegExp("("+ k +")").test(format))
                  format = format.replace(RegExp.$1,
                  RegExp.$1.length==1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length));
              return format;
            }


        $.ajax({
             url: 'web/history',
             type: 'GET',
             dataType: 'json',
             contentType: 'application/json'
        })
        .done(function(data) {
            console.log("success");
            showCharts(data.pojo.obj);
        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
        });

        var showCharts = function(dataArray){
            var xData =[];
            var yData1 = [];
            var yData2 = [];


            for (var i = 0; i < dataArray.length; i++) {
                yData1.push(dataArray[i].thermometerLogList[0].curTemperature);
                yData2.push(dataArray[i].thermometerLogList[1].curTemperature);
                var dTime = new Date();
                dTime.setTime(dataArray[i].createdTimestamp);
                xData.push(formatDate(dTime,"hh:mm"));
            };


            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('main')); 
            
            
            var option11 = {
           
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['温度计1','温度计2']
            },
            toolbox: {
                show : true,
                feature : {
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : xData
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLabel : {
                        formatter: '{value} °C'
                    },
                    splitNumber:3
                }
            ],
            series : [
                {
                    name:'温度计1',
                    type:'line',
                    data: yData1,
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                },
                {
                    name:'温度计2',
                    type:'line',
                    data: yData2,
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name : '平均值'}
                        ]
                    }
                }
            ]
        };
                    
            // 为echarts对象加载数据 
            myChart.setOption(option11); 

   
 

        }
    }
);