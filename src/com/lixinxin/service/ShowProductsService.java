package com.lixinxin.service;

import java.sql.SQLException;
import java.util.List;

import com.lixinxin.dao.ShowProductsDao;
import com.lixinxin.domain.Product;
import com.lixinxin.domain.vo.Condition;
import com.lixinxin.domain.vo.PageBean;


public class ShowProductsService {

	public List<Product> findAllProducts() {
		ShowProductsDao dao=new ShowProductsDao();
		List<Product> productList=null;
		try {
			productList = dao.findAllProducts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	public void deleteProduct(String pid) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		dao.deleteProduct(pid);		
	}

	public void addProduct(Product product) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		dao.addProduct(product);		
	}

	public Product findProductById(String pid) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		Product product=dao.findProductById(pid);
		return product;
	}

	public void editProduct(Product product) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		dao.editProduct(product);
	}

	public List<Product> searchProducts(Condition condition) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		List<Product> productList=dao.searchProducts(condition);
		return productList;
	}
	/*
	 * Ҫ��ʵ�ַ�ҳ�Ĺ��ܣ��ͱ���������������:���������ݽ�����װ��pagebaen�����У��ڸ���䴫��
	 * ��ǰҳ	   currentPage �����ݴӿͻ��˻�� //0��ʾ��һҳ
	 * ÿҳ��ʾ������    currentCount  ������Ҳ�ӿͻ��˻��  Ŀǰ��������д��  12��
	 * ����������	totalCount
	 * ��ҳ��		totalPage
	 * ��ǰҳ�ϵ�����     PageData    select * from product  limit ?,?
	 *   
	 */
	public PageBean<Product> findProductByPagebean(int currentPageInt, int currentCount) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		Object totalQuery=dao.findTotalCount();
		Long total=(Long)totalQuery;
		int totalCount=total.intValue();
		int totalPage=(int)Math.ceil(totalCount*1.0/currentCount);
		int pageIndex=(currentPageInt-1)*currentCount; 
		List<Product> pageData=dao.findProductByLimit(pageIndex,currentCount);
		PageBean<Product> pageBean=new PageBean<>();
		pageBean.setCurrentCount(currentCount);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setPageData(pageData);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	public void deleteAllProduct(String[] deleteBoxes) throws SQLException {
		ShowProductsDao dao=new ShowProductsDao();
		for(String pid:deleteBoxes){
		dao.deleteProduct(pid);
		}
		
	}

}
