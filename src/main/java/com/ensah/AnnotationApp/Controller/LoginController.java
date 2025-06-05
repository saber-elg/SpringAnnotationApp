package com.ensah.AnnotationApp.Controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {
        Object errorMessage = request.getSession().getAttribute("errorMessage"); //retournera null si existe pas (et pas exception)
        //un message d'erreur que j'ai dans CustomAuthenticationFailureHandler ie lors de problemes de login : identifiants incorrectes , compte desactiver
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage.toString());
            request.getSession().removeAttribute("errorMessage");
        }
        return "login";
    }

}