package com.kyoofus.framework.core.controller; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kyoofus.framework.core.util.ServletUtils;
import com.kyoofus.framework.core.util.SessionUtils;

/** 기본 컨트롤러이다. */
@Controller
public class DefaultController {

    /** Index 페이지  */
    // @GetMapping("/")
    // public ModelAndView index() {
    //   ModelAndView mav = new ModelAndView("index");
    //   boolean isLogin = SessionUtils.isLogin(); // 로그인 여부 
    //   mav.addObject("hello", "반가워요");
    //   mav.addObject("isLogin", isLogin);
    //   return mav ; 
    // }

        @GetMapping("/")
    public String index() {
      // String url = "http://ejin.com:5173"; 
      // ServletUtils.redirect(url);
      return "redirect:https://ejin.com/signin?authCode=1234";
    }

    

    /** home page */
    @GetMapping("/home")
    public ModelAndView home() {
      ModelAndView mav = new ModelAndView("home");
      mav.addObject("hello", "반가워요");
      return mav ; 
    }
    @GetMapping("/loginfail")
    public ModelAndView loginfail() {
      ModelAndView mav = new ModelAndView("loginfail");
      return mav ; 
    }
    @GetMapping("/error")
    public ModelAndView error() {
      ModelAndView mav = new ModelAndView("error");
      return mav ; 
    }

}///~