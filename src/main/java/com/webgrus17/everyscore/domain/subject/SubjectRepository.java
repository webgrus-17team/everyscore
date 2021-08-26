package com.webgrus17.everyscore.domain.subject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    // https://ppomelo.tistory.com/155?category=908484
    // JPA 메소드 이름 쿼리 생성 링크

    List<Subject> findBySubjectNameAndProfessorName(String subjectName, String professorName);
}
