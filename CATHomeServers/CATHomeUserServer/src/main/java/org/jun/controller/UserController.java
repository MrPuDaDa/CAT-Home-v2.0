package org.jun.controller;

import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.jun.service.UserService;
import org.springframework.web.bind.annotation.*;
import pojo.enum_data.SEX;
import pojo.goods.Goods;
import pojo.user.Favorites;
import pojo.user.FavoritesPostData;
import pojo.user.Users;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @Author: 蒲俊
 * @Description: 用户微服务控制调度层
 * @DateTime: 2023/6/19 11:13
 **/
@RestController
@RequestMapping(value = "User")
@CrossOrigin // 跨域 限制转载使用
public class UserController {
    @Resource
    private UserService userService;
    // 用户登录
    @PostMapping("/Login")
    public Result<Users> userLogin(@RequestBody Users users){
        return userService.userLogin(users);
    }

    // 获取用户的信息(By userId)
    @GetMapping("/{userId}")
    public Result<Users> findUserById(@PathVariable(value = "userId") Long userId){
        return userService.findUserById(userId);
    }

    // 删除用户的信息(By userId)
    @DeleteMapping ("/{userId}")
    public Result<Users> deleteUserById(@PathVariable(value = "userId") Long userId){
        return userService.deleteUserById(userId);
    }

    // 更新用户的信息(By userId)
    @PutMapping("/{userId}")
    public Result<Users> updateUserById(@PathVariable(value = "userId") Long userId,
                                        @RequestBody Users users){
        return userService.updateUserById(userId,users);
    }

    // 获取满足条件的用户By_Get_Method
    @GetMapping("/Conditions/{page}/{size}")
    public PageInfo<Users> findUsersByConditions(
            // 路径参数
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            // 查询参数
            String sex,
            String account,
            String name,
            String tel
    ){
        return userService.findUsersByConditions(page, size, sex, account, name, tel);
    }

    @PostMapping()
    public Result<Users> addUser(@RequestBody Users users){
        return userService.addUser(users);
    }

    @PostMapping(value = "/Favorites")
    public Result<Favorites> addFavorites(@RequestBody FavoritesPostData favoritesPostData){
        return userService.addFavoritesByUser(favoritesPostData);
    }

    // 获取用户发布的商品
    @GetMapping(value = "OwnGoods/{userId}/{page}/{size}")
    public PageInfo<Goods> findUserOwnGoods(
            @PathVariable("userId") Long userId,
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size
            ) {
        ;
        return userService.findGoodsByUserId(userId,page,size);
    }

    @DeleteMapping(value = "Favorites/{favoritesId}")
    public Result<Favorites> deleteFavoritesById(@PathVariable("favoritesId") Long favoritesId){
        return userService.deleteFavoritesById(favoritesId);
    }

    @DeleteMapping(value = "Favorites")
    public Result<Favorites> deleteFavoritesByCondition(Long objectId,Long userId){
        return userService.deleteFavoritesByCondition(objectId,userId);
    }

    @GetMapping(value = "Favorites/{userId}/{page}/{size}")
    public PageInfo<Favorites> findFavoritesByUserId(
            @PathVariable("userId") Long userId,
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size,
            String favoritesType
    ){
        return userService.findFavoritesByUserId(userId,page,size,favoritesType);
    }

    @GetMapping()
    public Result<List<Users>> findAllUser(){
        return userService.findAllUser();
    }

}
