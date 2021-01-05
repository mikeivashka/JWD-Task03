package by.epam.tc.document.builder.impl;

import by.epam.tc.document.builder.impl.XMLDocumentBuilder;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class XMLDocumentBuilderTest extends Assertions {

    @Test
    @SneakyThrows
    void documentBuilderParseStringSimpleXMLTest() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><notes>" +
                "<note>" +
                "<to kek=\"3\"><to2>Tove</to2></to>" +
                "<from>Jani</from>" +
                "<heading important=\"false\">Reminder</heading>" +
                "<body>Don't forget me this weekend!</body>" +
                "</note>" +
                "<note>" +
                "<to kek=\"5\"><to2>Move</to2></to>" +
                "<from>Mike</from>" +
                "<heading important=\"true\">Friendly Reminder</heading>" +
                "<body>Wash your hands!</body>" +
                "</note>" +
                "</notes>";
        XMLDocumentBuilder documentBuilder = new XMLDocumentBuilder(xml);
        System.out.println(documentBuilder.build().toString());
    }

    @Test
    @SneakyThrows
    void documentBuilderParseFileTest() {
        File file = new File("src/main/resources/example.xml");
        XMLDocumentBuilder documentBuilder = new XMLDocumentBuilder(file);
        System.out.println(documentBuilder.build().toString());
    }
}