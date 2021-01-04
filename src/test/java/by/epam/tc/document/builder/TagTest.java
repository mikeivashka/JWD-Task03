package by.epam.tc.document.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TagTest extends Assertions {

    @Test
    void findNextTagTest() {
        Tag tag = Tag.findTag("<note>", 0);
        assertNotNull(tag);
        assertTrue(tag.isOpening());
        assertNotNull(tag.getRelatedNode());
    }

    @Test
    void findNextTagWithAttributesTest() {
        Tag tag = Tag.findTag("<note attr=\"val\">", 0);
        assertNotNull(tag);
        assertTrue(tag.isOpening());
        assertNotNull(tag.getRelatedNode());
    }

    @Test
    void findNextTagClosingTest() {
        Tag tag = Tag.findTag("</note>", 0);
        assertNotNull(tag);
        assertFalse(tag.isOpening());
        assertNull(tag.getRelatedNode());
    }

    @Test
    void findNextTagMultilineTest() {
        Tag tag = Tag.findTag("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<note>", 0);
        assertNotNull(tag);
    }

    @Test
    void findNextTagStartPositionTest() {
        Tag tag = Tag.findTag("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<note>", 0);
        assertEquals(39, tag.getSelfStartPosition());
    }
}