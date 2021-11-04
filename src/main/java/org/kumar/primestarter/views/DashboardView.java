package org.kumar.primestarter.views;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.kumar.primestarter.service.SessionKey;
import org.kumar.primestarter.service.SessionStoreService;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@ViewScoped
public class DashboardView {

    @Autowired
    private SessionStoreService sessionMap;

    private String heading = "Dashboard";

    @PostConstruct
    void init() {
        System.out.println(sessionMap.getSessionAttribute(SessionKey.LOCALE));
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        for (Map.Entry<String, Object> entry : sessionMap.entrySet()) {
            System.out.println(entry.getKey() + " ----------- " + entry.getValue());

            if (entry.getKey().equals("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap")) {

            }
        }
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

}
