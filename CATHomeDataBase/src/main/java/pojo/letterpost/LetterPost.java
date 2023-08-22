package pojo.letterpost;

import com.baomidou.mybatisplus.annotation.TableField;
import entity.IdWorker;
import pojo.user.Users;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "letterPost")
public class LetterPost {
    IdWorker idWorker = new IdWorker(0, 0);
    @Id
    private Long id = idWorker.nextId();
    @Column(name = "image3")
    private String image3 = "";
    @Column(name = "image1")
    private String image1 = "";
    @Column(name = "image2")
    private String image2 = "";

    @Column(name = "videoPath")
    private String videoPath = "";

    @Column(name = "content")
    private String content;
    @Column(name = "place")
    private String place = "";
    @Column(name = "giveUpNum")
    private Integer giveUpNum = 0;
    @Column(name = "giveDownNum")
    private Integer giveDownNum = 0;
    @Column(name = "forwordNum")
    private Integer forwordNum = 0;
    @Column(name = "auditStatus")
    private String auditStatus = "0";
    @Column(name = "reasonsForRejection")
    private String reasonsForRejection = "";
    @Column(name = "createDate")
    private String createDate;
    @Column(name = "updateDate")
    private String updateDate;

    @TableField(exist = false)
    private Users letterUsers;

    public Users getLetterUsers() {
        return letterUsers;
    }

    public void setLetterUsers(Users letterUsers) {
        this.letterUsers = letterUsers;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getGiveUpNum() {
        return giveUpNum;
    }

    public void setGiveUpNum(Integer giveUpNum) {
        this.giveUpNum = giveUpNum;
    }

    public Integer getGiveDownNum() {
        return giveDownNum;
    }

    public void setGiveDownNum(Integer giveDownNum) {
        this.giveDownNum = giveDownNum;
    }

    public Integer getForwordNum() {
        return forwordNum;
    }

    public void setForwordNum(Integer forwordNum) {
        this.forwordNum = forwordNum;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getReasonsForRejection() {
        return reasonsForRejection;
    }

    public void setReasonsForRejection(String reasonsForRejection) {
        this.reasonsForRejection = reasonsForRejection;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
