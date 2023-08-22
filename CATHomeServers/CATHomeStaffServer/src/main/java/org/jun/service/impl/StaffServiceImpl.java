package org.jun.service.impl;

import entity.BCrypt;
import entity.Date;
import entity.Result;
import entity.StatusCode;
import org.jun.mapper.goods.CatGoodTypeMapper;
import org.jun.mapper.goods.CatTypeMapper;
import org.jun.mapper.staff.RoleStaffRelationshipMapper;
import org.jun.mapper.staff.RolesMapper;
import org.jun.mapper.staff.StaffMapper;
import org.jun.service.StaffService;
import org.springframework.stereotype.Service;
import pojo.goods.CatGoodType;
import pojo.goods.CatType;
import pojo.staff.RoleStaffRelationship;
import pojo.staff.Roles;
import pojo.staff.Staffs;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Resource
    private CatTypeMapper catTypeMapper;
    @Resource
    private CatGoodTypeMapper catGoodsTypeMapper;
    @Resource
    private StaffMapper staffMapper;
    @Resource
    private RoleStaffRelationshipMapper roleStaffRelationshipMapper;
    @Resource
    private RolesMapper rolesMapper;
    @Override
    public Result<List<CatType>> findAllCatType() {
        List<CatType> catTypes = catTypeMapper.selectAll();
        return new Result<List<CatType>>(true, StatusCode.OK,
                "查询所有猫咪种类数据成功", catTypes);
    }

    @Override
    public Result<List<CatGoodType>> findAllCatGoodsType() {
        List<CatGoodType> catGoodTypes = catGoodsTypeMapper.selectAll();
        return new Result<List<CatGoodType>>(true, StatusCode.OK,
                "查询所有猫咪商品种类成功", catGoodTypes);
    }

    @Override
    public Result<Staffs> staffLogin(Staffs staffs) {
        // 自定义条件搜索对象Example
        Example example = new Example(Staffs.class);
        Example.Criteria criteria = example.createCriteria(); // 条件构造器
        criteria.andEqualTo("name", staffs.getName());
        List<Staffs> Staffs = staffMapper.selectByExample(example);
        Staffs returnDBStaff = staffCheck(Staffs, staffs);
        if (returnDBStaff != null) {
            return new Result(true, StatusCode.OK,
                    "登陆成功", returnDBStaff);
        }
        return new Result(false, StatusCode.ERROR,
                "登陆失败,请检查账号与密码");
    }

    private Staffs staffCheck(List<Staffs> staffsList, Staffs staffs) {
        for (Staffs staff : staffsList
        ) {
            // 密码验证方法 staff.getPassword()加密密码、(staffs.getPassword()原密码 返回值 bool
            if (BCrypt.checkpw(staffs.getPassword(), staff.getPassword())) {
                // 获取管理员权限
                Example example = new Example(RoleStaffRelationship.class);
                Example.Criteria criteria = example.createCriteria(); // 条件构造器
                criteria.andEqualTo("staffId", staff.getId());
                List<RoleStaffRelationship> roleStaffRelationshipList = roleStaffRelationshipMapper.selectByExample(example);
                RoleStaffRelationship roleStaffRelationship = roleStaffRelationshipList.get(0);
                Roles staffRoleInfo = rolesMapper.selectByPrimaryKey(roleStaffRelationship.getRoleId());
                staff.setRoles(staffRoleInfo);
                // 设置更新时间（最后登陆时间）
                Date creatDate = new Date();
                Staffs staffsLastLoginTime = new Staffs();
                staffsLastLoginTime.setUpdateDate(creatDate.GetNowDate());
                staffsLastLoginTime.setId(staff.getId());
                staffMapper.updateByPrimaryKeySelective(staffsLastLoginTime);
                return staff;
            }
        }
        return null;
    }
}
