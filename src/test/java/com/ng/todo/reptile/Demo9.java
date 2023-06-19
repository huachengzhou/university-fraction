package com.ng.todo.reptile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.ng.todo.common.enums.ReptileTypeEnum;
import com.ng.todo.common.utils.AsposeUtils;
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

import java.util.*;

/**
 * 招生简章
 */
public class Demo9 {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static final String URL = "http://www.gk114.com/a/gxzs/zszc/xinjiang/2023/0612/28301.html";
    public static final ReptileTypeEnum TYPE_ENUM = ReptileTypeEnum.Jsoup;
    public static final RequestMethod METHOD = RequestMethod.GET;


    @Test
    public void test1() {
        org.jsoup.nodes.Document document = Jsoup.parse(getTextV());
        Element body = document.body();
        Element element = body.selectFirst(".main1");
        Element selectFirst = element.selectFirst(".g_con");
        System.out.println(selectFirst.html());
        String projectPath = System.getProperty("user.dir") + "\\src\\test\\resources\\zsjz\\" + UUID.randomUUID().toString() + ".docx";
        try {
            Document doc2 = new Document();
            DocumentBuilder builder = new DocumentBuilder(doc2);
            builder.insertHtml(selectFirst.html());
            AsposeUtils.saveWord(projectPath, doc2);
            System.out.println(projectPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runDocument() {
        for (int i = 1; i <= 71; i++) {
//            String url = "http://www.gk114.com/a/gxzs/list_155_1.html";
            String url = String.format("http://www.gk114.com/a/gxzs/list_155_%s.html",String.valueOf(i));
            InfoData infoData = MyHttpUtils.getHtml(new MyHttpParams(TYPE_ENUM, url, METHOD));
            if (infoData == null){
                continue;
            }
            org.jsoup.nodes.Document document = (org.jsoup.nodes.Document) infoData.getValue();
            Element body = document.body();
            Element element = body.selectFirst(".main1");
            Element gLists = element.selectFirst(".g_list");
            Element ul = gLists.selectFirst(".list2");
            Elements liLists = ul.select("li");
            for (Element li : liLists) {
                try {
                    Element h3 = li.selectFirst("h3");
                    String text = h3.text();
                    Element a = h3.selectFirst("a");
                    String href = a.attr("href");
                    createDocument(href, text);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            logger.info("run body ..............");

            logger.info("sleep 100");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }


    }

    private void createDocument(String url, String name) {
        InfoData infoData = MyHttpUtils.getHtml(new MyHttpParams(TYPE_ENUM, url, METHOD));
        if (infoData != null) {
            org.jsoup.nodes.Document document = (org.jsoup.nodes.Document) infoData.getValue();
            Element body = document.body();
            Element element = body.selectFirst(".main1");
            Element selectFirst = element.selectFirst(".g_con");
            System.out.println(selectFirst.html());
            String projectPath = System.getProperty("user.dir") + "\\src\\test\\resources\\zsjz\\" + name + ".docx";
            try {
                Document doc2 = new Document();
                DocumentBuilder builder = new DocumentBuilder(doc2);
                builder.insertHtml(selectFirst.html());
                AsposeUtils.saveWord(projectPath, doc2);
                System.out.println(projectPath);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }


    @Test
    public void test2() {
        InfoData infoData = MyHttpUtils.getHtml(new MyHttpParams(TYPE_ENUM, URL, METHOD));
        if (infoData != null) {
            org.jsoup.nodes.Document document = (org.jsoup.nodes.Document) infoData.getValue();
            Element body = document.body();
            Element wrapper = body.getElementById("wrapper");

        }
    }


    public static String getTextV() {
        String str = "";
        String projectPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Demo9.txt";
        String readString = FileUtil.readString(projectPath, "UTF-8");
        return readString;
    }

    @Test
    public void test3() {
        String textV = getTextV();
        System.out.println(textV);
    }
}
