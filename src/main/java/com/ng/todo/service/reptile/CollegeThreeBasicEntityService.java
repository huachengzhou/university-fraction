package com.ng.todo.service.reptile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ng.todo.common.enums.SchoolTypeEnum;
import com.ng.todo.common.utils.LangUtils;
import com.ng.todo.entity.SchoolFractionInfo;
import com.ng.todo.entity.UniversityInfo;
import com.ng.todo.service.BasicEntityAbstract;
import com.ng.todo.service.SchoolFractionInfoService;
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
 * http://college.gaokao.com/schpoint/  爬虫
 * <p>
 * 高考网 高校分数线
 */
@Service
public class CollegeThreeBasicEntityService extends BasicEntityAbstract {

    private final String baseUrl = "https://college.gaokao.com/school/tinfo";

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UniversityInfoService universityInfoService;

    @Override
    public boolean clear() {
        SchoolFractionInfoService schoolFractionInfoService = getSchoolFractionInfoService();
        QueryWrapper<SchoolFractionInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SchoolFractionInfo.TYPE_ENUM, typeEnum().toString());
        return schoolFractionInfoService.remove(queryWrapper);
    }

    @Override
    public SchoolTypeEnum typeEnum() {
        return SchoolTypeEnum.COLLEGE_EDU_CN;
    }

    /**
     * 拼接 年份
     *
     * @throws Exception
     */
    @Override
    public void invoke() throws Exception {
        List<UniversityInfo> universityInfoList = getUniversityInfoList();
        if (CollUtil.isEmpty(universityInfoList)) {
            return;
        }
        Map<String, String> stringMap = new HashMap<>(10);
        stringMap.put("理科", "1");
        stringMap.put("文科", "2");
        stringMap.put("综合", "3");
        stringMap.put("其他", "4");
        stringMap.put("艺术理", "8");
        stringMap.put("艺术文", "9");
        stringMap.put("综合改革", "10");

        Iterator<UniversityInfo> universityInfoIterator = universityInfoList.iterator();
        while (universityInfoIterator.hasNext()) {
            UniversityInfo universityInfo = universityInfoIterator.next();
            for (int i = 1; i <= 39; i++) {
                Iterator<Map.Entry<String, String>> entryIterator = stringMap.entrySet().iterator();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, String> stringEntry = entryIterator.next();
                    String str = baseUrl + "/" + universityInfo.getSId()+"/result" + "/" + i + "/" + stringEntry.getValue();

                    org.jsoup.nodes.Document document = null;
                    try {
                        document = Jsoup.parse(new URL(str), 5000);
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
                    try {
                        invokeWrite(body,stringEntry.getKey(),universityInfo);
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
            }
        }



    }




    /**
     * 具体处理数据
     *
     * @param body
     */
    private void invokeWrite(Element body,String type,UniversityInfo universityInfo) {
        if (body == null) {
            return;
        }
        Element wrapper = body.getElementById("pointbyarea");
        if (wrapper == null){
            return;
        }
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
                            schoolFractionInfo.setFraction(element.text());
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
                schoolFractionInfo.setTypeEnum(typeEnum().toString()) ;
                schoolFractionInfo.setUuid(LangUtils.shortUuid());
                schoolFractionInfo.setType(type) ;
                schoolFractionInfo.setSchool(universityInfo.getName()) ;
                schoolFractionInfo.setProvince(universityInfo.getLocation()) ;

                logger.info(JSONUtil.toJsonStr(schoolFractionInfo));
                infoList.add(schoolFractionInfo);
            }
            k++;
        }

        if (CollUtil.isNotEmpty(infoList)) {
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

    private List<UniversityInfo> getUniversityInfoList() {
        QueryWrapper<UniversityInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UniversityInfo.TYPE_ENUM, SchoolTypeEnum.COLLEGE_EDU_CN.toString());
        queryWrapper.groupBy(UniversityInfo.NAME);
        queryWrapper.isNotNull(UniversityInfo.S_ID);
        List<UniversityInfo> infoList = universityInfoService.list(queryWrapper);
        return infoList;
    }
}

