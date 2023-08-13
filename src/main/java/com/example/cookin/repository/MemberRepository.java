package com.example.cookin.repository;

import com.example.cookin.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByUsername(String username);
}