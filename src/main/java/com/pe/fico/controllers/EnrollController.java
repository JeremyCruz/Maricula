package com.pe.fico.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.fico.entities.Enroll;
import com.pe.fico.service.ICourseService;
import com.pe.fico.service.IEnrollService;
import com.pe.fico.service.IUserService;

@Controller
@RequestMapping("/enrolls")
public class EnrollController {
    @Autowired
    private ICourseService cS;

    @Autowired
    private IUserService uS;

    @Autowired
    private IEnrollService eS;

    @GetMapping("/new")
    public String newEnroll(Model model) {
        model.addAttribute("enroll", new Enroll());
        model.addAttribute("listaCursos", cS.findAvailableCourses());
        model.addAttribute("listaUsuarios", uS.list());
        model.addAttribute("enroll", new Enroll());
        return "enroll/enroll";
    }

    @RequestMapping("/coursebyuser/{id}")
    public String listCoursesByUser(@PathVariable String id, Model model) {
        List<Enroll> Aux = eS.findByUser(id);
        if (Aux.isEmpty()) {
            model.addAttribute("mensaje", "No hay datos que mostrar");
            return "enroll/listCoursesByStudent";
        }else{
            try {
                model.addAttribute("enroll", new Enroll());
                model.addAttribute("listaMatricula", eS.findByUser(id));
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
            }
            return "enroll/listCoursesByStudent";
        }
    }

    @GetMapping("/list")
    public String listEnroll(Model model) {
        try {
            model.addAttribute("enroll", new Enroll());
            model.addAttribute("listaMatricula", eS.findAll());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "enroll/listEnroll";
    }

    @RequestMapping("/save")
    public String insertEnroll(@ModelAttribute @Valid Enroll objPro, BindingResult binRes, Model model,
            RedirectAttributes objRedir)
            throws ParseException {
        if (binRes.hasErrors()) {
            model.addAttribute("listaCursos", cS.findAvailableCourses());
            model.addAttribute("listaUsuarios", uS.list());
            return "enroll/enroll";
        } else {
            int fla = eS.verify(objPro);
            if (fla == 0) {
                boolean flag = eS.save(objPro);
                if (eS.update_available(objPro)) {
                    cS.updateCheck(objPro.getCourse().getIdCourse());
                } else {
                    cS.updateCheckA(objPro.getCourse().getIdCourse());
                }
                if (flag) {
                    objRedir.addFlashAttribute("mensaje", "Matricula registrada");
                    return "redirect:/enrolls/list";
                } else {
                    model.addAttribute("mensaje", "Ocurrió un error");
                    return "redirect:/enrolls/new";
                }
            } else {
                model.addAttribute("listaCursos", cS.findAvailableCourses());
                model.addAttribute("listaUsuarios", uS.list());
                model.addAttribute("mensaje", "Ya se registro el usuario");
                return "enroll/enroll";
            }
        }

    }

    @RequestMapping("/list")
    public String listEnroll(Map<String, Object> model) {
        model.put("listaMatricula", eS.findAll());
        return "enroll/listEnroll";
    }

    @RequestMapping("/listarId")
    public String listarId(Map<String, Object> model, @ModelAttribute Enroll pro) {
        eS.listId(pro.getIdEnroll());
        return "course/listCourses";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

        Enroll objPro = eS.listId(id);
        if (objPro == null) {
            objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
            return "redirect:/enrolls/list";
        } else {
            model.addAttribute("listaCursos", cS.findAll());
            model.addAttribute("listaUsuarios", uS.list());
            model.addAttribute("enroll", objPro);
            return "enroll/enroll";
        }
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model, RedirectAttributes objRedir) {

        Enroll objPro = eS.listId(id);
        if (objPro == null) {
            objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
            return "redirect:/enrolls/list";
        } else {
            eS.delete(id);
            if (eS.update_available(objPro)) {
                cS.updateCheck(objPro.getCourse().getIdCourse());
            } else {
                cS.updateCheckA(objPro.getCourse().getIdCourse());
            }
            return "redirect:/enrolls/list";
        }
    }
}
