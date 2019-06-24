package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.RoleService;
import com.itheima.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有用户信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfoList = userService.findAll();
        mv.addObject("userList",userInfoList);
        mv.setViewName("user-list");
        return mv;
    }

    //新增用户
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    //根据id查询用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //根据id查询用户可添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        //查询用户信息
        UserInfo userInfo = userService.findById(id);
        //查询用户可添加的角色
        List<Role> roleList = roleService.findByUserId(id);

        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    //给用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids", required = true) String[] roleIds) throws Exception{
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

    //根据用户id查询可删除的角色
    @RequestMapping("/findUserAndRoleById.do")
    public ModelAndView findUserAndRoleById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findRoleByUserId(id);
        mv.addObject("userId",id);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-delete");
        return mv;
    }

    //删除角色
    @RequestMapping("/deleteRoleFromUser.do")
    public String deleteRoleFromUser(String userId,String[] ids) throws Exception {
        userService.deleteRoleFromUser(userId,ids);
        return "redirect:findAll.do";
    }

    //根据id查询单个用户信息
    @RequestMapping("/findOneById.do")
    public ModelAndView findOneById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findOneById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-update");
        return mv;
    }

    //更改用户信息
    @RequestMapping("/update.do")
    public String update(UserInfo userInfo) throws Exception{
        userService.update(userInfo);
        return "redirect:findAll.do";
    }

    //删除选中的用户
    @RequestMapping("/deleteSelected.do")
    public String deleteSelected(String[] ids) throws Exception{
//        System.out.println(ids);
        userService.deleteSelected(ids);
        return "redirect:findAll.do";
    }
}
