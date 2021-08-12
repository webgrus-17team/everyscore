package com.webgrus17.everyscore.rest.controller;

//첫글 작성 페이지에 대한 api

import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.subject.SubjectRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/start")
public class StartRestController {

    private SubjectRepository subjectRepository;

    public StartRestController(SubjectRepository subjectRepository){
        this.subjectRepository=subjectRepository;
    }

    @PostMapping
    public ResponseEntity<?> postStart(@RequestBody Subject subject){
        subjectRepository.save(subject);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }
}
