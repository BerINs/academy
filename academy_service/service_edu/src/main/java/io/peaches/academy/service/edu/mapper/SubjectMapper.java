package io.peaches.academy.service.edu.mapper;

import io.peaches.academy.service.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {

}
