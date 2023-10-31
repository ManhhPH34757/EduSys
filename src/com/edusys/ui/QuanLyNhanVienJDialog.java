package com.edusys.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.edusys.dao.NhanVienDAO;
import com.edusys.entity.NhanVien;
import com.edusys.utils.Auth;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XImage;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.util.List;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class QuanLyNhanVienJDialog extends JDialog {
	private DefaultTableModel model;
	private ButtonGroup buttonGroup;
	NhanVienDAO dao = new NhanVienDAO();
	private int row = -1;
	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JPasswordField txtMatKhau;
	private JPasswordField txtMatKhau2;
	private JTable tblNhanVien;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnNew;
	private JButton btnLast;
	private JButton btnNext;
	private JButton btnPrev;
	private JButton btnFirst;
	private JRadioButton rdoTruongPhong;
	private JRadioButton rdoNhanVien;

	/**
	 * Create the dialog.
	 */
	public QuanLyNhanVienJDialog() {
		setTitle("EduSys - Quản lý nhân viên");
		setBounds(100, 100, 940, 635);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(new XImage().getAppIcon());

		JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN QUẢN TRỊ");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setBounds(20, 10, 415, 35);
		getContentPane().add(lblTitle);
		
		JLabel lblMaNV = new JLabel("Mã nhân viên");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMaNV.setBounds(47, 78, 194, 32);
		getContentPane().add(lblMaNV);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMatKhau.setBounds(47, 162, 194, 32);
		getContentPane().add(lblMatKhau);
		
		JLabel lblXacNhanMatKhau = new JLabel("Xác nhận mật khẩu");
		lblXacNhanMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblXacNhanMatKhau.setBounds(553, 78, 194, 32);
		getContentPane().add(lblXacNhanMatKhau);
		
		JLabel lblHoTen = new JLabel("Họ và tên");
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHoTen.setBounds(553, 162, 194, 32);
		getContentPane().add(lblHoTen);
		
		JLabel lblVaiTro = new JLabel("Vai trò");
		lblVaiTro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVaiTro.setBounds(47, 260, 194, 32);
		getContentPane().add(lblVaiTro);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(47, 120, 279, 32);
		getContentPane().add(txtMaNV);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(553, 204, 279, 32);
		getContentPane().add(txtHoTen);
		
		rdoTruongPhong = new JRadioButton("Trưởng phòng");
		rdoTruongPhong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdoTruongPhong.setBackground(Color.WHITE);
		rdoTruongPhong.setBounds(182, 260, 159, 35);
		getContentPane().add(rdoTruongPhong);
		
		rdoNhanVien = new JRadioButton("Nhân viên");
		rdoNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdoNhanVien.setBackground(Color.WHITE);
		rdoNhanVien.setBounds(440, 261, 159, 33);
		getContentPane().add(rdoNhanVien);
		
		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdd.setBounds(47, 519, 105, 35);
		getContentPane().add(btnAdd);
		
		btnUpdate = new JButton("Sửa");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(178, 519, 105, 35);
		getContentPane().add(btnUpdate);
		
		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(305, 519, 105, 35);
		getContentPane().add(btnDelete);
		
		btnNew = new JButton("Mới");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNew.setBounds(433, 519, 105, 35);
		getContentPane().add(btnNew);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMatKhau.setBounds(47, 204, 279, 32);
		getContentPane().add(txtMatKhau);
		
		txtMatKhau2 = new JPasswordField();
		txtMatKhau2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMatKhau2.setBounds(553, 120, 279, 32);
		getContentPane().add(txtMatKhau2);
		
		btnLast = new JButton(">|");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				last();
			}
		});
		btnLast.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLast.setBounds(813, 519, 65, 35);
		getContentPane().add(btnLast);
		
		btnNext = new JButton(">>");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext.setBounds(738, 519, 65, 35);
		getContentPane().add(btnNext);
		
		btnPrev = new JButton("<<");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});
		btnPrev.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPrev.setBounds(663, 519, 65, 35);
		getContentPane().add(btnPrev);
		
		btnFirst = new JButton("|<");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first();
			}
		});
		btnFirst.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFirst.setBounds(588, 519, 65, 35);
		getContentPane().add(btnFirst);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(47, 302, 828, 193);
		getContentPane().add(scrollPane_1);
		
		model = new DefaultTableModel();
		String[] cols = { "Mã NV", "Mật khẩu", "Họ tên", "Vai trò" };
		model.setColumnIdentifiers(cols);
		
		tblNhanVien = new JTable(model);
		tblNhanVien.setRowMargin(3);
		tblNhanVien.setRowHeight(25);
		tblNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = tblNhanVien.getSelectedRow();
				if (row != -1) {
					edit();
				}else {
					MsgBox.alert(getContentPane(), "Vui lòng chọn chuyên đề");
				}
			}
		});
		tblNhanVien.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tblNhanVien);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdoNhanVien);
		buttonGroup.add(rdoTruongPhong);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		if (!Auth.isManager()) {
			rdoTruongPhong.setEnabled(false);
		}
		
		fillTable();
	}
	
	public void fillTable() {
		model.setRowCount(0);
		try {
			List<NhanVien> list = dao.selectAll();
			for (int i = 0; i < list.size(); i++) {
				Object[] rows = new Object[] {
						list.get(i).getMaNV(),
						list.get(i).getMatKhau(),
						list.get(i).getHoTen(),
						list.get(i).isVaiTro() ? "Trưởng phòng" : "Nhân viên"
				};
				model.addRow(rows);
			}
		} catch (Exception e) {
			MsgBox.alert(getContentPane(), "Lỗi truy vấn");
		}
	}
	
	public void setForm(NhanVien nhanVien) {
		txtMaNV.setText(nhanVien.getMaNV());
		txtHoTen.setText(nhanVien.getHoTen());
		txtMatKhau.setText(nhanVien.getMatKhau());
		txtMatKhau2.setText(nhanVien.getMatKhau());
		rdoTruongPhong.setSelected(nhanVien.isVaiTro());
		rdoNhanVien.setSelected(!nhanVien.isVaiTro());
	}
	
	public NhanVien getForm() {
		NhanVien nhanVien = new NhanVien();
		nhanVien.setMaNV(txtMaNV.getText());
		nhanVien.setMatKhau(new String(txtMatKhau.getPassword()));
		nhanVien.setHoTen(txtHoTen.getText());
		nhanVien.setVaiTro(rdoTruongPhong.isSelected());
		return nhanVien;
	}
	
	public void edit() {
		String maNV = (String) tblNhanVien.getValueAt(row, 0);
		NhanVien nhanVien = dao.selectById(maNV);
		setForm(nhanVien);
		updateStatus();
	}
	
	public void updateStatus() {
		boolean edit = row >= 0;
		boolean first = row == 0;
		boolean end = row == tblNhanVien.getRowCount()-1;
		
		txtMaNV.setEnabled(!edit);
		btnAdd.setEnabled(!edit);
		btnUpdate.setEnabled(edit);
		btnDelete.setEnabled(edit);
		
		btnFirst.setEnabled(edit && !first);
		btnPrev.setEnabled(edit && !first);
		btnNext.setEnabled(edit && !end);
		btnLast.setEnabled(edit && !end);
		
	}
	
	public void clearForm() {
		NhanVien nhanVien = new NhanVien();
		setForm(nhanVien);
		buttonGroup.clearSelection();
		btnFirst.setEnabled(true);
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		btnLast.setEnabled(true);
	}
	
	public void insert() {
		NhanVien nhanVien = getForm();
		try {
			String txtConfirmPassword = new String(txtMatKhau2.getPassword());
			if (!nhanVien.getMatKhau().equals(txtConfirmPassword)) {
				MsgBox.alert(getContentPane(), "Xác nhận mật khẩu không trùng khớp!");
			}else {
				dao.insert(nhanVien);
				fillTable();
				clearForm();
				MsgBox.alert(getContentPane(), "Thêm mới thành công!");
			}
		} catch (Exception e) {
			MsgBox.alert(getContentPane(), "Thêm mới thất bại!");
		}
	}
	
	public void update() {
		NhanVien nhanVien = getForm();
		try {
			String txtConfirmPassword = new String(txtMatKhau2.getPassword());
			if (!nhanVien.getMatKhau().equals(txtConfirmPassword)) {
				MsgBox.alert(getContentPane(), "Xác nhận mật khẩu không trùng khớp!");
			}else {
				dao.update(nhanVien);
				fillTable();
				clearForm();
				MsgBox.alert(getContentPane(), " Cập nhật thành công!");
			}
		} catch (Exception e) {
			MsgBox.alert(getContentPane(), "Cập nhật thất bại!");
		}
	}
	
	public void delete() {
		try {
			if (!Auth.isManager()) {
				MsgBox.alert(getContentPane(), "Bạn không có quyền xóa!");
			}else {
				NhanVien nhanVien = getForm();
				if (Auth.user == nhanVien) {
					MsgBox.alert(getContentPane(), "Bạn không được xóa chính mình!");
				}else {
					try {
						dao.delete(nhanVien.getMaNV());
						fillTable();
						clearForm();
						MsgBox.alert(getContentPane(), "Xóa thành công!");
					} catch (Exception e) {
						MsgBox.alert(getContentPane(), "Xóa thất bại!");
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
		if (row < tblNhanVien.getRowCount()-1) {
			row++;
			edit();
		}
	}
	
	public void last() {
		row = tblNhanVien.getRowCount()-1;
		edit();
	}
	
	public static void main(String[] args) {
		new QuanLyNhanVienJDialog().setVisible(true);
	}
}
