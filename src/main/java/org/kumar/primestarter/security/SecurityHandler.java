package org.kumar.primestarter.security;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kumar.primestarter.service.SessionStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SecurityHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private SessionStoreService sessionMap;

    @Autowired
    public SecurityHandler(SessionStoreService sessionMap) {
        this.sessionMap = sessionMap;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {

        System.out.println("##################################");

        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.containsKey("options_input")) {
            String[] langAndCountry = parameterMap.get("options_input")[0].split("_");
            if (langAndCountry.length == 2) {
                Locale locale = new Locale(langAndCountry[0], langAndCountry[1]);
                sessionMap.setSessionAttribute("locale", locale);
            } else {
                setDefaultLocale();
            }
        } else {
            setDefaultLocale();
        }

        for (String paramKey : parameterMap.keySet()) {
            for (String value : parameterMap.get(paramKey)) {
                System.out.println("Key: " + paramKey + " ---- value: " + value);
            }
        }

        clearAuthenticationAttributes(request);
        // Use the DefaultSavedRequest URL
        getRedirectStrategy().sendRedirect(request, response, "/dashboard.xhtml");

    }

    private void setDefaultLocale() {
        Locale defaultLocale = new Locale("de", "DE");
        sessionMap.setSessionAttribute("locale", defaultLocale);
    }
}
