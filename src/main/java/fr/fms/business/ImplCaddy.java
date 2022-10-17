package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.fms.entities.Article;
import fr.fms.entities.Customer;

@Service //pour que spring puisse instancier cette classe 
public class ImplCaddy implements ICaddy {
	private HashMap<Long, Article> caddy;
	private Customer customer;
	
	public ImplCaddy() {
		caddy = new HashMap<Long, Article>();
		customer = null;
	}

	@Override
	public void addArticle(Article article) {	
		Article a = caddy.get(article.getId()); 
		if(a != null) {		
			a.setQuantity(a.getQuantity()+1);	//si l'article est déjà présent
		}
		else caddy.put(article.getId(), article);		
	}

	@Override
	public void delArticle(Long id) {
		caddy.remove(id);		
	}

	@Override
	public void delCaddy() {
		caddy.clear();		
	}

	@Override
	public List<Article> getCaddy() {
		return new ArrayList<Article>(caddy.values());
	}

	public double getTotalAmount() {
		double total = 0;
		for(Article article : caddy.values()) {
			total += article.getPrice()*article.getQuantity();
		}
		return total;
	}
	
	public boolean isEmpty() {
		if(caddy.isEmpty())	return true;
		else return false;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
