package com.comsearch.repository;

import com.comsearch.vo.SearchVo;

import java.util.ArrayList;

public interface CommonRepository {

    SearchVo save(SearchVo searchVo);

    public ArrayList<SearchVo> keywordSearchList();
}
