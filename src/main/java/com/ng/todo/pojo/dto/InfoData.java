package com.ng.todo.pojo.dto;

import com.ng.todo.common.enums.ReptileTypeEnum;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

@Data
public class InfoData {

    private ReptileTypeEnum typeEnum;

    private Object value;

    private String html;

    private String url;

    private RequestMethod requestMethod;
}
