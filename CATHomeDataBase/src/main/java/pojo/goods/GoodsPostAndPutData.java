package pojo.goods;

public class GoodsPostAndPutData {
    private Double price;
    private String title;
    private Long catTypeId;
    private Long catGoodTypeId;
    private String reasonsForRejection = null;
    private Long userId = null;
    private Long sellerId = null;
    //    private Long staffId = null;
    private String productDescription = null;
    private String image;

    private Integer stockNumber;

    public Integer getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Long getCatTypeId() {
        return catTypeId;
    }

    public void setCatTypeId(Long catTypeId) {
        this.catTypeId = catTypeId;
    }


    public Long getCatGoodTypeId() {
        return catGoodTypeId;
    }

    public void setCatGoodTypeId(Long catGoodTypeId) {
        this.catGoodTypeId = catGoodTypeId;
    }

    public String getReasonsForRejection() {
        return reasonsForRejection;
    }

    public void setReasonsForRejection(String reasonsForRejection) {
        this.reasonsForRejection = reasonsForRejection;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

//    public Long getStaffId() {
//        return staffId;
//    }
//
//    public void setStaffId(Long staffId) {
//        this.staffId = staffId;
//    }
}
