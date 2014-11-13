package laba17.web;

import laba17.domain.User;
import laba17.service.RoleDaoService;
import laba17.service.UserDaoService;
import laba17.validation.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * Class that controls logging users,, deleting users, adding users, the validity of the data when adding a user
 * Created by redko on 9/29/2014.
 */

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private RoleDaoService roleDao;
    @Autowired
    private UserDaoService userDao;

    /**
     * Add-form validator instance
     */
    @Autowired
    @Qualifier("formValidator")
    private Validator validator;

    /**
     * Setting validator
     *
     * @param binder WebDataBinder
     */
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    /**
     * Creating add-form
     *
     * @return add-form
     */
    @ModelAttribute("validationForm")
    public Form createFormModel() {
        return new Form();
    }

    /**
     * Method determines user roles
     *
     * @param session Current session
     * @return one of the pages(Admin,User,Login)
     */
    @RequestMapping(value = "index")
    public String login(HttpSession session) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = userDao.findByLogin(authentication.getName());
        session.setAttribute("user", user);
        if (authentication.getAuthorities().toString().equals("[ADMINISTRATOR]")) {
            session.setAttribute("usersList", userDao.findAll());
            return "Admin";
        } else if (authentication.getAuthorities().toString().equals("[USER]")) {
            return "User";
        } else {
            return "Login";
        }
    }

    /**
     * Method denies access the user to the admin pages
     * @param session
     * @return 403-page
     */
    @RequestMapping(value = "403", method = RequestMethod.GET)
    public ModelAndView accessDenied(HttpSession session) {
        ModelAndView model = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
           model.addObject("user", authentication.getName());
        }
        model.setViewName("403");
        return model;
    }

    /**
     * Method redirects the user to a page AddUser
     *
     * @param model Model
     * @return AddUser-page
     */
    @RequestMapping(value = "GoToAddPage", method = RequestMethod.GET)
    public String goToAddPage(Model model) {
        model.addAttribute("validationForm", new Form());
        return "AddUser";
    }

    /**
     * Method to add a user to the database and check the validity of the entered data
     *
     * @param form          form to be filled
     * @param bindingResult result after filling

     * @param session       current session
     * @return one of the pages(AddUser,Admin)
     */
    @RequestMapping(value = "AddUserAction", method = RequestMethod.POST)
    public String addUser(
            @ModelAttribute("validationForm") @Validated Form form,
            BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "AddUser";
        } else {
            User user = new User();
            user.setLogin(form.getLogin());
            user.setPassword(form.getPassword());
            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setBirthday(Date.valueOf(form.getBirthday()));
            if (form.getRole().equals("user")) {
                user.setId_role(roleDao.findByName("USER"));
            } else {
                user.setId_role(roleDao.findByName("ADMINISTRATOR"));
            }
            userDao.create(user);
            session.setAttribute("usersList", userDao.findAll());
        }
        return "Admin";
    }

    /**
     * Method which removes the user from the database
     *
     * @param id      user id
     * @param session current session
     * @return Admin page
     */
    @RequestMapping(value = "DeleteUserAction", method = RequestMethod.GET)
    public String deleteUser(@RequestParam long id, HttpSession session) {
        User user = userDao.findById(id);
        userDao.remove(user);
        session.setAttribute("usersList", userDao.findAll());
        return "Admin";
    }

}
