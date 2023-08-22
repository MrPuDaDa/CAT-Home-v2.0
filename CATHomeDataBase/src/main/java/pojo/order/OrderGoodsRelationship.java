package pojo.order;

import entity.IdWorker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/4/5 15:12
 **/
@Table(name = "orderGoodsRelationship")
public class OrderGoodsRelationship {
    IdWorker idWorker = new IdWorker(0, 0);
    @Id
    private Long id = idWorker.nextId();
    @Column(name = "goodsId")
    private Long goodsId;
    @Column(name = "orderId")
    private Long orderId;
    @Column(name = "buyerId")
    private Long buyerId;
    @Column(name = "betrayId")
    private Long betrayId;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getBetrayId() {
        return betrayId;
    }

    public void setBetrayId(Long betrayId) {
        this.betrayId = betrayId;
    }
}
