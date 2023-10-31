package com.edusys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edusys.entity.ChuyenDe;
import com.edusys.utils.XJdbc;

public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {

	String Insert_SQL = "INSERT INTO ChuyenDe(MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
	String Update_SQL = "UPDATE ChuyenDe SET TenCD = ?, HocPhi = ?, ThoiLuong = ?, Hinh = ?, MoTa = ? WHERE MaCD = ?";
	String Delete_SQL = "DELETE FROM ChuyenDe WHERE MaCD = ?";
	String SelectAll_SQL = "SELECT * FROM ChuyenDe";
	String SelectById_SQL = "SELECT * FROM ChuyenDe WHERE MaCD = ?";
	String SelectTenCD_SQL = "SELECT TenCD FROM ChuyenDe";

	@Override
	public void insert(ChuyenDe chuyenDe) {
		try {
			XJdbc.update(Insert_SQL, chuyenDe.getMaCD(), chuyenDe.getTenCD(), chuyenDe.getHocPhi(),
					chuyenDe.getThoiLuong(), chuyenDe.getHinh(), chuyenDe.getMoTa());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void update(ChuyenDe chuyenDe) {
		try {
			XJdbc.update(Update_SQL, chuyenDe.getTenCD(), chuyenDe.getHocPhi(), chuyenDe.getThoiLuong(),
					chuyenDe.getHinh(), chuyenDe.getMoTa(), chuyenDe.getMaCD());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void delete(String maCD) {
		try {
			XJdbc.update(Delete_SQL, maCD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public ChuyenDe selectById(String maCD) {
		List<ChuyenDe> list = this.selectBySQL(SelectById_SQL, maCD);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<ChuyenDe> selectAll() {
		return this.selectBySQL(SelectAll_SQL);
	}

	@Override
	protected List<ChuyenDe> selectBySQL(String sql, Object... args) {
		List<ChuyenDe> list = new ArrayList<ChuyenDe>();
		try {
			ResultSet rs = XJdbc.query(sql, args);
			while (rs.next()) {
				ChuyenDe chuyenDe = new ChuyenDe();
				chuyenDe.setMaCD(rs.getString("MaCD"));
				chuyenDe.setTenCD(rs.getString("TenCD"));
				chuyenDe.setHocPhi(rs.getDouble("HocPhi"));
				chuyenDe.setThoiLuong(rs.getInt("ThoiLuong"));
				chuyenDe.setHinh(rs.getString("Hinh"));
				chuyenDe.setMoTa(rs.getString("MoTa"));
				list.add(chuyenDe);
			}
			rs.getStatement().getConnection().close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public HashMap<String, String> getMaCDByTenCD() {
		Map<String, String> cboTenChuyenDe = new HashMap<>();
		List<ChuyenDe> list = selectAll();
		for (ChuyenDe chuyenDe : list) {
			cboTenChuyenDe.put(chuyenDe.getTenCD(), chuyenDe.getMaCD());
		}
		return (HashMap<String, String>) cboTenChuyenDe;
	}
	
	public String getKeyByValue(HashMap<String, String> map, String value) {
        for (java.util.Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
	
	public List<String> getTenCD() {
		List<String> cboTenCD = new ArrayList<>();
		try {
			ResultSet rs = XJdbc.query(SelectTenCD_SQL);
			while (rs.next()) {
				cboTenCD.add(rs.getString("TenCD"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cboTenCD;
	}

}
