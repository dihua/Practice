package practise.wu.aop.annotate.controller;

import practise.wu.aop.annotate.model.RespHead;
import practise.wu.aop.annotate.model.Result;
import practise.wu.aop.annotate.model.User;
import practise.wu.aop.annotate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dihua.wu
 * @description
 * @create 2020/7/21
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public Result<User> getUser(User user){
        user=userService.getUser(user);
        Result<User> result =new Result<User>();
        RespHead respHead=new RespHead();
        result.setRespHead(respHead);
        result.setContent(user);
        return result;
    }

}
