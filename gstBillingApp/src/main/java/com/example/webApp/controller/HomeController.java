package com.example.webApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.webApp.model.Product;
import com.example.webApp.repository.ProductRepository;



@RestController
public class HomeController {
	
	Map<Integer, Product> lst=new HashMap<>();
	
	@Autowired
	ProductRepository repo;
	
	
	@RequestMapping(value ="/prod/code", method = {RequestMethod.POST})
	public ModelAndView getProd(int id) {
		ModelAndView mav=new ModelAndView("addProd");
		mav.addObject("prod", repo.findById(id).orElse(new Product()));
		return mav;
		//repo.findById(id).orElse(new Product());
		//return repo.findById(id);
	}
	
	@RequestMapping(value ="/prod/name", method = RequestMethod.POST)
	public ModelAndView getProd(String name) {
		ModelAndView mav=new ModelAndView("addProd");
		mav.addObject("prod", repo.findByName(name));
		return mav;
	}
	
	@RequestMapping(value ="/addList", method = RequestMethod.POST)
	public ModelAndView addlist(Product prod) {
		ModelAndView mav=new ModelAndView("billing");
		lst.put(prod.getId(), prod) ;
		mav.addObject("prod", lst.values());
		mav.addObject("qty",1);
		return mav;
	}
	
	@GetMapping("/prods")
	public ModelAndView getProds() {
//	List<Product>	return (List<Product>) repo.findAll();
		ModelAndView mav =new ModelAndView("entry");
		mav.addObject("messages", repo.findAll());
		return mav;
	}
	
	
	@PostMapping("/editProd")
	public ModelAndView editProd(Product prod){
			ModelAndView mav =new ModelAndView("index");
			mav.addObject("delcondition", "false");
			mav.addObject("condition", "true");
			mav.addObject("msg", "successfully Inserted Value");
			repo.save(prod);
			return mav;
	}
	
	@PostMapping("/prod")
	public ModelAndView addProd(Product prod){
			ModelAndView mav =new ModelAndView("index");
			mav.addObject("delcondition", "false");
			
			
			List<Product> lst = (List<Product>) repo.findAll();
			int flag=0;
			for(Product item:lst) {
				if(item.getId()==prod.getId()) {
					flag=1;
				}
			}
			if(flag==0){
				repo.save(prod);
				mav.addObject("condition", "true");
				mav.addObject("msg", "successfully Inserted Value");
				}
			else {
				mav.addObject("errorCondition","true");
				mav.addObject("error", "Id already Present");
				}
			
			return mav;
	}
	
	@GetMapping("prod/edit/details")
	public ModelAndView editprod(int id,String name,int price){
		ModelAndView mav=new ModelAndView("edit");
		mav.addObject("id", id );
		mav.addObject("name", name );
		mav.addObject("price", price );
		return mav;
	}
	
	@RequestMapping(value="delete/details",method = {RequestMethod.GET})
	public ModelAndView delprod(int id){
		ModelAndView mav=new ModelAndView("index");
		repo.deleteById(id);
		mav.addObject("delcondition", "true");
		mav.addObject("delmsg", "Product deleted");
		return mav;
	}
	
	
}
