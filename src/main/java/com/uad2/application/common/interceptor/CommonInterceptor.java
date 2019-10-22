package com.uad2.application.common.interceptor;

/*
 * @USER Jongyeob Kim
 * @DATE 2019-10-05
 * @DESCRIPTION 컨트롤러에 대한 인터셉터. 로그인 세션 정보를 확인한다.
 */

import com.uad2.application.common.annotation.Auth;
import com.uad2.application.common.enumData.Role;
import com.uad2.application.member.LoginProcessor;
import com.uad2.application.member.dto.MemberDto;
import com.uad2.application.member.entity.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;


@Component
public class CommonInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

    @Autowired
    LoginProcessor loginProcessor;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("-----Common interceptor-----");
        // handler : 인터셉터 이후에 실행할 컨트롤러의 메소드
        if (!(handler instanceof HandlerMethod)) {
            // 컨트롤러에 존재하지 않는 메소드인 경우 그대로 컨트롤러로 넘긴다.
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        if (Objects.isNull(auth)) {
            // @Auth 어노테이션 없는 메소드인 경우(public 엔드포인트) 컨트롤러로 바로 넘긴다.
            return true;
        }

        HttpSession session = request.getSession();

        // 세션 정보가 없을 경우
        Member member = (Member)session.getAttribute("member");
        if(Objects.isNull(member)){
            //로그인 페이지로 이동 필요
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        //api 열람 권한이 없을 경우
        if(auth.role() == Role.ADMIN && member.getIsAdmin() == 0){
            //어떻게 처리할지 미정
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        //정상적인 로그인 상태일 경우, 로그인 로직을 재실행 시켜 로그인 상태를 매번 업데이트 해준다.
        MemberDto.LoginRequest loginRequest = MemberDto.LoginRequest.builder()
                                                .id(member.getId())
                                                .pwd(member.getPwd())
                                                .isAutoLogin(Objects.nonNull(member.getSessionId()))
                                                .build();
        loginProcessor.login(request,response,session,loginRequest);
        return true;
    }

}
