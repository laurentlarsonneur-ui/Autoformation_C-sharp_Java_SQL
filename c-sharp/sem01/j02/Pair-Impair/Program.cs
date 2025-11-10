using System;

class Program
{
    static void Main()
    {
        Console.Write("Entrez un nombre : ");
        int x = int.Parse(Console.ReadLine());

        if (x % 2 == 0)
            Console.WriteLine($"{x} est pair");
        else
            Console.WriteLine($"{x} est impair");
    }
}
