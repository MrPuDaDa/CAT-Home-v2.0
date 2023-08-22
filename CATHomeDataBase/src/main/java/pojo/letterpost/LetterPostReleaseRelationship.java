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
@Table(name = "letterPostReleaseRelationship")
public class LetterPostReleaseRelationship {
    IdWorker idWorker = new IdWorker(0, 0);
    @Id
    private Long id = idWorker.nextId();
    @Column(name = "letterPostId")
    private Long letterPostId;
    @Column(name = "publisherId")
    private Long publisherId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLetterPostId() {
        return letterPostId;
    }

    public void setLetterPostId(Long letterPostId) {
        this.letterPostId = letterPostId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }
}
