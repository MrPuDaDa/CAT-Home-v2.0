package org.jun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.BCrypt;
import entity.Date;
import entity.Result;
import entity.StatusCode;
import org.apache.ibatis.session.RowBounds;
import org.jun.mapper.goods.CommentMapper;
import org.jun.mapper.goods.GoodsCommentRelationshipMapper;
import org.jun.mapper.goods.GoodsMapper;
import org.jun.mapper.goods.ProductReleaseRelationshipMapper;
import org.jun.mapper.letterpost.LetterPostMapper;
import org.jun.mapper.letterpost.LetterPostMessageRelationshipMapper;
import org.jun.mapper.letterpost.LetterPostReleaseRelationshipMapper;
import org.jun.mapper.letterpost.MessageMapper;
import org.jun.mapper.order.OrderGoodsRelationshipMapper;
import org.jun.mapper.user.FavoritesMapper;
import org.jun.mapper.user.ShoppingCartMapper;
import org.jun.mapper.user.UserMapper;
import org.jun.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.enum_data.SEX;
import pojo.goods.Comment;
import pojo.goods.Goods;
import pojo.goods.GoodsCommentRelationship;
import pojo.goods.ProductReleaseRelationship;
import pojo.letterpost.LetterPost;
import pojo.letterpost.LetterPostMessageRelationship;
import pojo.letterpost.LetterPostReleaseRelationship;
import pojo.letterpost.Message;
import pojo.order.OrderGoodsRelationship;
import pojo.user.Favorites;
import pojo.user.FavoritesPostData;
import pojo.user.ShoppingCart;
import pojo.user.Users;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/6/19 11:17
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private FavoritesMapper favoritesMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private GoodsCommentRelationshipMapper goodsCommentRelationshipMapper;
    @Resource
    private LetterPostReleaseRelationshipMapper letterPostReleaseRelationshipMapper;
    @Resource
    private LetterPostMessageRelationshipMapper letterPostMessageRelationshipMapper;
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private LetterPostMapper letterPostMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private ProductReleaseRelationshipMapper productReleaseRelationshipMapper;
    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    @Resource
    private OrderGoodsRelationshipMapper orderGoodsRelationshipMapper;

    @Override
    public Result<List<Users>> findAllUser() {
        int start =1;
        // 自定义条件搜索对象Example
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria(); // 条件构造器
        RowBounds rowBounds = new RowBounds(start, 20);
        return new Result(true, StatusCode.OK,
                "登陆成功",userMapper.selectByExampleAndRowBounds(example,rowBounds));
    }

    @Override
    public Result<Users> userLogin(Users users) {
        // 自定义条件搜索对象Example
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria(); // 条件构造器
        criteria.andEqualTo("account", users.getAccount());
        List<Users> sameNameUsers = userMapper.selectByExample(example);
        Users returnDBUser = UserCheck(sameNameUsers, users);
        if (returnDBUser != null) {
            return new Result(true, StatusCode.OK,
                    "登陆成功", returnDBUser);
        }
        return new Result(false, StatusCode.ERROR,
                "登陆失败,请检查账号与密码");
    }

    @Override
    public Result<Users> findUserById(Long userId) {
        /*
        *  完成代码，前端视情况调用。登录检查接口包含此接口的内容
        */
        Users users = userMapper.selectByPrimaryKey(userId);
        if (users != null){
            return new Result<Users>(true, StatusCode.OK,
                    "查询详细用户信息成功",users);
        }else {
            return new Result(false, StatusCode.ERROR,
                    "用户不存在！");
        }
    }

    @Override
    public Result<Users> updateUserById(Long userId,Users users) {
        if(findUserById(userId).isFlag() == false){
            return new Result(false, StatusCode.ERROR,
                    "用户不存在！");
        }
        // 对象实例化
        Date date = new Date();
        // 更新数据整理
        users.setId(userId);
        users.setUpdateDate(date.GetNowDate());
        // 密码加密
        if (users.getPassword() != null){
            String password = users.getPassword();
            BCrypt bCrypt = new BCrypt();
            String salt = bCrypt.gensalt(10, new SecureRandom());
            users.setPassword(bCrypt.hashpw(password, salt));
        }
        // 执行更新
        userMapper.updateByPrimaryKeySelective(users);
        Users user = findUserById(userId).getData();
        return new Result<Users>(true, StatusCode.OK,
                "修改用户信息成功",user);
    }

    @Override
    @Transactional
    public Result<Users> deleteUserById(Long userId) {
        if(findUserById(userId).isFlag() == false){
            return new Result(false, StatusCode.ERROR,
                    "用户不存在！");
        }
        Users DeleteUserData = findUserById(userId).getData();
        // 自定义条件搜索对象Example
        Example example = new Example(Favorites.class);
        Example.Criteria criteria = example.createCriteria(); // 条件构造器
        criteria.andEqualTo("userId", userId);
        // 删除对应的收藏品
        favoritesMapper.deleteByExample(example);
        // 自定义条件搜索对象Example
        Example exampleComment = new Example(Comment.class);
        Example.Criteria criteriaComment = exampleComment.createCriteria(); // 条件构造器
        criteriaComment.andEqualTo("userId", userId);
        List<Comment> commentList = commentMapper.selectByExample(exampleComment);
        for (Comment comment : commentList
        ) {
            // 自定义条件搜索对象Example
            Example exampleGoodsCommentRelationshipOne = new Example(GoodsCommentRelationship.class);
            Example.Criteria criteriaGoodsCommentRelationshipOne = exampleGoodsCommentRelationshipOne.createCriteria(); // 条件构造器
            criteriaGoodsCommentRelationshipOne.andEqualTo("commentId", comment.getId());
            goodsCommentRelationshipMapper.deleteByExample(exampleGoodsCommentRelationshipOne);
        }
        commentMapper.deleteByExample(exampleComment);
        // 自定义条件搜索对象Example
        Example exampleLetterPost = new Example(LetterPostReleaseRelationship.class);
        Example.Criteria criteriaLetterPost = exampleLetterPost.createCriteria(); // 条件构造器
        criteriaLetterPost.andEqualTo("publisherId", userId);
        List<LetterPostReleaseRelationship> letterPostReleaseRelationshipList = letterPostReleaseRelationshipMapper.selectByExample(exampleLetterPost);
        letterPostReleaseRelationshipMapper.deleteByExample(exampleLetterPost);
        ArrayList arrayLetterPostList = new ArrayList();
        for (LetterPostReleaseRelationship letterPostReleaseRelationship : letterPostReleaseRelationshipList
        ) {
            arrayLetterPostList.add(letterPostReleaseRelationship.getLetterPostId());
            // 自定义条件搜索对象Example
            Example exampleLetterPostMessageRelationship = new Example(LetterPostMessageRelationship.class);
            Example.Criteria criteriaLetterPostMessageRelationship = exampleLetterPostMessageRelationship.createCriteria(); // 条件构造器
            criteriaLetterPostMessageRelationship.andEqualTo("objectId", letterPostReleaseRelationship.getLetterPostId());
            List<LetterPostMessageRelationship> letterPostMessageRelationshipList = letterPostMessageRelationshipMapper.selectByExample(exampleLetterPostMessageRelationship);
            letterPostMessageRelationshipMapper.deleteByExample(exampleLetterPostMessageRelationship);
            ArrayList arrayLetterPostMessageList = new ArrayList<>();
            for (LetterPostMessageRelationship letterPostMessageRelationship : letterPostMessageRelationshipList
            ) {
                arrayLetterPostMessageList.add(letterPostMessageRelationship.getMessageId());
            }
            // 自定义条件搜索对象Example
            Example exampleMessage = new Example(Message.class);
            Example.Criteria criteriaMessage = exampleMessage.createCriteria(); // 条件构造器
            criteriaMessage.andIn("id", arrayLetterPostMessageList);
            messageMapper.deleteByExample(exampleMessage);
        }
        if (arrayLetterPostList.size() != 0) {
            // 自定义条件搜索对象Example
            Example exampleLetterPostMaster = new Example(LetterPost.class);
            Example.Criteria criteriaLetterPostMaster = exampleLetterPostMaster.createCriteria(); // 条件构造器
            criteriaLetterPostMaster.andIn("id", arrayLetterPostList);
            letterPostMapper.deleteByExample(exampleLetterPostMaster);
        }
        // 自定义条件搜索对象Example
        Example exampleProductReleaseRelationship = new Example(ProductReleaseRelationship.class);
        Example.Criteria criteriaProductReleaseRelationship = exampleProductReleaseRelationship.createCriteria(); // 条件构造器
        criteriaProductReleaseRelationship.andEqualTo("publisherId", userId);
        List<ProductReleaseRelationship> productReleaseRelationshipList = productReleaseRelationshipMapper.selectByExample(exampleProductReleaseRelationship);
        productReleaseRelationshipMapper.deleteByExample(exampleProductReleaseRelationship);
        ArrayList arrayProductReleaseRelationship = new ArrayList<>();
        for (ProductReleaseRelationship productReleaseRelationship : productReleaseRelationshipList
        ) {
            arrayProductReleaseRelationship.add(productReleaseRelationship.getGoodsId());
            // 自定义条件搜索对象Example
            Example exampleGoodsCommentRelationship = new Example(GoodsCommentRelationship.class);
            Example.Criteria criteriaGoodsCommentRelationship = exampleGoodsCommentRelationship.createCriteria(); // 条件构造器
            criteriaGoodsCommentRelationship.andEqualTo("goodsId", productReleaseRelationship.getGoodsId());
            goodsCommentRelationshipMapper.deleteByExample(exampleGoodsCommentRelationship);
            // 自定义条件搜索对象Example
            Example exampleShoppingCart = new Example(ShoppingCart.class);
            Example.Criteria criteriaShoppingCart = exampleShoppingCart.createCriteria(); // 条件构造器
            criteriaShoppingCart.andEqualTo("goodsId", productReleaseRelationship.getGoodsId());
            shoppingCartMapper.deleteByExample(exampleShoppingCart);
        }
        if (arrayProductReleaseRelationship.size() != 0) {
            // 自定义条件搜索对象Example
            Example exampleGoods = new Example(Goods.class);
            Example.Criteria criteriaGoods = exampleGoods.createCriteria(); // 条件构造器
            criteriaGoods.andEqualTo("id", arrayProductReleaseRelationship);
            goodsMapper.deleteByExample(exampleGoods);
        }
        // 自定义条件搜索对象Example
        Example exampleOrderGoodsRelationship = new Example(OrderGoodsRelationship.class);
        Example.Criteria criteriaOrderGoodsRelationship = exampleOrderGoodsRelationship.createCriteria(); // 条件构造器
        criteriaOrderGoodsRelationship.andEqualTo("buyerId", userId);
        orderGoodsRelationshipMapper.deleteByExample(exampleOrderGoodsRelationship);
        // 自定义条件搜索对象Example
        Example exampleShoppingCart = new Example(ShoppingCart.class);
        Example.Criteria criteriaShoppingCart = exampleShoppingCart.createCriteria(); // 条件构造器
        criteriaShoppingCart.andEqualTo("userId", userId);
        shoppingCartMapper.deleteByExample(exampleShoppingCart);
        // 删除用户
        userMapper.deleteByPrimaryKey(userId);
        return new Result<Users>(true, StatusCode.OK,
                "删除用户信息成功",DeleteUserData);
   }

    @Override
    public PageInfo<Users> findUsersByConditions(Integer page, Integer size, String sex, String account, String name, String tel) {
        PageHelper.startPage(page,size);
        Example userConditionExample = new Example(Users.class);
        Example.Criteria userConditioncriteria = userConditionExample.createCriteria();
        if (sex != null){
            userConditioncriteria.andEqualTo("sex",sex);
        }
        if (account != null){
            userConditioncriteria.andLike("account","%"+account+"%");
        }
        if (name != null){
            userConditioncriteria.andLike("name","%"+name+"%");
        }
        if (tel != null){
            userConditioncriteria.andEqualTo("tel",tel);
        }
        List<Users> usersList = userMapper.selectByExample(userConditionExample);
        return new PageInfo<Users>(usersList);
    }

    @Override
    public Result<Users> addUser(Users users) {
        Date date = new Date();
        users.setCreateDate(date.GetNowDate());
        // 密码加密
        String password = users.getPassword();
        BCrypt bCrypt = new BCrypt();
        String salt = bCrypt.gensalt(10, new SecureRandom());
        users.setPassword(bCrypt.hashpw(password, salt));
        userMapper.insertSelective(users);
        return new Result<Users>(true, StatusCode.OK,
                "新增用户成功",users);
    }

    @Override
    public Result<Favorites> addFavoritesByUser(FavoritesPostData favoritesPostData) {
        // 实例化对象
        Favorites favorites = new Favorites();
        Date date = new Date();
        // 创建时间（当前时间）
        favorites.setCreateDate((date.GetNowDate()));
        // 判断收藏对象的类型
        if (favoritesPostData.getGoodsId() != null) {
            if (goodsMapper.selectByPrimaryKey(favoritesPostData.getGoodsId()) == null) {
                return new Result<>(false, StatusCode.ERROR,
                        "收藏商品不存在！");
            }
            favorites.setObjectId(favoritesPostData.getGoodsId());
            // 设置收藏物品种类为商品
            favorites.setType("goods");
        } else if (favoritesPostData.getLetterPostId() != null) {
            if (letterPostMapper.selectByPrimaryKey(favoritesPostData.getLetterPostId()) == null) {
                return new Result<>(false, StatusCode.ERROR,
                        "收藏帖子不存在！");
            }
            favorites.setObjectId(favoritesPostData.getLetterPostId());
            // 设置收藏物品种类为帖子
            favorites.setType("letterPost");
        }
        // 喜爱程度
        favorites.setLikeness(favoritesPostData.getLikeness());
        // 用户ID
        favorites.setUserId(favoritesPostData.getUserId());
        favoritesMapper.insertSelective(favorites);
        return new Result<Favorites>(true, StatusCode.OK,
                "插入收藏夹数据成功！", favorites);
    }

    @Override
    public PageInfo<Goods> findGoodsByUserId(Long userId, Integer page, Integer size) {
        if(findUserById(userId).isFlag() == false){
            // 数据存在check
            throw new NullPointerException("查询的用户不存在");
        }
        // 实例化条件构造器
        Example Prexample = new Example(ProductReleaseRelationship.class);
        Example.Criteria Prcriteria = Prexample.createCriteria();
        Prcriteria.andEqualTo("type","user");
        // 获取全部用户关系
        List<ProductReleaseRelationship> productReleaseRelationships =
                productReleaseRelationshipMapper.selectByExample(Prexample);
        ArrayList arrayList = new ArrayList<>();
        // 获取当前用户的商品
        for (ProductReleaseRelationship productReleaseRelationship : productReleaseRelationships
        ) {
            if (productReleaseRelationship.getPublisherId().equals(userId)) {
                arrayList.add(productReleaseRelationship.getGoodsId());
            }
        }
        PageHelper.startPage(page,size);

        // 实例化条件构造器
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        Integer arrayListCount = Math.toIntExact(arrayList.stream().count());
        // 设置查询条件
        if (!arrayListCount.equals(0)) {
            // 用户有发布商品的场合
            criteria.andIn("id", arrayList);
        } else {
            // 用户没有发布商品的场合 返回空数组
            criteria.andIsNull("id");
        }
        List<Goods> userOwnGoods = goodsMapper.selectByExample(example);
        return new PageInfo<Goods>(userOwnGoods);
    }

    @Override
    public PageInfo<Favorites> findFavoritesByUserId(Long userId, Integer page, Integer size, String favoritesType) {
        // 分页操作
        PageHelper.startPage(page, size);
        // 自定义条件搜索对象Example
        // 条件构造器
        Example example = new Example(Favorites.class);
        Example.Criteria criteria = example.createCriteria();
        // 获取UserId是当前用户ID
        criteria.andEqualTo("publisherId", userId);
        if (!favoritesType.isEmpty()){
            criteria.andEqualTo("type",favoritesType);
        }
        // 条件查询
        List<Favorites> favoritesList = favoritesMapper.selectByExample(example);
        return new PageInfo<Favorites>(favoritesList);
    }

    @Override
    public Result<Favorites> deleteFavoritesById(Long favoritesId) {
        /*
        * 本方法基于收藏品ID删除仅用于“我的收藏”页面执行删除时使用
        */
        Favorites favoritesCheckData = favoritesMapper.selectByPrimaryKey(favoritesId);
        if (favoritesCheckData == null){
            return new Result<>(false, StatusCode.ERROR,
                    "删除的收藏信息不存在！请刷新后，再重试");
        }
        favoritesMapper.deleteByPrimaryKey(favoritesId);
        return new Result<>(true, StatusCode.OK,
                "删除收藏信息成功",favoritesCheckData);
    }

    @Override
    public Result<Favorites> deleteFavoritesByCondition(Long targetId,Long userId) {
        /*
         * 本方法基于收藏品删除条件删除仅用于“商品/帖子”页面执行取消收藏按钮时使用
         */
        // 实例化条件对象
        Example example = new Example(Favorites.class);
        Example.Criteria criteria = example.createCriteria();
        // 删除条件确认
        criteria.andEqualTo("objectId",targetId);
        criteria.andEqualTo("userId",userId);
        // 业务原因，用户对一个收藏物品对应关系为：1对1
        Favorites favoritesCheckData = favoritesMapper.selectOneByExample(example);
        if (favoritesCheckData == null){
            return new Result<>(false, StatusCode.ERROR,
                    "删除的收藏信息不存在！请刷新后，再重试");
        }
        favoritesMapper.deleteByPrimaryKey(favoritesCheckData.getId());
        return new Result<>(true, StatusCode.OK,
                "删除收藏信息成功",favoritesCheckData);
    }

    private Users UserCheck(List<Users> UserList, Users users) {
        for (Users user : UserList
        ) {
            // 密码验证方法 user.getPassword()加密密码、(users.getPassword()原密码 返回值 bool
            if (BCrypt.checkpw(users.getPassword(), user.getPassword())) {
                // 设置更新时间（最后登陆时间）
                Date Date = new Date();
                Users UserLastLoginTime = new Users();
                UserLastLoginTime.setLastLoginData(Date.GetNowDate());
                UserLastLoginTime.setId(user.getId());
                userMapper.updateByPrimaryKeySelective(UserLastLoginTime);
                return user;
            }
        }
        return null;
    }
}
