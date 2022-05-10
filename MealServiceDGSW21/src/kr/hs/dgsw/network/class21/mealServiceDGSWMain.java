package kr.hs.dgsw.network.class21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class mealServiceDGSWMain {
	public static void main(String[] args) {
		String newUrls = "https://open.neis.go.kr/hub/mealServiceDietInfo?Type=json&ATPT_OFCDC_SC_CODE=D10&SD_SCHUL_CODE=7240454&MLSV_YMD=20220503";
		
		try {
            URL url = new URL(newUrls);

            try {
                BufferedReader br = new BufferedReader(
                		new InputStreamReader(url.openStream(), "utf-8"));

                StringBuffer sb = new StringBuffer();
                String msg = null;
                while((msg=br.readLine())!=null){
                    sb.append(msg);
                }

                System.out.println(sb);
                
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = null;
                try {
                	jsonObject = (JSONObject) jsonParser.parse(sb.toString());
                } catch (ParseException e) {
                	e.printStackTrace();
                }
                JSONArray jsonArray =
                		(JSONArray) jsonObject.get("mealServiceDietInfo");
                
                JSONObject rowObject = (JSONObject) jsonArray.get(1);
                JSONArray rowArray =
                		(JSONArray) rowObject.get("row");
                
                for(int i=0; i<rowArray.size(); i++) {
                	JSONObject out = (JSONObject) rowArray.get(i);
                	System.out.println(out.get("MMEAL_SC_NM"));
                	System.out.println(out.get("DDISH_NM"));
                	System.out.println();
                }
                
                
            }catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }

    }

}