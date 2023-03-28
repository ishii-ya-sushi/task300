package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeesDAO;
import model.Employee;

@WebServlet("/SelectEmployees")
public class SelectEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*文字化け対策*/
		request.setCharacterEncoding("UTF-8"); /*HTTPリクエストのエンコーディング*/
		response.setContentType("text/html; charset=UTF-8"); /*HTTPレスポンスのエンコーディング*/

		/*フォームからのパラメータを取得*/
		int primaryid = 0;
		try {
			/*文字列を数値に変換するコード*/
			primaryid = Integer.parseInt(request.getParameter("primaryid"));
		} catch (NumberFormatException e) {
			primaryid = 999999;
			/*null文字列を解析しようとした場合に発生する例外を処理する*/
			System.out.println("Error: Cannot parse null string.");
		}
		String loginid = request.getParameter("loginid");
		String name = request.getParameter("name");
		int age = 0;
		try {
			age = Integer.parseInt(request.getParameter("age"));
		} catch (NumberFormatException e) {
			age = 999;
			System.out.println("Error: Cannot parse null string.");
		}
		System.out.println("パラメータ" + primaryid + loginid + name + age);

		/*--------------------------------------------------------------------*/
		/*メソッドの場合分け*/
		if (request.getParameter("create") != null) {

			/*EmployeesDAOのcreate()ﾒｿｯﾄﾞ実施*/
			EmployeesDAO empDAO = new EmployeesDAO();
			Employee employee = empDAO.create(loginid, name, age);

			System.out.println("作成" + employee);

			/*取得したレコードの内容をコンソールに出力*/
			System.out.println("ﾌﾟﾗｲﾏﾘID:" + employee.getPrimaryid());
			System.out.println("ログインID:" + employee.getLoginid());
			System.out.println("名前:" + employee.getName());
			System.out.println("年齢:" + employee.getAge() + "\n");

			/*JSPで表示するためにﾘｸｴｽﾄｽｺｰﾌﾟに保存 フォワード*/
			request.setAttribute("employee", employee);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/result.jsp");
			dispatcher.forward(request, response);

		} else if (request.getParameter("read") != null) {

			/*EmployeesDAOのread()ﾒｿｯﾄﾞ実施*/
			EmployeesDAO empDAO = new EmployeesDAO();
			Employee employee = empDAO.find(primaryid);

			if (employee != null) {
				/*employeeがnullでない、検索したprimaryIDが存在した場合*/
				request.setAttribute("employee", employee);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/read.jsp");
				dispatcher.forward(request, response);
			} else if (employee == null) {
				/*employeeがnull、primaryIDが存在しなかった場合*/
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/error.jsp");
				dispatcher.forward(request, response);
			}

		} else if (request.getParameter("updatePage") != null) {
			/*まずは検索のEmployeesDAOのread()ﾒｿｯﾄﾞ実施*/
			EmployeesDAO empDAO = new EmployeesDAO();
			Employee employee = empDAO.find(primaryid);
			if (employee != null) {
				/*employeeがnullでない、検索したprimaryIDが存在した場合*/
				request.setAttribute("employee", employee);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/update.jsp");
				dispatcher.forward(request, response);
			} else if (employee == null) {
				/*employeeがnull、primaryIDが存在しなかった場合*/
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/error.jsp");
				dispatcher.forward(request, response);
			}
		} else if (request.getParameter("update") != null) {
			/*EmployeesDAOのupdate()ﾒｿｯﾄﾞ実施*/
			EmployeesDAO empDAO = new EmployeesDAO();
			Employee employee = empDAO.update(primaryid, loginid, name, age);

			request.setAttribute("employee", employee);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/result.jsp");
			dispatcher.forward(request, response);

		} else if (request.getParameter("deletePage") != null) {
			/*まずは検索のEmployeesDAOのread()ﾒｿｯﾄﾞ実施*/
			EmployeesDAO empDAO = new EmployeesDAO();
			Employee employee = empDAO.find(primaryid);
			if (employee != null) {
				/*employeeがnullでない、検索したprimaryIDが存在した場合*/
				request.setAttribute("employee", employee);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/delete.jsp");
				dispatcher.forward(request, response);
			} else if (employee == null) {
				/*employeeがnull、primaryIDが存在しなかった場合*/
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/error.jsp");
				dispatcher.forward(request, response);
			}
		} else if (request.getParameter("delete") != null) {
			/*EmployeesDAOのdelete()ﾒｿｯﾄﾞ実施*/
			EmployeesDAO empDAO = new EmployeesDAO();
			/*Employee employee = empDAO.delete(primaryid);*/
			empDAO.delete(primaryid);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/crud.jsp");
			dispatcher.forward(request, response);

		} else {
			System.out.println("場合分け漏れ？");
		}
	}
}
