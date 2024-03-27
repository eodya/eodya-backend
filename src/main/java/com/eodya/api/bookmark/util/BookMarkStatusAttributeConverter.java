package com.eodya.api.bookmark.util;

import com.eodya.api.bookmark.domain.BookMarkStatus;
import jakarta.persistence.AttributeConverter;

public class BookMarkStatusAttributeConverter implements AttributeConverter<BookMarkStatus, String> {

    @Override
    public String convertToDatabaseColumn(BookMarkStatus bookMarkStatus) {
        return bookMarkStatus.getDescription();
    }

    @Override
    public BookMarkStatus convertToEntityAttribute(String status) {
        return BookMarkStatus.from(status);
    }
}
