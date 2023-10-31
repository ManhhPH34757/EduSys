package com.edusys.entity;

public class HocVien {
	private int maHV;
	private int maKH;
	private String maNH;
	private double diem;
	private String hoTen;
	
	public HocVien() {
		
	}

	public HocVien(int maHV, int maKH, String maNH, double diem) {
		this.maHV = maHV;
		this.maKH = maKH;
		this.maNH = maNH;
		this.diem = diem;
	}

	public HocVien(int maHV, String maNH, double diem, String hoTen) {
		this.maHV = maHV;
		this.maNH = maNH;
		this.diem = diem;
		this.hoTen = hoTen;
	}

	public HocVien(int maHV, int maKH, String maNH, double diem, String hoTen) {
		this.maHV = maHV;
		this.maKH = maKH;
		this.maNH = maNH;
		this.diem = diem;
		this.hoTen = hoTen;
	}

	public int getMaHV() {
		return maHV;
	}

	public void setMaHV(int maHV) {
		this.maHV = maHV;
	}

	public int getMaKH() {
		return maKH;
	}

	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}

	public String getMaNH() {
		return maNH;
	}

	public void setMaNH(String maNH) {
		this.maNH = maNH;
	}

	public double getDiem() {
		return diem;
	}

	public void setDiem(double diem) {
		this.diem = diem;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String xepLoai(double diem) {
		if (diem >= 9 && diem <= 10) {
			return "Xuất sắc";
		}
		if (diem < 9 && diem >= 8) {
			return "Giỏi";
		}
		if (diem < 8 && diem >= 6.5) {
			return "Khá";
		}
		if (diem < 6.5 && diem >= 5) {
			return "Trung bình";
		}
		if (diem < 5 && diem >= 3) {
			return "Yếu";
		}
		if (diem < 3 && diem >= 0) {
			return "Kém";
		}
		return null;
	}

	@Override
	public String toString() {
		return "HocVien [maHV=" + maHV + ", maKH=" + maKH + ", maNH=" + maNH + ", diem=" + diem + ", hoTen=" + hoTen
				+ "]";
	}
	
	
		
}
