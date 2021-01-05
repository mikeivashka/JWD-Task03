package by.epam.tc.document.builder;

import by.epam.tc.document.Node;
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
        Tag result = new Tag();
        result.self = xml;
        Matcher matcher = Pattern.compile("<([\\w._-]+[^>]+)>").matcher(xml);
        result.isOpening = matcher.matches();
        if (result.isOpening) {
            result.relatedNode = new Node();
            result.relatedNode.setName(TagService.parseTagName(xml));
            TagService.parseAttributes(xml)
                    .forEach((key, value) -> result.relatedNode.addAttribute(key, value));
        }
        return result;
    }

    public int endPosition() {
        return selfStartPosition + self.length();
    }
}
