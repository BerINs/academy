package io.peaches.academy.service.base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.reflections.Reflections.log;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "gmtCreate", LocalDateTime::now, LocalDateTime.class); // 起始版本 3.3.3(推荐)
        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime::now, LocalDateTime.class); // 起始版本 3.3.3(推荐)

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime::now, LocalDateTime.class); // 起始版本 3.3.3(推荐)
    }
}