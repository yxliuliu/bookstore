package com.bookstore.project.common.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LocalTimeProvider implements TimeProvider {

    @Override
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
