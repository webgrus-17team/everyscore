package com.webgrus17.everyscore.web.controller.main;

import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// 메인 페이지와 관련된 controller 모음
@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor // 생성자
@Controller
public class MainPageController {

    //게시판 과목 출력
    SubjectRepository subjectRepository;
    @RequestMapping(value = "api/v1/board", method = RequestMethod.GET)
    public List<Subject> all(){ //우선 list 형식으로 모두 출력해보기
        return subjectRepository.findAll();
    }
}