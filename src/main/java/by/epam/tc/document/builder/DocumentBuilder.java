package by.epam.tc.document.builder;

import by.epam.tc.document.entity.Document;

import java.text.ParseException;

public interface DocumentBuilder {
    Document build() throws ParseException;
}
