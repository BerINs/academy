package io.peaches.academy.service.edu.controller.api;

import io.peaches.academy.common.base.result.R;
import io.peaches.academy.service.edu.entity.Course;
import io.peaches.academy.service.edu.entity.vo.ChapterVO;
import io.peaches.academy.service.edu.entity.vo.WebCourseQueryVO;
import io.peaches.academy.service.edu.entity.vo.WebCourseVO;
import io.peaches.academy.service.edu.service.ChapterService;
import io.peaches.academy.service.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api(tags="课程")
@RestController
@RequestMapping("/api/edu/course")
public class ApiCourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @ApiOperation("课程列表")
    @GetMapping("list")
    public R pageList(
            @ApiParam(value = "查询对象", required = true)
                    WebCourseQueryVO webCourseQueryVo){

        List<Course> courseList = courseService.webSelectList(webCourseQueryVo);

        return R.ok().data("courseList", courseList);
    }

    @ApiOperation("根据id查询课程")
    @GetMapping("get/{courseId}")
    public R getById(
            @ApiParam(value = "课程id", required = true)
            @PathVariable String courseId){

        //查询课程信息和讲师信息
        WebCourseVO webCourseVo = courseService.selectWebCourseVoById(courseId);

        //查询当前课程的嵌套章节和课时信息
        List<ChapterVO> chapterVoList = chapterService.nestedList(courseId);

        return R.ok().data("course", webCourseVo).data("chapterVoList", chapterVoList);
    }
//
//     @ApiOperation("根据课程id查询课程信息")
//     @GetMapping("inner/get-course-dto/{courseId}")
//     public CourseDto getCourseDtoById(
//             @ApiParam(value = "课程ID", required = true)
//             @PathVariable String courseId){
//         CourseDto courseDto = courseService.getCourseDtoById(courseId);
//         return courseDto;
//     }
//
//     @ApiOperation("根据课程id更改销售量")
//     @GetMapping("inner/update-buy-count/{id}")
//     public R updateBuyCountById(
//             @ApiParam(value = "课程id", required = true)
//             @PathVariable String id){
//         courseService.updateBuyCountById(id);
//         return R.ok();
//     }
}
