package com.namoo.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoosori.namooshop.service.facade.CustomerService;
import com.namoosori.namooshop.service.facade.ProductService;
import com.namoosori.namooshop.service.factory.NamooShopServiceFactory;

@WebServlet("/order")
public class NamooOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 2694339589899960684L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		int totalPrice = 0;
		if (session.getAttribute("loginId") == null) {
			resp.sendRedirect("login");
			return;
		}

		resp.setContentType("text/html; charset = utf-8");
		req.setCharacterEncoding("utf-8");
		ProductService productService = NamooShopServiceFactory.getInstance()
				.getProductService();
		CustomerService customerService = NamooShopServiceFactory.getInstance()
				.getCustomerService();
		PrintWriter writer = resp.getWriter();
		String[] serialNum = req.getParameterValues("books");
		String customer = (String) session.getAttribute("loginId");
		
		
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>주문</title>");
		writer.println("<link href='./order.css' rel='stylesheet' type='text/css' />");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("["+customerService.getCustomer(customer).getName()+ "]님 로그인 되었습니다.!!");
		
		writer.println("<form action ='confirm' method ='post'>");
		writer.println("<h2>주문 상품</h2>");
		writer.println("<table>");
		writer.println("<tr id ='top'>");
		writer.println("<td >상품번호</td>");
		writer.println("<td>상품명</td>");
		writer.println("<td >가격</td>");
		writer.println("</tr>");

		for (String num : serialNum) {
			writer.println("<tr>");
			writer.println("<td  id = 'number' >"+ productService.getProduct(Integer.parseInt(num)).getSerialNo() +
					"<input type = 'hidden' name ='number' value='"+productService.getProduct(Integer.parseInt(num)).getSerialNo()+"'></td>");
			writer.println("<td name ='product'  id='product'>"+ productService.getProduct(Integer.parseInt(num)).getName() + "</td>");
			writer.println("<td name ='price' id='price'>"	+ productService.getProduct(Integer.parseInt(num)).getPrice() + "원</td>");
			writer.println("</tr>");
			totalPrice += productService.getProduct(Integer.parseInt(num)).getPrice();
			
		}

		writer.println("</table>");
		writer.println(" <br>");
		writer.println("<span id = 'total'> 주문 금액 : " + totalPrice + "원</span>");

		writer.println(" <br>");
		writer.println(" <br>");
		writer.println("<h2>주문정보 입력</h2>");
		writer.println(" ");
		writer.println(" <table>");
		writer.println(" <tr>");
		writer.println(" <td >결제방법</td>");
		writer.println("<td id = 'radio'><input  type ='radio' name='card' value='신용카드'>신용카드 <input type ='radio' name='card' value='실시간이체'>실시간이체</td> ");
		writer.println(" </tr> ");
		writer.println(" <tr>");
		writer.println(" <td id='address'>배송지 주소</td> ");
		writer.println(" <td id='input'><input  type='text' name = 'text' id ='box'></td>");
		writer.println(" </tr>");
		writer.println(" </table> ");
		writer.println(" <br>");
		writer.println(" <input type ='submit' value ='주문신청' id='btn'>");
		writer.println("</form>");
		

		writer.println("</body>");
		writer.println("</html>");
		writer.println("");
		writer.println("");

	}

}
