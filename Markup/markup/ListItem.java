package markup;

import markup.*;

import java.util.List;

public class ListItem {
    private List<InList> elements;

    public ListItem(List<InList> elements) {
        this.elements = elements;
    }

    public void toMarkdown(StringBuilder result) { //don't use it
        throw new NoSuchMethodError("Don't use ListItem.toMarkdown()");
    }

    public void toHtml(StringBuilder result) {
        result.append("<li>");
        for (InList element : elements) {
            element.toHtml(result);
        }
        result.append("</li>");
    }
}
