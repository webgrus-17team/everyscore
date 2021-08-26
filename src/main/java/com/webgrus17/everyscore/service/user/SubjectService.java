package com.webgrus17.everyscore.service.user;

import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.subject.SubjectRepository;
import com.webgrus17.everyscore.web.dto.SubjectSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Transactional
    public long save(final SubjectSaveDto subjectSaveDto){
        subjectRepository.save(subjectSaveDto.toEntity1());
        subjectRepository.save(subjectSaveDto.toEntity2());
        return subjectRepository.save(subjectSaveDto.toEntity3()).getId();
    }

    @Transactional
    public Subject findByName(String subjectName, String professorName, String testType) {
        // 문제가 없다면 무조건 하나의 과목만 들어갈 것
        List<Subject> subjects = subjectRepository.findBySubjectNameAndProfessorNameAndTestType(subjectName, professorName, testType);

        // 해당 과목명, 교수명을 지닌 과목의 id를 return
        return subjects.get(0);
    }

}
