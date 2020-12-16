package Applications;

/**
 * This is a test class that you can refer to if you want to learn more about the Flower class and its extra methods
 * @author Akash Veerappan
 * @version 1.0
 */

public class Flower {
    String color; // all public
    String name;
    boolean hasBloomed;
    int size;
    int number; // static

    Flower (String inColor, String inName, boolean inHasBloomed, int inSize, int inNumber) {
        color = inColor;
        name = inName;
        hasBloomed = inHasBloomed;
        size = inSize;
        number = inNumber;
        number++;
    }

    public void pollinate () {
        if (!hasBloomed) {
            System.out.println("Flower pollinating");
            hasBloomed = true;
        } else {
            System.out.println("Flower has already pollinated");
            hasBloomed = false;
        }
    }

    public void grow () {
        size++;
    }

    public void wither () {
        size--;
    }



}
