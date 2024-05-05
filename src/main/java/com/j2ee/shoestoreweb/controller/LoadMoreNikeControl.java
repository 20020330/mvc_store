package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;
import com.j2ee.shoestoreweb.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoadMoreNikeControl", urlPatterns = {"/loadNike"})
public class LoadMoreNikeControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String amount = request.getParameter("exitsNike");
        int iamount = Integer.parseInt(amount);
        DAO dao = new DAO();
        List<Product> list = dao.getNext4NikeProduct(iamount);
        PrintWriter out = response.getWriter();

        for (Product o : list) {
            out.println("<div class=\"productNike col-12 col-md-6 col-lg-3\">\r\n"
                    + "                                <div class=\"card\">\r\n"
                    + "                                 <div class=\"view zoom z-depth-2 rounded\">\r\n"
                    + "                                    <img class=\"img-fluid w-100\" src=\"" + o.getImage() + "\" alt=\"Card image cap\">\r\n"
                    + "                                    </div>\r\n"
                    + "                                    <div class=\"card-body\">\r\n"
                    + "                                        <h4 class=\"card-title show_txt\"><a href=\"detail?pid=" + o.getId() + "\" title=\"View Product\">" + o.getName() + "</a></h4>\r\n"
                    + "                                        <p class=\"card-text show_txt\">" + o.getTitle() + "</p>\r\n"
                    + "                                        <div class=\"row\">\r\n"
                    + "                                            <div class=\"col\">\r\n"
                    + "                                                <p class=\"btn btn-success btn-block\">" + o.getPrice() + " $</p>\r\n"
                    + "                                            </div>\r\n"
                    + "                                            \r\n"
                    + "                                        </div>\r\n"
                    + "                                    </div>\r\n"
                    + "                                </div>\r\n"
                    + "                            </div>");
        }
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