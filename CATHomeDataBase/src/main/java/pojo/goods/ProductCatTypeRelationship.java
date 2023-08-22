package pojo.goods;

import entity.IdWorker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "productCatTypeRelationship")
public class ProductCatTypeRelationship {
    IdWorker idWorker =new IdWorker(0,0);
    @Id
    @Column(name = "id")
    private Long id = idWorker.nextId();
    @Column(name = "goodsId")
    private Long goodsId;

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

    public Long getCatTypeId() {
        return catTypeId;
    }

    public void setCatTypeId(Long catTypeId) {
        this.catTypeId = catTypeId;
    }

    @Column(name = "catTypeId")
    private Long catTypeId;
}
