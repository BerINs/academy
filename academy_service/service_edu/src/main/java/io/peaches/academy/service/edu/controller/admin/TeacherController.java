package io.peaches.academy.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.peaches.academy.common.base.result.R;
import io.peaches.academy.service.edu.entity.Teacher;
import io.peaches.academy.service.edu.entity.vo.TeacherQueryVO;
import io.peaches.academy.service.edu.feign.OssFileService;
import io.peaches.academy.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-02-09,.,
 */
@CrossOrigin
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private OssFileService ossFileService;

    @GetMapping("list")
    public R listAll() {
        List<Teacher> list = teacherService.list();
        return R.ok().data("items", list);
    }

    @DeleteMapping("remove/{id}")
    public R removeById(@PathVariable String id) {
        teacherService.removeAvatarById(id);
        boolean res = teacherService.removeById(id);
        if (res) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("数据不存在");
        }
    }


    //    分页查询
    @GetMapping("/list/{page}/{limit}")
    public R listPage(@PathVariable Long page,
                      @PathVariable Long limit,
                      TeacherQueryVO teacherQueryVO) {
        Page<Teacher> pageParam = new Page<>(page, limit);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam, teacherQueryVO);
        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    //    新增讲师
    @PostMapping("add")
    public R add(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return R.ok().message("新增成功");
    }

    //    更新讲师
    @PutMapping("update")
    public R updateById(@RequestBody Teacher teacher) {
        boolean res = teacherService.updateById(teacher);
        if (res) {
            return R.ok().message("更新成功");
        } else {
            return R.error().message("更新失败");
        }
    }

    //    根据id或许讲师信息
    @GetMapping("get/{id}")
    public R getById(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher != null) {
            return R.ok().data("item", teacher);
        } else {
            return R.error().message("数据不存在");
        }
    }

    @DeleteMapping("batch-remove")
    public R removeByIds(@RequestBody List<String> idList) {
        boolean res = teacherService.removeByIds(idList);
        if (res) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据关键字查询讲师名列表")
    @GetMapping("list/name/{key}")
    public R selectNameListByKey(@PathVariable String key) {
        List<Map<String, Object>> nameList = teacherService.selectNameList(key);
        return R.ok().data("nameList", nameList);
    }

    @GetMapping("test")
    public R test() {
        ossFileService.test();
        return R.ok();
    }

    @GetMapping("/message1")
    public String message1() {
        return "message1";
    }

    @GetMapping("/message2")
    public String message2() {
        return "message2";
    }
}

