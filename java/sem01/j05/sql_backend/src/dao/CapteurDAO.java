// Un DAO (Data Access Object) est une classe spécialisée dans l'accès aux données
// Gère la communication avec SQL Server (CRUD).
// CRUD = Create (créer), Read (lire), Update (mettre à jour) et Delete (supprimer)

package dao;
import model.Capteur;
import java.sql.*;
import java.util.LinkedList;

public class CapteurDAO {
    private final String _connectionString;

    public CapteurDAO(String connectionString) {
        _connectionString = connectionString;
    }

    public void Insert(Capteur c) {
        try (Connection conn = DriverManager.getConnection(_connectionString)) {
            System.out.println("Connexion OK");
            String sql = "INSERT INTO Capteurs (nom, type, unite) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNom());
            ps.setString(2, c.getType());
            ps.setString(3, c.getUnite());
            ps.executeUpdate();
            System.out.println("Connexion OK - SQL entry created");
        } catch (SQLException e) {
            System.out.println("SQL Error");
        }
    }

    public LinkedList<Capteur> FindAll() {
        LinkedList<Capteur> mesCap = new LinkedList<>(); // Liste vide de capteurs
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Établir la connexion
            conn = DriverManager.getConnection(_connectionString);
            System.out.println("Connexion OK");

            // Créer une requête
            stmt = conn.createStatement();
            String sql = "SELECT id, nom, type, unite FROM Capteurs";

            // Exécuter la requête
            rs = stmt.executeQuery(sql);

            // Lire les résultats
            while (rs.next()) {
                Capteur cap = new Capteur();
                cap.setId(rs.getInt("id"));
                cap.setNom(rs.getString("nom"));
                cap.setType(rs.getString("type"));
                cap.setUnite(rs.getString("unite"));
                mesCap.addLast(cap);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error");
        } finally {
            // Fermer les ressources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Connexion OK - SQL read complete");
        return mesCap;
    }

    public Capteur findById(int id) {
        Capteur cap = new Capteur();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Établir la connexion
            conn = DriverManager.getConnection(_connectionString);
            System.out.println("Connexion OK");

            // Créer une requête
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Capteurs WHERE Id=" + id;
            System.out.println(sql);

            // Exécuter la requête
            rs = stmt.executeQuery(sql);

            // Lire les résultats
            while (rs.next()) {
                cap.setId(rs.getInt("id"));
                cap.setNom(rs.getString("nom"));
                cap.setType(rs.getString("type"));
                cap.setUnite(rs.getString("unite"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Connexion OK - SQL read complete");
        return cap;
    }

    public void Update(Capteur c) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Établir la connexion
            conn = DriverManager.getConnection(_connectionString);
            System.out.println("Connexion OK");

            // Créer une requête
            stmt = conn.createStatement();
            String sql = "UPDATE Capteurs SET nom = '" + c.getNom().replace("'", "''")
                    + "', type = '" + c.getType().replace("'", "''")
                    + "', unite = '" + c.getUnite().replace("'", "''")
                    + "'  WHERE id = " + c.getId();
            System.out.println(sql);

            // Exécuter la requête
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Connexion OK - SQL entry modified");
    }

    public void Delete(int id) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Établir la connexion
             conn = DriverManager.getConnection(_connectionString);
             System.out.println("Connexion OK");

            // Créer une requête
            stmt = conn.createStatement();
            String sql = "DELETE FROM Capteurs WHERE id = " + id;
            System.out.println(sql);

            // Exécuter la requête
            stmt.executeUpdate(sql);
            } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Connexion OK - SQL entry deleted");
    }

}
