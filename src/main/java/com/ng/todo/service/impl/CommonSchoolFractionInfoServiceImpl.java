package com.ng.todo.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ng.todo.common.utils.LangUtils;
import com.ng.todo.entity.CommonSchoolFractionInfo;
import com.ng.todo.mapper.CommonSchoolFractionInfoMapper;
import com.ng.todo.service.CommonSchoolFractionInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * <p>
 * 大学高考分数(普通) 服务实现类
 * </p>
 *
 * @author zch
 * @since 2023-06-10
 */
@Service
public class CommonSchoolFractionInfoServiceImpl extends ServiceImpl<CommonSchoolFractionInfoMapper, CommonSchoolFractionInfo> implements CommonSchoolFractionInfoService {
    private final String baseUrl = "http://gaokao.chinakaoyan.com/s/fenshu2/k";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 拼接 年份
     */
    public void invoke() {
        Map<Integer, Integer> integerMap = new HashMap<>();
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
        List<CommonSchoolFractionInfo> fractionInfoList = new ArrayList<>();
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
        if (CollUtil.isNotEmpty(fractionInfoList)) {
            saveBatch(fractionInfoList);
        }
    }
}