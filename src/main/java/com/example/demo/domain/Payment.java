package com.example.demo.domain;

import javax.persistence.*;

/**
 * Created by prakashdas on 21/08/18.
 */
@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String cardNumber;
    private String cardName;
    private int expiryMonth;
    private int expiryYear;
    private int cvc;
    private String holderName;

    @OneToOne
    private Order order;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userPayment")
    private UserBilling userBilling;

}
