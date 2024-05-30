package com.example.springwebtask.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.*;

@Controller
public class testController {

    private final String[] infoList = {"","登録が完了しました","削除に成功しました","更新処理が完了しました"};
    private int successCheck = 0;//0:何もなし　1:登録　2:削除　3:更新

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
    public String menu(@RequestParam(name = "keyword",defaultValue = "") String find,Model model) {
        if(session.getAttribute("user") == null){
            return "redirect:/index";
        }
        var list = productService.findByName(find);
        model.addAttribute("products",list);
        model.addAttribute("successInfo",infoList[successCheck]);
        successCheck = 0;
        return "menu";
    }

    @GetMapping("/insert")
    public String gInsert(@ModelAttribute("productForm") NewProductForm productForm, Model model){
        var list = categoryService.findAll();
        model.addAttribute("categories",list);
        return "insert";
    }

    @PostMapping("/insert")
    public String pInsert(@Validated @ModelAttribute("productForm") NewProductForm productForm,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            var list = categoryService.findAll();
            model.addAttribute("categories",list);
            return "insert";
        }else{
            try{
                var nowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:dd");
                String time = nowTime.format(LocalDateTime.now());
                productService.insert(productForm,time);
                successCheck=1;
                return "redirect:/menu";
            }catch(Exception e){
                var list = categoryService.findAll();
                model.addAttribute("categories",list);
                model.addAttribute("errorMes","商品IDが重複しています");
                return "insert";
            }
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id ,Model model){
        model.addAttribute("product",productService.findById(id));
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("productForm") NewProductForm productForm,Model model){
        var product = productService.findById(id);
        productForm.setProductId(product.product_id());
        productForm.setProductName(product.name());
        productForm.setCategory(product.category());
        productForm.setPrice(String.valueOf(product.price()));
        productForm.setDescription(product.description());
        productForm.setImgPath(product.image_path());


        var list = categoryService.findAll();
        model.addAttribute("categories",list);
        return "updateInput";
    }

    @PostMapping("/update/{id}")
    public String pUpdate(@PathVariable("id") int id, @Validated @ModelAttribute("productForm") NewProductForm productForm,BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){
            var list = categoryService.findAll();
            model.addAttribute("categories",list);
            return "updateInput";
        }
        else {

            var product = productService.findById(id);
            try {
                var nowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:dd");
                String time = nowTime.format(LocalDateTime.now());
                productService.update(productForm,time,id);
                successCheck = 3;
                return "redirect:/menu";
            }catch (Exception e){
                var list = categoryService.findAll();
                model.addAttribute("categories",list);
                model.addAttribute("errorMes","商品IDが重複しています");
                return "updateInput";
            }
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") int id){
        productService.delete(id);
        successCheck = 2;
        return "redirect:/menu";
    }

    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "logout";
    }
}
