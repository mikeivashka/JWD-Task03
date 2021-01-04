package by.epam.tc.document.builder;

enum RegEx {
    TAG("<([/]?[\\w._-]+[^>]+)>"), OPENING_TAG("<([\\w._-]+[^>]+)>");

    String content;

    RegEx(String regEx) {
        content = regEx;
    }
}
