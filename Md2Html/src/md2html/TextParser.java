package md2html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextParser {
    private StringBuilder source;
    private static Map<String, Integer> markdownIndex;
    private static String[] html;
    private static String[] markdownTag;
    private static int tagCount;

    static {
        html = new String[]{"em", "strong", "em", "strong", "s", "code", "u", "a"};
        markdownTag = new String[]{"*", "**", "_", "__", "--", "`", "++", "["};
        tagCount = markdownTag.length;
        markdownIndex = new HashMap<>();
        for (int i = 0; i < tagCount; i++) {
            markdownIndex.put(markdownTag[i], i);
        }
        markdownIndex.put("]", tagCount - 1);
        markdownIndex.put("(", tagCount - 1);
        markdownIndex.put(")", tagCount - 1);
    }

    TextParser(StringBuilder source) {
        this.source = source;
    }

    private Integer getTagPosition(String cur) {
        Integer res = markdownIndex.get(cur);
        if (res == null) {
            res = markdownIndex.get(Character.toString(cur.charAt(0)));
        }
        return res;
    }

    public void toHtml(StringBuilder result) {
        int tagCount = html.length;
        IntList[] arr = new IntList[tagCount];
        for (int i = 0; i < tagCount; i++) {
            arr[i] = new IntList();
        }
        for (int i = 0; i < source.length(); i++) {
            String cur = source.substring(i, Math.min(i + 2, source.length()));
            if (source.charAt(i) == '\\') {
                i++;
                continue;
            }
            Integer pos = getTagPosition(cur);
            if (pos != null) {
                arr[pos].add(i);
            }
        }
        for (IntList intList : arr) {
            if (intList.getSize() % 2 == 1) {
                intList.pop();
            }
        }
        int[] pos = new int[tagCount];
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            String cur = source.substring(i, Math.min(i + 2, source.length()));
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
                    continue;
            }
            Integer needPos = getTagPosition(cur);
            if (needPos == null || arr[needPos].getSize() == 0) {
                result.append(c);
            } else {
                cur = markdownTag[needPos];
                String tagName = html[needPos];
                int tagSize = cur.length();
                boolean usualChar = false;
                if (tagName.equals("a")) {
                    if (arr[needPos].getSize() - pos[needPos] < 4) {
                        result.append(c);
                        continue;
                    }
                    int p1 = arr[needPos].get(pos[needPos]++);
                    int p2 = arr[needPos].get(pos[needPos]++);
                    int p3 = arr[needPos].get(pos[needPos]++);
                    int p4 = arr[needPos].get(pos[needPos]++);

                    if (p2 + 1 == p3) {
                        result.append("<a href=\'");
                        result.append(source.substring(p3 + 1, p4));
                        result.append("\'>");
                        new TextParser(new StringBuilder(source.substring(p1 + 1, p2))).toHtml(result);
                        result.append("</a>");
                        i = p4;
                        continue;
                    }
                    usualChar = true;
                }
                if (usualChar) {
                    result.append(c);
                    continue;
                }
                boolean isOpen = ((arr[needPos].getSize() - pos[needPos]) % 2 == 0);
                String tmp = "<" + (isOpen ? "" : "/") + tagName + ">";
                result.append(tmp);
                i += tagSize - 1;
                pos[needPos]++;
            }
        }
    }
}
