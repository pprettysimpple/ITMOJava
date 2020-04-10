package search;

public class BinarySearch {

    static int iterative(int[] arr, int x) {
        int l = 0, r = arr.length;
        //inv: [l, r]
        while (l < r) {
            int m = l + (r - l) / 2;
            if (arr[m] > x) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        // Post: l == r and ans in [l, r] => l == r == ans
        return l;
    }

    static int rec(int[] arr, int x, int l, int r) {
        if (l >= r) {
            return l;
        }
        int m = l + (r - l) / 2;
        if (arr[m] > x) {
            return rec(arr, x, m + 1, r);
        } else {
            return rec(arr, x, l, m);
        }
    }

    static int recursive(int[] arr, int x) {
        return rec(arr, x, 0, arr.length);
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] arr = new int[args.length - 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(args[i + 1]);
        }
        System.out.println(iterative(arr, x));
    }
}
