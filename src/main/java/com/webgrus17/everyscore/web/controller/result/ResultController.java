package com.webgrus17.everyscore.web.controller.result;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// 점수 결과 페이지 controller가 위치
@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor // 생성자
@RestController
public class ResultController {

    // 점수를 score, 난이도를 level로 해당 과목의 모든 사용자의 결과를 보내준다.
    @GetMapping("/api/v1/result/{Subject_name}/{Professor_name}")
    public String sendResult(@PathVariable String Subject_name, @PathVariable String Professor_name) {
        // 1. UserScoreService 추가후 findByName으로 과목 찾기

        // 2. 해당 과목에 해당되는 UserScore의 score, level 가져오기

        // 3. json으로 만든후 return

        return "";
    }

}
