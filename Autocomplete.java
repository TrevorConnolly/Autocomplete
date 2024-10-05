import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {
    private Term[] terms; // holds the instance array of terms

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) throw new IllegalArgumentException("");
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null) // checks each array value
                throw new IllegalArgumentException("");
        }
        Term[] terms2 = new Term[terms.length];
        for (int i = 0; i < terms.length; i++)
            terms2[i] = terms[i]; // makes copy array
        this.terms = terms2;
        Arrays.sort(terms2); // naturally sorts the array
    }

    // Returns all terms that start with the given prefix,
    // in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("");
        Term x = new Term(prefix, 0);
        int r = prefix.length();
        Comparator<Term> comparator1 = Term.byPrefixOrder(r);
        int first = BinarySearchDeluxe.firstIndexOf(terms, x, comparator1);
        int last = BinarySearchDeluxe.lastIndexOf(terms, x, comparator1);
        int length = last - first + 1; // calculates length
        Term[] newTerms = new Term[length];
        if (first == -1) { // checks for search miss
            Term[] array = new Term[0];
            return array;
        }
        for (int i = 0; i < length; i++) {
            newTerms[i] = terms[first + i]; // generates new matches array
        }
        Comparator<Term> comparator2 = Term.byReverseWeightOrder();
        Arrays.sort(newTerms, comparator2);
        return (newTerms);
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("");
        Term x = new Term(prefix, 0);
        int r = prefix.length();
        Comparator<Term> comparator = Term.byPrefixOrder(r);
        int first = BinarySearchDeluxe.firstIndexOf(terms, x, comparator);
        int last = BinarySearchDeluxe.lastIndexOf(terms, x, comparator);
        if (first == -1 || last == -1) // checks for search misses
            return 0;
        int numMatches = last - first + 1;
        return numMatches;
    }

    // unit testing (required)
    public static void main(String[] args) {
        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}
