package com.pe.fico.controllers;

import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;

import com.pe.fico.entities.Course;
import com.pe.fico.service.ICampusService;
import com.pe.fico.service.ICourseService;
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
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private ITeacherService tS;

    @Autowired
    private ICourseService cS;

    @Autowired
    private ICampusService caS;

    @GetMapping("/new")
    public String newCourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("listaProfesores", tS.list());
        model.addAttribute("listaCampus", caS.findAll());
        model.addAttribute("course", new Course());
        return "course/course";
    }

    @GetMapping("/list")
    public String listCourses(Model model) {
        try {
            model.addAttribute("course", new Course());
            model.addAttribute("listaCursos",cS.findAll());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "course/listCourses";
    }

    @RequestMapping("/save")
    public String insertTeacher(@ModelAttribute @Valid Course objPro, BindingResult binRes, Model model)
            throws ParseException {
        if (binRes.hasErrors()) {
            model.addAttribute("listaProfesores", tS.list());
            model.addAttribute("listaCampus", caS.findAll());
            return "course/course";
        } else {
            boolean flag = cS.save(objPro);
            if (flag) {
                model.addAttribute("mensaje", "Guardado correcto");
                return "redirect:/courses/list";
            } else {
                model.addAttribute("mensaje", "Ocurrió un error");
                return "redirect:/courses/new";
            }
        }

    }

    @RequestMapping("/list")
    public String listCourse(Map<String, Object> model) {
        model.put("listaCursos", cS.findAll());
        return "course/listCourses";
    }

    @RequestMapping("/listarId")
    public String listarId(Map<String, Object> model, @ModelAttribute Course pro) {
        cS.listId(pro.getIdCourse());
        return "course/listCourses";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

        Course objPro = cS.listId(id);
        if (objPro == null) {
            objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
            return "redirect:/courses/list";
        } else {
            model.addAttribute("listaProfesores", tS.list());
            model.addAttribute("listaCampus", caS.findAll());
            model.addAttribute("course", objPro);
            return "course/course";
        }
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model, RedirectAttributes objRedir) {

        Course objPro = cS.listId(id);
        if (objPro == null) {
            objRedir.addFlashAttribute("mensaje", " un error");
            return "redirect:/courses/list";
        } else {
            cS.delete(id);
            return "redirect:/courses/list";
        }
    }

    

}
