package com.bookstore.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
public class Author {
    @NotEmpty
    String name;
    @NotEmpty
    String birthday;
}
