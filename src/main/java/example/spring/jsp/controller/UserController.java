package example.spring.jsp.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import example.spring.jsp.exception.ServiceException;
import example.spring.jsp.model.User;
import example.spring.jsp.service.UserService;
import example.spring.jsp.validator.UserValidator;

@Controller
public class UserController {

    private static final Logger LOG = LogManager.getLogger(UserController.class);

    private UserService userService;
    private UserValidator userValidator;
    private User user;

    @RequestMapping(method=RequestMethod.GET, value="/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "useradd";
    }

    @RequestMapping(method=RequestMethod.GET, value="/users/{id}")
    public String viewUser(@PathVariable("id") long id, Model model) {
        try {
            user = userService.get(id);
            if (user == null) {
                LOG.info("Rendering error page: User not found [" + id + "]");
                return "error";
            }

            LOG.info("Rendering view page: User found [" + id + "]");
            model.addAttribute("user", user);
            return "userdisplay";
        } catch (ServiceException e) {
            LOG.error("Rendering error page: Error encountered while getting user: " + e.getMessage());
            return "error";
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/users/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        try {
            user = userService.get(id);
            user = userService.get(id);
            if (user == null) {
                LOG.info("Rendering error page: User not found [" + id + "]");
                return "error";
            }

            LOG.info("Rendering edit page: User found [" + id + "]");
            model.addAttribute("user", user);
            return "useredit";
        } catch (ServiceException e) {
            LOG.error("Rendering error page: Error encountered while getting user: " + e.getMessage());
            return "error";
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/users")
    public String viewUserList(Model model) {
        try {
            List<User> users = userService.getAll();
            model.addAttribute("users", users);
            return "userlistview";
        } catch (ServiceException e) {
            LOG.error("Rendering error page: Error encountered while getting all users: " + e.getMessage());
            return "error";
        }
    }

    @RequestMapping(method=RequestMethod.POST, value="/users", params="save")
    public String saveUser(User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                LOG.info("Validation error [error field = " + error.getObjectName() + "]: " + error.getDefaultMessage());
            });
            return "useradd";
        }

        try {
            userService.save(user);
            LOG.info("Redirecting to user list page: User successfully saved");
            return "redirect:/users";
        } catch (ServiceException e) {
            LOG.error("Rendering error page: Error encountered while saving user: " + e.getMessage());
            return "error";
        }
    }

    @RequestMapping(method=RequestMethod.POST, value="/users", params="cancel")
    public String cancelNewUser() {
        LOG.info("Redirecting to user list page: Add user cancelled");
        return "redirect:/users";
    }

    @RequestMapping(method=RequestMethod.POST, value="/users/{id}", params="save")
    public String updateUser(@PathVariable("id") long id, User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                LOG.info("Validation error [user id = " + id + ", error field = " + error.getObjectName() + "]: " + error.getDefaultMessage());
            });
            return "useredit";
        }

        try {
            userService.update(id, user);
            LOG.info("Redirecting to user list page: User successfully updated [" + id + "]");
            return "redirect:/users/" + id;
        } catch (ServiceException e) {
            LOG.error("Rendering error page: Error encountered while updating user: " + e.getMessage());
            return "error";
        }
    }

    @RequestMapping(method=RequestMethod.POST, value="/users/{id}", params="cancel")
    public String cancelEditUser() {
        return "redirect:/users";
    }

    @RequestMapping(method=RequestMethod.GET, value="/users/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        try {
            userService.delete(id);
            LOG.info("Redirecting to user list page: User successfully deleted [" + id + "]");
            return "redirect:/users";
        } catch (ServiceException e) {
            LOG.error("Rendering error page: Error encountered while deleting user: " + e.getMessage());
            return "error";
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
