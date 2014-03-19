package com.namoo.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/complete")
public class NamooCompleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8383724162016461361L;

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
		
		
		writer.println("<html> ");
		writer.println("<head>");
		writer.println("<meta charset='UTF-8'>");
		writer.println("<title>주문완료</title> ");
		writer.println("<link href='./complete.css' rel='stylesheet' type='text/css' />");
		writer.println("</head> ");
		writer.println("<body> ");
		writer.println("<div class='area'> ");
		
		writer.println("<form action ='main' method='post'>");
		writer.println(" <h1 id = 'mes'>상품주문이 완료 되었습니다.</h1>");
		writer.println("<input type ='submit' value ='확인' class ='completeBtn'>");
		writer.println("</form>");
		
		writer.println("</div>");
		writer.println("</body>");
		writer.println("</html>");
					
	
	}

	
	
}
