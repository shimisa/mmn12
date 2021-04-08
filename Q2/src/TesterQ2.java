

public class TesterQ2 {

    public static void test() {

        BigInt i = new BigInt("0");
        BigInt w = new BigInt("-5");

        System.out.println(i.toString());
        System.out.println(w.toString());
        System.out.println("\n");

        BigInt e = i.plus(w);
        System.out.println(e.toString());

    }
}
