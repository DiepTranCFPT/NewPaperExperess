package com.experess.news.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company extends BaseObject {

    private String name;
    private String moreInfo;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users;
}

