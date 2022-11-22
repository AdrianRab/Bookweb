package pl.arabowski.bookweb.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedirectUrlResolver {
    @Value("${server.port:8090}")
    private String port;
    @Value("${server.hostname:localhost}")
    private String hostname;

    public String getRedirectView(String viewName) {
        return "redirect:" + "http://" + hostname + ":" + port + viewName;
    }
}
