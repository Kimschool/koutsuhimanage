package koutsuhi.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import koutsuhi.entity.UserInfoEntity;

public class DataUtil {

	String userInfoPath = "src\\userinfo.csv";

	public String[][] loadUserTransInfo(String date, String userId){
		String transInfoPath = "src\\data\\" + date + "\\" + userId + ".csv";
		String[][] transInfoArr = new String[0][];
		int count = 0;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(transInfoPath));
			String line;
			int lineCount = 0;

			while ((line = reader.readLine()) != null) {
                lineCount++;
            }

			transInfoArr = new String[lineCount][];

			reader.close();
	        reader = new BufferedReader(new FileReader(transInfoPath));


			while((line = reader.readLine()) != null) {
				String[] splitArr = line.split(",");
				transInfoArr[count] = splitArr;
				count++;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return transInfoArr;
	}


	public List<UserInfoEntity> loadAllData() {

		List<UserInfoEntity> userInfoList = new ArrayList<UserInfoEntity>();
		UserInfoEntity userInfo = new UserInfoEntity();
		try (
			BufferedReader reader = new BufferedReader(new FileReader(userInfoPath))) {
            String line;
            // 한 줄씩 읽어오기
            boolean existLine = true;

            while (existLine) {
            	line = reader.readLine();
            	if(line != null){
            		userInfo = new UserInfoEntity();
            		String[] splitArr =line.split(",");
            		userInfo.setId(splitArr[0]);
            		userInfo.setPw(splitArr[1]);
            		userInfo.setName(splitArr[2]);
            		userInfo.setGender(splitArr[3]);
            		userInfoList.add(userInfo);
            	} else {
            		existLine = false;
            	}
            }

        } catch (IOException e) {
            System.out.println("파일을 읽어오는 동안 오류가 발생했습니다.");
            e.printStackTrace();
        }

		return userInfoList;

	}

}
