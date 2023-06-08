package com.ng.todo;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ng.todo.entity.BaseDataDic;
import com.ng.todo.service.BaseDataDicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUserServiceTest {

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Test
    public void run() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+baseDataDicService);
        List<BaseDataDic> baseDataDicList = baseDataDicService.list(new QueryWrapper<>());
        if (CollUtil.isNotEmpty(baseDataDicList)){

        }
    }
}
