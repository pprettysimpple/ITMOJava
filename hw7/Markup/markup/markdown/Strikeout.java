package markup.markdown;

import markup.AbstractMarkupElement;
import markup.Markable;

import java.util.List;

public class Strikeout extends AbstractMarkupElement {
    public Strikeout(List<Markable> elements) {
        super(elements, "~", "~");
    }
}
