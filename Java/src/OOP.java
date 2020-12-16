public class OOP {
    // OOP - Object Oriented Programming
    public static void main(String[] args) {
        Student akash = new Student(); // allocate memory and create the object

        akash.work();
        akash.takeBreak();
        akash.study();
        akash.takeBreak();

        sum(1,2); // has the value returned by this method

    }

    // method -> input and output
    public static void sum (int a, int b) {
        if (a == b) {
            System.out.println(2 * (a + b));
        } else {
            System.out.println(a + b);
        }
    }

}
