package com.model2.mvc.services.purchase.test;

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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml"})
public class PurchaseTestApp {
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	
	//@Test
	public void testAddPurchase() throws Exception {
		System.out.println("=========================================================");
		User user = new User();
		user.setUserId("user01");
		
		Product product= new Product();
		product.setProdNo(10062);
		
		Purchase purchase = new Purchase();
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setPaymentOption("0");
		purchase.setReceiverName("user01");
		purchase.setReceiverPhone("00011112222");
		purchase.setDlvyAddr("집");
		purchase.setDlvyRequest("당일");
		purchase.setDlvyDate("20190423");
		
		System.out.println("insert 할 purchase : " + purchase);		
		
		System.out.println("insert 결과 : " + purchaseService.addPurchase(purchase));
				
		System.out.println("=========================================================\n");
	}
	
	//@Test
	public void testDeletePurchase() throws Exception{
		System.out.println("=========================================================");
		int tranNo = 10020;
		
		System.out.println("delete 결과 : " + purchaseService.deletePurchase(tranNo));
				
		System.out.println("=========================================================\n");
	}
	
	//@Test
	public void testGetPurchase() throws Exception{
		System.out.println("=========================================================");
		int tranNo = 10025;
		
		System.out.println("getUser 결과 : " + purchaseService.getPurchase(tranNo));
				
		System.out.println("=========================================================\n");
	}
	
	//@Test
	public void testUpdatePurchase() throws Exception{
		System.out.println("=========================================================");
		Purchase purchase = new Purchase();
		purchase.setPaymentOption("1");
		purchase.setReceiverName("user01");
		purchase.setReceiverPhone("99988887777");
		purchase.setDlvyAddr("update");
		purchase.setDlvyRequest("내일");
		purchase.setDlvyDate("20190424");
		purchase.setTranNo(10025);

		System.out.println("updateUser 결과 : " + purchaseService.updatePurchase(purchase));
				
		System.out.println("=========================================================\n");
	}
	
	//@Test
	public void testUpdateTranCode() throws Exception{
		System.out.println("=========================================================");
		Product purchaseProd = new Product();
		purchaseProd.setProdNo(10062);
		
		Purchase purchase = new Purchase();
		//tranNo나 prodNo 둘중 하나를 선택해서 tranCode Update
		//purchase.setTranNo(10025);
		purchase.setPurchaseProd(purchaseProd);
		purchase.setTranCode("3");

		System.out.println("updateUser 결과 : " + purchaseService.updateTranCode(purchase));
				
		System.out.println("=========================================================\n");
	}
	
	@Test
	public void testGetPurchaseList() throws Exception{
		System.out.println("=========================================================");
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setUserId("user01");
		
		
		Map<String,Object> map = purchaseService.getPurchaseList(search);
		List<Purchase> list = (List<Purchase>) map.get("list");
		int totalCount = (int) map.get("totalCount");
		
		System.out.println("getUserList 결과 : " + list);
		System.out.println("totalCount : " + totalCount);
				
		System.out.println("=========================================================\n");
	}
}
