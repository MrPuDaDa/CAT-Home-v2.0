package pojo.goods;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/4/7 11:18
 **/
public class CommentPostData {
    private String content;
    private String favorableRate;
    private Long userId;
    private Long goodsId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFavorableRate() {
        return favorableRate;
    }

    public void setFavorableRate(String favorableRate) {
        this.favorableRate = favorableRate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
