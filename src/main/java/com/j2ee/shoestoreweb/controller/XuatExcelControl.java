package com.j2ee.shoestoreweb.controller;

import com.j2ee.shoestoreweb.dao.DAO;
import com.j2ee.shoestoreweb.entity.Account;
import com.j2ee.shoestoreweb.entity.Invoice;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@WebServlet(name = "XuatExcelControl", urlPatterns = {"/xuatExcelControl"})
public class XuatExcelControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String ngayXuat = request.getParameter("dateHoaDon");

        DAO dao = new DAO();

        List<Invoice> list = dao.searchByNgayXuat(ngayXuat);
        List<Account> listAllAccount = dao.getAllAccount();

        int maximum = 2147483647;
        int minimum = 1;

        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;


        FileOutputStream file = new FileOutputStream("C:\\ExcelWebBanGiay\\" + "hoa-don-" + ngayXuat + "-" + Integer.toString(randomNum) + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet workSheet = workbook.createSheet("1");
        XSSFRow row;
        XSSFCell cell0;
        XSSFCell cell1;
        XSSFCell cell2;
        XSSFCell cell3;

        row = workSheet.createRow(0);
        cell0 = row.createCell(0);
        cell0.setCellValue("Mã Hóa Đơn");
        cell1 = row.createCell(1);
        cell1.setCellValue("Account");
        cell2 = row.createCell(2);
        cell2.setCellValue("Tổng Giá($)");
        cell3 = row.createCell(3);
        cell3.setCellValue("Ngày Xuất");

        double tongGia;
        int i = 0;

        for (Invoice o : list) {
            i = i + 1;
            for (Account a : listAllAccount) {
                if (o.getAccountID() == a.getId()) {
                    tongGia = Math.round((o.getTongGia()) * 100.0) / 100.0;
                    row = workSheet.createRow(i);
                    cell0 = row.createCell(0);
                    cell0.setCellValue(o.getMaHD());
                    cell1 = row.createCell(1);
                    cell1.setCellValue(a.getUser());
                    cell2 = row.createCell(2);
                    cell2.setCellValue(tongGia);
                    cell3 = row.createCell(3);
                    cell3.setCellValue(ngayXuat);
                }
            }
        }
        workbook.write(file);
        workbook.close();
        file.close();

        request.setAttribute("mess", "Đã xuất file Excel thành công!");
        request.getRequestDispatcher("hoaDon").forward(request, response);
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