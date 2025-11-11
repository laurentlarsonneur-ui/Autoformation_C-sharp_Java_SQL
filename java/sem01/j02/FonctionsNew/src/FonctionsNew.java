public class FonctionsNew {
    static int somme(int[] tab) {
        int total = 0;
        for (int x : tab) total += x;
        return total;
    }

    static double moyenne(int[] tab) {
        return (double)somme(tab)/ tab.length;
    }

    public static void main(String[] args) {
        int[] valeurs = {4, 6, 9};
        System.out.println("Somme = " + somme(valeurs));
        System.out.println("Moyenne = " + moyenne(valeurs));
    }
}
