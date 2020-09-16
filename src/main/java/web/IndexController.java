package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping("/index.do")
    @ResponseBody
    public String index(){
        System.out.println("index");
        return "index";
    }

    @RequestMapping("/map.do")
    @ResponseBody
    public Map<String,String> map(){
        System.out.println("map");
        Map<String,String> map = new HashMap<>();
        map.put("x","xx");
        return map;
    }

    @RequestMapping("/static.do")
    public String page(){
        System.out.println("static");
        return "static";
    }
}
