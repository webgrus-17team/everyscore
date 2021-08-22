package com.webgrus17.everyscore.rest.controller;

import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.subject.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    private SubjectRepository subjectRepository;


    // 오류나서 우선 주석
    //첫 글 작성 post
//    @RequestMapping(value="/start", method= RequestMethod.POST)
//    public ResponseEntity<?> postStart(@ResponseBody Subject subject){
//    subjectRepository.save(subject);
//    return new ResponseEntity<>(("{}", HttpStatus.CREATED));
//    }

}