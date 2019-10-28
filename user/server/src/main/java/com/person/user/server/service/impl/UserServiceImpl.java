package com.person.user.server.service.impl;

import com.person.user.server.dataobject.UserInfo;
import com.person.user.server.repository.UserInfoRepository;
import com.person.user.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: user
 * @description:
 * @author: zhangzx
 * @create: 2019-10-11 10:54
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}