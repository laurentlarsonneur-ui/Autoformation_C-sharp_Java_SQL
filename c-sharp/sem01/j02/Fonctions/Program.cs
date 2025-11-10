using System;

class Program
{
    static int Somme(int[] tab)
    {
        int total = 0;
        foreach (int x in tab) total += x;
        return total;
    }
    static double moyenne(int[] t)
    {
        return ((double)Somme(t) / t.Length);
    }
    static void Main()
    {
        int[] valeurs = { 1, 3, 6 };
        Console.WriteLine("Somme = " + Somme(valeurs));
        Console.WriteLine($"Moyenne = {moyenne(valeurs)}");
    }
}
