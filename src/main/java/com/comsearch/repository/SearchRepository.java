package com.comsearch.repository;

import com.comsearch.vo.SearchVo;

import java.io.IOException;
import java.util.ArrayList;

public interface SearchRepository {

    public ArrayList<SearchVo> apiCall(String keyword) throws IOException;

    SearchVo save(SearchVo searchVo);
}
