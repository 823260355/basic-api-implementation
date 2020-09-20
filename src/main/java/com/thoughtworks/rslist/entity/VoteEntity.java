package com.thoughtworks.rslist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer voteNum;
    private LocalDateTime voteTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "rs_event_id")
    private EventEntity rsEvent;
}