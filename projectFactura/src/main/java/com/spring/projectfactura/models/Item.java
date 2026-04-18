package com.spring.projectfactura.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Product product;
    private Integer quantity;

    public int getImport() {
        return quantity*product.getPrice();
    }
}
