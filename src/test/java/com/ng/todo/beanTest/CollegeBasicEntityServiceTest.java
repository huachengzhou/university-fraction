package com.ng.todo.beanTest;

import com.ng.todo.service.reptile.CollegeBasicEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 高考网 专业分数线
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollegeBasicEntityServiceTest {

    @Autowired
    private CollegeBasicEntityService basicEntityService;

    @Test
    public void run(){
        try {
            basicEntityService.clear();
            basicEntityService.invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
