package fr.fms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.fms.business.ImplCaddy;
import fr.fms.entities.Article;

@SpringBootTest
class SpringStockMvcApplicationTests {
	@Autowired
	ImplCaddy implCaddy;
	
	@Test
	void contextLoads() {
		assertEquals(10,5*2);
	}
	
	@Test
	void testCaddy() {
		implCaddy.addArticle(new Article((long)1,"Samsung S8",250,1,null));
		implCaddy.addArticle(new Article((long)1,"Samsung S9",250,1,null));
		implCaddy.addArticle(new Article((long)2,"Iphone 10",500,1,null));
		
		assertEquals(implCaddy.getTotalAmount(),1000);
	}
}
