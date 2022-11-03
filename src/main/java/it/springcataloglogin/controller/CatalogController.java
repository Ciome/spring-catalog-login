package it.springcataloglogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.springcataloglogin.entity.CatalogEntity;
import it.springcataloglogin.service.CatalogEntityService;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

	@Autowired
	private CatalogEntityService catalogEntityService;
	
	@GetMapping("/list")
	public String viewCatalog(Model model) {
		List<CatalogEntity> catalogEntities = catalogEntityService.findAll();
		model.addAttribute("catalogEntities", catalogEntities);
		return "catalog/catalog-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		model.addAttribute(new CatalogEntity());
		return "catalog/entity-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@ModelAttribute("id") int id, Model model) {
		model.addAttribute(catalogEntityService.findById(id));
		return "catalog/entity-form";
	}
	
	@PostMapping("/save")
	public String saveEntity(@ModelAttribute("catalogEntity") CatalogEntity catalogEntity) {
		catalogEntityService.save(catalogEntity);
		return "redirect:/catalog/list";
	}
	
	@GetMapping("/delete")
	public String showFormForDelete(@ModelAttribute("id") int id, Model model) {
		catalogEntityService.deleteById(id);
		return "redirect:/catalog/list";
	}
}
