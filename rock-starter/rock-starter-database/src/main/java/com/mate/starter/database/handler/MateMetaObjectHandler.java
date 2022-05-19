package com.mate.starter.database.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充时间字段
 *
 * @author Kevin
 */
@Slf4j
@Component
public class MateMetaObjectHandler implements MetaObjectHandler {


    /**
     * 新增数据执行
     *
     * @param metaObject 反射对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        // this.strictInsertFill(metaObject, "createBy", Long.class, MateContextHolder.getUserId());
        // this.strictInsertFill(metaObject, "updateBy", Long.class, MateContextHolder.getUserId());
    }


    /**
     * 更新数据执行
     *
     * @param metaObject 反射对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        // this.strictUpdateFill(metaObject, "updateBy", Long.class, MateContextHolder.getUserId());
    }
}
