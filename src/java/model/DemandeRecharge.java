package model;

import util.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class DemandeRecharge {
    private int id;
    private Utilisateur utilisateur;
    private String date;
    private double montant;
    private int statut;


    public DemandeRecharge() {
    }

    public DemandeRecharge(int id, Utilisateur utilisateur, String date, double montant, int statut) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.date = date;
        this.montant = montant;
        this.statut = statut;
    }

    public static Vector<DemandeRecharge> findEnAttente() throws Exception{
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_demanderecharge where statut=0";
        ResultSet res = sta.executeQuery(req);
        Vector<DemandeRecharge> liste = null;
        try {
            liste=new Vector<DemandeRecharge>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new DemandeRecharge(res.getInt(1),new Utilisateur(res.getInt(2),res.getString(3)),res.getString(4),res.getDouble(5),res.getInt(6)));
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

    public static void update(Connection con,String id) throws Exception {
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "update demanderecharge set statut=1 where id="+id;
            sta.executeUpdate(req);
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            if (sta != null)
                sta.close();
//            if (con != null)
//                con.close();
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
