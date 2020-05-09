package com.gomawa.gomawa.service;

import com.gomawa.gomawa.dto.DailyThanksDto;
import com.gomawa.gomawa.dto.MemberDto;
import com.gomawa.gomawa.entity.DailyThanks;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.repository.DailyThanksRepository;
import com.gomawa.gomawa.repository.DailyThanksRepositorySupport;
import com.gomawa.gomawa.repository.MemberRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class MyThanksService {

    @Autowired
    private DailyThanksRepository dailyThanksRepository;

    @Autowired
    private MemberRepository memberRepository;

    private ModelMapper modelMapper;
    private DailyThanksRepositorySupport dailyThanksRepositorySupport;

    public MyThanksService(ModelMapper modelMapper, DailyThanksRepositorySupport dailyThanksRepositorySupport) {
        this.modelMapper = modelMapper;
        this.dailyThanksRepositorySupport = dailyThanksRepositorySupport;
    }

    /**
     * 접속한 사용자의 당일 DailyThanks를 가져오는 기능
     * @param memberId 접속한 사용자의 id
     * @return 당일 작성된 DailyThanks
     *
     * 오늘 작성한 dailyThanks 가져오는 쿼리
     * select * from daily_thanks where to_char(reg_date, 'yyyy-mm-dd') = to_char(current_date, 'yyyy-mm-dd')
     */
    public DailyThanksDto getDailyThanks(Long memberId) {
        Optional<Member> optional = memberRepository.findById(memberId);
        Member member = optional.orElseThrow();
        DailyThanks dailyThanks = dailyThanksRepositorySupport.getDailyThanks(member);
        if (dailyThanks == null) return null;
        TypeMap<DailyThanks, DailyThanksDto> typeMap = modelMapper.typeMap(DailyThanks.class, DailyThanksDto.class);
        DailyThanksDto dailyThanksDto = typeMap.map(dailyThanks);
//        TypeMap<DailyThanks, DailyThanksDto> dailyThanksDailyThanksDtoTypeMap = typeMap.addMappings(mapper -> {
//            mapper.using((Converter<String, Integer>) mappingContext -> mappingContext.getSource().length()).map(src -> src.getContent(), DailyThanksDto::setCtLength);
//        });
//        DailyThanksDto dailyThanksDto = dailyThanksDailyThanksDtoTypeMap.map(dailyThanks);
        return dailyThanksDto;
    }
    /**
     * DailyThnaks를 저장하는 기능
     */
    public DailyThanksDto addDailyThanks(DailyThanksDto dailyThanksDto) {
        MemberDto memberDto = dailyThanksDto.getRegMember();
        Optional<Member> memberOptional = memberRepository.findByKey(memberDto.getKey());
        Member member = memberOptional.orElseThrow();

        String content = dailyThanksDto.getContent();

        DailyThanks dailyThanks = new DailyThanks();
        dailyThanks.setRegMember(member);
        dailyThanks.setContent(content);
        dailyThanksRepository.save(dailyThanks);

        DailyThanksDto savedDailyThanksDto = dailyThanks.convertToDto();
        return savedDailyThanksDto;
    }
}
