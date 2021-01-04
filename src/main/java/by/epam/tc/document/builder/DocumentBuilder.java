package by.epam.tc.document.builder;

import by.epam.tc.document.Document;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DocumentBuilder {
    private final String xml;

    public DocumentBuilder(File file) throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            xml = new BufferedReader(fileReader).lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new IOException("Error occurred while opening XML file");
        }
    }

    public Document build() {
        Deque<Tag> stack = new ArrayDeque<>();
        Tag currentTag = Tag.findTag(xml, 0);
        if (currentTag == null) throw new IllegalArgumentException("Invalid xml");
        Document document = new Document(currentTag.getRelatedNode());
        stack.add(currentTag);
        int currentPosition = currentTag.endPosition();
        while (!stack.isEmpty()) {
            currentTag = Tag.findTag(xml, currentPosition);
            if (currentTag == null) throw new IllegalArgumentException("Invalid xml");
            currentPosition = currentTag.endPosition();
            if (currentTag.isOpening()) {
                stack.peek().getRelatedNode().addChildNode(currentTag.getRelatedNode());
                stack.push(currentTag);
            } else {
                Tag openingTag = stack.pop();
                if (!openingTag.getRelatedNode().hasChildNodes()) {
                    openingTag.getRelatedNode().setContent(xml.substring(openingTag.endPosition(), currentTag.getSelfStartPosition()));
                }
            }
        }
        return document;
    }


}
