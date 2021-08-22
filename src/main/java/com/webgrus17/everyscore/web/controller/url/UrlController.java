package com.webgrus17.everyscore.web.controller.url;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

// 프론트에서 사이트를 옮길 때마다 해당 controller를 호출, 로그인 안되어있을 경우에는 다시 로그인 페이지로 이동하게 함
// -> WebSecurityConfig에서 로그인, 회원가입 api를 제외하고는 모두 로그인 상태에서만 작동하도록 설정해 두었기 때문

// 솔직히 이게 맞는 방법인지는 모르겠으나, 해당 처리를 안할 경우 로그인 여부와 관계없이 페이지를 돌아다닐 수 있음(프론트 끼리의 링크로 한다면)

// 권한, 인가를 위해 url 매핑하는 controller
@CrossOrigin(origins = "http://localhost:8081") // CORS 설정, 해당 주소에서 오는 api요청만 받아드림
@RequiredArgsConstructor // 생성자
@Controller
public class UrlController {

    /*
    !!!!!!!중요 오류사항!!!!!!!!
    메인페이지, 점수입력, 결과 입력 페이지 간의 권한 없는 이동을 막기 위해 다음 3개의 controller를 만들었음
    그런데 프론트의 각 페이지로 넘어가는 링크를 해당 api로 모두 변경해본 결과,

    <api가 작동하지 않고 권한이 없을 때 이동되는 로그인 페이지로 이동됨>

    이리 된다면 로그인이 되야만 가능한 다른 api들도 모두 작동하지 않을 수 있게 됨

    + 그런데 logout 기능은 또 잘 되는걸 보면, 로그인이 된거 같기는 한데(JSESSIONID도 존재하기는 함)

    WebSecurityConfig 설정을 권한이 있으면 접근 가능으로 바꾸어도 여전히 작동되지 않음
    */

    /*
    오류 가능성

    1. 현재 WebSecurityConfig 설정은 단순히 권한이 있으면 나머지 api를 접근하도록 되어있음
    - 일단 크롬 f12로 확인해 보면 사용자에게 JSESSIONID가 주어지기는 함
    - 그런데 WebSecurityConfig에서 단순히 권한이 없으면 api에 접근 못하도록 설정해 놈
    - JSESSIONID를 받아서 이 사용자가 권한이 있는지 확인하는 과정을 따로 제작해야한다?

    2. 일단 로그인 화면을 지나기는 했지만, 백엔드 측에서 단순히 로그인이 되었을 뿐, 프론트에게 주어지는 세션 id와 연결점이 없을 수도 있다.
    - 따라서 단순히 로그아웃 주소를 프론트에서 보내면, 그냥 "야 백엔드 너네 로그인한거 다시 로그아웃해!" 해서 로그아웃이 되는거일수도

    3. 생각나는 다른 경우 있으면 추가해주세요..

    일단 가능성이 높은건 1번 같은데 계속 검색해보는 중
    최악의 수단으로는 우선 시큐리티를 다뤄봤다는 것에 의의를 두고, 일단 api 권한을 다 여는 것(우선 작동하는 것은 봐야할것 같으므로)
    */

//    @GetMapping("/main-2")
//    public String main_2(){
//        return "redirect:http://localhost:8081/main_2.jsp";
//    }
//
//    @GetMapping("/main-3")
//    public String main_3(){
//        return "redirect:http://localhost:8081/main_3.jsp";
//    }
//
//    @GetMapping("/result-page")
//    public String result_page(){
//        return "redirect:http://localhost:8081/Result_Page.jsp";
//    }

}