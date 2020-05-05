package com.gomawa.gomawa.repository;

import com.gomawa.gomawa.entity.DailyThanks;
import com.gomawa.gomawa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyThanksRepository extends JpaRepository<DailyThanks, Long> {
    public DailyThanks findByRegMember(Member member);
}
