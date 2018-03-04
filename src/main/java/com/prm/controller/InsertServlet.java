/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prm.controller;

import com.prm.dao.UserDao;
import com.prm.model.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TINH HUYNH
 */
@WebServlet(name = "InsertServlet", urlPatterns = {"/InsertServlet"})
public class InsertServlet extends HttpServlet {

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
    response.setHeader("Access-Control-Allow-Origin", "*");
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      String username = request.getParameter("username");
      String password = request.getParameter("password");
      String fullname = request.getParameter("fullname");
      String role = request.getParameter("role");
      //TODO: Dao here
      boolean check = new UserDao().insert(new UserDTO(username, password, fullname, role));
      System.out.println("check = " + check);
      if (check) {
        response.setStatus(HttpServletResponse.SC_OK);
      } else {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
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
    processRequest(request, response);
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

  @Override
  protected void doOptions(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    setAccessControlHeaders(response);
    response.setStatus(HttpServletResponse.SC_OK);
  }

  private void setAccessControlHeaders(HttpServletResponse response) {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader(
        "Access-Control-Allow-Methods",
        "GET,PUT,POST,DELETE,OPTIONS"
    );
    response.setHeader(
        "Access-Control-Allow-Headers",
        "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With"
    );
  }
}
