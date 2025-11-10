using System;

class Program
{
    static void Main()
    {
        int[] notes = { 12, 15, 9, 18, 14 };
        int somme = 0;

        for (int i = 0; i < notes.Length; i++)
            somme += notes[i];

        double moyenne = (double)somme / notes.Length;
        Console.WriteLine($"Moyenne = {moyenne}");
    }
}
