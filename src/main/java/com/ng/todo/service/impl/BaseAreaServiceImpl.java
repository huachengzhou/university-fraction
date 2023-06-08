package com.ng.todo.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ng.todo.entity.BaseArea;
import com.ng.todo.mapper.BaseAreaMapper;
import com.ng.todo.service.BaseAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 省市区表 服务实现类
 * </p>
 *
 * @author zch
 * @since 2023-06-08
 */
@Service
public class BaseAreaServiceImpl extends ServiceImpl<BaseAreaMapper, BaseArea> implements BaseAreaService {

    @Override
    public List<BaseArea> getProvinceList() {
        QueryWrapper<BaseArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseArea.PID_CODE,"0") ;
        return list(queryWrapper);
    }

    @Override
    public List<String> getProvinceNameList() {
        List<BaseArea> list = getProvinceList();
        if (CollUtil.isNotEmpty(list)){
            return list.stream().map(obj -> obj.getName()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


}
