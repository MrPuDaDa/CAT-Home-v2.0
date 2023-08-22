package pojo.goods;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import entity.IdWorker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "catGoodType")
public class CatGoodType {
    IdWorker idWorker = new IdWorker(0, 0);
    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "createDate")
    private String createDate;
    @Column(name = "name")
    private String name;
    @Column(name = "updateDate")
    private String updateDate;

    public CatGoodType() {
        id = idWorker.nextId();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
