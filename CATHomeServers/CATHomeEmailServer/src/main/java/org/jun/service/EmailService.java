package org.jun.service;

import entity.Result;
import entity.StatusCode;
import org.jun.util.EmailSendUtil;
import org.springframework.stereotype.Service;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/7/1 18:45
 **/
@Service
public class EmailService {
    // 发送用户 注册/忘记密码 验证码
    public Result sendUserCode(String emailAddress){
        Integer EmailCode = EmailSendUtil.SendUserRegisterCodeEmail(emailAddress);
        if (!EmailCode.equals(0)) {
            return new Result<>(true, StatusCode.OK, "发送邮件成功", EmailCode.toString());
        } else {
            return new Result<>(false, StatusCode.ERROR, "发送邮件失败");
        }
    }
}
