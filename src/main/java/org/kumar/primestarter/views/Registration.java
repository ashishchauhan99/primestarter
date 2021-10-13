package org.kumar.primestarter.views;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.kumar.primestarter.entity.PrimeUser;
import org.kumar.primestarter.misc.Utility;
import org.kumar.primestarter.repository.PrimeUserRepository;
import org.kumar.primestarter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@ViewScoped
public class Registration {

    @Autowired
    private PrimeUserRepository primeUserRepository;
    @Autowired
    private Utility utility;
    @Autowired
    private ProductRepository productRepository;

    private PrimeUser primeUser = new PrimeUser();
    private String password;
    private String passwordRepeat;

    public PrimeUser getPrimeUser() {
        return primeUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getPassword() {
        return password;
    }

    public void setPrimeUser(PrimeUser primeUser) {
        this.primeUser = primeUser;
    }

    public void passwordSetListner() {
        // empty by intention, ajax listner so the the password value can be summitted to the server
    }

    public void usernameAvailabilityCheckListner() {
        PrimeUser primeUserByUsername = primeUserRepository.findByUsername(primeUser.getUsername());
        if (primeUserByUsername != null) {
            FacesContext.getCurrentInstance().addMessage("registrationForm:username", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Username: Validation Error: user name exists", null));
        }
    }

    public void passwordMatchCheckListner() {
        if (password != null && !password.equals(passwordRepeat)) {
            FacesContext.getCurrentInstance().addMessage("registrationForm:idRepeatePassword", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Password Repeat: Validation Error: passwords do not match", null));
        }
    }

    public void savePrimeUser() {
        productRepository.findAll();

        PrimeUser primeUserByUsername = primeUserRepository.findByUsername(primeUser.getUsername());
        if (primeUserByUsername != null) {
            FacesContext.getCurrentInstance().addMessage("registrationForm:username", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Username: Validation Error: user name exists", null));
        } else {
            primeUser.setPassword(utility.bCryptPasswordEncoder(password));
            primeUserRepository.save(primeUser);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("user registerd successfully"));
        }
    }

}
