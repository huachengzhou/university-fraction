package com.ng.todo.service;

import com.ng.todo.entity.BaseArea;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 省市区表 服务类
 * </p>
 *
 * @author zch
 * @since 2023-06-08
 */
public interface BaseAreaService extends IService<BaseArea> {
    /**
     * 获取省一级
     * @return
     */
    public List<BaseArea> getProvinceList() ;

    /**
     * 获取省一级名称
     * @return
     */
    public List<String> getProvinceNameList();





}
