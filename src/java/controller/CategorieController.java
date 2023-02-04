package controller;


import model.Admin;
import model.Categorie;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CategorieController", value = "/categorie")
public class CategorieController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("categories", Categorie.findAll());
            RequestDispatcher dispat=request.getRequestDispatcher("/categorie.jsp");
            dispat.forward(request,response);
        }catch (Exception e){
            PrintWriter out=response.getWriter();
            out.print(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nomcategorie=request.getParameter("nomcategorie");
            Categorie.save(new Categorie(nomcategorie));
            request.setAttribute("categories", Categorie.findAll());
            RequestDispatcher dispat=request.getRequestDispatcher("/categorie.jsp");
            dispat.forward(request,response);
        } catch (Exception e){
            PrintWriter out=response.getWriter();
            out.print(e.getMessage());
        }
    }
}
