package com.webgrus17.everyscore.web.dto;

//json 형식으로 제대로 응답하는지 확인하기 위함
//data 객체가 핵심
/*
제대로 응답한다면, {result: "success", message: null,
                data: {id:"", password:"", name:"",...}}의 형식이어야 함.
 */
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JSONResult {
    private String result; //성공 또는 실패
    private String message; //실패일 경우
    private Object data;    //성공일 경우

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
        this.result=result;
        this.message=message;
        this.data=data;
    }

    public JSONResult() {
        super();
    }

    @Override
    public String toString(){
        return "JSONResult [result="+result+", message="+message+", data="+data+"]";
    }
}
