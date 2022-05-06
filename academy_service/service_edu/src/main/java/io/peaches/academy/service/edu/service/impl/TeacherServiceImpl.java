package io.peaches.academy.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import io.peaches.academy.common.base.result.R;
import io.peaches.academy.service.edu.entity.Course;
import io.peaches.academy.service.edu.entity.Teacher;
import io.peaches.academy.service.edu.entity.vo.TeacherQueryVO;
import io.peaches.academy.service.edu.feign.OssFileService;
import io.peaches.academy.service.edu.mapper.CourseMapper;
import io.peaches.academy.service.edu.mapper.TeacherMapper;
import io.peaches.academy.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private OssFileService ossFileService;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public IPage<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVO teacherQueryVO) {

        //显示分页查询列表
//        1、排序
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

//        2、分页查询
        if (teacherQueryVO == null) {
            return baseMapper.selectPage(pageParam, queryWrapper);
        }

//        3、条件查询
        String name = teacherQueryVO.getName();
        Integer level = teacherQueryVO.getLevel();
        String joinDateBegin = teacherQueryVO.getJoinDateBegin();
        String joinDateEnd = teacherQueryVO.getJoinDateEnd();

        if (!StringUtils.isNullOrEmpty(name)) {
            queryWrapper.likeRight("name", name);
        }

        if (level != null) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isNullOrEmpty(joinDateBegin)) {
            queryWrapper.ge("join_date", joinDateBegin);
        }

        if (!StringUtils.isNullOrEmpty(joinDateEnd)) {
            queryWrapper.le("join_date", joinDateEnd);
        }

        return baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectNameList(String keyword) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        queryWrapper.likeRight("name", keyword);

        List<Map<String, Object>> list = baseMapper.selectMaps(queryWrapper);
        return list;
    }

    @Override
    public boolean removeAvatarById(String id) {
        // 根据id获取讲师avatar 的 url
        Teacher teacher = baseMapper.selectById(id);
        if (teacher != null) {
            String avatar = teacher.getAvatar();
            if (!avatar.isEmpty()) {
                R res = ossFileService.removeFile(avatar);
                return res.getSuccess();
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> selectTeacherInfoById(String id) {
        Teacher teacher = baseMapper.selectById(id);

        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id", id);
        List<Course> courseList = courseMapper.selectList(courseQueryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("teacher", teacher);
        map.put("courseList", courseList);

        return map;
    }
}
