# Zerobase Project 
-----
## 프로젝트 설명
1. 공공 API를 이용해 WIFI 정보들을 가져온다 ( Open API 와이파이 정보 가져오기 )
2. 와이파이 정보를 성공적으로 가져오면 데이터 수와 성공했다는 문구가 출력되고 홈으로 되돌아가기를 클릭하면 홈으로 되돌아온다.
3. 내 위치 가져오기를 누르거나 직접 좌표(위도, 경도)를 입력한다.
4. 좌표가 입력된 상태에서 내 근처 WIFI정보 보기를 누르면 거리순으로 해당 위치 주변의 20개 WIFI 정보를 가져온다.
5. 위치 히스토리 목록을 누르면 이전에 내 위치 가져오기나 직접 좌표입력을 이용해 내 근처 WIFI정보 보기를 눌렀던 위치의 목록들이 최신 목록 조회 순으로 출력된다.
6. 비고란에 삭제를 누르면 해당 히스토리는 삭제된다.
7. 삭제 실패 시 어떤 ID 삭제가 실패되었는 지 문구가 출력되며 홈 / 위치 히스토리 목록으로 되돌아갈 수 있다.
-----
## 프로젝트 내 파일 설명
- com.api 패키지
   1) GetOpenAPI.java : openAPI에서 WIFI 정보를 가져온다.

- com.controller 패키지
   1) DeleteHistService.java : 위치 히스토리 데이터를 삭제하는 서비스를 하는 Servlet
   2) NearWifiSearchService.java : 위치 히스토리 데이터를 저장하고 근처 wifi목록을 조회하는 서비스를 하는 Servlet
   3) WifiInfoRegisterService.java : openAPI를 통해 가져온 Wwifi정보를 저장하는 서비스
   4) WifiService.java : 모든 db와의 작업을 하는 서비스

- com.model 패키지
   1) LocInqHistDTO.java : 위치 히스토리 목록을 db로부터 받아와서 담을 DTO
   2) NearDistDTO.java : 위치 히스토리 목록을 db로부터 받아와서 담을 DTO
   3) Result.java : OPEN API에서 RESULT 값을 받아와서 담을 DTO
   4) Row.java : OPEN API에서 ROW값을 받아와서 담을 DTO
   5) TbPublicWifiInfo.java : ROW, RESULT , LIST_TOTAL_COUNT 등의 받아온 값을 저장할 DTO
   6) WifiInfoDTO.java : TbPublicWifiInfo 를 사용하기 편하게 하기 위한 DTO
  
- WebContent 내 jsp 파일
   1) getWifiInfo.jsp :  Open API 와이파이 정보 가져온 결과를 보여주는 jsp
   2) history.jsp : 위치 히스토리 목록을 구성하는 jsp
   3) home.jsp : 홈 화면을 구성하는 jsp
   4) historyDeleteFail.jsp : 위치 히스토리 삭제 실패 시를 구성하는 jsp

-----
## 참고
- IDE : Eclipse
- DB : sqlite
  (처음에 프로젝트를 진행할 때는 좀 더 익숙한 MariaDB로 하였고 그 다음 sqlite로 해보았는데 둘의 속도를 비교해보니 sqlite가 좀 더 빨랐습니다.)
- ERD : EXERD 




