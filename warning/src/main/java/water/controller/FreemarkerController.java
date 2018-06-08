package water.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class FreemarkerController {

    private static final String login ="login";
    private static final String welcome="welcome";
    private static final String upload="upload";
    @GetMapping("/login")
    public String login(){
        return login;
    }
    @GetMapping("/welcome")
    public String welcome(){
        return welcome;
    }
    @GetMapping("/upload")
    public String upload(){
        return upload;
    }
}
