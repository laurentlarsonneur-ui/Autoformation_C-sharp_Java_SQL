import java.util.Scanner;

public class MoyenneNew {
    public static void main(String[] args) {
        int[] notes ;
        Scanner sc = new Scanner(System.in);

        System.out.print("Entrez le nombre de notes : ");
        int n = sc.nextInt();
        notes = new int[n];
        for (int i = 0; i < notes.length; i++) {
            System.out.print("Entrez la note " + (i+1) + " : ");
            notes[i] = sc.nextInt();
        }

        int somme = 0, mini = 999, maxi = -1;
        for (int note : notes) {
            somme += note;
            if(mini>note) {
                mini = note;
            }
            if(maxi<note) {
                maxi = note;
            }
        }

        double moy = (double)somme / notes.length;
        System.out.println("Moyenne = " + moy);
        System.out.println("Mini = " + mini);
        System.out.println("Maxi = " + maxi);

        sc.close();
    }
}