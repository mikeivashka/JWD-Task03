package by.epam.tc.document.builder;

import by.epam.tc.document.entity.Node;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
class Tag {
    private int selfStartPosition;
    private String self;
    private boolean isOpening;
    private Node relatedNode;

    public static Tag fromString(String xml) {
        Tag parsingResult = new Tag();
        parsingResult.self = xml;
        Matcher matcher = Pattern.compile("<([\\w._-]+[^>]+)>").matcher(xml);
        parsingResult.isOpening = matcher.matches();
        if (parsingResult.isOpening) {
            parsingResult.relatedNode = new Node();
            parsingResult.relatedNode.setName(TagService.parseTagName(xml));
            TagService.parseAttributes(xml)
                    .forEach((key, value) -> parsingResult.relatedNode.addAttribute(key, value));
        }
        return parsingResult;
    }

    public int endPosition() {
        return selfStartPosition + self.length();
    }
}
