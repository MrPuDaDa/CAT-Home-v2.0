package pojo.goods;

import entity.IdWorker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

// 商品发布者关系表
@Table(name = "productReleaseRelationship")
public class ProductReleaseRelationship {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    IdWorker idWorker =new IdWorker(0,0);
    @Id
    @Column(name = "id")
    private Long id = idWorker.nextId();
    @Column(name = "goodsId")
    private Long goodsId;
    @Column(name = "publisherId")
    private Long publisherId;
    @Column(name = "type")
    private String type;
}
