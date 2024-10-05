import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Term implements Comparable<Term> {
    private String query; // stores the string query for each term
    private long weight; // stores the weight for each term

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null || weight < 0)
            throw new IllegalArgumentException("");
        this.query = query; // initializes query
        this.weight = weight; // initializes weight
    }

    // method used to compare weight, called in byReverseWeightOrder
    private static class CompareWeight implements Comparator<Term> {
        public int compare(Term term1, Term term2) {
            return Long.compare(term2.weight, term1.weight); // compares in
            // reverse order
        }
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new CompareWeight();
    }

    private static class CompareQuery implements Comparator<Term> {
        private int k; // stores integer representing first k characters

        // constructor to take in the r value
        public CompareQuery(int r) {
            k = r; // initializes the instance var to store the integer
        }

        public int compare(Term term1, Term term2) {
            // if (term1.query.length() > term2.query.length())

            for (int i = 0; i < k; i++) {
                // corner case
                if (i >= term1.query.length() && i >= term2.query.length())
                    return 0;
                // corner case
                if (i >= term1.query.length()) //&& i <= term2.query.length())
                    return -1;
                // corner case
                if (i >= term2.query.length()) // i <= term1.query.length() &&
                    return 1;

                if (term1.query.charAt(i) > term2.query.charAt(i))
                    return 1;
                if (term1.query.charAt(i) < term2.query.charAt(i))
                    return -1;
                // returns int > 0 if obj1 > obj2
                // returns int < 0 if obj1 < obj2
                // returns 0 otherwise
            }
            return 0;
        }
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0)
            throw new IllegalArgumentException("");
        return new CompareQuery(r);
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return weight + "\t" + query;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Term test = new Term("GTGG", 4);
        Term test2 = new Term("GTGG", 9);
        int k = test.compareTo(test2);
        StdOut.println(k);
        StdOut.println(byReverseWeightOrder().compare(test, test2));
        StdOut.println(byPrefixOrder(3).compare(test, test2));
        CompareQuery x = new CompareQuery(6);
        StdOut.println(x.compare(test, test2));
    }

}
