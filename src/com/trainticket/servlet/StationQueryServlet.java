package com.trainticket.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trainticket.dao.StationCodeDao;
import com.trainticket.dto.StationCode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class StationQueryServlet
 */
@WebServlet("/StationQueryServlet")
public class StationQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StationQueryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求的编码格式
		request.setCharacterEncoding("UTF-8");
		// 1.获取请求
		// String json = request.getParameter("StationQueryRequest");
		// System.out.println("StationQueryServlet的请求json--->" + json);
		// 设置响应的请求码
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();// 用于输出打印的类
		// 2.将请求到的参数进行Json解析
		// JSONObject jsonObject = JSONObject.fromObject(json);

		// 执行查询数据库的语言
		StationCodeDao dao = new StationCodeDao();
		List<StationCode> StationCodes = dao.select();// 查询所有信息
		// 将查询结果打包成JSONArray数组
		JSONArray array = new JSONArray();
		if (StationCodes != null) {
			for (int i = 0; i < StationCodes.size(); i++) {
				// 1).一条条的从list中读出数据
				StationCode stationCode = StationCodes.get(i);
				// 将查询出来的结果,设置成JSONObject
				JSONObject jObject = new JSONObject();
				jObject.put("station_name", stationCode.getName());
				jObject.put("station_code", stationCode.getCode());
				jObject.put("station_jianpin", stationCode.getJianPin());
				jObject.put("station_fullName", stationCode.getQuanPin());
				array.put(jObject.toString());
			}
			// 将array数组通过jObject传到客户端
			JSONObject jObject = new JSONObject();
			jObject.put("result", 1);
			jObject.put("test", "test1");
			jObject.put("content", array);
			out.println(jObject.toString());
		} else {
			// 将查询出来的结果,打包成Json格式传到客户端
			JSONObject jObject = new JSONObject();
			jObject.put("result", -1);
			out.println(jObject.toString());
		}

		out.flush();
		out.close();
	}

}
