package io.peaches.academy.service.oss.controller;

import io.peaches.academy.common.base.result.R;
import io.peaches.academy.common.base.result.ResultCodeEnum;
import io.peaches.academy.common.base.util.ExceptionUtils;
import io.peaches.academy.service.base.exception.AcademyException;
import io.peaches.academy.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Api(tags = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public R upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("module") String module
            ) throws IOException {


        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String uploadUrl = fileService.upload(inputStream, module, originalFilename);

            return R.ok().message("文件上传成功").data("url", uploadUrl);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw  new AcademyException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    @ApiOperation(value = "文件删除")
    @DeleteMapping("remove")
    public R removeFile(@RequestBody String url) {
        fileService.removeFile(url);
        return R.ok().message("文件删除成功");
    }


    @ApiOperation(value = "测试")
    @GetMapping("test")
    public R test() {
        log.info("oss test被调用");
        return R.ok();
    }
}
