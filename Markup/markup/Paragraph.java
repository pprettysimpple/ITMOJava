package markup;

import java.util.List;

public class Paragraph extends AbstractMarkupElement implements InList {
    public Paragraph(List<InParagraph> elements) {
        super(elements);
    }

    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "", "");
    }
}
