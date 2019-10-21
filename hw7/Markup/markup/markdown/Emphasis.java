package markup.markdown;

import markup.AbstractMarkupElement;
import markup.Markable;

import java.util.List;

public class Emphasis extends AbstractMarkupElement {
    public Emphasis(List<Markable> elements) {
        super(elements, "*", "*");
    }
}
