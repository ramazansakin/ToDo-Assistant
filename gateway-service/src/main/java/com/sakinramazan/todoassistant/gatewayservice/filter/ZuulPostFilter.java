package com.sakinramazan.todoassistant.gatewayservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class ZuulPostFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(ZuulPostFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        // Internal server errors handling
        try {
            HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
            log.info("PostFilter: " + String.format("response's content type is %s", response.getStatus()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
