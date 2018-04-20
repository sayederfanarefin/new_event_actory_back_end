//package com.sweetitech.tiger.controller.ecommerce;
//
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//
//import java.util.Set;
//
//import javax.validation.Valid;
//import javax.ws.rs.core.Link;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.Validator;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sweetitech.tiger.ecommerce.extra.OrderResource;
//import com.sweetitech.tiger.model.ecommerce.CartItem;
//import com.sweetitech.tiger.model.ecommerce.Order;
//import com.sweetitech.tiger.service.interfaces.CartService;
//
//
//@RestController
//@RequestMapping("/cart")
//public class CartController{
//
//    @Autowired
//    CartService cartService;
//
//    @Autowired
//    Validator orderValidator;
//
//    @InitBinder
//    protected void initBinder(WebDataBinder binder){
//        binder.addValidators(orderValidator);
//    }
//
//    @PostMapping("/")
//    public String create(){
//        return cartService.createNewCart();
//    }
//
//    @PostMapping("/{id}")
//    public String addProduct(@PathVariable("id") String cartId, @RequestBody CartItem cartItem){
//        cartService.addProduct(cartId, cartItem);
//        return "OK";
//    }
//
//    @GetMapping("/{id}")
//    public Set<CartItem> getCartItems(@PathVariable("id") String cartId){
//        return cartService.getItems(cartId);
//    }
//
//    @DeleteMapping("{id}/{product_id}")
//    public String removeItem(@PathVariable("id") String cartId, @PathVariable("product_id") String productId){
//        cartService.removeProduct(cartId,productId);
//        return "OK";
//    }
//
//    @PostMapping("{id}/quantity")
//    public String setProductQuantity(@PathVariable("id") String cartId,@RequestBody CartItem cartItem){
//        String productId = Long.toString(cartItem.getProductId());
//        cartService.setProductQuantity(cartId, productId, cartItem.getQuantity());
//        return "OK";
//    }
//
//    @PostMapping("{id}/order")
//    public OrderResource createOrder(@PathVariable("id") String cartId, @RequestBody @Valid Order order){
//        if(order == null){
//            System.out.println("Order not in POST");
//            return null;
//        }
//        OrderResource orderResource = new OrderResource(
//            cartService.createOrder(cartId, order)
//        );
//
//        // HAL link:
//        Link link = linkTo(OrderController.class).slash(order.getId()).withSelfRel();
//        orderResource.add(link);
//
//        if(orderResource.id < 1){
//            System.out.println("Resource not created");
//            return null;
//        }
//        return orderResource;
//
//    }
//
//}
