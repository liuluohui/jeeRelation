package com.jee.web.controller;

import com.jee.web.api.entity.User;
import com.jee.web.api.exception.RestfulException;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/6/6.
 */

@Scope("request")
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/{name}/{email}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable String name, @PathVariable String email) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);

        ModelAndView modelAndView = new ModelAndView("/user/index", "userInfo", user);
        return modelAndView;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView saveUser(@RequestBody User user) {

        ModelAndView modelAndView = new ModelAndView();
        if ("zxs".equals(user.getName())) {
            throw new RestfulException("User zxs is unique!");
        } else {
            modelAndView.setViewName("/user/index");
            modelAndView.addObject("userInfo", user);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/user/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ModelAndView updateUser(@RequestBody User user) {
        ModelAndView modelAndView = new ModelAndView("/user/index", "userInfo", user);
        return modelAndView;

    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ModelAndView removeUser(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("/user/index", "uname", name);
        return modelAndView;
    }

}
