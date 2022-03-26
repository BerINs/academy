package io.peaches.academy.service.edu.controller.admin;

import io.peaches.academy.common.base.result.R;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("user")
public class loginController {

    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    public R info() {
        return R.ok()
                .data("name", "admin")
                .data("roles", "[admin]")
                .data("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
    }

    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }
}
