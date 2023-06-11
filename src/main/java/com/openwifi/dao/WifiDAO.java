package com.openwifi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.openwifi.dto.BookMarkDTO;
import com.openwifi.dto.BookMarkListDTO;
import com.openwifi.dto.DBConn;
import com.openwifi.dto.HistoryDTO;
import com.openwifi.dto.JsonDTO;

public class WifiDAO {
	static Connection conn;
	static PreparedStatement pstmt;
	ResultSet rs;

	public WifiDAO() {
		try {
			conn = DBConn.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//public void wifiInsert(List<WifiDTO> list) {
//		
//		
//		try {
//			System.out.println(list.size()+"dao");
//			Connection conn = SqlConnection.connection();
//			String sql = "INSERT INTO OPEN_WIFI(WIFI_ID, DISTRICT, WIFI_NAME, ADDR1, ADDR2, INST_FLO, INST_TYPE, INST_AUTHORITY, SERVICE_DIVISION, SERVICE_VARIETY, INST_YEAR, INST_LOC, WIFI_ENVIRONMENT, WIFI_X_COD, WIFI_Y_COD, INST_DATE)"
//					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON CONFLICT (WIFI_ID) DO UPDATE SET DISTRICT = EXCLUDED.DISTRICT";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			for(WifiDTO item : list) {
//			pstmt.setString(1, item.getWifiId());
//			pstmt.setString(2, item.getDistrict());
//			pstmt.setString(3, item.getWifiName());
//			pstmt.setString(4, item.getAddr1());
//			pstmt.setString(5, item.getAddr2());
//			pstmt.setString(6, item.getInstFlo());
//			pstmt.setString(7, item.getInstType());
//			pstmt.setString(8, item.getInstAuthority());
//			pstmt.setString(9, item.getServiceDivision());
//			pstmt.setString(10, item.getServiceVariety());
//			pstmt.setInt(11, item.getInstYear());
//			pstmt.setString(12, item.getInstLoc());
//			pstmt.setString(13, item.getWifiEnvironment());
//			pstmt.setString(14, item.getWifiXCode());
//			pstmt.setString(15, item.getWifiYCode());
//			pstmt.setString(16, item.getInstDate());
//			pstmt.execute();
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
//	
//public void wifiInsert(JsonDTO[] jsonDTOs) {
//		
//		try {
//			System.out.println(jsonDTOs.length+"dao");
//			Connection conn = SqlConnection.connection();
//			String sql = "INSERT INTO OPEN_WIFI(WIFI_ID, DISTRICT, WIFI_NAME, ADDR1, ADDR2, INST_FLO, INST_TYPE, INST_AUTHORITY, SERVICE_DIVISION, SERVICE_VARIETY, INST_YEAR, INST_LOC, WIFI_ENVIRONMENT, WIFI_X_COD, WIFI_Y_COD, INST_DATE)"
//					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON CONFLICT (WIFI_ID) DO UPDATE SET DISTRICT = EXCLUDED.DISTRICT";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			for(JsonDTO item : jsonDTOs) {
//			pstmt.setString(1, item.getX_SWIFI_MGR_NO().substring(3));
//			pstmt.setString(2, item.getX_SWIFI_WRDOFC());
//			pstmt.setString(3, item.getX_SWIFI_MAIN_NM());
//			pstmt.setString(4, item.getX_SWIFI_ADRES1());
//			pstmt.setString(5, item.getX_SWIFI_ADRES2());
//			pstmt.setString(6, item.getX_SWIFI_INSTL_FLOOR());
//			pstmt.setString(7, item.getX_SWIFI_INSTL_TY());
//			pstmt.setString(8, item.getX_SWIFI_INSTL_MBY());
//			pstmt.setString(9, item.getX_SWIFI_SVC_SE());
//			pstmt.setString(10, item.getX_SWIFI_CMCWR());
//			pstmt.setInt(11, item.getX_SWIFI_CNSTC_YEAR());
//			pstmt.setString(12, item.getX_SWIFI_INOUT_DOOR());
//			pstmt.setString(13, item.getX_SWIFI_REMARS3());
//			pstmt.setString(14, item.getLAT());
//			pstmt.setString(15, item.getLNT());
//			pstmt.setString(16, item.getWORK_DTTM());
//			
//			pstmt.executeUpdate();
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}

	public boolean wifiInsert(ArrayList<JsonObject> arrList) {

		Gson gson = new Gson();
		for (JsonObject jsonObject : arrList) {
			JsonDTO[] jsonDTOs = gson.fromJson(jsonObject.get("row"), JsonDTO[].class);

			String sql = "INSERT INTO OPEN_WIFI(WIFI_ID, DISTRICT, WIFI_NAME, ADDR1, ADDR2, INST_FLO, INST_TYPE, INST_AUTHORITY, SERVICE_DIVISION, SERVICE_VARIETY, INST_YEAR, INST_LOC, WIFI_ENVIRONMENT, WIFI_X_COD, WIFI_Y_COD, INST_DATE)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON CONFLICT (WIFI_ID) DO NOTHING";
			try {
				pstmt = conn.prepareStatement(sql);
				for (JsonDTO item : jsonDTOs) {
					pstmt.setString(1, item.getX_SWIFI_MGR_NO().substring(3));
					pstmt.setString(2, item.getX_SWIFI_WRDOFC());
					pstmt.setString(3, item.getX_SWIFI_MAIN_NM());
					pstmt.setString(4, item.getX_SWIFI_ADRES1());
					pstmt.setString(5, item.getX_SWIFI_ADRES2());
					pstmt.setString(6, item.getX_SWIFI_INSTL_FLOOR());
					pstmt.setString(7, item.getX_SWIFI_INSTL_TY());
					pstmt.setString(8, item.getX_SWIFI_INSTL_MBY());
					pstmt.setString(9, item.getX_SWIFI_SVC_SE());
					pstmt.setString(10, item.getX_SWIFI_CMCWR());
					pstmt.setInt(11, item.getX_SWIFI_CNSTC_YEAR());
					pstmt.setString(12, item.getX_SWIFI_INOUT_DOOR());
					pstmt.setString(13, item.getX_SWIFI_REMARS3());
					pstmt.setString(14, item.getLAT());
					pstmt.setString(15, item.getLNT());
					pstmt.setString(16, item.getWORK_DTTM());
					pstmt.execute();
				}
			} catch (SQLException e) {
				System.out.println("Sql Insert error = " + e);
				return false;
			} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
				try {
					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
//				try {
//					if (conn != null && !conn.isClosed()) {
//						conn.close();
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
			}
		}
		return true;
	}

	public int wifiCnt() {
		int total = 0;
		String sql = "SELECT COUNT(*) AS TOTAL fROM OPEN_WIFI";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt("TOTAL");
			}
		} catch (Exception e) {
			System.out.println("오류 : " + e);
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

		}

		return total;

	}

	public List<JsonDTO> getWifiList(String x, String y, int postStartNum, int postPerPage) {
		List<JsonDTO> list = new ArrayList<JsonDTO>();

		String sql = "SELECT substr((6371*ACOS(COS(RADIANS(" + x
				+ "))*COS(RADIANS(WIFI_Y_COD))*COS(radians(WIFI_X_COD)-RADIANS(" + y + "))+SIN(RADIANS(" + x
				+ "))*SIN(RADIANS(WIFI_Y_COD)))),1,6) as distance, * from OPEN_WIFI order by  distance asc limit "
				+ postStartNum + ", " + postPerPage;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				JsonDTO jsonDTO = new JsonDTO();
				jsonDTO.setDistance(rs.getString("distance"));
				System.out.println(jsonDTO.getDistance());
				jsonDTO.setX_SWIFI_MGR_NO(rs.getString("WIFI_ID"));
				jsonDTO.setX_SWIFI_WRDOFC(rs.getString("DISTRICT"));
				jsonDTO.setX_SWIFI_MAIN_NM(rs.getString("WIFI_NAME"));
				jsonDTO.setX_SWIFI_ADRES1(rs.getString("ADDR1"));
				jsonDTO.setX_SWIFI_ADRES2(rs.getString("ADDR2"));
				jsonDTO.setX_SWIFI_INSTL_FLOOR(rs.getString("INST_FLO"));
				jsonDTO.setX_SWIFI_INSTL_TY(rs.getString("INST_TYPE"));
				jsonDTO.setX_SWIFI_INSTL_MBY(rs.getString("INST_AUTHORITY"));
				jsonDTO.setX_SWIFI_SVC_SE(rs.getString("SERVICE_DIVISION"));
				jsonDTO.setX_SWIFI_CMCWR(rs.getString("SERVICE_VARIETY"));
				jsonDTO.setX_SWIFI_CNSTC_YEAR(rs.getInt("INST_YEAR"));
				jsonDTO.setX_SWIFI_INOUT_DOOR(rs.getString("INST_LOC"));
				jsonDTO.setX_SWIFI_REMARS3(rs.getString("WIFI_ENVIRONMENT"));
				jsonDTO.setLAT(rs.getString("WIFI_X_COD"));
				jsonDTO.setLNT(rs.getString("WIFI_Y_COD"));
				jsonDTO.setWORK_DTTM(String.valueOf(rs.getDate("INST_DATE")));
				list.add(jsonDTO);
				System.out.println(list.toString());
			}
		} catch (Exception e) {
			System.out.println("getWifiList 오류 : " + e);
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return list;

	}

	public JsonDTO detail(String wifiId, String x, String y) {
		String sql = "SELECT substr((6371*acos(cos(radians(" + x
				+ "))*cos(radians(WIFI_Y_COD))*cos(radians(WIFI_X_COD)-radians(" + y + "))+sin(radians(" + x
				+ "))*sin(radians(WIFI_Y_COD)))) ,1 ,6) as distance, * fROM OPEN_WIFI where WIFI_ID like '" + wifiId
				+ "'";
		JsonDTO result = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new JsonDTO(rs.getString("distance"), rs.getString("WIFI_ID"), rs.getString("DISTRICT"),
						rs.getString("WIFI_NAME"), rs.getString("ADDR1"), rs.getString("ADDR2"),
						rs.getString("INST_FLO"), rs.getString("INST_TYPE"), rs.getString("INST_AUTHORITY"),
						rs.getString("SERVICE_DIVISION"), rs.getString("SERVICE_VARIETY"), rs.getInt("INST_YEAR"),
						rs.getString("INST_LOC"), rs.getString("WIFI_ENVIRONMENT"), rs.getString("WIFI_X_COD"),
						rs.getString("WIFI_Y_COD"), rs.getString("INST_DATE"));

			}
		} catch (Exception e) {
			System.out.println("오류 : " + e);
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (rs == null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

		}

		return result;

	}

	public List<HistoryDTO> getHistoryList() {
		List<HistoryDTO> historyDTOs = new ArrayList<HistoryDTO>();
		String sql = "SELECT ROWID as ROWID, * from HISTORY ORDER BY ROWID DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ResultSetMetaData rm = rs.getMetaData();
				int numCols = rm.getColumnCount();
				List<String> colNames = IntStream.range(0, numCols).mapToObj(i -> {
					try {
						return rm.getColumnLabel(i + 1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("HistoryList 오류 : " + e);
						return null;
					}
				}).collect(Collectors.toList());
				JsonObject jsonObject = new JsonObject();
				colNames.forEach(key -> {
					try {
						jsonObject.addProperty(key, rs.getString(key));
					} catch (SQLException e) {
						System.out.println("HistoryList 오류 : " + e);
					}
				});
				historyDTOs.add(new Gson().fromJson(jsonObject, HistoryDTO.class));

			}

		} catch (SQLException e) {
			System.out.println("getHistoryList 오류 = " + e);
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (rs == null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return historyDTOs;
	}

	public boolean hisRecord(String lat, String lnt, String date) {

		String sql = "INSERT INTO HISTORY(USER_X_COD, USER_Y_COD, INQUIRY_DATE) VALUES(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lat);
			pstmt.setString(2, lnt);
			pstmt.setString(3, date);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

		}
		return true;

	}

	public boolean deleteHistory(String rowid) {
		String sql = "DELETE FROM HISTORY where ROWID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(rowid));
			if (pstmt.executeUpdate() != 1) {
				return false;
			}

		} catch (SQLException e) {
			System.out.println("기록 삭제 오류 : " + e);
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

		}

		return true;
	}

	public boolean addBookMark(String groupName, int order) {
		DateTimeFormatter dfs = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String sql = "INSERT INTO BOOK_MARK (GROUP_NAME, 'ORDER', ADD_DATE) VALUES (?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupName);
			pstmt.setInt(2, order);
			pstmt.setString(3, dfs.format(now));

			if (pstmt.executeUpdate() != 1) {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("addBookMark 오류 : " + e);
			return false;
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

		}
		return true;
	}

	public List<BookMarkDTO> getBooksMarkList() {
		List<BookMarkDTO> bookMarkDTOs = new ArrayList<BookMarkDTO>();
		String sql = "SELECT ROWID as ROWID, * from BOOK_MARK order by \"ORDER\" asc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ResultSetMetaData rm = rs.getMetaData();
				int numCols = rm.getColumnCount();
				List<String> colNames = IntStream.range(0, numCols).mapToObj(i -> {
					try {
						return rm.getColumnLabel(i + 1);
					} catch (SQLException e) {
						System.out.println("BookMarksList 오류 : " + e);
						return null;
					}
				}).collect(Collectors.toList());
				JsonObject jsonObject = new JsonObject();
				colNames.forEach(key -> {
					try {
						jsonObject.addProperty(key, rs.getString(key));
					} catch (SQLException e) {
						System.out.println("BookMarksList 오류 : " + e);
					}
				});
				bookMarkDTOs.add(new Gson().fromJson(jsonObject, BookMarkDTO.class));
				System.out.println("hi!");
			}
		} catch (SQLException e) {
			System.out.println("BookMarksList 오류 : " + e);
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

		}

		return bookMarkDTOs;

	}

	public int checkBookMark(String groupName, int order) {
		int count = 0;
		String sql = "SELECT count(*) as COUNT from BOOK_MARK where GROUP_NAME = ? or \"ORDER\" = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupName);
			pstmt.setInt(2, order);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

		}

		return count;
	}

	public boolean modifyBookMark(String groupName, int order, String modiName, int modiOrder) {
		DateTimeFormatter dfs = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String sql = "UPDATE BOOK_MARK SET GROUP_NAME = ?, \"ORDER\" = ?, MODI_DATE = ?  WHERE GROUP_NAME = ? and \"ORDER\" = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modiName);
			pstmt.setInt(2, modiOrder);
			pstmt.setString(3, dfs.format(now));
			pstmt.setString(4, groupName);
			pstmt.setInt(5, order);
			if (pstmt.executeUpdate() < 1) {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("modifBookMark 오류 : " + e);
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

		}
		
		sql = "UPDATE BOOK_MARK_DETAIL SET GROUP_NAME = ?  WHERE GROUP_NAME = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modiName);
			pstmt.setString(2, groupName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public boolean deleteBookMark(String groupName, int order) {
		String sql = "DELETE FROM BOOK_MARK WHERE GROUP_NAME = ? and \"ORDER\" = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupName);
			pstmt.setInt(2, order);
			if (pstmt.executeUpdate() < 1) {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("deleteBookMark 오류 : " + e);
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

		}
		sql = "DELETE FROM BOOK_MARK_DETAIL WHERE GROUP_NAME = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public int checkBookMarkList(String wifiId, String groupName) {
		int total = 0;
		String sql = "SELECT COUNT(*) AS COUNT fROM BOOK_MARK_DETAIL WHERE WIFI_ID LIKE '" + wifiId
				+ "' AND GROUP_NAME like '" + groupName + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

		}

		return total;
	}

	public boolean addToBookMark(String wifiId, String groupName, String wifiName) {
		DateTimeFormatter dfs = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String sql = "INSERT INTO BOOK_MARK_DETAIL (WIFI_ID, GROUP_NAME, WIFI_NAME, ADD_DATE) VALUES (?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, wifiId);
			pstmt.setString(2, groupName);
			pstmt.setString(3, wifiName);
			pstmt.setString(4, dfs.format(now));

			if (pstmt.executeUpdate() != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

		}
		return true;
	}

	public List<BookMarkListDTO> getBookMarkList() {
		String sql = "SELECT d.ROWID as ROWID, m.ROWID as GROUP_ID, d.WIFI_ID, d.GROUP_NAME, d.WIFI_NAME, d.ADD_DATE FROM BOOK_MARK_DETAIL d left join BOOK_MARK m on d.GROUP_NAME = m.GROUP_NAME";
		List<BookMarkListDTO> bookMarkListDTOs = new ArrayList<BookMarkListDTO>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ResultSetMetaData rm = rs.getMetaData();
				int numCols = rm.getColumnCount();
				List<String> colNames = IntStream.range(0, numCols).mapToObj(i -> {
					try {
						return rm.getColumnLabel(i + 1);
					} catch (SQLException e) {
						System.out.println("BookMarkList 오류 : " + e);
						return null;
					}
				}).collect(Collectors.toList());
				JsonObject jsonObject = new JsonObject();
				colNames.forEach(key -> {
					try {
						jsonObject.addProperty(key, rs.getString(key));
					} catch (SQLException e) {
						System.out.println("BookMarkList 오류 : " + e);
					}
				});
				bookMarkListDTOs.add(new Gson().fromJson(jsonObject, BookMarkListDTO.class));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bookMarkListDTOs;
	}

	public boolean deleteBookMarkList(int rowId, String wifiId) {
		String sql = "DELETE FROM BOOK_MARK_DETAIL WHERE ROWID = ? AND WIFI_ID = ?";
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rowId);
			pstmt.setString(2, wifiId);
			
			if(pstmt.executeUpdate()!=1) {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("deleteBookMarkList SQL 오류 : "+e);
		}finally { // 중간에 문제가 생겨 실행되지 않을 수 있기 때문에 마지막에 finally 로 실행해준다.
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}

}
