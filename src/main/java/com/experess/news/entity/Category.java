package com.experess.news.entity;

import com.experess.news.infor.CategoryName;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Category extends BaseObject{

    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Type> types;

}

