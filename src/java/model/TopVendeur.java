package model;

import util.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class TopVendeur {
    private int idutilisateur;
    private String identifiant;
    private double prix;


    public TopVendeur() {
    }

    public TopVendeur(int idutilisateur, String identifiant, double prix) {
        this.idutilisateur = idutilisateur;
        this.identifiant = identifiant;
        this.prix = prix;
    }

    public static Vector<TopVendeur> findAll() throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from V_STAT_TOPVENDEUR;  ";
        ResultSet res = sta.executeQuery(req);
        Vector<TopVendeur> liste = null;
        try {
            liste = new Vector<TopVendeur>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new TopVendeur(res.getInt(1),res.getString(2),res.getDouble(3)));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null)
                res.close();
            if (sta != null)
                sta.close();
            if (con != null)
                con.close();

        }
        return liste;
    }
    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
