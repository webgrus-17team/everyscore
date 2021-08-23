package com.webgrus17.everyscore.web.controller.post;

import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 첫글 작성(교수명, 과목명 추가), 점수 입력 controller가 위치
@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor // 생성자
@Controller
//@RestController
public class PostController {

    private SubjectRepository subjectRepository;
    @RequestMapping(value="/api/v1/start", method= RequestMethod.POST)
    public ResponseEntity<?> postStart(@RequestBody Subject subject){
        subjectRepository.save(subject);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }


}
