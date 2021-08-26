package com.webgrus17.everyscore.web.controller.result;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.user_score.UserScore;
import com.webgrus17.everyscore.service.user.SubjectService;
import com.webgrus17.everyscore.service.user.UserScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 점수 결과 페이지 controller가 위치
@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor // 생성자
@RestController
public class ResultController {

    private final SubjectService subjectService;

    private final UserScoreService userScoreService;

    // 점수를 score, 난이도를 level로 해당 과목의 모든 사용자의 결과를 보내준다.
    @GetMapping("/api/v1/result/{subjectName}/{professorName}/{testType}")
    public String sendResult(@PathVariable String subjectName, @PathVariable String professorName, @PathVariable String testType) {
        // 1. SubjectService에 추가후 findByName으로 과목 찾기
        Subject tempSubject = subjectService.findByName(subjectName, professorName, testType);

        // 2. 해당 과목에 해당되는 UserScore의 score, level 가져오기
        List<UserScore> tempUserScore = userScoreService.findBySubject(tempSubject);

        // 3. json으로 만든후 return
        JsonObject jo = new JsonObject();

        JsonArray ja = new JsonArray();
        for (int i = 0; i < tempUserScore.size(); i++) {
            JsonObject tmpOb = new JsonObject();
            tmpOb.addProperty("score", tempUserScore.get(i).getMyscore());
            tmpOb.addProperty("level", tempUserScore.get(i).getLevel());
            ja.add(tmpOb);
        }

        jo.add("result", ja);

        return jo.toString();
    }
    /*
    sendResult 테스트하기 위한 쿼리문, API(과목/유저 하나 추가, userScore 내용 추가)

    - 실제로는 이렇게 테스트하는 것이 아닌, 테스트코드를 활용하는 것이 권장됨
    - 우선 완성을 목표로 하고 있으므로, 일단은 직접 쿼리문 작성한후 api 확인해보기

    - 쿼리문으로 생성하여서 생성일 및 수정일이 저장되지는 않을 것
    - 비밀번호도 암호화되서 들어가지는 않음, 단순 테스트니 문제 없을 듯

    1. 쿼리문 추가

    INSERT INTO SUBJECT (PROFESSOR_NAME, SUBJECT_NAME, TEST_TYPE)
    VALUES
    ('교수명1', '과목명1', '중간고사'),
    ('교수명1', '과목명1', '퀴즈'),
    ('교수명2', '과목명2', '퀴즈'),
    ('교수명2', '과목명2', '기말고사');

    INSERT INTO USER (BIRTHDAY, CLASSNUMBER, EMAIL, GENDER, ID, MAJOR, NAME, PW)
    VALUES
    ('2021-08-26', '컴퓨터공학과', 'test1@test.com', 'male', 'test1', '12121212', '테스트1', 'test1'),
    ('2021-08-25', '수학과', 'test2@test.com', 'female', 'test2', '13131313', '테스트2', 'test2');

    INSERT INTO USER_SCORE(LEVEL, MYSCORE, SUBJECT_ID, USER_CODE)
    VALUES
    ('1', '89', '1', '1'),
    ('4', '56', '1', '2'),
    ('3', '69', '2', '1'),
    ('2', '81', '2', '2'),
    ('1', '98', '3', '1'),
    ('1', '91', '3', '2'),
    ('5', '31', '4', '1'),
    ('4', '50', '4', '2');

    2. API 테스트
    http://localhost:8080/api/v1/result/과목명1/교수명1/중간고사

    http://localhost:8080/api/v1/result/과목명1/교수명1/퀴즈

    http://localhost:8080/api/v1/result/과목명2/교수명2/퀴즈

    http://localhost:8080/api/v1/result/과목명2/교수명2/기말고사
     */

    /*
    예상 보낼 json 형식(UserScore에 점수, 난이도 별 이름이 잘 저장되었다는 가정으로)
    {
        "result":[
            {
                "score":17,
                "level":1
            },
            {
                "score":21,
                "level":3
            },
        ]
    }
     */

    /*
    간과한 내용이 하나 있음
    현재 회원 기능이 불안정해서 내 점수가 몇점인지 모른다
    이를 알려면 현재 사용중인 user의 id 혹은 code가 필요, 일단은 생략해야 할 수도
     */


}
