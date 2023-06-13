package dzh.market.controller;

import dzh.market.BaseRoutes;
import dzh.market.entity.User;
import dzh.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final String REGISTRATION_VIEW = "registration";

    @Autowired
    private UserService userService;

    @GetMapping(BaseRoutes.REGISTRATION)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return REGISTRATION_VIEW;
    }

    @PostMapping(BaseRoutes.REGISTRATION)
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return REGISTRATION_VIEW;
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return REGISTRATION_VIEW;
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return REGISTRATION_VIEW;
        }

        return "redirect:" + BaseRoutes.ROOT;
    }
}
