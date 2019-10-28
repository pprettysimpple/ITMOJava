package markup;

import java.util.List;

public abstract class AbstractMarkupElement {
    private List<InParagraph> elements;

    AbstractMarkupElement(List<InParagraph> elements) {
        this.elements = elements;
    }

    protected void toMarkdown(StringBuilder result, String border) {
        result.append(border);
        for (InParagraph cur : elements) {
            cur.toMarkdown(result);
        }
        result.append(border);
    }

    protected void toHtml(StringBuilder result, String leftBorder, String rightBorder) {
        result.append(leftBorder);
        for (InParagraph cur : elements) {
            cur.toHtml(result);
        }
        result.append(rightBorder);
    }
}
