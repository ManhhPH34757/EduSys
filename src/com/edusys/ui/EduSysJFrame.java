package com.edusys.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import com.edusys.utils.Auth;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XImage;

import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class EduSysJFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnKetThuc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					EduSysJFrame frame = new EduSysJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EduSysJFrame() {
		setTitle("EduSys - HỆ QUẢN LÝ ĐÀO TẠO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1075, 685);
		setLocationRelativeTo(null);
		setIconImage(new XImage().getAppIcon());
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setSize(new Dimension(5, 5));
		menuBar.setMaximumSize(new Dimension(0, 5));
		menuBar.setMargin(new Insets(5, 5, 0, 0));
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		setJMenuBar(menuBar);
		
		JMenu mnuHeThong = new JMenu("Hệ thống");
		menuBar.add(mnuHeThong);
		
		JMenuItem mniDangNhap = new JMenuItem("Đăng nhập");
		mniDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangXuat();
			}
		});
		mniDangNhap.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Open lock.png")));
		mniDangNhap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		mnuHeThong.add(mniDangNhap);
		
		JMenuItem mniDangXuat = new JMenuItem("Đăng xuất");
		mniDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangXuat();
			}
		});
		mniDangXuat.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Log out.png")));
		mniDangXuat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		mnuHeThong.add(mniDangXuat);
		
		JSeparator separator = new JSeparator();
		mnuHeThong.add(separator);
		
		JMenuItem mniDoiMatKhau = new JMenuItem("Đổi mật khẩu");
		mniDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openChangePassword();
			}
		});
		mniDoiMatKhau.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Refresh.png")));
		mnuHeThong.add(mniDoiMatKhau);
		
		JMenuItem mniKetThuc = new JMenuItem("Kết thúc");
		mniKetThuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mniKetThuc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Stop.png")));
		mniKetThuc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
		mnuHeThong.add(mniKetThuc);
		
		JMenu mnQuanLy = new JMenu("Quản lý");
		menuBar.add(mnQuanLy);
		
		JMenuItem mniChuyenDe = new JMenuItem("Chuyên đề");
		mniChuyenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openQLChuyenDe();
			}
		});
		mniChuyenDe.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Notes.png")));
		mniChuyenDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_DOWN_MASK));
		mnQuanLy.add(mniChuyenDe);
		
		JMenuItem mniKhoaHoc = new JMenuItem("Khóa học");
		mniKhoaHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openQLKhoaHoc();
			}
		});
		mniKhoaHoc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Address book.png")));
		mniKhoaHoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_DOWN_MASK));
		mnQuanLy.add(mniKhoaHoc);
		
		JMenuItem mniNguoiHoc = new JMenuItem("Người học");
		mniNguoiHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openQLNguoiHoc();
			}
		});
		mniNguoiHoc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Users.png")));
		mniNguoiHoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.CTRL_DOWN_MASK));
		mnQuanLy.add(mniNguoiHoc);
		
		JMenuItem mniHocVien = new JMenuItem("Học viên");
		mniHocVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openQLHocVien();
			}
		});
		mniHocVien.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/User.png")));
		mniHocVien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_DOWN_MASK));
		mnQuanLy.add(mniHocVien);
		
		JSeparator separator_1 = new JSeparator();
		mnQuanLy.add(separator_1);
		
		JMenuItem mniNhanVien = new JMenuItem("Nhân viên");
		mniNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openQLNhanVien();
			}
		});
		mniNhanVien.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/nhanvien.png")));
		mniNhanVien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.CTRL_DOWN_MASK));
		mnQuanLy.add(mniNhanVien);
		
		JMenu mnThongKe = new JMenu("Thống kê");
		menuBar.add(mnThongKe);
		
		JMenuItem mniBangDiem = new JMenuItem("Bảng điểm");
		mniBangDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openThongKe(0);
			}
		});
		mniBangDiem.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Numbered list.png")));
		mniBangDiem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.SHIFT_DOWN_MASK));
		mnThongKe.add(mniBangDiem);
		
		JSeparator separator_2 = new JSeparator();
		mnThongKe.add(separator_2);
		
		JMenuItem mniLuongNguoiHoc = new JMenuItem("Lượng người học");
		mniLuongNguoiHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openThongKe(1);
			}
		});
		mniLuongNguoiHoc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Users.png")));
		mniLuongNguoiHoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.SHIFT_DOWN_MASK));
		mnThongKe.add(mniLuongNguoiHoc);
		
		JMenuItem mniDiemChuyenDe = new JMenuItem("Điểm chuyên đề");
		mniDiemChuyenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openThongKe(2);
			}
		});
		mniDiemChuyenDe.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Open letter.png")));
		mniDiemChuyenDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.SHIFT_DOWN_MASK));
		mnThongKe.add(mniDiemChuyenDe);
		
		JMenuItem mniDoanhThu = new JMenuItem("Doanh thu");
		mniDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Auth.isManager()) {
					MsgBox.alert(contentPane, "Bạn không có quyền xem doanh thu");
				}else {
					openThongKe(3);
				}
			}
		});
		mniDoanhThu.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Price list.png")));
		mniDoanhThu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.SHIFT_DOWN_MASK));
		mnThongKe.add(mniDoanhThu);
		
		JMenu mnTroGiup = new JMenu("Trợ giúp");
		menuBar.add(mnTroGiup);
		
		JMenuItem mniHuongDanSuDung = new JMenuItem("Hướng dẫn sử dụng");
		mniHuongDanSuDung.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Statistics.png")));
		mniHuongDanSuDung.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnTroGiup.add(mniHuongDanSuDung);
		
		JSeparator separator_3 = new JSeparator();
		mnTroGiup.add(separator_3);
		
		JMenuItem mniGioiThieuSanPham = new JMenuItem("Giới thiệu sản phẩm");
		mniGioiThieuSanPham.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Home.png")));
		mnTroGiup.add(mniGioiThieuSanPham);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar tbaCongCu = new JToolBar();
		tbaCongCu.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tbaCongCu.setBounds(10, 0, 1040, 60);
		contentPane.add(tbaCongCu);
		
		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangXuat();
			}
		});
		btnDangXuat.setMargin(new Insets(2, 16, 2, 16));
		btnDangXuat.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDangXuat.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDangXuat.setIconTextGap(0);
		btnDangXuat.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Log out.png")));
		btnDangXuat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tbaCongCu.add(btnDangXuat);
		
		btnKetThuc = new JButton("Kết thúc");
		btnKetThuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnKetThuc.setMargin(new Insets(2, 16, 2, 16));
		btnKetThuc.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnKetThuc.setHorizontalTextPosition(SwingConstants.CENTER);
		btnKetThuc.setIconTextGap(0);
		btnKetThuc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Stop.png")));
		btnKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tbaCongCu.add(btnKetThuc);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setMaximumSize(new Dimension(1, 60));
		separator_4.setOrientation(SwingConstants.VERTICAL);
		tbaCongCu.add(separator_4);
		
		JButton btnChuyenDe = new JButton("Chuyên đề");
		btnChuyenDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openQLChuyenDe();
			}
		});
		btnChuyenDe.setMargin(new Insets(2, 16, 2, 16));
		btnChuyenDe.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnChuyenDe.setHorizontalTextPosition(SwingConstants.CENTER);
		btnChuyenDe.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Notes.png")));
		btnChuyenDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tbaCongCu.add(btnChuyenDe);
		
		JButton btnNguoiHoc = new JButton("Người học");
		btnNguoiHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openQLNguoiHoc();
			}
		});
		btnNguoiHoc.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNguoiHoc.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNguoiHoc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Users.png")));
		btnNguoiHoc.setMargin(new Insets(2, 16, 2, 16));
		btnNguoiHoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tbaCongCu.add(btnNguoiHoc);
		
		JButton btnKhoaHoc = new JButton("Khóa học");
		btnKhoaHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openQLKhoaHoc();
			}
		});
		btnKhoaHoc.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnKhoaHoc.setHorizontalTextPosition(SwingConstants.CENTER);
		btnKhoaHoc.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Address book.png")));
		btnKhoaHoc.setMargin(new Insets(2, 16, 2, 16));
		btnKhoaHoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tbaCongCu.add(btnKhoaHoc);
		
		JButton btnHocVien = new JButton("Học viên");
		btnHocVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openQLHocVien();
			}
		});
		btnHocVien.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnHocVien.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHocVien.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/User.png")));
		btnHocVien.setMargin(new Insets(2, 16, 2, 16));
		btnHocVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tbaCongCu.add(btnHocVien);
		
		JSeparator separator_4_1 = new JSeparator();
		separator_4_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		separator_4_1.setOrientation(SwingConstants.VERTICAL);
		separator_4_1.setMaximumSize(new Dimension(1, 60));
		tbaCongCu.add(separator_4_1);
		
		JButton btnHuongDan = new JButton("Hướng dẫn");
		btnHuongDan.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnHuongDan.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHuongDan.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Statistics.png")));
		btnHuongDan.setMargin(new Insets(2, 16, 2, 16));
		btnHuongDan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tbaCongCu.add(btnHuongDan);
		
		JLabel lblBanner = new JLabel("");
		lblBanner.setOpaque(true);
		lblBanner.setBackground(new Color(255, 255, 255));
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanner.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/goldenBee.png")));
		lblBanner.setBounds(10, 59, 1040, 524);
		contentPane.add(lblBanner);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 582, 1041, 44);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblHeQLDaoTao = new JLabel("Hệ quản lý đào tạo");
		lblHeQLDaoTao.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/fpt.png")));
		lblHeQLDaoTao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblHeQLDaoTao, BorderLayout.WEST);
		
		JLabel lblOclock = new JLabel("");
		lblOclock.setIcon(new ImageIcon(EduSysJFrame.class.getResource("/com/edusys/icon/Alarm.png")));
		panel.add(lblOclock, BorderLayout.EAST);
		
		new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				java.util.Date nowDate = new java.util.Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
				String text = simpleDateFormat.format(nowDate);
				lblOclock.setText(text);
			}
		}).start();
		
		new ChaoJDialog(this, true).setVisible(true);
		new DangNhapJDialog(this, true).setVisible(true);
		
		
	}
	
	public void dangXuat() {
		if (MsgBox.confirm(contentPane, "Bạn muốn đăng xuất ra khỏi ứng dụng?")) {
			Auth.clear();
			new DangNhapJDialog(this, true).setVisible(true);
		}
	}
	
	public void openChangePassword() {
		if (Auth.isLogin()) {
			new DoiMatKhauJDialog().setVisible(true);
		}else {
			MsgBox.alert(contentPane, "Vui lòng đăng nhập");
		}
	}
	
	public void openQLNhanVien() {
		new QuanLyNhanVienJDialog().setVisible(true);
	}
	
	public void openQLChuyenDe() {
		new QuanLyChuyenDeJDialog().setVisible(true);
	}
	
	public void openQLKhoaHoc() {
		new QuanLyKhoaHocJDialog().setVisible(true);
	}
	
	public void openQLNguoiHoc() {
		new QuanLyNguoiHocJDialog().setVisible(true);
	}
	
	public void openQLHocVien() {
		new QuanLyHocVienJDialog().setVisible(true);
	}
	
	public void openThongKe(int index) {
		new ThongKeJDialog().setVisible(true);
		new ThongKeJDialog().tabs.setSelectedIndex(index);
	}
	
}
