package markup;

public class Text implements Markable {
    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        result.append(text);
    }

    @Override
    public void toHtml(StringBuilder result) {
        result.append(text);
    }

    @Override
    public void toTex(StringBuilder result) {
        result.append(text);
    }
}
