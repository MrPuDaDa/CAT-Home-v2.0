package pojo.order;

import entity.IdWorker;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/6/3 18:36
 **/
public class Order {
    IdWorker idWorker = new IdWorker(0, 0);
    @Id
    @Column(name = "id")
    private Long id = idWorker.nextId();
    @Column(name = "orderNum")
    private String orderNum;
    @Column(name = "pay")
    private String pay = "0";

    @Column(name = "createDate")
    private String createDate;

    @Column(name = "updateDate")
    private String updateDate;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
