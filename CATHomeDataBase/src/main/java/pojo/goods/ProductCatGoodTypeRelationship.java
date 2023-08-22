package pojo.goods;

import entity.IdWorker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "productCatGoodTypeRelationship")
public class ProductCatGoodTypeRelationship {
    IdWorker idWorker =new IdWorker(0,0);

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

    public Long getCatGoodTypeId() {
        return catGoodTypeId;
    }

    public void setCatGoodTypeId(Long catGoodTypeId) {
        this.catGoodTypeId = catGoodTypeId;
    }

    @Id
    @Column(name = "id")
    private Long id = idWorker.nextId();
    @Column(name = "goodsId")
    private Long goodsId;
    @Column(name = "catGoodTypeId")
    private Long catGoodTypeId;
}
