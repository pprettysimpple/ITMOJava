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
        toMarkdown(result, "*");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "<silny>", "<\\silny>");
    }

    @Override
    public void toTex(StringBuilder result) {
        toTex(result, "texxt{", "}");
    }
}
