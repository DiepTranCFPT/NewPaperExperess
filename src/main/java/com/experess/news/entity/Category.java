package com.experess.news.entity;

import com.experess.news.infor.CategoryName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseObject{

    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Type> types;

}

