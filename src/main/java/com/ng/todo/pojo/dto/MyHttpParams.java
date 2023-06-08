package com.ng.todo.pojo.dto;

import com.ng.todo.common.enums.ReptileTypeEnum;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

public class MyHttpParams {
    private final ReptileTypeEnum typeEnum;
    private final String url;
    private final RequestMethod requestMethod;
    private  Map<String, Object> paramMap ;

    public MyHttpParams(ReptileTypeEnum typeEnum, String url, RequestMethod requestMethod) {
        this.typeEnum = typeEnum;
        this.url = url;
        this.requestMethod = requestMethod;
    }

    public ReptileTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public String getUrl() {
        return url;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
}
