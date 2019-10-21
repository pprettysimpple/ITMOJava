package markup;

import java.util.List;

public abstract class AbstractMarkupElement implements Markable {
    private List<Markable> elements;

    protected AbstractMarkupElement(List<Markable> elements) {
        this.elements = elements;
    }

    protected void toMarkup(StringBuilder result, String leftBorder, String rightBorder) {
        result.append(leftBorder);
        for (Markable cur : elements) {
            cur.toMarkdown(result);
        }
        result.append(rightBorder);
    }
}
