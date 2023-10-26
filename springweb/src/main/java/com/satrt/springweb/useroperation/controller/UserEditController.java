package com.satrt.springweb.useroperation.controller;

import com.satrt.springweb.core.model.entity.UserEntity;
import com.satrt.springweb.exception.login.RegisterException;
import com.satrt.springweb.exception.sql.SqlServiceException;
import com.satrt.springweb.useroperation.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户编辑
 *
 * @author: Nanzhou
 * @version: v0.0.1
 * @date: 2023 2023/10/25 20:28
 */
@Controller
@RequestMapping("/useroperate")
public class UserEditController {
    private UserService userService;

    UserEditController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/userdirectory", method = RequestMethod.GET)
    public String list(Model model) throws SqlServiceException {
        // 查询用户列表
        List<UserEntity> list = userService.userDirectory();
        // 存到域中
        model.addAttribute("list", list);
        return "backgrounder/useroperate/userdirectory";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model, Integer id) throws SqlServiceException {
        // 查询用户信息
        UserEntity byId = userService.getById(id);
        // 存到域中
        model.addAttribute("user", byId);
        return "backgrounder/useroperate/useredit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(UserEntity user, @RequestParam("phonenumber") String phoneNumber ) throws SqlServiceException {
        // 修改用户信息
        user.setPhoneNumber(phoneNumber);
        userService.update(user);
        return "redirect:/useroperate/userdirectory";
    }
    @RequestMapping(value = "/useradd", method = RequestMethod.GET)
    public String addUser(Model model, Integer id) throws SqlServiceException {
        // 查询用户信息
        UserEntity byId = userService.getById(id);
        // 存到域中
        model.addAttribute("user", byId);
        return "backgrounder/useroperate/useradd";
    }
    @RequestMapping(value = "/useradd", method = RequestMethod.POST)
    public String addUser(UserEntity userEntity, String Registration_password, String re_Registration_password) throws RegisterException, SqlServiceException {
        //获取注册密码与确认密码
        String password = Registration_password;
        String rePassword = re_Registration_password;
        // 注册
        userService.register(userEntity, password,rePassword);
        return "redirect:/useroperate/userdirectory";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Integer id) throws SqlServiceException {
        System.out.println("删除");
        // 删除用户信息
        userService.delete(id);
        return "redirect:/useroperate/userdirectory";
    }
}
