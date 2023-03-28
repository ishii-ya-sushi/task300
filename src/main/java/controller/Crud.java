package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Crud")
public class Crud extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		String pageParam = request.getParameter("page");
		System.out.println("pageParamは " + pageParam);
		/*ページの場合分け*/
		if (pageParam.equals("index")) {
			/*フォワード*/
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/index.jsp");
			dispatcher.forward(request, response);
		} else if (pageParam.equals("crud")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/crud.jsp");
			dispatcher.forward(request, response);
		} else {

		}
	}
}
