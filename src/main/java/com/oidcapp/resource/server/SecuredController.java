package com.oidcapp.resource.server;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/secured")
public class SecuredController {

    @GetMapping("/foo")
    public String foo(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        return principal.getAttribute("sub") + " is the subject";
    }
}
