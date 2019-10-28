package com.person.user.server.service;

import com.person.user.server.dataobject.UserInfo;

/**
 * @program: user
 * @description:
 * @author: zhangzx
 * @create: 2019-10-11 10:51
 */
public interface UserService {

    /**
     * @Description: 通过openid查询用户信息
     * @Param: [openid]
     * @return: com.person.user.server.dataobject.UserInfo
     * @Author: zhangzx
     * @Date: 2019-10-11
     */
    UserInfo findByOpenid(String openid);
}