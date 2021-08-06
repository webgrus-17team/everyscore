package com.webgrus17.everyscore.web;

import com.webgrus17.everyscore.domain.user.User;
import com.webgrus17.everyscore.web.dto.JSONResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
        protected final Log logger= LogFactory.getLog(this.getClass());
        private RequestCache requestCache=new HttpSessionRequestCache();

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws ServletException, IOException {
            SavedRequest savedRequest = requestCache.getRequest(request, response);

            if (savedRequest != null) {
                requestCache.removeRequest(request, response);
                clearAuthenticationAttributes(request);
            }

            String accept = request.getHeader("accept");

            User user = null; //SecurityUser
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (principal != null && principal instanceof UserDetails) {
                    user = (User) principal;
                }
            }

            //일반 응답일 경우
            if (accept == null || accept.matches(".*Application/json.*") == false) {
                request.getSession(true).setAttribute("loginNow", true);
                getRedirectStrategy().sendRedirect(request, response, "/");
                return;
                //이전 페이지 없음 유의
            }

            //application/json(ajax) 요청일 경우 처리
            MappingJackson2CborHttpMessageConverter jsonConverter = new MappingJackson2CborHttpMessageConverter();
            MediaType jsonMimeType = MediaType.APPLICATION_JSON;

            JSONResult jsonResult = JSONResult.success(user);
            if (jsonConverter.canWrite(jsonResult.getClass(), jsonMimeType)) {
                jsonConverter.write(jsonResult, jsonMimeType, new ServletServerHttpResponse(response));
            }
        }

        public void setRequestCache(RequestCache requestCache){
            this.requestCache=requestCache;
    }
}
