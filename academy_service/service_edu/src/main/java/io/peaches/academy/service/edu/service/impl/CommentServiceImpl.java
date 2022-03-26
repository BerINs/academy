package io.peaches.academy.service.edu.service.impl;

import io.peaches.academy.service.edu.entity.Comment;
import io.peaches.academy.service.edu.mapper.CommentMapper;
import io.peaches.academy.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
