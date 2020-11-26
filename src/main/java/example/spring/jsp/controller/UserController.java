package example.spring.jsp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import example.spring.jsp.exception.ServiceException;
import example.spring.jsp.model.User;
import example.spring.jsp.service.UserService;

@Controller
public class UserController {

    private UserService userService;
    private User user;

    @RequestMapping(method=RequestMethod.GET, value="/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "useradd";
    }

    @RequestMapping(method=RequestMethod.GET, value="/users/{id}")
    public String viewUser(@PathVariable("id") long id, Model model) throws ServiceException {
        user = userService.get(id);
        model.addAttribute("user", user);
        return "userdisplay";
    }

    @RequestMapping(method=RequestMethod.GET, value="/users/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) throws ServiceException {
        user = userService.get(id);
        model.addAttribute("user", user);
        return "useredit";
    }

    @RequestMapping(method=RequestMethod.GET, value="/users")
    public String viewUserList(Model model) throws ServiceException {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "userlistview";
    }

    @RequestMapping(method=RequestMethod.POST, value="/users", params="save")
    public String saveUser(User user, ModelMap model) throws ServiceException {
    	userService.save(user);
        return "redirect:/users";
    }

    @RequestMapping(method=RequestMethod.POST, value="/users", params="cancel")
    public String cancelNewUser(Model model) {
        return "redirect:/users";
    }

    @RequestMapping(method=RequestMethod.POST, value="/users/{id}", params="save")
    public String updateUser(@PathVariable("id") long id, User user) throws ServiceException {
    	userService.update(id, user);
        return "redirect:/users/" + id;
    }

    @RequestMapping(method=RequestMethod.POST, value="/users/{id}", params="cancel")
    public String cancelEditUser(@PathVariable("id") long id, Model model) {
        return "redirect:/users";
    }

    @RequestMapping(method=RequestMethod.GET, value="/users/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) throws ServiceException {
    	userService.delete(id);
        return "redirect:/users";
    }

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
