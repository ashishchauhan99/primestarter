package org.kumar.primestarter.views;

import com.sun.faces.application.view.MultiViewHandler;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.kumar.primestarter.service.SessionStoreService;
import org.kumar.primestarter.service.SpringContextService;

public class LocaleViewHandler extends MultiViewHandler {

    @Override
    public Locale calculateLocale(FacesContext context) {
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

        SessionStoreService sessionMap = SpringContextService.getBean(SessionStoreService.class);
        if (session != null && sessionMap != null) {
            // Return the locale saved by the managed bean earlier
            Object localeObject = sessionMap.getSessionAttribute("locale");
            if (localeObject != null) {
                Locale locale = (Locale) localeObject;
                return locale;
            }
        }
        return super.calculateLocale(context);
    }

}
