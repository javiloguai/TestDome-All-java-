package codechef;

import java.util.Scanner;

/* Name of the class has to be "Main" only if the class is public. */
class Lucky4
{
    public static void main(String[] args) {
        // System.in and System.out are input and output streams, respectively.
        Scanner in =new Scanner(System.in);

        // Read the number of test casese.
        int T = in.nextInt();
        String num = in.nextLine();
        while (T-- > 0) {

            num = in.nextLine();
            //for (int i==0 ; i<)
            int count = num.length() - num.replace("4", "").length();


            System.out.println(count);
        }
    }


}