package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.DemandeRecharge;
import model.TransactionCompte;
import util.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Vector;

@WebServlet(name = "RechargeController", value = "/recharge")
public class RechargeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Vector<DemandeRecharge> demandeRecharges=DemandeRecharge.findEnAttente();
            request.setAttribute("demandes",demandeRecharges);
            RequestDispatcher dispat=request.getRequestDispatcher("/validation.jsp");
            dispat.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String iduser=request.getParameter("iduser");
            String iddemande=request.getParameter("iddemande");
            Double montant=Double.valueOf(request.getParameter("montant"));
            Connection con= Conexion.getCo();
            con.setAutoCommit(false);
            DemandeRecharge.update(con,iddemande);
            TransactionCompte.save(con,iduser,montant);
            con.close();
            List<DemandeRecharge> demandeRecharges=DemandeRecharge.findEnAttente();
            request.setAttribute("demandes",demandeRecharges);
            RequestDispatcher dispat=request.getRequestDispatcher("/validation.jsp");
            dispat.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
