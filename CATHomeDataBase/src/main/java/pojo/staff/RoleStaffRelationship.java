package pojo.staff;

import entity.IdWorker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "roleStaffRelationship")
public class RoleStaffRelationship {
    IdWorker idWorker =new IdWorker(0,0);
    @Id
    private Long Id = idWorker.nextId();
    @Column(name = "staffId")
    private Long staffId;
    @Column(name = "roleId")
    private Long roleId;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
