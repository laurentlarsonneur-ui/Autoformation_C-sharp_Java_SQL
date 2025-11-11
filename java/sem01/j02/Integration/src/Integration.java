import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class Integration {
    public record ResultatsIteration(int somme, double moyenne, int min, int max) {}

    public static int lireEntier(Scanner scanner, String msg)
    {
        int nombre = 0;
        boolean saisieValide;


        do
        {
            saisieValide = false;
            try {
                System.out.print(msg);
                nombre = scanner.nextInt();
                saisieValide = true;
            }   catch (InputMismatchException e) {
                System.out.println("Erreur : Vous devez entrer un nombre entier valide.");
                scanner.nextLine(); // Nettoie le buffer pour éviter une boucle infinie
            }
        }
        while (!saisieValide);

        return (nombre);
    }


    static int[] saisie() {
        int[] tableau;
        Scanner sc = new Scanner(System.in);


        int n = lireEntier(sc, "Entrez le nombre de valeurs : ");
        tableau = new int[n];

        for (int i = 0; i < tableau.length; i++) {
            String chaine = "Entrez la valeur n°" + (i+1) + " : ";
            tableau[i] = lireEntier(sc, chaine);
        }
        sc.close();

        return tableau;
   }

    public static ResultatsIteration analyserTableau(int[] tableau) {
            int somme = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int valeur : tableau) {
                somme += valeur;
                if (valeur < min) {
                    min = valeur;
                }
                if (valeur > max) {
                    max = valeur;
                }
            }
            double moyenne = (double) somme / tableau.length;
            return new ResultatsIteration(somme, moyenne, min, max);
        }

    public static void trieTableau(int[] tableau) {
        // Tri à bulles
        boolean inversion;
        do
        {
            inversion = false;
            for (int i = 0; i < tableau.length - 1; i++)
            {
                if (tableau[i] > tableau[i + 1])
                {
                    int tmp = tableau[i + 1];
                    tableau[i + 1] = tableau[i];
                    tableau[i] = tmp;
                    inversion = true;
                }
            }
        } while (inversion);
    }

    // Gestion des exceptions

    public static void main(String[] args) {
        int[] notes = saisie();
        System.out.println("Tableau : " + Arrays.toString(notes));

        ResultatsIteration resultats = analyserTableau(notes);
        System.out.println("Somme : " + resultats.somme());
        System.out.println("Moyenne : " + resultats.moyenne());
        System.out.println("Max : " + resultats.min());
        System.out.println("Max : " + resultats.max());

        trieTableau(notes);
        System.out.println("Tri : " + Arrays.toString(notes));
    }
}