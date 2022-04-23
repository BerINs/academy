package io.peaches.academy.service.edu.controller.admin;


import io.peaches.academy.common.base.result.R;
import io.peaches.academy.common.base.result.ResultCodeEnum;
import io.peaches.academy.common.base.util.ExceptionUtils;
import io.peaches.academy.service.base.exception.AcademyException;
import io.peaches.academy.service.edu.entity.vo.SubjectVO;
import io.peaches.academy.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@CrossOrigin
@Api(tags = "课程分类管理")
@RestController
@RequestMapping("/admin/edu/subject")
@Slf4j
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("import")
    public R batchImport(@RequestParam("file") MultipartFile file) {

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            subjectService.batchImport(inputStream);
            return R.ok().message("批量导入成功");
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new AcademyException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    @ApiOperation("嵌套数据列表")
    @GetMapping("nested-list")
    public R nestedList() {
        List<SubjectVO> subjectVoList = subjectService.nestedList();
        return R.ok().data("items", subjectVoList);
    }
}

