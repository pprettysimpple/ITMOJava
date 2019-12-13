package markup.markup;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import markup.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public abstract class ListTest extends AbstractTest {
    @Override
    protected void test() {
        final Paragraph paragraph1 = new Paragraph(List.of(
                new Strong(List.of(
                        new Text("1"),
                        new Strikeout(List.of(
                                new Text("2"),
                                new Emphasis(List.of(
                                        new Text("3"),
                                        new Text("4")
                                )),
                                new Text("5")
                        )),
                        new Text("6")
                ))
        ));
        final String paragraph1Markup = "<strong>1<s>2<em>34</em>5</s>6</strong>";

        final Paragraph paragraph2 = new Paragraph(List.of(new Strong(List.of(
                new Text("sdq"),
                new Strikeout(List.of(new Emphasis(List.of(new Text("r"))), new Text("vavc"))),
                new Text("zg")))
        ));
        final String paragraph2Markup = "<strong>sdq<s><em>r</em>vavc</s>zg</strong>";

        test(paragraph1, paragraph1Markup);
        test(paragraph2, paragraph2Markup);

        final ListItem li1 = new ListItem(List.of(new Paragraph(List.of(new Text("1")))));
        final ListItem li2 = new ListItem(List.of(new Paragraph(List.of(new Text("2")))));
        final ListItem pli1 = new ListItem(List.of(paragraph1));
        final ListItem pli2 = new ListItem(List.of(paragraph2));

        final ListItem nestedUl = new ListItem(List.of(new UnorderedList(List.of(li1, li2))));
        final String nestedUlMarkup = list("ul", "1", "2");

        test(new UnorderedList(List.of(li1)), list("ul", "1"));
        test(new UnorderedList(List.of(li2)), list("ul", "2"));
        test(new UnorderedList(List.of(pli1)), list("ul", paragraph1Markup));
        test(new UnorderedList(List.of(pli2)), list("ul", paragraph2Markup));
        test(new UnorderedList(List.of(li1, li2)), nestedUlMarkup);
        test(new UnorderedList(List.of(pli1, pli2)), list("ul", paragraph1Markup, paragraph2Markup));
        test(new UnorderedList(List.of(nestedUl)), list("ul", nestedUlMarkup));

        final ListItem nestedOl = new ListItem(List.of(new OrderedList(List.of(li1, li2))));
        final String nestedOlMarkup = "<ol><li>1</li><li>2</li></ol>";
        test(new OrderedList(List.of(li1)), list("ol", "1"));
        test(new OrderedList(List.of(li2)), list("ol", "2"));
        test(new OrderedList(List.of(pli1)), list("ol", paragraph1Markup));
        test(new OrderedList(List.of(pli2)), list("ol", paragraph2Markup));
        test(new OrderedList(List.of(li1, li2)), nestedOlMarkup);
        test(new OrderedList(List.of(pli1, pli2)), list("ol", paragraph1Markup, paragraph2Markup));
        test(new OrderedList(List.of(nestedOl)), list("ol", nestedOlMarkup));

        test(new UnorderedList(List.of(nestedUl, nestedOl)), list("ul", nestedUlMarkup, nestedOlMarkup));
        test(new OrderedList(List.of(nestedUl, nestedOl)), list("ol", nestedUlMarkup, nestedOlMarkup));

        test(
                new UnorderedList(List.of(nestedUl, nestedOl, pli1, pli2)),
                list("ul", nestedUlMarkup, nestedOlMarkup, paragraph1Markup, paragraph2Markup)
        );
        test(
                new OrderedList(List.of(nestedUl, nestedOl, pli1, pli2)),
                list("ol", nestedUlMarkup, nestedOlMarkup, paragraph1Markup, paragraph2Markup)
        );
    }

    private String list(final String type, final String... items) {
        return "<" + type + ">" + Stream.of(items).map(item -> "<li>" + item + "</li>").collect(Collectors.joining()) + "</" + type + ">";
    }

    protected abstract void test(final Paragraph paragraph, final String expected);

    protected abstract void test(final UnorderedList list, final String expected);

    protected abstract void test(final OrderedList list, final String expected);
}
