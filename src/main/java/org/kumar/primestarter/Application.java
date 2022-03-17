package org.kumar.primestarter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;;

@ServletComponentScan
@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String encode = bCryptPasswordEncoder.encode("pw");
//        log.info("----------------  " + encode);
//
//        ApplicationContext context = SpringApplication.run(Application.class);
//
//        System.exit(SpringApplication.exit(context));
        Class.forName("org.h2.Driver");

        Connection conn = DriverManager
                .getConnection("jdbc:h2:/home/ashish/Development/workspaceEclipse/primestarter/test", "sa", "");

        ApplicationContext context = SpringApplication.run(Application.class, args);

//        System.exit(SpringApplication.exit(context));
    }

}
