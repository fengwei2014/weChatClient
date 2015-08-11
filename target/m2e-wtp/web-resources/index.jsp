<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
  request.setCharacterEncoding("utf-8");
  String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>活点地图</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="<%=basePath%>/img/favicon.ico">
   <!-- Loading Bootstrap -->
    <link href="<%=basePath%>/resources/widget/Flat-UI/dist/css/vendor/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>/resources/css/main.css" rel="stylesheet">
</head>

<body class="clearfix">

<div class="banner-wrap clearfix">
    <div class="slider">
        <div class="section section1">
            <div class="title">
                <div class="title-ico"></div>
                <div class="title-1">活点地图</div>
                <div class="line"></div>
                <div class="title-2">高品质物流运输服务的象征</div>
            </div>
        </div>
        <div class="section section2">
            <div class="title">
                <div class="title-1">活点地图</div>
                <div class="title-2">运输过程透明管理专家</div>
            </div>
        </div>
        <div class="section section3">

            <div class="title">
                <div class="title-1">活点地图</div>
                <div class="title-2">专业的位置轨迹大数据服务</div>
            </div>
        </div>
        <div class="section section4">

            <div class="title">
                <div class="title-1">活点地图</div>
                <div class="title-2">专业的地理围栏服务</div>
            </div>
        </div>
        <div class="section section5">

            <div class="title">
                <div class="title-1">活点地图</div>
                <div class="title-2">专业的基于规则引擎的报警服务</div>
            </div>
        </div>
    </div>

    <div class="nav-bottom">
        <li class="nav-bottom-item">
            <a data-index="0" href="javascript:void(0)" class="nav-bottom-item-anchor anchor-0 current"></a>
        </li>
        <li class="nav-bottom-item">
            <a data-index="1" href="javascript:void(0)" class="nav-bottom-item-anchor anchor-1"></a>
        </li>
        <li class="nav-bottom-item">
            <a data-index="2" href="javascript:void(0)" class="nav-bottom-item-anchor anchor-2"></a>
        </li>
        <li class="nav-bottom-item">
            <a data-index="3" href="javascript:void(0)" class="nav-bottom-item-anchor anchor-3"></a>
        </li>
        <li class="nav-bottom-item">
            <a data-index="4" href="javascript:void(0)" class="nav-bottom-item-anchor anchor-4"></a>
        </li>
    </div>
</div>
<div class="yy-intro">
    <h2>核心功能</h2>
    <div class="yy-intro-content">


        <div class="yy-intro-block">
            <h3> <i>1</i><span>看板服务</span></h3>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>总览您业务的运营情况。</span>
            </p>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>试试活点地图吧。专业的大数据分析服务，让您决策不再困难。</span>
            </p>
        </div>
        <div class="yy-intro-block">
            <h3> <i>2</i><span>车辆定位服务</span></h3>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>实时掌握车辆的地理位置、状态信息。</span>
            </p>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>试试活点地图吧。专业的位置定位服务，让你的车辆在途无忧。</span>
            </p>
        </div>
        <div class="yy-intro-block">
            <h3> <i>3</i><span>轨迹回放服务</span></h3>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>查询车辆的行驶轨迹，掌握车辆的行驶记录。</span>
            </p>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>试试活点地图吧。专业的轨迹大数据服务，可视化轨迹服务。</span>
            </p>
        </div>


        <div class="yy-intro-block">
            <h3> <i>4</i><span>我的运单服务</span></h3>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>运单现在到哪了？喂，王师傅，你现在到哪了？喂，李师傅、张师傅、马师傅、牛师傅......喂......</span>
            </p>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>试试活点地图吧。运单位置，想看就看，让货主省心放心。</span>
            </p>
        </div>
        <div class="yy-intro-block">

            <h3><i>5</i><span>车辆管理</span></h3>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>管理车辆的基本信息和司机的基本信息。</span>
            </p>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>试试活点地图吧。低门槛管理车辆，让物流企业省心放心。</span>
            </p>
        </div>
        <div class="yy-intro-block">

            <h3><i>6</i><span>报警服务&展现</span></h3>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>丰富的车辆报警管理及多种提醒方式。</span>
            </p>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>试试活点地图吧。方便您对您的车辆司机进行考核。</span>
            </p>
        </div>
        <div class="yy-intro-block">
            <h3><i>7</i><span>报表系统&分析</span></h3>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>多种基础和扩展报表，全面的数据展现。</span>
            </p>
            <p class="yy-intro-p yy-intro-p1" style="margin-left:-120px">
                <span>试试活点地图吧。方便您对您的车辆和货物运输情况有全方面了解.还有大数据的分析报表哦。</span>
            </p>
        </div>
    </div>
</div>
<div class="yy-industry">
    <h2>行业方案</h2>
    <div class="yy-industry-content">
        <div class="yy-industry-block">
            <div class="yy-industry-block-box">
                <div class="yy-industry-icon1 yy-industry-icon"></div>
                <h3 class="yy-industry-h3-1">危化物流行业</h3>
                <div class="yy-industry-img1 yy-industry-img">
                    <div class="img-mask"></div>
                </div>
            </div>
        </div>
        <div class="yy-industry-block">
            <div class="yy-industry-block-box">
                <div class="yy-industry-icon2 yy-industry-icon"></div>
                <h3 class="yy-industry-h3-2">冷链物流行业</h3>
                <div class="yy-industry-img2 yy-industry-img">
                    <div class="img-mask"></div>
                </div>
            </div>
        </div>
        <div class="yy-industry-block">
            <div class="yy-industry-block-box">
                <div class="yy-industry-icon3 yy-industry-icon"></div>
                <h3 class="yy-industry-h3-3">城市配送行业</h3>
                <div class="yy-industry-img3 yy-industry-img">
                    <div class="img-mask"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=basePath%>/resources/widget/Flat-UI/dist/js/vendor/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath%>/resources/widget/Flat-UI/dist/js/flat-ui.min.js"></script>
<script type="text/javascript">
    // var section_bgs = ['section-bg-1.png', 'section-bg-2.jpg', 'section-bg-3.jpg'];
    var index = 0;
    var timer = null;
    var animateEvents = {
        0: {
            bg: function() {
                $('.banner-wrap').css('background', 'url(/weChat/resources/images/banner-bg.jpg)');
            },
            func: function() {
                $('.section1 .img,.section1 .title').addClass('active');
            },
            reset: function() {
                $('.section1 .img,.section1 .title').removeClass('active');
            }
        },
        1: {
            bg: function() {
                $('.banner-wrap').css('background', 'url(/weChat/resources/images/banner-bg.jpg)');
            },
            func: function() {
                $('.section2 .img,.section2 .title').addClass('active');
            },
            reset: function() {
                $('.section2 .img,.section2 .title').removeClass('active');
            }
        },
        2: {
            bg: function() {
                $('.banner-wrap').css('background', 'url(/weChat/resources/images/banner-bg.jpg)');
            },
            func: function() {
                $('.section3 .img,.section3 .title').addClass('active');
            },
            reset: function() {
                $('.section3 .img,.section3 .title').removeClass('active');
            }
        },
        3: {
            bg: function() {
                $('.banner-wrap').css('background', 'url(/weChat/resources/images/banner-bg.jpg)');
            },
            func: function() {
                $('.section4 .img,.section4 .title').addClass('active');
            },
            reset: function() {
                $('.section4 .img,.section4 .title').removeClass('active');
            }
        },
        4: {
            bg: function() {
                $('.banner-wrap').css('background', 'url(/weChat/resources/images/banner-bg.jpg)');
            },
            func: function() {
                $('.section5 .img,.section5 .title').addClass('active');
            },
            reset: function() {
                $('.section5 .img,.section5 .title').removeClass('active');
            }
        }
    }

    function bannerPlay(cur) {
        animateEvents[cur]['bg']();
        $('.nav-bottom-item-anchor').removeClass('current');
        $('.anchor-' + cur).addClass('current');
        $('.section').css('opacity', '0');
        $('.section' + (cur + 1)).css('opacity', '1');
        animateEvents[cur]['func']();
        if (cur == 0) {
            setTimeout(function() {
                animateEvents[4]['reset']();
            }, 500);
        } else {
            setTimeout(function() {
                animateEvents[cur - 1]['reset']();
            }, 500);
        }
    }
    $(document).ready(function($) {
        bannerPlay(0);
        timer = setInterval(function() {
            if (index >= 5) {
                index = 0;
            }
            bannerPlay(index);
            index++;
        }, 2500);
        $('.nav-bottom-item-anchor').bind('click', function() {
            index = parseInt($(this).attr('data-index'));
            bannerPlay(index);
        })
    });
</script>
</html>
