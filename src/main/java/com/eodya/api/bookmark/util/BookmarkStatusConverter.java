package com.eodya.api.bookmark.util;

import com.eodya.api.bookmark.domain.BookmarkStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BookmarkStatusConverter implements AttributeConverter<BookmarkStatus, String> {

    @Override
    public String convertToDatabaseColumn(BookmarkStatus bookMarkStatus) {
        return bookMarkStatus.getDescription();
    }

    @Override
    public BookmarkStatus convertToEntityAttribute(String status) {
        return BookmarkStatus.from(status);
    }
}
