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

    //方法参数/返回值验证
    @RequestMapping("/get0/{uuid}")
    public String get0(@PathVariable(value = "uuid") Integer uuid) {
        userService.get0(uuid);
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


    @Autowired
    private UserService userService;


    @RequestMapping("/changePassword")
    public String changePassword(
            @RequestParam("password") String password,
            @RequestParam("confirmation") String confirmation, Model model) {
        try {
            userService.changePassword(password, confirmation);
        } catch (ConstraintViolationException e) {
            for(ConstraintViolation violation : e.getConstraintViolations()) {
                System.out.println(violation.getMessage());
            }
        }
        return "success";
    }

}
