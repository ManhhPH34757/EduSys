package com.edusys.ui;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.edusys.dao.NguoiHocDAO;
import com.edusys.entity.NguoiHoc;
import com.edusys.utils.Auth;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XDate;
import com.edusys.utils.XImage;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class QuanLyNguoiHocJDialog extends JDialog {
	private DefaultTableModel model;
	NguoiHocDAO dao = new NguoiHocDAO();
	private ButtonGroup buttonGroup;
	private int row = -1;
	XDate date = new XDate();
	private JTextField txtMaNH;
	private JTextField txtHoTen;
	private JTextField txtNgaySinh;
	private JTextField txtEmail;
	private JTextField txtDienThoai;
	private JTextField txtTimKiem;
	private JTextArea txtGhiChu;
	private JRadioButton rdoMale;
	private JRadioButton rdoFemale;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnNew;
	private JButton btnFirst;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnLast;
	private JScrollPane scrollPane_1;
	private JTable tblNguoiHoc;

	/**
	 * Create the dialog.
	 */
	public QuanLyNguoiHocJDialog() {
		setTitle("EduSys - Quản lý người học");
		setBounds(100, 100, 940, 635);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(new XImage().getAppIcon());
		
		JLabel lblTitle = new JLabel("QUẢN LÝ NGƯỜI HỌC");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setBounds(20, 10, 415, 35);
		getContentPane().add(lblTitle);
		
		JLabel lblMaNH = new JLabel("Mã người học");
		lblMaNH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMaNH.setBounds(21, 127, 253, 32);
		getContentPane().add(lblMaNH);
		
		txtMaNH = new JTextField();
		txtMaNH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaNH.setColumns(10);
		txtMaNH.setBounds(20, 169, 254, 32);
		getContentPane().add(txtMaNH);
		
		JLabel lblHoTen = new JLabel("Họ tên");
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHoTen.setBounds(298, 127, 254, 32);
		getContentPane().add(lblHoTen);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(298, 169, 254, 32);
		getContentPane().add(txtHoTen);
		
		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGioiTinh.setBounds(21, 211, 253, 32);
		getContentPane().add(lblGioiTinh);
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(298, 253, 254, 32);
		getContentPane().add(txtNgaySinh);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNgaySinh.setBounds(298, 211, 254, 32);
		getContentPane().add(lblNgaySinh);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(585, 253, 254, 32);
		getContentPane().add(txtEmail);
		
		JLabel lblEmail = new JLabel("Địa chỉ email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(585, 211, 254, 32);
		getContentPane().add(lblEmail);
		
		txtDienThoai = new JTextField();
		txtDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDienThoai.setColumns(10);
		txtDienThoai.setBounds(585, 169, 253, 32);
		getContentPane().add(txtDienThoai);
		
		JLabel lblDienThoai = new JLabel("Điện thoại");
		lblDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDienThoai.setBounds(585, 127, 194, 32);
		getContentPane().add(lblDienThoai);
		
		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdd.setBounds(21, 553, 105, 35);
		getContentPane().add(btnAdd);
		
		btnUpdate = new JButton("Sửa");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(152, 553, 105, 35);
		getContentPane().add(btnUpdate);
		
		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(279, 553, 105, 35);
		getContentPane().add(btnDelete);
		
		btnNew = new JButton("Mới");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNew.setBounds(407, 553, 105, 35);
		getContentPane().add(btnNew);
		
		btnFirst = new JButton("|<");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first();
			}
		});
		btnFirst.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFirst.setBounds(585, 553, 65, 35);
		getContentPane().add(btnFirst);
		
		btnPrev = new JButton("<<");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});
		btnPrev.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPrev.setBounds(660, 553, 65, 35);
		getContentPane().add(btnPrev);
		
		btnNext = new JButton(">>");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext.setBounds(735, 553, 65, 35);
		getContentPane().add(btnNext);
		
		btnLast = new JButton(">|");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				last();
			}
		});
		btnLast.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLast.setBounds(810, 553, 65, 35);
		getContentPane().add(btnLast);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 337, 881, 62);
		getContentPane().add(scrollPane);
		
		txtGhiChu = new JTextArea();
		txtGhiChu.setLineWrap(true);
		scrollPane.setViewportView(txtGhiChu);
		
		JLabel lblGhiChu = new JLabel("Ghi chú");
		lblGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGhiChu.setBounds(20, 295, 194, 32);
		getContentPane().add(lblGhiChu);
		
		rdoMale = new JRadioButton("Nam");
		rdoMale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdoMale.setBackground(Color.WHITE);
		rdoMale.setBounds(21, 249, 103, 36);
		getContentPane().add(rdoMale);
		
		rdoFemale = new JRadioButton("Nữ");
		rdoFemale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdoFemale.setBackground(Color.WHITE);
		rdoFemale.setBounds(171, 249, 103, 36);
		getContentPane().add(rdoFemale);
		
		JPanel panel = new JPanel();
		Border border = BorderFactory.createTitledBorder(null, "TÌM KIẾM", TitledBorder.ABOVE_TOP, TitledBorder.ABOVE_TOP, new Font("Tahoma", Font.BOLD, 13));
		panel.setBorder(border);
		panel.setBounds(20, 55, 881, 62);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(10, 22, 720, 32);
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimKiem.setColumns(10);
		panel.add(txtTimKiem);
		
		JButton btnFind = new JButton("Tìm kiếm");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findByName();
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFind.setBounds(756, 22, 115, 33);
		panel.add(btnFind);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdoFemale);
		buttonGroup.add(rdoMale);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 408, 881, 135);
		getContentPane().add(scrollPane_1);
		
		model = new DefaultTableModel();
		String[] cols = {"MÃ NH","HỌ VÀ TÊN","GIỚI TÍNH","NGÀY SINH","ĐIỆN THOẠI","EMAIL","MÃ NV","NGÀY ĐĂNG KÝ"};
		model.setColumnIdentifiers(cols);
		
		tblNguoiHoc = new JTable(model);
		tblNguoiHoc.setRowMargin(3);
		tblNguoiHoc.setRowHeight(25);
		tblNguoiHoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = tblNguoiHoc.getSelectedRow();
				if (row != -1) {
					edit();
				}else {
					MsgBox.alert(getContentPane(), "Vui lòng chọn chuyên đề");
				}
			}
		});
		tblNguoiHoc.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tblNguoiHoc);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		fillTable();
	}
	
	public void fillTable() {
		model.setRowCount(0);
		try {
			String keyword = txtTimKiem.getText();
			if (keyword == null) {
				keyword = "";
			}
			List<NguoiHoc> list = dao.selectByKeyword(keyword);
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
							list.get(i).getEmail(),
							list.get(i).getMaNV(),
							new XDate().toString(list.get(i).getNgayDK(), "MM/dd/yyyy"),
					};
					model.addRow(rows);
				}
			}
		} catch (Exception e) {
			System.out.println(e);	
		}
	}
	
	public void setForm(NguoiHoc nguoiHoc) {
		txtMaNH.setText(nguoiHoc.getMaNH());
		txtHoTen.setText(nguoiHoc.getHoTen());
		txtNgaySinh.setText(date.toString(nguoiHoc.getNgaySinh(), "MM/dd/yyyy"));
		txtDienThoai.setText(nguoiHoc.getDienThoai());
		txtEmail.setText(nguoiHoc.getEmail());
		txtGhiChu.setText(nguoiHoc.getGhiChu());
		rdoMale.setSelected(nguoiHoc.isGioiTinh());
		rdoFemale.setSelected(!nguoiHoc.isGioiTinh());
	}
	
	public NguoiHoc getForm() {
		NguoiHoc nguoiHoc = new NguoiHoc();
		nguoiHoc.setMaNH(txtMaNH.getText());
		nguoiHoc.setHoTen(txtHoTen.getText());
		nguoiHoc.setGioiTinh(rdoMale.isSelected());
		nguoiHoc.setNgaySinh(date.toDate(txtNgaySinh.getText(), "MM/dd/yyyy"));
		nguoiHoc.setDienThoai(txtDienThoai.getText());
		nguoiHoc.setEmail(txtEmail.getText());
		nguoiHoc.setGhiChu(txtGhiChu.getText());
		nguoiHoc.setMaNV(Auth.user.getMaNV());
		nguoiHoc.setNgayDK(date.toDate(new SimpleDateFormat("MM/dd/yyyy").format(new Date()), "MM/dd/yyyy"));
		return nguoiHoc;
	}
	
	public void clearForm() {
		NguoiHoc nguoiHoc = new NguoiHoc();
		setForm(nguoiHoc);
		txtTimKiem.setText(null);
		buttonGroup.clearSelection();
		row = -1;
		updateStatus();
	}
	
	public void updateStatus() {
		boolean edit = row >= 0;
		boolean first = row == 0;
		boolean end = row == tblNguoiHoc.getRowCount()-1;
		
		txtMaNH.setEnabled(!edit);
		btnAdd.setEnabled(!edit);
		btnUpdate.setEnabled(edit);
		btnDelete.setEnabled(edit);
		
		btnFirst.setEnabled(edit && !first);
		btnPrev.setEnabled(edit && !first);
		btnNext.setEnabled(edit && !end);
		btnLast.setEnabled(edit && !end);
	}
	
	public void edit() {
		String maNH = (String) tblNguoiHoc.getValueAt(row, 0);
		NguoiHoc nguoiHoc = dao.selectById(maNH);
		setForm(nguoiHoc);
		updateStatus();
	}
	
	public void findByName() {
		fillTable();
		clearForm();
		row = -1;
		updateStatus();
	}
	
	public void insert() {
		NguoiHoc nguoiHoc = getForm();
		try {
			dao.insert(nguoiHoc);
			fillTable();
			clearForm();
			MsgBox.alert(getContentPane(), "Thêm mới thành công");
		} catch (Exception e) {
			MsgBox.alert(getContentPane(), "Thêm mới thất bại!");
		}
	}
	
	public void update() {
		NguoiHoc nguoiHoc = getForm();
		try {
			dao.update(nguoiHoc);
			fillTable();
			clearForm();
			model.fireTableDataChanged();
			MsgBox.alert(getContentPane(), "Cập nhật thành công");
		} catch (Exception e) {
			MsgBox.alert(getContentPane(), "Cập nhật thất bại!");
		}
	}
	
	public void delete() {
		if (!Auth.isManager()) {
			MsgBox.alert(getContentPane(), "Bạn không có quyền xóa người học");
		}else {
			String maNH = txtMaNH.getText();
			if (MsgBox.confirm(getContentPane(), "Xác nhận xóa người học:")) {
				try {
					dao.delete(maNH);
					fillTable();
					clearForm();
					MsgBox.alert(getContentPane(), "Xóa thành công!");
				} catch (Exception e) {
					MsgBox.alert(getContentPane(), "Xóa thất bại!");
				}
			}
		}
	}
	
	public void first() {
		row = 0;
		edit();
	}
	
	public void next() {
		if (row < tblNguoiHoc.getRowCount()-1) {
			row++;
			edit();
		}
	}
	
	public void prev() {
		if (row > 0) {
			row --;
			edit();
		}
	}
	
	public void last() {
		row = tblNguoiHoc.getRowCount()-1;
		edit();
	}
	
	public static void main(String[] args) {
		new QuanLyNguoiHocJDialog().setVisible(true);
	}
	
}
