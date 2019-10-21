package markup.types;

import markup.AbstractMarkupElement;
import markup.Markable;

import java.util.List;

public class Emphasis extends AbstractMarkupElement {
    public Emphasis(List<Markable> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkup(result, "*", "*");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toMarkup(result, "<silny>", "<\\silny>");
    }

    @Override
    public void toTex(StringBuilder result) {
        toMarkup(result, "texxt{", "}");
    }
}
