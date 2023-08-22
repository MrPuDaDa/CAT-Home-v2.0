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
@Table(name = "comment")
public class Comment {
    IdWorker idWorker = new IdWorker(0, 0);
    @Id
    @Column(name = "id")
    private Long id = idWorker.nextId();

    @Column(name = "content")
    private String content;

    @Column(name = "favorableRate")
    private String favorableRate;

    @Column(name = "createDate")
    private String createDate;

    @Column(name = "userId")
    private Long userId;

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

    public String getFavorableRate() {
        return favorableRate;
    }

    public void setFavorableRate(String favorableRate) {
        this.favorableRate = favorableRate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
