package practise.wu.controller;

import practise.wu.model.RespHead;
import practise.wu.model.Result;
import practise.wu.model.User;
import practise.wu.service.UserService;
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
