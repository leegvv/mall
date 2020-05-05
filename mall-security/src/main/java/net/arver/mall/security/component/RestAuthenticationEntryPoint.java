package net.arver.mall.security.component;

import cn.hutool.json.JSONUtil;
import net.arver.mall.common.api.JsonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义返回结构，未登录或登录过期
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException e) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(JsonResult.unauthorized(e.getMessage())));
        response.getWriter().flush();
    }
}
