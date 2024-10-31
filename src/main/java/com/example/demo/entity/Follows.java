package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Follows extends BaseObject {

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Account follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private Account following;

}
