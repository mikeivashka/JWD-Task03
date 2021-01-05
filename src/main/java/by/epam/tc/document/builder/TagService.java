package by.epam.tc.document.builder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TagService {

    private TagService() {
    }

    public static String parseTagName(String xml) {
        return xml.transform(s -> {
            int lastIndex = s.indexOf(" ", 1) > -1 ? s.indexOf(" ") : s.indexOf(">");
            s = s.substring(1, lastIndex);
            return s;
        });
    }

    public static Map<String, String> parseAttributes(String xml) {
        if (!xml.contains("=")) return Collections.emptyMap();
        Map<String, String> attributes = new HashMap<>();
        splitTagIntoAttributes(xml)
                .forEach(s -> {
                    Map.Entry<String, String> parsedAttributes = parseSingleAttribute(s);
                    attributes.put(parsedAttributes.getKey(), parsedAttributes.getValue());
                });
        return attributes;
    }

    public static Tag findTag(String xml, int beginIndex) {
        Matcher matcher = Pattern.compile("<([/]?[\\w._-]+[^>]+)>").matcher(xml);
        if (!matcher.find(beginIndex)) return null;
        String match = matcher.group();
        Tag nextTag = Tag.fromString(match);
        nextTag.setSelfStartPosition(matcher.start());
        return nextTag;
    }

    private static List<String> splitTagIntoAttributes(String xml) {
        return Arrays.asList(xml.transform(s -> s
                .substring(s.indexOf(' '), s.length() - 1)
                .trim())
                .replaceAll("[ +]?=[ +]?[\"]", "=\"")
                .split("\"[ +]]"));
    }

    private static Map.Entry<String, String> parseSingleAttribute(String attribute) {
        String[] keyValue = attribute.split("=");
        System.out.println(Arrays.toString(keyValue));
        keyValue[1] = keyValue[1].replace("\"", "").trim();
        return new AbstractMap.SimpleEntry<>(keyValue[0].trim(), keyValue[1]);
    }
}
