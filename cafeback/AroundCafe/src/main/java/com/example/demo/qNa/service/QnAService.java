package com.example.demo.qNa.service;

import com.example.demo.qNa.dto.QnACommentDto;
import com.example.demo.qNa.dto.QnADto;
import com.example.demo.qNa.dto.QnAResponse;
import com.example.demo.qNa.entity.QnA;
import com.example.demo.qNa.entity.QnAComment;

import java.util.List;
import java.util.Optional;

public interface QnAService {
    public void includeImgregister(QnADto info, String fileName);

    public void exceptImgRegister(QnADto info);

    public List<QnAComment> readQnA(Integer qnaNo);


    public List<QnAResponse> responseQnAList(Integer membNo);

    public void deleteQna(Integer qnaNo);

    public List<QnAResponse> responseAdminQnAList(Integer membNo);
}
