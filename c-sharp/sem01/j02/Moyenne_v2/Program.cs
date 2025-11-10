using System;

class Program
{
    static void Main()
    {
        int[] notes = { 0, 0, 0, 0, 0 };
        int somme = 0, mini = 999, maxi = -1;

        for (int i = 0; i < notes.Length; i++)
        {
            Console.WriteLine($"Entrez la note {i + 1} : ");
            notes[i] = int.Parse(Console.ReadLine());
        }

        for (int i = 0; i < notes.Length; i++)
        {
            somme += notes[i];
            if (notes[i] < mini) mini = notes[i];
            if (notes[i] > maxi) maxi = notes[i];
        }

        double moyenne = (double)somme / notes.Length;
        Console.WriteLine($"Moyenne = {moyenne}");
        Console.WriteLine($"Mini = {mini}");
        Console.WriteLine($"Maxi = {maxi}");
    }
}
