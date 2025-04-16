package com.oidcapp.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor.ClientRegistrationIdResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.ResponseSpec;
import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;


@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private RestClient restClient;

    @GetMapping("/hello")
    public String helloMessage() throws InterruptedException {
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        ResponseSpec response = restClient
            .get()
            .uri("http://127.0.0.1:8080/api/secured/foo")
            .attributes(clientRegistrationId(authentication.getAuthorizedClientRegistrationId()))
            .retrieve();
        String responseMessage = response.body(String.class);

        return "Hello From OAUTH Client application --- " + responseMessage;
    }

}
