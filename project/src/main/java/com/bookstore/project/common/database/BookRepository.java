package com.bookstore.project.common.database;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String> {
    BookEntity findByIsbnEquals(String isbn);
    List<BookEntity> findAllByTitleIgnoreCase(String title);
    List<BookEntity> findAllByAuthorIdsContains(String authorId);

    String deleteByIsbnEquals(String isbn);
}
