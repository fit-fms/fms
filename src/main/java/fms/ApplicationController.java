package fms;

import fms.jcr.InitDB;
import org.jtwig.mvc.JtwigViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;


import javax.jcr.Session;
import java.util.Collection;

@Controller
public class ApplicationController {
    @Autowired
    private UserService userService;

    @Autowired
    private InitDB initDB;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails =
                    (UserDetails) authentication.getPrincipal();
            map.addAttribute("userDetails", userDetails);
        }
        return "index";
    }

    //Taky nefunguje
    @RequestMapping(value = "/clearcache", method = RequestMethod.GET)
    public String clearCache(ModelMap map, JtwigViewResolver resolver) {
        resolver.clearCache();
        return "index";
    }


    @RequestMapping(value = "/initdb", method = RequestMethod.GET)
    public String init(ModelMap map) throws Exception {

        try {
            initDB.init();
            map.addAttribute("status", "podarilo");
        } catch (Exception e) {
            map.addAttribute("status", "<b>ne</b>podarilo   - " + e.toString());
        }
        return "init_db";
    }


    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(ModelMap map) {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> securedMessage = userService.getAuthorities(userDetails);
        map.addAttribute("userDetails", userDetails);
        map.addAttribute("userAuthorities", securedMessage);
        return "admin";
    }
}
