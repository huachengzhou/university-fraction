package com.ng.todo.common.enums;

/**
 * 学校枚举
 * @author zch
 **/
public enum SchoolTypeEnum {

    ADMISSION_BIT_EDU_CN("北京理工大学"),
   ;

    SchoolTypeEnum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

}
