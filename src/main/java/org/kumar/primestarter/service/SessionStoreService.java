package org.kumar.primestarter.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionStoreService {

    private Map<SessionKey, Object> map = new HashMap<>();

    public Object getSessionAttribute(SessionKey key) {
        return map.get(key);
    }

    public Object setSessionAttribute(SessionKey key, Object attribute) {
        return map.put(key, attribute);
    }

    public Object removeAttribute(SessionKey key) {
        return map.remove(key);
    }

}
