package com.ng.todo.service.reptile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ng.todo.common.utils.LangUtils;
import com.ng.todo.entity.CommonSchoolFractionInfo;
import com.ng.todo.service.CommonSchoolFractionInfoService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
public class CommonOtherBasicEntityService {
    @Autowired
    private CommonSchoolFractionInfoService commonSchoolFractionInfoService;

    private final String baseUrl = "http://gaokao.chinakaoyan.com/s/fenshu2/k";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 拼接 年份
     */
    public void invoke() {
        Map<Integer, Integer> integerMap = new HashMap<>();
        //年份 , 最大页码
        integerMap.put(2013, 785);
        integerMap.put(2014, 947);
        integerMap.put(2015, 22);
        Iterator<Map.Entry<Integer, Integer>> iterator = integerMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> integerEntry = iterator.next();
            logger.info(integerEntry.getKey() + "-----" + integerEntry.getValue());
            invokeAssemble(integerEntry.getKey(), integerEntry.getValue());

            logger.info("sleep 100");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 处理分页
     * @param year
     * @param page
     */
    private void invokeAssemble(Integer year, Integer page) {
        for (int i = 1; i <= page; i++) {
            String url = baseUrl + year.toString();
            if (i > 1) {
                url += "?p=" + i;
            }
            org.jsoup.nodes.Document document = null;
            try {
                document = Jsoup.parse(new URL(url), 5000);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            Element body = document.body();
            logger.info("run body ..............");

            logger.info("sleep 100");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
            invokeWrite(body) ;
        }
    }

    private void invokeWrite(Element body) {
        List<CommonSchoolFractionInfo> infoList = new ArrayList<>();
        Elements elements = body.select(".tab-form-body");
        if (CollUtil.isEmpty(elements)) {
            return;
        }
        for (Element element : elements) {
            Elements liList = element.select("li");
            if (CollUtil.isEmpty(liList)) {
                continue;
            }
            for (Element li : liList) {
                Elements spanList = li.select("span");
                if (CollUtil.isEmpty(spanList)) {
                    continue;
                }
                CommonSchoolFractionInfo fractionInfo = new CommonSchoolFractionInfo();
                fractionInfo.setGmtCreated(new Date());
                fractionInfo.setGmtModified(new Date());
                fractionInfo.setMethod("统招");
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
                            fractionInfo.setFraction(span.text());
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
                logger.info(JSONUtil.toJsonStr(fractionInfo));
                infoList.add(fractionInfo);
            }
        }
        if (CollUtil.isNotEmpty(infoList)) {
            try {
                commonSchoolFractionInfoService.saveBatch(infoList);
            } catch (Exception e) {
                logger.error(e.getMessage());
                for (CommonSchoolFractionInfo fractionInfo:infoList){
                    try {
                        commonSchoolFractionInfoService.save(fractionInfo) ;
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                    }
                }
            }
        }
    }
}
