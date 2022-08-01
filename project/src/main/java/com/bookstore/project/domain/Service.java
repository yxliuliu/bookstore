package com.bookstore.project.domain;

import com.bookstore.project.common.database.AuthorEntity;
import com.bookstore.project.common.database.AuthorRepository;
import com.bookstore.project.common.database.BookEntity;
import com.bookstore.project.common.database.BookRepository;
import com.bookstore.project.common.exceptions.RecordNotFoundException;
import com.bookstore.project.common.util.TimeProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final TimeProvider timeProvider;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public void addBook(Book book) {
        LocalDateTime processedTime = timeProvider.getCurrentDateTime();

        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setYear(book.getYear());
        bookEntity.setPrice(book.getPrice());
        bookEntity.setGenre(book.getGenre());

        bookEntity.setAuthorIds(getAuthorIds(book, processedTime));
        bookEntity.setCreatedDate(processedTime);
        bookEntity.setModifiedDate(processedTime);
        bookRepository.save(bookEntity);
    }

    public void updateBook(String isbn, Book book) throws Exception {
        LocalDateTime processedTime = timeProvider.getCurrentDateTime();

        BookEntity bookEntity = Optional.ofNullable(bookRepository.findByIsbnEquals(isbn)).orElseThrow(() -> new RecordNotFoundException("Book not found"));
        bookEntity.setTitle(book.getTitle());
        bookEntity.setYear(book.getYear());
        bookEntity.setPrice(book.getPrice());
        bookEntity.setGenre(book.getGenre());

        bookEntity.setAuthorIds(getAuthorIds(book, processedTime));
        bookEntity.setModifiedDate(processedTime);
        bookRepository.save(bookEntity);
    }

    @Transactional
    public void deleteBook(String isbn) {
        bookRepository.deleteByIsbnEquals(isbn);
    }

    public Book getBook(String isbn) throws Exception{
        BookEntity bookEntity = bookRepository.findByIsbnEquals(isbn);
        return mapEntityToBook(Optional.ofNullable(bookEntity).orElseThrow(() -> new RecordNotFoundException("Book not found")));
    }

    public List<Book> getBooksByTitle(String title){
        List<BookEntity> bookEntityList = bookRepository.findAllByTitleIgnoreCase(title);

        return bookEntityList.stream().map(bookEntity -> mapEntityToBook(bookEntity)).collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthor(Author author){
        AuthorEntity authorEntity = authorRepository.findByNameIgnoreCaseAndBirthdayEquals(author.getName(), LocalDate.parse(author.getBirthday(), DateTimeFormatter.ISO_LOCAL_DATE));

        List<BookEntity> bookEntityList = new ArrayList<>();
        if (authorEntity != null) {
            bookEntityList = bookRepository.findAllByAuthorIdsContains("#" + authorEntity.getId().toString() + "#");
        }

        return bookEntityList.stream().map(bookEntity -> mapEntityToBook(bookEntity)).collect(Collectors.toList());
    }

    private String getAuthorIds(Book book, LocalDateTime processedTime) {

        List<String> authorIdList = new ArrayList<>();

        book.getAuthors().forEach(author -> {
            String name = author.getName();
            LocalDate birthday = LocalDate.parse(author.getBirthday(), DateTimeFormatter.ISO_LOCAL_DATE);

            AuthorEntity authorEntity = authorRepository.findByNameIgnoreCaseAndBirthdayEquals(name, birthday);
            if (authorEntity == null) {
                authorEntity = new AuthorEntity(null, name, birthday, processedTime);
                authorRepository.save(authorEntity);
            }

            authorIdList.add(authorEntity.getId().toString());
        });

        return "#" + String.join("#", authorIdList) + "#";
    }

    private Book mapEntityToBook(BookEntity bookEntity) {
        List<String> authorIdStrList = Stream.of(bookEntity.getAuthorIds().split("#")).collect(Collectors.toList());
        authorIdStrList.remove(0);
        List<Long> authorIdList = authorIdStrList.stream().map(Long::parseLong).collect(Collectors.toList());

        List<AuthorEntity> authorEntityList = authorRepository.findAllByIdIn(authorIdList);
        List<Author> authors = authorEntityList.stream().map(authorEntity -> {
            return new Author(authorEntity.getName(), authorEntity.getBirthday().format(DateTimeFormatter.ISO_LOCAL_DATE));
        }).collect(Collectors.toList());

        return new Book(
                bookEntity.getIsbn(),
                bookEntity.getTitle(),
                authors,
                bookEntity.getYear(),
                bookEntity.getPrice(),
                bookEntity.getGenre()
        );
    }
}
