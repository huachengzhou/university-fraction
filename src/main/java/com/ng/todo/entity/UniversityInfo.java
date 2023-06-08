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
 * 大学信息
 * </p>
 *
 * @author zch
 * @since 2023-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_university_info")
public class UniversityInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String uuid;

    /**
     * 学校名称
     */
    private String name;

    /**
     * 院校类型
     */
    private String type;

    /**
     * tag
     */
    private String tag;

    /**
     * 院校隶属
     */
    private String subordinate;

    /**
     * 所在地区
     */
    private String location;

    /**
     * 官网
     */
    private String officialWebsite;

    /**
     * 特殊招生
     */
    private String specialAdmission;

    /**
     * 学校排名
     */
    private String ranking;

    /**
     * 星级
     */
    private String starRating;

    /**
     * 总分
     */
    private String totalScore;

    /**
     * 科学研究(分数)
     */
    private String scientificResearchScore;

    /**
     * 人才培养(分数)
     */
    private String personnelTrainingScore;

    /**
     * 综合声誉(分数)
     */
    private String comprehensiveReputation;

    /**
     * 办学层次
     */
    private String educationalLevel;

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

    public static final String NAME = "name";

    public static final String TYPE = "type";

    public static final String TAG = "tag";

    public static final String SUBORDINATE = "subordinate";

    public static final String LOCATION = "location";

    public static final String OFFICIAL_WEBSITE = "official_website";

    public static final String SPECIAL_ADMISSION = "special_admission";

    public static final String RANKING = "ranking";

    public static final String STAR_RATING = "star_rating";

    public static final String TOTAL_SCORE = "total_score";

    public static final String SCIENTIFIC_RESEARCH_SCORE = "scientific_research_score";

    public static final String PERSONNEL_TRAINING_SCORE = "personnel_training_score";

    public static final String COMPREHENSIVE_REPUTATION = "comprehensive_reputation";

    public static final String EDUCATIONAL_LEVEL = "educational_level";

    public static final String REMARK = "remark";

    public static final String GMT_CREATED = "gmt_created";

    public static final String GMT_MODIFIED = "gmt_modified";

}
