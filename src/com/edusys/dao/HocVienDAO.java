package com.edusys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edusys.entity.HocVien;
import com.edusys.utils.XJdbc;

public class HocVienDAO extends EduSysDAO<HocVien, Integer>{

	String Insert_SQL = "INSERT INTO HocVien(MaKH, MaNH, Diem) VALUES (?, ?, ?)";
	String Update_SQL = "UPDATE HocVien SET MaKH = ?, MaNH = ?, Diem = ? WHERE MaHV = ?";
	String Delete_SQL = "DELETE FROM HocVien WHERE MaHV = ?";
	String SelectAll_SQL = "SELECT MaHV, HocVien.MaNH, HoTen, Diem FROM HocVien JOIN NguoiHoc ON NguoiHoc.MaNH = HocVien.MaNH";
	String SelectById_SQL = "SELECT MaHV, HocVien.MaNH, HoTen, MaKH, Diem FROM HocVien JOIN NguoiHoc ON NguoiHoc.MaNH = HocVien.MaNH WHERE MaHV = ?";
	
	@Override
	public void insert(HocVien hocVien) {
		try {
			XJdbc.update(Insert_SQL, hocVien.getMaKH(), hocVien.getMaNH(), hocVien.getDiem());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(HocVien hocVien) {
		try {
			XJdbc.update(Update_SQL, hocVien.getMaKH(), hocVien.getMaNH(), hocVien.getDiem(), hocVien.getMaHV());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Integer maHV) {
		try {
			XJdbc.update(Delete_SQL, maHV);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public HocVien selectById(Integer maHV) {
		List<HocVien> list = this.selectBySQL(SelectById_SQL, maHV);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<HocVien> selectAll() {
		return this.selectBySQL(SelectAll_SQL);
	}

	@Override
	protected List<HocVien> selectBySQL(String sql, Object... args) {
		List<HocVien> list = new ArrayList<HocVien>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				HocVien hocVien = new HocVien();
				hocVien.setMaHV(rs.getInt("MaHV"));
				hocVien.setMaKH(rs.getInt("MaKH"));
				hocVien.setMaNH(rs.getString("MaNH"));
				hocVien.setHoTen(rs.getString("HoTen"));
				hocVien.setDiem(rs.getDouble("Diem"));
				list.add(hocVien);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected List<HocVien> selectBySQL_1(String sql, Object... args) {
		List<HocVien> list = new ArrayList<HocVien>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				HocVien hocVien = new HocVien();
				hocVien.setMaHV(rs.getInt("MaHV"));
				hocVien.setMaNH(rs.getString("MaNH"));
				hocVien.setHoTen(rs.getString("HoTen"));
				hocVien.setDiem(rs.getDouble("Diem"));
				list.add(hocVien);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<HocVien> selectHocVien(int MaKH) {
		String selectHocVien = "SELECT MaHV, HocVien.MaNH, HoTen, Diem FROM HocVien JOIN NguoiHoc ON NguoiHoc.MaNH = HocVien.MaNH WHERE MaKH = ?";
		return this.selectBySQL_1(selectHocVien, MaKH);
	}

}
