package com.prm.controller;

import com.prm.dao.BookDao;
import com.prm.model.Book;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet(name = "BookServlet", urlPatterns = {"/Books"})
public class BookServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    setAccessControlHeaders(response);

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
    setAccessControlHeaders(response);

    try (PrintWriter out = response.getWriter()) {
      boolean success = new BookDao().insert(from(request));

      if (success) {
        response.setStatus(HttpServletResponse.SC_OK);
      } else {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    setAccessControlHeaders(response);

     try (PrintWriter out = response.getWriter()) {
      boolean success = new BookDao().update(fromPut(request));

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
    String idOrNull     = request.getParameter("id");
    int id              = idOrNull != null ? Integer.parseInt(idOrNull) : 0;
    String title        = request.getParameter("title");
    String author       = request.getParameter("author");
    String publisher    = request.getParameter("publisher");
    boolean available   = Boolean.parseBoolean(request.getParameter("available"));
    String quantityOrNull  = request.getParameter("quantity");
    int quantity        = quantityOrNull != null ? Integer.parseInt(quantityOrNull) : 0;
    String genre        = request.getParameter("genre");
    String description  = request.getParameter("description");

    return new Book(id, title, author, publisher, available, quantity, genre, description);
  }

  private Book fromPut(HttpServletRequest request) throws IOException {
    Map params;

    try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
      String line;

      line = br.readLine();
      params = Arrays.stream(line.split("&"))
          .map(URLDecoder::decode)
          .map(param -> param.split("="))
          .collect(Collectors.toMap(param -> param[0], param -> param.length > 1 ? param[1] : ""));
      System.out.println(params);

    }

    return new Book(
        Integer.valueOf(String.valueOf(params.get("id"))),
        String.valueOf(params.get("title")),
        String.valueOf(params.get("author")),
        String.valueOf(params.get("publisher")),
        Boolean.valueOf(String.valueOf(params.get("isAvailable"))),
        Integer.valueOf(String.valueOf(params.get("quantity"))),
        String.valueOf(params.get("genre")),
        String.valueOf(params.get("description"))
    );
  }

  private void setAccessControlHeaders(HttpServletResponse response) {
    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "*");
    response.setHeader(
        "Access-Control-Allow-Headers",
        "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With"
    );
  }

}
