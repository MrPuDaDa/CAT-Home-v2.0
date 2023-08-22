package pojo.goods;

// 商品一览搜索用
public class GoodsConditions {
    private String title;
    private Double minPrice;
    private Double maxPrice;

    private Long catTypeId;

    private Long catGoodsTypeId;

    private String formType;

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public Long getCatTypeId() {
        return catTypeId;
    }

    public void setCatTypeId(Long catTypeId) {
        this.catTypeId = catTypeId;
    }

    public Long getCatGoodsTypeId() {
        return catGoodsTypeId;
    }

    public void setCatGoodsTypeId(Long catGoodsTypeId) {
        this.catGoodsTypeId = catGoodsTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
