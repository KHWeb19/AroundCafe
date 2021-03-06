package com.example.demo.review.repository;


import com.example.demo.member.entity.Member;
import com.example.demo.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("select r from Review r where r.reviewNo = :memNo")
    List<Review> findReviewByMemberNo(@Param("memNo") Long memNo);


    @Query("select r from Review r where r.reviewNo = :cafeNum")
    List<Review> findAllReviewByCafe(@Param("cafeNum") Long cafeNum);

    @Query("select r from Review r where r.cafeNum = :CafeNo")
    List<Review> findAllReviewByCafeNo(@Param("CafeNo")Long CafeNo);


    @Query("select r from Review r where r.reviewNo = :reviewNo")
    Optional<Review> findByReviewNo(@Param("reviewNo")Long reviewNo);

    @Query("select m from Review m where m.cafeNum = :cafeNum order by m.reviewNo desc")
    List<Review> findByCafeNo(@Param("cafeNum") Long cafeNum);

    List<Review> findByMemberInfoOrderByReviewNoDesc(Member member);
}

