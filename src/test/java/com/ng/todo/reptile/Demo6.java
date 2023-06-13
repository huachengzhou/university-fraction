package com.ng.todo.reptile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.ng.todo.common.enums.ReptileTypeEnum;
import com.ng.todo.common.utils.LangUtils;
import com.ng.todo.common.utils.MyHttpUtils;
import com.ng.todo.entity.UniversityInfo;
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

public class Demo6 {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static final String URL = "https://college.gaokao.com/schlist/";
    public static final ReptileTypeEnum TYPE_ENUM = ReptileTypeEnum.Jsoup;
    public static final RequestMethod METHOD = RequestMethod.GET;

    @Test
    public void test1() {
        org.jsoup.nodes.Document document = Jsoup.parse(getTextV());
        Element body = document.body();
        Element wrapper = body.getElementById("wrapper");
        Elements scoresList = wrapper.select(".scores_List");
        if (CollUtil.isEmpty(scoresList)) {
            return;
        }
        List<UniversityInfo> infoList = new ArrayList<>();
        for (Element scores : scoresList) {
            Elements dlElements = scores.select("dl");
            if (CollUtil.isEmpty(dlElements)) {
                continue;
            }
            for (int i = 0; i < dlElements.size(); i++) {
                if (i == 0) {
                    continue;
                }
                Element dlElement = dlElements.get(i);
                UniversityInfo universityInfo = new UniversityInfo();
                Element element = dlElement.selectFirst("dt");
                if (element != null) {
                    Element strong = element.selectFirst("strong");
                    universityInfo.setName(strong.text());
                    Element aElement = element.selectFirst("a");
                    if (aElement != null) {
                        universityInfo.setOfficialWebsite(aElement.attr("href"));
                    }
                }


                universityInfo.setGmtCreated(new Date());
                universityInfo.setGmtModified(new Date());
                universityInfo.setUuid(LangUtils.shortUuid());

                logger.info(JSONUtil.toJsonStr(universityInfo));
                infoList.add(universityInfo);
            }
        }

        if (CollUtil.isNotEmpty(infoList)) {

        }
    }


    @Test
    public void test2() {
        InfoData infoData = MyHttpUtils.getHtml(new MyHttpParams(TYPE_ENUM, URL, METHOD));
        if (infoData != null) {
            org.jsoup.nodes.Document document = (org.jsoup.nodes.Document) infoData.getValue();
            Element body = document.body();
            Element wrapper = body.getElementById("wrapper");
            Elements scoresList = wrapper.select("scores_List");
            if (CollUtil.isEmpty(scoresList)) {
                return;
            }
            List<UniversityInfo> infoList = new ArrayList<>();
            for (Element scores : scoresList) {
                Elements dlElements = scores.select("dl");
                if (CollUtil.isEmpty(dlElements)) {
                    continue;
                }
                for (int i = 0; i < dlElements.size(); i++) {
                    if (i == 0) {
                        continue;
                    }
                    Element dlElement = dlElements.get(i);

                    UniversityInfo universityInfo = new UniversityInfo();

                    universityInfo.setGmtCreated(new Date());
                    universityInfo.setGmtModified(new Date());
                    universityInfo.setUuid(LangUtils.shortUuid());

                    logger.info(JSONUtil.toJsonStr(universityInfo));
                    infoList.add(universityInfo);
                }
            }

            if (CollUtil.isNotEmpty(infoList)) {

            }
        }
    }


    public static String getTextV() {
        String str = "";
        String projectPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Demo6.txt";
        String readString = FileUtil.readString(projectPath, "UTF-8");
        return readString;
    }

    @Test
    public void test3() {
        String textV = getTextV();
        System.out.println(textV);
    }

}
