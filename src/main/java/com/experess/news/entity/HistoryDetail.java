package com.experess.news.entity;

import com.experess.news.infor.TypeHistory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class HistoryDetail extends BaseObject {
    @Enumerated(EnumType.STRING)
    private TypeHistory typeHistory;
    private String idContent;
    private String title;
    private String description;

    @ManyToOne
    private History history;
}
