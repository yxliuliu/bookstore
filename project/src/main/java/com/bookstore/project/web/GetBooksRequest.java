package com.bookstore.project.web;

import com.bookstore.project.domain.Author;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetBooksRequest {
    String title;
    Author author;
}
