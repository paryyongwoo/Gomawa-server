package com.gomawa.gomawa.repository;

import com.gomawa.gomawa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    //Member findByKey(Long key);
    Optional<Member> findByKey(Long key);
}
