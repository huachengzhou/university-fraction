package com.ng.todo.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysTaskQueryDto {

    private String processClassifyKey;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String remark;

    private String taskName;

    private String applyUserCode;

    private String userCode;

    private Integer pageNo;
    private Integer pageSize;


}
