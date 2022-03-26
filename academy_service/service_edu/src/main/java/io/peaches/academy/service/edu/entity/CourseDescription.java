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
 * 课程简介
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@Getter
@Setter
@TableName("edu_course_description")
@ApiModel(value = "CourseDescription对象", description = "课程简介")
public class CourseDescription extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程简介")
    @TableField("description")
    private String description;


}
