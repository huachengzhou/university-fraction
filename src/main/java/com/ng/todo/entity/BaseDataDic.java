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
 * 数据字典
 * </p>
 *
 * @author zch
 * @since 2023-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_base_data_dic")
public class BaseDataDic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String uuid;

    /**
     * 父级uuid
     */
    private String pidDicUuid;

    /**
     * 名称
     */
    private String name;

    /**
     * 使用该数据的字段名称
     */
    private String fieldName;

    /**
     * 助记码
     */
    private String mnemonicCode;

    /**
     * 排序
     */
    private Integer sorting;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否自定义
     */
    private Boolean bisCustom;

    /**
     * 是否树形结构
     */
    private Boolean bisTree;

    /**
     * 是否删除
     */
    private Boolean bisDelete;

    /**
     * 顶级节点可设置
     */
    private Boolean bisAddible;

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

    public static final String UUID = "uuid";

    public static final String PID_DIC_UUID = "pid_dic_uuid";

    public static final String NAME = "name";

    public static final String FIELD_NAME = "field_name";

    public static final String MNEMONIC_CODE = "mnemonic_code";

    public static final String SORTING = "sorting";

    public static final String REMARK = "remark";

    public static final String BIS_CUSTOM = "bis_custom";

    public static final String BIS_TREE = "bis_tree";

    public static final String BIS_DELETE = "bis_delete";

    public static final String BIS_ADDIBLE = "bis_addible";

    public static final String CREATOR = "creator";

    public static final String GMT_CREATED = "gmt_created";

    public static final String GMT_MODIFIED = "gmt_modified";

}
