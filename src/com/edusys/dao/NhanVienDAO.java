package com.edusys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edusys.entity.NhanVien;
import com.edusys.utils.XJdbc;

public class NhanVienDAO extends EduSysDAO<NhanVien, String>{
	
	String Insert_SQL = "INSERT INTO NhanVien(MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?)";
	String Update_SQL = "UPDATE NhanVien SET MatKhau = ?, HoTen = ?, VaiTro = ? WHERE MaNV = ?";
	String Delete_SQL = "DELETE FROM NhanVien WHERE MaNV = ?";
	String SelectAll_SQL = "SELECT * FROM NhanVien";
	String SelectById_SQL = "SELECT * FROM NhanVien WHERE MaNV = ?";

	@Override
	public void insert(NhanVien nhanVien) {
		try {
			XJdbc.update(Insert_SQL, nhanVien.getMaNV(), nhanVien.getMatKhau(), nhanVien.getHoTen(), nhanVien.isVaiTro());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void update(NhanVien nhanVien) {
		try {
			XJdbc.update(Update_SQL, nhanVien.getMatKhau(), nhanVien.getHoTen(), nhanVien.isVaiTro(), nhanVien.getMaNV());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void delete(String maNV) {
		try {
			XJdbc.update(Delete_SQL, maNV);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public NhanVien selectById(String maNV) {
		List<NhanVien> list = this.selectBySQL(SelectById_SQL, maNV);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<NhanVien> selectAll() {
		return this.selectBySQL(SelectAll_SQL);
	}

	@Override
	protected List<NhanVien> selectBySQL(String sql, Object... args) {
		List<NhanVien> list = new ArrayList<NhanVien>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien();
				nhanVien.setMaNV(rs.getString("MaNV"));
				nhanVien.setMatKhau(rs.getString("MatKhau"));
				nhanVien.setHoTen(rs.getString("HoTen"));
				nhanVien.setVaiTro(rs.getBoolean("VaiTro"));
				list.add(nhanVien);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
