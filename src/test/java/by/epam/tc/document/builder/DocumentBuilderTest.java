package by.epam.tc.document.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class DocumentBuilderTest extends Assertions {

    @Test
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
        DocumentBuilder documentBuilder = new DocumentBuilder(xml);
        System.out.println(documentBuilder.build().toString());
    }

    @Test
    void documentBuilderParseFileTest() throws IOException {
        File file = new File("example.xml");
        DocumentBuilder documentBuilder = new DocumentBuilder(file);
        System.out.println(documentBuilder.build().toString());
    }
}