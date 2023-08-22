package org.jun.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/7/1 17:15
 **/
public class EmailSendUtil {
    // Outlook发送
    public static Integer SendUserRegisterCodeEmail(String receiver) {
        // 填写你的outlook帐户的授权码
        // 以及你的outlook的授权码
        String sender = "pj15378689391@outlook.com";
        String password = "uzizvowadcmqofam";
        // office365 邮箱服务器地址及端口号
        // 这个就是之前的Server Name，注意：你使用的Outlook应用可能使用了不同的服务器，根据自己刚才拿到的地址为准
        String host = "smtp.office365.com";
        String port = "587";

        try {
            Properties props = new Properties();
            // 开启debug调试
            props.setProperty("mail.debug", "false");
            // 发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");
            // 设置邮件服务器主机名
            props.setProperty("mail.host", host);
            // 发送邮件协议名称 这里使用的是smtp协议
            props.setProperty("mail.transport.protocol", "smtp");
            // 服务端口号
            props.setProperty("mail.smtp.port", port);
            // 权限设置
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "true");
            props.put("mail.properties.mail.smtp.starttls.required", "true");

            // 设置环境信息
            Session session = Session.getInstance(props);

            // 创建邮件对象
            MimeMessage msg = new MimeMessage(session);

            // 设置发件人
            msg.setFrom(new InternetAddress(sender));

            // 设置收件人
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

            // 设置邮件主题
            msg.setSubject("亲爱的猫之家用户，您好！！！");

            // 设置邮件内容
            Multipart multipart = new MimeMultipart();

            // 生成随机验证码 6位
            Integer code = (int) ((Math.random() * 9 + 1) * 100000);

            // 添加内容
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("<html lang='zh-CN'><head ><meta charset='utf-8'>"
                            + "</head><body>"
                            + "<h1>这是您的邮箱验证码:</h1>"
                            + "<h1 style=\"color:red;font-family:arial;font-size:75px;\">" + code + "</h1><br>"
                            + "<p>每日一言：为寻求真理的努力所付出的代价，总是比不担风险地占有它要高昂得多。<p/>"
                            + "<p>如果是你本人，则你的帐户安全，可以进行下一步。<p/>"
                            + "<p>如果不是你本人，表明某个恶意用户试图访问你的帐户。<p/>"
                            + "<p>请查看你最近的活动，我们将帮助你保护你的帐户。<p/>"
                            + "<a href='http://localhost:5173/'><p style=\"background-color:#7a7374;color:white\">前往猫之家(ps:前端规划中！！!敬请期待}</p></a><br>"
                            + "<p>附送一张精美猫咪图片，希望您生活愉快</p>"
                            + "图片请见附件<p/>"
                            + "<p>谢谢！</p></body></html>"
                            + "<div style=\"color:#7a7374;position:fixed; bottom:0px; width:100%; text-align:center;font-size:15px\">猫之家服务公司  By:MrPu</div>",
                    "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);

//            //添加附件
//            MimeBodyPart attachPart = new MimeBodyPart();
//            //可以选择发送文件...
//            DataSource source = new FileDataSource("C:\\Users\\pj153\\Pictures\\wallhaven-p9op59.jpg");
//            attachPart.setDataHandler(new DataHandler(source));
//            //设置文件名
//            attachPart.setFileName("wallhaven-1ppy5w.jpg");
//            multipart.addBodyPart(attachPart);

            msg.setContent(multipart);

            Transport transport = session.getTransport();
            // 连接邮件服务器
            transport.connect(sender, password);
            // 发送邮件
            transport.sendMessage(msg, new Address[]{new InternetAddress(receiver)});
            // 关闭连接
            transport.close();

            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
