package ru.skanerxxl.rambler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.skanerxxl.rambler.database.entity.Product;
import ru.skanerxxl.rambler.database.entity.User;
import ru.skanerxxl.rambler.service.ServiceAnonymous;
import ru.skanerxxl.rambler.service.ServiceUser;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/")
@SessionAttributes({
        "idCC",
        "nameCC",
        "idPC",
        "namePC",
        "product",
        "listOfConsumerCategory",
        "error",
        "page"})
public class ControllerAnonymous {
    @Autowired
    private ServiceAnonymous serviceAnonymous;
    @Autowired
    private ServiceUser serviceUser;

    private boolean toggle = false;// переключатель для error

    private String utf8(final String paramValue) {
        String name = null;
        try {
            name = new String(paramValue.getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
    }


    //-------------------------Enter-------------------------//


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(Model model,
                               @RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password) {
        User user = new User(username, password);
        if (serviceUser.saveUser(user, "ROLE_USER")) {
            model.addAttribute("error", "");
            return "login";
        } else {
            model.addAttribute("error", "пользователь с таким именем уже существует");
            return "start";
        }
    }

    @RequestMapping("/login")
    public String readdressingLogin(Model model) {
        if (!toggle) {
            toggle = true;
            model.addAttribute("error", "");
            return "login";
        } else {
            model.addAttribute("error", "неверный логин или пароль");
            return "login";
        }
    }


    //-------------------------Start-------------------------//


    @RequestMapping("/")
    public String start(Model model) {
        model.addAttribute("listOfConsumerCategory", serviceAnonymous.listOfConsumerCategory());
//        создаём необходимые атрибуты в сессии
        Product product = new Product("");
        model.addAttribute("error", "");
        model.addAttribute("nameCC", "");
        model.addAttribute("idCC", -1);
        model.addAttribute("namePC", "");
        model.addAttribute("idPC", -1);
        model.addAttribute("product", product);
        toggle = false;
        return "start";
    }


    //-------------------------ProductCategory-------------------------//


    @RequestMapping("/product_category/{idCC}/{nameCC}")
    public String productCategory(Model model,
                                  @PathVariable(value = "idCC") long idCC,
                                  @PathVariable(value = "nameCC") String nameCC) {
        nameCC = utf8(nameCC);
        model.addAttribute("error", "");
        model.addAttribute("idCC", idCC);
        model.addAttribute("nameCC", nameCC);
        model.addAttribute("listOfProductCategory", serviceAnonymous.listOfProductCategory(idCC));
        return "category";
    }


    //-------------------------Product-------------------------//


    @RequestMapping("/list_product/{idPC}/{namePC}")
    public String listProduct(Model model,
                              @PathVariable(value = "idPC") long idPC,
                              @PathVariable(value = "namePC") String namePC) {
        namePC = utf8(namePC);
        model.addAttribute("listOfProduct", serviceAnonymous.listOfProduct(idPC));
        model.addAttribute("idPC", idPC);
        model.addAttribute("namePC", namePC);
        return "list_product";
    }

    @RequestMapping("/open_product/{idPr}/{page}")
    public String openProduct(Model model,
                              @PathVariable(value = "idPr") long idPr,
                              @PathVariable(value = "page") String page) {
        Product product = serviceAnonymous.openProduct(idPr);
        model.addAttribute("page", page);
        model.addAttribute("product", product);
        model.addAttribute("error", "");
        return "product";
    }

    @RequestMapping("/photo/{id}")
    public ResponseEntity<byte[]> viewPhoto(@PathVariable("id") long id) {
        return serviceAnonymous.viewPhoto(id);
    }

}
