package com.namoo.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoosori.namooshop.domain.Product;
import com.namoosori.namooshop.service.facade.CustomerService;
import com.namoosori.namooshop.service.facade.ProductService;
import com.namoosori.namooshop.service.factory.NamooShopServiceFactory;

@WebServlet("/main")
public class NamooMainServlet extends HttpServlet {

	private static final long serialVersionUID = 2409881855103790342L;

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
		

		ProductService productService = NamooShopServiceFactory.getInstance()	.getProductService();
		CustomerService customerService = NamooShopServiceFactory.getInstance().getCustomerService();
		
		List<Product> productList = new ArrayList<>();
		productList = productService.getAllProducts();
		String star = "";
		String emptyStar="";
		
		
		HttpSession session = req.getSession();
		
		if(customerService.login(req.getParameter("loginId"), req.getParameter("password"))){
			session.setAttribute("loginId", req.getParameter("loginId"));
		}
		
		writer.println("<html>");
		writer.println("<meta charset='UTF-8'>");
		writer.println("<head>");
		writer.println("<title border=\"1\">상품목록</title>");
		writer.println("<link href='./main.css' rel='stylesheet' type='text/css' />");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h2>상품 목록</h2>");
		writer.println("<form action = 'order' method = 'post'>");
		writer.println("<table>");
		writer.println("<tr id ='bold'>");		
		writer.println("<td></td>");
		writer.println("<td>상품번호</td>");
		writer.println("<td>상품명</td>");
		writer.println("<td>가격</td>");
		writer.println("<td>평점</td>");
		writer.println("</tr>");
		for (Product product : productList) {
			
			writer.println("<tr>");
			writer.println("<td><input type = 'checkbox' name = 'books' value = "+product.getSerialNo()+"></td>");
			writer.println("<td>" + product.getSerialNo() + "</td>");
			writer.println("<td id='product'>  " + product.getName() + "</td>");
			writer.println("<td id='price'>" + product.getPrice() + "원</td>");
			for(int i = 0; i<product.getLike(); i++){
				star +="★";
			}			
			for(int j = 0; j<(5-product.getLike()); j++){
				emptyStar +="☆";
			}
			writer.println("<td id='star'>" + star+emptyStar+ "</td>");
			writer.println("</tr>");
			
			star = "";
			emptyStar = "";
		}
		writer.println("</table>");
		writer.println("<br>");
		
		writer.println("<input type = 'submit' value = '주문하기' class ='orderBtn'>");
		writer.println("</form>");
		writer.println("<input type = 'button' onclick=\"location.href='logout'\" value = '로그아웃' class ='logoutBtn'>");
		writer.println("</body>");
		writer.println("</html>");
	}

}
