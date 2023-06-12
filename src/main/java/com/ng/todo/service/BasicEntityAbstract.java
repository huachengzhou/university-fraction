package com.ng.todo.service;

import cn.hutool.core.util.StrUtil;
import com.ng.todo.common.enums.SchoolTypeEnum;
import com.ng.todo.entity.SchoolFractionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @apiNote zch
 */
@Service
public abstract class BasicEntityAbstract {

    @Autowired
    private SchoolFractionInfoService schoolFractionInfoService;
    @Autowired
    private BaseAreaService baseAreaService;

    public BaseAreaService getBaseAreaService() {
        return baseAreaService;
    }

    public SchoolFractionInfoService getSchoolFractionInfoService() {
        return schoolFractionInfoService;
    }

    /**
     * 批量插入
     */
    public void batchInsertSchoolFractionInfo(List<SchoolFractionInfo> list) {
        schoolFractionInfoService.saveBatch(list, 50);
    }

    /**
     * 清楚当前学校数据
     */
    public abstract boolean clear();

    /**
     * 学校类型
     *
     * @return
     */
    public abstract SchoolTypeEnum typeEnum();

    /**
     * 执行爬虫
     */
    public abstract void invoke() throws Exception;

    /**
     * 校验数据是否完整
     *
     * @return
     */
    public boolean check(SchoolFractionInfo entity) {
        if (entity == null) {
            return false;
        }
        if (StrUtil.isBlank(entity.getSchool())) {
            return false;
        }
        if (StrUtil.isBlank(entity.getYear())) {
            return false;
        }
        if (StrUtil.isBlank(entity.getFraction())) {
            return false;
        }
        if (StrUtil.isBlank(entity.getProvince())) {
            return false;
        }
        if (StrUtil.isBlank(entity.getSpeciality())) {
            return false;
        }
        return true;
    }

    /**
     * 校验数据是否重复
     *
     * @return
     */
    public boolean distinct(SchoolFractionInfo entity) {
        return false;
    }


    /**
     * 获取该学校数据
     *
     * @return
     */
    public abstract List<SchoolFractionInfo> findSchoolFractionInfoList();

}
