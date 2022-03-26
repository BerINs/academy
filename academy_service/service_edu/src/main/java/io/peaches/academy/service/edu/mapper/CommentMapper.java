package io.peaches.academy.service.edu.mapper;

import io.peaches.academy.service.edu.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
