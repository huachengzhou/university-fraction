package com.ng.todo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 省市区表
 * </p>
 *
 * @author zch
 * @since 2023-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_base_area")
public class BaseArea implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编码
     */
    private String code;

    /**
     * 父级编码
     */
    private String pidCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     */
    private Date gmtModified;


    public static final String ID = "id";

    public static final String CODE = "code";

    public static final String PID_CODE = "pid_code";

    public static final String NAME = "name";

    public static final String CREATOR = "creator";

    public static final String GMT_CREATED = "gmt_created";

    public static final String GMT_MODIFIED = "gmt_modified";

}
