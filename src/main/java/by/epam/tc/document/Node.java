package by.epam.tc.document;

import java.util.List;

class Node {
    private List<Node> childNodes;
    private boolean isLeafNode;
    private List<Attribute> attributes;
    private String text;
}
