package com.thoughtworks.rslist.entity;

import com.thoughtworks.rslist.dto.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "re_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsEventEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name")
    private String eventName;
    private String keyword;
    private int  userId;
}
