package by.epam.tc.document;

import io.bretty.console.tree.TreePrinter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Document {
    private final Node root;

    @Override
    public String toString() {
        return TreePrinter.toString(root);
    }
}
