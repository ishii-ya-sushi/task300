package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeesDAO;
import model.Employee;

@WebServlet("/ListAll")
public class ListAll extends HttpServlet {	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*パラメータを取得*/
		String pageParam = null;
		try {
			pageParam = request.getParameter("page");
		} catch (NullPointerException e) {
			/*パラメータがnullの場合の処理を行う*/
			pageParam = ("");
		}

		System.out.println("pageParamは " + pageParam);
		
		/*ページの場合分け*/
		if (pageParam.equals("listAll")) {

			System.out.println("ユーザ一覧呼び出し");

			/*EmployeesDAOのlistAll()ﾒｿｯﾄﾞ実施*/
			EmployeesDAO empDAO = new EmployeesDAO();
			List<Employee> listAll = empDAO.listAll();

			/*取得したレコードの内容をコンソールに出力*/
			for (Employee emp : listAll) {
				System.out.println("ﾌﾟﾗｲﾏﾘID:" + emp.getPrimaryid());				
				System.out.println("ログインID:" + emp.getLoginid());
				System.out.println("名前:" + emp.getName());
				System.out.println("年齢:" + emp.getAge() + "\n");
			}

			/*文字化け対策*/
			request.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html; charset=UTF-8");

			/*HTML 出力準備*/
			PrintWriter out = response.getWriter();

			out.println("<html lang='ja'>");
			out.println("<head>");
			out.println("<title>データベースの連携</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>全件検索</p>");
			out.println("<pre>");

			/*取得したレコードの内容をコンソールに出力*/
			for (Employee emp : listAll) {
				out.print("ﾌﾟﾗｲﾏﾘID:" + emp.getPrimaryid());
				out.print(" ログインID:" + emp.getLoginid());
				out.print(" 名前:" + emp.getName());
				out.println(" 年齢:" + emp.getAge() + "\n");
			}

			out.println("</pre>");
			out.println("<a href=\"Crud?page=crud\">CRUD入力画面へ</a>");
			out.println("</body>");
			out.println("</html>");

			/*JSPで表示するためにﾘｸｴｽﾄｽｺｰﾌﾟに保存 フォワード*/
			/*request.setAttribute("employee", listAll);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/listAll.jsp");
			dispatcher.forward(request, response);*/

		} else if(pageParam.equals("listA"))  {
			/*JSP側でユーザの一覧を表示するようにする
			EmployeesDAOをｲﾝｽﾀﾝｽのcreate()ﾒｿｯﾄﾞ実施*/
			EmployeesDAO empDAO = new EmployeesDAO();
			List<Employee> listAll = empDAO.listAll();

			// JSPで表示するためにﾘｸｴｽﾄｽｺｰﾌﾟに保存 フォワード
			request.setAttribute("employee", listAll);

			Object obj = request.getAttribute("employee");
			System.out.println(obj != null);
			System.out.println(obj instanceof Employee);
			System.out.println(listAll instanceof List<Employee>);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/listAll.jsp");
			dispatcher.forward(request, response);

		}
	}
}
