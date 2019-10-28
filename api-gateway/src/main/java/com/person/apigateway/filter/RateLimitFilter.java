package com.person.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.person.apigateway.exception.RateLimitException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * @program: api-gateway
 * @description: 限流
 * @author: zhangzx
 * @create: 2019-10-10 15:55
 */
@Component
public class RateLimitFilter extends ZuulFilter {

    /**
     * 每秒放入100个令牌
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER -1 ;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //如果没有拿到令牌
        if (!RATE_LIMITER.tryAcquire()) {
            throw new RateLimitException();
        }

        return null;
    }
}