package by.epam.tc.document.entity.impl;

import by.epam.tc.document.entity.Document;
import by.epam.tc.document.entity.Node;
import io.bretty.console.tree.TreePrinter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class XMLDocument implements Document {
    private final Node root;

    @Override
    public String toString() {
        return TreePrinter.toString(root);
    }
}
