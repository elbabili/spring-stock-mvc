package fr.fms.web;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.fms.business.ImplCaddy;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CustomerRepository;
import fr.fms.dao.OrderItemRepository;
import fr.fms.dao.OrderRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Customer;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;

@Controller
public class CaddyController {
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	ImplCaddy implCaddy;
	
	@GetMapping("/vcaddy")		//afficher un message pour indiquer que le panier est vide + si ajout indiquer avec un cigle
	public String viewCaddy(Model model) {
		model.addAttribute("caddy",implCaddy.getCaddy());		
		double total = implCaddy.getTotalAmount();
		model.addAttribute("total",total);
		return "caddy";
	}
	
	@GetMapping("/acaddy")
	public String addArticleCaddy(Long id, int page, String keyword , Long idCat) {	//ajout d'un article au caddy		
		implCaddy.addArticle(articleRepository.getById(id));
		return "redirect:/index?page="+page+"&keyword="+keyword + "&idCat=" + idCat;
	}
	
	@GetMapping("/dcaddy")
	public String deleteArticleCaddy(Long id) {
		implCaddy.delArticle(id);
		return "redirect:/vcaddy"; 
	}	
	
	@GetMapping("/order")		
	public String order(Model model) {		
		if(implCaddy.isEmpty())	return "redirect:/index";
		model.addAttribute("customer", new Customer());
		return "order";
	}
	
	@PostMapping("/porder")
	public String postOrder(@Valid Customer customer , BindingResult bindingResult , Model model) {	//les infos saisies par l'utilisateur doivent être valide
		if(bindingResult.hasErrors())	return "order";
		
		model.addAttribute("caddy",implCaddy.getCaddy());
		double total = implCaddy.getTotalAmount();
		model.addAttribute("total",total);		
		model.addAttribute("customer",customer);		
		implCaddy.setCustomer(customer);
		return "recap";
	}
	
	@GetMapping("/confirm")
	public String confirm() {
		if(implCaddy.isEmpty())	return "redirect:/index";
		
		//ajout d'un client en base : ToDo s'il existe déjà..
		Customer customer = implCaddy.getCustomer();
		customerRepository.save(customer);
		
		//insertion en base de la commande
		Order order = new Order(null,new Date(),implCaddy.getTotalAmount(),customer,null,null);
		orderRepository.save(order);
		
		//insertion en base des commandés minifiées
		for(Article article : implCaddy.getCaddy()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setArticle(article);
			orderItem.setPrice(article.getPrice());
			orderItem.setQuantity(article.getQuantity());			
			orderItemRepository.save(orderItem);
		}		
		
		//suppression du caddy et renvoi vers une page de remerciement
		implCaddy.delCaddy();
		
		//ToDo renvoi vers une page de paiement suivi envoi mail de confirmation si ok...
		
		return "thanks";
	}
}
