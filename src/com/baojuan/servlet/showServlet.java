package com.baojuan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baojuan.service.logService;
import com.baojuan.serviceImp.logServiceImp;

@WebServlet("/show")
public class showServlet extends HttpServlet {
	private logService  logservice=new logServiceImp();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int pageSize=2;
		String pageSizeStr=req.getParameter("pageSize");
		if(pageSizeStr!=null && !pageSizeStr.equals("")) {
			pageSize=Integer.parseInt(pageSizeStr);
		}
		int pageNumber=1;
		String pageNumberStr=req.getParameter("pageNumber");
		if(pageNumberStr!=null && !pageNumberStr.equals("")) {
			pageNumber=Integer.parseInt(pageNumberStr);
		}
		req.setAttribute("pageinfo",logservice.showPage(pageSize, pageNumber));
		req.getRequestDispatcher("log.jsp").forward(req, res);;
	}
}
