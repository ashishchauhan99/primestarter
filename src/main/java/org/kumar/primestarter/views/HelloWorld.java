package org.kumar.primestarter.views;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.annotation.SessionScope;

@Named
@SessionScope
public class HelloWorld {

    private String firstName = "John";
    private String lastName = "Doe";
    private Date date;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String showGreeting() {
        return "Hello " + firstName + " " + lastName + "!";
    }

    public String logout() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance();
        System.out.println(request.getContextPath());
        return "login.xhtml";

    }

}