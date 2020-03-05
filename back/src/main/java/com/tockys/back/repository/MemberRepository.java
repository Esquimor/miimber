package com.tockys.back.repository;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.model.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {

}
