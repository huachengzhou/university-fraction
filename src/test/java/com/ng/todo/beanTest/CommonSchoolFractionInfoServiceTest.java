package com.ng.todo.beanTest;


import com.ng.todo.service.reptile.CommonOtherBasicEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 其它某个网站
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonSchoolFractionInfoServiceTest {

    @Autowired
    private CommonOtherBasicEntityService commonOtherBasicEntityService;

    @Test
    public void run(){
        try {
            commonOtherBasicEntityService.invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
