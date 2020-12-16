import java.util.ArrayList;
import java.util.Scanner;

public class ForLoops {

    public ForLoops() {
    }

    public static void main(String[] args) {

        // shopping list: raspberry, dragon-fruit, kiwi

        String[] shoppingList = new String[] {"raspberry", "dragon-fruit", "kiwi"}; // array - length: 3

        double[] moneySpentOnShopping = new double[12]; // 12 items -> 0

        // built in array -> have a set number of items

        moneySpentOnShopping[0] = 123.34; // january
        moneySpentOnShopping[1] = 119.58; // february
        // while -> condition
        // for -> iterations

        for (int i = 0; i < shoppingList.length; i++) {
            System.out.println(shoppingList[i]);
        }

        for (int j = 0; j < moneySpentOnShopping.length; j++) {
            System.out.println(moneySpentOnShopping[j]);
        }

        //i += 2 => i = i + 2

        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
        }

        for (int i = 1; i < 100; i += 2) {
            System.out.println(i);
        }

        Scanner scanner = new Scanner(System.in);
        String variable = "";

        ArrayList<String> packingList = new ArrayList<>(); // size is dynamic

        packingList.add("Computer"); // keeps adding
        packingList.add("Mattress");

        for (int i = 0; i < packingList.size(); i++) {
            System.out.println(packingList.get(i));
        }

        for (String item: packingList) {
            System.out.println(item);
        }

    }

}
