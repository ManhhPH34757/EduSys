package com.edusys.ui;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.edusys.dao.ChuyenDeDAO;
import com.edusys.dao.HocVienDAO;
import com.edusys.dao.KhoaHocDAO;
import com.edusys.dao.NguoiHocDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.entity.HocVien;
import com.edusys.entity.KhoaHoc;
import com.edusys.entity.NguoiHoc;
import com.edusys.utils.Auth;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XDate;
import com.edusys.utils.XImage;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class QuanLyHocVienJDialog extends JDialog {
	private JTable tblHocVien;
	private JTextField txtTimKiem;
	private JTable tblNguoiHoc;
	private JComboBox<KhoaHoc> cboKhoaHoc;
	private JComboBox<ChuyenDe> cboChuyenDe;
	private DefaultTableModel modelHocVien;
	private DefaultTableModel modelNguoiHoc;
	private HocVienDAO hocVienDAO = new HocVienDAO();
	private NguoiHocDAO nguoiHocDAO = new NguoiHocDAO();
	private JTabbedPane tabs;

	/**
	 * Launch the application.
	 */
	
	public QuanLyHocVienJDialog() {
		setTitle("Edusys - Quản lý học viên");
		setBounds(100, 100, 940, 635);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(new XImage().getAppIcon());
		
		JPanel panelCD = new JPanel();
		Border borderCD = BorderFactory.createTitledBorder(null, "Chuyên Đề", TitledBorder.ABOVE_TOP, TitledBorder.ABOVE_TOP, new Font("Tahoma", Font.BOLD, 16));
		panelCD.setBorder(borderCD);
		panelCD.setBounds(10, 29, 434, 79);
		getContentPane().add(panelCD);
		panelCD.setLayout(null);
		
		cboChuyenDe = new JComboBox<ChuyenDe>();
		cboChuyenDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fillCboChuyenDe();
		cboChuyenDe.setBounds(10, 33, 414, 32);
		panelCD.add(cboChuyenDe);
		
		JPanel panelKH = new JPanel();
		Border borderKH = BorderFactory.createTitledBorder(null, "Khóa học", TitledBorder.ABOVE_TOP, TitledBorder.ABOVE_TOP, new Font("Tahoma", Font.BOLD, 16));
		panelKH.setBorder(borderKH);
		panelKH.setBounds(482, 29, 434, 79);
		getContentPane().add(panelKH);
		panelKH.setLayout(null);
		
		cboKhoaHoc = new JComboBox<KhoaHoc>();
		cboKhoaHoc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboKhoaHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTableHocVien();
			}
		});
		cboChuyenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillCboKhoaHoc();
			}
		});
		cboKhoaHoc.setBounds(10, 33, 414, 32);
		panelKH.add(cboKhoaHoc);
		
		tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(10, 131, 906, 457);
		getContentPane().add(tabs);
		
		JLayeredPane layerHocVien = new JLayeredPane();
		tabs.addTab("HỌC VIÊN", null, layerHocVien, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 881, 348);
		layerHocVien.add(scrollPane);
		
		modelHocVien = new DefaultTableModel();
		String[] colsHV = {"TT","MÃ HV","MÃ NH","HỌ TÊN","ĐIỂM"};
		modelHocVien.setColumnIdentifiers(colsHV);
		
		tblHocVien = new JTable(modelHocVien);
		tblHocVien.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblHocVien);
		
		JButton btnDelete = new JButton("Xóa khỏi khóa học");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteHocVien();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(442, 378, 211, 42);
		layerHocVien.add(btnDelete);
		
		JButton btnUpdate = new JButton("Cập nhật điểm");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateDiem();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(680, 378, 211, 42);
		layerHocVien.add(btnUpdate);
		
		JLayeredPane layerNguoiHoc = new JLayeredPane();
		tabs.addTab("NGƯỜI HỌC", null, layerNguoiHoc, null);
		
		JPanel panelCD_1 = new JPanel();
		panelCD_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panelCD_1.setLayout(null);
		panelCD_1.setBounds(10, 10, 881, 79);
		layerNguoiHoc.add(panelCD_1);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimKiem.setBounds(10, 30, 707, 30);
		panelCD_1.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findByName();
			}
		});
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimKiem.setBounds(727, 30, 114, 30);
		panelCD_1.add(btnTimKiem);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 99, 881, 269);
		layerNguoiHoc.add(scrollPane_1);
		
		modelNguoiHoc = new DefaultTableModel();
		String [] colsNH = {"MÃ NH","HỌ VÀ TÊN","GIỚI TÍNH","NGÀY SINH","ĐIỆN THOẠI","EMAIL"};
		modelNguoiHoc.setColumnIdentifiers(colsNH);
		
		tblNguoiHoc = new JTable(modelNguoiHoc);
		tblNguoiHoc.setRowMargin(3);
		tblNguoiHoc.setRowHeight(25);
		tblNguoiHoc.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tblNguoiHoc);
		
		JButton btnAdd = new JButton("Thêm vào khóa học");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNguoiHoc();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(660, 378, 211, 42);
		layerNguoiHoc.add(btnAdd);
		
	}
	
	public void fillCboChuyenDe() {
		DefaultComboBoxModel<ChuyenDe> model = (DefaultComboBoxModel<ChuyenDe>) cboChuyenDe.getModel();
		model.removeAllElements();
		List<ChuyenDe> list = new ChuyenDeDAO().selectAll();
		for (ChuyenDe chuyenDe : list) {
			model.addElement(chuyenDe);
		}
	}
	
	public void fillCboKhoaHoc() {
		DefaultComboBoxModel<KhoaHoc> model = (DefaultComboBoxModel<KhoaHoc>) cboKhoaHoc.getModel();
		model.removeAllElements();
		ChuyenDe chuyenDe = (ChuyenDe) cboChuyenDe.getSelectedItem();
		if (chuyenDe != null) {
			List<KhoaHoc> list = new KhoaHocDAO().selectByChuyenDe(chuyenDe.getMaCD());
			for (KhoaHoc khoaHoc : list) {
				model.addElement(khoaHoc);
			}
			fillTableHocVien();
		}
	}
	
	public void fillTableHocVien() {
		modelHocVien.setRowCount(0);
		try {
			KhoaHoc khoaHoc = (KhoaHoc) cboKhoaHoc.getSelectedItem();
			if (khoaHoc != null) {
				List<HocVien> list = hocVienDAO.selectHocVien(khoaHoc.getMaKH());
				int i = 1;
				for (HocVien hocVien : list) {
					Object[] rows = new Object[] {
						i++,
						hocVien.getMaHV(),
						hocVien.getMaNH(),
						hocVien.getHoTen(),
						hocVien.getDiem()
					};
					modelHocVien.addRow(rows);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		fillTableNguoiHoc();
	}
	
	public void fillTableNguoiHoc() {
		modelNguoiHoc.setRowCount(0);
		try {
			KhoaHoc khoaHoc = (KhoaHoc) cboKhoaHoc.getSelectedItem();
			String keyword = txtTimKiem.getText();
			if (khoaHoc != null) {
				List<NguoiHoc> list = nguoiHocDAO.selectByKeyword_1(khoaHoc.getMaKH(), keyword);
				if (list == null) {
					MsgBox.alert(getContentPane(), "Not found");
				}else {
					for (int i = 0; i < list.size(); i++) {
						Object[] rows = new Object[] {
								list.get(i).getMaNH(),
								list.get(i).getHoTen(),
								list.get(i).isGioiTinh() ? "Nam" : "Nữ",
								new XDate().toString(list.get(i).getNgaySinh(), "MM/dd/yyyy"),
								list.get(i).getDienThoai(),
								list.get(i).getEmail()
						};
						modelNguoiHoc.addRow(rows);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);	
		}
	}
	
	public void findByName() {
		fillTableNguoiHoc();
	}
	
	public void addNguoiHoc() {
		KhoaHoc khoaHoc = (KhoaHoc) cboKhoaHoc.getSelectedItem();
		for (int row : tblNguoiHoc.getSelectedRows()) {
			HocVien hocVien = new HocVien();
			hocVien.setMaKH(khoaHoc.getMaKH());
			hocVien.setMaNH((String) tblNguoiHoc.getValueAt(row, 0));
			hocVien.setDiem(0);
			hocVienDAO.insert(hocVien);
		}
		fillTableHocVien();
		tabs.setSelectedIndex(0);
	}
	
	public void deleteHocVien() {
		if (!Auth.isManager()) {
			MsgBox.alert(getContentPane(), "Bạn không có quyền xóa học viên!");
		}else {
			if (MsgBox.confirm(getContentPane(), "Xác nhận xóa học viên:")) {
				for (int row : tblHocVien.getSelectedRows()) {
					hocVienDAO.delete((Integer) tblHocVien.getValueAt(row, 1));
				}
				fillTableHocVien();
			}
		}
	}
	
	public void updateDiem() {
		for (int i = 0; i < tblHocVien.getRowCount(); i++) {
			if (i < tblHocVien.getRowCount()) {
				HocVien hocVien = hocVienDAO.selectById((Integer) tblHocVien.getValueAt(i, 1));
				hocVien.setDiem((Double) tblHocVien.getValueAt(i, 4));
				System.out.println(hocVien);
				hocVienDAO.update(hocVien);
			}
		}
		fillTableHocVien();
		MsgBox.alert(getContentPane(), "Cập nhật thành công!");
	}
	
	public static void main(String[] args) {
		new QuanLyHocVienJDialog().setVisible(true);
	}
	
}
