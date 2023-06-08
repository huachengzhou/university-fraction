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
 * 大学分数
 * </p>
 *
 * @author zch
 * @since 2023-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_school_fraction_info")
public class SchoolFractionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String uuid;

    /**
     * 年份(不要用数字)
     */
    private String year;

    /**
     * 分数
     */
    private String fraction;

    /**
     * 录取最高分数
     */
    private String maxScore;

    /**
     * 录取最低分数
     */
    private String minScore;

    /**
     * 省
     */
    private String province;

    /**
     * 分类
     */
    private String type;

    /**
     * 一级分类
     */
    private String primaryClassification;

    /**
     * 统招/或者单招/其他
     */
    private String method;

    /**
     * 专业(工科/理工科/文科/国家专项)
     */
    private String speciality;

    /**
     * 数据json或者html
     */
    private String sourceText;

    /**
     * 学校名称
     */
    private String school;

    /**
     * 枚举分类
     */
    private String typeEnum;

    /**
     * 说明
     */
    private String remark;

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

    public static final String YEAR = "year";

    public static final String FRACTION = "fraction";

    public static final String MAX_SCORE = "max_score";

    public static final String MIN_SCORE = "min_score";

    public static final String PROVINCE = "province";

    public static final String TYPE = "type";

    public static final String PRIMARY_CLASSIFICATION = "primary_classification";

    public static final String METHOD = "method";

    public static final String SPECIALITY = "speciality";

    public static final String SOURCE_TEXT = "source_text";

    public static final String SCHOOL = "school";

    public static final String TYPE_ENUM = "type_enum";

    public static final String REMARK = "remark";

    public static final String GMT_CREATED = "gmt_created";

    public static final String GMT_MODIFIED = "gmt_modified";

}
