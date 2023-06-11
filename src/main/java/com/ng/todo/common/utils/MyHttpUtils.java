package com.ng.todo.common.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ng.todo.pojo.dto.InfoData;
import com.ng.todo.pojo.dto.MyHttpParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyHttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(MyHttpUtils.class);

    public static InfoData getHtml(MyHttpParams myHttpParams) {
        InfoData infoData = new InfoData();
        switch (myHttpParams.getTypeEnum()) {
            case WebClient: {
                // 实例化Web客户端
                WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
                //考虑买一个 或者找一个代理ip  国外的
//                WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52,"202.110.67.141",9091);
//                webClient.getOptions().setCssEnabled(true);
//                webClient.getOptions().setJavaScriptEnabled(true);
                try {
                    // 休息10秒 等待htmlunit执行Js
                    Thread.sleep(1000 * 3);
                    HtmlPage htmlPage = webClient.getPage(myHttpParams.getUrl());
                    infoData.setHtml(htmlPage.asXml());
                    infoData.setValue(htmlPage);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                break;
            }
            case HttpClient: {
                if (myHttpParams.getRequestMethod() != null) {
                    switch (myHttpParams.getRequestMethod()) {
                        case GET: {
                            try {
                                toHttpClientGet(myHttpParams.getUrl(), infoData);
                            } catch (IOException e) {
                                logger.error(e.getMessage());
                            }
                            break;
                        }
                        case POST: {
                            try {
                                toHttpClientPost(myHttpParams.getUrl(), infoData);
                            } catch (IOException e) {
                                logger.error(e.getMessage());
                            }
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
                break;
            }
            case HttpReqest: {
                if (myHttpParams.getRequestMethod() != null) {
                    switch (myHttpParams.getRequestMethod()) {
                        case GET: {
                            String result = HttpUtil.get(myHttpParams.getUrl());
                            infoData.setValue(JSONUtil.parseObj(result));
                            infoData.setHtml(result);
                            break;
                        }
                        case POST: {
                            String result = HttpUtil.post(myHttpParams.getUrl(), myHttpParams.getParamMap());
                            infoData.setValue(JSONUtil.parseObj(result));
                            infoData.setHtml(result);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
                break;
            }
            case Jsoup: {
                //获取连接
                Connection con = Jsoup.connect(myHttpParams.getUrl());
                if (myHttpParams.getParamMap() != null && !myHttpParams.getParamMap().isEmpty()) {
                    Map<String, String> map = new HashMap<>();
                    myHttpParams.getParamMap().entrySet().forEach(stringObjectEntry -> map.put(stringObjectEntry.getKey(), String.valueOf(stringObjectEntry.getValue())));
                    con.data(map);
                }
                if (myHttpParams.getRequestMethod() != null) {
                    switch (myHttpParams.getRequestMethod()) {
                        case GET: {
                            try {
                                org.jsoup.nodes.Document document = con.get();
                                infoData.setHtml(document.html());
                                infoData.setValue(document);
                            } catch (IOException e) {
                                logger.error(e.getMessage());
                            }
                            break;
                        }
                        case POST: {
                            try {
                                org.jsoup.nodes.Document document = con.post();
                                infoData.setHtml(document.html());
                                infoData.setValue(document);
                            } catch (IOException e) {
                                logger.error(e.getMessage());
                            }
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
                break;
            }
            default: {
                break;
            }
        }
        infoData.setTypeEnum(myHttpParams.getTypeEnum());
        infoData.setUrl(myHttpParams.getUrl());
        infoData.setRequestMethod(myHttpParams.getRequestMethod());
        return infoData;
    }

    private static boolean toHttpClientPost(String url, InfoData infoData) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
        HttpResponse httpResponse = null;

        httpResponse = httpClient.execute(httpPost);
        if (httpResponse == null) {
            return true;
        }
        HttpEntity httpEntity = httpResponse.getEntity();
        if (httpEntity == null) {
            return true;
        }
        String webHtml = EntityUtils.toString(httpEntity, "utf-8");
        infoData.setHtml(webHtml);
        infoData.setValue(httpEntity);
        return false;
    }

    private static boolean toHttpClientGet(String url, InfoData infoData) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000) // 设置连接超时时间 3秒钟
                .setSocketTimeout(4000) // 设置读取超时时间4秒钟
                .build();
        httpGet.setConfig(config);
        HttpResponse httpResponse = null;

        httpResponse = httpClient.execute(httpGet);
        if (httpResponse == null) {
            return true;
        }
        HttpEntity httpEntity = httpResponse.getEntity();
        if (httpEntity == null) {
            return true;
        }
        String webHtml = EntityUtils.toString(httpEntity, "utf-8");
        infoData.setValue(httpEntity);
        infoData.setHtml(webHtml);
        return false;
    }
}
