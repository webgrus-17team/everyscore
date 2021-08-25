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
    public Long save(final SubjectSaveDto subjectSaveDto){
        return subjectRepository.save(subjectSaveDto.toEntity()).getId();
    }

    @Transactional
    public Subject findByName(String Subject_name, String Professor_name) {
        // 문제가 없다면 무조건 하나의 과목만 들어갈 것
        List<Subject> subjects = subjectRepository.findBySubject_nameAndProfessor_name(Subject_name, Professor_name);

        // 해당 과목명, 교수명을 지닌 과목의 id를 return
        return subjects.get(0);
    }

}
