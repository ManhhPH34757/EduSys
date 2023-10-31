package com.edusys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edusys.entity.NguoiHoc;
import com.edusys.utils.XJdbc;

public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String>{

	String Insert_SQL = "INSERT INTO NguoiHoc(MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String Update_SQL = "UPDATE NguoiHoc SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, DienThoai = ?, Email = ?, GhiChu = ?, MaNV = ?, NgayDK = ? WHERE MaNH = ?";
	String Delete_SQL = "DELETE FROM NguoiHoc WHERE MaNH = ?";
	String SelectAll_SQL = "SELECT * FROM NguoiHoc";
	String SelectById_SQL = "SELECT * FROM NguoiHoc WHERE MaNH = ?";
	String SelectByKeyword_SQL = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
	String SelectByKeyword_SQL1 = "SELECT * FROM NguoiHoc WHERE MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH = ?) AND HoTen LIKE ?";
	
	@Override
	public void insert(NguoiHoc nguoiHoc) {
		try {
			XJdbc.update(Insert_SQL, nguoiHoc.getMaNH(), nguoiHoc.getHoTen(), nguoiHoc.getNgaySinh(), nguoiHoc.isGioiTinh(), nguoiHoc.getDienThoai(), nguoiHoc.getEmail(), nguoiHoc.getGhiChu(), nguoiHoc.getMaNV(), nguoiHoc.getNgayDK());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void update(NguoiHoc nguoiHoc) {
		try {
			XJdbc.update(Update_SQL, nguoiHoc.getHoTen(), nguoiHoc.getNgaySinh(), nguoiHoc.isGioiTinh(), nguoiHoc.getDienThoai(), nguoiHoc.getEmail(), nguoiHoc.getGhiChu(), nguoiHoc.getMaNV(), nguoiHoc.getNgayDK(), nguoiHoc.getMaNH());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(String maNH) {
		try {
			XJdbc.update(Delete_SQL, maNH);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public NguoiHoc selectById(String maNH) {
		List<NguoiHoc> list = this.selectBySQL(SelectById_SQL, maNH);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<NguoiHoc> selectAll() {
		return this.selectBySQL(SelectAll_SQL);
	}

	@Override
	protected List<NguoiHoc> selectBySQL(String sql, Object... args) {
		List<NguoiHoc> list = new ArrayList<NguoiHoc>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				NguoiHoc nguoiHoc = new NguoiHoc();
				nguoiHoc.setMaNH(rs.getString("MaNH"));
				nguoiHoc.setHoTen(rs.getString("HoTen"));
				nguoiHoc.setNgaySinh(rs.getDate("NgaySinh"));
				nguoiHoc.setGioiTinh(rs.getBoolean("GioiTinh"));
				nguoiHoc.setDienThoai(rs.getString("DienThoai"));
				nguoiHoc.setEmail(rs.getString("Email"));
				nguoiHoc.setGhiChu(rs.getString("GhiChu"));
				nguoiHoc.setMaNV(rs.getString("MaNV"));
				nguoiHoc.setNgayDK(rs.getDate("NgayDK"));
				list.add(nguoiHoc);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<NguoiHoc> selectByKeyword(String key) {
		List<NguoiHoc> list = selectBySQL(SelectByKeyword_SQL, "%"+key+"%");
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
	
	public List<NguoiHoc> selectByKeyword_1(Integer maKH, String key) {
		List<NguoiHoc> list = selectBySQL(SelectByKeyword_SQL1, maKH, "%"+key+"%");
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

}
