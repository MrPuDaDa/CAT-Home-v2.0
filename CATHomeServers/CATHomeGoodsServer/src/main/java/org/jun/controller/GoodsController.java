package org.jun.controller;

import com.github.pagehelper.PageInfo;
import entity.Result;
import org.jun.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.goods.*;

/**
 * @Author: 蒲俊
 * @Description: 商品微服务Controller
 * @DateTime: 2023/6/1 16:42
 **/
@RestController
@RequestMapping(value = "Goods")
@CrossOrigin // 跨域 限制转载使用
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    // 获取商品信息(By GoodsId)
    @GetMapping("/{goodsId}")
    public Result<Goods> findById(@PathVariable("goodsId") Long goodsId) {
       return  goodsService.findById(goodsId);
    }

    // 更新商品信息(By GoodsId)
    @PutMapping("/{goodsId}")
    public Result<Goods> updateById(@PathVariable("goodsId") Long goodsId, @RequestBody GoodsPostAndPutData goodsPutData){
        return goodsService.updateById(goodsId,goodsPutData);
    }

    // 删除商品信息(By GoodsId)
    @DeleteMapping("/{goodsId}")
    public Result<Goods> deleteById(@PathVariable("goodsId") Long goodsId){
        return  goodsService.deleteById(goodsId);
    }

    // 上传商品
    @PostMapping()
    public Result<Goods> insertGoodsData(@RequestBody GoodsPostAndPutData goodsPostData){
        return goodsService.insertGoodsData(goodsPostData);
    }

    // 获取满足条件的商品
    @PostMapping("ConditionalQuery/{page}/{size}")
    public PageInfo<Goods> findPageGoodsByCondition(@PathVariable("page") Integer page,
                                                    @PathVariable("size") Integer size,
                                                    @RequestBody GoodsConditions goodsConditions
                                                    ){
        return goodsService.findPageGoodsByCondition(page,size,goodsConditions);
    }

    // 发布商品评价
    @PostMapping("/Comment")
    public Result<Comment> insertCommentData(@RequestBody CommentPostData commentPostData){
        return goodsService.insertCommentData(commentPostData);
    }
}
