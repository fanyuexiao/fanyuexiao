package web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
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
