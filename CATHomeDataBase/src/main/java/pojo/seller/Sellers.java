package pojo.seller;


import entity.IdWorker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sellers")
public class Sellers {
    IdWorker idWorker = new IdWorker(0, 0);
    @Id
    private Long id = idWorker.nextId();
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "isCertified")
    private String isCertified = "0";

    public String getCertificatesImage() {
        return certificatesImage;
    }

    public void setCertificatesImage(String certificatesImage) {
        this.certificatesImage = certificatesImage;
    }

    @Column(name = "certificatesImage")
    private String certificatesImage;
    @Column(name = "fansNum")
    private Long fansNum = 0L;
    @Column(name = "image")
    private String image;
    @Column(name = "shopownerName")
    private String shopownerName;
    @Column(name = "shopownerCodeId")
    private Long shopownerCodeId;
    @Column(name = "shopownerCapital")
    private Double shopownerCapital;
    @Column(name = "shopownerTel")
    private Long shopownerTel;
    @Column(name = "badNum")
    private Long badNum = 0L;
    @Column(name = "niceNum")
    private Long niceNum = 0L;
    @Column(name = "createDate")
    private String createDate;
    @Column(name = "updateDate")
    private String updateDate;
    @Column(name = "reasonsForRejection")
    private String reasonsForRejection;

    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReasonsForRejection() {
        return reasonsForRejection;
    }

    public void setReasonsForRejection(String reasonsForRejection) {
        this.reasonsForRejection = reasonsForRejection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(String isCertified) {
        this.isCertified = isCertified;
    }


    public Long getFansNum() {
        return fansNum;
    }

    public void setFansNum(Long fansNum) {
        this.fansNum = fansNum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShopownerName() {
        return shopownerName;
    }

    public void setShopownerName(String shopownerName) {
        this.shopownerName = shopownerName;
    }

    public Long getShopownerCodeId() {
        return shopownerCodeId;
    }

    public void setShopownerCodeId(Long shopownerCodeId) {
        this.shopownerCodeId = shopownerCodeId;
    }

    public Double getShopownerCapital() {
        return shopownerCapital;
    }

    public void setShopownerCapital(Double shopownerCapital) {
        this.shopownerCapital = shopownerCapital;
    }

    public Long getShopownerTel() {
        return shopownerTel;
    }

    public void setShopownerTel(Long shopownerTel) {
        this.shopownerTel = shopownerTel;
    }

    public Long getBadNum() {
        return badNum;
    }

    public void setBadNum(Long badNum) {
        this.badNum = badNum;
    }

    public Long getNiceNum() {
        return niceNum;
    }

    public void setNiceNum(Long niceNum) {
        this.niceNum = niceNum;
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
