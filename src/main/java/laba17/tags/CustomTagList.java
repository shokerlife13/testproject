package laba17.tags;

import laba17.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * Class for creating custom tag - list of users for administrator
 * <p/>
 * Created by redko on 9/19/2014.
 */
public class CustomTagList implements Tag {

    /**
     * For logging
     */
    private static Log log = LogFactory.getLog(CustomTagList.class);

    private PageContext pageContext;
    private Tag parentTag;
    /**
     * List of database users
     */
    private List<User> usersList;


    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    /**
     * Set the current page context
     *
     * @param pageContext page context for this tag handler
     */
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    /**
     * Get parent tag
     *
     * @return the current parent
     */
    public Tag getParent() {
        return parentTag;
    }

    /**
     * Set parent
     *
     * @param tag Parent tag
     */
    public void setParent(Tag tag) {
        parentTag = tag;
    }

    /**
     * The start tag
     *
     * @return Skip body
     * @throws javax.servlet.jsp.JspException
     */
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.println("<table id=\"listTable\" border=\"1\" style=\"font-size:20px;\">");
            out.println("<tr style=\"border:1px black solid; font-size:20px;\">");
            out.println("<td>Login</td>");
            out.println("<td>First Name</td>");
            out.println("<td>Last Name</td>");
            out.println("<td>Age</td>");
            out.println("<td>Role</td>");
            out.println("<td colspan=\"2\">Actions</td>");
            out.println("</tr>");

            if (usersList != null) {
                for (User user : usersList) {
                    out.println("<tr>");
                    out.println("<td>" + user.getLogin() + "</td>");
                    out.println("<td>" + user.getFirstName() + "</td>");
                    out.println("<td>" + user.getLastName() + "</td>");
                    out.println("<td>" + getAge(user) + "</td>");
                    out.println("<td>" + getRoleName(user) + "</td>");
                    out.println("<td colspan=\"2\">" + "<a href=\"GoToEditPage?id=" + user.getId_user() + "\">" + "Edit</a>");
                    out.println("&nbsp");
                    out.println("<a href=\"DeleteUserAction?id=" + user.getId_user() + "\" onclick=\"return confirm(\'Are you sure?\')\">" + "Delete </a>");
                    out.println("</td>");
                    out.println("</tr>");
                }
            }
            out.println("</table>");
        } catch (IOException e) {
            log.error("Error with create html-table", e);
        }
        return SKIP_BODY;
    }

    /**
     * The end tag
     *
     * @return The rest of the page continues to be evaluated
     * @throws javax.servlet.jsp.JspException
     */
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    /**
     * Clearing values
     */
    public void release() {
        parentTag = null;
    }

    /**
     * Getting the user's age
     *
     * @param user User
     * @return User's age
     */
    private int getAge(User user) {
        Calendar userBirthday = Calendar.getInstance();
        userBirthday.setTime(user.getBirthday());
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - userBirthday.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= userBirthday.get(Calendar.DAY_OF_YEAR))
            age--;
        return age;
    }

    /**
     * Getting the user's role name
     *
     * @param user User
     * @return Role name
     */
    private String getRoleName(User user) {
        if (user.getId_role().getId_role() == 1) {
            return "Administrator";
        } else {
            return "User";
        }
    }
}
