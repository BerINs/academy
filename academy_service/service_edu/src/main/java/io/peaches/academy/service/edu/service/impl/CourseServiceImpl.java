package io.peaches.academy.service.edu.service.impl;

import io.peaches.academy.service.edu.entity.Course;
import io.peaches.academy.service.edu.mapper.CourseMapper;
import io.peaches.academy.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
