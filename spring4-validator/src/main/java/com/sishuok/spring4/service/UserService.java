package com.sishuok.spring4.service;

import com.sishuok.spring4.entity.UserModel;
import com.sishuok.spring4.validator.CrossParameter;
import com.sishuok.spring4.validator.CrossParameterScriptAssert;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-12-15
 * <p>Version: 1.0
 */

@Validated      //① 告诉MethodValidationPostProcessor此Bean需要开启方法级别验证支持
@Service
public class UserService {

    @NotNull
    public UserModel get0(@NotNull @Min(value = 1, message = "{can.not.less}") Integer uuid) { //②声明前置条件/后置条件
        //获取 User Model
        UserModel user = new UserModel(); //此处应该从数据库获取
        if(uuid > 100) {//方便后置添加的判断（此处假设传入的uuid>100 则返回null）
            return null;
        }
        return user;
    }

    @CrossParameterScriptAssert(script = "args[0] == args[1]", lang = "javascript", alias = "args", message = "{password.confirmation.error}")
    public void changePassword(String password, String confirmation) {

    }

}
