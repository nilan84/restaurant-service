package lk.uoc.mit.restaurant.mysql.model;


import lk.uoc.mit.restaurant.mysql.config.FoodType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by nilan on 8/21/15.
 */
public class Food implements Serializable {

    private static final long serialVersionUID = -7788619177798333712L;

    private FoodL2 foodL2;

    private FoodType foodtype;

    @Size(min = 3, max = 255, message="Food Name must be at least 3 characters")
    private String foodName;

    private double foodPrice;
    @Size(min = 2, max = 500, message="Food Description must be between 2 and 500 characters")
    private String foodDiscription;
    @Size(min = 2, max = 255, message="Food Scan Code must be between 2 and 255 characters")
    private String foodscanCode;
    private int foodNo;

    private byte[] itemImage;
    MultipartFile file;

    public FoodType getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(FoodType foodtype) {
        this.foodtype = foodtype;
    }

    public byte[] getItemImage() {
        return itemImage;
    }

    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public int getFoodNo() {
        return foodNo;
    }

    public void setFoodNo(int foodNo) {
        this.foodNo = foodNo;
    }

    public String getFoodscanCode() {
        return foodscanCode;
    }

    public void setFoodscanCode(String foodscanCode) {
        this.foodscanCode = foodscanCode;
    }

    public String getFoodDiscription() {
        return foodDiscription;
    }

    public void setFoodDiscription(String foodDiscription) {
        this.foodDiscription = foodDiscription;
    }

    public FoodL2 getFoodL2() {
        return foodL2;
    }

    public void setFoodL2(FoodL2 foodL2) {
        this.foodL2 = foodL2;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }



}
