package io.peaches.academy.service.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.peaches.academy.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.peaches.academy.service.edu.entity.vo.CoursePublishVO;
import io.peaches.academy.service.edu.entity.vo.CourseVO;
import io.peaches.academy.service.edu.entity.vo.WebCourseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVO> selectPageByCourseQueryVO(Page<CourseVO> pageParam,
                                             @Param(Constants.WRAPPER) QueryWrapper<CourseVO> queryWrapper);

    CoursePublishVO selectCoursePublishVoById(String id);

    WebCourseVO selectWebCourseVoById(String courseId);

    int updateViewCount(Course course);
}
