package io.peaches.academy.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.peaches.academy.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import io.peaches.academy.service.edu.entity.vo.TeacherQueryVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
public interface TeacherService extends IService<Teacher> {

    IPage<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVO teacherQueryVO);

    List<Map<String, Object>> selectNameList(String keyword);

    boolean removeAvatarById(String id);

    Map<String, Object> selectTeacherInfoById(String id);

}
