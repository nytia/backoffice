package model;

import util.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class TopCategorie {
    private int idcategorie;
    private String nomcategorie;
    private double totalprix;

    public TopCategorie() {
    }

    public TopCategorie(int idcategorie, String nomcategorie, double totalprix) {
        this.idcategorie = idcategorie;
        this.nomcategorie = nomcategorie;
        this.totalprix = totalprix;
    }

    public static Vector<TopCategorie> findAll() throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from V_STAT_TOPCATEGORIE  ";
        ResultSet res = sta.executeQuery(req);
        Vector<TopCategorie> liste = null;
        try {
            liste = new Vector<TopCategorie>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new TopCategorie(res.getInt(1),res.getString(2),res.getDouble(3)));
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

    public double getTotalprix() {
        return totalprix;
    }

    public void setTotalprix(double totalprix) {
        this.totalprix = totalprix;
    }
}
