package com.webgrus17.everyscore.web.controller.post;

import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.subject.SubjectRepository;
import com.webgrus17.everyscore.domain.user_score.UserScore;
import com.webgrus17.everyscore.domain.user_score.UserScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 첫글 작성(교수명, 과목명 추가), 점수 입력 controller가 위치
@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor // 생성자
@RestController
//@RestController
public class PostController {

    //첫글작성
    //시험종류 3가지 생성해야함
    /*
    테스트 실행 시에 500번 interver server error가 뜸
    db 접속에 불량이 있는것으로 보임
    레포지토리 save 코드를 제거했을 때엔 404번 포트가 뜸 즉, 이 코드에서 발생하는 문제로 보임
     */
    private SubjectRepository subjectRepository;
    @RequestMapping(value="/api/v1/start", method= RequestMethod.POST)
    public ResponseEntity<?> postStart(@RequestBody Subject subject){
        System.out.println("post");
        subjectRepository.save(subject);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    //점수입력
    private UserScoreRepository userScoreRepository;
    @RequestMapping(value="/api/v1/input", method=RequestMethod.POST)
    public ResponseEntity<?> postInput(@RequestBody UserScore userScore){
        userScoreRepository.save(userScore);
        return new ResponseEntity<>("{}",HttpStatus.CREATED);
    }
}
