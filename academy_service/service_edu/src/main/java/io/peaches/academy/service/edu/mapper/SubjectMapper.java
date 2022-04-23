package io.peaches.academy.service.edu.mapper;

import io.peaches.academy.service.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.peaches.academy.service.edu.entity.vo.SubjectVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    List<SubjectVO> selectNestedListByParentId(String s);
}
