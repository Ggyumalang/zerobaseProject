//근처 WIFI 목록을 보여줄 서비스
package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.NearDistDTO;

/**
 * Servlet implementation class NearWifiSearchService
 */
@WebServlet("/NearWifiSearchService.do")
public class NearWifiSearchService extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lnt = Double.parseDouble(request.getParameter("long"));
		//받아온 값을 db history에 저장하고
		WifiService ws = new WifiService();
		int result = ws.registerHist(lat, lnt);
		if(result >= 1) {
			System.out.println("데이터 삽입 성공");
			List<NearDistDTO> histList = ws.nearWifiSelect(lat, lnt);
			if(histList != null) {
				System.out.println("근처 wifi 목록 조회 성공!");
				request.setAttribute("lat", lat);
				request.setAttribute("lnt", lnt);
				request.setAttribute("histList", histList);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}else {
				System.out.println("근처 wifi 목록 조회 실패!");
			}
		}else {
			System.out.println("데이터 삽입 실패");
			return;
		}
		
		//이를 기준으로 정렬해서 보여줄 것을 20개까지.
		
	}

}
