package com.edusys.ui;

import javax.swing.JDialog;

import com.edusys.dao.KhoaHocDAO;
import com.edusys.dao.ThongKeDAO;
import com.edusys.entity.HocVien;
import com.edusys.entity.KhoaHoc;
import com.edusys.utils.XImage;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ThongKeJDialog extends JDialog {
	private JTable tblBangDiem;
	private JTable tblNguoiHoc;
	private JTable tblDiemChuyenDe;
	private JTable tblDoanhThu;
	private ThongKeDAO thongKeDAO = new ThongKeDAO();
	private JComboBox<KhoaHoc> cboKhoaHoc;
	private JComboBox<Integer> cboNam;
	private DefaultTableModel modelBangDiem;
	private DefaultTableModel modelNguoiHoc;
	private DefaultTableModel modelDiemChuyenDe;
	private DefaultTableModel modelDoanhThu;
	public JTabbedPane tabs;

	/**
	 * Create the dialog.
	 */
	public ThongKeJDialog() {
		setTitle("EduSys - Tổng hợp & Thống kê");
		setBounds(100, 100, 940, 635);
		getContentPane().setLayout(null);
		
		JLabel lblTngHpThng = new JLabel("TỔNG HỢP THỐNG KÊ");
		lblTngHpThng.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTngHpThng.setBounds(20, 10, 415, 35);
		getContentPane().add(lblTngHpThng);
		
		tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(10, 55, 906, 542);
		getContentPane().add(tabs);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabs.addTab("BẢNG ĐIỂM", null, layeredPane, null);
		
		JLabel lblNewLabel = new JLabel("KHÓA HỌC :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(50, 10, 139, 30);
		layeredPane.add(lblNewLabel);
		
		cboKhoaHoc = new JComboBox<KhoaHoc>();
		cboKhoaHoc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboKhoaHoc.setBounds(228, 10, 587, 30);
		layeredPane.add(cboKhoaHoc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 881, 440);
		layeredPane.add(scrollPane);
		
		modelBangDiem = new DefaultTableModel();
		String [] colsBangdiem = {"MÃ NH","HỌ VÀ TÊN","ĐIỂM","XẾP LOẠI"};
		modelBangDiem.setColumnIdentifiers(colsBangdiem);
		
		tblBangDiem = new JTable(modelBangDiem);
		tblBangDiem.setRowMargin(3);
		tblBangDiem.setRowHeight(25);
		tblBangDiem.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblBangDiem);
		
		fillCboKhoaHoc();
		cboKhoaHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTableBangDiem();
			}
		});
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabs.addTab("NGƯỜI HỌC", null, layeredPane_1, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 881, 495);
		layeredPane_1.add(scrollPane_1);
		
		modelNguoiHoc = new DefaultTableModel();
		String [] colsNguoiHoc = {"NĂM","SỐ NGƯỜI HỌC","ĐĂNG KÍ SỚM NHẤT","ĐĂNG KÍ MUỘN NHẤT"};
		modelNguoiHoc.setColumnIdentifiers(colsNguoiHoc);
		
		tblNguoiHoc = new JTable(modelNguoiHoc);
		tblNguoiHoc.setRowMargin(3);
		tblNguoiHoc.setRowHeight(25);
		tblNguoiHoc.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tblNguoiHoc);
		fillTableNguoiHoc();
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabs.addTab("ĐIỂM CHUYÊN ĐỀ", null, layeredPane_2, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 10, 881, 495);
		layeredPane_2.add(scrollPane_2);
		
		modelDiemChuyenDe = new DefaultTableModel();
		String [] colsDiemCD = {"CHUYÊN ĐỀ","SL HỌC VIÊN","ĐIỂM TN","ĐIỂM CN","ĐIỂM TB"};
		modelDiemChuyenDe.setColumnIdentifiers(colsDiemCD);
		
		tblDiemChuyenDe = new JTable(modelDiemChuyenDe);
		tblDiemChuyenDe.setFillsViewportHeight(true);
		scrollPane_2.setViewportView(tblDiemChuyenDe);
		fillTableDiemChuyenDe();
		
		JLayeredPane layeredPane_3 = new JLayeredPane();
		tabs.addTab("DOANH THU", null, layeredPane_3, null);
		
		JLabel lblNewLabel_1 = new JLabel("NĂM :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(90, 10, 99, 30);
		layeredPane_3.add(lblNewLabel_1);
		
		cboNam = new JComboBox<Integer>();
		cboNam.setBounds(199, 10, 602, 30);
		layeredPane_3.add(cboNam);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 65, 881, 440);
		layeredPane_3.add(scrollPane_3);
		
		modelDoanhThu = new DefaultTableModel();
		String [] colsDoanhThu = {"CHUYÊN ĐỀ","SỐ KHÓA HỌC","SỐ HỌC VIÊN","DOANH THU","HỌC PHÍ TN","HỌC PHÍ CN","HỌC PHÍ TB"};
		modelDoanhThu.setColumnIdentifiers(colsDoanhThu);
		
		tblDoanhThu = new JTable(modelDoanhThu);
		tblDoanhThu.setFillsViewportHeight(true);
		tblDoanhThu.setRowMargin(3);
		tblDoanhThu.setRowHeight(25);
		scrollPane_3.setViewportView(tblDoanhThu);
		fillCboNam();
		cboNam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTableDoanhThu();
			}
		});
		
		setLocationRelativeTo(null);
		setIconImage(new XImage().getAppIcon());

	}
	
	public void fillCboKhoaHoc() {
		DefaultComboBoxModel<KhoaHoc> model = (DefaultComboBoxModel<KhoaHoc>) cboKhoaHoc.getModel();
		model.removeAllElements();
		List<KhoaHoc> list = new KhoaHocDAO().selectAll();
		for (KhoaHoc khoaHoc : list) {
			model.addElement(khoaHoc);
		}
		fillTableBangDiem();
	}
	
	public void fillTableBangDiem() {
		modelBangDiem.setRowCount(0);
		try {
			KhoaHoc khoaHoc = (KhoaHoc) cboKhoaHoc.getSelectedItem();
			if (khoaHoc != null) {
				List<Object[]> listBangDiem = thongKeDAO.getBangDiem(khoaHoc.getMaKH());
				for (Object[] objects : listBangDiem) {
					Object[] rows = new Object[] {
						objects[0],
						objects[1],
						objects[2],
						new HocVien().xepLoai((double) objects[2])
					};
					modelBangDiem.addRow(rows);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void fillTableNguoiHoc() {
		modelNguoiHoc.setRowCount(0);
		try {
			List<Object[]> listNguoiHoc = thongKeDAO.getLuongNguoiHoc();
			for (Object[] objects : listNguoiHoc) {
				Object[] rows = new Object[] {
					objects[0],
					objects[1],
					objects[2],
					objects[3]
				};
				modelNguoiHoc.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void fillTableDiemChuyenDe() {
		modelDiemChuyenDe.setRowCount(0);
		try {
			List<Object[]> listDiemChuyenDiem = thongKeDAO.getDiemChuyeDe();
			for (Object[] objects : listDiemChuyenDiem) {
				Object[] rows = new Object[] {
					objects[0],
					objects[1],
					objects[2],
					objects[3],
					objects[4]
				};
				modelDiemChuyenDe.addRow(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void fillCboNam() {
		DefaultComboBoxModel<Integer> model = (DefaultComboBoxModel<Integer>) cboNam.getModel();
		model.removeAllElements();
		List<Integer> list = new KhoaHocDAO().getNam();
		for (Integer nam : list) {
			model.addElement(nam);
		}
		fillTableDoanhThu();
	}
	
	public void fillTableDoanhThu() {
		modelDoanhThu.setRowCount(0);
		try {
			int nam = (int) cboNam.getSelectedItem();
			if (nam != 0) {
				List<Object[]> listDoanhThu = thongKeDAO.getDoanhThu(nam);
				for (Object[] objects : listDoanhThu) {
					Object[] rows = new Object[] {
						objects[0],
						objects[1],
						objects[2],
						objects[3],
						objects[4],
						objects[5],
						objects[6]
					};
					modelDoanhThu.addRow(rows);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
