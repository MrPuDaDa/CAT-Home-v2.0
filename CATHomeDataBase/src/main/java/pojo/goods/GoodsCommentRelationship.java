package pojo.goods;

import entity.IdWorker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/4/7 11:14
 **/
@Table(name = "goodsCommentRelationship")
public class GoodsCommentRelationship {
    IdWorker idWorker = new IdWorker(0, 0);
    @Id
    @Column(name = "id")
    private Long id = idWorker.nextId();

    @Column(name = "goodsId")
    private Long goodsId;

    @Column(name = "commentId")
    private Long commentId;

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

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}
