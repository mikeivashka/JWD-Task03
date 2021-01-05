package by.epam.tc.document;

import io.bretty.console.tree.PrintableTreeNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Node implements PrintableTreeNode {
    private final List<Node> childNodes = new LinkedList<>();
    private final Map<String, String> attributes = new HashMap<>();
    private String name;
    private String content;

    public boolean hasChildNodes() {
        return !childNodes.isEmpty();
    }

    public void addChildNode(Node child) {
        childNodes.add(child);
    }

    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    @Override
    public String name() {
        return name +
                (attributes.isEmpty() ? "" : attributes.toString()) +
                ((content != null && !content.isEmpty()) ? " = " + content : "");
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return childNodes;
    }
}
