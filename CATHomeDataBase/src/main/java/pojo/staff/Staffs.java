package pojo.staff;

import com.baomidou.mybatisplus.annotation.TableField;
import entity.IdWorker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "staffs")
public class Staffs {
    IdWorker idWorker =new IdWorker(0,0);
    @Id
    private Long id = idWorker.nextId();
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "image")
    private String image;
    @Column(name = "createDate")
    private String createDate;
    @TableField(exist = false)
    private Roles roles;

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "email")
    private String email;
    @Column(name = "updateDate")
    private String updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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
}
