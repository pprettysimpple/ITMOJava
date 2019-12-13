package markup;

import java.util.List;

public class Strikeout extends AbstractMarkupElement implements InParagraph {
    public Strikeout(List<InParagraph> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "~");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "<s>", "</s>");
    }
}
