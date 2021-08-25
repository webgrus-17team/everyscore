package com.webgrus17.everyscore.web.controller.result;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

// 점수 결과 페이지 controller가 위치
@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor // 생성자
@Controller
public class ResultController {

    // 점수를 score, 난이도를 level로 해당 과목의 모든 사용자의 결과를 보내준다.
}
