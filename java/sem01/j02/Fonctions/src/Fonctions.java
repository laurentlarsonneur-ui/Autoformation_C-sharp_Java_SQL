//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Fonctions {
    static int somme(int[] tab) {
        int total = 0;
        for (int x : tab) total += x;
        return total;
        }

    public static void main(String[] args) {
        int[] valeurs = {4, 6, 8};
        System.out.println("Somme = " + somme(valeurs));
        }
    }
