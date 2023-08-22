package pojo.letterpost;

import entity.IdWorker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/4/5 12:20
 **/
@Table(name = "message")
public class Message {
    IdWorker idWorker = new IdWorker(0, 0);
    @Id
    private Long id = idWorker.nextId();
    @Column(name = "content")
    private String content;
    @Column(name = "createDate")
    private String createDate;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
