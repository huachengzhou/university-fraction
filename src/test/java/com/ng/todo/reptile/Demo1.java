package com.ng.todo.reptile;

import com.ng.todo.common.enums.ReptileTypeEnum;
import com.ng.todo.common.utils.MyHttpUtils;
import com.ng.todo.pojo.dto.InfoData;
import com.ng.todo.pojo.dto.MyHttpParams;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

public class Demo1 {

    //北京理工大学

    public static final String URL = "https://admission.bit.edu.cn/f/ajax_lnfs?ts=1686143845450";
    public static final ReptileTypeEnum TYPE_ENUM = ReptileTypeEnum.HttpClient;
    public static final RequestMethod METHOD = RequestMethod.POST;

    @Test
    public void test1() {

        InfoData infoData = MyHttpUtils.getHtml(new MyHttpParams(TYPE_ENUM, URL, METHOD));
        if (infoData != null) {

        }

    }

    @Test
    public void test2() {
        MyHttpParams myHttpParams = new MyHttpParams(ReptileTypeEnum.HttpReqest, "https://admission.bit.edu.cn/f/ajax_lnfs", METHOD);
        Map<String, Object> paramMap = new HashMap<>() ;
        paramMap.put("ssmc","内蒙古") ;
        paramMap.put("zsnf","2022") ;
        paramMap.put("klmc","理工") ;
        myHttpParams.setParamMap(paramMap);
        InfoData infoData = MyHttpUtils.getHtml(myHttpParams);
        if (infoData != null) {

        }

    }

}
