package md2html;

import java.util.ArrayList;
import java.util.List;

public class TextParser {
    StringBuilder source;

    TextParser(StringBuilder source) {
        this.source = source;
    }

    public void toHtml(StringBuilder result) {
        List<String> markdown = new ArrayList<>(List.of("*", "**", "_", "__", "--", "`", "++"));
        String[] html = {"em", "strong", "em", "strong", "s", "code", "u"};
        int tagCount = html.length;
        IntList[] arr = new IntList[tagCount];
        for (int i = 0; i < tagCount; i++) {
            arr[i] = new IntList();
        }
        for (int i = 0; i < source.length(); i++) {
            String cur = source.substring(i, Math.min(i + 2, source.length()));
            int pos = markdown.indexOf(cur);
            if (pos != -1) {
                arr[pos].add(i++);
            } else {
                cur = cur.substring(0, 1);
                if (cur.equals("\\")) {
                    i++;
                    continue;
                }
                pos = markdown.indexOf(cur);
                if (pos != -1) {
                    arr[pos].add(i);
                }
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
            int needPos = -1;
            for (int j = 0; j < arr.length; j++) {
                if (pos[j] < arr[j].getSize()
                        && arr[j].get(pos[j]) == i) {
                    needPos = j;
                    break;
                }
            }
            if (needPos == -1) {
                result.append(c);
            } else {
                String tagName = html[needPos];
                int tagSize = markdown.get(needPos).length();
                boolean isOpen = ((arr[needPos].getSize() - pos[needPos]) % 2 == 0);
                String cur = "<" + (isOpen ? "" : "/") + tagName + ">";
                result.append(cur);
                i += tagSize - 1;
                pos[needPos]++;
            }
        }
    }
}
