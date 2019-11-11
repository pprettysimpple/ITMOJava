package md2html;

import java.util.HashMap;
import java.util.Map;

public class TextParser {
    private StringBuilder source;
    private static Map<String, Integer> markdownIndex;
    private static String[] html;
    private static String[] markdownTag;
    private static int tagCount;
    static {
        html = new String[]{"em", "strong", "em", "strong", "s", "code", "u"};
        markdownTag = new String[]{"*", "**", "_", "__", "--", "`", "++"};
        tagCount = markdownTag.length;
        markdownIndex = new HashMap<>();
        for (int i = 0; i < tagCount; i++) {
            markdownIndex.put(markdownTag[i], i);
        }
    }

    TextParser(StringBuilder source) {
        this.source = source;
    }

    private Integer getTagPosition(int pos) {
        String cur = source.substring(
                pos,
                Math.min(pos + 2, source.length())
        );
        Integer res = markdownIndex.get(cur);
        if (res == null) {
            res = markdownIndex.get(Character.toString(cur.charAt(0)));
        }
        return res;
    }

    public void toHtml(StringBuilder result) {
        int[] count = new int[tagCount];
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (Character.isLetter(c)) {
                continue;
            }
            if (c == '\\') {
                i++;
                continue;
            }
            Integer pos = getTagPosition(i);
            if (pos != null) {
                count[pos]++;
                i += markdownTag[pos].length() - 1;
            }
        }
        for (int i = 0; i < tagCount; i++) {
            if (count[i] % 2 == 1) {
                count[i]--;
            }
        }
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
                case '<':
                    result.append("&lt;");
                    continue;
                case '>':
                    result.append("&gt;");
                    continue;
                case '&':
                    result.append("&amp;");
                    continue;
                case '\\':
                    if (i + 1 < source.length()) {
                        result.append(source.charAt(++i));
                    }
                    continue;
            }
            Integer needPos = getTagPosition(i);
            if (needPos == null || count[needPos] == 0) {
                result.append(c);
            } else {
                String tagName = html[needPos];
                boolean isOpen = ((count[needPos]) % 2 == 0);
                String cur = "<" + (isOpen ? "" : "/") + tagName + ">";
                result.append(cur);
                i += markdownTag[needPos].length() - 1;
                count[needPos]--;
            }
        }
    }
}
