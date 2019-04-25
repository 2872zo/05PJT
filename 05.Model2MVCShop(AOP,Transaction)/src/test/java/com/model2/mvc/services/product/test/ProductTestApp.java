package com.model2.mvc.services.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.user.UserService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml"})
public class ProductTestApp {
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	
	//@Test
	public void testInsertProduct() throws Exception {
		System.out.println("=========================================================");
		Product product = new Product();
		product.setProdName("테스트insert상품");
		product.setProdDetail("테스트");
		product.setPrice(1000);
		product.setManuDate("20190422");
		
		System.out.println("insert 할 product : " + product);		
		
		System.out.println("insert 결과 : " + productService.addProduct(product));
				
		System.out.println("=========================================================\n");
	}
	
	//@Test
	public void testDeleteProduct() throws Exception{
		System.out.println("=========================================================");
		int prodNo = 10061;
		
		System.out.println("delete 결과 : " + productService.deleteProduct(prodNo));
				
		System.out.println("=========================================================\n");
	}
	
	//@Test
	public void testGetUser() throws Exception{
		System.out.println("=========================================================");
		int prodNo = 10061;
		
		System.out.println("getUser 결과 : " + productService.getProduct(prodNo));
				
		System.out.println("=========================================================\n");
	}
	
	//@Test
	public void testUpdateUser() throws Exception{
		System.out.println("=========================================================");
		Product product = new Product();
		product.setProdNo(10061);
		product.setProdName("update확인");
		product.setProdDetail("update");
		product.setPrice(99999);
		
		System.out.println("updateUser 결과 : " + productService.updateProduct(product));
				
		System.out.println("=========================================================\n");
	}
	
	@Test
	public void testGetProductList() throws Exception{
		System.out.println("=========================================================");
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("%자%");
		
		
		Map<String,Object> map = productService.getProductList(search);
		List<Product> list = (List<Product>) map.get("list");
		int totalCount = (int) map.get("totalCount");
				
		System.out.println("=========================================================\n");
	}
}
