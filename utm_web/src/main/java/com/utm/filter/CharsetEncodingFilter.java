package com.utm.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 改变字符编码Filter
 */
public class CharsetEncodingFilter implements Filter {

    private String encoding;

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {


        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

        this.encoding = filterConfig.getInitParameter("encoding");
    }
}
