package com.ng.todo.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ng.todo.entity.CommonSchoolFractionInfo;
import com.ng.todo.mapper.CommonSchoolFractionInfoMapper;
import com.ng.todo.service.CommonSchoolFractionInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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


    /**
     * 根据年份获取信息uuid
     *
     * @param year
     * @return
     */
    @Override
    public List<String> findDataUuidList(Integer year) {
        QueryWrapper<CommonSchoolFractionInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("uuid");
        queryWrapper.eq(CommonSchoolFractionInfo.YEAR,year);
        List<Map<String, Object>> mapList = listMaps(queryWrapper);
        if (CollUtil.isNotEmpty(mapList)){

        }
        return null;
    }
}
