package com.edusys.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.edusys.dao.ChuyenDeDAO;
import com.edusys.dao.KhoaHocDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.entity.KhoaHoc;
import com.edusys.utils.Auth;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XDate;
import com.edusys.utils.XImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class QuanLyKhoaHocJDialog extends JDialog {
	private DefaultTableModel model;
	KhoaHocDAO dao = new KhoaHocDAO();
	ChuyenDeDAO CDdao = new ChuyenDeDAO();
	private int row = -1;
	private JComboBox<String> cboChuyenDe;
	private JTextField txtTenCD;
	private JTextField txtNgayKG;
	private JTextField txtHocPhi;
	private JTextField txtThoiLuong;
	private JTextField txtMaNV;
	private JTextField txtNgayTao;
	private JTable tblKhoaHoc;
	private JTextArea txtGhiChu;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnNew;
	private JButton btnFirst;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnLast;

	/**
	 * Create the dialog.
	 */
	public QuanLyKhoaHocJDialog() {
		setTitle("EduSys - Quản lý khóa học");
		setBounds(100, 100, 940, 635);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(new XImage().getAppIcon());
		
		JLabel lblQ = new JLabel("CHUYÊN ĐỀ");
		lblQ.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblQ.setBounds(20, 10, 438, 34);
		getContentPane().add(lblQ);
		
		cboChuyenDe = new JComboBox<String>();
		cboChuyenDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboChuyenDe.setModel(new DefaultComboBoxModel<String>(setCboChuyenDe()));
		cboChuyenDe.setSelectedIndex(-1);
		cboChuyenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chonChuyenDe();
			}
		});
		cboChuyenDe.setBounds(20, 54, 896, 28);
		getContentPane().add(cboChuyenDe);
		
		JLabel lblChuyenDe = new JLabel("Chuyên đề");
		lblChuyenDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChuyenDe.setBounds(30, 92, 260, 26);
		getContentPane().add(lblChuyenDe);
		
		txtTenCD = new JTextField();
		txtTenCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenCD.setColumns(10);
		txtTenCD.setBounds(30, 128, 260, 26);
		getContentPane().add(txtTenCD);
		
		txtNgayKG = new JTextField();
		txtNgayKG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgayKG.setColumns(10);
		txtNgayKG.setBounds(342, 128, 260, 26);
		getContentPane().add(txtNgayKG);
		
		JLabel lblKhaiGiang = new JLabel("Khai giảng");
		lblKhaiGiang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKhaiGiang.setBounds(342, 92, 260, 26);
		getContentPane().add(lblKhaiGiang);
		
		JLabel lblHocPhi = new JLabel("Học phí");
		lblHocPhi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHocPhi.setBounds(30, 164, 260, 26);
		getContentPane().add(lblHocPhi);
		
		txtHocPhi = new JTextField();
		txtHocPhi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHocPhi.setColumns(10);
		txtHocPhi.setBounds(30, 200, 260, 26);
		getContentPane().add(txtHocPhi);
		
		JLabel lblThoiLuong = new JLabel("Thời lượng (giờ)");
		lblThoiLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblThoiLuong.setBounds(342, 164, 260, 26);
		getContentPane().add(lblThoiLuong);
		
		txtThoiLuong = new JTextField();
		txtThoiLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtThoiLuong.setColumns(10);
		txtThoiLuong.setBounds(342, 200, 260, 26);
		getContentPane().add(txtThoiLuong);
		
		JLabel lblNguoiTao = new JLabel("Người tạo");
		lblNguoiTao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNguoiTao.setBounds(656, 92, 260, 26);
		getContentPane().add(lblNguoiTao);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(656, 128, 260, 26);
		getContentPane().add(txtMaNV);
		
		JLabel lblNgayTao = new JLabel("Ngày tạo");
		lblNgayTao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgayTao.setBounds(656, 164, 260, 26);
		getContentPane().add(lblNgayTao);
		
		txtNgayTao = new JTextField();
		txtNgayTao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgayTao.setColumns(10);
		txtNgayTao.setBounds(656, 200, 260, 26);
		getContentPane().add(txtNgayTao);
		
		JLabel lblGhiChu = new JLabel("Ghi chú");
		lblGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGhiChu.setBounds(30, 236, 886, 26);
		getContentPane().add(lblGhiChu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 272, 885, 70);
		getContentPane().add(scrollPane);
		
		txtGhiChu = new JTextArea();
		txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(txtGhiChu);
		
		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdd.setBounds(30, 553, 105, 35);
		getContentPane().add(btnAdd);
		
		btnUpdate = new JButton("Sửa");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(161, 553, 105, 35);
		getContentPane().add(btnUpdate);
		
		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(288, 553, 105, 35);
		getContentPane().add(btnDelete);
		
		btnNew = new JButton("Mới");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
				cboChuyenDe.setSelectedIndex(-1);
				fillTable();
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNew.setBounds(416, 553, 105, 35);
		getContentPane().add(btnNew);
		
		btnFirst = new JButton("|<");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first();
			}
		});
		btnFirst.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFirst.setBounds(594, 553, 65, 35);
		getContentPane().add(btnFirst);
		
		btnPrev = new JButton("<<");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});
		btnPrev.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPrev.setBounds(669, 553, 65, 35);
		getContentPane().add(btnPrev);
		
		btnNext = new JButton(">>");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext.setBounds(744, 553, 65, 35);
		getContentPane().add(btnNext);
		
		btnLast = new JButton(">|");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				last();
			}
		});
		btnLast.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLast.setBounds(819, 553, 65, 35);
		getContentPane().add(btnLast);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 352, 886, 190);
		getContentPane().add(scrollPane_1);
		
		model = new DefaultTableModel();
		String[] cols = {"MÃ KH","THỜI LƯỢNG","HỌC PHÍ","KHẢI GIẢNG","TẠO BỞI","NGÀY TẠO"};
		model.setColumnIdentifiers(cols);
		
		tblKhoaHoc = new JTable(model);
		tblKhoaHoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = tblKhoaHoc.getSelectedRow();
				if (row != -1) {
					edit();
				}else {
					MsgBox.alert(getContentPane(), "Vui lòng chọn chuyên đề");
				}
			}
		});
		tblKhoaHoc.setRowMargin(3);
		tblKhoaHoc.setRowHeight(25);
		tblKhoaHoc.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tblKhoaHoc);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		fillTable();
	}
	
	public void fillTable() {
		model.setRowCount(0);
		try {
			List<KhoaHoc> list = null;
			if (cboChuyenDe.getSelectedIndex() != -1) {
				ChuyenDe chuyenDe = CDdao.selectById(CDdao.getMaCDByTenCD().get(cboChuyenDe.getSelectedItem()));
				list = dao.selectByChuyenDe(chuyenDe.getMaCD());
			}else {
				list = dao.selectAll();
			}
			for (KhoaHoc khoaHoc : list) {
				Object[] rows = new Object[] {
					khoaHoc.getMaKH(),
					khoaHoc.getThoiLuong(),
					khoaHoc.getHocPhi(),
					new XDate().toString(khoaHoc.getNgayKG(), "MM/dd/yyyy"),
					khoaHoc.getMaNV(),
					new XDate().toString(khoaHoc.getNgayTao(), "MM/dd/yyyy")
				};
				model.addRow(rows);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public String[] setCboChuyenDe() {
		String[] cboChuyenDe = new String[CDdao.selectAll().size()];
		for (int i = 0; i < cboChuyenDe.length; i++) {
			cboChuyenDe[i] = CDdao.getTenCD().get(i);
		}
		return cboChuyenDe;
	}
	
	public void chonChuyenDe() {
		if (cboChuyenDe.getSelectedIndex() != -1) {
			ChuyenDe chuyenDe = CDdao.selectById(CDdao.getMaCDByTenCD().get(cboChuyenDe.getSelectedItem()));
			clearForm();
			txtHocPhi.setText(chuyenDe.getHocPhi()+"");
			txtThoiLuong.setText(chuyenDe.getThoiLuong()+"");
			txtTenCD.setText(chuyenDe.getTenCD());
		}else {
			clearForm();
		}
		fillTable();		

	}
	
	public KhoaHoc getForm() {
		KhoaHoc khoaHoc = new KhoaHoc();
		khoaHoc.setMaCD(CDdao.getMaCDByTenCD().get(txtTenCD.getText()));
		khoaHoc.setHocPhi(Double.valueOf(txtHocPhi.getText()));
		khoaHoc.setThoiLuong(Integer.valueOf(txtThoiLuong.getText()));
		khoaHoc.setNgayKG(new XDate().toDate(txtNgayKG.getText(), "MM/dd/yyyy"));
		khoaHoc.setGhiChu(txtGhiChu.getText());
		khoaHoc.setMaNV(Auth.user.getMaNV());
		khoaHoc.setNgayTao(new XDate().toDate(new XDate().toString(new Date(), "MM/dd/yyyy"), "MM/dd/yyyy"));
		return khoaHoc;
	}
	
	public void setForm(KhoaHoc khoaHoc) {
		txtTenCD.setText(CDdao.getKeyByValue(CDdao.getMaCDByTenCD(), khoaHoc.getMaCD()));
		txtNgayKG.setText(new XDate().toString(khoaHoc.getNgayKG(),"MM/dd/yyyy"));
		txtMaNV.setText(khoaHoc.getMaNV());
		txtThoiLuong.setText(khoaHoc.getThoiLuong()+"");
		txtNgayTao.setText(new XDate().toString(khoaHoc.getNgayTao(),"MM/dd/yyyy"));
		txtHocPhi.setText(khoaHoc.getHocPhi()+"");
		txtGhiChu.setText(khoaHoc.getGhiChu());
		txtHocPhi.setEnabled(false);
		txtThoiLuong.setEnabled(false);
		txtMaNV.setEnabled(false);
		txtNgayTao.setEnabled(false);
	}
	
	public void clearForm() {
		KhoaHoc khoaHoc = new KhoaHoc();
		setForm(khoaHoc);
		row = -1;
		btnFirst.setEnabled(true);
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		btnLast.setEnabled(true);
	}
	
	public void updateStatus() {
		boolean edit = row >= 0;
		boolean first = row == 0;
		boolean end = row == tblKhoaHoc.getRowCount()-1;
		
		txtMaNV.setEnabled(!edit);
		btnAdd.setEnabled(!edit);
		btnUpdate.setEnabled(edit);
		btnDelete.setEnabled(edit);
		
		btnFirst.setEnabled(edit && !first);
		btnPrev.setEnabled(edit && !first);
		btnNext.setEnabled(edit && !end);
		btnLast.setEnabled(edit && !end);
	}
	
	public void edit() {
		int maKH = (Integer) tblKhoaHoc.getValueAt(row, 0);
		KhoaHoc khoaHoc = dao.selectById(maKH);
		setForm(khoaHoc);
		updateStatus();
	}
	
	public void first() {
		row = 0;
		edit();
	}
	
	public void prev() {
		if (row > 0) {
			row --;
			edit();
		}
	}
	
	public void next() {
		if (row < tblKhoaHoc.getRowCount()-1) {
			row++;
			edit();
		}
	}
	
	public void last() {
		row = tblKhoaHoc.getRowCount()-1;
		edit();
	}
	
	public void insert() {
		KhoaHoc khoaHoc = getForm();
		try {
			dao.insert(khoaHoc);
			fillTable();
			clearForm();
			MsgBox.alert(getContentPane(), "Thêm mới thành công");
		} catch (Exception e) {
			MsgBox.alert(getContentPane(), "Thêm mới thất bại");
		}
	}
	
	public void update() {
		KhoaHoc khoaHoc = getForm();
		khoaHoc.setMaKH((int) tblKhoaHoc.getValueAt(row, 0));
		try {
			dao.update(khoaHoc);
			fillTable();
			clearForm();
			MsgBox.alert(getContentPane(), "Cập nhật thành công");
		} catch (Exception e) {
			MsgBox.alert(getContentPane(), "Cập nhật thất bại");
		}
	}
	
	public void delete() {
		if (!Auth.isManager()) {
			MsgBox.alert(getContentPane(), "Bạn không có quyền xóa khóa học!");
		}else {
			int maKH = (Integer) tblKhoaHoc.getValueAt(row, 0);
			if (MsgBox.confirm(getContentPane(), "Xác nhận xóa khóa học:")) {
				try {
					dao.delete(maKH);
					fillTable();
					clearForm();
					MsgBox.alert(getContentPane(), "Xóa thành công!");
				} catch (Exception e) {
					MsgBox.alert(getContentPane(), "Xóa thất bại!");
				}
			}
		}
	}
	
}
