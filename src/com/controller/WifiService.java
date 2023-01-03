//DB CRUD를 실행하는 서비스
package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.model.LocInqHistDTO;
import com.model.NearDistDTO;
import com.model.Row;
import com.model.WifiInfoDTO;

public class WifiService {
	private String url = "jdbc:sqlite:C:\\Users\\a\\MySQLiteDB";
    private String dbUserId = "wifiUser";
    private String dbPassword = "zerobase";
    private String className = "org.sqlite.JDBC";
    
	/**
     * 테이블에 와이파이 정보 Insert
     * @param member 회원 정보
     * */
    public int register(Row row) {

        
        int result = 0;

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Class.forName(className);
            connection = DriverManager.getConnection(url,dbUserId,dbPassword);
            
            String sql = "INSERT INTO WIFI_INFO VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            ps = connection.prepareStatement(sql);

            ps.setString(1,row.getXSwifiMgrNo()); 
            ps.setString(2,row.getXSwifiWrdofc());
            ps.setString(3,row.getXSwifiMainNm()); 
            ps.setString(4,row.getXSwifiAdres1()); 
            ps.setString(5,row.getXSwifiAdres2()); 
            ps.setString(6,row.getXSwifiInstlFloor()); 
            ps.setString(7,row.getXSwifiInstlTy()); 
            ps.setString(8,row.getXSwifiInstlMby()); 
            ps.setString(9,row.getXSwifiSvcSe()); 
            ps.setString(10,row.getXSwifiCmcwr());
            ps.setString(11,row.getXSwifiCnstcYear());
            ps.setString(12,row.getXSwifiInoutDoor());
            ps.setString(13,row.getXSwifiRemars3());
            ps.setDouble(14,Double.parseDouble(row.getLat()));
            ps.setDouble(15,Double.parseDouble(row.getLnt()));
            String formatDateTime = row.getWorkDttm();
            LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(formatDateTime.substring(0,4))
            		, Integer.parseInt(formatDateTime.substring(5,7))
            		, Integer.parseInt(formatDateTime.substring(8,10))
            		, Integer.parseInt(formatDateTime.substring(11,13))
            		, Integer.parseInt(formatDateTime.substring(14,16))
            		, Integer.parseInt(formatDateTime.substring(17,19)));
            
            Timestamp ts = Timestamp.valueOf(localDateTime);
            ps.setTimestamp(16, ts);
//            ps.setDate(16,Date.UTC(result, result, result, result, result, result).getWorkDttm()));

            result = ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ps != null && !ps.isClosed()){
                    ps.close();
                }
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
    
    /**
     * 위치 히스토리 값 등록
     * @param
     * */    
    public int registerHist(double lat , double lnt) {
         int result = 0;

         Connection connection = null;
         PreparedStatement ps = null;
         try {
             Class.forName(className);
             connection = DriverManager.getConnection(url,dbUserId,dbPassword);
             
             String sql = "INSERT INTO LOC_INQ_H ( lat , lnt , inq_dtm ) "
             		+ " VALUES ( ?,?,?) ";
             ps = connection.prepareStatement(sql);
             Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
             
             ps.setDouble(1,lat); 
             ps.setDouble(2,lnt);
             ps.setTimestamp(3,ts); 

             result = ps.executeUpdate();

         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             try {
                 if(ps != null && !ps.isClosed()){
                     ps.close();
                 }
                 if(connection != null && !connection.isClosed()){
                     connection.close();
                 }
             } catch (SQLException e) {
                 throw new RuntimeException(e);
             }
         }
         return result;
    }
    
    /**
     * 위치 히스토리 목록 조회
     * @param
     * */    
    public List<LocInqHistDTO> LocInqHistSelect(){
        
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<LocInqHistDTO> resultList = new ArrayList<>();
        try {
            Class.forName(className);
            connection = DriverManager.getConnection(url,dbUserId,dbPassword);


            String sql = "SELECT * FROM LOC_INQ_H ORDER BY inqNo DESC";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();


            while (rs.next()){
            	LocInqHistDTO lihDTO = new LocInqHistDTO();
            	lihDTO.setInqNo(rs.getInt("inqNo"));
            	lihDTO.setLat(rs.getDouble("lat"));
            	lihDTO.setLnt(rs.getDouble("lnt"));
            	lihDTO.setInq_dtm(rs.getTimestamp("inq_dtm"));
                resultList.add(lihDTO);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(ps != null && !ps.isClosed()){
                    ps.close();
                }
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
		return resultList;
    }
    
    
    /**
     * 위치 히스토리 목록 조회
     * @param
     * */    
    public List<NearDistDTO> nearWifiSelect(double lat , double lnt){
        
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<NearDistDTO> resultList = new ArrayList<>();
        try {
            Class.forName(className);
            connection = DriverManager.getConnection(url,dbUserId,dbPassword);


            String sql = "SELECT ROUND((6371*acos(cos(radians(?))*cos(radians(wi.lat))*cos(radians(wi.lnt) "
            		+ "	-radians(?))+sin(radians(?))*sin(radians(wi.lat)))),4) AS distance "
            		+ "	, wi.* "
            		+ " FROM WIFI_INFO wi "
            		+ " ORDER BY distance "
            		+ " LIMIT 0,20";
//            Statement statement = connection.createStatement();
            ps = connection.prepareStatement(sql);
            ps.setDouble(1, lat);
            ps.setDouble(2, lnt);
            ps.setDouble(3, lat);
            rs = ps.executeQuery();


            while (rs.next()){
            	NearDistDTO nearDist = new NearDistDTO();
            	nearDist.setDistance(rs.getDouble(1));
            	nearDist.setXSwifiMgrNo(rs.getString(2));
            	nearDist.setXSwifiWrdofc(rs.getString(3));
            	nearDist.setXSwifiMainNm(rs.getString(4));
            	nearDist.setXSwifiAdres1(rs.getString(5));
            	nearDist.setXSwifiAdres2(rs.getString(6));
            	nearDist.setXSwifiInstlFloor(rs.getString(7));
            	nearDist.setXSwifiInstlTy(rs.getString(8));
            	nearDist.setXSwifiInstlMby(rs.getString(9));
            	nearDist.setXSwifiSvcSe(rs.getString(10));
            	nearDist.setXSwifiCmcwr(rs.getString(11));
            	nearDist.setXSwifiCnstcYear(rs.getString(12));
            	nearDist.setXSwifiInoutDoor(rs.getString(13));
            	nearDist.setXSwifiRemars3(rs.getString(14));
            	nearDist.setLat(rs.getString(15));
            	nearDist.setLnt(rs.getString(16));
            	nearDist.setWorkDttm(rs.getTimestamp(17));
                resultList.add(nearDist);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(ps != null && !ps.isClosed()){
                    ps.close();
                }
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
		return resultList;
    }

    /**
     * 와이파이 정보 가져오기 전에 테이블에 있는 정보 삭제하기
     * @param
     * */
    public int deleteAll()  {
        int result = 0;

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Class.forName(className);
            connection = DriverManager.getConnection(url,dbUserId,dbPassword);

            String sql = "DELETE FROM WIFI_INFO";
            ps = connection.prepareStatement(sql);

            result = ps.executeUpdate();
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ps != null && !ps.isClosed()){
                    ps.close();
                }
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        return result;
    }
    
    /**
     * 지정된 위치 히스토리 목록 삭제하기
     * @param
     * */
    public int deleteHist(int inqNo)  {
        int result = 0;

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Class.forName(className);
            connection = DriverManager.getConnection(url,dbUserId,dbPassword);

            String sql = "DELETE FROM LOC_INQ_H WHERE inqNo = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, inqNo);

            result = ps.executeUpdate();
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ps != null && !ps.isClosed()){
                    ps.close();
                }
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        return result;
    }
}
