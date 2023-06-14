package com.ng.todo.beanTest;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ng.todo.common.enums.SchoolTypeEnum;
import com.ng.todo.entity.UniversityInfo;
import com.ng.todo.service.UniversityInfoService;
import com.ng.todo.service.reptile.UniversityBasicEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 高考网  学校信息
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UniversityBasicEntityServiceTest {
    @Autowired
    private UniversityInfoService universityInfoService;
    @Autowired
    private UniversityBasicEntityService basicEntityService;

    @Test
    public void run() {
        try {
            basicEntityService.clear();
            basicEntityService.invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testA() {
        QueryWrapper<UniversityInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UniversityInfo.TYPE_ENUM, SchoolTypeEnum.COLLEGE_EDU_CN.toString());
        queryWrapper.groupBy(UniversityInfo.NAME) ;
        queryWrapper.isNotNull(UniversityInfo.S_ID);
        List<UniversityInfo> infoList = universityInfoService.list(queryWrapper);
        if (CollUtil.isNotEmpty(infoList)){

        }

    }
}
