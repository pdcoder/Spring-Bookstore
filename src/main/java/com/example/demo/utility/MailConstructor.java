package com.example.demo.utility;

import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.tools.java.Environment;

/**
 * Created by prakashdas on 19/08/18.
 */
@Component
public class MailConstructor {

    @Autowired
    private Environment env;

    public SimpleMailMessage constructResetTokenEmail{

    }
}
