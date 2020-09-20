package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.Event;
import com.thoughtworks.rslist.dto.UserDto;
import com.thoughtworks.rslist.dto.VoteDto;
import com.thoughtworks.rslist.entity.UserEntity;
import com.thoughtworks.rslist.entity.*;
import com.thoughtworks.rslist.exceptions.CommonException;
import com.thoughtworks.rslist.repository.*;
import com.thoughtworks.rslist.repository.VoteRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VoteService {

    @Resource
    VoteRepository voteRepository;

    @Resource
    UserService userService;

    @Resource
    EventService eventService;

    private VoteEntity voteDtoToVoteEntity(VoteDto voteDto) {
        return VoteEntity.builder()
//                .user()
//                .userId(voteDto.getUserId())
                .voteNum(voteDto.getVoteNum())
                .voteTime(voteDto.getVoteTime())
                .build();
    }

    private VoteDto voteEntityToVoteDto(VoteEntity voteEntity) {
        return new VoteDto(voteEntity.getId(), voteEntity.getId(), voteEntity.getVoteNum(), voteEntity.getVoteTime());
    }

    @Transactional(rollbackOn = Exception.class)
    public void vote(VoteDto voteDto) throws CommonException {
        voteRepository.save(voteDtoToVoteEntity(voteDto));

        UserDto userDto = userService.getOneUser(voteDto.getUserId());
        int num = userDto.getVote() - voteDto.getVoteNum();
        if (num < 0) throw new CommonException("vote num is not enough, you have " + userDto.getVote());
        userDto.setVote(num);
        userService.updateUserById(voteDto.getUserId(), userDto);

        Event event = eventService.getEventById(voteDto.getRsEventId());
        event.setVoteNum(event.getVoteNum() + voteDto.getVoteNum());
        eventService.updateEvent(voteDto.getRsEventId(), event);
    }



//    public  List<VoteDto> getVotesByUserIdAndEventId(Integer userId, Integer eventId) {
////        List<VoteEntity> voteEntities = voteRepository.findAllByUserIdAndEventId(userId, eventId);
////        List<VoteDto> voteDtos = new ArrayList<>();
////        voteEntities.forEach(voteEntity -> voteDtos.add(voteEntityToVoteDto(voteEntity)));
////        return voteDtos;
//    }

    public List<VoteDto> getVotesByStartAndEnd(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        List<VoteEntity> voteEntities = voteRepository.findAllByVoteTimeBetween(start, end, pageable);
        List<VoteDto> voteDtos = new ArrayList<>();
        voteEntities.forEach(voteEntity -> voteDtos.add(voteEntityToVoteDto(voteEntity)));
        return voteDtos;
    }
}