package ru.skanerxxl.rambler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skanerxxl.rambler.database.entity.*;
import ru.skanerxxl.rambler.service.ServiceAdmin;
import ru.skanerxxl.rambler.service.ServiceAnonymous;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@SessionAttributes({"listOfConsumerCategory",
        "idCC",
        "nameCC",
        "idPC",
        "namePC",
        "idPr",
        "cmnd",
        "page"})
public class ControllerAdmin {
    @Autowired
    private ServiceAdmin serviceAdmin;
    @Autowired
    private ServiceAnonymous serviceAnonymous;

    private String utf8(final String paramValue) {
        String name = null;
        try {
            name = new String(paramValue.getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
    }


    //-------------------------ConsumerCategory-------------------------//

    @RequestMapping(value = "/new_consumer_category", method = RequestMethod.POST)
    public String newConsumerCategory(Model model,
                                      @RequestParam(value = "newNameCC") String newNameCC,
                                      @RequestParam(value = "photo") MultipartFile photo,
                                      @ModelAttribute(value = "cmnd") String cmnd,
                                      @RequestParam(value = "command") String command) {
        if (command.equals(cmnd)) {
            newNameCC = utf8(newNameCC);
            try {
                ConsumerCategory consumerCategory = new ConsumerCategory(newNameCC, new Photo(photo.getOriginalFilename(), photo.getBytes()));
                serviceAdmin.createConsumerCategory(consumerCategory);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("error", "");
        } else {
            model.addAttribute("error", "пароль неверен");
        }
        model.addAttribute("cmnd", "");
        model.addAttribute("listOfConsumerCategory", serviceAnonymous.listOfConsumerCategory());
        return "start";
    }

    @RequestMapping(value = "/change_consumer_category", method = RequestMethod.POST)
    public String changeConsumerCategory(Model model,
                                         @RequestParam(value = "command") String command,
                                         @ModelAttribute(value = "cmnd") String cmnd,
                                         @RequestParam(value = "photo") MultipartFile photo,
                                         @ModelAttribute(value = "idCC") long idCC,
                                         @RequestParam(value = "newNameCC") String newNameCC) {
        if (command.equals(cmnd)) {
            newNameCC = utf8(newNameCC);
            try {
                Photo ph = new Photo(photo.getOriginalFilename(), photo.getBytes());
                serviceAdmin.changeConsumerCategory(idCC, newNameCC, ph);
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("error", "");
        } else {
            model.addAttribute("error", "пароль неверен");
        }
        model.addAttribute("cmnd", "");
        model.addAttribute("listOfConsumerCategory", serviceAnonymous.listOfConsumerCategory());
        return "start";
    }

    @RequestMapping("/delete_consumer_category")
    public String deleteConsumerCategory(Model model,
                                         @RequestParam(value = "command") String command,
                                         @ModelAttribute(value = "cmnd") String cmnd,
                                         @ModelAttribute(value = "idCC") long idCC) {
        if (command.equals(cmnd)) {
            serviceAdmin.deleteConsumerCategory(idCC);
            model.addAttribute("error", "");
        } else {
            model.addAttribute("error", "пароль неверен");
        }
        model.addAttribute("cmnd", "");
        model.addAttribute("listOfConsumerCategory", serviceAnonymous.listOfConsumerCategory());
        return "start";
    }

    @RequestMapping("/new_change_delete_consumer_category/{idCC}/{nameCC}")
    public void ncdConsumerCategory(Model model,
                                    @PathVariable(value = "idCC") long idCC,
                                    @PathVariable(value = "nameCC") String nameCC) {
        nameCC = utf8(nameCC);
        model.addAttribute("cmnd", new Administrator().getPassword());
        model.addAttribute("idCC", idCC);
        model.addAttribute("nameCC", nameCC);
    }


    //-------------------------ProductCategory-------------------------//

    @RequestMapping(value = "/new_product_category", method = RequestMethod.POST)
    public String newProductCategory(Model model,
                                     @ModelAttribute(value = "idCC") long idCC,
                                     @RequestParam(value = "newNamePC") String newNamePC,
                                     @RequestParam(value = "photo") MultipartFile photo,
                                     @ModelAttribute(value = "cmnd") String cmnd,
                                     @RequestParam(value = "command") String command) {
        if (command.equals(cmnd)) {
            newNamePC = utf8(newNamePC);
            try {
                ProductCategory productCategory = new ProductCategory(newNamePC, new Photo(photo.getOriginalFilename(), photo.getBytes()));
                serviceAdmin.createProductCategory(productCategory, idCC);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("error", "");
        } else {
            model.addAttribute("error", "пароль неверен");
        }
        model.addAttribute("cmnd", "");
        model.addAttribute("listOfProductCategory", serviceAnonymous.listOfProductCategory(idCC));
        return "category";
    }

    @RequestMapping(value = "/change_product_category", method = RequestMethod.POST)
    public String changeProductCategory(Model model,
                                        @ModelAttribute(value = "idPC") long idPC,
                                        @ModelAttribute(value = "idCC") long idCC,
                                        @ModelAttribute(value = "cmnd") String cmnd,
                                        @RequestParam(value = "command") String command,
                                        @RequestParam(value = "newNamePC") String newNamePC,
                                        @RequestParam(value = "photo") MultipartFile photo) {
        if (command.equals(cmnd)) {
            newNamePC = utf8(newNamePC);
            ProductCategory productCategory = null;
            try {
                productCategory = new ProductCategory(newNamePC, new Photo(photo.getOriginalFilename(), photo.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            serviceAdmin.changeProductCategory(idPC, productCategory);
            model.addAttribute("error", "");
        } else {
            model.addAttribute("error", "пароль неверен");
        }
        model.addAttribute("cmnd", "");
        model.addAttribute("listOfProductCategory", serviceAnonymous.listOfProductCategory(idCC));
        return "category";
    }

    @RequestMapping("/delete_product_category")
    public String deleteProductCategory(Model model,
                                        @RequestParam(value = "command") String command,
                                        @ModelAttribute(value = "cmnd") String cmnd,
                                        @ModelAttribute(value = "idPC") long idPC,
                                        @ModelAttribute(value = "idCC") long idCC) {
        if (command.equals(cmnd)) {
            serviceAdmin.deleteProductCategory(idPC, idCC);
            model.addAttribute("error", "");
        } else {
            model.addAttribute("error", "пароль неверен");
        }
        model.addAttribute("cmnd", "");
        model.addAttribute("listOfProductCategory", serviceAnonymous.listOfProductCategory(idCC));
        return "category";
    }

    @RequestMapping("/new_change_delete_product_category/{idPC}/{namePC}")
    public void ncdProductCategory(Model model,
                                   @PathVariable(value = "idPC") long idPC,
                                   @PathVariable(value = "namePC") String namePC) {
        namePC = utf8(namePC);
        model.addAttribute("cmnd", new Administrator().getPassword());
        model.addAttribute("idPC", idPC);
        model.addAttribute("namePC", namePC);
    }


    //-------------------------Product-------------------------//


    @RequestMapping(value = "/new_product", method = RequestMethod.POST)
    public String newProduct(Model model,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "size") String size,
                             @RequestParam(value = "season") String season,
                             @RequestParam(value = "color") String color,
                             @RequestParam(value = "zastezhka") String zastezhka,
                             @RequestParam(value = "length") String length,
                             @RequestParam(value = "sleeve") String sleeve,
                             @RequestParam(value = "material") String material,
                             @RequestParam(value = "description") String description,
                             @RequestParam(value = "photo") MultipartFile photo,
                             @ModelAttribute(value = "idPC") long idPC) {
        name = utf8(name);
        size = utf8(size);
        season = utf8(season);
        color = utf8(color);
        zastezhka = utf8(zastezhka);
        length = utf8(length);
        sleeve = utf8(sleeve);
        material = utf8(material);
        description = utf8(description);
        double price = 0;// поле не используется, но оставил на всякий случай
        try {
            Product product = new Product(name, new Photo(photo.getOriginalFilename(), photo.getBytes()),
                    price, color, size, season, zastezhka, length, sleeve, material, description);
            serviceAdmin.createProduct(product, idPC);
            model.addAttribute("product", product);
            model.addAttribute("page", "create");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "product";
    }

    @RequestMapping("/change_product")
    public String changeProduct(Model model,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "size") String size,
                                @RequestParam(value = "season") String season,
                                @RequestParam(value = "color") String color,
                                @RequestParam(value = "zastezhka") String zastezhka,
                                @RequestParam(value = "length") String length,
                                @RequestParam(value = "sleeve") String sleeve,
                                @RequestParam(value = "material") String material,
                                @RequestParam(value = "description") String description,
                                @RequestParam(value = "photo") MultipartFile photo,

                                @RequestParam(value = "command") String command,
                                @ModelAttribute(value = "cmnd") String cmnd,
                                @ModelAttribute(value = "idPr") long idPr,
                                @ModelAttribute(value = "idPC") long idPC,
                                @ModelAttribute(value = "page") String page) {

        if (command.equals(cmnd)) {
            name = utf8(name);
            size = utf8(size);
            season = utf8(season);
            color = utf8(color);
            zastezhka = utf8(zastezhka);
            length = utf8(length);
            sleeve = utf8(sleeve);
            material = utf8(material);
            description = utf8(description);
            double price = 0;// поле не используется, но оставил на всякий случай
            try {
                Product newProduct = new Product(name, new Photo(photo.getOriginalFilename(), photo.getBytes()),
                        price, color, size, season, zastezhka, length, sleeve, material, description);
                serviceAdmin.changeProduct(newProduct, idPr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("error", "");
            model.addAttribute("page", page);
        } else {
            model.addAttribute("error", "пароль неверен");
        }
        model.addAttribute("cmnd", "");
        model.addAttribute("product", serviceAnonymous.openProduct(idPr));
        return "product";
    }

    @RequestMapping("/delete_product")
    public String deleteProduct(Model model,
                                @RequestParam(value = "command") String command,
                                @ModelAttribute(value = "cmnd") String cmnd,
                                @ModelAttribute(value = "idPr") long idPr,
                                @ModelAttribute(value = "idPC") long idPC) {
        if (command.equals(cmnd)) {
            serviceAdmin.deleteProduct(idPC, idPr);
            model.addAttribute("error", "");
        } else {
            model.addAttribute("error", "пароль неверен");
        }
        model.addAttribute("cmnd", "");
        model.addAttribute("listOfProduct", serviceAnonymous.listOfProduct(idPC));
        return "list_product";
    }

    @RequestMapping("/change_delete_product/{idPr}")
    public void ncdProduct(Model model,
                           @PathVariable(value = "idPr") long idPr) {
        model.addAttribute("cmnd", new Administrator().getPassword());
        model.addAttribute("idPr", idPr);
        model.addAttribute("product", serviceAnonymous.openProduct(idPr));
    }


    //-------------------------Deal-------------------------//


    @RequestMapping("/deals_list")
    public String dealsList(Model model) {
        List<Deal> deals = serviceAdmin.dealsList();
        model.addAttribute("size", deals.size());
        model.addAttribute("dealsList", deals);
        return "admin_deals";
    }

    @RequestMapping("/delete_deal/{idDeal}")
    public String deleteDeal(Model model,
                             @PathVariable(value = "idDeal") long idDeal) {
        serviceAdmin.deleteDeal(idDeal);
        return dealsList(model);
    }

    @RequestMapping("/open_deal/{idDeal}")
    public String openDeal(Model model,
                           @PathVariable(value = "idDeal") long idDeal) {
        model.addAttribute("deal", serviceAdmin.openDeal(idDeal));
        return "deal";
    }
}
