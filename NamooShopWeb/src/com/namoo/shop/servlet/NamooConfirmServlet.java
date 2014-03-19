package com.namoo.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoosori.namooshop.domain.Customer;
import com.namoosori.namooshop.domain.Order;
import com.namoosori.namooshop.service.facade.CustomerService;
import com.namoosori.namooshop.service.facade.OrderService;
import com.namoosori.namooshop.service.facade.ProductService;
import com.namoosori.namooshop.service.factory.NamooShopServiceFactory;

@WebServlet("/confirm")
public class NamooConfirmServlet extends HttpServlet {

	
	private static final long serialVersionUID = -7087265756311530756L;

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
		OrderService orderService = NamooShopServiceFactory.getInstance().getOrderService();
		ProductService productService = NamooShopServiceFactory.getInstance()	.getProductService();
		CustomerService customerService = NamooShopServiceFactory.getInstance()	.getCustomerService();
		PrintWriter writer = resp.getWriter();
		
		String customer = (String) session.getAttribute("loginId");
		String[] serialNum = req.getParameterValues("number");
		String address = req.getParameter("text");
		String pay  = req.getParameter("card");
		
		Order order = new Order();
		Customer custom = new Customer(customerService.getCustomer(customer).getName(),customerService.getCustomer(customer).getUserId(),
				customerService.getCustomer(customer).getPassword());
		
	
		order.setCustomer(custom);
		
		order.setShipAddress(address);
		if(!orderService.order(order))	{
			resp.sendRedirect("/order");
			return;
		}
		
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>확인</title>");
		writer.println("<link href='./confirm.css' rel='stylesheet' type='text/css' />");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("["+customerService.getCustomer(customer).getName()+ "]님 환영합니다.!!");
		
		writer.println("<form action='complete' method='post'>");
		writer.println("<h2>주문 상품</h2>");
		writer.println("<table>");
		writer.println("<tr id='top'>");
		writer.println("<td>상품번호</td>");
		writer.println("<td>상품명</td>");
		writer.println("<td>가격</td>");
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
		writer.println("<br>");
		writer.println("<span id = 'total'> 주문 금액 : " + totalPrice + "원</span>");

		writer.println("<br>");
	

		writer.println("<h2>주문정보 확인</h2>");
		writer.println("<table>");
		writer.println(" <tr>");
		writer.println(" <td>결제방법</td>");
		writer.println("<td>"+pay+"</td> ");
		writer.println(" </tr> ");
		writer.println(" <tr>");
		writer.println(" <td id='address'>배송지 주소</td> ");
		writer.println(" <td id='output'>"+address+"</td>");
		writer.println(" </tr>");
		writer.println("</table>");
		writer.println("<br>");
		writer.println(" <input type='button' onclick ='history.back();' id='btn1' value='수정'></button>");
		writer.println(" <input type ='submit' id='btn2' value ='신청완료'>");
		writer.println("</form>");
		writer.println(" ");

		writer.println("</body>");
		writer.println("</html>");
		writer.println("");
		writer.println("");

	}

	}

	

