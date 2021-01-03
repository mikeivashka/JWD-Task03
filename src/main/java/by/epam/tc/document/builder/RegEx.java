package by.epam.tc.document.builder;

public enum RegEx {
    TAG("<([\\w._-]+[/]?)>"), OPENING_TAG("<([\\w._-]+)>"), CLOSING_TAG("<([\\\\w._-]+[/])");

    String content;

    RegEx(String regEx) {
        content = regEx;
    }
}
