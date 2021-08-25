package com.webgrus17.everyscore.service.user;

import com.webgrus17.everyscore.domain.subject.SubjectRepository;
import com.webgrus17.everyscore.web.dto.SubjectSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Transactional
    public Long save(final SubjectSaveDto subjectSaveDto){
        return subjectRepository.save(subjectSaveDto.toEntity()).getId();
    }
}
