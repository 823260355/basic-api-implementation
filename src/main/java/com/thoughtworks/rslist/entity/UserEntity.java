package com.thoughtworks.rslist.entity;

import com.thoughtworks.rslist.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String gender;
    private Integer age;
    private String email;
    private String phone;
    private Integer vote;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<EventEntity> rsEvents;
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<VoteEntity> voteEntities;


}
