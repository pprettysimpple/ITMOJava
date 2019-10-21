package markup;

import java.util.List;

public abstract class AbstractMarkupElement implements Markable {
    private String leftBorder, rightBorder;
    private List<Markable> elements;

    protected AbstractMarkupElement(List<Markable> elements, String leftBorder, String rightBorder) {
        this.elements = elements;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        result.append(leftBorder);
        for (Markable cur : elements) {
            cur.toMarkdown(result);
        }
        result.append(rightBorder);
    }
}
