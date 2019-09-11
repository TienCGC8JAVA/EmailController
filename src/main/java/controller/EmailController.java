package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class EmailController {

  private static Pattern pattern;

  private Matcher matcher;

  private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

  public EmailController() {
    pattern = Pattern.compile(EMAIL_REGEX);
  }

  @GetMapping("/")
  String getIndex() {
    return "index";
  }

  @PostMapping("/validate")
  ModelAndView validateEmail(@RequestParam String email){
    ModelAndView modelAndView1 = new ModelAndView("success");
    ModelAndView modelAndView2 = new ModelAndView("index");
    boolean isValid = this.validate(email);
    if(!isValid){
      modelAndView2.addObject("message", "Email is invalid");
      return  modelAndView2;
    }
    modelAndView1.addObject("email", email);
    return modelAndView1;
  }

  private boolean validate(String regex) {
    matcher = pattern.matcher(regex);
    return  matcher.matches();
  }
}
