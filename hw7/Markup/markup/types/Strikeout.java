package markup.types;

import markup.AbstractMarkupElement;
import markup.Markable;

import java.util.List;

public class Strikeout extends AbstractMarkupElement {
    public Strikeout(List<Markable> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "~");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "<viydi, razbiynik>", "<\\viydi, razbiynik>");
    }

    @Override
    public void toTex(StringBuilder result) {
        toTex(result, "viydi, razbiynik{", "}");
    }
}
