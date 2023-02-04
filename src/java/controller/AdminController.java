package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import model.Categorie;
import service.AdminService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminController", value = "/admin")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String identifiant=request.getParameter("identifiant");
            String mdp=request.getParameter("mdp");
            if(!AdminService.login(new Admin(identifiant,mdp))) throw new Exception("identifiant ou mot de passe incorrecte");
            else {
                getServletContext().setAttribute("idadmin",Admin.findByName(identifiant).getId());
                request.setAttribute("admin",Admin.findByName(identifiant));
                request.setAttribute("categories", Categorie.findAll());
                RequestDispatcher dispat=request.getRequestDispatcher("/categorie.jsp");
                dispat.forward(request,response);
            }
        }catch (Exception e){
            PrintWriter out=response.getWriter();
            out.print("login:  "+e.getMessage());
        }
    }

}
