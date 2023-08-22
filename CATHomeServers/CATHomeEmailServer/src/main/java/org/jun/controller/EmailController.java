package org.jun.controller;

import entity.Result;
import org.jun.service.EmailService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/7/1 18:54
 **/
@RestController
@RequestMapping(value = "MailCode")
@CrossOrigin // 跨域 限制转载使用
public class EmailController {
    @Resource
    private EmailService emailService;
    @RequestMapping("User/{emailAddress}")
    public Result sendUserCode(@PathVariable(name = "emailAddress") String emailAddress){
        return emailService.sendUserCode(emailAddress);
    }

}
