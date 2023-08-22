package org.jun.service;

import com.github.pagehelper.PageInfo;
import entity.Result;
import pojo.goods.*;

/**
 * @Author: 蒲俊
 * @Description: GoodsService
 * @DateTime: 2023/6/1 18:16
 **/
public interface GoodsService {
    public Result<Goods> findById(Long goodsId);

    public Result<Goods> updateById(Long goodsId ,GoodsPostAndPutData goodsPutData);

    public Result<Goods> deleteById(Long goodsId);

    public Result<Goods> insertGoodsData(GoodsPostAndPutData goodsPostData);

    public PageInfo<Goods> findPageGoodsByCondition(Integer page,Integer size,GoodsConditions goodsConditions);

    public Result<Comment> insertCommentData(CommentPostData commentPostData);
}
