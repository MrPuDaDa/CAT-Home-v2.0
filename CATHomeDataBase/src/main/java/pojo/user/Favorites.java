package pojo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import entity.IdWorker;
import pojo.goods.Goods;
import pojo.letterpost.LetterPost;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "favorites")
public class Favorites {
    IdWorker idWorker = new IdWorker(0, 0);
    @Id
    @Column(name = "id")
    private Long id = idWorker.nextId();

    @Column(name = "objectId")
    private Long objectId;

    @Column(name = "type")
    private String type;

    @Column(name = "likeness")
    private String likeness;

    @Column(name = "createDate")
    private String createDate;

    @TableField(exist = false)
    private Goods goods;

    public LetterPost getLetterPost() {
        return letterPost;
    }

    public void setLetterPost(LetterPost letterPost) {
        this.letterPost = letterPost;
    }

    @TableField(exist = false)
    private LetterPost letterPost;

    @Column(name = "userId")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLikeness() {
        return likeness;
    }

    public void setLikeness(String likeness) {
        this.likeness = likeness;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
