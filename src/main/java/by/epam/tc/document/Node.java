package by.epam.tc.document;

import io.bretty.console.tree.PrintableTreeNode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Builder
@ToString
public class Node implements PrintableTreeNode {
    @Getter
    private final List<Node> childNodes = new LinkedList<>();
    @Getter
    @Setter
    private Map<String, String> attributes;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String content;

    public boolean hasChildNodes() {
        return !childNodes.isEmpty();
    }


    public void addChildNode(Node child) {
        childNodes.add(child);
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
