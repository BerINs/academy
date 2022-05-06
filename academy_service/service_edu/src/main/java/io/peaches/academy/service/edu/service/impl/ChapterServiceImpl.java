package io.peaches.academy.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.peaches.academy.service.edu.entity.Chapter;
import io.peaches.academy.service.edu.entity.Video;
import io.peaches.academy.service.edu.entity.vo.ChapterVO;
import io.peaches.academy.service.edu.entity.vo.VideoVO;
import io.peaches.academy.service.edu.mapper.ChapterMapper;
import io.peaches.academy.service.edu.mapper.VideoMapper;
import io.peaches.academy.service.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoMapper videoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeChapterById(String id) {

        //根据courseId删除Video(课时)
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", id);
        videoMapper.delete(videoQueryWrapper);

        //删除章节
        return this.removeById(id);
    }

    @Override
    public List<ChapterVO> nestedList(String courseId) {


        //方案1：效率低  1+n个sql
        //通过course_id获取章节列表信息：List<Chapter>  sql
        //遍历List<Chapter>{ n
        //    通过chapter_id查询List<Video> sql
        // }

        //方案2：效率高 1+1个sql
        //通过course_id获取章节列表信息：List<Chapter>  sql
        //通过course_id查询List<Video> sql

        //获取章节信息列表
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        chapterQueryWrapper.orderByAsc("sort", "id");
        List<Chapter> chapterList = baseMapper.selectList(chapterQueryWrapper);

        //获取课时信息列表
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        videoQueryWrapper.orderByAsc("sort", "id");
        List<Video> videoList = videoMapper.selectList(videoQueryWrapper);


        //组装章节列表：List<ChapterVO>
        List<ChapterVO> chapterVoList = new ArrayList<>();
//        for (Chapter chapter : chapterList) {
//
//        }
        for (int i = 0; i < chapterList.size(); i++) {
            Chapter chapter = chapterList.get(i);
            ChapterVO chapterVo = new ChapterVO();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoList.add(chapterVo);

            //组装章节列表：List<ChapterVO>
            List<VideoVO> videoVoList = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                Video video = videoList.get(j);
                if(chapter.getId().equals(video.getChapterId())){

                    VideoVO videoVo = new VideoVO();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }

        return chapterVoList;
    }
}
