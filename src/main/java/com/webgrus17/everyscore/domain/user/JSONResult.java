package com.webgrus17.everyscore.domain.user;

//쓰일지 모르겠으나, 프론트엔드에 json 형식의 응답을 보내는 경우 이용(예를 들어, 마이페이지)
public class JSONResult {
    private String result;  //success, fail
    private String message; //fail이면 세팅됨
    private Object data;    //success면 값을 세팅함

    public static JSONResult success(Object data){
        return new JSONResult("success",null,data);
    }

    public static JSONResult success(Object data, String value){
        return new JSONResult("success",value,data);
    }

    public static JSONResult fail(String message){
        return new JSONResult("fail",message,null);
    }

    private JSONResult(String result, String message, Object data){
        this.result =result;
        this.message=message;
        this.data=data;
    }

    public JSONResult(){
        super();
    }

    //미완성
    //CustomUrlAuthenticationSuccessHandle.java도 구현해야함
}
