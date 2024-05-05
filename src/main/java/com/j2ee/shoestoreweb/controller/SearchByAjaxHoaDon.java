package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;
import com.j2ee.shoestoreweb.entity.Account;
import com.j2ee.shoestoreweb.entity.Invoice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SearchByAjaxHoaDon", urlPatterns = {"/searchAjaxHoaDon"})
public class SearchByAjaxHoaDon extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String ngayXuat = request.getParameter("ngayXuat");
        DAO dao = new DAO();
        List<Invoice> listInvoiceByDate = dao.searchByNgayXuat(ngayXuat);
        List<Account> listAllAccount = dao.getAllAccount();
        PrintWriter out = response.getWriter();
        double tongGia;
        for (Invoice o : listInvoiceByDate) {
            for (Account a : listAllAccount) {
                if (o.getAccountID() == a.getId()) {
                    tongGia = Math.round((o.getTongGia()) * 100.0) / 100.0;
                    out.println("<tr>\r\n"
                            + "                  <th scope=\"row\"></th>\r\n"
                            + "                  <td>" + o.getMaHD() + "</td>\r\n"
                            + "                  <td>" + a.getUser() + "</td>\r\n"
                            + "                  <td>" + tongGia + "</td>\r\n"
                            + "                  <td>" + o.getNgayXuat() + "</td> \r\n"
                            + "                </tr>");
                }
            }
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