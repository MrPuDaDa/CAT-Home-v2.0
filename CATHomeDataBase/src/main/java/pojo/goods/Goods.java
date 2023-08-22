package pojo.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import entity.IdWorker;
import pojo.seller.Sellers;
import pojo.user.Users;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@TableName("goods")
/*
 * 出售或者赠送猫咪用
 * */
public class Goods {

    IdWorker idWorker = new IdWorker(0, 0);
    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    @Column(name = "id")
    private Long id = idWorker.nextId();
    @Column(name = "title")
    private String title;
    @Column(name = "productDescription")
    private String productDescription;
    @Column(name = "image")
    private String image;
    @Column(name = "auditStatus")
    private String auditStatus = "0";
    @Column(name = "reasonsForRejection")
    private String reasonsForRejection = null;
    @Column(name = "isDelete")
    private String isDelete = "0";
    @Column(name = "createDate")
    private String createDate;
    @Column(name = "updateDate")
    private String updateDate;
    @Column(name = "price")
    private Double price;

    @Column(name = "pageView")
    private Integer pageView = 0;
    @TableField(exist = false)
    private Users users;

    @Column(name = "stockNumber")
    private Integer stockNumber;

    public Integer getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public CatGoodType getCatGoodType() {
        return catGoodType;
    }

    public void setCatGoodType(CatGoodType catGoodType) {
        this.catGoodType = catGoodType;
    }

    public CatType getCatType() {
        return catType;
    }

    public void setCatType(CatType catType) {
        this.catType = catType;
    }

    @TableField(exist = false)
    private CatGoodType catGoodType;

    @TableField(exist = false)
    private CatType catType;

    @TableField(exist = false)
    private List<Comment> commentList;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Sellers getSellers() {
        return sellers;
    }

    public void setSellers(Sellers sellers) {
        this.sellers = sellers;
    }

    @TableField(exist = false)
    private Sellers sellers;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", image='" + image + '\'' +
                ", auditStatus='" + auditStatus + '\'' +
                ", reasonsForRejection='" + reasonsForRejection + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return productDescription;
    }

    public void setDescribe(String describe) {
        this.productDescription = describe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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
