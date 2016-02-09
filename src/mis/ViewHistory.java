package mis;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ViewHistory extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public String ID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHistory frame = new ViewHistory();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	/**
	 * Create the frame.
	 */
	public ViewHistory() {
		connection = DBConnector.dbConnector();
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewHistory.class.getResource("/Resources/Custo.Man.Christmas.Folder.Library.ico.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		StartPosition.centerOnScreen(this);
		
		JLabel label = new JLabel("X");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeFrame();
			}
		});
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		label.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		label.setBounds(1320, 11, 34, 23);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("__");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				minimize();
			}
		});
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Ubuntu", Font.PLAIN, 16));
		label_1.setBounds(1276, 11, 34, 23);
		contentPane.add(label_1);
		
		JLabel lblViewBooks = new JLabel("View Burrowed Books");
		lblViewBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewBooks.setForeground(Color.WHITE);
		lblViewBooks.setFont(new Font("Ubuntu", Font.BOLD, 36));
		lblViewBooks.setBounds(429, 25, 518, 72);
		contentPane.add(lblViewBooks);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLUE));
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(10, 132, 1344, 623);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1324, 535);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setGridColor(Color.GRAY);
		table.setFont(new Font("Ubuntu", Font.PLAIN, 11));
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setBackground(Color.WHITE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					
					String Query = "select * from LibHistory where MemID = ?";
					PreparedStatement pst = connection.prepareStatement(Query);
					pst.setString(1, ID);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();
					pst.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex, "ERROR!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnView.setForeground(Color.WHITE);
		btnView.setBackground(Color.DARK_GRAY);
		btnView.setFont(new Font("Ubuntu", Font.PLAIN, 13));
		btnView.setBounds(628, 577, 89, 23);
		panel.add(btnView);
		
		
		
	}
	
	public void closeFrame()
	{
		super.dispose();
	}
	
	@SuppressWarnings("static-access")
	public void minimize()
	{
		super.setState(super.ICONIFIED);
	}
}
