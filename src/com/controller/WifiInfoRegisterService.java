//Wifi 정보를 등록하는 서비스
package com.controller;

import java.util.List;

import com.model.Row;
import com.model.WifiInfoDTO;

public class WifiInfoRegisterService {
	public int wifiInfoRegister(WifiInfoDTO wifiInfoDTO) {
		if(wifiInfoDTO == null){
            return -1;
        }
        List<Row> rows = wifiInfoDTO.getTbPublicWifiInfo().getRow(); 
        int result = 0;
        WifiService ws = new WifiService(); 
        
        for(int i = 0 ; i < rows.size() ; i++) {
        	result = ws.register(rows.get(i));
        	if(result <= 0) {
        		System.out.println("인서트 실패");
        		break;
        	}
        }
        return result;
	}

}
