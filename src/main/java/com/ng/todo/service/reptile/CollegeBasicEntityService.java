package com.ng.todo.service.reptile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ng.todo.common.enums.SchoolTypeEnum;
import com.ng.todo.common.utils.LangUtils;
import com.ng.todo.entity.SchoolFractionInfo;
import com.ng.todo.pojo.dto.InfoData;
import com.ng.todo.pojo.dto.MyHttpParams;
import com.ng.todo.service.BaseAreaService;
import com.ng.todo.service.BasicEntityAbstract;
import com.ng.todo.service.SchoolFractionInfoService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 高考网 专业分数线
 * http://college.gaokao.com/schpoint/  爬虫
 */
@Service
public class CollegeBasicEntityService extends BasicEntityAbstract {

    public static final String REMARK = "专业分数线";
    private final String baseUrl = "https://college.gaokao.com/spepoint/y";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean clear() {
        SchoolFractionInfoService schoolFractionInfoService = getSchoolFractionInfoService();
        QueryWrapper<SchoolFractionInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SchoolFractionInfo.TYPE_ENUM, typeEnum().toString());
        return schoolFractionInfoService.remove(queryWrapper);
    }

    @Override
    public SchoolTypeEnum typeEnum() {
        return SchoolTypeEnum.COLLEGE_EDU_CN_SPECIALITY;
    }

    /**
     * 拼接 年份
     * @throws Exception
     */
    @Override
    public void invoke() throws Exception {

        Map<Integer, Integer> integerMap = new HashMap<>();
        //年份 , 最大页码
        integerMap.put(2022, 231);
        integerMap.put(2021, 1057);
        integerMap.put(2020, 317);
        integerMap.put(2019, 1);

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
    private void invokeAssemble(Integer year, Integer page){
        for (int i = 1; i <= page; i++) {
            String url = baseUrl + year.toString()+"/";
            if (i > 1) {
                url += "p" + i+"/";
            }
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

                schoolFractionInfo.setRemark(REMARK) ;
                schoolFractionInfo.setMethod("统招") ;
                schoolFractionInfo.setGmtCreated(new Date());
                schoolFractionInfo.setGmtModified(new Date());
                schoolFractionInfo.setTypeEnum(typeEnum().toString()) ;
                schoolFractionInfo.setUuid(LangUtils.shortUuid());

                logger.info(JSONUtil.toJsonStr(schoolFractionInfo));
                infoList.add(schoolFractionInfo);
            }
        }

        if (CollUtil.isNotEmpty(infoList)){
            batchInsertSchoolFractionInfo(infoList);
        }
    }

    @Override
    public List<SchoolFractionInfo> findSchoolFractionInfoList() {
        SchoolFractionInfoService schoolFractionInfoService = getSchoolFractionInfoService();
        QueryWrapper<SchoolFractionInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SchoolFractionInfo.TYPE_ENUM, typeEnum().toString());
        return schoolFractionInfoService.list(queryWrapper);
    }


    private String getObject(Object obj) {
        if (obj == null) {
            return null;
        }
        return String.valueOf(obj);
    }


}

