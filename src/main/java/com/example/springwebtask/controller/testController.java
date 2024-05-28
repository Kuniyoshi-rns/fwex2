package com.example.springwebtask.controller;

import com.example.springwebtask.entity.Product;
import com.example.springwebtask.form.LoginForm;
import com.example.springwebtask.form.NewProductForm;
import com.example.springwebtask.service.ICategoryService;
import com.example.springwebtask.service.IProductService;
import com.example.springwebtask.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class testController {

    @Autowired
    IUserService userService;
    @Autowired
    IProductService productService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    private HttpSession session;

    @GetMapping("/index")
    public String index(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "index";
    }

    @PostMapping("/index")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) {

        // バリデーション
        if(bindingResult.hasErrors()) {
            System.out.println(loginForm);
            return "index";
        }else {
            var user = userService.findByLoginForm(loginForm);
            if (user != null) {
                session.setAttribute("user", user);
                return "redirect:/menu";
            } else {
                model.addAttribute("errorMes","IDまたはパスワードが不正です");
                return "index";
            }
        }
    }
    @GetMapping("/menu")
    public String menu(@RequestParam(name = "keyword",defaultValue = "") String find, BindingResult bindingResult,Model model) {
        var list = productService.findByName(find);
        model.addAttribute("length",list.size());
        model.addAttribute("products",list);
        return "menu";
    }

    @GetMapping("/insert")
    public String insert(@Validated @ModelAttribute("newProductForm") NewProductForm productForm, Model model){
        var list = categoryService.findAll();
        model.addAttribute("categories",list);
        return "insert";
    }
}
