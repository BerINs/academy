package io.peaches.academy.service.edu.service.impl;

import io.peaches.academy.service.edu.entity.Video;
import io.peaches.academy.service.edu.mapper.VideoMapper;
import io.peaches.academy.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
