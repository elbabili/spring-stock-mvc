package fr.ldnr.business;

import java.util.List;

import fr.ldnr.entities.Article;

interface ICaddy {
	public void addArticle(Article article);	//ajouter un article au caddy
	public void delArticle(Long id);			//supprimer un article du caddy
	public void delCaddy();						//supprime le caddy en cours
	public List<Article> getCaddy();			//renvoi le contenu du caddy	
	//public Order orderCaddy();				//passer commande du caddy
}
