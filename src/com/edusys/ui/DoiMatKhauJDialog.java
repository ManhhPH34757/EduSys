package com.edusys.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import com.edusys.dao.NhanVienDAO;
import com.edusys.utils.Auth;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XImage;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DoiMatKhauJDialog extends JDialog {
	private JTextField txtMaNV;
	private JPasswordField txtMatKhau;
	private JPasswordField txtMatKhau1;
	private JPasswordField txtMatKhau2;
	NhanVienDAO dao = new NhanVienDAO();

	/**
	 * Create the dialog.
	 */
	public DoiMatKhauJDialog() {
		setTitle("EduSys - Đổi mật khẩu");
		setBounds(100, 100, 720, 440);
		getContentPane().setLayout(null);
		setIconImage(new XImage().getAppIcon());
		setLocationRelativeTo(null);
		
		JLabel lblTitle = new JLabel("Đổi mật khẩu");
		lblTitle.setForeground(new Color(0, 104, 0));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblTitle.setBounds(10, 10, 200, 35);
		getContentPane().add(lblTitle);
		
		JLabel lblMaNV = new JLabel("Tên đăng nhập");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaNV.setBounds(38, 69, 240, 28);
		getContentPane().add(lblMaNV);
		
		JLabel MatKhau2 = new JLabel("Mật khẩu mới");
		MatKhau2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		MatKhau2.setBounds(38, 199, 240, 28);
		getContentPane().add(MatKhau2);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaNV.setBounds(38, 130, 240, 28);
		getContentPane().add(txtMaNV);
		txtMaNV.setColumns(10);
		
		JLabel lblMatKhau1 = new JLabel("Mật khẩu hiện tại");
		lblMatKhau1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMatKhau1.setBounds(417, 69, 240, 28);
		getContentPane().add(lblMatKhau1);
		
		JLabel lblMatKhauXN = new JLabel("Xác nhận mật khẩu mới");
		lblMatKhauXN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMatKhauXN.setBounds(417, 199, 240, 28);
		getContentPane().add(lblMatKhauXN);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMatKhau.setBounds(417, 130, 240, 28);
		getContentPane().add(txtMatKhau);
		
		txtMatKhau1 = new JPasswordField();
		txtMatKhau1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMatKhau1.setBounds(38, 259, 240, 28);
		getContentPane().add(txtMatKhau1);
		
		txtMatKhau2 = new JPasswordField();
		txtMatKhau2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMatKhau2.setBounds(417, 259, 240, 28);
		getContentPane().add(txtMatKhau2);
		
		JButton btnDongY = new JButton("Đồng ý");
		btnDongY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword();
			}
		});
		btnDongY.setIcon(new ImageIcon(DoiMatKhauJDialog.class.getResource("/com/edusys/icon/Accept.png")));
		btnDongY.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDongY.setBounds(358, 338, 124, 42);
		getContentPane().add(btnDongY);
		
		JButton btnHuyBo = new JButton("Hủy bỏ");
		btnHuyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnHuyBo.setIcon(new ImageIcon(DoiMatKhauJDialog.class.getResource("/com/edusys/icon/Stop.png")));
		btnHuyBo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHuyBo.setBounds(520, 338, 124, 42);
		getContentPane().add(btnHuyBo);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}
	
	public void changePassword() {
		String maNV = txtMaNV.getText();
		String matKhau = new String(txtMatKhau.getPassword());
		String matKhauMoi = new String(txtMatKhau1.getPassword());
		String matKhauMoi2 = new String(txtMatKhau2.getPassword());
		if (!maNV.equals(Auth.user.getMaNV())) {
			MsgBox.alert(getContentPane(), "Sai tên đăng nhập");
		}else {
			if (!matKhau.equals(Auth.user.getMatKhau())) {
				MsgBox.alert(getContentPane(), "Sai mật khẩu");
			}else {
				if (!matKhauMoi.equals(matKhauMoi2)) {
					MsgBox.alert(getContentPane(), "Xác nhận mật khẩu không chính xác");
				}else {
					Auth.user.setMatKhau(matKhauMoi);
					dao.update(Auth.user);
					MsgBox.alert(getContentPane(), "Đổi mật khẩu thành công!");
				}
			}
		}
	}
}
