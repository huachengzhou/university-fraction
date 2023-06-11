package com.ng.todo.reptile;

import cn.hutool.core.collection.CollUtil;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ng.todo.common.enums.ReptileTypeEnum;
import com.ng.todo.common.utils.MyHttpUtils;
import com.ng.todo.pojo.dto.InfoData;
import com.ng.todo.pojo.dto.MyHttpParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URL;

public class Demo4 {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static final String S_SICHUAN_HTML = "https://mdaxue.911cha.com/s_sichuan.html";
    public static final ReptileTypeEnum TYPE_ENUM = ReptileTypeEnum.Jsoup;
    public static final RequestMethod METHOD = RequestMethod.GET;

    @Test
    public void test1() throws Exception {
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);//运行js脚本执行
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置支持AJAX
        webClient.getOptions().setCssEnabled(false);//忽略css
        webClient.getOptions().setUseInsecureSSL(false);//ssl安全访问
        webClient.getOptions().setThrowExceptionOnScriptError(false);  //解析js出错时不抛异常
        webClient.getOptions().setTimeout(10000);  //超时时间  ms

        //获取页面
        HtmlPage page = webClient.getPage(S_SICHUAN_HTML);

        System.out.println("页面title文本:"+page.getTitleText());
        System.out.println("-------------------执行js脚本之前");
        System.out.println(page.asXml());//页面文档结构字符串

        webClient.waitForBackgroundJavaScript(5000);   //等待js脚本执行完成
        System.out.println("-------------------执行js脚本后");
        System.out.println(page.asXml());//页面文档结构字符串

    }

    @Test
    public void test2()throws Exception{
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);//运行js脚本执行
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置支持AJAX
        webClient.getOptions().setCssEnabled(false);//忽略css
        webClient.getOptions().setUseInsecureSSL(true);//ssl安全访问
        webClient.getOptions().setThrowExceptionOnScriptError(false);  //解析js出错时不抛异常
        webClient.getOptions().setTimeout(10000);  //超时时间  ms

        //获取页面
        String url ="https://www.baidu.com";
        HtmlPage page = webClient.getPage(url);

        System.out.println("页面title文本:"+page.getTitleText());
        System.out.println("-------------------执行js脚本之前");
        System.out.println(page.asXml());//页面文档结构字符串

        webClient.waitForBackgroundJavaScript(5000);   //等待js脚本执行完成
        System.out.println("-------------------执行js脚本后");
        System.out.println(page.asXml());//页面文档结构字符串
    }
}
