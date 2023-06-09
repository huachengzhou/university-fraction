package com.ng.todo.beanTest;


import com.ng.todo.service.reptile.CollegeThreeBasicEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 高考网 高校分数线
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollegeThreeBasicEntityServiceTest {

    @Autowired
    private CollegeThreeBasicEntityService basicEntityService;


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
