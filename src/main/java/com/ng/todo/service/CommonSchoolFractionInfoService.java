package com.ng.todo.service;

import com.ng.todo.entity.CommonSchoolFractionInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 大学高考分数(普通) 服务类
 * </p>
 *
 * @author zch
 * @since 2023-06-10
 */
public interface CommonSchoolFractionInfoService extends IService<CommonSchoolFractionInfo> {
    /**
     * 根据年份获取信息uuid
     * @param year
     * @return
     */
    List<String> findDataUuidList(Integer year);
}
