package com.example.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul过滤器
 * Created by baipan
 * Date: 2018-07-16
 */
public class AccessFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 过滤器的类型
     * 决定过滤器在请求的哪个生命周期中执行
     * 在请求之前执行
     */
    @Override
    public String filterType() {
        return "pre";
    }


    /**
     * 过滤器的执行顺序
     * 可能会存在多个过滤器
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断过滤器是否需要被执行
     * 可以利用函数来指定过滤器的有效范围
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }


    /**
     * shouldFilter() == true后执行
     * 过滤器的具体逻辑
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        Object accessToken = request.getParameter("accessToken");
        if (accessToken == null) {
            logger.warn("access token is empty");
            ctx.setSendZuulResponse(false);//使zuul过滤改请求
            ctx.setResponseStatusCode(401);//response返回的状态码
            ctx.setResponseBody("<b>你莫得权限</>");//response返回的内容
        }

        logger.info("access token ok");

        return null;
    }
}
