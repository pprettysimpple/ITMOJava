package markup;

import java.util.List;

public class Paragraph implements Markable {
    private List<Markable> elements;

    public Paragraph(List<Markable> elements) {
        this.elements = elements;
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        for (Markable cur : elements) {
            cur.toMarkdown(result);
        }
    }

    @Override
    public void toHtml(StringBuilder result) {
        for (Markable cur : elements) {
            cur.toHtml(result);
        }
    }

    @Override
    public void toTex(StringBuilder result) {
        for (Markable cur : elements) {
            cur.toTex(result);
        }
    }
}
