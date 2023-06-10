package com.ng.todo.beanTest;


import com.ng.todo.service.impl.CommonSchoolFractionInfoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonSchoolFractionInfoServiceTest {

    @Autowired
    private CommonSchoolFractionInfoServiceImpl commonSchoolFractionInfoService;

    @Test
    public void run(){
        try {
            commonSchoolFractionInfoService.invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
