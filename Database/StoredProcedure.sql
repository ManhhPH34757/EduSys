/*Create Stored Procedure*/
USE EduSys
GO
/*1. SP_BangDiem( )*/
CREATE PROC [dbo].[SP_BangDiem](@MaKH INT)
AS
BEGIN
    SELECT
        NguoiHoc.MaNH AS [Mã người học],
        NguoiHoc.HoTen AS [Họ tên],
        HocVien.Diem AS [Điểm]
    FROM HocVien JOIN NguoiHoc ON NguoiHoc.MaNH = HocVien.MaNH
    WHERE HocVien.MaKH = @MaKH
    ORDER BY HocVien.Diem DESC
END
GO

/*2. SP_ThongKeDiem()*/
CREATE PROC [dbo].[SP_ThongKeDiem]
AS
BEGIN
    SELECT
        ChuyenDe.TenCD AS [Chuyên đề],
        COUNT(HocVien.MaHV) AS [Số học viên],
        MIN(HocVien.Diem) AS [Điểm thấp nhất],
        MAX(HocVien.Diem) AS [Điểm cao nhất],
        AVG(HocVien.Diem) AS [Điểm trung bình]
    FROM KhoaHoc
        JOIN HocVien ON KhoaHoc.MaKH = HocVien.MaKH
        JOIN ChuyenDe ON ChuyenDe.MaCD = KhoaHoc.MaCD
    GROUP BY TenCD
END
GO

/*3. SP_ThongKeDoanhThu()*/
CREATE PROC [dbo].[SP_ThongKeDoanhThu](@Year INT)
AS
BEGIN
    SELECT
        ChuyenDe.TenCD AS [Chuyên đề],
        COUNT(DISTINCT KhoaHoc.MaKH) AS [Số khóa học],
        COUNT(HocVien.MaHV) AS [Số học viên],
        SUM(KhoaHoc.HocPhi) AS [Doanh thu],
        MIN(KhoaHoc.HocPhi) AS [Học phí thấp nhất],
        MAX(KhoaHoc.HocPhi) AS [Học phí cao nhất],
        AVG(KhoaHoc.HocPhi) AS [Học phí trung bình]
    FROM KhoaHoc
        JOIN HocVien ON KhoaHoc.MaKH = HocVien.MaKH
        JOIN ChuyenDe ON ChuyenDe.MaCD = KhoaHoc.MaCD
    WHERE YEAR(KhoaHoc.NgayKG) = @YEAR
    GROUP BY TenCD
END
GO

/*4. SP_ThongKeNguoiHoc()*/
CREATE PROC [dbo].[SP_ThongKeNguoiHoc]
AS
BEGIN
    SELECT
        YEAR(NgayDK) AS [Năm],
        COUNT(*) AS [Số lượng người học],
        MIN(NgayDK) AS [Ngày đăng kí đầu tiên],
        MAX(NgayDK) AS [Ngày đăng kí cuối cùng]
    FROM NguoiHoc
    GROUP BY YEAR(NgayDK)
END
GO





