using Microsoft.Data.SqlClient;
using sql_backend.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
// Un DAO (Data Access Object) est une classe spécialisée dans l'accès aux données
// Gère la communication avec SQL Server (CRUD).
// CRUD = Create (créer), Read (lire), Update (mettre à jour) et Delete (supprimer)

namespace sql_backend.DAO
{
    public class CapteurDAO
    {
        private string _connectionString;

        public CapteurDAO(string connectionString)
        {
            _connectionString = connectionString;
        }

        public void Insert(Capteur c)
        {
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                conn.Open();
                string sql = "INSERT INTO Capteurs (nom, type, unite) VALUES ('" + c.Nom.Replace("\'", "\'\'") + "', '" + c.Type.Replace("\'", "\'\'") + "', '" + c.Unite.Replace("\'", "\'\'") + "')";
                SqlCommand cmd = new SqlCommand(sql, conn);
                cmd.ExecuteNonQuery();
                Console.WriteLine("Connexion OK - SQL entry created");
            }
        }

        public List<Capteur> FindAll()
        {
            List<Capteur> mes_cap = new List<Capteur>(); // Liste vide de capteurs
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                conn.Open();

                string sql = "SELECT id, nom, type, unite FROM Capteurs";
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
                string sql = "SELECT * FROM Capteurs WHERE Id="+id;
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
                string sql = "UPDATE Capteurs SET nom = '" + c.Nom.Replace("\'", "\'\'") 
                    + "', type = '" + c.Type.Replace("\'", "\'\'") 
                    + "', unite = '" + c.Unite.Replace("\'", "\'\'") 
                    + "'  WHERE id = " + c.Id ;
                SqlCommand cmd = new SqlCommand(sql, conn);
                cmd.ExecuteNonQuery();
                Console.WriteLine("Connexion OK - SQL entry modified");
            }
        }
        public void Delete(int id)
        {
            {
                using (SqlConnection conn = new SqlConnection(_connectionString))
                {
                    conn.Open();
                    string sql = "DELETE FROM Capteurs WHERE id = " + id;
                    SqlCommand cmd = new SqlCommand(sql, conn);
                    cmd.ExecuteNonQuery();
                    Console.WriteLine("Connexion OK - SQL entry deleted");
                }
            }
        }
    }

}
