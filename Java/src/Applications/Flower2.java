package Applications;

public class Flower2 {

    private String name;
    private String color;
    private int size;
    private boolean hasBloomed;
    private int flowerPopulation;

    public Flower2 (String name, String inColor, int inSize, boolean inHasBloomed) {
        this.name = name;
        color = inColor;
        size = inSize;
        hasBloomed = inHasBloomed;
    }

    public void grow () {
        size++; // size goes up by 1
    }

    public void wither () {
        size--;
    }

    // setter
    public void setSize (int inSize) {
        size = Math.max(inSize, 0); // inSize = 100
    }

    public int getSize () {
        return size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isHasBloomed() {
        return hasBloomed;
    }

    public void setHasBloomed(boolean hasBloomed) {
        this.hasBloomed = hasBloomed;
    }

    public int getFlowerPopulation() {
        return flowerPopulation;
    }

    public void setFlowerPopulation(int flowerPopulation) {
        this.flowerPopulation = flowerPopulation;
    }
}
