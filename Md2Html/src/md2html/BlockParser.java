package md2html;

class BlockParser {
    private StringBuilder source;

    BlockParser(StringBuilder source) {
        this.source = source;
    }

    private boolean isHeader(StringBuilder text) {
        int pos = 0;
        while (pos < text.length() && text.charAt(pos) == '#') {
            pos++;
        }
        return pos > 0 && pos < text.length() && text.charAt(pos) == ' ';
    }

    public void toHtml(StringBuilder result) {
        if (isHeader(source)) {
            new HeaderParser(source).toHtml(result);
        } else {
            new ParagraphParser(source).toHtml(result);
        }
    }
}
