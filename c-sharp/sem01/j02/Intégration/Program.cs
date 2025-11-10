using System;
using System.Text;


/*Sujet: Statistiques sur une série de nombres

Crée un programme qui :
Demande à l’utilisateur combien de valeurs il veut saisir.
Lit les valeurs dans un tableau.
Calcule : somme, moyenne, min, max, et affiche le tout.

💡 Variante :
Trie les valeurs (tri à bulles).
Gère les erreurs de saisie (non-numérique).*/

class Program
{
    static int mini = 999, maxi = -1, somme = 0;
    static double moyenne = 0.0;

    static int entier(string msg)
    {
        string s = "";
        int nombre = 0;
        bool bad = true;

        do
        {
            bad = true;
            try
            {
                Console.Write(msg);
                s = Console.ReadLine();
                nombre = int.Parse(s);
                bad = false;
            }
            catch (FormatException) { Console.WriteLine("{0}: Bad Format", s); }
            catch (OverflowException) { Console.WriteLine("{0}: Overflow", s); }
        }
        while (bad);
        return (nombre);
    }
    static void saisie(int[] tab)
    {
        int n = tab.Length;
        for (int i = 0; i < n; i++)
        {
            tab[i] = entier("Saisie de la valeur " + (i + 1) + " : ");
            somme += tab[i];
            if (tab[i] < mini) mini = tab[i];
            if (tab[i] > maxi) maxi = tab[i];
        }
        moyenne = (double)somme / n;
    }
    static void trie(int[] tab)
    {
        int n = tab.Length;
        bool inversion = false;
        do
        {
            inversion = false;
            for (int i = 0; i < n - 1; i++)
            {
                if (tab[i] > tab[i + 1])
                {
                    int tmp = tab[i + 1];
                    tab[i + 1] = tab[i];
                    tab[i] = tmp;
                    inversion = true;
                }
            }
        } while (inversion);
    }
    static void Main()
    {
        int x = entier("Combien de valeurs voulez vous saisir : ");
        var valeurs = new int[x];

        saisie(valeurs);
        Console.Write("Etat du tableau : ");
        Console.WriteLine(string.Join(", ", valeurs));

        Console.WriteLine($"Somme = {somme}");
        Console.WriteLine($"Moyenne = {moyenne}");
        Console.WriteLine($"Mini = {mini}");
        Console.WriteLine($"Maxi = {maxi}");

        trie(valeurs);
        Console.Write("Tableau trié : ");
        Console.WriteLine(string.Join(", ", valeurs));
    }
}
