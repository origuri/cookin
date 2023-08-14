package com.example.cookin.repository;

import com.example.cookin.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByUsername(String username);

    @Query("SELECT m.password FROM Member m WHERE m.id = :memberUid")
    public String findPasswordById(@Param("memberUid") Long memberUid);
}
