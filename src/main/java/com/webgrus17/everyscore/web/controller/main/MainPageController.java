package com.webgrus17.everyscore.web.controller.main;

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
    public ResponseEntity<?> getBoard(Subject subject) throws Exception{
        return ResponseEntity.ok(subjectRepository.findAll());
    }

}