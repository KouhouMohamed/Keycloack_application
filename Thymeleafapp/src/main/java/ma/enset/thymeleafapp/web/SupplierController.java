package ma.enset.thymeleafapp.web;

import ma.enset.thymeleafapp.entities.Supplier;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.facade.SimpleHttpFacade;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SupplierController {
    @Autowired KeycloakRestTemplate keycloakRestTemplate;
    @Autowired AdapterDeploymentContext adapterDeploymentContext;

    @GetMapping(path = "/suppliers")
    public String listsuppliers(Model model){
        /*
            With simple controller (Not @RestRepositoryResource)
        Collection<Supplier> suppliers = keycloakRestTemplate.getForObject("http://localhost:8082/suppliers", Collection.class);
        model.addAttribute("suppliers",suppliers);

         */
        PagedModel<Supplier> suppliers = keycloakRestTemplate.getForObject("http://localhost:8082/suppliers", PagedModel.class);
        model.addAttribute("suppliers",suppliers.getContent());

        return "suppliers";
    }
    /*
    @GetMapping(path = "/suppliers")
    public String suppliers(Model model){
        ResponseEntity<PagedModel<Supplier>> response= keycloakRestTemplate.exchange(
                "http://localhost:8082/suppliers",
                        HttpMethod.GET, null, new ParameterizedTypeReference<PagedModel<Supplier>>() { });
        model.addAttribute("suppliers",response.getBody());
        return "suppliers";
    }
     */
    @GetMapping(path = "changepassword")
    public String changePassword(RedirectAttributes attributes, HttpServletRequest request, HttpServletResponse response){
        HttpFacade httpFacade = new SimpleHttpFacade(request,response);
        KeycloakDeployment deployment = adapterDeploymentContext.resolveDeployment(httpFacade);
        attributes.addAttribute("referrer",deployment.getResourceName());
        attributes.addAttribute("referre_uri",request.getHeader("referrer"));

        return "redirect:"+deployment.getAccountUrl();
    }
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Model model){
        model.addAttribute("errorMessage","Not Authorized"); return "errors";
    }


    /*
        Old method to contact supplier microservices
     */
    /*
    public String suppliers(Model model, HttpServletRequest request){
        // get JWT
        KeycloakAuthenticationToken token =(KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal keycloakPrincipal =(KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext keycloakSecurityContext = keycloakPrincipal.getKeycloakSecurityContext();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Autorization","Bearer "+keycloakSecurityContext.getTokenString());

        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<Supplier> response = restTemplate.exchange(

                "http://localhost:8082/suppliers",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<Supplier>() {}
        );
        return "suppliers";
    }

     */

}
