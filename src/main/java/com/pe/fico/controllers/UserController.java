package com.pe.fico.controllers;

import java.util.List;
import java.util.Map;
import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.fico.entities.Users;
import com.pe.fico.service.IUserService;

@Controller
@RequestMapping("users")
//@Secured("ROLE_ADMIN")
public class UserController {

	@Autowired
	private IUserService uS;

	@Autowired
	private BCryptPasswordEncoder passwordE;

	@GetMapping("/new")
	public String newUser(Model model) {

		model.addAttribute("user", new Users());
		model.addAttribute("listaUsuarios", uS.list());
		model.addAttribute("user", new Users());
		return "user/user";
	}
	
	@GetMapping("/home")
	public String newHome(Model model) {
		return "fragments/home";
	}

	@RequestMapping("/save")
	public String saveUser(@ModelAttribute @Valid Users objPro, BindingResult result, Model model)
			throws ParseException {
		if (result.hasErrors()) {
			
			return "user/user";
		} else {
			String bcryptPassword = passwordE.encode(objPro.getPassword());
			objPro.setPassword(bcryptPassword);
			boolean flag = uS.insertboo(objPro);
			if (flag) {
				return "redirect:/users/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
                return "redirect:/users/new";
			}
		}
	}

	@GetMapping("/list")
	public String listUser(Model model) {
		try {
			model.addAttribute("user", new Users());
			model.addAttribute("listaUsuarios", uS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "user/listUser";
	}

	@RequestMapping("/list")
    public String listCourse(Map<String, Object> model) {
        model.put("listaUsuarios", uS.list());
        return "user/listUsers";
    }

	
	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") long id, Map<String, Object> model, RedirectAttributes flash) {

		Users user = uS.listarId(id);

		if (user == null) {
			flash.addFlashAttribute("error", "El registro no existe en la base de datos");
			return "usersecurity/listUser";
		}

		model.put("user", user);
		model.put("titulo", "Detalle de registro: " + user.getName());

		return "user/ver";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable long id, Model model, RedirectAttributes objRedir) {

		Users user = uS.listarId(id);
		if (user == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrira un error");
			return "user/user";
		} else {
			model.addAttribute("user", user);
			return "user/user";
		}
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "id") Integer id, Model model, @ModelAttribute Users user) {

		try {
			if (id != null && id > 0) {
				uS.delete(id);
				model.addAttribute("mensaje", "Usuario eliminado");
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar");
		}
		model.addAttribute("user", user);
		model.addAttribute("listaUsuarios", uS.list());
		return "user/listUser";
	}

	@RequestMapping("/search")
	public String findByUser(Map<String, Object> model, @ModelAttribute Users user) {

		List<Users> listUsers;
		user.setName(user.getName());
		listUsers = uS.findByNameUsers(user.getName());
		model.put("user", new Users());
		if (listUsers.isEmpty()) {
			listUsers = uS.findByNameLikeIgnoreCase(user.getName());
		}
		if (listUsers.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaUsuarios", listUsers);
		return "user/listUser";
	}
	
	@GetMapping(value = "/profile/{username}")
	public String viewprofile(@PathVariable(value = "username")String name, Map<String, Object> model, RedirectAttributes flash) {

		Users user = uS.listName(name);

		if (user == null) {
			flash.addFlashAttribute("error", "El registro no existe en la base de datos");
			return "user/listUser";
		}

		model.put("user", user);
		model.put("titulo", "Detalle de registro: " + user.getName());

		return "user/ver";
	}
}
