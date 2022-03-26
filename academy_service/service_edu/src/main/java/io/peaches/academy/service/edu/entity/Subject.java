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
 * 课程科目
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@Getter
@Setter
@TableName("edu_subject")
@ApiModel(value = "Subject对象", description = "课程科目")
public class Subject extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("类别名称")
    @TableField("title")
    private String title;

    @ApiModelProperty("父ID")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty("排序字段")
    @TableField("sort")
    private Integer sort;


}
