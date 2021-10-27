package org.kumar.primestarter.views;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class LoginView {

    private String lang;

    @PostConstruct
    void init() {
        String ln = FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("Accept-Language");
        System.out.println("------------ Accept-Language -- " + ln);
        lang = "de";
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}
