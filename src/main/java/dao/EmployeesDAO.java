package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeesDAO {

	// データベース接続に使用する情報  
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/task30";
	private final String DB_USER = "root";
	private final String DB_PASS = "naraitsys";

	// ｺﾝｽﾄﾗｸﾀでJDBCﾄﾞﾗｲﾊﾞを読み込む
	public EmployeesDAO() {
		try {
			/*Class.forName("com.mysql.jdbc.Driver"); // 古い*/
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			/*『getMessage()ﾒｿｯﾄﾞ』は、Throwableクラスで定義されている*/
			System.err.println("エラー: " + e.getMessage());
			/*IllegalStateExceptionは、JavaのRuntimeExceptionのサブクラス*/
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
	}

	/*--------------------------listAll()--------------------------------------------*/
	public List<Employee> listAll() {

		List<Employee> listAll = new ArrayList<Employee>();

		/*JDBCドライバを読み込む*/
		/*try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("エラー: " + e.getMessage());
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}*/
		
		/*データベースへ接続*/
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			/*SQL文を準備の作成*/
			String sql = "SELECT primaryID, loginID, NAME, AGE FROM users";
			/*ステートメントを作成*/
			PreparedStatement pstmt = conn.prepareStatement(sql);
			/*SQL文を実行し、結果表を取得*/
			ResultSet rs = pstmt.executeQuery();
			
			/*結果に格納されたレコードが存在する間はループ*/
			while (rs.next()) {
				/*結果表のレコードの各列を取得*/
				int primaryid = rs.getInt("primaryID");
				String loginid = rs.getString("loginID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				/*レコードの各列をEmployeeインスタンスに設定し、*/
				Employee employee = new Employee(primaryid, loginid, name, age);
				/*Employeeインスタンスを、ArrayListに追加（add）する*/
				listAll.add(employee);
			}
			/*リソースを解放*/
			rs.close();// リサルトセットをクローズ
			pstmt.close();// ステートメントをクローズ
			conn.close();// コネクションをクローズ

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("エラー: " + e.getMessage());
			return null;
		}
		return listAll;
	}

	/*--------------------------create()--------------------------------------------*/

	public Employee create(String loginid, String name, int age) {

		Employee employee = null;
		/*データベースへ接続*/
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			/*INSERT文の準備(idは自動連番なので指定しない）*/
			String sql = "INSERT INTO users ( loginID, NAME, AGE) VALUES (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// INSERT文中のﾌﾟﾚｰｽﾎﾙﾀﾞ「?」に値を設定しSQLを完成させる
			pstmt.setString(1, loginid);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);

			/*SQL文を実行
			execute『Update』は、ResultSetオブジェクトではなく行数（int型）を返す
			int rs = pstmt.executeUpdate();*/
			pstmt.executeUpdate();
			/*『getGeneratedKeys()』は、JavaのJDBCのAPI
			主キー値など、自動生成されたキーを取得するためのメソッド*/
			ResultSet rs = pstmt.getGeneratedKeys();
			int primaryid = 0;
			if (rs != null && rs.next()) {
				primaryid = rs.getInt(1);
			}

			System.out.println("rs.next()は" + rs.next());
			System.out.println("primaryidは" + primaryid);

			/*if (rs.next()) {*/
			employee = new Employee(primaryid, loginid, name, age);
			/*}*/
			System.out.println(primaryid + loginid + name + age);

			/*リソースを解放*/
			/*rs.close(); execute『Update()』はint型（行数）を戻すのでクローズの必要はない*/
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(employee);
		return employee;
	}

	/*--------------------------find()--------------------------------------------*/

	public Employee find(int primaryid) {

		Employee employee = null;
		/*データベースへ接続*/
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			/*SQL文を作成*/
			String sql = "SELECT * FROM users WHERE primaryID = ?";
			/*ステートメントを作成*/
			PreparedStatement pstmt = conn.prepareStatement(sql);
			/*プレースホルダ『?』に値を設定*/
			pstmt.setInt(1, primaryid);

			/*SQL文の実行し、結果セットを（ResultSetに）取得*/
			ResultSet rs = pstmt.executeQuery();
			/*結果セットからユーザー情報を取得してEmployeeオブジェクトを作成*/
			if (rs.next()) {
				primaryid = rs.getInt("primaryID");
				String loginid = rs.getString("loginID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				employee = new Employee(primaryid, loginid, name, age);
			} else if (!rs.next()) {
				System.out.println("primaryIDが存在しません");
			}

			// リソースを解放
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	/*--------------------------update()--------------------------------------------*/

	public Employee update(int primaryid, String loginid, String name, int age) {

		Employee employee = null;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			/*SQL文の準備*/
			String sql = "UPDATE users SET loginID = ?, NAME = ?, AGE = ? WHERE primaryID = ?";
			/*ステートメントを作成*/
			PreparedStatement pstmt = conn.prepareStatement(sql);
			/*UPDATE文中のプレースホルダ「?」に値を設定しSQLを完成させる*/
			pstmt.setInt(4, primaryid);
			pstmt.setString(1, loginid);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);

			/*SQL文の実行し、行数（int型）を取得
			int rs = pstmt.executeUpdate();*/
			pstmt.executeUpdate();

			// 入力情報でEmployeeオブジェクトを作成
			employee = new Employee(primaryid, loginid, name, age);

			// リソースを解放
			/*rs.close();*/
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	/*--------------------------delete()--------------------------------------------*/

	public Employee delete(int primaryid) {

		Employee employee = null;
		// データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SQL文を作成
			String sql = "DELETE FROM users WHERE primaryID = ?";
			// ステートメントを作成
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// プレースホルダに値を設定
			pstmt.setInt(1, primaryid);

			// SQL文を実行
			pstmt.executeUpdate();
			// int rs = pstmt.executeUpdate();

			// リソースを解放
			/*rs.close();*/
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

}
