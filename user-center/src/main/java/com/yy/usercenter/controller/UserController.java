package com.yy.usercenter.controller;

import com.yy.usercenter.mapper.UserMapper;
import com.yy.usercenter.model.Bonus;
import com.yy.usercenter.model.User;
import com.yy.usercenter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserMapper userMapper;
    @Resource
    private UserService userService;

    @GetMapping("/me/{userId}")
    public User getUser(@PathVariable int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @GetMapping("/bonus-logs/{userId}")
    public List<Bonus> getBonuses(@PathVariable int userId) {
        return userService.selectBonus(userId);
    }
}
