package pojo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import entity.IdWorker;
import pojo.goods.Goods;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/4/4 22:22
 **/
@Table(name = "shoppingCart")
public class ShoppingCart {
    IdWorker idWorker = new IdWorker(0, 0);
    @Id
    @Column(name = "id")
    private Long id = idWorker.nextId();

    @Column(name = "goodsId")
    private Long goodsId;

    @Column(name = "userId")
    private Long userId;
    @TableField(exist = false)
    private Goods goods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodId) {
        this.goodsId = goodId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
