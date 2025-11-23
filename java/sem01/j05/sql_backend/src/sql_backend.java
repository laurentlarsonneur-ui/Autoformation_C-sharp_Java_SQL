import java.util.Scanner;
import java.sql.*;
// Penser à ajouter le driver mssql-jdbc-13.2.1.jre11.jar
// dans Project Structure/Library/New Library/Java

// Structure du code
// Program / UI  →  Services  →  DAO  →  SQL Server

public class sql_backend {
    private static char menu(Scanner scanner) {
        System.out.println("\nChoose one option :");
        System.out.println(" c = Create");
        System.out.println(" r = Read");
        System.out.println(" u = Update");
        System.out.println(" d = Delete");
        System.out.println("\n q = Quit");
        return (scanner.nextLine().charAt(0));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char choix;

        System.out.println("*** SQL backend test ***");
        do {
            choix = menu(sc);
            switch (choix) {
                case 'c':
                    //Create();
                    break;
                case 'r':
                    //var liste = dao.FindAll();
                    //foreach (Capteur cap in liste) cap.display();
                    break;
                case 'u':
                    //Change();
                    break;
                case 'd':
                    //Eliminate();
                    break;
                case 'q':
                    System.out.println("End of program");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println(choix);
        } while (choix != 'q');
    }
}