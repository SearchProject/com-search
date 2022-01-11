# 장소검색 서비스
**1. Springboot 기반의 장소검색 서비스입니다.**
  - Java 8
  - Spring Boot
  - Gradle
  <br/>
  
**2. 외부 라이브러리 사용**
  - json-simple : json 데이터 처리
  <br/>
  
**3. 테스트 방법**
  - HTTP Request file 실행 또는 url 호출
    - HTTP Request file path : com-search/src/main/resources/Scratches/generated-requests.http
  
  - 장소 검색 호출 ex) http://localhost:8080/keyword?keyword=판교마카롱
  ```
  {"places":[
  {"searchKeyword":"판교마카롱","searchCount":0,"seq":1,"fromData":"KN","title":"기욤 판교아브뉴프랑점","category":"카페","address":"경기 성남시 분당구 삼평동 740","roadAddress":"경기 성남시 분당구 동판교로177번길 25"},
  {"searchKeyword":"판교마카롱","searchCount":0,"seq":1,"fromData":"KN","title":"마카블랑","category":"음식점","address":"경기 성남시 분당구 운중동 963","roadAddress":"경기 성남시 분당구 운중로 129"},
  {"searchKeyword":"판교마카롱","searchCount":0,"seq":1,"fromData":"KN","title":"카스테라봉봉","category":"카페","address":"경기 성남시 분당구 운중동 35-4","roadAddress":"경기 성남시 분당구 하오개로344번길 8"},
  {"searchKeyword":"판교마카롱","searchCount":0,"seq":1,"fromData":"K","title":"이억녀의마카롱","category":"카페","address":"경기 성남시 분당구 백현동 541","roadAddress":"경기 성남시 분당구 판교역로146번길 20"},
  {"searchKeyword":"판교마카롱","searchCount":0,"seq":1,"fromData":"K","title":"마카롱팩토리","category":"","address":"경기 성남시 분당구 삼평동 670","roadAddress":"경기 성남시 분당구 대왕판교로 660"},
  {"searchKeyword":"판교마카롱","searchCount":0,"seq":1,"fromData":"K","title":"쿠잉","category":"카페","address":"경기 성남시 분당구 백현동 580-3","roadAddress":"경기 성남시 분당구 판교역로10번길 12-7"},
  {"searchKeyword":"판교마카롱","searchCount":0,"seq":1,"fromData":"K","title":"라미벨르","category":"음식점","address":"경기 성남시 분당구 운중동 970-7","roadAddress":"경기 성남시 분당구 산운로160번길 20-6"},
  {"searchKeyword":"판교마카롱","searchCount":0,"seq":1,"fromData":"K","title":"라꼬끄","category":"음식점","address":"경기 성남시 분당구 운중동 978-3","roadAddress":"경기 성남시 분당구 운중로125번길 3-3"},
  {"searchKeyword":"판교마카롱","searchCount":0,"seq":2,"fromData":"N","title":"다문제과점","category":"카페,디저트>베이커리","address":"경기도 성남시 분당구 운중동 944","roadAddress":"경기도 성남시 분당구 운중로 128 마크시티그린 401호"},
  {"searchKeyword":"판교마카롱","searchCount":0,"seq":2,"fromData":"N","title":"데조로의집","category":"카페,디저트>베이커리","address":"경기도 성남시 분당구 삼평동 719","roadAddress":"경기도 성남시 분당구 판교로 376 마크시티 퍼플"}]
  }
  ```
  
  - 검색 키워드 목록 호출 ex) http://localhost:8080/searchKeywordList
```
{"placesCount":[
{"searchKeyword":null,"searchCount":1,"seq":0,"fromData":null,"title":null,"category":null,"address":null,"roadAddress":null},
{"searchKeyword":"","searchCount":1,"seq":0,"fromData":null,"title":null,"category":null,"address":null,"roadAddress":null},
{"searchKeyword":"판교쌀국수","searchCount":1,"seq":0,"fromData":null,"title":null,"category":null,"address":null,"roadAddress":null},
{"searchKeyword":"apple","searchCount":1,"seq":0,"fromData":null,"title":null,"category":null,"address":null,"roadAddress":null},
{"searchKeyword":"test","searchCount":1,"seq":0,"fromData":null,"title":null,"category":null,"address":null,"roadAddress":null},
{"searchKeyword":"판교떡볶이","searchCount":1,"seq":0,"fromData":null,"title":null,"category":null,"address":null,"roadAddress":null},
{"searchKeyword":"과일","searchCount":1,"seq":0,"fromData":null,"title":null,"category":null,"address":null,"roadAddress":null},
{"searchKeyword":"마카롱","searchCount":1,"seq":0,"fromData":null,"title":null,"category":null,"address":null,"roadAddress":null},
{"searchKeyword":"판교마카롱","searchCount":1,"seq":0,"fromData":null,"title":null,"category":null,"address":null,"roadAddress":null},
{"searchKeyword":"라면","searchCount":1,"seq":0,"fromData":null,"title":null,"category":null,"address":null,"roadAddress":null}]
}
```
<br/>

**4. 설명**
  - 각각의 API에서 호출해온 title(장소명)의 문자열을 edit distance 계산을 통하여 유사도 확인 
  - 유사도는 0 ~ 1 사이의 값으로 0은 전혀 다름, 1은 동일 문자열을 뜻함
  - 문자열이 기준 값 이상일 경우 동일 장소로 간주함
  <br/>
  
**5. 참조**
  - http://rosettacode.org/wiki/Levenshtein_distance#Java
  - http://www.gisdeveloper.co.kr/?p=9756
