package chicken;

import chicken.Chicken;

public class Creator {

    private static Chicken pollo;

    public static void main(String[] args) {
        try {
            new Chicken(null) {
                @Override
                protected void finalize() throws Throwable {
                    pollo = this;
                }
            };
        } catch (NullPointerException ignore) {}

        while (pollo == null) {
            System.gc();
        }
        pollo.ask();
    }
}
