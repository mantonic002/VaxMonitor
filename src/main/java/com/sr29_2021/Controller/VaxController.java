package com.sr29_2021.Controller;

import com.sr29_2021.Exceptions.UserNotFoundException;
import com.sr29_2021.Model.Manufacturer;
import com.sr29_2021.Model.UserRole;
import com.sr29_2021.Model.Vax;
import com.sr29_2021.Service.ManufacturerService;
import com.sr29_2021.Service.UserService;
import com.sr29_2021.Service.VaxService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class VaxController {

    @Autowired
    private UserService userService;

    @Autowired
    private VaxService service;

    @Autowired
    private ManufacturerService manService;

    @GetMapping("/vaxes")
    public String showVaxList(Model model, HttpServletRequest request) throws UserNotFoundException {
        List<Vax> list = service.listAll();
        model.addAttribute("listVaxes", list);

        Cookie[] cookies = request.getCookies();
        if(userService.checkCookies(cookies, UserRole.ADMIN)){
            return "admin_layouts/vaxes";
        }
        return "access_denied";
    }

    @GetMapping("/staff/vax")
    public String showVaxStaff(Model model, HttpServletRequest request) throws UserNotFoundException {
        List<Vax> list = service.listAll();
        model.addAttribute("listVaxes", list);

        Cookie[] cookies = request.getCookies();
        if(userService.checkCookies(cookies, UserRole.STAFF)){
            return "staff_layouts/staff_vaccine";
        }
        return "access_denied";
    }

    @GetMapping("/vaxes/new")
    public String showNewForm(Model model, HttpServletRequest request) throws UserNotFoundException {
        List<Manufacturer> manufacturers = manService.listAll();

        model.addAttribute("vax", new Vax());
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("pageTitle", "Add new vax");

        Cookie[] cookies = request.getCookies();
        if(userService.checkCookies(cookies, UserRole.ADMIN)){
            return "admin_layouts/vax_form";
        }
        return "access_denied";
    }

    @PostMapping("/vaxes/save")
    public String saveVax(Vax Vax, RedirectAttributes ra) {
        Vax.setManufacturer(manService.get(Vax.getManufacturerId()));
        service.save(Vax);
        ra.addFlashAttribute("message", "Vax has been saved");
        return "redirect:/vaxes";
    }

    @GetMapping("/vaxes/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, HttpServletRequest request) throws UserNotFoundException {
        List<Manufacturer> manufacturers = manService.listAll();
        Vax Vax = service.get(id);
        model.addAttribute("vax", Vax);
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("manufacturer", Vax.getManufacturer());
        model.addAttribute("pageTitle",
                "Edit vax (name:" + Vax.getName() + ", manufacturer:" + Vax.getManufacturer().getName() + ")");

        Cookie[] cookies = request.getCookies();
        if(userService.checkCookies(cookies, UserRole.ADMIN)){
            return "admin_layouts/vax_form";
        }
        return "access_denied";
    }

    @GetMapping("/vaxes/delete/{id}")
    public String deleteVax(@PathVariable("id") Integer id, RedirectAttributes ra, HttpServletRequest request) throws UserNotFoundException {
        service.delete(id);
        ra.addFlashAttribute("message", "Vax has been deleted");

        Cookie[] cookies = request.getCookies();
        if(userService.checkCookies(cookies, UserRole.ADMIN)){
            return "redirect:/vaxes";
        }
        return "access_denied";
    }

}
