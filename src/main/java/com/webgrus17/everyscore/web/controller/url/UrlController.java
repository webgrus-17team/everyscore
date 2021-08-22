package com.webgrus17.everyscore.web.controller.url;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

// 프론트에서 사이트를 옮길 때마다 해당 controller를 호출, 로그인 안되어있을 경우에는 다시 로그인 페이지로 이동하게 함
// 권한, 인가를 위해 url 매핑하는 controller
@CrossOrigin(origins = "http://localhost:8081") // CORS 설정, 해당 주소에서 오는 api요청만 받아드림
@RequiredArgsConstructor // 생성자
@Controller
public class UrlController {
}
