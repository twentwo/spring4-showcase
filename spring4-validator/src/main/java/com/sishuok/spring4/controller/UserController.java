package com.sishuok.spring4.controller;

import com.sishuok.spring4.entity.*;
import com.sishuok.spring4.error.AjaxError;
import com.sishuok.spring4.group.First;
import com.sishuok.spring4.group.Second;
import com.sishuok.spring4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-12-13
 * <p>Version: 1.0
 *
 * @see <url>http://jinnianshilongnian.iteye.com/blog/1990081</url>
 */
@Controller
public class UserController {

    //----------Bean Validation 1.1(JSR349)---------
    @RequestMapping("/save0")
    public String save0(@ModelAttribute("user") @Valid User0 user, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        return "success";
    }

    //分组验证
    @RequestMapping("/save1")
    public String save1(@ModelAttribute("user") @Validated({Second.class}) User1 user, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        return "success";
    }

    //分组顺序 （@Valid和@Validated对@GroupSequence都起效）
    @RequestMapping("/save2")
    public String save2(@ModelAttribute("user") @Valid User2 user, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        return "success";
    }

    /**
     * 级联验证
     * http://127.0.0.1:9080/spring4/save3?name=123&o.name=123
     *
     * @param user
     * @param result
     *
     * @return
     * 验证失败了！
     * 字段错误：
     * o.name------org长度必须在5到20之间  //@ConvertGroup(from=First.class, to=Second.class)
     * o.name------org名必须是字母        //o验证的其实是First.class
     * password------zh_CN 密码不能为空
     * id------zh_CN 用户编号不能为空
     *
     */
    @RequestMapping("/save3")
    public String save3(@ModelAttribute("user") @Validated({First.class}) User3 user, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        return "success";
    }

    //消息中使用EL表达式
    @RequestMapping("/save4")
    public String save4(@ModelAttribute("user") @Valid User0 user, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        return "success";
    }


    /**
     * 方法参数/返回值验证
     *
     * 方法或返回值验证失败抛出 javax.validation.ConstraintViolationException: null
     * 需要在ExceptionResolver中处理，获得失败信息 TODO
     * @param uuid
     * @return
     */
    @RequestMapping("/get0/{uuid}")
    public String get0(@PathVariable(value = "uuid") Integer uuid) {
        try {
            userService.get0(uuid);
        } catch (ConstraintViolationException e) {
            for(ConstraintViolation violation : e.getConstraintViolations()) {
                System.out.println(String.format("==================%s",
                        violation.getMessage()));
            }
            return "error";
        }
        return "success";
    }

    //自定义验证规则
    @RequestMapping("/save5")
    public String save5(@ModelAttribute("user") @Valid User4 user, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        return "success";
    }

    //类级别验证器
    @RequestMapping("/save6")
    public String save6(@ModelAttribute("user") @Valid User5 user, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        return "success";
    }

    //通过脚本验证
    @RequestMapping("/save7")
    public String save7(@ModelAttribute("user") @Valid User6 user, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        return "success";
    }

    @Autowired
    private UserService userService;

    //cross-parameter，跨参数验证
    @RequestMapping("/changePassword")
    public String changePassword0(
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "confirmation", required = false) String confirmation, Model model) {
        try {
            userService.changePassword0(password, confirmation);
        } catch (ConstraintViolationException e) {
            for(ConstraintViolation violation : e.getConstraintViolations()) {
                System.out.println(String.format("==================%s",
                        violation.getMessage()));
            }
            return "error";
        }
        return "success";
    }

    //混合类级别验证器和跨参数验证器 跨参数验证
    @RequestMapping("/changePassword0")
    public String changePassword(
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "confirmation", required = false) String confirmation, Model model) {
        try {
            userService.changePassword(password, confirmation);
        } catch (ConstraintViolationException e) {
            for(ConstraintViolation violation : e.getConstraintViolations()) {
                System.out.println(String.format("==================%s",
                        violation.getMessage()));
            }
            return "error";
        }
        return "success";
    }

    //混合类级别验证器和跨参数验证器 类级别使用
    @RequestMapping("/changePassword1")
    public String changePassword(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        return "success";
    }

    //组合验证注解
    @RequestMapping("/save8")
    public String save7(@ModelAttribute("user") @Valid User7 user, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        return "success";
    }

    @RequestMapping("/ajax")
    @ResponseBody
    public Object ajaxError(@Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            return AjaxError.from(result, null);
        }
        return "success";
    }


    @RequestMapping("/register")
    public String register(@Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        return "success";
    }

}
