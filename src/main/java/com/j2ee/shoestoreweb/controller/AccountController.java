package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;
import com.j2ee.shoestoreweb.entity.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccountController", urlPatterns = {"/managerAccount", "/addAccount", "/deleteAccount"})
public class AccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if ("/addAccount".equals(servletPath)) {
            processAddAccount(request, response);
        } else if ("/deleteAccount".equals(servletPath)) {
            processDeleteAccount(request, response);
        } else {
            processViewAccount(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void processViewAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int id = a.getId();
        DAO dao = new DAO();

        List<Account> list = dao.getAllAccount();

        request.setAttribute("listA", list);
        request.getRequestDispatcher("QuanLyTaiKhoan.jsp").forward(request, response);
    }

    protected void processAddAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String isSell = request.getParameter("isSell");
        String isAdmin = request.getParameter("isAdmin");
        String email = request.getParameter("email");

        DAO dao = new DAO();
        dao.insertAccount(user, pass, isSell, isAdmin, email);

        request.setAttribute("mess", "Account Added!");
        request.getRequestDispatcher("managerAccount").forward(request, response);
    }

    protected void processDeleteAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        System.out.println("id: " + id);
        DAO dao = new DAO();

        dao.deleteCartByAccountID(Integer.parseInt(id));
        dao.deleteProductBySellID(id);
        dao.deleteReviewByAccountID(id);
        dao.deleteInvoiceByAccountId(id);
        dao.deleteTongChiTieuBanHangByUserID(id);
        dao.deleteAccount(id);

        request.setAttribute("mess", "Account Deleted!");
        request.getRequestDispatcher("managerAccount").forward(request, response);
    }
}