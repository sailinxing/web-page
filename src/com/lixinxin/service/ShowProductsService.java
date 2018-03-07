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
	 * 要想实现分页的功能，就必须获得这五项数据:这五项数据将被封装进pagebaen对象中，在各层间传递
	 * 当前页	   currentPage 该数据从客户端获得 //0表示第一页
	 * 每页显示的条数    currentCount  该数据也从客户端获得  目前我们在这写死  12条
	 * 数据总条数	totalCount
	 * 总页数		totalPage
	 * 当前页上的数据     PageData    select * from product  limit ?,?
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
