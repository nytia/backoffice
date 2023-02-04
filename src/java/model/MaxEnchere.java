package model;

import util.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class MaxEnchere {
    private int idcategorie;
    private String nomcategorie;
    private String produit;
    private String identifiant;
    private double prix;


    public MaxEnchere() {
    }

    public MaxEnchere(int idcategorie, String nomcategorie, String produit, String identifiant, double prix) {
        this.idcategorie = idcategorie;
        this.nomcategorie = nomcategorie;
        this.produit = produit;
        this.identifiant = identifiant;
        this.prix = prix;
    }

    public static Vector<MaxEnchere> findAll() throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from V_STAT_MAXENCHERE  ";
        ResultSet res = sta.executeQuery(req);
        Vector<MaxEnchere> liste = null;
        try {
            liste = new Vector<MaxEnchere>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new MaxEnchere(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getDouble(5)));
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

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
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
