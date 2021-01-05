package by.epam.tc.document.builder.impl;

import by.epam.tc.document.builder.DocumentBuilder;
import by.epam.tc.document.entity.impl.XMLDocument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class XMLDocumentBuilder implements DocumentBuilder {
    private static final String TAG_NOT_FOUND_MESSAGE = "Invalid XML, couldn't find tag after pos:";
    private final String xml;

    public XMLDocumentBuilder(String xml) {
        this.xml = xml;
    }

    public XMLDocumentBuilder(File file) throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            xml = new BufferedReader(fileReader).lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new IOException("Error occurred while opening XML file");
        }
    }

    public XMLDocument build() throws ParseException {
        Deque<Tag> openingTags = new LinkedList<>();
        Tag currentTag = TagService.findTag(xml, 0);
        if (currentTag == null) {
            throw new ParseException(TAG_NOT_FOUND_MESSAGE, 0);
        }
        XMLDocument document = new XMLDocument(currentTag.getRelatedNode());
        openingTags.add(currentTag);
        int currentPosition = currentTag.endPosition();
        while (!openingTags.isEmpty()) {
            currentTag = TagService.findTag(xml, currentPosition);
            if (currentTag == null) {
                throw new ParseException(TAG_NOT_FOUND_MESSAGE, currentPosition);
            }
            currentPosition = currentTag.endPosition();
            if (currentTag.isOpening()) {
                assert openingTags.peek() != null;
                openingTags.peek().getRelatedNode().addChildNode(currentTag.getRelatedNode());
                openingTags.push(currentTag);
            } else {
                Tag openingTag = openingTags.pop();
                if (!openingTag.getRelatedNode().hasChildNodes()) {
                    openingTag.getRelatedNode()
                            .setContent(xml.substring(openingTag.endPosition(), currentTag.getSelfStartPosition()));
                }
            }
        }
        return document;
    }
}
