package pojo.user;

// 收藏夹登录数据
public class
FavoritesPostData {
    private Long goodsId = null;
    private Long letterPostId = null;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getLetterPostId() {
        return letterPostId;
    }

    public void setLetterPostId(Long letterPostId) {
        this.letterPostId = letterPostId;
    }

    public String getLikeness() {
        return likeness;
    }

    public void setLikeness(String likeness) {
        this.likeness = likeness;
    }

    private String likeness;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
