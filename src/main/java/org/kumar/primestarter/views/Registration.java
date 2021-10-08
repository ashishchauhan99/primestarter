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

    public PrimeUser getPrimeUser() {
        return primeUser;
    }

    public void setPrimeUser(PrimeUser primeUser) {
        this.primeUser = primeUser;
    }

    public void savePrimeUser() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("user saved"));
//        primeUserRepository.save(primeUser);

    }

}
