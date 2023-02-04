package model;

public class Utilisateur {
    private int id;
    private String identifiant;
    private String mdp;

    public Utilisateur(int id, String identifiant, String mdp) {
        this.id = id;
        this.identifiant = identifiant;
        this.mdp = mdp;
    }

    public Utilisateur() {
    }

    public Utilisateur(int id, String identifiant) {
        this.id = id;
        this.identifiant = identifiant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
