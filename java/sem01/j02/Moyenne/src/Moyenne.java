public class Moyenne {
    public static void main(String[] args) {
        int[] notes = {12, 15, 9, 18, 14};
        int somme = 0;

        for (int i = 0; i < notes.length; i++)
            somme += notes[i];

        double moyenne = (double)somme / notes.length;
        System.out.println("Moyenne = " + moyenne);
    }
}
