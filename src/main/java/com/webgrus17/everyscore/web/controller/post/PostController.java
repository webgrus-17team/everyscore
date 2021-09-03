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
                            @RequestAttribute String id, @RequestAttribute Integer myscore, @RequestAttribute Integer level){    //현재 request에 담긴 값들은 user/myscore/level
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
/*
현재 post인데 url에 파라미터 또한 존재함
때문에 param 처리하는 어노테이션과 body 처리하는 어노테이션이 함께 쓰여야 함
구글링헤봤을 때, 동시에 실행하는 케이스가 드물게 있는 것으로 보임
user를 모두 주석처리하고, subject post된 것 확인하고 나서 api 테스트를 실행해봤는데, 404가 뜸
경로의 문제거나 , 없는 데이터를 요구하는 문제인데, 파악하지 못함
(user를 주석처리한 이유는, 회원가입 때 500에러가 발생하기 때문임)
우선 프론트 수정해보고 그래도 회원가입때 error 발생할 시 확인해 보기

-> 그냥 프론트 main_3 페이지 이름이 변경되어서 주소 오류로 안되었던 것

requestattribute 어노테이션을 쓴 이유는, requestbody 어노테이션은 dto 전체를 받는데, dto에서 subject 부분은 우리가 받고나서 엔티티화 해줘야하기 때문에 dto 일부만을 받기 위함임
그러나 처음 써보는 것이라 이 부분에서 문제가 발생할 수 있기 때문에 공부가 더 필요한 듯함
*/