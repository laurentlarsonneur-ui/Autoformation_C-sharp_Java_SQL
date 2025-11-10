import java.util.Scanner;

public class PairImpair {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez un nombre : ");
        int x = sc.nextInt();

        if (x % 2 == 0)
            System.out.println(x + " est pair");
        else
            System.out.println(x + " est impair");

        sc.close();
    }
}
