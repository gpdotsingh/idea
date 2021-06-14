package com.idea.loadOnLogin.idea.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet filter to log request and response
 * The logging implementation is pretty native and for demonstration only
 *
 * @author hemant
 */
@Component
@Order(2)
public class GenericFilter implements Filter {

    private final static Logger LOG = LoggerFactory.getLogger(GenericFilter.class);

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing filter :{}", this);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        chain.doFilter(request, response);
        LOG.debug("Logging Response :{}", res.getContentType());
        System.out.println("Logging Request  {} : {}"+ req.getRequestURI());
        System.out.println("Logging Request  {} : {}"+ req.getMethod());
    }

    @Override
    public void destroy() {
        LOG.warn("Destructing filter :{}", this);
    }
}
