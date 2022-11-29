package com.example.datajpa.repository;

import com.example.datajpa.dto.MemberDto;
import com.example.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    List<Member> findByUserNameAndAgeGreaterThan(String userName, int age);

    @Query(name="Member.findByUserName")
    List<Member> findByUserName(@Param("userName") String userName);

    @Query("select m from Member m where m.userName=:userName and m.age=:age")
    List<Member> findUser(@Param("userName") String userName, @Param("age") int age);

    @Query("select m.userName from Member m")
    List<String> findNames();

    @Query("select new com.example.datajpa.dto.MemberDto(m.id, m.userName, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    List<Member> findListByUserName(String userName);
    Member findMemberByUserName(String userName);
    Optional<Member> findOptionalByUserName(String userName);

    Page<Member> findByAge(int age, Pageable pageable);
    Slice<Member> findSliceByAge(int age, Pageable pageable);

    @Query(value = "select m from Member m left join m.team t",
    countQuery = "select count(m) from Member m")
    Page<Member> findMemberByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = :age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberByFetchJoin();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findByEntityGraph();

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUserName(String userName);

    //select for update
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUserName(String userName);

    List<UserNameOnly> findProjectionsByUserName(@Param("userName") String userName);

    @Query(value = "select * from member where username = ?", nativeQuery = true)
    Member findByNativeQuery(String userName);
}
