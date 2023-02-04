package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.MaxEnchere;
import model.TopCategorie;
import model.TopVendeur;

import java.io.IOException;

@WebServlet(name = "StatController", value = "/stat")
public class StatController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("topcategorie", TopCategorie.findAll());
            request.setAttribute("maxenchere", MaxEnchere.findAll());
            request.setAttribute("topvendeur", TopVendeur.findAll());
            RequestDispatcher dispat=request.getRequestDispatcher("/statistique.jsp");
            dispat.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
