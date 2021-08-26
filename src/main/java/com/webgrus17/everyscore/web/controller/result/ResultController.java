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
    @GetMapping("/api/v1/result/{subjectName}/{professorName}")
    public String sendResult(@PathVariable String subjectName, @PathVariable String professorName) {
        // 1. SubjectService에 추가후 findByName으로 과목 찾기
        Subject tempSubject = subjectService.findByName(subjectName, professorName);

        // 2. 해당 과목에 해당되는 UserScore의 score, level 가져오기
        List<UserScore> tempUserScore = userScoreService.findBySubject(tempSubject);

        // 3. json으로 만든후 return
        JsonObject jo = new JsonObject();

        JsonArray ja = new JsonArray();
        for(int i=0; i< tempUserScore.size(); i++){
            JsonObject tmpOb = new JsonObject();
            tmpOb.addProperty("score", tempUserScore.get(i).getMyscore());
            tmpOb.addProperty("level", tempUserScore.get(i).getLevel());
            ja.add(tmpOb);
        }

        jo.add("result", ja);

        return jo.toString();

        /*
        예상 보낼 json 형식(UserScore에 점수, 난이도 별 이름이 잘 저장되었다는 가정으로)
        {
            "result":[
                {
                    "score":17,
                    "level":"easy"
                },
                {
                    "score":21,
                    "level":"hard"
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

}
