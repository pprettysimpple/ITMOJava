public class SumHex {

    public static void main(String[] args) {
        int resultSum = 0;
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i];
            for (int left = 0; left < args[i].length(); left++) {
                int right = left;
                while (right < args[i].length() && !Character.isWhitespace(args[i].charAt(right))) {
                    right++;
                }
                if (right > left) {
                    if (left + 2 < right && args[i].substring(left, left + 2).toLowerCase().equals("0x")) {
                        resultSum += Integer.parseUnsignedInt(args[i].substring(left + 2, right), 16);
                    } else {
                        resultSum += Integer.parseInt(args[i].substring(left, right));
                    }
                    left = right - 1;
                }
            }
        }
        System.out.println(resultSum);
    }
}
