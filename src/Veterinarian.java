public class Veterinarian {
    public void accept(String petName) {
        throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public String heal() {
        throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public static void main(String[] args) {
        Veterinarian veterinarian = new Veterinarian();
        veterinarian.accept("Barkley");
        veterinarian.accept("Mittens");
        System.out.println(veterinarian.heal());
        System.out.println(veterinarian.heal());
    }
}