package com.person.user.server.repository;

import com.person.user.server.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: user
 * @description:
 * @author: zhangzx
 * @create: 2019-10-11 10:41
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByOpenid(String openid);
}