package com.yy.usercenter.service;

import com.yy.usercenter.model.Bonus;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    void sign(int userId);

    List<Bonus> selectBonus(int userId);
}
