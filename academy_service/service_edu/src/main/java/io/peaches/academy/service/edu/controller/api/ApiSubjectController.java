package io.peaches.academy.service.edu.controller.api;

import io.peaches.academy.common.base.result.R;
import io.peaches.academy.service.edu.entity.vo.SubjectVO;
import io.peaches.academy.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@Api(tags="课程分类")
@RestController
@RequestMapping("/api/edu/subject")
public class ApiSubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("嵌套数据列表")
    @GetMapping("nested-list")
    public R nestedList(){
        List<SubjectVO> subjectVoList = subjectService.nestedList();
        return R.ok().data("items", subjectVoList);
    }
}
