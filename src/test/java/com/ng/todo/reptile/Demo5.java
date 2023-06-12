package com.ng.todo.reptile;

import cn.hutool.core.collection.CollUtil;
import com.ng.todo.common.enums.ReptileTypeEnum;
import com.ng.todo.common.enums.SchoolTypeEnum;
import com.ng.todo.common.utils.MyHttpUtils;
import com.ng.todo.entity.SchoolFractionInfo;
import com.ng.todo.pojo.dto.InfoData;
import com.ng.todo.pojo.dto.MyHttpParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public class Demo5 {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static final String URL = "https://college.gaokao.com/spepoint/y2022/";
    public static final ReptileTypeEnum TYPE_ENUM = ReptileTypeEnum.Jsoup;
    public static final RequestMethod METHOD = RequestMethod.GET;


    @Test
    public void test1() {
        org.jsoup.nodes.Document document = Jsoup.parse(getTextV());
        Element body = document.body();
        Element wrapper = body.getElementById("wrapper");
        Elements tableList = wrapper.select("table");
        if (CollUtil.isEmpty(tableList)) {
            return;
        }
        List<SchoolFractionInfo> infoList = new ArrayList<>() ;
        for (Element table : tableList) {
            Element tbody = table.selectFirst("tbody");
            Elements trList = tbody.select("tr");
            if (CollUtil.isEmpty(trList)) {
                continue;
            }
            for (int i = 0; i < trList.size(); i++) {
                if (i == 0) {
                    continue;
                }
                Element element = trList.get(i);
                Elements children = element.children();

                SchoolFractionInfo schoolFractionInfo = new SchoolFractionInfo() ;
                schoolFractionInfo.setSpeciality(children.get(0).text()) ;
                schoolFractionInfo.setSchool(children.get(1).text()) ;
                schoolFractionInfo.setFraction(children.get(2).text()) ;
                schoolFractionInfo.setMaxScore(children.get(3).text()) ;
                schoolFractionInfo.setProvince(children.get(4).text()) ;
                schoolFractionInfo.setType(children.get(5).text()) ;
                schoolFractionInfo.setYear(children.get(6).text()) ;
                schoolFractionInfo.setBatch(children.get(7).text()) ;
                schoolFractionInfo.setMethod("统招") ;


                schoolFractionInfo.setTypeEnum(SchoolTypeEnum.COLLEGE_EDU_CN.toString()) ;


                infoList.add(schoolFractionInfo);
            }
        }

        if (CollUtil.isNotEmpty(infoList)){

        }
    }

    @Test
    public void test2() {
        InfoData infoData = MyHttpUtils.getHtml(new MyHttpParams(TYPE_ENUM, URL, METHOD));
        if (infoData != null) {
            org.jsoup.nodes.Document document = (org.jsoup.nodes.Document) infoData.getValue();
            Element body = document.body();
            Element wrapper = body.getElementById("wrapper");
            Elements tableList = wrapper.select("table");
            if (CollUtil.isEmpty(tableList)) {
                return;
            }
            for (Element table : tableList) {
                Element tbody = table.selectFirst("tbody");
                Elements trList = tbody.select("tr");
                if (CollUtil.isEmpty(trList)) {
                    continue;
                }
                for (int i = 0; i < trList.size(); i++) {
                    if (i == 0) {
                        continue;
                    }
                    Element element = trList.get(i);

                }
            }
        }
    }

    public static String getTextV() {
        String str = "";

        str = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                " <head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html;charset=gb2312\">\n" +
                "  <meta http-equiv=\"mobile-agent\" content=\"format=wml;url=https://college.gaokao.com/sitemap/college.gaokao.com.xml\">\n" +
                "  <meta http-equiv=\"”Cache-Control”\" content=\"no-transform\">\n" +
                "  <meta name=\"keywords\" content=\"2022专业录取分数线,高校专业分数线,录取分数线,专业分数线,所有专业录取分数线\">\n" +
                "  <meta name=\"description\" content=\"\">\n" +
                "  <title>2022高校专业分数线_高考专业录取分数线_高考院校库</title>\n" +
                "  <link href=\"https://www.gaokao.com/favicon.ico\" rel=\"shortcut icon\">\n" +
                "  <link href=\"https://college.gaokao.com/style/college/css/base.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "  <link href=\"https://college.gaokao.com/style/college/css/style.css?2014623\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "  <link href=\"https://college.gaokao.com/style/college/css/nav_jump_gk.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "  <script type=\"text/javascript\" src=\"https://college.gaokao.com/style/college/js/jquery.js\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"https://college.gaokao.com/style/college/js/common.js\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"https://cbjs.baidu.com/js/s.js\"></script><!--[if IE 6]>\n" +
                "\n" +
                "    <script type=\"text/javascript\" src=\"https://img.eduuu.com/website/public_js/DD_belatedPNG.js\" ></script>\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "\n" +
                "    DD_belatedPNG.fix('..collegeName b,.collegeName .areaBox em,table em,.classColl .areaBox .arry, .f_foot_bg, .head p span a');\n" +
                "\n" +
                "    </script>\n" +
                "\n" +
                "<![endif]--> <!--新增-->\n" +
                "  <link rel=\"stylesheet\" type=\"text/css\" href=\"https://eduuimg.mmbang.info/img6kybimgcom/static/gaokao/css/new_base.css\">\n" +
                "  <link rel=\"stylesheet\" type=\"text/css\" href=\"https://eduuimg.mmbang.info/img6kybimgcom/static/gaokao/css/common.css\">\n" +
                "  <script type=\"text/javascript\" src=\"https://www.gaokao.com/public/js/jquery-1.9.1.min.js\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"https://eduuimg.mmbang.info/img6kybimgcom/static/gaokao/js/common.js\"></script>\n" +
                "  <script>var _hmt = _hmt || [];(function() {  var hm = document.createElement(\"script\");  hm.src = \"https://hm.baidu.com/hm.js?c35069925979266b098765567b245009\";  var s = document.getElementsByTagName(\"script\")[0];   s.parentNode.insertBefore(hm, s);})();</script>\n" +
                " </head>\n" +
                " <body><!--网站新头 开始--> <!-- <link href='nav_jump_gk.css?2012-05-17' type='text/css' rel='stylesheet' /> --> <!--头部--> <!-- \n" +
                "<script src=\"https://gkcms.oss-cn-beijing.aliyuncs.com/attachs/static/gaokao/2015/topMenu_gaokao.js?041322\" charset=\"gbk\"></script>\n" +
                "<script src=\"https://img.eduuu.com/website/public_js/topMenu_gaokao.js\"></script> --> <!--<script type=\"text/javascript\" src=\"https://college.gaokao.com/style/college/js/topMenu_gaokao.js?123\" charset=\"gbk\"></script>--> <!-- <script src=\"https://img.eduuu.com/gaokao/2015/topMenu_gaokao.js?0413\" charset=\"gbk\"></script>--> <!-- <div id=\"ad_top_gkw\" style=\"width:950px;height:250px;margin:0 auto;margin-bottom:10px;\"><a href=\"https://www.gaokao.com/z2015/gkbgkw/\"><img src=\"https://gkcms.oss-cn-beijing.aliyuncs.com/attachs/img/2016/12/07/135139_5847a36ba69f5.jpg\" alt=\"高考帮\" style=\"width:100%;\" target=\"_blank\"></a></div>\n" +
                "<script type=\"text/javascript\">\n" +
                " $(document).ready(function() {\n" +
                "\tvar i=3;\n" +
                "\tvar timer;\n" +
                "\t   timer=setInterval(fun, 1000);\t\t\n" +
                "\t\t\tfunction fun(){\n" +
                "\t\t\t\tif(i==0){\n" +
                "\t\t\t\t\t$(\"#ad_top_gkw\").slideUp(700);\n" +
                "\t\t\t\t}\n" +
                "\t\t\t\ti--;\n" +
                "\t\t\t}\n" +
                "           \t\n" +
                "\t});\n" +
                "\t\t\t\n" +
                "\t\t\t\n" +
                "\t</script>\n" +
                "--> <!--header头部-->\n" +
                "  <div id=\"sec_head\">\n" +
                "   <div class=\"inner po_re\" style=\"z-index: 10;\">\n" +
                "    <div class=\"sec_head_con tp15\"><a class=\"logo\" href=\"https://www.gaokao.com\" target=\"_blank\"> <img src=\"https://www.gaokao.com/public/images/logo_new.png\" alt=\"高考帮\" title=\"高考帮\" width=\"185\" height=\"37\"> </a>\n" +
                "     <div class=\"newsite\" style=\"margin-top:22px\">\n" +
                "      <dl>\n" +
                "       <dt>\n" +
                "        全国<i class=\"iconAll \"></i>\n" +
                "       </dt>\n" +
                "       <dd>\n" +
                "        <p><em>热门城市</em> | <a href=\"https://www.gaokao.com/\" target=\"_blank\">全国</a> <a href=\"https://www.gaokao.com/beijing/\" target=\"_blank\" title=\"北京高考网\">北京</a> <a href=\"http://sh.gaokao.com/\" target=\"_blank\" title=\"上海高考网\">上海</a> <a href=\"https://www.gaokao.com/guangdong/\" target=\"_blank\" title=\"广东高考网\">广东</a></p>\n" +
                "        <p><em>华北地区</em> | <a href=\"https://www.gaokao.com/beijing/\" target=\"_blank\" title=\"北京高考网\">北京</a> <a href=\"https://www.gaokao.com/tianjin/\" target=\"_blank\" title=\"天津高考网\">天津</a> <a href=\"https://www.gaokao.com/hebei/\" target=\"_blank\" title=\"河北高考网\">河北</a> <a href=\"https://www.gaokao.com/sx/\" target=\"_blank\" title=\"山西高考网\">山西</a> <a href=\"https://www.gaokao.com/neimenggu/\" target=\"_blank\" title=\"内蒙古高考网\">内蒙古</a></p>\n" +
                "        <p><em>东北地区</em> | <a href=\"https://www.gaokao.com/liaoning/\" target=\"_blank\" title=\"辽宁高考网\">辽宁</a> <a href=\"https://www.gaokao.com/jilin/\" target=\"_blank\" title=\"吉林高考网\">吉林</a> <a href=\"https://www.gaokao.com/heilongjiang/\" target=\"_blank\" title=\"黑龙江高考网\">黑龙江</a></p>\n" +
                "        <p><em>华东地区</em> | <a href=\"http://sh.gaokao.com/\" target=\"_blank\" title=\"上海高考网\">上海</a> <a href=\"https://www.gaokao.com/jiangsu/\" target=\"_blank\" title=\"江苏高考网\">江苏</a> <a href=\"https://www.gaokao.com/zhejiang/\" target=\"_blank\" title=\"浙江高考网\">浙江</a> <a href=\"https://www.gaokao.com/anhui/\" target=\"_blank\" title=\"安徽高考网\">安徽</a> <a href=\"https://www.gaokao.com/fujian/\" target=\"_blank\" title=\"福建高考网\">福建</a> <a href=\"https://www.gaokao.com/jiangxi/\" target=\"_blank\" title=\"江西高考网\">江西</a> <a href=\"https://www.gaokao.com/shandong/\" target=\"_blank\" title=\"山东高考网\" class=\"ml70\">山东</a></p>\n" +
                "        <p><em>华中地区</em> | <a href=\"https://www.gaokao.com/henan/\" target=\"_blank\" title=\"河南高考网\">河南</a> <a href=\"https://www.gaokao.com/hubei/\" target=\"_blank\" title=\"湖北高考网\">湖北</a> <a href=\"https://www.gaokao.com/hunan/\" target=\"_blank\" title=\"湖南高考网\">湖南</a></p>\n" +
                "        <p><em>西南地区</em> | <a href=\"https://www.gaokao.com/chongqing/\" target=\"_blank\" title=\"重庆高考网\">重庆</a> <a href=\"https://www.gaokao.com/sichuan/\" target=\"_blank\" title=\"四川高考网\">四川</a> <a href=\"https://www.gaokao.com/guizhou/\" target=\"_blank\" title=\"贵州高考网\">贵州</a> <a href=\"https://www.gaokao.com/yunnan/\" target=\"_blank\" title=\"云南高考网\">云南</a> <a href=\"https://www.gaokao.com/xizang/\" target=\"_blank\" title=\"西藏高考网\">西藏</a></p>\n" +
                "        <p><em>西北地区</em> | <a href=\"https://www.gaokao.com/shanxi/\" target=\"_blank\" title=\"陕西高考网\">陕西</a> <a href=\"https://www.gaokao.com/gansu/\" target=\"_blank\" title=\"甘肃高考网\">甘肃</a> <a href=\"https://www.gaokao.com/qinghai/\" target=\"_blank\" title=\"青海高考网\">青海</a> <a href=\"https://www.gaokao.com/ningxia/\" target=\"_blank\" title=\"宁夏高考网\">宁夏</a> <a href=\"https://www.gaokao.com/xinjiang/\" target=\"_blank\" title=\"新疆高考网\">新疆</a></p>\n" +
                "        <p class=\"last\"><em>华南地区</em> | <a href=\"https://www.gaokao.com/guangdong/\" target=\"_blank\" title=\"广东高考网\">广东</a> <a href=\"https://www.gaokao.com/guangxi/\" target=\"_blank\" title=\"广西高考网\">广西</a> <a href=\"https://www.gaokao.com/hainan/\" target=\"_blank\" title=\"海南高考网\">海南</a></p>\n" +
                "       </dd>\n" +
                "      </dl>\n" +
                "     </div>\n" +
                "     <script>\n" +
                "\t\t\t\t\tjQuery(function(){\n" +
                "\t\t\t\t\t\tjQuery('.newsite dl').hover(function(){\n" +
                "\t\t\t\t\t\t\tjQuery(this).addClass('on');\n" +
                "\t\t\t\t\t\t},function(){\n" +
                "\t\t\t\t\t\t\tjQuery(this).removeClass('on');\n" +
                "\t\t\t\t\t\t});\n" +
                "\t\t\t\t\t});\n" +
                "\t\t\t\t</script>\n" +
                "     <div class=\"nav tp20 lp20\"><a href=\"/\" target=\"_blank\" title=\"首页\">首页</a> <a href=\"https://www.gaokao.com/beikao/\" target=\"_blank\" title=\"备考\">备考</a> <a href=\"https://www.gaokao.com/baokao/\" target=\"_blank\" title=\"报考\">报考</a> <a href=\"https://www.gaokao.com/gdgkkx/\" target=\"_blank\" title=\"资讯\">资讯</a> <a href=\"http://tiku.gaokao.com/\" target=\"_blank\" title=\"高考试题库\">高考试题库</a>\n" +
                "     </div>\n" +
                "     <div class=\"topNavR\" style=\"padding-top:0\"><!--<a href=\"#\" class=\"zhuce_login rm10\">注册/登录</a>-->\n" +
                "      <ul class=\"con_us clearfix\">\n" +
                "       <li class=\"w\"><a class=\"wx_ic iconAll\" href=\"javascript:;\"></a> 微 信 \n" +
                "        <div class=\"tips\" style=\"top:77px;right: -70px;\">\n" +
                "         <div class=\"triangle-up\"></div>\n" +
                "         <div class=\"part part1\">\n" +
                "          <div class=\"fl conL\">\n" +
                "           <img src=\"https://www.gaokao.com/public/images/gkw.jpg\" width=\"90\" height=\"90\">\n" +
                "          </div>\n" +
                "          <div class=\"fr conR\">\n" +
                "           <div>\n" +
                "            <h3>关注高考帮公众号</h3>\n" +
                "            <p class=\"pys\">（www_gaokao_com）<br>\n" +
                "             了解更多高考资讯</p>\n" +
                "           </div>\n" +
                "          </div>\n" +
                "         </div>\n" +
                "        </div></li>\n" +
                "       <li class=\"t\"><a class=\"tel_ic iconAll\" href=\"http://www.jzb.com/app/\" rel=\"nofollow\" target=\"_blank\" title=\"移动APP\" style=\"background: url(https://gkb-cms.oss-cn-beijing.aliyuncs.com/attachs/img/2018/03/16/163050_5aab80bacfa0e.png);\"></a> 家长帮APP \n" +
                "        <div class=\"tips\" style=\"top: 80px; right: -55px; display: none;\">\n" +
                "         <div class=\"triangle-up\"></div>\n" +
                "         <div class=\"part part1\">\n" +
                "          <div class=\"fl conL\">\n" +
                "           <img src=\"https://img.eduuu.com/edu/ad/wm2.gif\" alt=\"家长帮\" data-bd-imgshare-binded=\"1\" width=\"90\" height=\"90\">\n" +
                "          </div>\n" +
                "          <div class=\"fr conR\">\n" +
                "           <h3>家长帮APP</h3><span>家庭教育家长帮</span>\n" +
                "           <p class=\"downA\"><a href=\"http://mapi.eduu.com/a?c=46&amp;tid=1&amp;d=1\" rel=\"nofollow\"> <span><i class=\"icon1\"></i></span> iPhone </a> <a href=\"http://mapi.eduu.com/a?c=46&amp;tid=2&amp;d=1\"> <span><i class=\"icon2\"></i></span> Android </a></p>\n" +
                "          </div>\n" +
                "         </div>\n" +
                "        </div></li>\n" +
                "      </ul>\n" +
                "     </div>\n" +
                "     <script type=\"text/javascript\">Hover('.con_us','.tips','on')</script>\n" +
                "    </div>\n" +
                "   </div>\n" +
                "   <div class=\"highlightBg png\"></div>\n" +
                "  </div>\n" +
                "  <div class=\"inner\">\n" +
                "   <div class=\"tp10 bp10\" style=\"height: 70px;\">\n" +
                "    <div class=\"left\" style=\"width:1000px\">\n" +
                "     <li><a href=\"https://www.gaokao.com/z2021/fsx/\" target=\"_blank\" class=\"ad920\"><img src=\"https://gaokaobang.oss-cn-beijing.aliyuncs.com/attachs/img/2021/05/12/150337_609b7dc9a14e8.png\" width=\"1000\" height=\"70\"></a></li>\n" +
                "    </div>\n" +
                "   </div>\n" +
                "  </div>\n" +
                "  <div class=\"wrap\">\n" +
                "   <div class=\"channel_Nav\">\n" +
                "    <div class=\"right search_Box ft14\"><input id=\"schname_h\" type=\"text\" onfocus=\"this.value=''\" onblur=\"if(!this.value) this.value='搜索高校'\" value=\"搜索高校\" size=\"20\" class=\"search_Txt\"> <input id=\"schbtn_h\" class=\"search_Btn\" type=\"button\" value=\"搜索\">\n" +
                "    </div>\n" +
                "    <ul>\n" +
                "     <li><a href=\"https://college.gaokao.com/schlist/\">高校搜索</a></li>\n" +
                "     <li><a href=\"https://college.gaokao.com/spelist/\">专业搜索</a></li>\n" +
                "     <li><a href=\"https://college.gaokao.com/schpoint/\">高校分数线</a></li>\n" +
                "     <li><a class=\"on\" href=\"https://college.gaokao.com/spepoint/\">专业分数线</a></li>\n" +
                "     <li><a href=\"https://college.gaokao.com/areapoint/\">地区批次线</a></li>\n" +
                "    </ul>\n" +
                "   </div>\n" +
                "  </div>\n" +
                "  <script type=\"text/javascript\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "//头部高校搜索\n" +
                "\n" +
                "$(\"#schbtn_h\").click(function(){\n" +
                "\n" +
                "    var name = $(\"#schname_h\").val();\n" +
                "\n" +
                "    if(name!='搜索高校' && name!=''){\n" +
                "\n" +
                "        var name = encodeURI(name);\n" +
                "\n" +
                "        window.open('https://college.gaokao.com/schlist/n'+name+'/');\n" +
                "\n" +
                "    }else{\n" +
                "\n" +
                "        alert('高校名称不能为空');\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "});\n" +
                "\n" +
                "\n" +
                "\n" +
                "//function navTabs(tabTit2,tabCon2,on2){\n" +
                "\n" +
                "//\tvar _on=jQuery(tabTit2).find('.'+on2);\n" +
                "\n" +
                "//\tvar _i=jQuery(tabTit2).children('span').index(_on[0]);\n" +
                "\n" +
                "//\tjQuery(tabCon2).each(function(){\n" +
                "\n" +
                "//\t\tjQuery(this).children('div').eq(_i).show();\n" +
                "\n" +
                "//\t});\n" +
                "\n" +
                "//\t$(tabTit2).children('span').hoverDelay({\n" +
                "\n" +
                "//\t\thoverDuring: 300,\n" +
                "\n" +
                "//\t\toutDuring: 300,\n" +
                "\n" +
                "//\t\thoverEvent: function(){\n" +
                "//\n" +
                "//\t\t\tjQuery(this).addClass(on2).siblings('span').removeClass(on2);\n" +
                "\n" +
                "//\t\t\tvar index = jQuery(tabTit2).children('span').index(this);\n" +
                "\n" +
                "//\t\t\tjQuery(tabCon2).children('div').eq(index).show().siblings('div').hide();\n" +
                "\n" +
                "//\t\t}\n" +
                "\n" +
                "//\t});\n" +
                "\n" +
                "//};\n" +
                "\n" +
                "//navTabs('#tag_nav','#nav_con','tag_nav_2');\n" +
                "\n" +
                "\n" +
                "\n" +
                "$('body').append(\"<p id='back-to-top'>返回顶部</p>\");\n" +
                "\n" +
                "$(\"#back-to-top\").hide();\n" +
                "\n" +
                "$(function(){\n" +
                "\n" +
                "\t$(window).scroll(function(){\n" +
                "\n" +
                "\t\tif ($(window).scrollTop()>500){\n" +
                "\n" +
                "\t\t$(\"#back-to-top\").fadeIn();\n" +
                "\n" +
                "\t\t}\n" +
                "\n" +
                "\t\telse\n" +
                "\n" +
                "\t\t{\n" +
                "\n" +
                "\t\t$(\"#back-to-top\").fadeOut();\n" +
                "\n" +
                "\t\t}\n" +
                "\n" +
                "\t});\n" +
                "\n" +
                "\t$(\"#back-to-top\").click(function(){\n" +
                "\n" +
                "\t\t$(this).animate({ bottom:400,opacity:0},300);\n" +
                "\n" +
                "\t\t$('body,html').animate({ scrollTop:0},300,function(){ $(\"#back-to-top\").css('bottom',0);$(\"#back-to-top\").animate({ opacity:100})});\n" +
                "\n" +
                "\t\treturn false;\n" +
                "\n" +
                "\t});\n" +
                "\n" +
                "});\n" +
                "\n" +
                "</script><!--网站新 头结束 -->\n" +
                "  <link rel=\"stylesheet\" type=\"text/css\" href=\"https://www.gaokaopai.com/Public/Css/gkb_vip.css\">\n" +
                "  <script type=\"text/javascript\" src=\"https://www.gaokaopai.com/Public/Js/mydialog.js\"></script>\n" +
                "  <div class=\"zoomIn errorBox\" id=\"errorBox\">\n" +
                "   <h4><em class=\"tip_gantanhao\"></em>所属资料仅支持在高考帮APP上查看</h4><img src=\"https://www.gaokaopai.com/Public/Images/tips.jpg\" alt=\"\"> <a class=\"errorBox_close\" href=\"javascript:;\" onclick=\"easyDialog.close();\"></a>\n" +
                "  </div>\n" +
                "  <script type=\"text/javascript\">\n" +
                "$(function(){\n" +
                "$('.errorTips').on('click',function(){\n" +
                " \t\t\t\teasyDialog.open({\n" +
                "\t\t\t\t\tcontainer : 'errorBox',\n" +
                "\t\t\t\t\tautoClose:false\n" +
                "\t\t\t\t});\n" +
                " \t\t\t});\n" +
                "})\n" +
                "\t\t\t\n" +
                "</script>\n" +
                "  <div id=\"wrapper\">\n" +
                "   <div class=\"menufix\">\n" +
                "    <p><strong>考生所在地：</strong> <a class=\"on\" href=\"https://college.gaokao.com/spepoint/a100/y2022/\">全部</a> <a href=\"https://college.gaokao.com/spepoint/a1/y2022/\">北京</a> <a href=\"https://college.gaokao.com/spepoint/a2/y2022/\">天津</a> <a href=\"https://college.gaokao.com/spepoint/a3/y2022/\">辽宁</a> <a href=\"https://college.gaokao.com/spepoint/a4/y2022/\">吉林</a> <a href=\"https://college.gaokao.com/spepoint/a5/y2022/\">黑龙江</a> <a href=\"https://college.gaokao.com/spepoint/a6/y2022/\">上海</a> <a href=\"https://college.gaokao.com/spepoint/a7/y2022/\">江苏</a> <a href=\"https://college.gaokao.com/spepoint/a8/y2022/\">浙江</a> <a href=\"https://college.gaokao.com/spepoint/a9/y2022/\">安徽</a> <a href=\"https://college.gaokao.com/spepoint/a10/y2022/\">福建</a> <a href=\"https://college.gaokao.com/spepoint/a11/y2022/\">山东</a> <a href=\"https://college.gaokao.com/spepoint/a12/y2022/\">湖北</a> <a href=\"https://college.gaokao.com/spepoint/a13/y2022/\">湖南</a> <a href=\"https://college.gaokao.com/spepoint/a14/y2022/\">广东</a> <a href=\"https://college.gaokao.com/spepoint/a15/y2022/\">重庆</a> <a href=\"https://college.gaokao.com/spepoint/a16/y2022/\">四川</a> <a href=\"https://college.gaokao.com/spepoint/a17/y2022/\">陕西</a> <a href=\"https://college.gaokao.com/spepoint/a18/y2022/\">甘肃</a> <a href=\"https://college.gaokao.com/spepoint/a19/y2022/\">河北</a> <a href=\"https://college.gaokao.com/spepoint/a20/y2022/\">山西</a> <a href=\"https://college.gaokao.com/spepoint/a21/y2022/\">内蒙古</a> <a href=\"https://college.gaokao.com/spepoint/a22/y2022/\">河南</a> <a href=\"https://college.gaokao.com/spepoint/a23/y2022/\">海南</a> <a href=\"https://college.gaokao.com/spepoint/a24/y2022/\">广西</a> <a href=\"https://college.gaokao.com/spepoint/a25/y2022/\">贵州</a> <a href=\"https://college.gaokao.com/spepoint/a26/y2022/\">云南</a> <a href=\"https://college.gaokao.com/spepoint/a27/y2022/\">西藏</a> <a href=\"https://college.gaokao.com/spepoint/a28/y2022/\">青海</a> <a href=\"https://college.gaokao.com/spepoint/a29/y2022/\">宁夏</a> <a href=\"https://college.gaokao.com/spepoint/a30/y2022/\">新疆</a> <a href=\"https://college.gaokao.com/spepoint/a31/y2022/\">江西</a> <a href=\"https://college.gaokao.com/spepoint/a33/y2022/\">香港</a> <a href=\"https://college.gaokao.com/spepoint/a38/y2022/\">澳门</a> <a href=\"https://college.gaokao.com/spepoint/a39/y2022/\">台湾</a></p>\n" +
                "    <p><strong>文理分科：</strong> <a class=\"on\" href=\"https://college.gaokao.com/spepoint/y2022/\">全部</a> <a href=\"https://college.gaokao.com/spepoint/s1/y2022/\">理科</a> <a href=\"https://college.gaokao.com/spepoint/s2/y2022/\">文科</a> <a href=\"https://college.gaokao.com/spepoint/s3/y2022/\">综合</a> <a href=\"https://college.gaokao.com/spepoint/s4/y2022/\">其他</a> <a href=\"https://college.gaokao.com/spepoint/s8/y2022/\">艺术理</a> <a href=\"https://college.gaokao.com/spepoint/s9/y2022/\">艺术文</a> <a href=\"https://college.gaokao.com/spepoint/s10/y2022/\">综合改革</a></p>\n" +
                "    <p><strong>招生年份：</strong> <a href=\"https://college.gaokao.com/spepoint/\">全部</a> <a href=\"https://college.gaokao.com/spepoint/y2023/\">2023</a> <a class=\"on\" href=\"https://college.gaokao.com/spepoint/y2022/\">2022</a> <a href=\"https://college.gaokao.com/spepoint/y2021/\">2021</a> <a href=\"https://college.gaokao.com/spepoint/y2020/\">2020</a> <a href=\"https://college.gaokao.com/spepoint/y2019/\">2019</a> <a href=\"https://college.gaokao.com/spepoint/y2018/\">2018</a> <a href=\"https://college.gaokao.com/spepoint/y2017/\">2017</a> <a href=\"https://college.gaokao.com/spepoint/y2016/\">2016</a> <a href=\"https://college.gaokao.com/spepoint/y2015/\">2015</a> <a href=\"https://college.gaokao.com/spepoint/y2014/\">2014</a> <a href=\"https://college.gaokao.com/spepoint/y2013/\">2013</a> <a href=\"https://college.gaokao.com/spepoint/y2012/\">2012</a> <a href=\"https://college.gaokao.com/spepoint/y2011/\">2011</a> <a href=\"https://college.gaokao.com/spepoint/y2010/\">2010</a> <a href=\"https://college.gaokao.com/spepoint/y2009/\">2009</a> <a href=\"https://college.gaokao.com/spepoint/y2008/\">2008</a> <a href=\"https://college.gaokao.com/spepoint/y2007/\">2007</a></p>\n" +
                "   </div>\n" +
                "   <div class=\"ment_Cto\">\n" +
                "    <a href=\"javascript:void(0);\" onclick=\"javascript:$('.menufix').toggle();if($(this).html()=='收起选项'){ $(this).html('展开选项');$(this).addClass('down'); }else{ $(this).html('收起选项');$(this).removeClass('down'); }\">收起选项</a>\n" +
                "   </div>\n" +
                "   <div class=\"hr_10\"></div>\n" +
                "   <div class=\"cont_l zycx\">\n" +
                "    <h3 class=\"zy_tit ss2\"><span><em>专业分数线</em></span> <p><input id=\"schname\" type=\"text\" onfocus=\"this.value=''\" onblur=\"if(!this.value) this.value='请输入高校名称'\" value=\"请输入高校名称\" size=\"20\" class=\"searchInput110\"></p><p><input id=\"spename\" type=\"text\" onfocus=\"this.value=''\" onblur=\"if(!this.value) this.value='请输入专业名称'\" value=\"请输入专业名称\" size=\"20\" class=\"searchInput110\"></p><p><select id=\"stuarea\" class=\"stuarea\" name=\"stuarea\"> <option value=\"\">所在地区</option> </select></p><p><select id=\"subject\" class=\"subject\" name=\"subject\"> <option value=\"\">文理分科</option> </select></p><p><select id=\"year\" class=\"year\" name=\"year\"> <option value=\"\">年份</option> <option value=\"2023\">2023</option> <option selected value=\"2022\">2022</option> <option value=\"2021\">2021</option> <option value=\"2020\">2020</option> <option value=\"2019\">2019</option> <option value=\"2018\">2018</option> <option value=\"2017\">2017</option> <option value=\"2016\">2016</option> <option value=\"2015\">2015</option> <option value=\"2014\">2014</option> <option value=\"2013\">2013</option> <option value=\"2012\">2012</option> <option value=\"2011\">2011</option> <option value=\"2010\">2010</option> <option value=\"2009\">2009</option> <option value=\"2008\">2008</option> <option value=\"2007\">2007</option> </select></p><p><input id=\"spepselect\" type=\"image\" src=\"https://college.gaokao.com/style/college/images/sous.png\" width=\"64\" height=\"20\"></p></h3>\n" +
                "    <div class=\"hr_10\"></div>\n" +
                "    <table width=\"725\" border=\"0\" cellpadding=\"0\" cellspacing=\"1\" bgcolor=\"#E1E1E1\">\n" +
                "     <tbody>\n" +
                "      <tr>\n" +
                "       <th scope=\"col\">专业名称</th>\n" +
                "       <th scope=\"col\">高校名称</th>\n" +
                "       <th scope=\"col\">平均分</th>\n" +
                "       <th scope=\"col\">最高分</th>\n" +
                "       <th scope=\"col\">考生地区</th>\n" +
                "       <th scope=\"col\">科别</th>\n" +
                "       <th scope=\"col\">年份</th>\n" +
                "       <th scope=\"col\">批次</th>\n" +
                "       <th scope=\"col\">专业对比</th>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/506/\" target=\"_blank\">水利类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453647/\" target=\"_blank\">629</a></td>\n" +
                "       <td>634</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/506/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"sz\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/349/\" target=\"_blank\">园林</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453648/\" target=\"_blank\">629</a></td>\n" +
                "       <td>631</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/349/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/435/\" target=\"_blank\">农业工程类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453649/\" target=\"_blank\">632</a></td>\n" +
                "       <td>640</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/435/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"sz\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/480/\" target=\"_blank\">机械类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453650/\" target=\"_blank\">631</a></td>\n" +
                "       <td>637</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/480/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/690/\" target=\"_blank\">社会学类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453651/\" target=\"_blank\">633</a></td>\n" +
                "       <td>640</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/690/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"sz\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/650/\" target=\"_blank\">英语</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453652/\" target=\"_blank\">630</a></td>\n" +
                "       <td>631</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/650/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/798/\" target=\"_blank\">工商管理类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453653/\" target=\"_blank\">633</a></td>\n" +
                "       <td>636</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/798/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"sz\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/700/\" target=\"_blank\">化学类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453654/\" target=\"_blank\">632</a></td>\n" +
                "       <td>635</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/700/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/2264/\" target=\"_blank\">环境科学与工程类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453655/\" target=\"_blank\">633</a></td>\n" +
                "       <td>635</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/2264/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"sz\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/1174/\" target=\"_blank\">动物医学类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453656/\" target=\"_blank\">640</a></td>\n" +
                "       <td>656</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/1174/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/785/\" target=\"_blank\">农村区域发展</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453657/\" target=\"_blank\">639</a></td>\n" +
                "       <td>646</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/785/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"sz\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/659/\" target=\"_blank\">传播学</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453658/\" target=\"_blank\">633</a></td>\n" +
                "       <td>634</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/659/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/471/\" target=\"_blank\">工程力学</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453659/\" target=\"_blank\">633</a></td>\n" +
                "       <td>633</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/471/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"sz\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/251/\" target=\"_blank\">电子信息类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453660/\" target=\"_blank\">638</a></td>\n" +
                "       <td>656</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/251/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/710/\" target=\"_blank\">地理科学类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453661/\" target=\"_blank\">639</a></td>\n" +
                "       <td>648</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/710/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"sz\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/723/\" target=\"_blank\">数学与应用数学</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453662/\" target=\"_blank\">637</a></td>\n" +
                "       <td>641</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/723/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/827/\" target=\"_blank\">经济学类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453663/\" target=\"_blank\">639</a></td>\n" +
                "       <td>645</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/827/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"sz\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/339/\" target=\"_blank\">园艺</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453664/\" target=\"_blank\">642</a></td>\n" +
                "       <td>651</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/339/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/352/\" target=\"_blank\">草业科学</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453665/\" target=\"_blank\">637</a></td>\n" +
                "       <td>638</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/352/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"sz\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/341/\" target=\"_blank\">植物保护</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453666/\" target=\"_blank\">639</a></td>\n" +
                "       <td>641</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/341/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/2266/\" target=\"_blank\">食品科学与工程类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453667/\" target=\"_blank\">645</a></td>\n" +
                "       <td>655</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/2266/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"sz\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/683/\" target=\"_blank\">法学</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453668/\" target=\"_blank\">641</a></td>\n" +
                "       <td>643</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/683/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/2255/\" target=\"_blank\">计算机类</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453669/\" target=\"_blank\">641</a></td>\n" +
                "       <td>643</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/2255/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"sz\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/338/\" target=\"_blank\">农学</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453670/\" target=\"_blank\">644</a></td>\n" +
                "       <td>655</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/338/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "      <tr class=\"szw\">\n" +
                "       <td><a href=\"https://college.gaokao.com/speciality/331/\" target=\"_blank\">动物科学</a></td>\n" +
                "       <td><a href=\"https://college.gaokao.com/school/6/\" target=\"_blank\">中国农业大学</a></td>\n" +
                "       <td class=\"pjf\"><a href=\"https://college.gaokao.com/school/spepoint/5453671/\" target=\"_blank\">646</a></td>\n" +
                "       <td>652</td>\n" +
                "       <td>北京</td>\n" +
                "       <td>综合改革</td>\n" +
                "       <td>2022</td>\n" +
                "       <td>本科批</td>\n" +
                "       <td><a href=\"https://college.gaokao.com/spevs/331/6/pk/\" target=\"_blank\">加入对比 </a></td>\n" +
                "      </tr>\n" +
                "     </tbody>\n" +
                "    </table>\n" +
                "    <ul class=\"fany\">\n" +
                "     <li>首页</li>\n" +
                "     <li>&lt;&lt; 上一页</li>\n" +
                "     <li><a href=\"https://college.gaokao.com/spepoint/y2022/p2/\">下一页 &gt;&gt;</a></li>\n" +
                "     <li><a href=\"https://college.gaokao.com/spepoint/y2022/p237/\">末页</a></li>\n" +
                "     <li id=\"qx\">1/237页 第<input id=\"pagenum\" type=\"text\">页 <input id=\"pagego\" type=\"image\" src=\"https://college.gaokao.com/style/college/images/go.jpg\" width=\"31\" height=\"20\"></li>\n" +
                "    </ul>\n" +
                "   </div>\n" +
                "   <div class=\"cont_r\">\n" +
                "    <div class=\"ct_t bk\">\n" +
                "     <h3 class=\"ct1\"><span></span><p><a href=\"https://www.gaokao.com/baokao/zydq/zyjs/\" target=\"_blank\">专业介绍</a></p></h3>\n" +
                "     <div class=\"hr_10\"></div>\n" +
                "     <ul>\n" +
                "     </ul>\n" +
                "    </div>\n" +
                "    <div class=\"hr_10\"></div><!--\n" +
                "    <div class=\"baidu_Ad_box\">\n" +
                "        <div class=\"baidu_Ad_right\">\n" +
                "        <script type=\"text/javascript\">\n" +
                "\t\t\t/*college-全站-右侧矩形-百度联盟 250*250 创建于 2015-12-16*/\n" +
                "\t\t\tvar cpro_id = \"u2451770\";\n" +
                "\t\t</script>\n" +
                "<script src=\"https://cpro.baidustatic.com/cpro/ui/c.js\" type=\"text/javascript\"></script>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"hr_10\"></div>\n" +
                "-->\n" +
                "    <div class=\"ct_t bk\">\n" +
                "     <h3 class=\"ct2\"><span></span><p><a href=\"https://www.gaokao.com/baokao/zytb/tbzd/\" target=\"_blank\">填报指导</a></p></h3>\n" +
                "     <div class=\"hr_10\"></div>\n" +
                "     <ul>\n" +
                "     </ul>\n" +
                "    </div>\n" +
                "   </div>\n" +
                "   <div class=\"hr_10\"></div>\n" +
                "  </div>\n" +
                "  <div style=\"display:none;\">\n" +
                "   <script type=\"text/javascript\">var EDUU_GKEY=\"高考网/院校库/专业分数线/2022\";</script>\n" +
                "   <script type=\"text/javascript\" src=\"https://img.eduuu.com/edu/js/ggc.js?gx=2011_4\"></script>\n" +
                "   <script src=\" https://s50.cnzz.com/stat.php?id=1997329&amp;web_id=1997329\" type=\"text/javascript\"></script>\n" +
                "  </div>\n" +
                "  <script type=\"text/javascript\" language=\"javascript\">\n" +
                "\n" +
                "    //搜索分数线(下拉单)\n" +
                "    function spepselect(stuarea,subject,year,spename,schname){\n" +
                "        if(stuarea!=''){\n" +
                "            stuarea = '/a'+stuarea;\n" +
                "        }\n" +
                "        if(subject!=''){\n" +
                "            subject = '/s'+subject;\n" +
                "        }\n" +
                "        if(year!=''){\n" +
                "            year = '/y'+year;\n" +
                "        }\n" +
                "        if(spename!=''){\n" +
                "            spename = '/n'+spename;\n" +
                "        }\n" +
                "        if(schname!=''){\n" +
                "            schname = '/o'+schname;\n" +
                "        }\n" +
                "\n" +
                "        var page = $(\"#pagenum\").val();\n" +
                "        if(page!=''){\n" +
                "            page = '/p'+page;\n" +
                "        }\n" +
                "\n" +
                "        var url = \"https://college.gaokao.com/spepoint\"+stuarea+subject+year+spename+schname+page+\"/\";\n" +
                "        window.location.href=url;\n" +
                "    }\n" +
                "\n" +
                "    //跳转到url\n" +
                "    function gotourl(){\n" +
                "        if($(\"#spename\").val() == \"请输入专业名称\"){\n" +
                "            var spename = \"\";\n" +
                "        }else{\n" +
                "            var spename = $(\"#spename\").val();\n" +
                "        }\n" +
                "        if($(\"#schname\").val() == \"请输入高校名称\"){\n" +
                "            var schname = \"\";\n" +
                "        }else{\n" +
                "            var schname = $(\"#schname\").val();\n" +
                "        }\n" +
                "        spepselect($(\"#stuarea\").val(),$(\"#subject\").val(),$(\"#year\").val(),spename,schname);\n" +
                "    }\n" +
                "\n" +
                "    $(document).ready(function(){\n" +
                "\n" +
                "        //取得select框内数据\n" +
                "        $.getJSON(\"https://college.gaokao.com/json/area?id=&callback=?\",function(data){ \n" +
                "            $(\"#stuarea\").html(data.json);\n" +
                "        });\n" +
                "        $.getJSON(\"https://college.gaokao.com/json/subject?id=&callback=?\",function(data){ \n" +
                "            $(\"#subject\").html(data.json);\n" +
                "        });\n" +
                "\n" +
                "        //搜索分数线\n" +
                "        $(\"#spepselect\").click(function(){\n" +
                "            _gaq.push(['_trackEvent', 'College', 'search', 'spepli']);\n" +
                "            gotourl();\n" +
                "        });\n" +
                "\n" +
                "        //跳转到X页\n" +
                "        $(\"#pagego\").click(function(){\n" +
                "            gotourl();\n" +
                "        });\n" +
                "\n" +
                "        //显示全部考生所在地\n" +
                "        $(\"#showAllB\").click(function(){\n" +
                "            $(\"#showAllArea\").show();\n" +
                "            $(\"#showNoArea\").hide();\n" +
                "        });\n" +
                "\n" +
                "        //回车事件 回车触发\n" +
                "        $('body').bind('keydown', function (e) {\n" +
                "            var key = e.which;\n" +
                "            if(key==13){\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "    });\n" +
                "\n" +
                "</script>\n" +
                "  <div class=\"wrapper wj_bk\">\n" +
                "   <p>高考院校库分数线统计时包含了特长生分数，因此最低分和平均分可能与院校公布数据不一致，请以各高校正式公布数据为准。</p>\n" +
                "   <p>如果您发现网页当中的任何错误，欢迎发送邮件(zhangran@100tal.com）与我们联系，我们会及时更正，谢谢!</p>\n" +
                "  </div>\n" +
                "  <div class=\"wrap\">\n" +
                "   <div class=\"wrap\" style=\"margin-top:10px;\"><!-- 广告位：2022_高考网_学校库_PC_学校详_页面底部 -->\n" +
                "    <script>\n" +
                "(function() {\n" +
                "    var s = \"_\" + Math.random().toString(36).slice(2);\n" +
                "    document.write('<div id=\"' + s + '\"></div>');\n" +
                "    (window.slotbydup=window.slotbydup || []).push({\n" +
                "        id: '8105759',\n" +
                "        container: s,\n" +
                "        size: '1000,90',\n" +
                "        display: 'inlay-fix'\n" +
                "    });\n" +
                "})();\n" +
                "</script>\n" +
                "   </div>\n" +
                "   <div class=\"hr_10\"></div><!--新底 开始 20140225-->\n" +
                "   <div class=\"footer2013\">\n" +
                "    <div class=\"wrapper\">\n" +
                "     <dl class=\"link clearfix\">\n" +
                "      <dt class=\"ft18 ffm\">\n" +
                "       城市导航\n" +
                "      </dt>\n" +
                "      <dd>\n" +
                "       <a title=\"北京高考网\" target=\"_blank\" href=\"https://www.gaokao.com/beijing/\">北京高考</a> <a title=\"上海高考网\" target=\"_blank\" href=\"http://sh.gaokao.com/\">上海高考</a> <a title=\"天津高考网\" target=\"_blank\" href=\"https://www.gaokao.com/tianjin/\">天津高考</a> <a title=\"重庆高考网\" target=\"_blank\" href=\"https://www.gaokao.com/chongqing/\">重庆高考</a> <a title=\"广东高考网\" target=\"_blank\" href=\"https://www.gaokao.com/guangdong/\">广东高考</a> <a title=\"江苏高考网\" target=\"_blank\" href=\"https://www.gaokao.com/jiangsu/\">江苏高考</a> <a title=\"山东高考网\" target=\"_blank\" href=\"https://www.gaokao.com/shandong/\">山东高考</a> <a title=\"浙江高考网\" target=\"_blank\" href=\"https://www.gaokao.com/zhejiang/\">浙江高考</a> <a title=\"湖北高考网\" target=\"_blank\" href=\"https://www.gaokao.com/hubei/\">湖北高考</a> <a title=\"四川高考网\" target=\"_blank\" href=\"https://www.gaokao.com/sichuan/\">四川高考</a> <a title=\"黑龙江高考网\" target=\"_blank\" href=\"https://www.gaokao.com/heilongjiang/\">黑龙江高考</a><br><a title=\"湖南高考网\" target=\"_blank\" href=\"https://www.gaokao.com/hunan/\">湖南高考</a> <a title=\"辽宁高考网\" target=\"_blank\" href=\"https://www.gaokao.com/liaoning/\">辽宁高考</a> <a title=\"海南高考网\" target=\"_blank\" href=\"https://www.gaokao.com/hainan/\">海南高考</a> <a title=\"宁夏高考网\" target=\"_blank\" href=\"https://www.gaokao.com/ningxia/\">宁夏高考</a> <a title=\"福建高考网\" target=\"_blank\" href=\"https://www.gaokao.com/fujian/\">福建高考</a> <a title=\"甘肃高考网\" target=\"_blank\" href=\"https://www.gaokao.com/gansu/\">甘肃高考</a> <a title=\"河北高考网\" target=\"_blank\" href=\"https://www.gaokao.com/hebei/\">河北高考</a> <a title=\"吉林高考网\" target=\"_blank\" href=\"https://www.gaokao.com/jilin/\">吉林高考</a> <a title=\"江西高考网\" target=\"_blank\" href=\"https://www.gaokao.com/jiangxi/\">江西高考</a> <a title=\"云南高考网\" target=\"_blank\" href=\"https://www.gaokao.com/yunnan/\">云南高考</a> <a title=\"内蒙古高考网\" target=\"_blank\" href=\"https://www.gaokao.com/neimenggu/\">内蒙古高考</a><br><a title=\"河南高考网\" target=\"_blank\" href=\"https://www.gaokao.com/henan/\">河南高考</a> <a title=\"广西高考网\" target=\"_blank\" href=\"https://www.gaokao.com/guangxi/\">广西高考</a> <a title=\"陕西高考网\" target=\"_blank\" href=\"https://www.gaokao.com/shanxi/\">陕西高考</a> <a title=\"山西高考网\" target=\"_blank\" href=\"https://www.gaokao.com/sx/\">山西高考</a> <a title=\"安徽高考网\" target=\"_blank\" href=\"https://www.gaokao.com/anhui/\">安徽高考</a> <a title=\"新疆高考网\" target=\"_blank\" href=\"https://www.gaokao.com/xinjiang/\">新疆高考</a> <a title=\"西藏高考网\" target=\"_blank\" href=\"https://www.gaokao.com/xizang/\">西藏高考</a> <a title=\"贵州高考网\" target=\"_blank\" href=\"https://www.gaokao.com/guizhou/\">贵州高考</a> <a title=\"青海高考网\" target=\"_blank\" href=\"https://www.gaokao.com/qinghai/\">青海高考</a> <a title=\"港澳高考网\" target=\"_blank\" href=\"https://www.gaokao.com/gd/\">港澳高考</a>\n" +
                "      </dd>\n" +
                "     </dl>\n" +
                "     <div class=\"tc\">\n" +
                "      <p><a title=\"关于我们\" target=\"_blank\" href=\"https://www.gaokao.com/z2015/about/\">关于我们</a> - <a target=\"_blank\" href=\"https://www.gaokao.com/z2014/ad/\">广告服务</a> - <a target=\"_blank\" href=\"https://www.gaokao.com/\">友情链接</a> - <a target=\"_blank\" href=\"https://www.gaokao.com/include/map.html\">网站地图</a> - <a target=\"_blank\" href=\"https://www.gaokao.com/\">服务条款</a> - <a target=\"_blank\" href=\"https://www.gaokao.com/\">诚聘英才</a> <!--\n" +
                "- <a target=\"_blank\" href=\"https://www.gaokao.com/z2014/contactus/\">联系我们</a>\n" +
                "--></p>\n" +
                "      <p><a target=\"_blank\" href=\"https://beian.miit.gov.cn/\">京ICP备10033062号-2</a> <span style=\"color:#55595f;\">北京市公安局海淀分局备案编号：</span><a target=\"_blank\" href=\"http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11010802029651\">1101081950</a></p>\n" +
                "      <p>高考网版权所有 Copyright &copy; 2005-2017 www.gaokao.com. All Rights Reserved.</p>\n" +
                "     </div>\n" +
                "    </div>\n" +
                "   </div><!-- 底部浮动层 --> <!--\n" +
                "<div class=\"f_foot_fixed\" style=\"display:none;\">\n" +
                "\t<div class=\"wrapper f_foot_box\">\n" +
                "    \t<a class=\"f_close f_foot_bg\" href=\"javascript:void(0);\"></a>\n" +
                "        <dl>\n" +
                "        \t<dt class=\"left f_foot_bg\"></dt>\n" +
                "            <dd class=\"left\"><span>下载高考院校库手机客户端，助你随时随地掌握高校信息！</span><a class=\"f_foot_bg\" href=\"https://www.gaokao.com/mobile/\" target=\"_blank\"></a></dd>\n" +
                "        </dl>\n" +
                "    </div>\n" +
                "</div>\n" +
                "--> <!-- 底部浮层广告 start--> <!--<div class=\"fixed_popbox\" id=\"J_fixed_popbox\">\n" +
                "    <div class=\"popcon clearfix\">\n" +
                "        <div class=\"popcon_text\">\n" +
                "            <table>\n" +
                "                <tbody>\n" +
                "                    <tr>\n" +
                "                        <td>高考期间全程陪伴-备考、真题、估分、查院校、选专业、报志愿，尽在高考帮。扫我→</td>\n" +
                "                    </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "        <div class=\"qr_code\">\n" +
                "            <table>\n" +
                "                <tbody>\n" +
                "                    <tr>\n" +
                "                        <td><img src=\"https://gkb-cms.oss-cn-beijing.aliyuncs.com/attachs/img/2017/04/27/153729_59019fb93d449.png\" width=\"70\" height=\"70\" /></td>\n" +
                "                        <td class=\"\">\n" +
                "                            <p class=\"ios\"><a href=\"https://itunes.apple.com/cn/app/gao-kao-bang-bang-ni-shi-xian/id920371894\" target=\"_blank\">iOS下载</a></p>\n" +
                "                            <p class=\"android\"><a href=\"http://gkimg.oss-cn-beijing.aliyuncs.com/gaokaobang/222/gaokao_gkw.apk\" target=\"_blank\">Android下载</a></p>\n" +
                "                        </td>\n" +
                "                        <td class=\"qr_txt\">打开微信扫一扫<br>随时随地聊高考\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "        <a href=\"javascript:void(0)\" class=\"popbox_close\"></a>\n" +
                "    </div>\n" +
                "</div>-->\n" +
                "   <style type=\"text/css\">\n" +
                "\t.fixed_popbox{display:none;position:fixed;left:0;bottom:0;width:100%;height:90px;background:rgba(67,166,189,0.9)}.fixed_popbox .popcon{width:1000px;height:100%;margin:0 auto;position:relative}.fixed_popbox .popcon_text{float:left;height:100%;width:630px;color:#FFF;font-size:16px}.fixed_popbox .popcon_text table{height:100%}.fixed_popbox .popcon_text a{color:#FFF}.fixed_popbox .qr_code{height:70px;padding-left:14px;border-left:1px dotted #FFF;float:right;margin-top:10px;margin-right:20px}.fixed_popbox .qr_code a{display:block;width:100px;height:26px;line-height:26px;color:#FFF;font-size:14px;text-align:center;border:1px solid #FFF;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px}.fixed_popbox .qr_code .ios{padding-bottom:10px}.fixed_popbox .qr_code p{padding:0 10px}.fixed_popbox .qr_code .qr_txt{color:#FFF}.fixed_popbox .popbox_close{position:absolute;right:0;top:5px;width:12px;height:12px;background:url('https://img.eduuu.com/bbs/images/bbsfc/close.gif') no-repeat center center;overflow:hidden}\n" +
                "</style>\n" +
                "   <script type=\"text/javascript\">\n" +
                "\t$(function(){\n" +
                "\tif($('#J_fixed_popbox')[0]){\n" +
                "\t\tvar _box = $('#J_fixed_popbox');\n" +
                "\n" +
                "\t\t$(window).scroll(function(){\n" +
                "\t\t\tif ($(this).scrollTop() > 300) {\n" +
                "\t\t\t\t_box.fadeIn(800);\n" +
                "\t\t\t} else {\n" +
                "\t\t\t\t_box.fadeOut(800);\n" +
                "\t\t\t}\n" +
                "\t\t});\n" +
                "\t\t$(\".popbox_close\",_box).click(function(){\n" +
                "\t\t\t_box.remove();\n" +
                "\t\t\t$('.footer').css({\n" +
                "\t\t\t\t'padding' : 0\n" +
                "\t\t\t})\n" +
                "\t\t});\n" +
                "\t}\n" +
                "});\n" +
                "</script>\n" +
                "   <script type=\"text/javascript\">\n" +
                "    /*college-全站-悬浮按钮-百度联盟 120*120 创建于 2015-12-15*/\n" +
                "var cpro_id = \"u2450442\";\n" +
                "</script>\n" +
                "   <script src=\"https://cpro.baidustatic.com/cpro/ui/f.js\" type=\"text/javascript\"></script>\n" +
                "   <script>\n" +
                "//关闭底部浮动层、微信关注\n" +
                "//var f_flag = getCookie('f_flag') ? 1 : 0;\n" +
                "//$(\".f_close\").click(function(){\n" +
                "//\t$(this).parent().parent().hide();\n" +
                "//    f_flag=1;\n" +
                "//    setCookie('f_flag', 1, 86400000);\n" +
                "//})\n" +
                "//$(window).scroll(function(){ \n" +
                "//\tvar win_top=$(document).scrollTop();\n" +
                "//\tif(win_top>1 && f_flag==0)\n" +
                "//\t{\n" +
                "//\t\t$(\".f_foot_fixed\").fadeIn();\n" +
                "//\t}\n" +
                "//\telse\n" +
                "//\t{\n" +
                "//\t\t$(\".f_foot_fixed\").fadeOut();\n" +
                "//\t}\n" +
                "//})\n" +
                "\n" +
                "//设置cookie\n" +
                "function setCookie(cookieName, cookieValue, seconds, path, domain, secure) {\n" +
                "\tvar expires = new Date();\n" +
                "\texpires.setTime(expires.getTime() + seconds);\n" +
                "\tdocument.cookie = escape(cookieName) + '=' + escape(cookieValue)\n" +
                "\t\t+ (expires ? '; expires=' + expires.toGMTString() : '')\n" +
                "\t\t+ (path ? '; path=' + path : '/')\n" +
                "\t\t+ (domain ? '; domain=' + domain : '')\n" +
                "\t\t+ (secure ? '; secure' : '');\n" +
                "}\n" +
                "\n" +
                "//获取cookie\n" +
                "function getCookie(name) {\n" +
                "    var start = document.cookie.indexOf(name);\n" +
                "    var end = document.cookie.indexOf(\";\",start);\n" +
                "    return start==-1 ? null : unescape(document.cookie.substring(start+name.length+1,(end>start ? end : document.cookie.length)));\n" +
                "}\n" +
                "</script>\n" +
                "   <script type=\"text/javascript\" src=\"https://college.gaokao.com/style/college/js/index_call.js\"></script><!--script type=\"text/javascript\" src=\"https://img.eduuu.com/gaokao/2015/bannerDaili.js?1019\"></script-->\n" +
                "  </div>\n" +
                " </body>\n" +
                "</html>";
        return str;
    }

    private String getObject(Object obj) {
        if (obj == null) {
            return null;
        }
        return String.valueOf(obj);
    }

}
