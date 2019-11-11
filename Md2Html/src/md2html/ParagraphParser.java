package md2html;

class ParagraphParser {
    private StringBuilder source;

    public ParagraphParser(StringBuilder source) {
        this.source = source;
    }

    public void toHtml(StringBuilder result) {
        result.append("<p>");
        new TextParser(source).toHtml(result);
        result.append("</p>");
    }
}
