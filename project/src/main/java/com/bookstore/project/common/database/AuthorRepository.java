package com.bookstore.project.common.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    AuthorEntity findByNameIgnoreCaseAndBirthdayEquals(String name, LocalDate birthday);
    List<AuthorEntity> findAllByIdIn(List<Long> IdList);
}
