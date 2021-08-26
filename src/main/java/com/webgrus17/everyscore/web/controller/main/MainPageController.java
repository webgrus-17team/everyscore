package com.webgrus17.everyscore.web.controller.main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// 메인 페이지와 관련된 controller 모음
@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor // 생성자
@RestController
public class MainPageController {

    //게시판 검색기능을 구현안해도 될듯, 전체 게시판 내용만 주면 검색은 프론트에서 처리한다고 함
    //게시판 과목 출력
    SubjectRepository subjectRepository;
    @RequestMapping(value = "api/v1/board", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBoard(){
        List<Subject> subjects=subjectRepository.findAll();
        JsonArray jsonArray=new JsonArray();

        for(int i=0;i<subjects.size();i++){
            JsonObject temp=new JsonObject();
            temp.addProperty("professorName", subjects.get(i).getProfessorName());
            temp.addProperty("subjectName",subjects.get(i).getSubjectName());
            temp.addProperty("testType",subjects.get(i).getTestType());
            jsonArray.add(temp);
        }
        return jsonArray.toString();
    }
    /*
    restconroller 내부의 responseentity를 사용하고 싶었지만 직관적이지가 않아서 result api와 동일하게 jsonarray로 처리
     */

}