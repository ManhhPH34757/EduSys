package com.edusys.ui;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.edusys.dao.ChuyenDeDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.utils.Auth;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XImage;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.io.File;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class QuanLyChuyenDeJDialog extends JDialog {
	private DefaultTableModel model;
	ChuyenDeDAO dao = new ChuyenDeDAO();
	private int row = -1;
	private JLabel lblLogo;
	private JPanel panel;
	private JLabel lblAnh;
	private JLabel lblMaCD;
	private JTextField txtMaCD;
	private JLabel lblTenCD;
	private JTextField txtTenCD;
	private JLabel lblMoTaCD;
	private JScrollPane scrollPane;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnNew;
	private JButton btnFirst;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnLast;
	private JTextField txtHocPhi;
	private JTextField txtThoiLuong;
	private JTable tblChuyenDe;
	private JTextArea txtMoTaCD;

	/**
	 * Create the dialog.
	 */
	public QuanLyChuyenDeJDialog() {
		setTitle("EduSys - Quản lý chuyên đề");
		setBounds(100, 100, 940, 635);
		getContentPane().setLayout(null);
		setIconImage(new XImage().getAppIcon());
		setLocationRelativeTo(null);
		
		JLabel lblTitle = new JLabel("QUẢN LÝ CHUYÊN ĐỀ");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setBounds(10, 10, 438, 34);
		getContentPane().add(lblTitle);
		
		lblLogo = new JLabel("Hình logo");
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogo.setBounds(20, 48, 152, 25);
		getContentPane().add(lblLogo);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 128)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(30, 83, 195, 239);
		getContentPane().add(panel);
		
		lblAnh = new JLabel("");
		lblAnh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chooseImage();
			}
		});
		lblAnh.setOpaque(true);
		lblAnh.setBackground(Color.WHITE);
		lblAnh.setBounds(10, 10, 175, 219);
		panel.add(lblAnh);
		
		lblMaCD = new JLabel("Mã chuyên đề");
		lblMaCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaCD.setBounds(267, 48, 300, 35);
		getContentPane().add(lblMaCD);
		
		txtMaCD = new JTextField();
		txtMaCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaCD.setColumns(10);
		txtMaCD.setBounds(267, 93, 300, 35);
		getContentPane().add(txtMaCD);
		
		lblTenCD = new JLabel("Tên chuyên đề");
		lblTenCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenCD.setBounds(267, 138, 300, 35);
		getContentPane().add(lblTenCD);
		
		txtTenCD = new JTextField();
		txtTenCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenCD.setColumns(10);
		txtTenCD.setBounds(267, 183, 300, 35);
		getContentPane().add(txtTenCD);
		
		lblMoTaCD = new JLabel("Mô tả chuyên đề");
		lblMoTaCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMoTaCD.setBounds(269, 228, 298, 35);
		getContentPane().add(lblMoTaCD);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(267, 267, 627, 55);
		getContentPane().add(scrollPane);
		
		txtMoTaCD = new JTextArea();
		txtMoTaCD.setLineWrap(true);
		txtMoTaCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(txtMoTaCD);
		
		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdd.setBounds(40, 540, 105, 35);
		getContentPane().add(btnAdd);
		
		btnUpdate = new JButton("Sửa");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(171, 540, 105, 35);
		getContentPane().add(btnUpdate);
		
		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(298, 540, 105, 35);
		getContentPane().add(btnDelete);
		
		btnNew = new JButton("Mới");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNew.setBounds(426, 540, 105, 35);
		getContentPane().add(btnNew);
		
		btnFirst = new JButton("|<");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first();
			}
		});
		btnFirst.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFirst.setBounds(604, 540, 65, 35);
		getContentPane().add(btnFirst);
		
		btnPrev = new JButton("<<");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});
		btnPrev.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPrev.setBounds(679, 540, 65, 35);
		getContentPane().add(btnPrev);
		
		btnNext = new JButton(">>");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext.setBounds(754, 540, 65, 35);
		getContentPane().add(btnNext);
		
		btnLast = new JButton(">|");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				last();
			}
		});
		btnLast.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLast.setBounds(829, 540, 65, 35);
		getContentPane().add(btnLast);
		
		JLabel lblThoiLuong = new JLabel("Thời lượng (Giờ)");
		lblThoiLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblThoiLuong.setBounds(594, 48, 300, 35);
		getContentPane().add(lblThoiLuong);
		
		txtHocPhi = new JTextField();
		txtHocPhi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHocPhi.setColumns(10);
		txtHocPhi.setBounds(594, 183, 300, 35);
		getContentPane().add(txtHocPhi);
		
		txtThoiLuong = new JTextField();
		txtThoiLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtThoiLuong.setColumns(10);
		txtThoiLuong.setBounds(594, 93, 300, 35);
		getContentPane().add(txtThoiLuong);
		
		JLabel lblHocPhi = new JLabel("Học phí");
		lblHocPhi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHocPhi.setBounds(594, 138, 300, 35);
		getContentPane().add(lblHocPhi);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(29, 332, 865, 192);
		getContentPane().add(scrollPane_1);
		
		model = new DefaultTableModel();
		String[] cols = {"Mã CD","Tên CD","Học phí","Thời lượng","Hình"};
		model.setColumnIdentifiers(cols);
		
		tblChuyenDe = new JTable(model);
		tblChuyenDe.setRowMargin(3);
		tblChuyenDe.setRowHeight(25);
		tblChuyenDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = tblChuyenDe.getSelectedRow();
				if (row != -1) {
					edit();
				}else {
					MsgBox.alert(getContentPane(), "Vui lòng chọn chuyên đề");
				}
			}
		});
		tblChuyenDe.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tblChuyenDe);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		fillTable();
	}
	
	public void fillTable() {
		model.setRowCount(0);
		try {
			List<ChuyenDe> list = dao.selectAll();
			for (int i = 0; i < list.size(); i++) {
				Object[] rows = new Object[] {
					list.get(i).getMaCD(),
					list.get(i).getTenCD(),
					list.get(i).getHocPhi(),
					list.get(i).getThoiLuong(),
					list.get(i).getHinh()
				};
				model.addRow(rows);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void setForm(ChuyenDe chuyenDe) {
		txtMaCD.setText(chuyenDe.getMaCD());
		txtTenCD.setText(chuyenDe.getTenCD());
		txtThoiLuong.setText(String.valueOf(chuyenDe.getThoiLuong()));
		txtHocPhi.setText(String.valueOf(chuyenDe.getHocPhi()));
		txtMoTaCD.setText(chuyenDe.getMoTa());
		if (chuyenDe.getHinh()!= null) {
			lblAnh.setToolTipText(chuyenDe.getHinh());
			lblAnh.setIcon(XImage.read(chuyenDe.getHinh()));
		}
	}
	
	public ChuyenDe getForm() {
		ChuyenDe chuyenDe = new ChuyenDe();
		chuyenDe.setMaCD(txtMaCD.getText());
		chuyenDe.setTenCD(txtTenCD.getText());
		chuyenDe.setThoiLuong(Integer.valueOf(txtThoiLuong.getText()));
		chuyenDe.setHocPhi(Double.valueOf(txtHocPhi.getText()));
		chuyenDe.setMoTa(txtMoTaCD.getText());
		chuyenDe.setHinh(lblAnh.getToolTipText());
		return chuyenDe;
	}
	
	public void clearForm() {
		ChuyenDe chuyenDe = new ChuyenDe();
		setForm(chuyenDe);
		txtMaCD.setEnabled(true);
		lblAnh.setIcon(null);
		btnFirst.setEnabled(true);
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		btnLast.setEnabled(true);
	}
	
	public void insert() {
		ChuyenDe chuyenDe = getForm();
		try {
			dao.insert(chuyenDe);
			fillTable();
			clearForm();
			MsgBox.alert(getContentPane(), "Thêm mới thành công!");
		} catch (Exception e) {
			MsgBox.alert(getContentPane(), "Thêm mới thất bại!");
		}
	}
	
	public void update() {
		ChuyenDe chuyenDe = getForm();
		try {
			dao.update(chuyenDe);
			fillTable();
			clearForm();
			MsgBox.alert(getContentPane(), "Cập nhật thành công!");
		} catch (Exception e) {
			MsgBox.alert(getContentPane(), "Cập nhật thất bại!");
		}
	}
	
	public void delete() {
		if (!Auth.isManager()) {
			MsgBox.alert(getContentPane(), "Bạn không có quyền xóa chuyên đề!");
		}else {
			String maCD = txtMaCD.getText();
			if (MsgBox.confirm(getContentPane(), "Xác nhận xóa chuyên đề!")) {
				try {
					dao.delete(maCD);
					fillTable();
					clearForm();
					MsgBox.alert(getContentPane(), "Xóa thành công!");
				} catch (Exception e) {
					MsgBox.alert(getContentPane(), "Xóa thất bại!");
				}
			}
		}
	}
	
	public void edit() {
		String maCD = (String) tblChuyenDe.getValueAt(row, 0);
		ChuyenDe chuyenDe = dao.selectById(maCD);
		setForm(chuyenDe);
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
		if (row < tblChuyenDe.getRowCount()-1) {
			row++;
			edit();
		}
	}
	
	public void last() {
		row = tblChuyenDe.getRowCount()-1;
		edit();
	}
	
	public void updateStatus() {
		boolean edit = row >= 0;
		boolean first = row == 0;
		boolean end = row == tblChuyenDe.getRowCount()-1;
		
		txtMaCD.setEnabled(!edit);
		btnAdd.setEnabled(!edit);
		btnUpdate.setEnabled(edit);
		btnDelete.setEnabled(edit);
		
		btnFirst.setEnabled(edit && !first);
		btnPrev.setEnabled(edit && !first);
		btnNext.setEnabled(edit && !end);
		btnLast.setEnabled(edit && !end);
	}
	
	public void chooseImage() {
		try {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				XImage.save(file);
				ImageIcon icon = XImage.read(file.getName());
				lblAnh.setIcon(icon);
				lblAnh.setToolTipText(file.getName());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public boolean valid() {
		if (txtMaCD.getText().trim().isEmpty()) {
			MsgBox.alert(getContentPane(), "Vui lòng nhập mã chuyên đề!");
			return false;
		}
		if (txtTenCD.getText().trim().isEmpty()) {
			MsgBox.alert(getContentPane(), "Vui lòng nhập mã chuyên đề!");
			return false;
		}
		if (txtMoTaCD.getText().trim().isEmpty()) {
			MsgBox.alert(getContentPane(), "Vui lòng nhập mã chuyên đề!");
			return false;
		}
		if (txtHocPhi.getText().trim().isEmpty()) {
			MsgBox.alert(getContentPane(), "Vui lòng nhập mã chuyên đề!");
			return false;
		}
		if (txtThoiLuong.getText().trim().isEmpty()) {
			MsgBox.alert(getContentPane(), "Vui lòng nhập mã chuyên đề!");
			return false;
		}
		return true;
		
	}
	
	public static void main(String[] args) {
		new QuanLyChuyenDeJDialog().setVisible(true);
	}
}
