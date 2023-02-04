package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Admin;
import model.Categorie;
import model.Commission;

import java.io.IOException;

@WebServlet(name = "CommissionController", value = "/commission")
public class CommissionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Commission.save(new Commission(Double.valueOf(request.getParameter("valeur"))/100));
            request.setAttribute("categories", Categorie.findAll());
            RequestDispatcher dispat=request.getRequestDispatcher("/categorie.jsp");
            dispat.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
