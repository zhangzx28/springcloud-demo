package com.person.user.server.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: user
 * @description:
 * @author: zhangzx
 * @create: 2019-10-11 14:08
 */
public class CookieUtil {

    /**
     * @Description: 设置cookie
     * @Param: [response, name, value, maxAge]
     * @return: void
     * @Author: zhangzx
     * @Date: 2019-10-11
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * @Description:  获取cookie
     * @Param: [request, name]
     * @return: javax.servlet.http.Cookie
     * @Author: zhangzx
     * @Date: 2019-10-11
     */
    public static Cookie get(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}