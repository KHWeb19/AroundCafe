package com.example.demo.qNa.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class QnAResponse {
    private Long qna_no;
    private Long received_no;
    private String received_img;
    private String type;
    private String received_name;
    private Long writer;
    private String writerImg;
    private String writer_name;
    private String content;
    private String regTime;
    private String regYear;
    private String received_socialType;



    @Builder
    public QnAResponse (Long qna_no, Long received_no, String received_img, String type
    ,String received_name, Long writer, String content, String regTime, String regYear,
                        String writerImg,String writer_name, String received_socialType){
        this.qna_no = qna_no;
        this.received_no = received_no;
        this.received_img = received_img;
        this.type = type;
        this.received_name = received_name;
        this.writer = writer;
        this.content = content;
        this.regTime = regTime;
        this.regYear = regYear;
        this.writerImg = writerImg;
        this.writer_name = writer_name;
        this.received_socialType = received_socialType;
    }
}
