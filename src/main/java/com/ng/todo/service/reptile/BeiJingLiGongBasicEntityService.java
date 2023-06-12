package com.ng.todo.service.reptile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.ng.todo.common.enums.ReptileTypeEnum;
import com.ng.todo.common.enums.SchoolTypeEnum;
import com.ng.todo.common.utils.LangUtils;
import com.ng.todo.common.utils.MyHttpUtils;
import com.ng.todo.entity.SchoolFractionInfo;
import com.ng.todo.pojo.dto.InfoData;
import com.ng.todo.pojo.dto.MyHttpParams;
import com.ng.todo.service.BaseAreaService;
import com.ng.todo.service.BasicEntityAbstract;
import com.ng.todo.service.SchoolFractionInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 北京理工大学  爬虫
 */
@Service
public class BeiJingLiGongBasicEntityService extends BasicEntityAbstract {
    public static final RequestMethod METHOD = RequestMethod.POST;
    public static final String SSZYGRADE_LIST = "sszygradeList";
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
        return SchoolTypeEnum.ADMISSION_BIT_EDU_CN;
    }

    @Override
    public void invoke() throws Exception {
        BaseAreaService baseAreaService = getBaseAreaService();
        List<String> provinceNameList = baseAreaService.getProvinceNameList();
        if (CollUtil.isEmpty(provinceNameList)) {
            throw new Exception("省一级没有数据");
        }
        provinceNameList = provinceNameList.stream().map(s -> {
            String all = StrUtil.removeAll(s, "省");
            all = StrUtil.removeAll(all, "市");
            List<String> stringList = Lists.newArrayList("内蒙古", "广西", "西藏", "宁夏", "新疆");
            String base = null;
            for (String str : stringList) {
                if (StrUtil.contains(s, str)) {
                    base = str;
                }
            }
            if (StrUtil.isNotBlank(base)) {
                return base;
            }
            return all;
        }).collect(Collectors.toList());
        List<String> klmcList = Lists.newArrayList("理工", "文史", "艺术类", "综合改革", "物理类", "历史类");
        List<String> zsnfList = Lists.newArrayList("2018", "2019", "2020", "2021", "2022");
        List<SchoolFractionInfo> infoList = new ArrayList<>();
        MyHttpParams myHttpParams = new MyHttpParams(ReptileTypeEnum.HttpReqest, "https://admission.bit.edu.cn/f/ajax_lnfs", METHOD);
        Map<String, Object> paramMap = new HashMap<>();
        for (String area : provinceNameList) {
            logger.info("area:" + area + " ==> start ");
            for (String klmc : klmcList) {
                logger.info("klmc:" + klmc + " ==> start ");
                for (String zsnf : zsnfList) {
                    logger.info("zsnf:" + zsnf + " ==> start ");
                    paramMap.put("ssmc", area);
                    paramMap.put("zsnf", zsnf);
                    paramMap.put("klmc", klmc);
                    myHttpParams.setParamMap(paramMap);
                    InfoData infoData = null;
                    try {
                        infoData = MyHttpUtils.getHtml(myHttpParams);
                    } catch (Exception e) {
                        paramMap.clear();
                        continue;
                    }
                    Object value = infoData.getValue();
                    Map resultMap = (Map) value;
                    Map data = (Map) resultMap.get("data");
                    List<Map> sszygradeList = (List<Map>) data.get(SSZYGRADE_LIST);
                    if (CollUtil.isNotEmpty(sszygradeList)) {
                        Iterator<Map> iterator = sszygradeList.iterator();
                        while (iterator.hasNext()) {
                            Map map = iterator.next();
                            SchoolFractionInfo infoObj = new SchoolFractionInfo();
                            infoObj.setUuid(LangUtils.shortUuid());
                            FinalField finalField = new FinalField().invoke();
                            String ssmc = finalField.getSsmc();
                            String minScore = finalField.getMinScore();

                            infoObj.setTypeEnum(typeEnum().toString());
                            infoObj.setYear(zsnf);
                            infoObj.setBatch("本科批") ;
                            infoObj.setSchool(typeEnum().getName());
                            infoObj.setProvince(getObject(map.get(ssmc)));
                            infoObj.setFraction(getObject(map.get(minScore)));
                            infoObj.setMinScore(getObject(map.get(minScore)));
                            infoObj.setType(getObject(map.get(finalField.getKlmc())));
                            infoObj.setSpeciality(getObject(map.get(finalField.getZymc())));
                            infoObj.setMethod(getObject(map.get(finalField.getZslx())));
                            infoObj.setGmtCreated(new Date());
                            infoObj.setGmtModified(new Date());
                            logger.info(JSONUtil.toJsonStr(infoObj));
                            infoList.add(infoObj);
                        }
                    }
                    paramMap.clear();
                    logger.info("sleep 100");
                    Thread.sleep(100);
                }
                if (CollUtil.isNotEmpty(infoList)) {
                    batchInsertSchoolFractionInfo(infoList);
                    infoList.clear();
                }
            }
            logger.info(area + " ==> end ");
            Thread.sleep(100);
            logger.info("sleep 100");
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

    private class FinalField {
        private String ssmc;
        private String minScore;
        private String klmc;
        private String zymc;
        private String zslx;

        public String getSsmc() {
            return ssmc;
        }

        public String getMinScore() {
            return minScore;
        }

        public String getKlmc() {
            return klmc;
        }

        public String getZymc() {
            return zymc;
        }

        public String getZslx() {
            return zslx;
        }

        public FinalField invoke() {
            ssmc = "ssmc";
            minScore = "minScore";
            klmc = "klmc";
            zymc = "zymc";
            zslx = "zslx";
            return this;
        }
    }
}

