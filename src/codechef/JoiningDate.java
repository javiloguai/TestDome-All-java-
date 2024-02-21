package codechef;

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class JoiningDate
{
    public static void main(String[] args) {
        // System.in and System.out are input and output streams, respectively.

        Map<Integer,Integer> dist1 = new LinkedHashMap<>();
        Map<Integer,Integer> dist2 = new LinkedHashMap<>();

        // Read the number of test casese.
        int T =1;
        while (T-- > 0) {
            // Read the numbers a and b.
            int Ncandidates = 14;
            int coward =3;
            int moved = 0;

                for (int i = 1; i <= Ncandidates; i++) {
                    int j = i;
                    int day = (i / 5);

                    if (i % 5 > 0) {
                        day++;
                    }
                    if (j >= coward){
                        j++;
                    }
                    int day2 = (j / 5);;

                    if (j % 5 > 0) {
                        day2++;
                    }
                    if(day!=day2){
                        moved++;
                    }
                }

            System.out.println(moved);
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}