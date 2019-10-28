//package com.person.apigateway.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import com.person.apigateway.constant.CookieConstant;
//import com.person.apigateway.constant.RedisConstant;
//import com.person.apigateway.utils.CookieUtil;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
//
///**
// * @program: api-gateway
// * @description:
// * @author: zhangzx
// * @create: 2019-10-09 21:05
// */
//@Component
//public class AuthFilter extends ZuulFilter {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Override
//    public String filterType() {
//        return PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return PRE_DECORATION_FILTER_ORDER - 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//
//        /**
//         * /order/create 只能买家访问(cookie里有openid)
//         * /order/finash 只能卖家访问(cookie里有token，并且对应的redis中有值)
//         * /product/list 都能访问
//         */
//        if ("/order/order/create".equals(request.getRequestURI())) {
//            Cookie cookie = CookieUtil.get(request, CookieConstant.OPENID);
//            if (cookie == null || StringUtils.isBlank(cookie.getValue())) {
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//            }
//        }
//
//        if ("/order/order/finish".equals(request.getRequestURI())) {
//            Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
//            if (cookie == null
//                    || StringUtils.isBlank(cookie.getValue())
//                    || StringUtils.isBlank(stringRedisTemplate.opsForValue()
//                    .get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//            }
//        }
//
//        return null;
//    }
//}