// Un DAO (Data Access Object) est une classe spécialisée dans l'accès aux données
// Gère la communication avec SQL Server (CRUD).
// CRUD = Create (créer), Read (lire), Update (mettre à jour) et Delete (supprimer)

package dao;

public class CapteurDAO {

            private String _connectionString;

            public CapteurDAO(String connectionString)
            {
                _connectionString = connectionString;
            }
            public void Insert(Capteur c)
            {
                using (SqlConnection conn = new SqlConnection(_connectionString))
                {
                    conn.Open();
                    String sql = "INSERT INTO Capteurs (nom, type, unite) VALUES ('" + c.Nom.Replace("\'", "\'\'") + "', '" + c.Type.Replace("\'", "\'\'") + "', '" + c.Unite.Replace("\'", "\'\'") + "')";
                    SqlCommand cmd = new SqlCommand(sql, conn);
                    cmd.ExecuteNonQuery();
                    System.out.println("Connexion OK - SQL entry created");
                }
            }
            public List<Capteur> FindAll()
            {
                List<Capteur> mes_cap = new List<Capteur>(); // Liste vide de capteurs
                using (SqlConnection conn = new SqlConnection(_connectionString))
                {
                    conn.Open();

                    String sql = "SELECT id, nom, type, unite FROM Capteurs";
                    SqlCommand cmd = new SqlCommand(sql, conn);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        Capteur cap = new Capteur();
                        cap.Id = Convert.ToInt32(reader["id"]);
                        cap.Nom = Convert.ToString(reader["nom"]);
                        cap.Type = Convert.ToString(reader["type"]);
                        cap.Unite = Convert.ToString(reader["unite"]);
                        mes_cap.Add(cap);
                    }
                }
                return mes_cap;
            }
            public Capteur findById(int id)
            {
                Capteur cap = new Capteur();

                using (SqlConnection conn = new SqlConnection(_connectionString))
                {
                    conn.Open();
                    String sql = "SELECT * FROM Capteurs WHERE Id="+id;
                    SqlCommand cmd = new SqlCommand(sql, conn);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        cap.Id = Convert.ToInt32(reader["id"]);
                        cap.Nom = Convert.ToString(reader["nom"]);
                        cap.Type = Convert.ToString(reader["type"]);
                        cap.Unite = Convert.ToString(reader["unite"]);
                    }
                }
                return cap;
            }
            public void Update(Capteur c)
            {
                using (SqlConnection conn = new SqlConnection(_connectionString))
                {
                    conn.Open();
                    String sql = "UPDATE Capteurs SET nom = '" + c.Nom.Replace("\'", "\'\'")
                            + "', type = '" + c.Type.Replace("\'", "\'\'")
                            + "', unite = '" + c.Unite.Replace("\'", "\'\'")
                            + "'  WHERE id = " + c.Id ;
                    SqlCommand cmd = new SqlCommand(sql, conn);
                    cmd.ExecuteNonQuery();
                    System.out.println("Connexion OK - SQL entry modified");
                }
            }
            public void Delete(int id)
            {
                {
                    using (SqlConnection conn = new SqlConnection(_connectionString))
                    {
                        conn.Open();
                        String sql = "DELETE FROM Capteurs WHERE id = " + id;
                        SqlCommand cmd = new SqlCommand(sql, conn);
                        cmd.ExecuteNonQuery();
                        System.out.println("Connexion OK - SQL entry deleted");
                    }
                }
    }
}
