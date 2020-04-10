package search;

public class BinarySearchShift {
    static int getInt(String num) {
        return Integer.parseInt(num);
    }

    // PRE: \exists k in [2, ..., n] : [arr_k, arr_{k+1}, ..., arr_n, arr_1, arr_2, ... arr_{k-1}]
    // or   [arr_1, arr_2, ... arr_n]
    // or   empty
    // and  \forall i < j: arr_i < arr_j
    static int iterative(String[] arr) {
        if  (arr.length == 0 || getInt(arr[0]) <= getInt(arr[arr.length - 1])) {
            // empty or [arr_1, ..., arr_n]
            return 0;
            // POST R = 0
        }
        // k' = n - k + 1
        // k' in [1, n - 1]
        int l = 1, r = arr.length - 1;
        // INV: k' in [l, r]
        // and r - l > r' - l'
        // and l <= r
        while (l < r) {
            int m = l + (r - l) / 2;
            // m in [l, r - 1]
            if (getInt(arr[m]) >= getInt(arr[0])) {
                // k in [m + 1, r]
                l = m + 1;
                // l' = m + 1 => l' > l => r - l and l' <= r'
            } else {
                // k in [l, m]
                r = m;
                // r' == m < r => r' - l' < r - l and l' <= r'
            }
        }
        // INV_END: k' in [l, r], l == r ==> k' == l == r
        return l;
    }
    // POST: R = k' = n - k + 1

    // PRE: \exists k in [2, ..., n] : [arr_k, arr_{k+1}, ..., arr_n, arr_1, arr_2, ... arr_{k-1}]
    // and  \forall i < j: arr_i < arr_j
    // and  RET = k' = n - k + 1 in [l, r]
    // and  0 < l < r < n
    static int rec(String[] arr, int l, int r) {
        if (l == r) {
            // k' in [l, r], l == r => k == l == r
            // POST: R = l = k' = n - k + 1 from sequence
            return l;
        }
        int m = l + (r - l) / 2;
        // m in [l, r - 1]
        if (getInt(arr[m]) >= getInt(arr[0])) {
            l = m + 1;
            // l' = m + 1 > l => r' - l' < r - l and l' <= r'
        } else {
            r = m;
            // r' == m < r => r' - l' < r - l and l' <= r'
        }
        // r' - l' < r - l and r' <= r and l' >= l
        return rec(arr, l, r);
    }
    // POST: R = k' = n - k + 1

    // PRE: \exists k in [2, ..., n] : [arr_k, arr_{k+1}, ..., arr_n, arr_1, arr_2, ... arr_{k-1}]
    // or   [arr_1, arr_2, ... arr_n]
    // or   empty
    // and  \forall i < j: arr_i < arr_j
    static int recursive(String[] arr) {
        if (arr.length == 0 || getInt(arr[0]) <= getInt(arr[arr.length - 1])) {
            // [arr_1, arr_2, ... arr_n] or empty
            return 0;
            // POST: R = 0
        } else {
            // \exists k : [arr_k, arr_{k+1}, ..., arr_n, arr_1, arr_2, ... arr_{k-1}]
            // and  \forall i < j: arr_i < arr_j
            return rec(arr, 1, arr.length - 1);
            // R = n - k + 1 in [1, n - 1]
        }
    }

    public static void main(String[] args) {
        System.out.println(recursive(args));
    }
}
