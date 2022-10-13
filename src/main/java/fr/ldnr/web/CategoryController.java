package fr.ldnr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.ldnr.dao.CategoryRepository;
import fr.ldnr.entities.Category;

@Controller
public class CategoryController {
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/category")
	public String categories(Long id, Model model) {
		Category category = categoryRepository.getById(id+1);
		
		model.addAttribute("idCat",category.getId());
		
		return "redirect:/index?idCat=" + category.getId();
	}
}
