package markup;

import java.util.List;

public abstract class AbstractMarkupElement implements Markable {
    private List<Markable> elements;

    protected AbstractMarkupElement(List<Markable> elements) {
        this.elements = elements;
    }

    public void toMarkdown(StringBuilder result, String border) {
        result.append(border);
        for (Markable cur : elements) {
            cur.toMarkdown(result);
        }
        result.append(border);
    }

    public void toHtml(StringBuilder result, String leftBorder, String rightBorder) {
        result.append(leftBorder);
        for (Markable cur : elements) {
            cur.toHtml(result);
        }
        result.append(rightBorder);
    }

    public void toTex(StringBuilder result, String leftBorder, String rightBorder) {
        result.append(leftBorder);
        for (Markable cur : elements) {
            cur.toTex(result);
        }
        result.append(rightBorder);
    }
}
