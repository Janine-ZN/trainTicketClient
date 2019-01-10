package com.trainticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.trainticket.dto.StationCode;
import com.trainticket.jdbc.JDBCTools;

public class StationCodeDao {
	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement preparedStatement = null;

	/**
	 * 将文件中的数据读取并添加到数据库中
	 * 
	 * @param sql
	 */
	public StationCode insert(Object... arg) {
		System.out.println("StationCode.insert.into--->");
		StationCode kaoqin = null;
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写新增SQL语句
			String sql = "INSERT INTO tb_station(station_name,station_code,station_jianpin,station_fullName)"
					+ "VALUES(?,?,?,?)";
			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.循环添加数据参数(即设置参数)
			for (int i = 0; i < arg.length; i++) {
				preparedStatement.setObject(i + 1, arg[i]);
			}

			// 4.执行preparedStatement
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(null, preparedStatement, conn);
		}
		return kaoqin;
	}

	/**
	 * 查询所有的车站信息
	 * 
	 * @return
	 */
	public List<StationCode> select() {
		StationCode stationCode = null;
		List<StationCode> list = new ArrayList<StationCode>();
		try {
			// 1.获取Connection
			conn = JDBCTools.getConnection();

			// 写查询SQL语句
			String sql = "select * from tb_station";

			// 2.获取preparedStatement
			preparedStatement = conn.prepareStatement(sql);

			// 3.设置查询参数
			// 没有条件不执行

			// 4.执行查询，得到ResultSet
			rs = preparedStatement.executeQuery();

			// 5.处理ResultSet
			while (rs.next()) {

				stationCode = new StationCode();
				// 用来返回的数据
				stationCode.setName(rs.getString("station_name"));
				stationCode.setCode(rs.getString("station_code"));
				stationCode.setJianPin(rs.getString("station_jianpin"));
				stationCode.setQuanPin(rs.getString("station_fullName"));
				list.add(stationCode);// 布尔型
			
			}
			System.out.println("StationCodeDao.list--->"+list);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// 6.关闭数据库资源
			JDBCTools.releaseSource(rs, preparedStatement, conn);
		}
		return list;
	}
	
}
