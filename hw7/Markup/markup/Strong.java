package markup;

import java.util.List;

public class Strong extends AbstractMarkupElement implements InParagraph {
    public Strong(List<InParagraph> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "__");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "<strong>", "</strong>");
    }
}
