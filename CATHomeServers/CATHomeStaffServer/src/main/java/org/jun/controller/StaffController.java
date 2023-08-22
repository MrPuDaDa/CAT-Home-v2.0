package org.jun.controller;

import entity.Result;
import entity.StatusCode;
import org.jun.service.StaffService;
import org.springframework.web.bind.annotation.*;
import pojo.goods.CatGoodType;
import pojo.goods.CatType;
import pojo.staff.Staffs;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "Staff")
@CrossOrigin // 跨域 限制转载使用
public class StaffController {
    @Resource
    private StaffService staffService;
    // 获取所有的猫咪商品种类
    @GetMapping(value = "/CatGoodsType")
    public Result<List<CatGoodType>> findAllCatGoodsType() {
       return staffService.findAllCatGoodsType();
    }
    // 管理员管理 获取所有猫咪种类
    @GetMapping(value = "/CatType")
    public Result<List<CatType>> findAllCatType() {
        return staffService.findAllCatType();
    }
    // 登录操作
    @PostMapping("/Login")
    public Result<Staffs> staffsLogin(@RequestBody Staffs staffs) {
        return staffService.staffLogin(staffs);
    }
}
