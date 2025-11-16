/* Mini-projet : Calculatrice
🎯 Objectif :  Créer une calculatrice console (C# et Java) avec gestion d’erreurs et boucle utilisateur.

Application console permettant :
* Choix d’une opération (addition, soustraction, multiplication, division)
* Saisie de deux nombres
* Affichage du résultat
* Gestion des erreurs (division par zéro, saisie invalide)
* Boucle pour continuer tant que l’utilisateur le souhaite
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculatrice {
    private static String Saisie(Scanner scanner, String msg, String type, String contrainte)
    {
        String s = "";
        boolean saisieValide;

        do
        {
            saisieValide = false;
            try {
                System.out.print(msg);
                s = scanner.nextLine();
                switch (type) {
                    case "char":
                        if((s.length()!=1)||(!contrainte.contains(s))) throw new InputMismatchException("Saisie incorrecte.");
                        break;
                    case "double":
                        Double.parseDouble(s);
                        break;
                    default:
                        break;
                }
                saisieValide = true;
            }   catch (InputMismatchException e) {
                System.out.println("Erreur : Entrée incorrecte 1.");
            }   catch (NumberFormatException e) {
                System.out.println("Erreur : Entrée incorrecte 2.");
            }   catch (NullPointerException e) {
                System.out.println("Erreur : Entrée incorrecte 3.");
            }
        }
        while (!saisieValide);

        return (s);
    }

    private static double Calculer(double a, char op, double b) {
        return switch(op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> ((b != 0) ? (a / b) : Double.NaN);
            default -> Double.NaN;
        };
    }

    public static void main(String[] args) {
        char o,rep;
        double n1, n2, r;
        boolean autre;

        Scanner sc = new Scanner(System.in);
        System.out.println("Bonjour");

        do
        {
            autre = false;
            o = Saisie(sc,"\nChoisissez une opération (+, -, *, /) :", "char", "+-*/").charAt(0);
            n1 = Double.parseDouble(Saisie(sc,"Choisissez le nombre 1 :", "double", ""));
            n2 = Double.parseDouble(Saisie(sc,"Choisissez le nombre 2 :", "double", ""));
            r = Calculer(n1, o, n2);
            System.out.println("Le résultat est " + r);
            rep = Saisie(sc,"\nVoulez vous faire une autre opération (o/n) ?", "char", "onON").charAt(0);
            if (rep=='o') autre = true;
        }
        while (autre);
        System.out.println("\nAu revoir !");

        sc.close();
    }
}