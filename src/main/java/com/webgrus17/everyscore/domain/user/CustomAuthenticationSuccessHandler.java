package com.webgrus17.everyscore.domain.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//로그인 성공을 위한 핸들러
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    //@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException{
        ObjectMapper mapper =new ObjectMapper();    //json 변경용
        JSONResult jsonResult=new JSONResult();
        jsonResult.setCode(JSONResult.ResponseDataCode.SUCCESS);
        jsonResult.setStatus(JSONResult.ResponseDataStatus.SUCCESS);

        //...
    }
}
