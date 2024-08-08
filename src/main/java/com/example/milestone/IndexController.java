package com.example.milestone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Random;


@Controller
public class IndexController {
    @GetMapping("/")
    public String getIndex() {
        return "index";
    }
    @GetMapping("hello")
    public String getHello(Model model) {
        int random = new Random().nextInt(3);
        String[] omikuji = {"大吉","中吉","庄吉"};
        model.addAttribute("name","田中");
        model.addAttribute("omikuji",omikuji[random]);
        return "hello";
    }
    
    
}
