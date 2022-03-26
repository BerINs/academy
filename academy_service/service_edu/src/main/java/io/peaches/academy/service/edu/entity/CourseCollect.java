package io.peaches.academy.service.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.peaches.academy.service.base.model.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程收藏
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@Getter
@Setter
@TableName("edu_course_collect")
@ApiModel(value = "CourseCollect对象", description = "课程收藏")
public class CourseCollect extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程讲师ID")
    @TableField("course_id")
    private String courseId;

    @ApiModelProperty("课程专业ID")
    @TableField("member_id")
    private String memberId;


}
