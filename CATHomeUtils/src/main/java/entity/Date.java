package entity;

import java.text.SimpleDateFormat;

public class Date {
    public String GetNowDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String creatDate = formatter.format(new java.util.Date().getTime());
        return creatDate;
    }
}
