package com.bookstore.project.common.util;

import java.time.LocalDateTime;

public interface TimeProvider {
    LocalDateTime getCurrentDateTime();
}
