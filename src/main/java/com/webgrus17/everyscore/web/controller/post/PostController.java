package com.webgrus17.everyscore.web.controller.post;

import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.subject.SubjectRepository;
import com.webgrus17.everyscore.domain.user_score.UserScore;
import com.webgrus17.everyscore.domain.user_score.UserScoreRepository;
import com.webgrus17.everyscore.service.user.SubjectService;
import com.webgrus17.everyscore.service.user.UserScoreService;
import com.webgrus17.everyscore.web.dto.SubjectSaveDto;
import com.webgrus17.everyscore.web.dto.UserScoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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
    private final SubjectService subjectService;

    @RequestMapping(value="/api/v1/start", method= RequestMethod.POST)
    public Long saveSubject(@RequestBody final SubjectSaveDto subjectSaveDto){
        return subjectService.save(subjectSaveDto);
    }
    /*
    1. 받은 값은 교수명과 과목명... 여기에 덧붙여 시험종류를 추가해줘야하는데 그렇게 되면 requestbody를 subjectsavedto로 처리하면
        안될 것 같음
    2. 시험종류 추가는 enum으로 처리해서 반복문 3번 돌리거나, 일일이 추가하거나 하면 될거 같음
     */

    //점수입력
    private UserScoreService userScoreService;
    @RequestMapping(value="/api/v1/input/{subjectName}/{professorName}/{testType}", method=RequestMethod.POST)
    public ResponseEntity<?> postInput(@RequestBody UserScoreDto userScoreDto){
        userScoreService.save(userScoreDto);
        return new ResponseEntity<>("{}",HttpStatus.CREATED);
    }
    public String postInput(@PathVariable String subjectName, @PathVariable String professorName, @PathVariable String testType,
                            @RequestBody UserScoreDto userScoreDto){
        //파라미터값으로 과목 찾음
        Subject subject = subjectService.findByName(subjectName, professorName, testType);


        /*
        1.파라미터로 과목 찾아냄 ->key
        2. 파라미터로 user 찾아내거나 세션으로 어떤 user가 사용하는지 알아채서 userscore의 user id를 입력해줘야하는데 알수없음
        3. 엔티티로 만들어냄
        4. 저장
         */
        return "redirect:http://localhost:8081/Main_2.jsp"; //메인게시판으로 이동
    }
}
