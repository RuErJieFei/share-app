package com.yy.usercenter.service.Impl;

import com.yy.usercenter.mapper.BonusMapper;
import com.yy.usercenter.model.Bonus;
import com.yy.usercenter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final BonusMapper bonusMapper;

    @Override
    public void sign(int userId) {
        bonusMapper.insert(Bonus.builder().userId(userId).value(5)
                .event("签到").description("完成一次签到").createTime(Timestamp.valueOf(LocalDateTime.now())).build());
    }

    @Override
    public List<Bonus> selectBonus(int userId) {
        return bonusMapper.select(Bonus.builder().userId(userId).build());
    }
}
