package io.peaches.academy.service.edu.service;

import io.peaches.academy.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import io.peaches.academy.service.edu.entity.vo.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVO> nestedList(String courseId);
}
