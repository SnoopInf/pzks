package edu.kpi.pzks.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebController extends HttpServlet {

  private static final long serialVersionUID = 6407201151107747016L;

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final String pathInfo = req.getServletPath();
    if (pathInfo.equals("/")) {
      req.getRequestDispatcher("/root.jsp").forward(req, resp);
    }
  }
}
