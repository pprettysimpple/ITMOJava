package markup;

import java.util.List;

public class Paragraph extends AbstractMarkupElement {
    public Paragraph(List<Markable> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "", "");
    }

    @Override
    public void toTex(StringBuilder result) {
        toTex(result, "", "");
    }
}
