import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key>
            comparator) {
        cornerCase(a, key, comparator);
        int tracker = -1; // stores the index value
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int compare = comparator.compare(key, a[mid]);
            if (compare < 0) {
                hi = mid - 1; // shrinks top bound
            }
            else if (compare > 0) lo = mid + 1;
            else {
                hi = mid - 1;
                tracker = mid; // stores index value of last match
            }
        }
        return tracker; // returns the index of the last match

    }


    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        cornerCase(a, key, comparator);
        int tracker = -1; // stores the index value
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int compare = comparator.compare(key, a[mid]);
            if (compare < 0) {
                hi = mid - 1; // shrinks upper bound
            }
            else if (compare > 0) lo = mid + 1;
            else {
                lo = mid + 1;
                tracker = mid;
            }
        }
        return tracker; // returns the index of the last match
    }

    // helper method to check each corner case
    private static <Key> void cornerCase(Key[] a, Key key, Comparator<Key>
            comparator) {
        if (a == null)
            throw new IllegalArgumentException("");
        for (int i = 0; i < a.length; i++) {
            if (a[i] == null)
                throw new IllegalArgumentException("");
        }
        if (key == null) {
            throw new IllegalArgumentException("");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        String[] a = { "A", "B", "B", "B", "B", "B", "B" };
        Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
        int index1 = BinarySearchDeluxe.firstIndexOf(a, "B", comparator);
        int index2 = BinarySearchDeluxe.lastIndexOf(a, "A", comparator);
        StdOut.println(index1);
        StdOut.println(index2);
    }
}
