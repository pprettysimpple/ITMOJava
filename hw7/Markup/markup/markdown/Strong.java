package markup.markdown;

import markup.AbstractMarkupElement;
import markup.Markable;

import java.util.List;

public class Strong extends AbstractMarkupElement {
    public Strong(List<Markable> elements) {
        super(elements, "__", "__");
    }
}
