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

@WebServlet(name = "CategoryControl", urlPatterns = {"/category"})
public class CategoryControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cateID = request.getParameter("cid");
        //da lay dc category id ve roi
        DAO dao = new DAO();
        List<Product> list = dao.getProductByCID(cateID);
        //in list p day
        PrintWriter out = response.getWriter();
        for (Product o : list) {
            out.println(" <div class=\"product col-12 col-md-6 col-lg-4\">\r\n"
                    + "                                <div class=\"card\">\r\n"
                    + "                                    <img class=\"card-img-top\" src=\"" + o.getImage() + "\" alt=\"Card image cap\">\r\n"
                    + "                                    <div class=\"card-body\">\r\n"
                    + "                                        <h4 class=\"card-title show_txt\"><a href=\"detail?pid=" + o.getId() + "\" title=\"View Product\">" + o.getName() + "</a></h4>\r\n"
                    + "                                        <p class=\"card-text show_txt\">" + o.getTitle() + "</p>\r\n"
                    + "                                        <div class=\"row\">\r\n"
                    + "                                            <div class=\"col\">\r\n"
                    + "                                                <p class=\"btn btn-danger btn-block\">" + o.getPrice() + " $</p>\r\n"
                    + "                                            </div>\r\n"
                    + "                                            <div class=\"col\">\r\n"
                    + "                                                <a href=\"#\" class=\"btn btn-success btn-block\">Add to cart</a>\r\n"
                    + "                                            </div>\r\n"
                    + "                                        </div>\r\n"
                    + "                                    </div>\r\n"
                    + "                                </div>\r\n"
                    + "                            </div>");
        }
//        List<Category> listC = dao.getAllCategory();
//        Product last = dao.getLast();
//        
//        
//        
//        request.setAttribute("listP", list);
//        request.setAttribute("listCC", listC);
//        request.setAttribute("p", last);
//        request.setAttribute("tag", cateID);
//        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
    }// </editor-fold>

}
