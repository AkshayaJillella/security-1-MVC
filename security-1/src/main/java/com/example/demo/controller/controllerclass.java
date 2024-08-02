package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.modelclass;
import com.example.demo.repository.repositoryinterface;

import jakarta.servlet.http.HttpSession;

@Controller
public class controllerclass {
	@Autowired 
	repositoryinterface repo;
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	@GetMapping("/")
	public String home(Model m) {
		List<modelclass> li=(List<modelclass>) repo.findAll();
		m.addAttribute("add-products",li);
		return "Home";
		
	}
	@GetMapping("/getbyid/{id}")
	public String getby(@PathVariable(value="id") int id, Model m) {
		Optional<modelclass> c=repo.findById(id);
		modelclass v=c.get();
		m.addAttribute("products",v);
		return "Edit";
		
	}
	@PostMapping("/save_products")
	public String insert(@ModelAttribute modelclass m,HttpSession session) {
		repo.save(m);
		session.setAttribute("message", "successfullyadded");
		return "redirect:/loadform";
	}
	@PutMapping("/update")
	public String edit(@ModelAttribute modelclass m,HttpSession session) {
		repo.save(m);
		session.setAttribute("message", "successfully updated");
		return "redirect:/";
	}
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") int id,HttpSession session) {
		repo.deleteById(id);
		session.setAttribute("message", "succesfully deleted");
		return "redirect:/";
	}
	
	@GetMapping("/loaform")
	public String loadform() {
		return "Add";
	}

}
