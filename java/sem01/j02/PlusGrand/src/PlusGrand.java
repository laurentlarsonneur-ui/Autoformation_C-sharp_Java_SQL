import java.util.Scanner;

public class PlusGrand {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez le nombre 1 : ");
        int n1 = sc.nextInt();

        System.out.print("Entrez le nombre 2 : ");
        int n2 = sc.nextInt();

        if (n1 > n2)
            System.out.println(n1 + " est plus grand");
        else
            System.out.println(n2 + " est plus grand");

        sc.close();
    }
}