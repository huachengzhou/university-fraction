package com.ng.todo.select;


import cn.hutool.core.util.StrUtil;
import com.ng.todo.service.CommonSchoolFractionInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonSchoolFractionInfoServiceTest {
    private final Logger logger = LoggerFactory.getLogger(getClass()) ;
    @Autowired
    private CommonSchoolFractionInfoService commonSchoolFractionInfoService;


    @Test
    public void test_findDataUuidList(){
        int year = 2013;
        List<String> dataUuidList = commonSchoolFractionInfoService.findDataUuidList(year);
        logger.info(StrUtil.join(",",dataUuidList));
    }
}
