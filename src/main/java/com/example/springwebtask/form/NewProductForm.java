package com.example.springwebtask.form;

import com.example.springwebtask.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class NewProductForm {
    @NotEmpty(message = "商品IDは必須です")
    private String ProductId;
    @NotEmpty(message = "商品名は必須です")
    private String productName;

    @NotEmpty(message = "単価は必須です")
    @Range(min = 0,max = 9999999)
    private String price;
}
