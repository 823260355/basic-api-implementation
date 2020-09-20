package com.thoughtworks.rslist.entity;

import com.thoughtworks.rslist.dto.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "re_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String eventName;
    private String keyWord;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToMany(mappedBy = "rsEvent",cascade = CascadeType.REMOVE)
    private List<VoteEntity> voteEntities;


}
