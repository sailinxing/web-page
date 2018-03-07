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
		 * Ҫ��ʵ�ַ�ҳ�Ĺ��ܣ��ͱ���������������:���������ݽ�����װ��pagebaen�����У��ڸ���䴫��
		 * ��ǰҳ	   currentPage �����ݴӿͻ��˻�� //1��ʾ��һҳ
		 * ÿҳ��ʾ������    currentCount  ������Ҳ�ӿͻ��˻��  Ŀǰ��������д��  12��
		 * ����������	totalCount
		 * ��ҳ��		totalPage
		 * ��ǰҳ�ϵ�����     PageData 
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