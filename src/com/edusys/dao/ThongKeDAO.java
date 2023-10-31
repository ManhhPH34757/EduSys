package com.edusys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edusys.utils.XJdbc;

public class ThongKeDAO {
	
	private List<Object[]> getListOfArray(String sql, String[] cols, Object... args){
		try {
			List<Object[]> list = new ArrayList<>();
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				Object[] vals = new Object[cols.length];
				for (int i = 0; i < cols.length; i++) {
					vals[i] = rs.getObject(cols[i]);
				}
				list.add(vals);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Object[]> getBangDiem(int maKH) {
		String sql = "{CALL SP_BangDiem(?)}";
		String[] cols = {"Mã người học","Họ tên","Điểm"};
		return this.getListOfArray(sql, cols, maKH);
		
	}
	
	public List<Object[]> getLuongNguoiHoc() {
		String sql = "{CALL SP_ThongKeNguoiHoc}";
		String[] cols = {"Năm","Số lượng người học","Ngày đăng kí đầu tiên","Ngày đăng kí cuối cùng"};
		return this.getListOfArray(sql, cols);
		
	}
	
	public List<Object[]> getDiemChuyeDe() {
		String sql = "{CALL SP_ThongKeDiem}";
		String[] cols = {"Chuyên đề","Số học viên","Điểm thấp nhất","Điểm cao nhất","Điểm trung bình"};
		return this.getListOfArray(sql, cols);
	}
	
	public List<Object[]> getDoanhThu(int nam) {
		String sql = "{CALL SP_ThongKeDoanhThu(?)}";
		String[] cols = {"Chuyên đề","Số khóa học","Số học viên","Doanh thu","Học phí thấp nhất","Học phí cao nhất","Học phí trung bình"};
		return this.getListOfArray(sql, cols, nam);
	}
	
}
