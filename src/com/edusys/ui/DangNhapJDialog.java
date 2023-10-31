package com.edusys.ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.edusys.dao.NhanVienDAO;
import com.edusys.entity.NhanVien;
import com.edusys.utils.Auth;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XImage;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DangNhapJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMaNV;
	private JPasswordField txtMatKhau;
	private NhanVienDAO dao = new NhanVienDAO();

	/**
	 * Create the dialog.
	 */
	public DangNhapJDialog() {
		
	}

	public DangNhapJDialog(EduSysJFrame owner, boolean modal) {
		super(owner, modal);
		setTitle("EduSys - Đăng nhập");
		setBounds(100, 100, 700, 390);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(259, 0, 427, 275);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblMaNV = new JLabel("Tên đăng nhập");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMaNV.setBounds(20, 55, 165, 34);
		contentPanel.add(lblMaNV);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMatKhau.setBounds(20, 153, 165, 34);
		contentPanel.add(lblMatKhau);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaNV.setBounds(20, 102, 334, 34);
		contentPanel.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMatKhau.setBounds(20, 197, 334, 34);
		contentPanel.add(txtMatKhau);
		setIconImage(new XImage().getAppIcon());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(281, 274, 352, 79);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton btnDangNhap = new JButton("Đăng nhập");
				btnDangNhap.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String maNV = txtMaNV.getText();
						String password = new String(txtMatKhau.getPassword());
						NhanVien nhanVien = dao.selectById(maNV);
						if (nhanVien == null) {
							MsgBox.alert(getContentPane(),"Sai tên đăng nhập");
						}else {
							if (!password.equals(nhanVien.getMatKhau())) {
								MsgBox.alert(getContentPane(),"Sai mật khẩu");
							}else {
								Auth.user = nhanVien;
								dispose();
							}
						}
					}
				});
				btnDangNhap.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnDangNhap.setHorizontalTextPosition(SwingConstants.CENTER);
				btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnDangNhap.setIcon(new ImageIcon(DangNhapJDialog.class.getResource("/com/edusys/icon/Open lock.png")));
				btnDangNhap.setBounds(27, 10, 125, 59);
				btnDangNhap.setActionCommand("OK");
				buttonPane.add(btnDangNhap);
				getRootPane().setDefaultButton(btnDangNhap);
			}
			{
				JButton btnKetThuc = new JButton("Kết thúc");
				btnKetThuc.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (MsgBox.confirm(getContentPane(), "Bạn muốn kết thúc ứng dụng?")) {
							System.exit(0);
						}
						
					}
				});
				btnKetThuc.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnKetThuc.setHorizontalTextPosition(SwingConstants.CENTER);
				btnKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnKetThuc.setIcon(new ImageIcon(DangNhapJDialog.class.getResource("/com/edusys/icon/Log out.png")));
				btnKetThuc.setBounds(188, 10, 125, 59);
				btnKetThuc.setActionCommand("Cancel");
				buttonPane.add(btnKetThuc);
			}
		}
		
		JLabel lblImage = new JLabel("");
		lblImage.setBackground(new Color(255, 255, 255));
		lblImage.setOpaque(true);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(new ImageIcon(DangNhapJDialog.class.getResource("/com/edusys/icon/img_Login.png")));
		lblImage.setBounds(25, 29, 235, 298);
		getContentPane().add(lblImage);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
}
