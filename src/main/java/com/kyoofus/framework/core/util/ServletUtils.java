package com.kyoofus.framework.core.util;


// 새로운 스프링 부트 3.0 는 Java17 을 기반으로 작성된 새로운 프레임 워크입니다.

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kyoofus.framework.core.exception.AjaxExceptoin;
import com.kyoofus.framework.core.exception.ErrorCode;

import java.util.Optional;

/**
 * Servlet 처리할 때 사용할 편리한 기능을 제공한다.
 */
public class ServletUtils {
    
    /**
     * HttpServletRequest를 반환한다.
     */
    public static Optional<HttpServletRequest> getRequest() {
        RequestAttributes rattr = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sattr = (ServletRequestAttributes) rattr;
        if(sattr == null) {
            return Optional.empty();
        }else {
            return Optional.ofNullable(sattr.getRequest());
        }
    }

    /**
     * HttpServletResponse 반환한다.
     */
    public static Optional<HttpServletResponse> getResponse() {
        RequestAttributes rattr = RequestContextHolder.getRequestAttributes();
        
        if(rattr == null) {
            return Optional.empty();
        }else {
            HttpServletResponse response = ((ServletRequestAttributes)rattr).getResponse();
            return Optional.ofNullable(response); 
        }
    }

    public static void redirect(String url ) {
        Optional<HttpServletResponse> opt =  ServletUtils.getResponse();
        try {
           if(opt.isPresent()){ 
               ((HttpServletResponse)opt.get()).sendRedirect(url);
            }
        }catch(Exception e) {
            throw new AjaxExceptoin(e.getMessage(), ErrorCode.INTERAL_SERVER_ERROR);
        }
        // 예외 처리 필요 
    }//:

    /** 권한없음(401: 인증자격증명 없음) 페이지를 생성하여 반환한다. */
    public static void responseUnauthorized(@NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response) {
    
        String accept = request.getHeader("accept");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            if (accept.contains("application/json")) {
                response.setContentType("application/json");
                response.getWriter().write("{\"message\":\"Unauthorized\"}");
            } else if(accept.contains("text/html")) {
                response.setContentType("text/html");
                response.getWriter().write("<h1>Unauthorized</h1>");
            }else { 
                response.setContentType("text/plain");
                response.getWriter().write("Unauthorized");
            }
            // 401 에러는 전달할 에러 메시지가 없으므로 문자열을 전달한다. 
            // Content-Type은 http2.js에서 사용하므로 정확히 맞추어야 한다.
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }// :



}/// ~