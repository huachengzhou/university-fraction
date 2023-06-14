package com.ng.todo.reptile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.ng.todo.common.enums.ReptileTypeEnum;
import com.ng.todo.common.utils.LangUtils;
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

import java.util.*;

public class Demo8 {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static final String URL = "https://college.gaokao.com/school/tinfo/1/result/3/1/";
    public static final ReptileTypeEnum TYPE_ENUM = ReptileTypeEnum.Jsoup;
    public static final RequestMethod METHOD = RequestMethod.GET;

    @Test
    public void test1() {
        Map<String,String> stringMap = new HashMap<>(10) ;
        stringMap.put("理科","1") ;
        stringMap.put("文科","2") ;
        stringMap.put("综合","3") ;
        stringMap.put("其他","4") ;
        stringMap.put("艺术理","8") ;
        stringMap.put("艺术文","9") ;
        stringMap.put("综合改革","10") ;

        org.jsoup.nodes.Document document = Jsoup.parse(getTextV());
        Element body = document.body();
        Element wrapper = body.getElementById("pointbyarea");
        Elements tableList = wrapper.select("table");
        if (CollUtil.isEmpty(tableList)) {
            return;
        }
        List<SchoolFractionInfo> infoList = new ArrayList<>();
        int k = 0;
        for (Element table : tableList) {
            Element tbody = table.selectFirst("tbody");
            if (tbody == null) {
                continue;
            }
            Elements trList = tbody.select("tr");
            for (int j = 1; j < trList.size(); j++) {
                Element tr = trList.get(j);
                Elements tdList = tr.select("td");
                SchoolFractionInfo schoolFractionInfo = new SchoolFractionInfo();
                for (int i = 0; i < tdList.size(); i++) {
                    Element element = tdList.get(i);
                    switch (i) {
                        case 0: {
                            schoolFractionInfo.setYear(element.text());
                            break;
                        }
                        case 1: {
                            schoolFractionInfo.setMinScore(element.text());
                            break;
                        }
                        case 2: {
                            schoolFractionInfo.setMaxScore(element.text());
                            break;
                        }
                        case 3: {
                            schoolFractionInfo.setFraction(element.text());
                            break;
                        }
                        case 4: {
                            schoolFractionInfo.setEnrollment(element.text());
                            break;
                        }
                        case 5: {
                            schoolFractionInfo.setBatch(element.text());
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
                schoolFractionInfo.setRemark("高校分数线");
                schoolFractionInfo.setMethod("统招");
                schoolFractionInfo.setGmtCreated(new Date());
                schoolFractionInfo.setGmtModified(new Date());
                schoolFractionInfo.setUuid(LangUtils.shortUuid());

                logger.info(JSONUtil.toJsonStr(schoolFractionInfo));
                infoList.add(schoolFractionInfo);
            }
            k++;
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

        }
    }


    public static String getTextV() {
        String str = "";
        String projectPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Demo8.txt";
        String readString = FileUtil.readString(projectPath, "UTF-8");
        return readString;
    }

    @Test
    public void test3() {
        String textV = getTextV();
        System.out.println(textV);
    }

}
