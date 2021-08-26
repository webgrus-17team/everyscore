package com.webgrus17.everyscore.web.controller.post;

import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.subject.SubjectRepository;
import com.webgrus17.everyscore.domain.user.User;
import com.webgrus17.everyscore.domain.user_score.UserScore;
import com.webgrus17.everyscore.domain.user_score.UserScoreRepository;
import com.webgrus17.everyscore.service.user.SubjectService;
import com.webgrus17.everyscore.service.user.UserScoreService;
import com.webgrus17.everyscore.service.user.UserService;
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
    private final SubjectService subjectService;

    @RequestMapping(value="/api/v1/start", method= RequestMethod.POST)
    public String saveSubject(@RequestBody final SubjectSaveDto subjectSaveDto){
        subjectService.save(subjectSaveDto);
        return "redirect:http://localhost:8081/Main_3.jsp"; //메인게시판으로 이동
    }
    /*
    테스트 실행 시에 500번 interver server error가 뜸
    db 접속에 불량이 있는것으로 보임
    레포지토리 save 코드를 제거했을 때엔 404번 포트가 뜸 즉, 이 코드에서 발생하는 문제로 보임
    -->> 해결됨
     */
    /*
    ***시험종류 3가지 추가하는 코드 있어야함
    1. 받은 값은 교수명과 과목명... 여기에 덧붙여 시험종류를 추가해줘야하는데 그렇게 되면 requestbody를 subjectsavedto로 처리하면 안됨
    -> 대안 필요 -->>임의 해결
        entity 내부에는 testType이 있고, dto 내부에는 testType을 없앰.
        dto 내부에서 entity화 할때, 중간/기말/퀴즈를 우선 일일이 추가해줌
    -> save 시에 퀴즈엔티티의 id 값을 받아본 결과, 3으로 적절히 나옴.
     */

    //점수입력
    private UserScoreService userScoreService;
    private UserService userService;
    @RequestMapping(value="/api/v1/input/{subjectName}/{professorName}/{testType}", method=RequestMethod.POST)
    public String postInput(@PathVariable String subjectName, @PathVariable String professorName, @PathVariable String testType,
                            @RequestAttribute String id, @RequestAttribute Integer myscore, @RequestAttribute Integer level){    //현재 requestbody에 담긴 값들은 user/myscore/level
        //과목명/교수명/시험종류로 과목 pk 찾기
        Subject subject = subjectService.findByName(subjectName, professorName, testType);
        //id로 user pk 찾기
        User user=userService.findById(id);

        //엔티티 만듦
        UserScoreDto userScoreDto=UserScoreDto.builder()
                .user(user)
                .subject(subject)
                .myscore(myscore)
                .level(level)
                .build();

        userScoreService.save(userScoreDto);
        return "redirect:http://localhost:8081/Main_3.jsp"; //메인게시판으로 이동
    }
}
