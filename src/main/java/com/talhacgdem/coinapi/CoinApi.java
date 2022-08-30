/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.talhacgdem.coinapi;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author talha
 */
@WebServlet(name = "CoinApi", urlPatterns = {"/getcoins"})
public class CoinApi extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CoinApi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CoinApi at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

        PrintWriter out = response.getWriter();

        String limit = request.getParameter("limit");
        if (limit == null || "".equals(limit)) {
            out.print("FAILED. Missing parameter 'limit'");
            out.flush();
        } else {
            try {
                int limitControl = Integer.parseInt(limit);
                if (Integer.parseInt(limit) < 1) {
                out.print("FAILED. Wrong parameter: 'limit' can't be less then 1");
                out.flush();
            } else {
                Crawler cr = new Crawler();
                String resp = cr.getAll(limit).toString();
                if (resp == null) {
                    resp = "[]";
                }

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(resp);
                out.flush();
            }
            } catch (Exception e) {
                out.print("FAILED. Wrong parameter: 'limit' must be a number.");
                out.flush();
            }            
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
