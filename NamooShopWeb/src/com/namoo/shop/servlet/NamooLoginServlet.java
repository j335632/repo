package com.namoo.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class NamooLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 7836013883507465040L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html; charset = utf-8");
		req.setCharacterEncoding("utf-8");
		
		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>로그인</title>");
		writer.println("</head>");
		writer.println("<body>");
		
		writer.println("<form action ='main' method = 'post'>");
		
		writer.println("<table>");
		writer.println("<tr>");
		writer.println("<td>로그인ID </td>");
		writer.println("<td><input type = 'text' name = 'loginId'></td>");
		writer.println("</tr>");

		writer.println("<tr>");
		writer.println("<td>패스워드</td>");
		writer.println("<td><input type = 'password'' name = 'password'></td>");
		writer.println("</tr>");
		
		writer.println("</table>");
		writer.println("<input type = 'submit' value = '로그인'>");
		writer.println("</form>");
		
		writer.println("</body>");
		writer.println("</html>");

	}

	
}
