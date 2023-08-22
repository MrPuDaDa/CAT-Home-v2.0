package org.jun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Date;
import entity.Result;
import entity.StatusCode;
import javax.annotation.Resource;
import org.jun.mapper.goods.*;
import org.jun.mapper.sellers.SellerMapper;
import org.jun.mapper.user.UserMapper;
import org.jun.service.GoodsService;
import org.springframework.stereotype.Service;
import pojo.goods.*;
import pojo.seller.Sellers;
import pojo.user.Users;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 蒲俊
 * @Description: GoodsService的实现类
 * @DateTime: 2023/6/1 18:16
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    // 注入Mapper
    /*
    *  @Autowired 与 @Resource注解的区别：
    *  1. @Autowired注解来源于spring框架，@Resource注解来源于JSR-250规范；
    *  2. @Autowired只按照byType 注入；@Resource默认按byName自动注入；
    *  3. @Autowired按类型装配依赖对象，默认情况下它要求依赖对象必须存在，如果允许null值，可以设置它required属性为false。
    *   如果我们想使用按名称装配，可以结合@Qualifier注解一起使用。@Resource有两个中重要的属性：name和type。
    *   name属性指定byName，如果没有指定name属性，当注解标注在字段上，即默认取字段的名称作为bean名称寻找依赖对象。
    *   需要注意的是，@Resource如果没有指定name属性，并且按照默认的名称仍然找不到依赖对象时，
    *   @Resource注解会回退到按类型装配。但一旦指定了name属性，就只能按名称装配了。
    */
    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private CatGoodTypeMapper catGoodTypeMapper;

    @Resource
    private CatTypeMapper catTypeMapper;

    @Resource
    private ProductCatGoodTypeRelationshipMapper productCatGoodTypeRelationshipMapper;

    @Resource
    private ProductCatTypeRelationshipMapper productCatTypeRelationshipMapper;

    @Resource
    private ProductReleaseRelationshipMapper productReleaseRelationshipMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private SellerMapper sellerMapper;

    @Resource
    private GoodsCommentRelationshipMapper goodsCommentRelationshipMapper;

    @Resource
    private CommentMapper commentMapper;

    @Override
    public Result<Goods> findById(Long goodsId) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        // 数据存在check
        if (goods == null) {
            throw new NullPointerException("查询的商品详细数据不存在");
        } else {
            // 自定义条件搜索对象Example ps：在原有代码的基础上去除ForEach循环 想要进一步优化需要修改表结构
            Example examplePR1 = new Example(ProductReleaseRelationship.class);
            Example.Criteria criteriaPR1 = examplePR1.createCriteria(); // 条件构造器
            criteriaPR1.andEqualTo("goodsId", goodsId);
            ProductReleaseRelationship productReleaseRelationship =
                    productReleaseRelationshipMapper.selectOneByExample(examplePR1);
            if (productReleaseRelationship != null) {
                switch (productReleaseRelationship.getType()) {
                    case "seller":
                        goods.setSellers(
                                sellerMapper.selectByPrimaryKey(
                                        productReleaseRelationship.getPublisherId()
                                )
                        );
                        break;
                    case "user":
                        goods.setUsers(
                                userMapper.selectByPrimaryKey(
                                        productReleaseRelationship.getPublisherId()
                                )
                        );
                        break;
                }
            }
            Example examplePCGR = new Example(ProductCatGoodTypeRelationship.class);
            Example.Criteria criteriaPCGR = examplePCGR.createCriteria();
            criteriaPCGR.andEqualTo("goodsId", goodsId);
            ProductCatGoodTypeRelationship productCatGoodTypeRelationship =
                    productCatGoodTypeRelationshipMapper.selectOneByExample(examplePCGR);
            if (productCatGoodTypeRelationship != null) {
                goods.setCatGoodType(
                        catGoodTypeMapper.selectByPrimaryKey(
                                productCatGoodTypeRelationship.getCatGoodTypeId()
                        )
                );
            }else{
                // 因为商品的类型不会同时是猫咪又是猫咪商品
                Example examplePCR = new Example(ProductCatTypeRelationship.class);
                Example.Criteria criteriaPCR = examplePCR.createCriteria();
                criteriaPCR.andEqualTo("goodsId", goodsId);
                ProductCatTypeRelationship productCatTypeRelationship =
                        productCatTypeRelationshipMapper.selectOneByExample(examplePCR);
                if (productCatTypeRelationship != null) {
                    goods.setCatType(
                            catTypeMapper.selectByPrimaryKey(
                                    productCatTypeRelationship.getCatTypeId()
                            )
                    );
                }
            }
            List<Comment> commentList = new ArrayList<>();
            Example exampleGCR = new Example(GoodsCommentRelationship.class);
            Example.Criteria criteriaGCR = exampleGCR.createCriteria();
            criteriaGCR.andEqualTo("goodsId", goodsId);
            List<GoodsCommentRelationship> goodsCommentRelationshipList =
                    goodsCommentRelationshipMapper.selectByExample(exampleGCR);
            if (goodsCommentRelationshipList.size() >= 1){
                for (GoodsCommentRelationship goodsCommentRelationship:goodsCommentRelationshipList
                     ) {
                    commentList.add(commentMapper.selectByPrimaryKey(goodsCommentRelationship.getCommentId()));
                }
            }
            goods.setCommentList(commentList);
            // 更新浏览量
            Goods updatePageViewData = new Goods();
            Date date = new Date();
            updatePageViewData.setId(goodsId);
            updatePageViewData.setUpdateDate(date.GetNowDate());
            Integer oldNum = goods.getPageView();
            updatePageViewData.setPageView(oldNum+1);
            updatePageViewData.setAuditStatus(goods.getAuditStatus());
            updatePageViewData.setIsDelete(goods.getIsDelete());
            goodsMapper.updateByPrimaryKeySelective(updatePageViewData);
            return new Result<Goods>(
                    true,
                    StatusCode.OK,
                    "查询商品详细数据成功！",
                    goods
            );
        }
    }

    @Override
    public Result<Goods> updateById(Long goodsId, GoodsPostAndPutData goodsPutData) {
        Goods checkData = goodsMapper.selectByPrimaryKey(goodsId);
        // 数据存在check
        if (checkData == null) {
            throw new NullPointerException("修改的商品数据不存在");
        } else {
            /*
             *  更新商品不支持更改其根本类型，即不支持更改其是猫咪商品，还是猫咪用品商品
             *  内部更新 获取新的猫咪种类或者猫咪商品类型
             *  重点不要忘记第三方表的更新
             */
            if (goodsPutData.getCatGoodTypeId() != null){
                ProductCatGoodTypeRelationship productCatGoodTypeRelationship = new ProductCatGoodTypeRelationship();
                productCatGoodTypeRelationship.setCatGoodTypeId(goodsPutData.getCatGoodTypeId());
                Example example = new Example(ProductCatGoodTypeRelationship.class);
                Example.Criteria criteria= example.createCriteria();
                criteria.andEqualTo("goodsId",goodsId);
                productCatGoodTypeRelationshipMapper.updateByExampleSelective(productCatGoodTypeRelationship,example);
            } else if (goodsPutData.getCatTypeId() != null) {
                ProductCatTypeRelationship productCatTypeRelationship = new ProductCatTypeRelationship();
                productCatTypeRelationship.setCatTypeId(goodsPutData.getCatTypeId());
                Example example = new Example(ProductCatTypeRelationship.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("goodsId",goodsId);
                productCatTypeRelationshipMapper.updateByExampleSelective(productCatTypeRelationship,example);
            }
            // 主表的更新
            Goods goods = new Goods();
            goods.setId(goodsId);
            if (goodsPutData.getPrice() != null){
                goods.setPrice(goodsPutData.getPrice());
            }
            if (goodsPutData.getTitle() != null){
                goods.setTitle(goodsPutData.getTitle());
            }
            if (goodsPutData.getImage() != null){
                goods.setImage(goodsPutData.getImage());
            }
            if (goodsPutData.getProductDescription() != null){
                goods.setProductDescription(goodsPutData.getProductDescription());
            }
            // 更新时间获取
            Date date = new Date();
            goods.setUpdateDate(date.GetNowDate());
            goodsMapper.updateByPrimaryKeySelective(goods);
            Result<Goods> resultData = findById(goodsId);
            Goods returnData = resultData.getData();
            return new Result<Goods>(
                    true,
                    StatusCode.OK,
                    "修改商品数据成功！",
                    returnData
            );
        }
    }

    @Override
    public Result<Goods> deleteById(Long goodsId) {
        Goods checkData = goodsMapper.selectByPrimaryKey(goodsId);
        // 数据存在check
        if (checkData == null) {
            throw new NullPointerException("删除的商品数据不存在");
        } else {
            // 删除第三方表
            Example examplePR = new Example(ProductReleaseRelationship.class);
            Example.Criteria criteriaPR = examplePR.createCriteria(); // 条件构造器
            criteriaPR.andEqualTo("goodsId", goodsId);
            productReleaseRelationshipMapper.deleteByExample(examplePR);
            Example examplePCGR = new Example(ProductCatGoodTypeRelationship.class);
            Example.Criteria criteriaPCGR = examplePCGR.createCriteria();
            criteriaPCGR.andEqualTo("goodsId", goodsId);
            productCatGoodTypeRelationshipMapper.deleteByExample(examplePCGR);
            Example examplePCR = new Example(ProductCatTypeRelationship.class);
            Example.Criteria criteriaPCR = examplePCR.createCriteria();
            criteriaPCR.andEqualTo("goodsId", goodsId);
            productCatTypeRelationshipMapper.deleteByExample(examplePCR);
            // 删除主表
            goodsMapper.deleteByPrimaryKey(goodsId);
            return new Result<Goods>(
                    true,
                    StatusCode.OK,
                    "删除商品数据成功！",
                    checkData
            );
        }
    }

    @Override
    public Result<Goods> insertGoodsData(GoodsPostAndPutData goodsPostData) {
        // 登录数据实体（实例化）
        Goods goods = new Goods();
        ProductCatTypeRelationship productCatTypeRelationship = new ProductCatTypeRelationship();
        ProductCatGoodTypeRelationship productCatGoodTypeRelationship = new ProductCatGoodTypeRelationship();
        ProductReleaseRelationship productReleaseRelationship = new ProductReleaseRelationship();
        Date date = new Date();
        /*
         * 业务说明
         * 1.商户发布商品无上限
         * 2.用户发布商品限五条
         */
        if (goodsPostData.getUserId() != null){
            Long userId = goodsPostData.getUserId();
            Example examplePRRSP = new Example(ProductReleaseRelationship.class);
            Example.Criteria criteriaPRRSP = examplePRRSP.createCriteria();
            criteriaPRRSP.andEqualTo("publisherId",userId);
            List<ProductReleaseRelationship> productReleaseRelationshipList =
                    productReleaseRelationshipMapper.selectByExample(examplePRRSP);
            if (productReleaseRelationshipList.size() >= 6){
                throw new ArrayIndexOutOfBoundsException("用户发布商品数量大于5，请先撤回商品！！！");
            }
        }
        // 数据存在check AND 登陆数据整理
        if (goodsPostData.getUserId() != null){
            Users checkData = userMapper.selectByPrimaryKey(goodsPostData.getUserId());
            if (checkData == null) {
                throw new NullPointerException("发布商品者数据不存在");
            }
            goods.setStockNumber(1);
        }else if (goodsPostData.getSellerId() != null) {
            Sellers checkData = sellerMapper.selectByPrimaryKey(goodsPostData.getSellerId());
            if (checkData == null) {
                throw new NullPointerException("发布商品者数据不存在");
            }
            goods.setStockNumber(goodsPostData.getStockNumber());
        }
        if (goodsPostData.getCatGoodTypeId() != null){
            CatGoodType checkData = catGoodTypeMapper.selectByPrimaryKey(goodsPostData.getCatGoodTypeId());
            if (checkData == null) {
                throw new NullPointerException("发布商品类型数据不存在");
            }
        }else if (goodsPostData.getCatTypeId() != null) {
            CatType checkData = catTypeMapper.selectByPrimaryKey(goodsPostData.getCatTypeId());
            if (checkData == null) {
                throw new NullPointerException("发布商品类型数据不存在");
            }
        }
        // 主表登录
        goods.setCreateDate(date.GetNowDate());
        goods.setImage(goodsPostData.getImage());
        goods.setTitle(goodsPostData.getTitle());
        if (goodsPostData.getPrice() != null){
            goods.setPrice(goodsPostData.getPrice());
        }
        if (goodsPostData.getProductDescription() != null){
            goods.setProductDescription(goodsPostData.getProductDescription());
        }
        goodsMapper.insertSelective(goods);
        // 第三方表登录
        if (goodsPostData.getUserId() != null){
            productReleaseRelationship.setGoodsId(goods.getId());
            productReleaseRelationship.setPublisherId(goodsPostData.getUserId());
            productReleaseRelationship.setType("user");
            productReleaseRelationshipMapper.insertSelective(productReleaseRelationship);
        } else if (goodsPostData.getSellerId() != null) {
            productReleaseRelationship.setGoodsId(goods.getId());
            productReleaseRelationship.setPublisherId(goodsPostData.getSellerId());
            productReleaseRelationship.setType("seller");
            productReleaseRelationshipMapper.insertSelective(productReleaseRelationship);
        }
        if (goodsPostData.getCatTypeId() != null){
            productCatTypeRelationship.setGoodsId(goods.getId());
            productCatTypeRelationship.setCatTypeId(goodsPostData.getCatTypeId());
            productCatTypeRelationshipMapper.insertSelective(productCatTypeRelationship);
        } else if (goodsPostData.getCatGoodTypeId() != null) {
            productCatGoodTypeRelationship.setGoodsId(goods.getId());
            productCatGoodTypeRelationship.setCatGoodTypeId(goodsPostData.getCatGoodTypeId());
            productCatGoodTypeRelationshipMapper.insertSelective(productCatGoodTypeRelationship);
        }
        Result<Goods> resultData = findById(goods.getId());
        Goods returnData = resultData.getData();
        return new Result<Goods>(
                true,
                StatusCode.OK,
                "添加商品数据成功！",
                returnData
        );
    }

    @Override
    public PageInfo<Goods> findPageGoodsByCondition(Integer page, Integer size, GoodsConditions goodsConditions) {
        PageHelper.startPage(page,size);
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        if (goodsConditions.getCatGoodsTypeId() != null || goodsConditions.getCatTypeId() != null) {
            ArrayList goodsIds = new ArrayList();
            if (goodsConditions.getCatGoodsTypeId() != null && goodsConditions.getCatTypeId() == null){
                Example examplePCGR1 = new Example(ProductCatGoodTypeRelationship.class);
                Example.Criteria criteriaPCGR1 = examplePCGR1.createCriteria();
                criteriaPCGR1.andEqualTo("catGoodTypeId",goodsConditions.getCatGoodsTypeId());
                List<ProductCatGoodTypeRelationship> productCatGoodTypeRelationshipList
                        = productCatGoodTypeRelationshipMapper.selectByExample(examplePCGR1);
                for (ProductCatGoodTypeRelationship productCatGoodTypeRelationship:productCatGoodTypeRelationshipList
                ) {
                    goodsIds.add(productCatGoodTypeRelationship.getGoodsId());
                }
            }else if (goodsConditions.getCatGoodsTypeId() == null && goodsConditions.getCatTypeId() != null) {
                Example examplePCR1 = new Example(ProductCatTypeRelationship.class);
                Example.Criteria criteriaPCR1 = examplePCR1.createCriteria();
                criteriaPCR1.andEqualTo("catTypeId",goodsConditions.getCatTypeId());
                List<ProductCatTypeRelationship> productCatTypeRelationshipList
                        = productCatTypeRelationshipMapper.selectByExample(examplePCR1);
                for (ProductCatTypeRelationship productCatTypeRelationship:productCatTypeRelationshipList
                ) {
                    goodsIds.add(productCatTypeRelationship.getGoodsId());
                }
            }
            if (goodsIds.size() == 0){
                throw new NullPointerException("商品数据不存在");
            }
            criteria.andIn("id",goodsIds);
        }
        if (goodsConditions.getFormType().equals("user")){
            criteria.andEqualTo("auditStatus","1");
        }
        if (goodsConditions.getTitle() != null){
            criteria.andLike("title","%"+goodsConditions.getTitle()+"%");
        }
        if (goodsConditions.getMinPrice()!= null && goodsConditions.getMaxPrice() != null){
            criteria.andBetween("price",goodsConditions.getMinPrice(),goodsConditions.getMaxPrice());
        }
        List<Goods> goodsList = goodsMapper.selectByExample(example);
        // 商品部分标签
        for (Goods goods:goodsList) {
            Long goodsId =  goods.getId();
            Example examplePR2 = new Example(ProductReleaseRelationship.class);
            Example.Criteria criteriaPR2 = examplePR2.createCriteria(); // 条件构造器
            criteriaPR2.andEqualTo("goodsId", goodsId);
            ProductReleaseRelationship productReleaseRelationship =
                    productReleaseRelationshipMapper.selectOneByExample(examplePR2);
            if (productReleaseRelationship != null) {
                switch (productReleaseRelationship.getType()) {
                    case "seller":
                        goods.setSellers(
                                sellerMapper.selectByPrimaryKey(
                                        productReleaseRelationship.getPublisherId()
                                )
                        );
                        break;
                    case "user":
                        goods.setUsers(
                                userMapper.selectByPrimaryKey(
                                        productReleaseRelationship.getPublisherId()
                                )
                        );
                        break;
                }
            }
            Example examplePCGR = new Example(ProductCatGoodTypeRelationship.class);
            Example.Criteria criteriaPCGR = examplePCGR.createCriteria();
            criteriaPCGR.andEqualTo("goodsId", goodsId);
            ProductCatGoodTypeRelationship productCatGoodTypeRelationship =
                    productCatGoodTypeRelationshipMapper.selectOneByExample(examplePCGR);
            if (productCatGoodTypeRelationship != null) {
                goods.setCatGoodType(
                        catGoodTypeMapper.selectByPrimaryKey(
                                productCatGoodTypeRelationship.getCatGoodTypeId()
                        )
                );
            }else{
                // 因为商品的类型不会同时是猫咪又是猫咪商品
                Example examplePCR = new Example(ProductCatTypeRelationship.class);
                Example.Criteria criteriaPCR = examplePCR.createCriteria();
                criteriaPCR.andEqualTo("goodsId", goodsId);
                ProductCatTypeRelationship productCatTypeRelationship =
                        productCatTypeRelationshipMapper.selectOneByExample(examplePCR);
                if (productCatTypeRelationship != null) {
                    goods.setCatType(
                            catTypeMapper.selectByPrimaryKey(
                                    productCatTypeRelationship.getCatTypeId()
                            )
                    );
                }
            }
        }
        return new PageInfo<Goods>(goodsList);
    }

    @Override
    public Result<Comment> insertCommentData(CommentPostData commentPostData) {
        // 数据存在check
        if (commentPostData.getUserId() != null){
            Users checkData = userMapper.selectByPrimaryKey(commentPostData.getUserId());
            if (checkData == null) {
                throw new NullPointerException("发布评论者数据不存在");
            }
        }
        if (commentPostData.getGoodsId() != null) {
            Goods checkData = goodsMapper.selectByPrimaryKey(commentPostData.getGoodsId());
            if (checkData == null) {
                throw new NullPointerException("发布评论商品数据不存在");
            }
        }
        /*
        * 业务说明
        * 1.商户商品评论无上限
        * 2.用户商品评论限一条
        */
        Long goodsId = commentPostData.getGoodsId();
        Result<Goods> goodsResult = findById(goodsId);
        Goods goods = goodsResult.getData();
        if (goods.getUsers() != null){
            if(goods.getCommentList() != null){
                throw new NullPointerException("用户商品只允许评论一条！！！");
            }
        }
        // 对象实体实例化
        Comment comment = new Comment();
        GoodsCommentRelationship goodsCommentRelationship = new GoodsCommentRelationship();
        Date date = new Date();
        // 主表登录
        comment.setUserId(commentPostData.getUserId());
        comment.setFavorableRate(commentPostData.getFavorableRate());
        comment.setContent(commentPostData.getContent());
        comment.setCreateDate(date.GetNowDate());
        commentMapper.insertSelective(comment);
        // 第三方表登录
       goodsCommentRelationship.setCommentId(comment.getId());
       goodsCommentRelationship.setGoodsId(commentPostData.getGoodsId());
       goodsCommentRelationshipMapper.insertSelective(goodsCommentRelationship);
        return new Result<Comment>(
                true,
                StatusCode.OK,
                "添加商品评论数据成功！",
                comment
        );
    }
}
