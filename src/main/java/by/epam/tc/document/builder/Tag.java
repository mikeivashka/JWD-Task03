package by.epam.tc.document.builder;

import by.epam.tc.document.Node;
import lombok.Data;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
class Tag {
    @Setter
    private Integer selfStartPosition;
    private String self;
    private boolean isOpening;
    private Node relatedNode;

    public static Tag fromString(String tag) {
        Tag result = new Tag();
        result.self = tag;
        Matcher matcher = Pattern.compile(RegEx.OPENING_TAG.content).matcher(tag);
        result.isOpening = matcher.matches();
        if (result.isOpening) {
            result.relatedNode = Node.builder()
                    .name(parseTagName(tag))
                    .attributes(parseAttributes(tag))
                    .build();
        }
        return result;
    }

    private static String parseTagName(String xml) {
        return xml.transform(s -> {
            int lastIndex = s.indexOf(" ", 1) > -1 ? s.indexOf(" ") : s.indexOf(">");
            s = s.substring(1, lastIndex);
            return s;
        });
    }

    private static Map<String, String> parseAttributes(String xml) {
        if (!xml.contains("=")) return Collections.emptyMap();
        Map<String, String> attributes = new HashMap<>();
        Arrays.stream(xml.transform(s -> s
                .substring(s.indexOf(' '), s.length() - 1)
                .trim())
                .split(" ")
        )
                .forEach(s -> {
                    String[] keyValue = s.split("=");
                    keyValue[1] = keyValue[1].transform(val -> val.substring(1, val.length() - 1));
                    attributes.put(keyValue[0], keyValue[1]);
                });
        return attributes;
    }

    public static Tag findTag(String xml, int beginIndex) {
        Matcher matcher = Pattern.compile(RegEx.TAG.content).matcher(xml);
        if (!matcher.find(beginIndex)) return null;
        String match = matcher.group();
        Tag nextTag = Tag.fromString(match);
        nextTag.selfStartPosition = matcher.start();
        return nextTag;
    }

    public int endPosition() {
        return selfStartPosition + self.length();
    }

    public int startPosition() {
        return selfStartPosition;
    }
}
