import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
		// 识别login.html->form->input中的name属性，并获得相应的用户输入
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.setContentType("text/html;charset = UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html");
		out.println("<html><body>");
		// 用户名密码匹配
//        if("admin".equals(username) && "admin".equals(password)) {
//        	out.println("登陆成功！欢迎您" + username + "^_^");
//        } else {
//            out.println("请再次确认用户名和密码的正确性。-_-");
//        }

		Map<String, String> map = new HashMap<>();
		map.put("admin", "admin");
		map.put("jack", "jack");
		map.put("tom", "tom");

//		if (map.get(username) == null) {
//			out.println("not exist。-_-");
//		} else if (map.get(username).equals(password)) {
//			out.println("登陆成功！欢迎您" + username + "^_^");
//		} else {
//			out.println("请再次确认用户名和密码的正确性。-_-");
//		}
//		
		User user = DbConnection.getPasswordByUser(username);
		if (user == null) {
			out.println("not exist。-_-");
		} else if (user.password.equals(password)) {
			out.println("登陆成功！欢迎您" + username + "^_^");
		} else {
			out.println("请再次确认用户名和密码的正确性。-_-");
		}

		out.println("</body></html>");
	}
}