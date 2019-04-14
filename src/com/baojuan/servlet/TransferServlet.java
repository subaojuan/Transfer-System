package com.baojuan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baojuan.pojo.Account;
import com.baojuan.service.accountService;
import com.baojuan.serviceImp.accountServiceImp;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private accountService accountservice=new accountServiceImp();
	@SuppressWarnings("static-access")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Account accOut = new Account();
		accOut.setAccno(req.getParameter("accOutAccNo"));
		accOut.setPassword(Integer.parseInt(req.getParameter("accOutPassword")));
		accOut.setBalance(Double.parseDouble(req.getParameter("accOutBalance")));
		Account accIn = new Account();
		accIn.setAccno(req.getParameter("accInAccNo"));
		accIn.setName(req.getParameter("accInName"));
		int index=accountservice.transFer(accIn, accOut);
		if(index==accountservice.SUCCESS) {			
			res.sendRedirect("/bank/show");
		}else {
			HttpSession session=req.getSession();
			session.setAttribute("code", index);
			res.sendRedirect("/bank/error.jsp");
		}
	}

}
