using System;

class Program
{
    static void Main()
    {
        Console.Write("Entrez le nombre 1 : ");
        int n1 = int.Parse(Console.ReadLine());

        Console.Write("Entrez le nombre 2 : ");
        int n2 = int.Parse(Console.ReadLine());

        if (n1 == n2)
            Console.WriteLine("Les nombres sont égaux");
        else
            if (n1 > n2)
            Console.WriteLine($"{n1} est le plus grand nombre");
        else
            Console.WriteLine($"{n2} est le plus grand nombre");
    }
}
