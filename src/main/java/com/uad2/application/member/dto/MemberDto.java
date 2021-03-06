package com.uad2.application.member.dto;

/*
 * @USER Jongyeob
 * @DATE 2019-09-19
 * @DESCRIPTION 회원 도메인에 대한 request, response 처리하는 커맨드 객체
 */

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class MemberDto {

    @Getter
    @Setter
    public static class Request {   // 프로퍼티 공통 사용여부에 따라 Create, Update로 분리해도 괜찮을 것 같다.
        private String id;

        private String pwd;

        private String name;

        private String birthDay;

        private int studentId;

        private int isWorker;

        private String phoneNumber;

        public Request(String id,String pwd,String name,String birthDay,int studentId,int isWorker,String phoneNumber) throws Exception{
            this.id = id;
            this.pwd = pwd;
            this.name = name;
            this.birthDay = birthDay;
            this.studentId = studentId;
            this.isWorker = isWorker;
            this.phoneNumber = phoneNumber;
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class LoginRequest {
        private String id;

        private String pwd;

        private boolean isAutoLogin;

        public boolean getIsAutoLogin(){
            return isAutoLogin;
        }
        public void setIsAutoLogin(boolean isAutoLogin){
            this.isAutoLogin = isAutoLogin;
        }
    }

    @Getter
    @Setter
    public static class EditRequest {
        private String pwd;

        private Date birthDay;

        private int studentId;

        private String phoneNumber;
    }

    @Getter
    @Setter
    public static class Response {
        private String id;

        private String name;

        private int seq;

        private String profileImg;

        private int attdCnt;

        private Date birthDay;

        private int studentId;

        private int isWorker;

        private String phoneNumber;

        private String sessionId;

        private Date sessionLimit;

        private int isAdmin;

        private int isBenefit;
    }

}
