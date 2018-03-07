package com.lixinxin.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.lixinxin.domain.Product;
import com.lixinxin.service.ShowProductsService;

public class AdminEditProServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> map = request.getParameterMap();
		Product product=new Product();
		try {
			BeanUtils.populate(product,map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		product.setPimage("products/1/c_0014.jpg");//��ΪͼƬ�ϴ�֮�����ר�õĿ�ܹ��ߣ�����������д��
		product.setPdate(new Date());//�ϼ�ʱ��
		product.setPflag(1);//�ϼ�״̬
		ShowProductsService service=new ShowProductsService();
		try {
			service.editProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/adminProductList");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}