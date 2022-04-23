package io.peaches.academy.service.edu.service;

import io.peaches.academy.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import io.peaches.academy.service.edu.entity.form.CourseInfoForm;

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
}
