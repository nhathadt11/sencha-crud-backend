package com.prm.controller;

import com.prm.dao.BookDao;
import com.prm.model.Book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(name = "BookServlet", urlPatterns = {"/Books"})
public class BookServlet extends HttpServlet {

  private void preProcessResponse(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    preProcessResponse(request, response);

    try (PrintWriter out = response.getWriter()) {
      JSONArray books = new JSONArray();
      BookDao bookDao = new BookDao();
      List<Book> result = bookDao.getAllBooks();

      for (Book book : result) {
        books.add(JSONObject.fromObject(book));
      }

      response.setStatus(HttpServletResponse.SC_OK);
      out.println(books.toString());
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    preProcessResponse(request, response);

    try (PrintWriter out = response.getWriter()) {
      boolean success = new BookDao().insert(from(request));

      if (success) {
        response.setStatus(HttpServletResponse.SC_OK);
      } else {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
    }
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    preProcessResponse(request, response);

    try (PrintWriter out = response.getWriter()) {
      boolean success = new BookDao().update(from(request));

      if (success) {
        response.setStatus(HttpServletResponse.SC_OK);
      } else {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
    }
  }

  @Override
  protected void doOptions(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    setAccessControlHeaders(response);
    response.setStatus(HttpServletResponse.SC_OK);
  }

  private Book from(HttpServletRequest request) {
    int id              = Integer.parseInt(request.getParameter("id"));
    String title        = request.getParameter("title");
    String author       = request.getParameter("author");
    String publisher    = request.getParameter("publisher");
    boolean available   = Boolean.parseBoolean(request.getParameter("available"));
    int quantity        = Integer.parseInt(request.getParameter("quantity"));
    String genre        = request.getParameter("genre");
    String description  = request.getParameter("description");

    return new Book(id, title, author, publisher, available, quantity, genre, description);
  }

  private void setAccessControlHeaders(HttpServletResponse response) {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "*");
    response.setHeader(
        "Access-Control-Allow-Headers",
        "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With"
    );
  }

}
