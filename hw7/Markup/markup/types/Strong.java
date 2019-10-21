package markup.types;

import markup.AbstractMarkupElement;
import markup.Markable;

import java.util.List;

public class Strong extends AbstractMarkupElement {
    public Strong(List<Markable> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "__");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "<SUPERsilny>", "<\\SUPERsilny>");
    }

    @Override
    public void toTex(StringBuilder result) {
        toTex(result, "teXXXt{", "}");
    }
}
