package com.lixinxin.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lixinxin.domain.Product;
import com.lixinxin.domain.vo.PageBean;
import com.lixinxin.service.ShowProductsService;

public class ClientShowAllProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 要想实现分页的功能，就必须获得这五项数据:这五项数据将被封装进pagebaen对象中，在各层间传递
		 * 当前页	   currentPage 该数据从客户端获得 //1表示第一页
		 * 每页显示的条数    currentCount  该数据也从客户端获得  目前我们在这写死  12条
		 * 数据总条数	totalCount
		 * 总页数		totalPage
		 * 当前页上的数据     PageData 
		 *   
		 */
		String currentPage = request.getParameter("currentPage");
		int currentPageInt=1;
		if(currentPage!=null&&!"".equals(currentPage)){
			currentPageInt=Integer.parseInt(currentPage);
		}
		int currentCount=12;
		ShowProductsService service=new ShowProductsService();
		PageBean<Product> pageBean=null;
		try {
			pageBean=service.findProductByPagebean(currentPageInt,currentCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}