package org.jun.service;

import com.github.pagehelper.PageInfo;
import entity.Result;
import pojo.goods.Goods;
import pojo.user.Favorites;
import pojo.user.FavoritesPostData;
import pojo.user.Users;

import java.util.List;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/6/19 11:16
 **/
public interface UserService {
    Result<List<Users>> findAllUser();
    Result<Users> userLogin(Users users);

    Result<Users> findUserById(Long userId);

    Result<Users> updateUserById(Long userId,Users users);

    Result<Users> deleteUserById(Long userId);

    PageInfo<Users> findUsersByConditions(Integer page,Integer size,String sex,String account,String name,String tel);

    Result<Users> addUser(Users users);

    Result<Favorites> addFavoritesByUser(FavoritesPostData favoritesPostData);

    PageInfo<Goods> findGoodsByUserId(Long userId,Integer page,Integer size);

    PageInfo<Favorites> findFavoritesByUserId(Long userId,Integer page,Integer size,String favoritesType);

    Result<Favorites> deleteFavoritesById(Long favoritesId);

    Result<Favorites> deleteFavoritesByCondition(Long targetId,Long userId);
}
