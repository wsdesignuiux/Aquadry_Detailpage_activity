package model;
/**
 * Created by one on 22/1/18.
 */
public class Products {

    private String id;
    private String name;
    private String description;
    private Integer image;
    private String price;

    private int quantity = 0;
    private String priceAsPerQuantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPriceAsPerQuantity() {
        return priceAsPerQuantity;
    }

    public void setPriceAsPerQuantity(String priceAsPerQuantity) {
        this.priceAsPerQuantity = priceAsPerQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}