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
@Table(name = "letterPostMessageRelationship")
public class LetterPostMessageRelationship {
    IdWorker idWorker = new IdWorker(0, 0);
    @Id
    private Long id = idWorker.nextId();
    @Column(name = "messageId")
    private Long messageId;
    @Column(name = "objectId")
    private Long objectId;

    @Column(name = "originator")
    private Long originator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getOriginator() {
        return originator;
    }

    public void setOriginator(Long originator) {
        this.originator = originator;
    }
}
