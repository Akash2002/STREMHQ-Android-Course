import kotlin.jvm.internal.SpreadBuilder;

/**
 *
 * <h1> Hello World - Variables </h1>
 *
 * In Hello World, we explore the different variable types and learn how to print
 * variables out to the console by concatenating strings together. 
 *
 * @author akashveerappan
 * @version 1.0
 * @since Mon Aug 10, 2020
 *
 */

public class HelloWorld {

    public static void main (String[] args) { // runner method

        int age = 70000;
        String name = "Kurzner";
        System.out.println(name + " is " + age + " years old");

        // Variable types

        int maxInt = Integer.MAX_VALUE;
        int minInt = Integer.MIN_VALUE;

        //floating point -> it has decimals

        float maxFloat = Float.MAX_VALUE; // this is a smaller decimal
        float minFloat = Float.MIN_VALUE;

        double maxDouble = Double.MAX_VALUE; // this is our bigger decimal
        double minDouble = Double.MIN_VALUE;

        String hello = "Hello there"; // sequence of characters

        char letter = 'A';

        boolean isButtonClicked = false;

        System.out.println(maxInt + " | " + minInt);
        System.out.println(maxDouble + " | " + minDouble);
        System.out.println(maxFloat + " | " + minFloat);

        int datatype = 4; // reserved java keyword
        Integer classType = 4;

        System.out.println(datatype + " | " + classType);


    }

}
