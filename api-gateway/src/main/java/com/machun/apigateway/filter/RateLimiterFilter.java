package com.machun.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/18 16:29
 * @UpdateDate: 2020/4/18 16:29
 *
 * @menu
 */
public class RateLimiterFilter extends ZuulFilter {


    /**
     * 每秒产生令牌数
     */
     private  static final RateLimiter RATE_LIMITER=RateLimiter.create(1000);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -4;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if(!RATE_LIMITER.tryAcquire()){
            RequestContext requestContext=RequestContext.getCurrentContext();
            requestContext.setResponseStatusCode(HttpStatus.SC_REQUEST_TIMEOUT);
        }
        return null;
    }
}
