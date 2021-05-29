package example.spring.jsp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import example.spring.jsp.controller.UserController;
import example.spring.jsp.exception.ServiceException;
import example.spring.jsp.model.User;
import example.spring.jsp.service.UserService;
import example.spring.jsp.validator.UserValidator;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private static final long ID = 1;
    private static final User USER = new User();
    private static final String USER_MODEL_ATTRIBUTE = "user";
    private static final String ADD_USER_PAGE = "useradd";
    private static final String EDIT_USER_PAGE = "useredit";
    private static final String USER_DETAILS_PAGE = "userdisplay";
    private static final String LIST_USERS_PAGE = "userlistview";
    private static final String LIST_USERS_PAGE_REDIRECT = "redirect:/users";
    private static final String USER_DETAILS_PAGE_REDIRECT = "redirect:/users/" + ID;
    private static final String ERROR_PAGE = "error";

    @Mock
    private UserService userService;

    @Mock
    private UserValidator userValidator;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private UserController controller;

    @DisplayName("Load new user page")
    @Test
    public void testAddNewUser() {
        assertEquals(ADD_USER_PAGE, controller.newUser(model));
        verify(model).addAttribute(eq(USER_MODEL_ATTRIBUTE), any(User.class));
    }

    @DisplayName("Load view user page")
    @Test
    public void testViewUserDetails() throws ServiceException {
        when(userService.get(ID)).thenReturn(USER);
        assertEquals(USER_DETAILS_PAGE, controller.viewUser(ID, model));
        verify(model).addAttribute(USER_MODEL_ATTRIBUTE, USER);
    }

    @DisplayName("Load view user page errors")
    @Test
    public void testViewUserDetailsErrors() throws ServiceException {
        when(userService.get(ID)).thenThrow(new ServiceException("Unit test error", null));
        assertEquals(ERROR_PAGE, controller.viewUser(ID, model));
        verify(model, never()).addAttribute(USER_MODEL_ATTRIBUTE, USER);
    }

    @DisplayName("Load edit user page")
    @Test
    public void testEditUser() throws ServiceException {
        when(userService.get(ID)).thenReturn(USER);
        assertEquals(EDIT_USER_PAGE, controller.editUser(ID, model));
        verify(model).addAttribute(USER_MODEL_ATTRIBUTE, USER);
    }

    @DisplayName("Load edit user page errors")
    @Test
    public void testEditUserErrors() throws ServiceException {
        when(userService.get(ID)).thenThrow(new ServiceException("Unit test error", null));
        assertEquals(ERROR_PAGE, controller.editUser(ID, model));
        verify(model, never()).addAttribute(eq(USER_MODEL_ATTRIBUTE), any());
    }

    @DisplayName("Load view users page")
    @Test
    public void testViewUsers() throws ServiceException {
         List<User> users = new ArrayList<>();
         users.add(USER);

        when(userService.getAll()).thenReturn(users);
        assertEquals(LIST_USERS_PAGE, controller.viewUserList(model));
        verify(model).addAttribute("users", users);
    }

    @DisplayName("Load view users page errors")
    @Test
    public void testViewUsersErrors() throws ServiceException {
        when(userService.getAll()).thenThrow(new ServiceException("Unit test error", null));
        assertEquals(ERROR_PAGE, controller.viewUserList(model));
        verify(model, never()).addAttribute(eq(USER_MODEL_ATTRIBUTE), any());
    }

    @DisplayName("Save user")
    @Test
    public void testSaveUser() throws ServiceException {
        doNothing().when(userValidator).validate(USER, bindingResult);
        when(bindingResult.hasErrors()).thenReturn(false);
        doNothing().when(userService).save(USER);

        assertEquals(LIST_USERS_PAGE_REDIRECT, controller.saveUser(USER, bindingResult));
        verify(userService).save(USER);
    }

    @DisplayName("Save user has validation errors")
    @Test
    public void testSaveUserValidationErrors() throws ServiceException {
        List<ObjectError> errors = new ArrayList<>();
        ObjectError error = new ObjectError("name", "Default message");
        errors.add(error);

        doNothing().when(userValidator).validate(USER, bindingResult);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(errors);

        assertEquals(ADD_USER_PAGE, controller.saveUser(USER, bindingResult));
        verify(userService, never()).save(USER);
    }

    @DisplayName("Save user errors")
    @Test
    public void testSaveUserErrors() throws ServiceException {
        doNothing().when(userValidator).validate(USER, bindingResult);
        when(bindingResult.hasErrors()).thenReturn(false);
        doThrow(new ServiceException("Unit test error", null)).when(userService).save(USER);

        assertEquals(ERROR_PAGE, controller.saveUser(USER, bindingResult));
        verify(userService).save(USER);
    }

    @DisplayName("Cancel new user")
    @Test
    public void testCancelNewUser() throws ServiceException {
        assertEquals(LIST_USERS_PAGE_REDIRECT, controller.cancelNewUser());
    }

    @DisplayName("Update user")
    @Test
    public void testUpdateUser() throws ServiceException {
        doNothing().when(userValidator).validate(USER, bindingResult);
        when(bindingResult.hasErrors()).thenReturn(false);
        doNothing().when(userService).update(ID, USER);

        assertEquals(USER_DETAILS_PAGE_REDIRECT, controller.updateUser(ID, USER, bindingResult));
        verify(userService).update(ID, USER);
    }

    @DisplayName("Update user has validation errors")
    @Test
    public void testUpdateUserValidationErrors() throws ServiceException {
        List<ObjectError> errors = new ArrayList<>();
        ObjectError error = new ObjectError("name", "Default message");
        errors.add(error);

        doNothing().when(userValidator).validate(USER, bindingResult);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(errors);

        assertEquals(EDIT_USER_PAGE, controller.updateUser(ID, USER, bindingResult));
        verify(userService, never()).update(ID, USER);
    }

    @DisplayName("Update user errors")
    @Test
    public void testUpdateUserErrors() throws ServiceException {
        doNothing().when(userValidator).validate(USER, bindingResult);
        when(bindingResult.hasErrors()).thenReturn(false);
        doThrow(new ServiceException("Unit test error", null)).when(userService).update(ID, USER);

        assertEquals(ERROR_PAGE, controller.updateUser(ID, USER, bindingResult));
        verify(userService).update(ID, USER);
    }

    @DisplayName("Cancel edit user")
    @Test
    public void testCancelEditUser() throws ServiceException {
        assertEquals(LIST_USERS_PAGE_REDIRECT, controller.cancelEditUser());
    }

    @DisplayName("Delete user")
    @Test
    public void testDeleteUser() throws ServiceException {
        doNothing().when(userService).delete(ID);
        assertEquals(LIST_USERS_PAGE_REDIRECT, controller.deleteUser(ID));
        verify(userService).delete(ID);
    }

    @DisplayName("Delete user errors")
    @Test
    public void testDeleteUserErrors() throws ServiceException {
        doThrow(new ServiceException("Unit test error", null)).when(userService).delete(ID);
        assertEquals(ERROR_PAGE, controller.deleteUser(ID));
        verify(userService).delete(ID);
    }
}
