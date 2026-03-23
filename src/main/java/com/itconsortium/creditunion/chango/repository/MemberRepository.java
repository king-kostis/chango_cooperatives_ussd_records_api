package com.itconsortium.creditunion.chango.repository;

import com.itconsortium.creditunion.chango.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberByEmailAddress(String emailAddress);
}
