package model;


import util.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Admin {
    private int id;
    private String identifiant;
    private String mdp;


    public Admin(int id, String identifiant, String mdp) {
        this.id = id;
        this.identifiant = identifiant;
        this.mdp = mdp;
    }

    public Admin() {
    }

    public Admin(String identifiant, String mdp) {
        this.identifiant = identifiant;
        this.mdp = mdp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return identifiant;
    }


    public String getMdp() {
        return mdp;
    }



    public static Vector<Admin> findAll() throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from admin";
        ResultSet res = sta.executeQuery(req);
        Vector<Admin> liste = null;
        try {

            liste = new Vector<Admin>();
            res.first();
            res.beforeFirst();
            while(res.next()) {
                liste.add(new Admin(res.getInt(1),res.getString(2),res.getString(3)));
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
    public static Admin findByName(String identifiant) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from admin where identifiant='"+identifiant+"'";
        ResultSet res = sta.executeQuery(req);
        Admin liste = null;
        try {

            res.first();
            res.beforeFirst();
            if(res.next()) {
                liste=new Admin(res.getInt(1),res.getString(2),res.getString(3));
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

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
