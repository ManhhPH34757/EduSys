package com.edusys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edusys.entity.KhoaHoc;
import com.edusys.utils.XJdbc;

public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer>{

	String Insert_SQL = "INSERT INTO KhoaHoc(MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao) VALUES (?, ?, ?, ?, ?, ?, ?)";
	String Update_SQL = "UPDATE KhoaHoc SET MaCD = ?, HocPhi = ?, ThoiLuong = ?, NgayKG = ?, GhiChu = ?, MaNV = ?, NgayTao = ? WHERE MaKH = ?";
	String Delete_SQL = "DELETE FROM KhoaHoc WHERE MaKH = ?";
	String SelectAll_SQL = "SELECT * FROM KhoaHoc";
	String SelectNam_SQL = "SELECT DISTINCT YEAR(NGAYKG) AS [Năm] FROM KHOAHOC";
	String SelectById_SQL = "SELECT * FROM KhoaHoc WHERE MaKH = ?";
	String SelectByChuyenDe_SQL = "SELECT * FROM KhoaHoc WHERE MaCD = ?";
	
	@Override
	public void insert(KhoaHoc khoaHoc) {
		try {
			XJdbc.update(Insert_SQL, khoaHoc.getMaCD(), khoaHoc.getHocPhi(), khoaHoc.getThoiLuong(), khoaHoc.getNgayKG(), khoaHoc.getGhiChu(), khoaHoc.getMaNV(), khoaHoc.getNgayTao());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void update(KhoaHoc khoaHoc) {
		try {
			XJdbc.update(Update_SQL, khoaHoc.getMaCD(), khoaHoc.getHocPhi(), khoaHoc.getThoiLuong(), khoaHoc.getNgayKG(), khoaHoc.getGhiChu(), khoaHoc.getMaNV(), khoaHoc.getNgayTao(), khoaHoc.getMaKH());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Integer maKH) {
		try {
			XJdbc.update(Delete_SQL, maKH);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public KhoaHoc selectById(Integer maKH) {
		List<KhoaHoc> list = this.selectBySQL(SelectById_SQL, maKH);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public List<KhoaHoc> selectByChuyenDe(String maCD) {
		return this.selectBySQL(SelectByChuyenDe_SQL, maCD);
	}

	@Override
	public List<KhoaHoc> selectAll() {
		return this.selectBySQL(SelectAll_SQL);
	}

	@Override
	protected List<KhoaHoc> selectBySQL(String sql, Object... args) {
		List<KhoaHoc> list = new ArrayList<KhoaHoc>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				KhoaHoc khoaHoc = new KhoaHoc();
				khoaHoc.setMaKH(rs.getInt("MaKH"));
				khoaHoc.setMaCD(rs.getString("MaCD"));
				khoaHoc.setHocPhi(rs.getDouble("HocPhi"));
				khoaHoc.setThoiLuong(rs.getInt("ThoiLuong"));
				khoaHoc.setNgayKG(rs.getDate("NgayKG"));
				khoaHoc.setGhiChu(rs.getString("GhiChu"));
				khoaHoc.setMaNV(rs.getString("MaNV"));
				khoaHoc.setNgayTao(rs.getDate("NgayTao"));
				list.add(khoaHoc);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Integer> getNam() {
		List<Integer> list = new ArrayList<Integer>();
		try {
			ResultSet rs = XJdbc.query(SelectNam_SQL);
			while (rs.next()) {
				list.add(rs.getInt("Năm"));
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
