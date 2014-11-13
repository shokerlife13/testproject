package laba17.web;

import laba17.domain.User;
import laba17.service.RoleDaoService;
import laba17.service.UserDaoService;
import laba17.validation.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * Class that controls editing users, the validity of the data when editing a user
 * Created by redko on 10/2/2014.
 */
@org.springframework.stereotype.Controller
public class ControllerEdit {

    @Autowired
    private RoleDaoService roleDao;
    @Autowired
    private UserDaoService userDao;

    /**
     * Edit-form validator instance
     */
    @Autowired
    @Qualifier("formValidatorEdit")
    private Validator validatorEdit;

    /**
     * Setting validator
     *
     * @param binder WebDataBinder
     */
    @InitBinder
    private void initBinderEdit(WebDataBinder binder) {
        binder.setValidator(validatorEdit);
    }

    /**
     * Creating edit-form
     *
     * @return edit-form
     */
    @ModelAttribute("validationFormEdit")
    public Form createFormModelEdit() {
        return new Form();
    }

    /**
     * Method redirects the user to a page EditUser
     *
     * @param id    user id
     * @param model Model
     * @return EditUser-page
     */
    @RequestMapping(value = "GoToEditPage", method = RequestMethod.GET)
    public String goToEditPage(@RequestParam long id, Model model) {
        User user = userDao.findById(id);
        Form form = new Form();
        form.setLogin(user.getLogin());
        form.setPassword(user.getPassword());
        form.setPasswordAgain(user.getPassword());
        form.setEmail(user.getEmail());
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setBirthday(user.getBirthday().toString());
        form.setRole(user.getId_role().getName().toLowerCase());
        model.addAttribute("validationFormEdit", form);
        return "EditUser";
    }

    /**
     * Method to add a user to the database and check the validity of the entered data
     *
     * @param form          form to be filled
     * @param bindingResult result after filling
     * @param session       current session
     * @return one of the pages(EditUser,Admin)
     */
    @RequestMapping(value = "EditUserAction", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("validationFormEdit") @Validated Form form,
                           BindingResult bindingResult, HttpSession session) {
        User user = userDao.findByLogin(form.getLogin());
        if (bindingResult.hasErrors()) {
            return "EditUser";
        } else {
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
            userDao.update(user);
            session.setAttribute("usersList", userDao.findAll());
        }
        return "Admin";
    }

}