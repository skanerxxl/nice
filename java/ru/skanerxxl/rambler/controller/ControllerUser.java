package ru.skanerxxl.rambler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.skanerxxl.rambler.database.entity.AddressOfDelivery;
import ru.skanerxxl.rambler.database.entity.Deal;
import ru.skanerxxl.rambler.database.entity.MyOrder;
import ru.skanerxxl.rambler.database.entity.Product;
import ru.skanerxxl.rambler.service.ServiceAnonymous;
import ru.skanerxxl.rambler.service.ServiceUser;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Set;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('USER')")
@SessionAttributes({
        "idCC",
        "nameCC",
        "idPC",
        "namePC",
        "product",
        "list",
        "idOr",
        "error",
        "ind",
        "size",
        "page"})
public class ControllerUser {
    @Autowired
    private ServiceUser serviceUser;
    @Autowired
    private ServiceAnonymous serviceAnonymous;

    private boolean toggle = false;// переключатель для сообщения error

    private String utf8(final String paramValue) {
        String name = null;
        try {
            name = new String(paramValue.getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
    }

    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    //------------------------------------------------------------------------------//

    @RequestMapping("/basket_deals_list/{ind}/{page}")
    public String basketDeals(Model model,
                              @PathVariable(value = "ind") int indicator,
                              @PathVariable(value = "page") String page,
                              @ModelAttribute(value = "nameCC") String nameCC,
                              @ModelAttribute(value = "idCC") long idCC,
                              @ModelAttribute(value = "namePC") String namePC,
                              @ModelAttribute(value = "idPC") long idPC,
                              @ModelAttribute(value = "product") Product product) {
        if (!toggle) {
            model.addAttribute("error", "");
        } else {
            toggle = false;
        }
        boolean b_d = (indicator == 0) ? false : true;
        Set<MyOrder> list = serviceUser.userBasketDeals(getUserName(), b_d);
        model.addAttribute("list", list);
        model.addAttribute("size", list.size());
        model.addAttribute("page", page);
        model.addAttribute("ind", indicator);
        model.addAttribute("nameCC", nameCC);
        model.addAttribute("idCC", idCC);
        model.addAttribute("namePC", namePC);
        model.addAttribute("idPC", idPC);
        model.addAttribute("product", product);
        return "basket";
    }

    @RequestMapping("/add_in_basket/{idPr}/{namePr}/{idPh}")
    public String addInBasket(Model model,
                              @PathVariable(value = "idPr") long idPr,
                              @PathVariable(value = "namePr") String namePr,
                              @PathVariable(value = "idPh") long idPh,
                              @ModelAttribute(value = "idPC") long idPC) {
        MyOrder myOrder = new MyOrder(utf8(namePr), idPr, idPh, new Date(), false);
        serviceUser.addOrderInBasket(getUserName(), myOrder);
        model.addAttribute("listOfProduct", serviceAnonymous.listOfProduct(idPC));
        return "list_product";
    }

    @RequestMapping(value = "/delete_my_orders", method = RequestMethod.POST)
    public void deleteFromDeals(Model model,
                                @RequestParam(value = "idOr[]", required = false) long[] idOr) {
        if (idOr != null) {
            serviceUser.deleteMyOrders(getUserName(), idOr);
        } else {
            model.addAttribute("error", "сделайте выбор");
            toggle = true;
        }
    }

    @RequestMapping(value = "/delete_my_order/{idOr}/{ind}")
    public String deleteOneFromDeals(Model model,
                                     @PathVariable(value = "idOr") long idOr,
                                     @PathVariable(value = "ind") int indicator) {
        serviceUser.deleteMyOrder(getUserName(), idOr);
        boolean basketOrDeal = (indicator == 0) ? false : true;
        Set<MyOrder> list = serviceUser.userBasketDeals(getUserName(), basketOrDeal);
        model.addAttribute("list", list);
        model.addAttribute("size", list.size());
        model.addAttribute("ind", indicator);
        return "basket";
    }


    @RequestMapping(value = "/add_in_deals", method = RequestMethod.POST)
    public void addInDeals(Model model,
                           @RequestParam(value = "idOr[]", required = false) long[] idOr) {
        if (idOr != null) {
            model.addAttribute("idOr", idOr);
            toggle = true;
        } else {
            model.addAttribute("error", "сделайте выбор");
        }
    }

    @RequestMapping("/readdressing_check_deal")
    public String readdressingCheckout() {
        return (toggle) ? "check_deal" : "basket";
    }

    @RequestMapping(value = "/check_deal", method = RequestMethod.POST)
    public String checkout(Model model,
                           @RequestParam String nameUser,
                           @RequestParam String email,
                           @RequestParam String phone,
                           @RequestParam String carrier,
                           @RequestParam String city,
                           @RequestParam String numberOffice,
                           @ModelAttribute(value = "idOr") long[] idOr) {
        serviceUser.addInDeals(getUserName(), idOr);
        Deal deal = new Deal(utf8(nameUser), utf8(phone), utf8(email), new AddressOfDelivery(utf8(carrier), utf8(city), utf8(numberOffice)));
        serviceUser.checkDeal(deal, idOr);
        model.addAttribute("error", "ваш заказ принят в обработку");
        toggle = false;
        return "start";
    }
}


