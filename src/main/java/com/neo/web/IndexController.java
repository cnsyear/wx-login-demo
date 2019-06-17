package com.neo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 官方
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
 * 博客
 * https://segmentfault.com/a/1190000013392838
 * 测试平台
 * https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index
 */
@Controller
public class IndexController {

    /**
     * 首页跳转到微信页面授权页
     *
     * @param map
     * @return
     */
    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("message", "http://weixin0000.free.idcfengye.com/");
        return "index";
    }

    @RequestMapping("/auth")
    public String auth(@RequestParam("code") String code, @RequestParam("state") String state, ModelMap map) {
        System.out.println("auth开始了。。。。");
        System.out.println("code={}" + code);
        System.out.println("state={}" + state);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx3a1d9011d6145273&secret=2b026f13919a54244cb9db503170f47e&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        map.addAttribute("result", result);
        return "userInfo";
    }

}