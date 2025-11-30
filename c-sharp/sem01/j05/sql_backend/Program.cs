using Microsoft.Data.SqlClient;
using sql_backend.Util;
using sql_backend.DAO;
using sql_backend.Model;
using System;
// Ne pas oublier d'ajouter ce package NuGet à la solution ou au projet
// Structure du code
// Program / UI  →  Services  →  DAO  →  SQL Server

class Program
{
    static string connectionString = "Server=localhost,1440;Database=SupervisionDB;encrypt=false;trustServerCertificate=true;user=sa;password=gyuezo+fk5;";
    static ConsoleHelper con = new ConsoleHelper();
    static CapteurDAO dao = new CapteurDAO(connectionString);
    static char Menu()
    {
        Console.WriteLine("\nChoose one option :");
        Console.WriteLine(" c = Create");
        Console.WriteLine(" r = Read");
        Console.WriteLine(" u = Update");
        Console.WriteLine(" d = Delete");
        Console.WriteLine("\n q = Quit");
        return con.LireChoix('C', 'R', 'U', 'D', 'Q');
//        return Console.ReadLine();
    }
    static void Create()
    {
        Capteur cap = new Capteur();
        cap.Id = 0;
        cap.Nom = con.LireChaine("Enter name:");
        cap.Type = con.LireChaine("Enter type:");
        cap.Unite = con.LireChaine("Enter unit:");
        dao.Insert(cap);
    }
    static public void Change()
    {
        int MonId = con.LireEntier("Which Id should be modified?");
        Console.WriteLine("Searched index:" + MonId);
        Capteur cap = dao.findById(MonId);
        cap.display();
        string s = con.LireChaineOptionnelle("Enter name (hit enter to keep existing):");
        if (s != "") cap.Nom = s;
        s = con.LireChaineOptionnelle("Enter type (hit enter to keep existing):");
        if (s != "") cap.Type = s;
        s = con.LireChaineOptionnelle("Enter unit (hit enter to keep existing):");
        s = Console.ReadLine();
        if (s != "") cap.Unite = s;
        cap.display();
        dao.Update(cap);
    }
    static void Eliminate()
    {
        int MonId = con.LireEntier("Which Id should be deleted?");
        Console.WriteLine("Searched index:" + MonId);
        Capteur cap = dao.findById(MonId);
        cap.display();

        Boolean b = con.LireBoolean("Confirm deletion (y/n):");
        if (b) dao.Delete(MonId);
    }
    static void Main()
    {
        Console.WriteLine("*** SQL backend test ***");

        char choix;

        do
        {
            choix = Menu();
            switch (choix)
            {
                case 'C':
                    Create();
                    break;
                case 'R':
                    var liste = dao.FindAll();
                    foreach (Capteur cap in liste) cap.display();
                    break;
                case 'U':
                    Change();
                    break;
                case 'D':
                    Eliminate();
                    break;
                case 'Q':
                    Console.WriteLine("End of program");
                    break;
                default:
                    Console.WriteLine("Invalid choice");
                    break;
            }
        } while (choix != 'Q');
    }
}
