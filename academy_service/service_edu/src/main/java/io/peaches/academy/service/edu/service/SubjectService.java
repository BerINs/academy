package io.peaches.academy.service.edu.service;

import io.peaches.academy.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import io.peaches.academy.service.edu.entity.vo.SubjectVO;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
public interface SubjectService extends IService<Subject> {

    void batchImport(InputStream inputStream);

    List<SubjectVO> nestedList();
}
