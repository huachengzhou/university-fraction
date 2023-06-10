package com.ng.todo.reptile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ng.todo.common.enums.ReptileTypeEnum;
import com.ng.todo.common.utils.LangUtils;
import com.ng.todo.common.utils.MyHttpUtils;
import com.ng.todo.entity.CommonSchoolFractionInfo;
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
import java.util.Date;
import java.util.List;

public class Demo3 {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static final String URL = "http://gaokao.chinakaoyan.com/s/fenshu2/k2013";
    public static final ReptileTypeEnum TYPE_ENUM = ReptileTypeEnum.Jsoup;
    public static final RequestMethod METHOD = RequestMethod.GET;

    @Test
    public void test1() {

        //www.85aaa.com
        InfoData infoData = MyHttpUtils.getHtml(new MyHttpParams(TYPE_ENUM, URL, METHOD));
        if (infoData != null) {
            org.jsoup.nodes.Document document = (org.jsoup.nodes.Document) infoData.getValue();
            Element body = document.body();

            Elements elements = body.select("tab-form-body");
            if (CollUtil.isNotEmpty(elements)) {

            }
        }

    }

    @Test
    public void test2() {
        org.jsoup.nodes.Document document = Jsoup.parse(getTextV());
        Element body = document.body();
        List<CommonSchoolFractionInfo> fractionInfoList = new ArrayList<>();
        Elements elements = body.select(".tab-form-body");
        if (CollUtil.isNotEmpty(elements)) {
            for (Element element : elements) {
                Elements liList = element.select("li");
                if (CollUtil.isEmpty(liList)){
                    continue;
                }
                for (Element li : liList) {
                    Elements spanList = li.select("span");
                    if (CollUtil.isEmpty(spanList)){
                        continue;
                    }
                    CommonSchoolFractionInfo fractionInfo = new CommonSchoolFractionInfo();
                    fractionInfo.setGmtCreated(new Date());
                    fractionInfo.setGmtModified(new Date());
                    fractionInfo.setUuid(LangUtils.shortUuid());
                    for (Element span : spanList) {
                        String aClass = span.attr("class");
                        if (StrUtil.isBlank(aClass)) {
                            continue;
                        }
                        switch (aClass) {
                            case "name": {
                                fractionInfo.setSchool(span.text());
                                break;
                            }
                            case "address": {
                                fractionInfo.setProvince(span.text());
                                break;
                            }
                            case "category": {
                                fractionInfo.setType(span.text());
                                break;
                            }
                            case "batch": {
                                fractionInfo.setNumber(span.text());
                                break;
                            }
                            case "year": {
                                fractionInfo.setYear(span.text());
                                break;
                            }
                            case "height": {
                                fractionInfo.setMaxScore(span.text());
                                break;
                            }
                            case "short": {
                                fractionInfo.setMinScore(span.text());
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }
                    logger.info(JSONUtil.toJsonStr(fractionInfo));
                    fractionInfoList.add(fractionInfo);
                }
            }
        }
        if (CollUtil.isNotEmpty(fractionInfoList)) {

        }
    }

    public static String getTextV() {
        String str = "";
        str = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                " <head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "  <title>高考分数线查询_高校录取分数线查询_大学分数线查询 - 中国高考网</title>\n" +
                "  <meta name=\"keywords\" content=\"高校录取分数线,2016高校分数线,高校历年分数线,分数线预测,分数线查询,今年高考分数线,高考录取分数线,历年高考录取分数线,大学录取分数线,今年大学录取分数线,各大学录取分数线\">\n" +
                "  <meta name=\"description\" content=\"中国高考网高考分数线查询频道为高考考生及家长提供大学录取分数线等高校分数线查询功能。\">\n" +
                "  <link rel=\"stylesheet\" type=\"text/css\" href=\"http://gaokao.chinakaoyan.com/statics/css/basic_new_v2.css\">\n" +
                "  <script>\n" +
                "var _hmt = _hmt || [];\n" +
                "(function() {\n" +
                "  var hm = document.createElement(\"script\");\n" +
                "  hm.src = \"//hm.baidu.com/hm.js?6f750027e850c0f186a356f8cba1ccb5\";\n" +
                "  var s = document.getElementsByTagName(\"script\")[0]; \n" +
                "  s.parentNode.insertBefore(hm, s);\n" +
                "})();\n" +
                "</script>\n" +
                "  <link rel=\"stylesheet\" type=\"text/css\" href=\"http://gaokao.chinakaoyan.com/statics/css/score.css\">\n" +
                "  <script type=\"text/javascript\" src=\"http://gaokao.chinakaoyan.com/statics/js/jquery-1.10.2.min.js\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"http://gaokao.chinakaoyan.com/statics/js/listed.js\"></script>\n" +
                " </head>\n" +
                " <body><!--miniNav-->\n" +
                "  <div class=\"miniNav\">\n" +
                "   <div class=\"container\">\n" +
                "    <div class=\"loginNav fl\">\n" +
                "     <ul class=\"mini-list font14\">\n" +
                "      <li><a href=\"http://gaokao.chinakaoyan.com/\" target=\"_blank\">首页</a></li>\n" +
                "      <li><a href=\"/news/\" target=\"_blank\">资讯</a></li>\n" +
                "      <li><a href=\"/s/\" target=\"_blank\" title=\"高考院校库\">挑大学</a></li>\n" +
                "      <li><a href=\"/s/spe/\" target=\"_blank\">选专业</a></li>\n" +
                "      <li><a href=\"/ask/\" target=\"_blank\">问答</a></li>\n" +
                "      <li><a href=\"/paihang/\" target=\"_blank\">排行榜</a></li>\n" +
                "      <li><a href=\"/course/\" target=\"_blank\">课程</a></li>\n" +
                "      <li><a href=\"/user/\">会员</a></li>\n" +
                "     </ul>\n" +
                "    </div>\n" +
                "    <div class=\"login fr font14\"><a href=\"/login/insert.shtml\" class=\"register\">注册</a> <a href=\"/login/login.shtml\" class=\"login-btn\"><i class=\"icon iconfont\">\uDB80\uDC0A</i>登录</a>\n" +
                "    </div>\n" +
                "   </div>\n" +
                "  </div><!--end mini--> <!--ysoso-->\n" +
                "  <div class=\"ysoso\">\n" +
                "   <div class=\"logo fl\">\n" +
                "    <h1><a href=\"/\" class=\"kylogo\"><span class=\"undis\">中国高考网</span></a></h1>\n" +
                "   </div>\n" +
                "   <div class=\"region fl\" id=\"region\">\n" +
                "    <div class=\"region-body font18\">\n" +
                "     全国站 <i class=\"icon iconjtfont\">\uE60F</i>\n" +
                "    </div>\n" +
                "    <div class=\"region-list undis\" id=\"region-list\">\n" +
                "     <div class=\"hot-tag font14\"><span class=\"sred\">热门</span> <span><a href=\"/beijing/\" target=\"_blank\">北京</a></span> <span><a href=\"/shanghai/\" target=\"_blank\">上海</a></span> <span><a href=\"/jiangsu/\" target=\"_blank\">江苏</a></span> <span><a href=\"/henan/\" target=\"_blank\">河南</a></span> <span><a href=\"/shaanxi/\" target=\"_blank\">陕西</a></span>\n" +
                "     </div>\n" +
                "     <div class=\"r-body font14\"><a href=\"/anhui/\" target=\"_blank\">安徽</a> <a href=\"/beijing/\" target=\"_blank\">北京</a> <a href=\"/fujian/\" target=\"_blank\">福建</a> <a href=\"/gansu/\" target=\"_blank\">甘肃</a> <a href=\"/guangdong/\" target=\"_blank\">广东</a> <a href=\"/guangxi/\" target=\"_blank\">广西</a><br><a href=\"/guizhou/\" target=\"_blank\">贵州</a> <a href=\"/hainan/\" target=\"_blank\">海南</a> <a href=\"/hebei/\" target=\"_blank\">河北</a> <a href=\"/henan/\" target=\"_blank\">河南</a> <a href=\"/hubei/\" target=\"_blank\">湖北</a> <a href=\"/hunan/\" target=\"_blank\">湖南</a><br><a href=\"/jilin/\" target=\"_blank\">吉林</a> <a href=\"/jiangsu/\" target=\"_blank\">江苏</a> <a href=\"/jiangxi/\" target=\"_blank\">江西</a> <a href=\"/liaoning/\" target=\"_blank\">辽宁</a> <a href=\"/ningxia/\" target=\"_blank\">宁夏</a> <a href=\"/qinghai/\" target=\"_blank\">青海</a><br><a href=\"/shandong/\" target=\"_blank\">山东</a> <a href=\"/shanxi/\" target=\"_blank\">山西</a> <a href=\"/shaanxi/\" target=\"_blank\">陕西</a> <a href=\"/shanghai/\" target=\"_blank\">上海</a> <a href=\"/sichuan/\" target=\"_blank\">四川</a> <a href=\"/tianjin/\" target=\"_blank\">天津</a><br><a href=\"/xizang/\" target=\"_blank\">西藏</a> <a href=\"/xinjiang/\" target=\"_blank\">新疆</a> <a href=\"/yunnan/\" target=\"_blank\">云南</a> <a href=\"/zhejiang/\" target=\"_blank\">浙江</a> <a href=\"/chongqing/\" target=\"_blank\">重庆</a> <a href=\"/taiwan/\" target=\"_blank\">台湾</a><br><a href=\"/hongkong/\" target=\"_blank\">香港</a> <a href=\"/macau/\" target=\"_blank\">澳门</a> <a href=\"/heilongjiang/\" target=\"_blank\">黑龙江</a> <a href=\"/neimenggu/\" target=\"_blank\">内蒙古</a>\n" +
                "     </div>\n" +
                "     <div class=\"r-footer\">\n" +
                "      <form action=\"/site/search\" method=\"post\"><input type=\"text\" name=\"province\" class=\"r-inpt fl\"> <input type=\"submit\" class=\"r-btn fl font16\" value=\"确认\">\n" +
                "      </form>\n" +
                "     </div>\n" +
                "    </div>\n" +
                "   </div><!--search-->\n" +
                "   <div class=\"search fl\">\n" +
                "    <form id=\"searchForm\" method=\"get\" name=\"soso_search_box\" action=\"/search/index\" target=\"_blank\"><input id=\"inputkey\" type=\"text\" value=\"\" name=\"keywords\" autocomplete=\"off\" placeholder=\"输入你搜索的内容\" class=\"i-key fl\"> <button id=\"searchBtn\" type=\"submit\" class=\"s-btn fl font18\">搜索</button>\n" +
                "    </form>\n" +
                "   </div>\n" +
                "   <div class=\"head-ad fr\"><a href=\"/course/\" target=\"_blank\"><img src=\"http://download.chinakaoyan.com/wwwfile/20160720122911.jpg\"></a>\n" +
                "   </div>\n" +
                "  </div><!--n22222222av-->\n" +
                "  <div class=\"nav\">\n" +
                "   <div class=\"container\">\n" +
                "    <ul class=\"nav-bate\">\n" +
                "     <li><a href=\"/\" title=\"高考网\"><i class=\"icon iconfont-home\"></i>高考网首页</a></li>\n" +
                "     <li><a href=\"/news/\" title=\"高考资源网\"><i class=\"icon iconfont-zy\"></i>高考资讯</a></li>\n" +
                "     <li class=\"this-nav\"><a href=\"/s/\"><i class=\"icon iconfont-dx\"></i>挑大学</a></li>\n" +
                "     <li><a href=\"/s/spe/\"><i class=\"icon iconfont-tzy\"></i>选专业</a></li>\n" +
                "     <li><a href=\"/ask/\"><i class=\"icon iconfont-wd\"></i>高考问答</a></li>\n" +
                "     <li><a href=\"/paihang/\"><i class=\"icon iconfont-ph\"></i>大学排行榜</a></li><!--<li ><a href=\"http://download.chinakaoyan.com/gaokao/\"><i class=\"icon iconfont-kc\"></i>资料库</a></li>-->\n" +
                "     <li><a href=\"/course/\"><i class=\"icon iconfont-zl\"></i>在线课程</a></li>\n" +
                "    </ul>\n" +
                "   </div>\n" +
                "  </div><!--nav over--><!--currt over-->\n" +
                "  <div class=\"container\">\n" +
                "   <div class=\"currt font14\"><a href=\"/\">中国高考网</a> &gt; <a href=\"/s\">高考院校库</a> &gt; 高校分数线\n" +
                "   </div>\n" +
                "  </div><!--currt over-->\n" +
                "  <div class=\"navbar\">\n" +
                "   <ul class=\"navbar-nav fl font16\">\n" +
                "    <li><a href=\"/s/sch/\">挑大学</a></li>\n" +
                "    <li><a href=\"/s/spe/\">选专业</a></li>\n" +
                "    <li class=\"active\"><a href=\"/s/fenshu2/\">高校分数线</a></li>\n" +
                "    <li><a href=\"/s/fenshu3/\">专业分数线</a></li>\n" +
                "    <li><a href=\"/s/fenshu1/\">地区批次线</a></li>\n" +
                "   </ul>\n" +
                "   <div class=\"university-search fr\">\n" +
                "    <form action=\"/school/searchSchool\" method=\"post\" role=\"university-search\"><input type=\"text\" class=\"form-control fl\" name=\"school_name\" placeholder=\"输入学校名\"> <button type=\"submit\" name=\"sub\" class=\"btn-un fl font14\">搜索</button>\n" +
                "    </form>\n" +
                "   </div>\n" +
                "  </div>\n" +
                "  <div class=\"hr20\"></div>\n" +
                "  <div class=\"tab-select\">\n" +
                "   <ul class=\"row font14\" id=\"tab-select\">\n" +
                "    <li class=\"select-list\">\n" +
                "     <dl>\n" +
                "      <dt>\n" +
                "       考生所在地：\n" +
                "      </dt>\n" +
                "      <dd class=\"select-all selected\">\n" +
                "       <a href=\"/s/fenshu2/k2013\">不限</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b157/k2013\">北京</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1935/k2013\">天津</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b603/k2013\">河北</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1564/k2013\">山西</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1304/k2013\">内蒙古</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1244/k2013\">辽宁</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1029/k2013\">吉林</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b877/k2013\">黑龙江</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1767/k2013\">上海</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1077/k2013\">江苏</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b2176/k2013\">浙江</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1/k2013\">安徽</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b166/k2013\">福建</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1152/k2013\">江西</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1454/k2013\">山东</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b748/k2013\">河南</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b947/k2013\">湖北</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b948/k2013\">湖南</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b316/k2013\">广东</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b414/k2013\">广西</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b583/k2013\">海南</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b2255/k2013\">重庆</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1777/k2013\">四川</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b502/k2013\">贵州</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b2050/k2013\">云南</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1941/k2013\">西藏</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1670/k2013\">陕西</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b234/k2013\">甘肃</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1412/k2013\">青海</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1392/k2013\">宁夏</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1965/k2013\">新疆</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b2288/k2013\">香港</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b2289/k2013\">澳门</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/b1907/k2013\">台湾</a>\n" +
                "      </dd>\n" +
                "     </dl></li>\n" +
                "    <li class=\"select-list\">\n" +
                "     <dl>\n" +
                "      <dt>\n" +
                "       院校所在地：\n" +
                "      </dt>\n" +
                "      <dd class=\"select-all selected\">\n" +
                "       <a href=\"/s/fenshu2/k2013\">不限</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a157/k2013\">北京</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1935/k2013\">天津</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a603/k2013\">河北</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1564/k2013\">山西</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1304/k2013\">内蒙古</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1244/k2013\">辽宁</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1029/k2013\">吉林</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a877/k2013\">黑龙江</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1767/k2013\">上海</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1077/k2013\">江苏</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a2176/k2013\">浙江</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1/k2013\">安徽</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a166/k2013\">福建</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1152/k2013\">江西</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1454/k2013\">山东</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a748/k2013\">河南</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a947/k2013\">湖北</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a948/k2013\">湖南</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a316/k2013\">广东</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a414/k2013\">广西</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a583/k2013\">海南</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a2255/k2013\">重庆</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1777/k2013\">四川</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a502/k2013\">贵州</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a2050/k2013\">云南</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1941/k2013\">西藏</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1670/k2013\">陕西</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a234/k2013\">甘肃</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1412/k2013\">青海</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1392/k2013\">宁夏</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1965/k2013\">新疆</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a2288/k2013\">香港</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a2289/k2013\">澳门</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/a1907/k2013\">台湾</a>\n" +
                "      </dd>\n" +
                "     </dl></li>\n" +
                "    <li class=\"select-list\">\n" +
                "     <dl>\n" +
                "      <dt>\n" +
                "       科目：\n" +
                "      </dt>\n" +
                "      <dd class=\"select-all selected\">\n" +
                "       <a href=\"/s/fenshu2/k2013\">不限</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/i1/k2013\">理科</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/i2/k2013\">文科</a>\n" +
                "      </dd>\n" +
                "     </dl></li>\n" +
                "    <li class=\"select-list\">\n" +
                "     <dl>\n" +
                "      <dt>\n" +
                "       录取批次：\n" +
                "      </dt>\n" +
                "      <dd class=\"select-all selected\">\n" +
                "       <a href=\"/s/fenshu2/k2013\">不限</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l1\">本科提前批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l2\">本科第一批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l3\">本科第二批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l4\">本科第三批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l8\">高职专科</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l10\">藏文专业本科</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l11\">彝文专业本科</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l12\">艺术类本科</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l13\">艺术类专科</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l14\">体育类本科</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l15\">体育类专科</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l16\">不祥</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l17\">本科第二批A</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l18\">本科第二批B</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l19\">本科第三批A</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l20\">本科第三批B</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l22\">高职专科第一批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l23\">高职专科第二批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l24\">体育类本科第一批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l25\">体育类本科第二批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l26\">艺术类本科第一批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l27\">艺术类本科第二批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l28\">体育类本科第三批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l29\">高职专科提前批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l30\">省内预科</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l31\">本科军检</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l32\">本科第一批（汉族）</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l33\">本科第一批（少数民族）</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l34\">本科第二批（汉族）</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l35\">本科第二批（少数民族）</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l36\">高职专科（汉族）</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l37\">高职专科（少数民族）</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l38\">公安高专</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l39\">本科第二批C类</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l40\">本科最后批测试</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l41\">艺术类本科美术类</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l42\">艺术类本科广电类</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l43\">艺术类本科其他类</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l44\">体育类本科体育类</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l45\">体育类本科特招生</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l46\">民族院校民族班</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l47\">重点本科（少数民族）</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l48\">重点本科（汉族）</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l49\">普通本科（少数民族）</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l50\">普通本科（汉族）</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l51\">专科（少数民族）</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l52\">专科（汉族）</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l53\">本科批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l54\">自主招生</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l55\">国家专项计划本科一批</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2013/l56\">国家专项计划本科二批</a>\n" +
                "      </dd>\n" +
                "     </dl></li>\n" +
                "    <li class=\"select-list\">\n" +
                "     <dl>\n" +
                "      <dt>\n" +
                "       年份：\n" +
                "      </dt>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/\">不限</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2010\">2010</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2011\">2011</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2012\">2012</a>\n" +
                "      </dd>\n" +
                "      <dd class=\"select-all selected\">\n" +
                "       <a href=\"/s/fenshu2/k2013\">2013</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2014\">2014</a>\n" +
                "      </dd>\n" +
                "      <dd>\n" +
                "       <a href=\"/s/fenshu2/k2015\">2015</a>\n" +
                "      </dd>\n" +
                "     </dl></li>\n" +
                "   </ul>\n" +
                "   <div class=\"select-result\">\n" +
                "    <dl>\n" +
                "     <dt>\n" +
                "      已选条件：\n" +
                "     </dt>\n" +
                "     <dd class=\"select-no\">\n" +
                "     </dd>\n" +
                "     <dd class=\"selected\">\n" +
                "      <a href=\"/s/fenshu2/\">2013</a>\n" +
                "     </dd>\n" +
                "    </dl>\n" +
                "   </div>\n" +
                "  </div><!--tab select over--> <!--container-->\n" +
                "  <div class=\"hr20\"></div>\n" +
                "  <div class=\"container\">\n" +
                "   <div class=\"maxBox fl\">\n" +
                "    <div class=\"row\">\n" +
                "     <div class=\"tab-form\">\n" +
                "      <div class=\"tab-form-title font14 b\"><span class=\"name\">院校名称</span> <span class=\"address\">考生所在地</span> <span class=\"category\">考生类别</span> <span class=\"batch\">批次</span> <span class=\"year\">年份</span> <span class=\"height\">最高分</span> <span class=\"short\">最低分</span> <span class=\"view\">查看全部</span>\n" +
                "      </div>\n" +
                "      <ul class=\"tab-form-body\">\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">江西</span> <span class=\"category\">文科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">431</span> <span class=\"short\">215</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">江西</span> <span class=\"category\">理科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">297</span> <span class=\"short\">231</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">新疆</span> <span class=\"category\">文科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">288</span> <span class=\"short\">200</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">新疆</span> <span class=\"category\">理科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">238</span> <span class=\"short\">238</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">宁夏</span> <span class=\"category\">文科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">426</span> <span class=\"short\">240</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">宁夏</span> <span class=\"category\">理科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">407</span> <span class=\"short\">216</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">青海</span> <span class=\"category\">文科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">357</span> <span class=\"short\">201</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">青海</span> <span class=\"category\">理科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">256</span> <span class=\"short\">173</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">西藏</span> <span class=\"category\">文科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">241</span> <span class=\"short\">235</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">西藏</span> <span class=\"category\">理科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">219</span> <span class=\"short\">203</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">河南</span> <span class=\"category\">文科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">409</span> <span class=\"short\">229</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">河南</span> <span class=\"category\">理科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">409</span> <span class=\"short\">229</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">内蒙古</span> <span class=\"category\">文科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">354</span> <span class=\"short\">280</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">内蒙古</span> <span class=\"category\">理科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">304</span> <span class=\"short\">244</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">山西</span> <span class=\"category\">文科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">446</span> <span class=\"short\">201</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">山西</span> <span class=\"category\">理科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">376</span> <span class=\"short\">220</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">河北</span> <span class=\"category\">文科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">398</span> <span class=\"short\">332</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">河北</span> <span class=\"category\">理科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">443</span> <span class=\"short\">209</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">甘肃</span> <span class=\"category\">文科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">412</span> <span class=\"short\">299</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "       <li><span class=\"name\">山东旅游职业学院</span> <span class=\"address\">甘肃</span> <span class=\"category\">理科</span> <span class=\"batch\">高职专科</span> <span class=\"year\">2013</span> <span class=\"height\">344</span> <span class=\"short\">344</span> <span class=\"view\"><a href=\"/s/sdts\">查看</a></span></li>\n" +
                "      </ul>\n" +
                "     </div><!--page-->\n" +
                "     <div class=\"hr20\"></div>\n" +
                "     <div class=\"page font16\"><span class=\"current\">1</span>&nbsp;<a href=\"http://gaokao.chinakaoyan.com/s/fenshu2/k2013?p=2\" data-ci-pagination-page=\"2\">2</a>&nbsp;<a href=\"http://gaokao.chinakaoyan.com/s/fenshu2/k2013?p=3\" data-ci-pagination-page=\"3\">3</a>&nbsp;<a href=\"http://gaokao.chinakaoyan.com/s/fenshu2/k2013?p=4\" data-ci-pagination-page=\"4\">4</a>&nbsp;<a href=\"http://gaokao.chinakaoyan.com/s/fenshu2/k2013?p=5\" data-ci-pagination-page=\"5\">5</a>&nbsp;<a href=\"http://gaokao.chinakaoyan.com/s/fenshu2/k2013?p=6\" data-ci-pagination-page=\"6\">6</a>&nbsp;<a href=\"http://gaokao.chinakaoyan.com/s/fenshu2/k2013?p=2\" data-ci-pagination-page=\"2\" rel=\"next\">下一页</a>&nbsp;<a href=\"http://gaokao.chinakaoyan.com/s/fenshu2/k2013?p=785\" data-ci-pagination-page=\"785\">尾页</a>&nbsp;\n" +
                "     </div><!--page over-->\n" +
                "    </div>\n" +
                "    <div class=\"hr20\"></div>\n" +
                "    <div class=\"list-m-ad\"></div>\n" +
                "   </div><!--sidebar-->\n" +
                "   <div class=\"sidebar fr\"><!--widget-->\n" +
                "    <div class=\"row widget\">\n" +
                "     <ul class=\"w-title font16\" id=\"w-title\">\n" +
                "      <li class=\"selected\">大学排行榜</li>\n" +
                "      <li>专业排行榜</li>\n" +
                "     </ul>\n" +
                "     <div class=\"w-content\">\n" +
                "      <div class=\"row wg\">\n" +
                "       <div class=\"col-title\"><span class=\"ranking\">名次</span> <span class=\"name\">学校名称</span> <span class=\"address\">所在省市</span> <span class=\"category\">类型</span> <span class=\"total\">总分</span>\n" +
                "       </div>\n" +
                "       <ul class=\"col-body\">\n" +
                "        <li><span class=\"ranking\"><i class=\"on\">1</i></span> <span class=\"name\">北京大学</span> <span class=\"address\">北京</span> <span class=\"category\">综合</span> <span class=\"total\">100</span></li>\n" +
                "        <li><span class=\"ranking\"><i class=\"on\">2</i></span> <span class=\"name\">清华大学</span> <span class=\"address\">北京</span> <span class=\"category\">工科</span> <span class=\"total\">98.5</span></li>\n" +
                "        <li><span class=\"ranking\"><i class=\"on\">3</i></span> <span class=\"name\">复旦大学</span> <span class=\"address\">上海</span> <span class=\"category\">综合</span> <span class=\"total\">82.79</span></li>\n" +
                "        <li><span class=\"ranking\"><i>4</i></span> <span class=\"name\">武汉大学</span> <span class=\"address\">湖北</span> <span class=\"category\">综合</span> <span class=\"total\">82.43</span></li>\n" +
                "        <li><span class=\"ranking\"><i>5</i></span> <span class=\"name\">浙江大学</span> <span class=\"address\">浙江</span> <span class=\"category\">综合</span> <span class=\"total\">82.38</span></li>\n" +
                "        <li><span class=\"ranking\"><i>6</i></span> <span class=\"name\">中国人民大学</span> <span class=\"address\">北京</span> <span class=\"category\">综合</span> <span class=\"total\">81.98</span></li>\n" +
                "        <li><span class=\"ranking\"><i>7</i></span> <span class=\"name\">上海交通大学</span> <span class=\"address\">上海</span> <span class=\"category\">综合</span> <span class=\"total\">81.76</span></li>\n" +
                "        <li><span class=\"ranking\"><i>8</i></span> <span class=\"name\">南京大学</span> <span class=\"address\">江苏</span> <span class=\"category\">综合</span> <span class=\"total\">75.88</span></li>\n" +
                "       </ul>\n" +
                "      </div>\n" +
                "      <div class=\"row wg undis\">\n" +
                "       <div class=\"col-title\"><span class=\"ranking\">名次</span> <span class=\"name\">专业名称</span> <span class=\"address\">所在省市</span> <span class=\"category\">专业大类</span> <span class=\"total\">关注</span>\n" +
                "       </div>\n" +
                "       <ul class=\"col-body\">\n" +
                "        <li><span class=\"ranking\"><i class=\"on\">1</i></span> <span class=\"name\">金融学</span> <span class=\"address\">北京</span> <span class=\"category\">经济学</span> <span class=\"total\">3049362</span></li>\n" +
                "        <li><span class=\"ranking\"><i class=\"on\">2</i></span> <span class=\"name\">土木工程</span> <span class=\"address\">北京</span> <span class=\"category\">综工学</span> <span class=\"total\">2598987</span></li>\n" +
                "        <li><span class=\"ranking\"><i class=\"on\">3</i></span> <span class=\"name\">国际经济与贸易</span> <span class=\"address\">北京</span> <span class=\"category\">经济学</span> <span class=\"total\">2497485</span></li>\n" +
                "        <li><span class=\"ranking\"><i>4</i></span> <span class=\"name\">机械设计制作及</span> <span class=\"address\">北京</span> <span class=\"category\">工学</span> <span class=\"total\">2278535</span></li>\n" +
                "        <li><span class=\"ranking\"><i>5</i></span> <span class=\"name\">会计学</span> <span class=\"address\">北京</span> <span class=\"category\">管理学</span> <span class=\"total\">2103597</span></li>\n" +
                "        <li><span class=\"ranking\"><i>6</i></span> <span class=\"name\">经济学</span> <span class=\"address\">北京</span> <span class=\"category\">经济学</span> <span class=\"total\">2039488</span></li>\n" +
                "        <li><span class=\"ranking\"><i>7</i></span> <span class=\"name\">电气工程及其自</span> <span class=\"address\">北京</span> <span class=\"category\">工学</span> <span class=\"total\">2010907</span></li>\n" +
                "        <li><span class=\"ranking\"><i>8</i></span> <span class=\"name\">临床一些</span> <span class=\"address\">北京</span> <span class=\"category\">医学</span> <span class=\"total\">1639016</span></li>\n" +
                "       </ul>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "    </div><!--widget over--> <!--widget-->\n" +
                "    <div class=\"hr20\"></div>\n" +
                "    <div class=\"row widget\">\n" +
                "     <h4 class=\"news-title b\">高考新闻</h4>\n" +
                "     <ul class=\"news-group font14\"><!--此部分需要程序写入最新全部发布新闻8条，先做了静态处理-->\n" +
                "      <li><a class=\"fl\" href=\"/news/57285.html\" target=\"_blank\">教育部：高职院校今明两年扩招200万</a> <span class=\"fr\">12-08</span></li>\n" +
                "      <li><a class=\"fl\" href=\"/news/57281.html\" target=\"_blank\">400余所高职院校与国外机构合作办学</a> <span class=\"fr\">12-08</span></li>\n" +
                "      <li><a class=\"fl\" href=\"/news/57269.html\" target=\"_blank\">2021安徽普通高校招生补报名工作开始</a> <span class=\"fr\">12-08</span></li>\n" +
                "      <li><a class=\"fl\" href=\"/news/57279.html\" target=\"_blank\">全国高职(专科)招生483.61万人</a> <span class=\"fr\">12-08</span></li>\n" +
                "      <li><a class=\"fl\" href=\"/news/57277.html\" target=\"_blank\">全力推动海南大学建设国内一流大学</a> <span class=\"fr\">12-08</span></li>\n" +
                "      <li><a class=\"fl\" href=\"/news/56841.html\" target=\"_blank\">15所港校内地两方式招收本科生</a> <span class=\"fr\">11-25</span></li>\n" +
                "      <li><a class=\"fl\" href=\"/news/56651.html\" target=\"_blank\">教育部2021艺考减少校考专业范围</a> <span class=\"fr\">10-24</span></li>\n" +
                "      <li><a class=\"fl\" href=\"/news/56649.html\" target=\"_blank\">2021高考部分特殊类型招生基本要求</a> <span class=\"fr\">10-24</span></li>\n" +
                "     </ul>\n" +
                "    </div><!--widget over--> <!--ad-->\n" +
                "    <div class=\"sid-ad\"></div>\n" +
                "   </div><!--sidebar over-->\n" +
                "  </div><!--container over-->\n" +
                "  <script type=\"text/javascript\">\n" +
                "$(function(){\n" +
                "\tjQuery.hoverTab(\"region\",\"thisM\",\"region-list\");\n" +
                "\tjQuery.tabSide(\"w-title\",\"wg\");\n" +
                "});\n" +
                "</script>\n" +
                "  <div class=\"hr20\"></div>\n" +
                "  <div class=\"footer font14\">\n" +
                "   <p><a href=\"/about/1.shtml\">关于我们</a> <a href=\"/about/2.shtml\">发展历程</a> <a href=\"/about/3.shtml\">商务合作</a> <a href=\"/about/4.shtml\">服务条款</a> <a href=\"/about/5.shtml\">隐私保护</a> <a href=\"/about/7.shtml\">友情链接</a> <a href=\"/about/8.shtml\">联系我们</a></p>\n" +
                "   <p>1998-2018 ChinaKaoyan.com Network Studio. All Rights Reserved. 沪ICP备12018245号</p>\n" +
                "  </div>\n" +
                "  <script type=\"text/javascript\">\n" +
                "$(function(){\n" +
                "\tjQuery.hoverTab(\"region\",\"thisM\",\"region-list\");\n" +
                "});\n" +
                "</script><!--bt-->\n" +
                "  <script>\n" +
                "var _hmt = _hmt || [];\n" +
                "(function() {\n" +
                "  var hm = document.createElement(\"script\");\n" +
                "  hm.src = \"//hm.baidu.com/hm.js?7391c131571d09a58f1b6fccdb70fc2e\";\n" +
                "  var s = document.getElementsByTagName(\"script\")[0]; \n" +
                "  s.parentNode.insertBefore(hm, s);\n" +
                "})();\n" +
                "</script><!--bt-->\n" +
                " </body>\n" +
                "</html>";

        return str;
    }
}
