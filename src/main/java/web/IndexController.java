package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        System.out.println("index");
        return "index";
    }

    @RequestMapping("/map")
    public Map<String,String> map(){
        System.out.println("map");
        Map<String,String> map = new HashMap<>();
        map.put("x","xx");
        return map;
    }
}
