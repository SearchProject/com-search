package com.comsearch.repository;

import com.comsearch.vo.SearchVo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

@Repository
public class NSearchRepositoryImpl implements SearchRepository{

    ArrayList<SearchVo> arrayList;

    @Value("${n.url}")
    private String n_url;

    @Value("${n.client.id}")
    private String n_client_id;

    @Value("${n.client.secret}")
    private String n_client_secret;

    @Override
    public ArrayList<SearchVo>  apiCall(String keyword) throws IOException {

        String encodeKeyword = URLEncoder.encode(keyword,"UTF-8");

        //1(기본값), 5(최대)
        String url = n_url+ encodeKeyword+"&display=5";

        String jsonString = new String();

        String buf;

        URL Url = new URL(url);

        HttpsURLConnection conn = (HttpsURLConnection) Url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-Requested-With", "curl");
        conn.setRequestProperty("X-Naver-Client-Id", n_client_id);
        conn.setRequestProperty("X-Naver-Client-Secret", n_client_secret);
        conn.setDoOutput(true);
        int responseCode = conn.getResponseCode();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        while((buf = br.readLine()) != null) {
            jsonString += buf;
        }

        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse( jsonString );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObj = (JSONObject) obj;
        JSONArray jArray = (JSONArray) jsonObj.get("items");

        arrayList = new ArrayList<SearchVo>();
        for(int i=0; i<jArray.size(); i++){
            SearchVo insertVo = new SearchVo();

            JSONObject test = (JSONObject)jArray.get(i);
            String title = (String) test.get("title");
            title = title.replaceAll("<b>","").replaceAll("</b>","");
            String category = (String) test.get("category");
            //String description = (String) test.get("description");
            String address = (String) test.get("address");
            String roadAddress = (String) test.get("roadAddress");
            System.out.println("title : "+title+" category : "+category+" address : "+address+" roadAddress : "+roadAddress);

            insertVo.setSeq(2);
            insertVo.setFromData("N");
            insertVo.setSearchKeyword(keyword);
            insertVo.setTitle(title);
            insertVo.setCategory(category);
            insertVo.setAddress(address);
            insertVo.setRoadAddress(roadAddress);

            arrayList.add(insertVo);

        }
        return arrayList;
    }

    @Override
    public SearchVo save(SearchVo searchVo) {
        return null;
    }
}
