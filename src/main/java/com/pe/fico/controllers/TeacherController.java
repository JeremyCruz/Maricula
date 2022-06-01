package com.pe.fico.controllers;
import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;

import com.pe.fico.entities.Teacher;
import com.pe.fico.service.IFacultyService;
import com.pe.fico.service.ITeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private ITeacherService  tS;

    @Autowired
    private IFacultyService fS;

    @GetMapping("/new")
    public String newTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("listaFacultad", fS.findAll());
        model.addAttribute("teacher", new Teacher());
        return "teacher/teacher";
    }

    @GetMapping("/list")
    public String listTeachers(Model model) {
        try {
            model.addAttribute("teacher", new Teacher());
            model.addAttribute("listaProfesores", tS.list());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "teacher/listTeachers";
    }

    @RequestMapping("/save")
    public String insertTeacher(@ModelAttribute @Valid Teacher objPro, BindingResult binRes, Model model)
            throws ParseException {
        if (binRes.hasErrors()) {
            model.addAttribute("listaFacultad", fS.findAll());
            return "teacher/teacher";
        } else {
            boolean flag = tS.save(objPro);
            if (flag) {
                return "redirect:/teachers/list";
            } else {
                model.addAttribute("mensaje", "Ocurrió un error");
                return "redirect:/teachers/new";
            }
        }

    }

    @RequestMapping("/list")
    public String listTeacher(Map<String, Object> model) {
        model.put("listaProfesores", tS.list());
        return "teacher/listTeachers";
    }

    @RequestMapping("/listarId")
    public String listarId(Map<String, Object> model, @ModelAttribute Teacher pro) {
        tS.listId(pro.getIdTeacher());
        return "teacher/listTeachers";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

        Teacher objPro = tS.listId(id);
        if (objPro == null) {
            objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
            return "redirect:/teachers/list";
        } else {
            model.addAttribute("listaFacultad", fS.findAll());
            model.addAttribute("teacher", objPro);
            return "teacher/teacher";
        }
    }

    @RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Teacher objPro = tS.listId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/teachers/list";
		} else {
			tS.delete(id);
			return "redirect:/teachers/list";
		}
	}



}
