package org.kumar.primestarter.views;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.kumar.primestarter.entity.PrimeUser;
import org.kumar.primestarter.repository.PrimeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;

@Named
@RequestScope
public class Registration {

    @Autowired
    private PrimeUserRepository primeUserRepository;

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

    public void savePrimeUser() {
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("user saved"));
        if (primeUser.getUsername().length() < 2) {
            FacesContext.getCurrentInstance().addMessage("registrationForm:username", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Username: Validation Error: user name exists", null));
        }
//        primeUserRepository.save(primeUser);

    }

}
