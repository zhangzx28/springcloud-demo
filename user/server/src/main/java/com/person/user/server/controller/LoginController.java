package com.person.user.server.controller;

import com.person.user.server.constant.CookieConstant;
import com.person.user.server.constant.RedisConstant;
import com.person.user.server.dataobject.UserInfo;
import com.person.user.server.enums.ResultEnum;
import com.person.user.server.enums.RoleEnum;
import com.person.user.server.service.UserService;
import com.person.user.server.utils.CookieUtil;
import com.person.user.server.utils.ResultVOUtil;
import com.person.user.server.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: user
 * @description:
 * @author: zhangzx
 * @create: 2019-10-11 11:00
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response) {
        // 1.openid和数据库里数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        // 2.判断角色
        if (!RoleEnum.BUYER.getCode().equals(userInfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        // 3.cookie设置openid
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        // 判断是否已登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

        if (cookie != null
                && StringUtils.isNotEmpty(stringRedisTemplate.opsForValue()
                .get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultVOUtil.success();
        }

        // 1.openid和数据库里数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        // 2.判断角色
        if (!RoleEnum.SELLER.getCode().equals(userInfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        // 3.redis设置UUID
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid,
                expire,
                TimeUnit.SECONDS);

        // 4.cookie设置token=UUID
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return ResultVOUtil.success();
    }

}