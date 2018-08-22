package com.example.demo.bookstore.controller;

import com.example.demo.domain.*;
import com.example.demo.service.UserService;
import com.example.demo.utility.MailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

/**
 * Created by prakashdas on 22/08/18.
 */

@Controller
public class CheckoutController {

    private ShippingAddress shippingAddress = new ShippingAddress();
    private BillingAddress billingAddress = new BillingAddress();
    private Payment payment = new Payment();

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private BillingAddressService billingAddressService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private  UserShippingService userShippingService;

    @Autowired
    private UserPaymentService userPaymentService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/checkout")
    public String checkout(
            @RequestParam("id") Long cartId,
            @RequestParam(value="missingRequiredField",required = false) boolean missingRequiredField, Model model, Principal principal)
        User user = userService.findByUsername(principal.getName());
            if(cartId!=user.getShoppingCart().getId()){
            return "badRequestPage" ;
        }


    List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

    if(cartItemList.size()==0){
        model.addAttribute("emptyCart",true);
        return "forward:/shoppingCart/cart";
    }

    if(CartItem cartItem: cartItemList){
        if(cartItem.getBook().getInStockNumber()<cartItem.getQty()){
            model.addAttribute("notEnoughStock",true);
        }
    }
}
