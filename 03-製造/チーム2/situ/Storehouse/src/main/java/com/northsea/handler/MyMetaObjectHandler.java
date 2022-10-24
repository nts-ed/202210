package com.northsea.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // default <T, E extends T> MetaObjectHandler strictInsertFill(MetaObject metaObject, String fieldName, Supplier<E> fieldVal, Class<T> fieldType) {
        //    return this.strictInsertFill(this.findTableInfo(metaObject), metaObject, Collections.singletonList(StrictFill.of(fieldName, fieldVal, fieldType)));
        // }

        // this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        // 或者
        this.strictInsertFill(metaObject, "createDate", Date::new, Date.class); // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "updateDate", Date::new, Date.class); // 起始版本 3.3.3(推荐)
        // 或者
        // this.fillStrategy(metaObject, "createTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)

        // this.setFieldValByName("createTime",new Date(),metaObject);  //可以使用
        // this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        //this.strictUpdateFill(metaObject, "updateDate", Date::new, Date.class);
        this.setFieldValByName("updateDate", new Date(), metaObject);
        //this.setFieldValByName("updateTime",new Date(),metaObject);
    }


}