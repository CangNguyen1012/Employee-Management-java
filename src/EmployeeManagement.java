import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int id;
	protected String firstName;
	protected String lastName;
	protected String gender;
	protected String DoB;
	protected String address;
	protected String phone;
	protected String email;
	public Employee() {}
	public Employee(int pid, String fName, String lName, String g, String dob, String addr, String ph, String e) {
		id = pid;
		firstName = fName;
		lastName = lName;
		gender = g;
		DoB = dob;
		address = addr;
		phone = ph;
		email = e;
	}
}

public class EmployeeManagement {
	
	ArrayList<Employee> eList = new ArrayList<Employee>();
	Employee currentEmp;
	File empFile = new File("Employee.data");
	FileOutputStream empStream;
	ObjectOutputStream empObjectStream;
	
	FileInputStream empInputStream;
	ObjectInputStream empObjectInputStream;
	
	private JFrame frmEmployeeManamentSystem;
	private JTextField txtId;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTable table;
	private JTextField txtGender;
	private JTextField txtDOB;
	private JTextField txtAddress;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	
	public void UpdateTable() {
		conn = EmployeeData.ConnectDB();
		if(conn != null) {
		String sql = "Select ID,`First Name`,`Last Name`,Gender,BOD,Address,Phone,Email";
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			Object[] columnData = new Object[8];
			while(rs.next()) {
				columnData[0] =rs.getString("ID");
				columnData[1] =rs.getString("First Name");
				columnData[2] =rs.getString("Last Name");
				columnData[3] =rs.getString("Gender");
				columnData[4] =rs.getString("BOD");
				columnData[5] =rs.getString("Address");
				columnData[6] =rs.getString("Phone");
				columnData[7] =rs.getString("Email");
				
				model.addRow(columnData);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
		}
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeManagement window = new EmployeeManagement();
					window.frmEmployeeManamentSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeManagement() {
		initialize();
		
		conn = EmployeeData.ConnectDB();
		Object col[] = {"ID","First Name","Last Name","Gender","BOD","Address","Phone","Email"};
		model.setColumnIdentifiers(col);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmployeeManamentSystem = new JFrame();
		frmEmployeeManamentSystem.setTitle("EMPLOYEE MANAMENT SYSTEM");
		frmEmployeeManamentSystem.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 10));
		frmEmployeeManamentSystem.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\bruno\\Downloads\\eclipse-java-2022-03-R-win32-x86_64\\eclipse\\workspace\\JavaProgramming\\Lab8EmployeeManagement\\employee_48px.png"));
		frmEmployeeManamentSystem.setBackground(new Color(0, 0, 0));
		frmEmployeeManamentSystem.getContentPane().setBackground(new Color(0, 191, 255));
		frmEmployeeManamentSystem.getContentPane().setForeground(new Color(0, 0, 0));
		frmEmployeeManamentSystem.setBounds(0,0,1450,800);
		frmEmployeeManamentSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmployeeManamentSystem.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(30, 73, 61, 13);
		frmEmployeeManamentSystem.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(30, 50, 45, 13);
		frmEmployeeManamentSystem.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(30, 96, 61, 13);
		frmEmployeeManamentSystem.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("EMPLOYEE INFORMATION");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(95, 6, 262, 31);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_3.setForeground(Color.RED);
		frmEmployeeManamentSystem.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4.setBounds(30, 119, 45, 13);
		frmEmployeeManamentSystem.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("DoB");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_5.setBounds(30, 142, 45, 13);
		frmEmployeeManamentSystem.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Address");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_6.setBounds(30, 165, 45, 13);
		frmEmployeeManamentSystem.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Phone");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_7.setBounds(30, 188, 45, 13);
		frmEmployeeManamentSystem.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Email");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_8.setBounds(30, 211, 45, 13);
		frmEmployeeManamentSystem.getContentPane().add(lblNewLabel_8);
		
		txtId = new JTextField();
		txtId.setBounds(95, 47, 262, 19);
		frmEmployeeManamentSystem.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(95, 70, 262, 19);
		frmEmployeeManamentSystem.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setBounds(95, 93, 262, 19);
		frmEmployeeManamentSystem.getContentPane().add(txtLastName);
		txtLastName.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(95, 185, 262, 19);
		frmEmployeeManamentSystem.getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(95, 208, 262, 19);
		frmEmployeeManamentSystem.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO employee(ID,First Name,Last Name,Gender,BOD,Address,Phone,Email) VALUES(?,?,?,?,?,?,?,?)";
				try {
					pst = conn.prepareStatement(sql);
					pst.setString(1, txtId.getText());
					pst.setString(2, txtFirstName.getText());
					pst.setString(3, txtLastName.getText());
					pst.setString(4, txtGender.getText());
					pst.setString(5, txtDOB.getText());
					pst.setString(6, txtAddress.getText());
					pst.setString(7, txtPhone.getText());
					pst.setString(8, txtEmail.getText());
					
					pst.execute();
					
					rs.close();
					pst.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "System Update Completed");
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						txtId.getText(),
						txtFirstName.getText(),
						txtLastName.getText(),
						txtGender.getText(),
						txtDOB.getText(),
						txtAddress.getText(),
						txtPhone.getText(),
						txtEmail.getText(),
				});
				if (table.getSelectedRow() == -1) {
					if(table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Membership Update confirmed","Employee Database System",JOptionPane.OK_OPTION);
					}
				}
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCreate.setBounds(30, 267, 85, 21);
		frmEmployeeManamentSystem.getContentPane().add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEmployeeManamentSystem = new JFrame("Cancel");
				if (JOptionPane.showConfirmDialog(frmEmployeeManamentSystem, "Confirm if you want to cancel", "Employee Database System",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnCancel.setBounds(272, 267, 85, 21);
		frmEmployeeManamentSystem.getContentPane().add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(502, 47, 607, 393);
		frmEmployeeManamentSystem.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("null")
			@Override public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				txtId.setText(model.getValueAt(i,0).toString());
				txtFirstName.setText(model.getValueAt(i,1).toString());
				txtLastName.setText(model.getValueAt(i,2).toString());
				txtGender.setText(model.getValueAt(i,3).toString());
				txtDOB.setText(model.getValueAt(i,4).toString());
				txtAddress.setText(model.getValueAt(i,5).toString());
				txtPhone.setText(model.getValueAt(i,6).toString());
				txtEmail.setText(model.getValueAt(i,7).toString());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "First Name", "Last Name", "Gender", "Date of Birth", "Address", "Phone", "Email"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(49);
		table.getColumnModel().getColumn(1).setPreferredWidth(94);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.getColumnModel().getColumn(4).setPreferredWidth(88);
		table.getColumnModel().getColumn(5).setPreferredWidth(103);
		table.getColumnModel().getColumn(6).setPreferredWidth(99);
		table.getColumnModel().getColumn(7).setPreferredWidth(114);
		table.setFont(new Font("Tahoma", Font.PLAIN, 10));
		scrollPane.setViewportView(table);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setText(null);
				txtFirstName.setText(null);
				txtLastName.setText(null);
				txtGender.setText(null);
				txtDOB.setText(null);
				txtAddress.setText(null);
				txtPhone.setText(null);
				txtEmail.setText(null);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnReset.setBounds(151, 267, 85, 21);
		frmEmployeeManamentSystem.getContentPane().add(btnReset);
		
		txtGender = new JTextField();
		txtGender.setBounds(94, 116, 111, 19);
		frmEmployeeManamentSystem.getContentPane().add(txtGender);
		txtGender.setColumns(10);
		
		txtDOB = new JTextField();
		txtDOB.setBounds(94, 139, 111, 19);
		frmEmployeeManamentSystem.getContentPane().add(txtDOB);
		txtDOB.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(94, 162, 263, 16);
		frmEmployeeManamentSystem.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("EMPLOYEE TABLE MANAGEMENT");
		lblNewLabel_9.setForeground(Color.RED);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(502, 6, 607, 31);
		frmEmployeeManamentSystem.getContentPane().add(lblNewLabel_9);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Arial", Font.PLAIN, 12));
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(Color.CYAN);
		frmEmployeeManamentSystem.setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Segoe UI", Font.BOLD, 12));
		JMenuItem menuItemOpen = new JMenuItem("Open");
		JMenuItem menuItemNew = new JMenuItem("New");
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemNew);
		
		JMenu menuHome = new JMenu("Home");
		menuHome.setFont(new Font("Segoe UI", Font.BOLD, 12));
		JMenuItem menuItemCopy = new JMenuItem("Copy");
		JMenuItem menuItemCut = new JMenuItem("Cut");
		menuHome.add(menuItemCopy);
		menuHome.add(menuItemCut);
		
		JMenu menuView = new JMenu("View");
		menuView.setFont(new Font("Segoe UI", Font.BOLD, 12));
		JMenuItem menuItemReview = new JMenuItem("Review");
		JMenuItem menuItemEdit = new JMenuItem("Edit");
		menuView.add(menuItemReview);
		menuView.add(menuItemEdit);
		
		JMenu menuHelp = new JMenu("Help");
		menuHelp.setFont(new Font("Segoe UI", Font.BOLD, 12));
		JMenuItem menuItemRestore = new JMenuItem("Restore");
		menuHelp.add(menuItemRestore);
		JMenuItem menuItemClose = new JMenuItem("Close");
		menuHelp.add(menuItemClose);
		
		menuItemClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeApplication();
            }
        });
		
		menuBar.add(menuFile);
		
		JMenuItem mLoad = new JMenuItem("Load All Employee Data");
		mLoad.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				try {
					empInputStream = new FileInputStream(empFile);
					empObjectInputStream = new ObjectInputStream(empInputStream);
					
					eList = (ArrayList<Employee>)empObjectInputStream.readObject();
					currentEmp = (Employee) eList.get(0);
					empObjectInputStream.close();
					txtId.setText(String.valueOf(currentEmp.id));
					txtFirstName.setText(currentEmp.firstName);
					txtLastName.setText(currentEmp.lastName);
					txtGender.setText(currentEmp.gender);
					txtDOB.setText(currentEmp.DoB);
					txtAddress.setText(currentEmp.address);
					txtPhone.setText(currentEmp.phone);
					txtEmail.setText(currentEmp.email);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuFile.add(mLoad);
		
		JMenuItem mSave = new JMenuItem("Save All Employee Data");
		mSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					empStream = new FileOutputStream(empFile);
					empObjectStream = new ObjectOutputStream(empStream);
					empObjectStream.writeObject(eList);
					empObjectStream.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuFile.add(mSave);
		menuBar.add(menuHome);
		menuBar.add(menuView);
		menuBar.add(menuHelp);
		
	}
	private void closeApplication() {
        int response = JOptionPane.showConfirmDialog(null, "Do you really want to close the application?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            frmEmployeeManamentSystem.dispose();
        }
    }
}
