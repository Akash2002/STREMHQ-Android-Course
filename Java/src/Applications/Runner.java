package Applications;

public class Runner {
    public static void main (String[] args) {
        Flower2 rose = new Flower2("Rose", "Red", 10, false);
        Flower2 sunflower = new Flower2("Sunflower", "Yellow", 5, true);
        sunflower.grow();

        rose.setSize(100);
        System.out.println(rose.getSize());
    }
}
