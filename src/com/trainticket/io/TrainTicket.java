package com.trainticket.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.trainticket.dao.StationCodeDao;

public class TrainTicket {
	public static void main(String[] args) {
		System.out.println("TrainTicket.main.into--->");
		initStationCode();
	}

	/**
	 * 初始化车站编码
	 */
	private static void initStationCode() {
		System.out.println("initStationCode.into--->");
		long startTime = System.currentTimeMillis();
		System.out.println(">>>>> 插入城市代码数据：startTime = " + startTime);

		InputStream input = null;// 文件的输入输出流
		try {
			input = TrainTicket.class.getClassLoader().getResourceAsStream("station_code.txt");// 打开文件
			BufferedReader readCode = new BufferedReader(new InputStreamReader(input));

			StringBuilder builderCode = new StringBuilder();

			String codeStr = null;

			while ((codeStr = readCode.readLine()) != null) {
				builderCode.append(codeStr + "\n");
			}

			String[] codes = builderCode.toString().split(":");// 以":"的形式分割，得到关于车站的数组
			System.out.println("codes--->" + codes);
			StationCodeDao codeDao = new StationCodeDao();
			String code = null;
			for (int i = 0; i < codes.length; i++) {
				code = codes[i];// 循环得到关于车站消息的每条信息
				String[] stationParam = code.split(",");// 以":"的形式分割，得到关于车站的各个数据
				String stationName = stationParam[0];
				String stationCode = stationParam[1];
				String stationJianpin = stationParam[2];
				String stationFullName = stationParam[3];
				codeDao.insert(stationName, stationCode, stationJianpin, stationFullName);// 执行添加语句
			}
			long stopTime = System.currentTimeMillis();
			System.out.println(">>>>> 插入城市代码数据：stopTime = " + stopTime);
			System.out.println(">>>>> 插入城市代码数据：总用时 = " + (stopTime - startTime));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
