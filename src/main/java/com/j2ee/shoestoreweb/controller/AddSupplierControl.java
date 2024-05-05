package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddSupplierControl", urlPatterns = {"/addSupplier"})
public class AddSupplierControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String nameSupplier = request.getParameter("nameSupplier");
        String phoneSupplier = request.getParameter("phoneSupplier");
        String emailSupplier = request.getParameter("emailSupplier");
        String addressSupplier = request.getParameter("addressSupplier");
        String cateID = request.getParameter("cateID");

        DAO dao = new DAO();
        dao.insertSupplier(nameSupplier, phoneSupplier, emailSupplier, addressSupplier, cateID);
        request.setAttribute("mess", "Supplier Added!");
        request.getRequestDispatcher("managerSupplier").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}