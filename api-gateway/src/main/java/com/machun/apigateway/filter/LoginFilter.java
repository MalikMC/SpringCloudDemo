package com.machun.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.RequestContent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @Description:
 * @Author: machun
 * @CreateDate: 2020/4/18 15:30
 * @UpdateDate: 2020/4/18 15:30
 * @menu
 */
@Component
public class LoginFilter extends ZuulFilter {
     /**
      * 前置过滤器
       *
      * @author: machun
      * @createDate: 2020/4/18 15:36
      * @version: V1.0
      * @status: undone
     */

    @Override
    public String filterType() {
        return PRE_TYPE;
    }
     /**
      *  过滤器顺序，越小越先执行
       *
      * @author: machun
      * @createDate: 2020/4/18 15:35
      * @version: V1.0
     */
    @Override
    public int filterOrder() {
        return 4;
    }

     /**
       * 过滤器是否生效
       *
      * @author: machun
      * @createDate: 2020/4/18 15:34
      * @version: V1.0
      * @status: undone
     */
    @Override
    public boolean shouldFilter() {
        //需要过滤判断权限的接口
      RequestContext requestContext= RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest=requestContext.getRequest();

        if(httpServletRequest.getRequestURI().startsWith("/zuui-order/order"))
        {
            return  true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {

        // 登录检验，一般公司都是通过JWT
        RequestContext requestContext= RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest=requestContext.getRequest();
        String token=httpServletRequest.getHeader("token");
        token=StringUtils.isEmpty(token)?httpServletRequest.getParameter("token"):token;

        if(StringUtils.isEmpty(token))
        {
           requestContext.setSendZuulResponse(false);
           requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }

        return null;
    }
}
