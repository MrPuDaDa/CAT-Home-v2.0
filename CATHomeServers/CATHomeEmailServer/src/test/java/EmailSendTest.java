import org.jun.util.EmailSendUtil;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/7/1 17:39
 **/
public class EmailSendTest {
    public static void main(String[] args) {
        // 收件人邮箱地址
        String receiver = "pj15378689391@gmail.com";

        // office365 邮箱服务器地址及端口号
        //这个就是之前的Server Name，注意：你使用的Outlook应用可能使用了不同的服务器，根据自己刚才拿到的地址为准
        //String host = "smtp.office365.com";
        //String port = "587";    //这个就是拿到的port
        Integer b = EmailSendUtil.SendUserRegisterCodeEmail(receiver);
        if (!b.equals(0)) {
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败");
        }
    }
}
