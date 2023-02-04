package model;

import util.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransactionCompte {
    private int idcompte;
    private double montant;
    private String date;


    public static int findIdCompte(String iduser) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from  compte where idutilisateur="+iduser+"";
        ResultSet res = sta.executeQuery(req);
        int id=0;
        try {

            res.first();
            res.beforeFirst();
            while(res.next()) {
                id=res.getInt(1);
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
        return id;
    }

    public static void save(Connection con,String idcompte,double montant) throws Exception {
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "insert into transactioncompte(idcompte,montant) values ("+idcompte+","+montant+")";
            sta.executeUpdate(req);
            con.commit();
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
    public int getIdcompte() {
        return idcompte;
    }

    public void setIdcompte(int idcompte) {
        this.idcompte = idcompte;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TransactionCompte() {
    }

    public TransactionCompte(int idcompte, double montant, String date) {
        this.idcompte = idcompte;
        this.montant = montant;
        this.date = date;
    }
}
