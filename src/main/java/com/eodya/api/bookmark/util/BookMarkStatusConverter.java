package com.eodya.api.bookmark.util;

import com.eodya.api.bookmark.domain.BookMarkStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookMarkStatusConverter implements Converter<String, BookMarkStatus> {

    @Override
    public BookMarkStatus convert(String status) {
        return BookMarkStatus.from(status);
    }
}
