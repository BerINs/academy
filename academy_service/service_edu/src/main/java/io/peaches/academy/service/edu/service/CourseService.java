package io.peaches.academy.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.peaches.academy.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import io.peaches.academy.service.edu.entity.form.CourseInfoForm;
import io.peaches.academy.service.edu.entity.vo.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfoByIf(CourseInfoForm courseInfoForm);

    IPage<CourseVO> selectPage(Long page, Long limit, CourseQueryVO courseQueryVo);

    boolean removeCoverById(String id);

    boolean removeCourseById(String id);

    CoursePublishVO getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    List<Course> webSelectList(WebCourseQueryVO webCourseQueryVo);

    WebCourseVO selectWebCourseVoById(String id);
}
