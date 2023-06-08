package com.ng.todo.common.enums;

/**
 * @author zch
 */
public enum ReptileTypeEnum {
    WebClient("动态类型"),HttpClient("apache"),HttpReqest("普通http请求")
    ;

    ReptileTypeEnum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
