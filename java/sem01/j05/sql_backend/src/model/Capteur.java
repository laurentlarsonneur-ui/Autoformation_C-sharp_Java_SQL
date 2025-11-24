// Représente un objet métier (juste des données).

package model;

public class Capteur
    {
        private int id;
        private String nom, type, unite;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getNom() { return nom; }
        public void setNom(String nom) { this.nom = nom; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public String getUnite() { return unite; }
        public void setUnite(String unite) { this.unite = unite; }

        public void display() {
            System.out.println("Id: " + id + " ; Nom: " + nom + " ; Type: " + type + " ; Unite: " + unite);
        }
    }