package markup;

import java.util.List;

abstract class AbstractList implements InList {
    private List<ListItem> elements;

    AbstractList(List<ListItem> elements) {
        this.elements = elements;
    }

    public void toHtml(StringBuilder result, String leftBorder, String rightBorder) {
        result.append(leftBorder);
        for (ListItem cur : elements) {
            cur.toHtml(result);
        }
        result.append(rightBorder);
    }
}
