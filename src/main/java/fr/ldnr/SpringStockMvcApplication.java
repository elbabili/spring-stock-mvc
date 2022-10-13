/**
 * Version 1.0 : Réalisation d'une appli web de gestion de stock d'article proposant sur la partie front d'afficher des articles, supprimer, ajouter, rechercher
 * avec gestion des accès via spring security 
 * 
 * Version 1.1 : ajout de la possibilitée d'afficher les articles par catégories + gestion des rôles + possibilitée de passer commande pour un user connecté
 * 
 * @author El babili - 2021
 * 
 */

package fr.ldnr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.dao.CategoryRepository;
import fr.ldnr.entities.Article;
import fr.ldnr.entities.Category;

@SpringBootApplication
public class SpringStockMvcApplication implements CommandLineRunner {
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringStockMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		System.out.println("hello");
//		articleRepository.findAll().forEach(a -> articleRepository.delete(a));
		
//		Category smartphone = categoryRepository.save(new Category("Smartphone"));
//		Category pc = categoryRepository.save(new Category("Ordinateur"));
//		Category tablet = categoryRepository.save(new Category("Tablette"));
//		Category printer = categoryRepository.save(new Category("Imprimante"));
//		Category camera = categoryRepository.save(new Category("Camera"));
//		Category tv = categoryRepository.save(new Category("TV"));
//		Category telescope = categoryRepository.save(new Category("Telescope"));
//		Category gps = categoryRepository.save(new Category("Gps"));
//		Category enceinte = categoryRepository.save(new Category("Enceinte"));
//		
//		articleRepository.save(new Article(null,"Samsung S8",250,1,smartphone));
//		articleRepository.save(new Article(null,"Samsung S9",300,1,smartphone));
//		articleRepository.save(new Article(null,"Iphone 10",500,1,smartphone));		
//		articleRepository.save(new Article(null,"Xiaomi MI11",100,1,smartphone));
//		articleRepository.save(new Article(null,"OnePlus 9 Pro",200,1,smartphone));
//		articleRepository.save(new Article(null,"Google Pixel 5",350,1,smartphone));
//		articleRepository.save(new Article(null,"Poco F3",150,1,smartphone));
//		
//		articleRepository.save(new Article(null,"Dell 810",550,1,pc));
//		articleRepository.save(new Article(null,"Asus F756",600,1,pc));
//		articleRepository.save(new Article(null,"Asus E80",700,1,pc));
//		articleRepository.save(new Article(null,"MacBook Pro",1000,1,pc));
//		articleRepository.save(new Article(null,"MacBook Air",1200,1,pc));
//		
//		articleRepository.save(new Article(null,"IPad 5",300,1,tablet));
//		articleRepository.save(new Article(null,"IPad 7",500,1,tablet));
//		articleRepository.save(new Article(null,"Galaxy Tab",400,1,tablet));
//		
//		articleRepository.save(new Article(null,"Canon MG30",50,1,printer));
//		articleRepository.save(new Article(null,"Canon MG50",60,1,printer));
//		articleRepository.save(new Article(null,"HP 800",50,1,printer));
//		articleRepository.save(new Article(null,"Epson 3T",100,1,printer));
//		
//		articleRepository.save(new Article(null,"GoPro 7",150,1,camera));
//		articleRepository.save(new Article(null,"GoPro 10",200,1,camera));
//		
//		articleRepository.save(new Article(null,"Panasonic HT",1500,1,tv));
//		articleRepository.save(new Article(null,"Philips L43",450,1,tv));		
		
		//articleRepository.findAll().forEach(a -> System.out.println(a));
		//categoryRepository.findAll().forEach(c -> System.out.println(c));
	}
}
