package markup;

public interface Markable {
    void toMarkdown(StringBuilder result);
    void toHtml(StringBuilder result);
    void toTex(StringBuilder result);
}
