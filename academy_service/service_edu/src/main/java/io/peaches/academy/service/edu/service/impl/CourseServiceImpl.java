package io.peaches.academy.service.edu.service.impl;

import io.peaches.academy.service.edu.entity.Course;
import io.peaches.academy.service.edu.entity.CourseDescription;
import io.peaches.academy.service.edu.entity.form.CourseInfoForm;
import io.peaches.academy.service.edu.mapper.CourseDescriptionMapper;
import io.peaches.academy.service.edu.mapper.CourseMapper;
import io.peaches.academy.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        // 保存Course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        course.setStatus(Course.COURSE_DRAFT);
        baseMapper.insert(course);

        // 保存CourseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.insert(courseDescription);

        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseInfoById(String id) {
        //根据id获取Course
        Course course = baseMapper.selectById(id);
        if(course == null){
            return null;
        }

        //根据id获取CourseDescription
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);

        //组装成CourseInfoForm
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfoByIf(CourseInfoForm courseInfoForm) {

        //更新Course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        baseMapper.updateById(course);

        //更新CourseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(courseInfoForm.getId());
        courseDescriptionMapper.updateById(courseDescription);
    }
}
