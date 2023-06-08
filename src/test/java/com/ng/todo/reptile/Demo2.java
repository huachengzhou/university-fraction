package com.ng.todo.reptile;

import com.ng.todo.common.enums.ReptileTypeEnum;
import com.ng.todo.common.utils.MyHttpUtils;
import com.ng.todo.pojo.dto.InfoData;
import com.ng.todo.pojo.dto.MyHttpParams;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;

public class Demo2 {
    //https://mdaxue.911cha.com/s_sichuan.html


    public static final String URL = "https://mdaxue.911cha.com/s_sichuan.html";
    public static final ReptileTypeEnum TYPE_ENUM = ReptileTypeEnum.WebClient;
    public static final RequestMethod METHOD = RequestMethod.GET;

    @Test
    public void test1() {

        InfoData infoData = MyHttpUtils.getHtml(new MyHttpParams(TYPE_ENUM, URL, METHOD));
        if (infoData != null) {

        }

    }

    public static String getTempValue(){
        String text = "" ;

        return text;
    }

}
