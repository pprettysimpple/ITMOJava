import markup.*;
import markup.types.Emphasis;
import markup.types.Strikeout;
import markup.types.Strong;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Paragraph paragraph = new Paragraph(List.of(
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
        StringBuilder res = new StringBuilder();
        paragraph.toMarkdown(res);
        System.out.println(res.toString());
    }
}
