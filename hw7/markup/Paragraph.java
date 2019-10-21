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
}
