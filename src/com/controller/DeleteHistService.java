package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteHistService
 */
@WebServlet("/DeleteHistService.do")
public class DeleteHistService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int inqNo = Integer.parseInt(request.getParameter("inqNo"));
		System.out.println("조회번호 >> " + inqNo);
		
		WifiService ws = new WifiService();
		
		int result = ws.deleteHist(inqNo);
		
		if (result > 0) {
			System.out.println("위치 히스토리 데이터 삭제 성공");
			response.sendRedirect("history.jsp");
		} else {
			request.setAttribute("inqNo", inqNo);
			request.getRequestDispatcher("historyDeleteFail.jsp").forward(request, response);
			System.out.println("위치 히스토리 데이터 삭제 실패");
		}

	}

}
