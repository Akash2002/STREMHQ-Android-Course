package Applications;

public interface Groomable {
    default void hello () {
        System.out.println("HI");
    }
    static void hello2 () { // not overridden
        System.out.println("HITHIE");
    }
}
