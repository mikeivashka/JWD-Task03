package by.epam.tc.document.builder;

import by.epam.tc.document.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DocumentBuilder {

    Pattern tag = Pattern.compile(RegEx.TAG.content);
    Pattern closingTag = Pattern.compile(RegEx.CLOSING_TAG.content);
    Pattern openingTag = Pattern.compile(RegEx.OPENING_TAG.content);

    public Document parse(File file) throws FileNotFoundException {
        String content = new BufferedReader(new FileReader(file))
                .lines()
                .parallel()
                .collect(Collectors.joining("\n"));
        return parse(content);
    }

    private Document parse(String xml) {
        return new Document();
    }
}
