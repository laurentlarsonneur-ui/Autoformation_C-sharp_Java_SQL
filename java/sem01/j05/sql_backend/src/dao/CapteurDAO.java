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
        }
        System.out.println("Connexion OK - SQL read complete");
        return mesCap;
    }
}
//            public Capteur findById(int id)
//            {
//                Capteur cap = new Capteur();
//
//                using (SqlConnection conn = new SqlConnection(_connectionString))
//                {
//                    conn.Open();
//                    String sql = "SELECT * FROM Capteurs WHERE Id="+id;
//                    SqlCommand cmd = new SqlCommand(sql, conn);
//                    SqlDataReader reader = cmd.ExecuteReader();
//
//                    while (reader.Read())
//                    {
//                        cap.Id = Convert.ToInt32(reader["id"]);
//                        cap.Nom = Convert.ToString(reader["nom"]);
//                        cap.Type = Convert.ToString(reader["type"]);
//                        cap.Unite = Convert.ToString(reader["unite"]);
//                    }
//                }
//                return cap;
//            }
//
//            public void Update(Capteur c)
//            {
//                using (SqlConnection conn = new SqlConnection(_connectionString))
//                {
//                    conn.Open();
//                    String sql = "UPDATE Capteurs SET nom = '" + c.Nom.Replace("\'", "\'\'")
//                            + "', type = '" + c.Type.Replace("\'", "\'\'")
//                            + "', unite = '" + c.Unite.Replace("\'", "\'\'")
//                            + "'  WHERE id = " + c.Id ;
//                    SqlCommand cmd = new SqlCommand(sql, conn);
//                    cmd.ExecuteNonQuery();
//                    System.out.println("Connexion OK - SQL entry modified");
//                }
//            }
//
//            public void Delete(int id)
//            {
//                {
//                    using (SqlConnection conn = new SqlConnection(_connectionString))
//                    {
//                        conn.Open();
//                        String sql = "DELETE FROM Capteurs WHERE id = " + id;
//                        SqlCommand cmd = new SqlCommand(sql, conn);
//                        cmd.ExecuteNonQuery();
//                        System.out.println("Connexion OK - SQL entry deleted");
//                    }
//                }
//            }
//    }
