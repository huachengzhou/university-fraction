package com.ng.todo.common.enums;

/**
 * 学校枚举
 * @author zch
 **/
public enum SchoolTypeEnum {

    ADMISSION_BIT_EDU_CN("北京理工大学"),

    COLLEGE_EDU_CN("http://college.gaokao.com/schpoint/"),
    COLLEGE_EDU_CN_INFO("高考网-高校分数线"),
    COLLEGE_EDU_CN_SPECIALITY("高考网-专业分数线"),
    COLLEGE_EDU_CN_LOCATION("高考网-地区批次线"),
   ;

    SchoolTypeEnum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

}
