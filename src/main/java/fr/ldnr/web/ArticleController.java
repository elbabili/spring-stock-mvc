package fr.ldnr.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.dao.CategoryRepository;
import fr.ldnr.entities.Article;

@Controller
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/index")			 
	public String index(Model model, @RequestParam(name="page" , defaultValue = "0") int page, 
									 @RequestParam(name="keyword" , defaultValue = "") String kw,
									 @RequestParam(name="idCat" , defaultValue = "0") Long idCat) {	
		Page<Article> articles = null;
		if(idCat > 0)	{	//on souhaite afficher tous les articles d'une catégorie
			articles = articleRepository.findByCategoryId(idCat, PageRequest.of(page, 5));
		}
		else articles = articleRepository.findByDescriptionContains(kw , PageRequest.of(page, 5));	
		model.addAttribute("idCat",idCat);

		model.addAttribute("listArticle",articles.getContent());	//pour récupérer sous forme de liste la page pointée		
		
		//pour afficher des liens de pagination permettant à l'utilisateur de passer d'une page à l'autre, il faut :
		//- récupérer le nombre total de pages
		//- l'injecter dans le model sous forme de tableau d'entier
		//- sur la partie html il suffira de boucler sur ce tableau pour afficher toutes les pages
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		
		//s'agissant de l'activation des liens de navigation, il faut transmettre à la vue la page courante
		//thymeleaf pourra delors vérifier si la page courante est égal à l'index de la page active
		model.addAttribute("currentPage",page);
		
		//afin de garder afficher le mot clé dans le formulaire de recherche une fois l'action validée, 
		//il faut le transmettre à la vue via le modèle
		model.addAttribute("keyword",kw);
		
		//je souhaite afficher l'ensemble des catégories en base sur la page des articles
		model.addAttribute("categories",categoryRepository.findAll());
		
		return "articles";	
	}
	
	@GetMapping("/delete")		//on peut ne pas préciser les paramètres de la requete, il va rechercher les variables correspondantes
	public String delete(Long id, int page, String keyword , Long idCat) {
		//ToDo avant de supprimer un article, il faut supprimer les commandes qui y font références OrderItem/Order 
		articleRepository.deleteById(id);		
		return "redirect:/index?page="+page+"&keyword="+keyword + "&idCat=" + idCat;
	}
	
	@GetMapping("/edit")	//mettre à jour un article
	public String edit(Long id, Model model) {
		Article article = articleRepository.getById(id);
		model.addAttribute("categories",categoryRepository.findAll());
		model.addAttribute("article", article);
		return "edit";
	}
	
	@GetMapping("/article")		//ajouter un nouvel article
	public String article(Model model) {
		model.addAttribute("article" , new Article());		//injection d'un article par défaut dans le formualaire de la vue article		
		model.addAttribute("categories",categoryRepository.findAll());
		return "article";
	}
	
	@PostMapping("/save")		//sauvegarder	
	public String save(@Valid Article article, BindingResult bindingResult) {		
		if(bindingResult.hasErrors()) {
			return "article";
		}
		articleRepository.save(article);
		return "redirect:/index";
	}
	
	@PostMapping("/update")		//mettre à jour	
	public String update(@Valid Article article, BindingResult bindingResult) {		
		if(bindingResult.hasErrors()) {
			return "edit";
		}
		articleRepository.save(article);
		return "redirect:/index";
	}
}
