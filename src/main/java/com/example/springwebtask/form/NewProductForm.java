package com.example.springwebtask.form;

import com.example.springwebtask.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
public class NewProductForm {
    @NotEmpty(message = "商品IDは必須です")
    private String productId;
    @NotEmpty(message = "商品名は必須です")
    private String productName;

    @NotEmpty(message = "単価は必須です")
    private String price;

    @NotEmpty(message = "カテゴリは必須です")
    private String category;

    private String description;

    private String imgPath;

    public int getCategoryId(){
        return Integer.parseInt(this.category);
    }
    public int getPriceValue(){
        return Integer.parseInt(this.price);
    }
}
