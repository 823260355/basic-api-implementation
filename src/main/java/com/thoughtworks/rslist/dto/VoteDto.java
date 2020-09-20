package com.thoughtworks.rslist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {
    private int  userId;
    private int rsEventId;
    private LocalDateTime time;
    private int voteNum;

}
