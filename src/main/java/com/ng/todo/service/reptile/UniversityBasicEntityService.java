package com.ng.todo.service.reptile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ng.todo.common.enums.SchoolTypeEnum;
import com.ng.todo.common.utils.LangUtils;
import com.ng.todo.entity.UniversityInfo;
import com.ng.todo.service.UniversityInfoService;
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

/**
 * 高考网  院校信息 简单录入
 */
@Service
public class UniversityBasicEntityService {

    @Autowired
    private UniversityInfoService  universityInfoService;

    private final String baseUrl = "https://college.gaokao.com/schpoint/p";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean clear() {
        QueryWrapper<UniversityInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UniversityInfo.TYPE_ENUM, typeEnum().toString());
        return universityInfoService.remove(queryWrapper);
    }

    public SchoolTypeEnum typeEnum() {
        return SchoolTypeEnum.COLLEGE_EDU_CN;
    }

    /**
     * @throws Exception
     */
    public void invoke() throws Exception {
        for (int i = 1; i <= 50; i++) {
            String url = baseUrl + i+"/";
            org.jsoup.nodes.Document document = null;
            try {
                document = Jsoup.parse(new URL(url), 5000);
            } catch (IOException e) {
                logger.error(e.getMessage());
                continue;
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


    /**
     * 具体处理数据
     * @param body
     */
    private void invokeWrite(Element body){
        if (body == null){
            return;
        }
        Element wrapper = body.getElementById("wrapper");
        Elements scoresList = wrapper.select("scores_List");
        if (CollUtil.isEmpty(scoresList)) {
            return;
        }
        List<UniversityInfo> infoList = new ArrayList<>() ;
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

                UniversityInfo universityInfo = new UniversityInfo() ;

                universityInfo.setGmtCreated(new Date());
                universityInfo.setGmtModified(new Date());
                universityInfo.setTypeEnum(typeEnum().toString()) ;
                universityInfo.setUuid(LangUtils.shortUuid());

                logger.info(JSONUtil.toJsonStr(universityInfo));
                infoList.add(universityInfo);
            }
        }

        if (CollUtil.isNotEmpty(infoList)){
            universityInfoService.saveBatch(infoList) ;
        }
    }

    public List<UniversityInfo> findUniversityInfoList() {
        QueryWrapper<UniversityInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UniversityInfo.TYPE_ENUM, typeEnum().toString());
        return universityInfoService.list(queryWrapper);
    }


    private String getObject(Object obj) {
        if (obj == null) {
            return null;
        }
        return String.valueOf(obj);
    }
}
