import java.sql.*;
// Penser à ajouter le driver mssql-jdbc-13.2.1.jre11.jar
// dans Project Structure/Library/New Library/Java


public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=master;"
                + "encrypt=false;"
                + "trustServerCertificate=true;"
                + "user=sa;"
                + "password=gyuezo+fk5;";

        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("✅ Connexion réussie !");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT @@VERSION");
            while (rs.next()) {
                System.out.println("Version SQL Server : " + rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println("❌ Erreur de connexion : " + e.getMessage());
        }
    }
}