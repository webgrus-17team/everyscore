package com.webgrus17.everyscore.domain.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//JSON 응답을 위해 만듦
public class JSONResult {
    private String code;
    private String status;  //success, fail
    private String message; //fail이면 세팅됨
    private Object data;    //success면 값을 세팅함

    public class ResponseDataCode{
        public static final String SUCCESS="200";
        public static final String ERROR="999";
    }

    public class ResponseDataStatus{
        public static final String SUCCESS="200";
        public static final String ERROR="999";
    }
}
