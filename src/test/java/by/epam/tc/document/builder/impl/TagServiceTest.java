package by.epam.tc.document.builder.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TagServiceTest extends Assertions {

    @Test
    void findNextTagTest() {
        Tag tag = TagService.findTag("<note>", 0);
        assertNotNull(tag);
        assertTrue(tag.isOpening());
        assertNotNull(tag.getRelatedNode());
    }

    @Test
    void findNextTagWithAttributesTest() {
        Tag tag = TagService.findTag("<note attr=\"val\">", 0);
        assertNotNull(tag);
        assertTrue(tag.isOpening());
        assertNotNull(tag.getRelatedNode());
    }

    @Test
    void findNextTagClosingTest() {
        Tag tag = TagService.findTag("</note>", 0);
        assertNotNull(tag);
        assertFalse(tag.isOpening());
        assertNull(tag.getRelatedNode());
    }

    @Test
    void findNextTagMultilineTest() {
        Tag tag = TagService.findTag("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<note>", 0);
        assertNotNull(tag);
    }

    @Test
    void findNextTagStartPositionTest() {
        Tag tag = TagService.findTag("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<note>", 0);
        assertEquals(39, tag.getSelfStartPosition());
    }
}