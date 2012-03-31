package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import classes.entity.Activity;
import classes.entity.Group;
import classes.entity.Material;
import classes.entity.Stock;
import classes.entity.Supplier;
import classes.entity.Worker;
import classes.relations.Charge;
import classes.relations.Deposit;
import classes.relations.Offer;
import classes.relations.Used;


import db.entity.DBActivity;
import db.entity.DBGroup;
import db.entity.DBMaterial;
import db.entity.DBStock;
import db.entity.DBSupplier;
import db.entity.DBWorker;
import db.relations.DBCharge;
import db.relations.DBDeposit;
import db.relations.DBOffer;
import db.relations.DBUsed;

import view.model.ActivityTableModel;
import view.model.ChargeTabelModel;
import view.model.DepositTableModel;
import view.model.GroupTableModel;
import view.model.MaterialTableModel;
import view.model.OfferTableModel;
import view.model.StockTableModel;
import view.model.SupplierTableModel;
import view.model.UsedTableModel;
import view.model.WorkerTableModel;

public class LogisticGui extends JFrame implements Observer {

	// �˵�
	private JMenu fileMenu;
	private JMenuItem groupItem;
	private JMenuItem workerItem;
	private JMenuItem activityItem;
	private JMenuItem materialItem;
	private JMenuItem supplierItem;
	private JMenuItem stockItem;
	private JMenuItem logoutItem;
	private JMenuItem exitItem;

	private JMenu relationMenu;
	private JMenuItem chargeItem;
	private JMenuItem depositItem;
	private JMenuItem usedItem;
	private JMenuItem offerItem;

	private JMenu helpMenu;
	private JMenuItem helpItem;
	private JMenuItem aboutItem;
	private JMenuBar menuBar;

	// //////////////////////////////////////////////
	
	private JPanel displayPanel;//��ʾ���
	private JPanel operate_displayPanel;//��ʾ���Ĳ������
	private JButton printButton;//��ӡ��ť
	private JLabel filterLabel;//���˱�ǩ
	private JTextField filterTextField;//��������
	private JButton doFilterButton;//���˰�ť
	
	private TableRowSorter<TableModel> sorter ;//����
	
	private JPanel operatePanel;// ���������

	private JPanel label_operatePanel;// ������ǩ���
	private JPanel sub_operatePanel;// �Ӳ������:��Ϣ����
//	private JPanel label_sub_Panel;// ������ǩ�������/////////////////////
	private JPanel button_operatePanel;// �Ӳ�����壺��ť
	private JButton addButton;
	private JButton delButton;
	private JButton queryButton;
	private JButton updateButton;

	// ///////////////////////////////////////////////
	// ������
	private JPanel groupPanel;
	private JLabel groupLabel;

	private JLabel groupIdLabel, groupNameLabel, groupDutyLabel, officeLabel,
			workTimeLabel;
	private JTextField groupIdField, groupNameField, groupDutyField,
			officeField, workTimeField;

	// �
	private JPanel activityPanel;
	private JLabel activityLabel;

	private JLabel activityIdLabel, activityName, activityPlaceLabel,
			activityTimeLabel, activityContactLabel, activityPersonNumLabel;
	private JTextField activityIdField, activityNameField, activityPlaceField,
			activityTimeField, activityContactField, activityPersonNumField;

	// ����
	private JPanel materialPanel;
	private JLabel materialLabel;

	private JLabel materialIdLabel, materialNameLabel, materialUsedLabel;
	private JTextField materialIdField, materialNameField, materialUsedField;

	// �ֿ�
	private JPanel stockPanel;
	private JLabel stockLabel;

	private JLabel stockIdLabel, stockPlaceLabel, stockTelLabel,
			stockAreaLabel;
	private JTextField stockIdField, stockPlaceField, stockTelField,
			stockAreaField;

	// ��Ӧ��
	private JPanel supplierPanel;
	private JLabel supplierLabel;

	private JLabel supplierIdLabel, origanizationLabel, addressLabel,
			contactTelLabel;
	private JTextField supplierIdField, origanizationField, addressField,
			contactTelField;

	// ������Ա
	private JPanel workerPanel;
	private JLabel workerLabel;

	private JLabel workerIdLabel, workerNameLabel, workerTelLabel,
			workerSexLabel;
	private JTextField workerIdField, workerNameField, workerTelField,
			workerSexField;

	// ��ϵ
	// ����
	private JPanel chargePanel;
	private JLabel chargeLabel;

	private JLabel charge_groupIdLabel, charge_workerIdLabel,
			charge_activityIdLabel, charge_chargeTimeLabel;
	private JTextField charge_groupIdField, charge_workerIdField,
			charge_activityIdField, charge_chargeTimeField;

	// �洢
	private JPanel depositPanel;
	private JLabel depositLabel;

	private JLabel deposit_materialIdLabel, deposit_stockIdLabel,
			deposit_depositNumLabel;
	private JTextField deposit_materialIdField, deposit_stockIdField,
			deposit_depositNumField;

	// �ṩ
	private JPanel offerPanel;
	private JLabel offerLabel;

	private JLabel offer_supplierIdLabel, offer_materialIdLabel,
			offer_offerNumLabel;
	private JTextField offer_supplierIdField, offer_materialIdField,
			offer_offerNumField;

	// ʹ��
	private JPanel usedPanel;
	private JLabel usedLabel;

	private JLabel used_workerIdLabel, used_materialIdLabel,
			used_materialUsedNumLabel;
	private JTextField used_workerIdField, used_materialIdField,
			used_materialUsedNumField;

	JScrollPane scrollPane;

	// ���
	// ʵ��
	private JTable tableGroup;
	private JTable tableActivity;
	private JTable tableMaterial;
	private JTable tableStock;
	private JTable tableSupplier;
	private JTable tableWorker;

	// ��ϵ
	private JTable tableCharge;
	private JTable tableOffer;
	private JTable tableDeposit;
	private JTable tableUsed;

	// �ܵ����
	private Container contentPanel;
	private JPanel finalPanel;// ���contentPanel�����

	// ��������
	Font font0 = new Font("SanSerif", Font.BOLD, 30);
	Font font1 = new Font("SanSerif", Font.BOLD, 15);

	// ///////////////////////////////////////////////////////////////////
	// ʵ��
	public static final int GROUP = 1;
	public static final int ACTIVITY = 2;
	public static final int STOCK = 3;
	public static final int MATERIAL = 4;
	public static final int SUPPLIER = 5;
	public static final int WORKER = 6;

	// ��ϵ
	public static final int CHARGE = 7;
	public static final int DEPOSIT = 8;
	public static final int USED = 9;
	public static final int OFFER = 10;

	// ����
	public static final int INSTRUCTION = 11;
	public static final int ABOUT = 12;

	private int select;// �˵���ťѡ����

	public int getSelect() {
		return select;
	}

	public void setSelect(int select) {
		this.select = select;
	}

	// /////////////////////////////////////////////////

	// ������庯��
	/**
	 * ������Ҫ��ʾ�����
	 */
	private JPanel display_operate_panel(int select) {
		label_operatePanel = new JPanel(new FlowLayout());

		switch (select) {
		case GROUP: {
			displayPanel = sub_display_panle(group_display_panel());
			operatePanel = group_operate_panel();
			groupPanel = new JPanel(new FlowLayout());
			groupPanel.add(displayPanel);
			groupPanel.add(operatePanel);
			return groupPanel;
		}
		case ACTIVITY: {
			displayPanel = sub_display_panle(activity_display_panel());
			operatePanel = activity_operate_panel();
			activityPanel = new JPanel(new FlowLayout());
			activityPanel.add(displayPanel);
			activityPanel.add(operatePanel);
			return activityPanel;
		}
		case STOCK: {
			displayPanel = sub_display_panle(stock_display_panel());
			operatePanel = stock_operate_panel();

			stockPanel = new JPanel(new FlowLayout());
			stockPanel.add(displayPanel);
			stockPanel.add(operatePanel);

			return stockPanel;
		}
		case MATERIAL: {
			displayPanel = sub_display_panle(material_display_panel());
			operatePanel = material_operate_panel();

			materialPanel = new JPanel(new FlowLayout());
			materialPanel.add(displayPanel);
			materialPanel.add(operatePanel);
			return materialPanel;
		}
		case SUPPLIER: {
			displayPanel = sub_display_panle(supplier_display_panel());
			operatePanel = supplier_operate_panel();

			supplierPanel = new JPanel(new FlowLayout());
			supplierPanel.add(displayPanel);
			supplierPanel.add(operatePanel);

			return supplierPanel;
		}
		case WORKER: {
			displayPanel = sub_display_panle(worker_display_panel());
			operatePanel = worker_operate_panel();
			workerPanel = new JPanel(new FlowLayout());
			workerPanel.add(displayPanel);
			workerPanel.add(operatePanel);
			return workerPanel;
		}
		case CHARGE: {
			displayPanel = sub_display_panle(charge_display_panel());
			operatePanel = charge_operate_panel();
			chargePanel = new JPanel(new FlowLayout());
			chargePanel.add(displayPanel);
			chargePanel.add(operatePanel);
			return chargePanel;
		}
		case DEPOSIT: {
			displayPanel = sub_display_panle(deposit_display_panel());
			operatePanel = deposit_operate_panel();
			depositPanel = new JPanel(new FlowLayout());
			depositPanel.add(displayPanel);
			depositPanel.add(operatePanel);
			return depositPanel;
		}
		case USED: {
			displayPanel = sub_display_panle(used_display_panel());
			operatePanel = used_operate_panel();
			usedPanel = new JPanel(new FlowLayout());
			usedPanel.add(displayPanel);
			usedPanel.add(operatePanel);
			return usedPanel;
		}
		case OFFER: {
			displayPanel = sub_display_panle(offer_display_panel());
			operatePanel = offer_operate_panel();
			offerPanel = new JPanel(new FlowLayout());
			offerPanel.add(displayPanel);
			offerPanel.add(operatePanel);
			return offerPanel;
		}
		default: {
			return new JPanel();
		}

		}
	}

	/**
	 * ��ģ�幹������ʾ���
	 * 
	 * @param table
	 * @return
	 */
	private JPanel sub_display_panle(JTable table) {

		// ������Ԫ��ѡ��
		table.setCellSelectionEnabled(true);
		// ���ⵥԪ��ѡ��
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		displayPanel = new JPanel(new BorderLayout());
		displayPanel.add(new JScrollPane(table), BorderLayout.NORTH);

		filterLabel = new JLabel("Filter Text:");
		filterTextField = new JTextField(12);
		doFilterButton = new JButton("Filter");
		doFilterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(filterTextField.getText().length() == 0){
					sorter.setRowFilter(null);
				}else {
					sorter.setRowFilter(RowFilter.regexFilter(filterTextField.getText()));
				}//������ʽ����
			} 
		});
		
		operate_displayPanel = new JPanel(new FlowLayout());
		operate_displayPanel.add(filterLabel);
		operate_displayPanel.add(filterTextField);
		operate_displayPanel.add(doFilterButton);
		
		printButton = new JButton("Print");
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionTablePrint();
			}
		});
		
		operate_displayPanel.add(printButton);
		displayPanel.add(operate_displayPanel, BorderLayout.SOUTH);
		
		displayPanel.setBorder(BorderFactory.createTitledBorder("�����Ϣ"));
		return displayPanel;

	}

	// /////////////////////////////////////////////////////////////

	/**
	 * ��ʾϵͳ�ĸ����������������
	 */
	private void sub_display(int select) {
		// ��ʼ������
		initialize();
		// ��ʼ���˵���
		menu();
		// ��ʾ����
		contentPanel = getContentPane();

		contentPanel.setLayout(new BorderLayout());

		if (select == 0) {
			ImageIcon icon1;
			JLabel image1;
			finalPanel = new JPanel();
			icon1 = new ImageIcon("xinzhu.jpg");
			image1 = new JLabel(icon1);
			finalPanel.add(image1);
		} else {
			finalPanel = display_operate_panel(select);
		}

		finalPanel.setBorder(BorderFactory.createRaisedBevelBorder());// ���߿�

		contentPanel.add(finalPanel, BorderLayout.CENTER);
		contentPanel.setVisible(true);
	}

	private void display() {
		sub_display(select);
	}

	/**
	 * ������
	 */
	public LogisticGui() {
		display();
	}

	/**
	 * ��ʼ�������С����ʾλ��
	 */
	private void initialize() {
		this.setSize(800, 600);
		// ********************************************************
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = screenSize.width / 2;
		int centerY = screenSize.height / 2;
		int halfwidth = frameSize.width / 2;
		int halfHeight = frameSize.height / 2;
		this.setLocation(centerX - halfwidth, centerY - halfHeight);
		// **********************************************************8

		this.setTitle("���ڹ���ϵͳ");
		this.setVisible(true);
		this.setResizable(false);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Login.actionExit();
			}
		});
	}

	/**
	 * �˵������
	 */
	private void menu() {
		fileMenu = new JMenu("�ļ�");

		groupItem = new JMenuItem("������");
		groupItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelect(GROUP);
				actionGroupItemButton();
			}
		});
		workerItem = new JMenuItem("������Ա");
		workerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelect(WORKER);
				actionWorkerItemButton();
			}
		});
		activityItem = new JMenuItem("�");
		activityItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelect(ACTIVITY);
				actionActivityItmeButton();
			}
		});
		materialItem = new JMenuItem("����");
		materialItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelect(MATERIAL);
				actionMaterialItemButton();
			}
		});
		supplierItem = new JMenuItem("��Ӧ��");
		supplierItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelect(SUPPLIER);
				actionSupplierItemButton();
			}
		});
		stockItem = new JMenuItem("�ֿ�");
		stockItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelect(STOCK);
				actionStockItemButton();
			}
		});

		/*
		 * logoutItem = new JMenuItem("ע��"); logoutItem.addActionListener(new
		 * ActionListener(){ public void actionPerformed(ActionEvent e){
		 * ///////////////////////////// Login login = new Login();
		 * login.setVisible(true); } });
		 */exitItem = new JMenuItem("�˳�");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.actionExit();
			}
		});

		fileMenu.add(groupItem);
		fileMenu.add(workerItem);
		fileMenu.add(activityItem);
		fileMenu.add(materialItem);
		fileMenu.add(supplierItem);
		fileMenu.add(stockItem);
		// fileMenu.add(logoutItem);
		fileMenu.add(exitItem);

		relationMenu = new JMenu("��ϵ");
		chargeItem = new JMenuItem("����");
		chargeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelect(CHARGE);
				actionChargeItemButton();
			}
		});
		depositItem = new JMenuItem("���");
		depositItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelect(DEPOSIT);
				actionDepositItemButton();
			}
		});
		usedItem = new JMenuItem("ʹ��");
		usedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelect(USED);
				actionUsedItemButton();
			}
		});
		offerItem = new JMenuItem("�ṩ");
		offerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelect(OFFER);
				actionOfferItemButton();
			}
		});

		relationMenu.add(chargeItem);
		relationMenu.add(depositItem);
		relationMenu.add(usedItem);
		relationMenu.add(offerItem);

		helpMenu = new JMenu("����");
		helpItem = new JMenuItem("˵��");
		helpItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��ʾʹ��˵��
				finalPanel.setVisible(false);
				finalPanel = new JPanel(new BorderLayout());

				JLabel helpLabel = new JLabel("����˵��", JLabel.CENTER);

				helpLabel.setFont(font0);
				String help = Help.readIntroduce("help.txt");

				JTextArea helpContent = new JTextArea(help);
				helpContent.setEditable(false);
				JScrollPane helpScrollPane = new JScrollPane(helpContent);
				helpContent.setFont(font1);

				finalPanel.add(helpLabel, BorderLayout.NORTH);
				finalPanel.add(helpScrollPane, BorderLayout.CENTER);

				contentPanel.add(finalPanel, BorderLayout.CENTER);
				finalPanel.setVisible(true);

			}
		});
		aboutItem = new JMenuItem("����");
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��ʾ����
				finalPanel.setVisible(false);
				finalPanel = new JPanel(new BorderLayout());

				JLabel aboutLabel = new JLabel("��Ȩ����", JLabel.CENTER);
				aboutLabel.setFont(font0);

				String about = Help.readIntroduce("copyright.txt");
				JTextArea aboutContent = new JTextArea(about);
				aboutContent.setEditable(false);
				JScrollPane aboutScrollPane = new JScrollPane(aboutContent);
				aboutContent.setFont(font1);

				finalPanel.add(aboutLabel, BorderLayout.NORTH);
				finalPanel.add(aboutScrollPane, BorderLayout.CENTER);

				contentPanel.add(finalPanel, BorderLayout.CENTER);
				finalPanel.setVisible(true);
			}
		});

		helpMenu.add(helpItem);
		helpMenu.add(aboutItem);

		menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(relationMenu);
		menuBar.add(helpMenu);
		setJMenuBar(menuBar);

	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	// /////////////////////////////////////////////////////////////////////
	// ������
	private JTable group_display_panel() {
		tableGroup = new JTable(new GroupTableModel()) {

			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				if (realColumnIndex == 1) { // Sport column
					tip = "This group's name is : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 2) {
					tip = "This group's duty is : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 3) {
					tip = "This group's office is : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 4) {
					tip = "This group's worktime is : "
							+ getValueAt(rowIndex, colIndex);
				} else { // another column
					// You can omit this part if you know you don't
					// have any renderers that supply their own tool
					// tips.
					tip = super.getToolTipText(e);
				}
				return tip;
			}

		};
		tableGroup.setAutoCreateRowSorter(true);
	
		//final TableRowSorter 
		sorter = new TableRowSorter(new GroupTableModel()); 
	    tableGroup.setRowSorter(sorter); //ΪJTable����������

		tableGroup.add(tableGroup.getTableHeader(), BorderLayout.PAGE_START);

		
		return tableGroup;
	}

	private JPanel group_operate_panel() {

		groupLabel = new JLabel("��������Ϣ");
		groupLabel.setFont(font0);
		label_operatePanel.add(groupLabel);

		// �������:���
		sub_operatePanel = new JPanel(new GridLayout(5, 2));
		sub_operatePanel.setBorder(BorderFactory.createEtchedBorder());
		groupIdLabel = new JLabel("Id:");
		groupIdField = new JTextField(6);
		groupNameLabel = new JLabel("Name:");
		groupNameField = new JTextField(6);
		groupDutyLabel = new JLabel("Duty:");
		groupDutyField = new JTextField(6);
		officeLabel = new JLabel("office:");
		officeField = new JTextField(6);
		workTimeLabel = new JLabel("workTime:");
		workTimeField = new JTextField(6);

		JLabel[] groupLabelArray = { groupIdLabel, groupNameLabel,
				groupDutyLabel, officeLabel, workTimeLabel };
		JTextField[] groupFieldArray = { groupIdField, groupNameField,
				groupDutyField, officeField, workTimeField };

		for (int i = 0; i < groupLabelArray.length; i++) {
			sub_operatePanel.add(groupLabelArray[i]);
			sub_operatePanel.add(groupFieldArray[i]);
		}

		return sub_operate();
	}

	// �
	private JTable activity_display_panel() {
		tableActivity = new JTable(new ActivityTableModel()) {

			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				if (realColumnIndex == 1) { // Sport column
					tip = "This activity's name is : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 2) {
					tip = "This activity will be held in : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 3) {
					tip = "This acticity  will be held at : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 4) {
					tip = "This acticity concerns with : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 5) {
					tip = " There will be " + getValueAt(rowIndex, colIndex)
							+ " people attending.";
				} else {// another column
					// You can omit this part if you know you don't
					// have any renderers that supply their own tool
					// tips.
					tip = super.getToolTipText(e);
				}
				return tip;
			}
		};
		tableActivity.setAutoCreateRowSorter(true);
		
		sorter = new TableRowSorter(new ActivityTableModel()); 
	    tableActivity.setRowSorter(sorter); //ΪJTable����������
	    
		tableActivity.add(tableActivity.getTableHeader(),
				BorderLayout.PAGE_START);
		return tableActivity;
	}

	private JPanel activity_operate_panel() {
		activityLabel = new JLabel("���Ϣ");
		activityLabel.setFont(font0);
		label_operatePanel.add(activityLabel);
		// �������:���
		sub_operatePanel = new JPanel(new GridLayout(6, 2));
		sub_operatePanel.setBorder(BorderFactory.createEtchedBorder());
		activityIdLabel = new JLabel("Id:");
		activityIdField = new JTextField(6);
		activityName = new JLabel("Name:");
		activityNameField = new JTextField(6);
		activityPlaceLabel = new JLabel("Place:");
		activityPlaceField = new JTextField(6);
		activityTimeLabel = new JLabel("Time:");
		activityTimeField = new JTextField(6);
		activityContactLabel = new JLabel("Contact:");
		activityContactField = new JTextField(6);
		activityPersonNumLabel = new JLabel("PersonNum:");
		activityPersonNumField = new JTextField(6);

		JLabel[] activityLabelArray = { activityIdLabel, activityName,
				activityPlaceLabel, activityTimeLabel, activityContactLabel,
				activityPersonNumLabel };
		JTextField[] activityFieldArray = { activityIdField, activityNameField,
				activityPlaceField, activityTimeField, activityContactField,
				activityPersonNumField };

		for (int i = 0; i < activityLabelArray.length; i++) {
			sub_operatePanel.add(activityLabelArray[i]);
			sub_operatePanel.add(activityFieldArray[i]);
		}

		return sub_operate();
	}

	private JTable stock_display_panel() {
		tableStock = new JTable(new StockTableModel()){
			
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				if (realColumnIndex == 1) { // Sport column
					tip = "This stock is located at : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 2) {
					tip = "This stock's telephone Number is : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 3) {
					tip = "This stock size is : "
							+ getValueAt(rowIndex, colIndex);
				} else {// another column
					// You can omit this part if you know you don't
					// have any renderers that supply their own tool
					// tips.
					tip = super.getToolTipText(e);
				}
				return tip;
			}
			
		};
		tableStock.setAutoCreateRowSorter(true);
		
		sorter = new TableRowSorter(new StockTableModel()); 
	    tableStock.setRowSorter(sorter); //ΪJTable����������
	    
		tableStock.add(tableStock.getTableHeader(), BorderLayout.PAGE_START);
		return tableStock;
	}

	// �ֿ�

	private JPanel stock_operate_panel() {
		stockLabel = new JLabel("�ֿ���Ϣ");
		stockLabel.setFont(font0);
		label_operatePanel.add(stockLabel);

		// �������:���
		sub_operatePanel = new JPanel(new GridLayout(4, 2));
		sub_operatePanel.setBorder(BorderFactory.createEtchedBorder());
		stockIdLabel = new JLabel("Id:");
		stockIdField = new JTextField(6);
		stockPlaceLabel = new JLabel("Place:");
		stockPlaceField = new JTextField(6);
		stockTelLabel = new JLabel("Tel:");
		stockTelField = new JTextField(6);
		stockAreaLabel = new JLabel("Area:");
		stockAreaField = new JTextField(6);

		JLabel[] stockLabelArray = { stockIdLabel, stockPlaceLabel,
				stockTelLabel, stockAreaLabel };
		JTextField[] stockFieldArray = { stockIdField, stockPlaceField,
				stockTelField, stockAreaField };

		for (int i = 0; i < stockLabelArray.length; i++) {
			sub_operatePanel.add(stockLabelArray[i]);
			sub_operatePanel.add(stockFieldArray[i]);
		}

		return sub_operate();
	}

	// ����
	private JTable material_display_panel() {
		tableMaterial = new JTable(new MaterialTableModel()){
			
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				if (realColumnIndex == 1) { // Sport column
					tip = "This material's name is : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 2) {
					tip = "This material can be used to : "
							+ getValueAt(rowIndex, colIndex);
				} else {// another column
					// You can omit this part if you know you don't
					// have any renderers that supply their own tool
					// tips.
					tip = super.getToolTipText(e);
				}
				return tip;
			}
		};
		tableMaterial.setAutoCreateRowSorter(true);
		
		sorter = new TableRowSorter(new MaterialTableModel()); 
	    tableMaterial.setRowSorter(sorter); //ΪJTable����������
	    
		tableMaterial.add(tableMaterial.getTableHeader(),
				BorderLayout.PAGE_START);
		return tableMaterial;
	}

	private JPanel material_operate_panel() {
		materialLabel = new JLabel("������Ϣ");
		materialLabel.setFont(font0);
		label_operatePanel.add(materialLabel);

		// �������:���
		sub_operatePanel = new JPanel(new GridLayout(3, 2));
		sub_operatePanel.setBorder(BorderFactory.createEtchedBorder());
		materialIdLabel = new JLabel("Id:");
		materialIdField = new JTextField(6);
		materialNameLabel = new JLabel("Name:");
		materialNameField = new JTextField(6);
		materialUsedLabel = new JLabel("Used:");
		materialUsedField = new JTextField(6);

		JLabel[] materialLabelArray = { materialIdLabel, materialNameLabel,
				materialUsedLabel };
		JTextField[] materialFieldArray = { materialIdField, materialNameField,
				materialUsedField };

		for (int i = 0; i < materialLabelArray.length; i++) {
			sub_operatePanel.add(materialLabelArray[i]);
			sub_operatePanel.add(materialFieldArray[i]);
		}

		return sub_operate();
	}

	// ��Ӧ��
	private JTable supplier_display_panel() {
		tableSupplier = new JTable(new SupplierTableModel()){
			
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				if (realColumnIndex == 1) { // Sport column
					tip = "This supplier's name is : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 2) {
					tip = "This supplier coming from : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 3) {
					tip = "This supplier's phone number is : "
						+ getValueAt(rowIndex, colIndex);
				} else {// another column
					// You can omit this part if you know you don't
					// have any renderers that supply their own tool
					// tips.
					tip = super.getToolTipText(e);
				}
				return tip;
			}
		};
		tableSupplier.setAutoCreateRowSorter(true);
		
		sorter = new TableRowSorter(new SupplierTableModel()); 
	    tableSupplier.setRowSorter(sorter); //ΪJTable����������
	    
		tableSupplier.add(tableSupplier.getTableHeader(),
				BorderLayout.PAGE_START);
		return tableSupplier;
	}
	
	

	private JPanel supplier_operate_panel() {
		supplierLabel = new JLabel("��Ӧ����Ϣ");
		supplierLabel.setFont(font0);
		label_operatePanel.add(supplierLabel);

		// �������:���
		sub_operatePanel = new JPanel(new GridLayout(4, 2));
		sub_operatePanel.setBorder(BorderFactory.createEtchedBorder());
		supplierIdLabel = new JLabel("Id:");
		supplierIdField = new JTextField(6);
		origanizationLabel = new JLabel("Origanization:");
		origanizationField = new JTextField(6);
		addressLabel = new JLabel("Address:");
		addressField = new JTextField(6);
		contactTelLabel = new JLabel("Tel:");
		contactTelField = new JTextField(6);

		JLabel[] supplierLabelArray = { supplierIdLabel, origanizationLabel,
				addressLabel, contactTelLabel };
		JTextField[] supplierFieldArray = { supplierIdField,
				origanizationField, addressField, contactTelField };

		for (int i = 0; i < supplierLabelArray.length; i++) {
			sub_operatePanel.add(supplierLabelArray[i]);
			sub_operatePanel.add(supplierFieldArray[i]);
		}
		return sub_operate();
	}

	// ������Ա
	private JTable worker_display_panel() {
		tableWorker = new JTable(new WorkerTableModel()){
			
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				if (realColumnIndex == 1) { // Sport column
					tip = "This worker's name is : "
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 2) {
					tip = "This worker's phone number is :"
							+ getValueAt(rowIndex, colIndex);
				} else if (realColumnIndex == 3) {
					tip = "This worker's sex is : "
						+ getValueAt(rowIndex, colIndex);
				} else {// another column
					// You can omit this part if you know you don't
					// have any renderers that supply their own tool
					// tips.
					tip = super.getToolTipText(e);
				}
				return tip;
			}
		};
		tableWorker.setAutoCreateRowSorter(true);
		
		sorter = new TableRowSorter(new WorkerTableModel()); 
	    tableWorker.setRowSorter(sorter); //ΪJTable����������
	    
		tableWorker.add(tableWorker.getTableHeader(), BorderLayout.PAGE_START);
		return tableWorker;
	}

	private JPanel worker_operate_panel() {
		workerLabel = new JLabel("������Ա��Ϣ");
		workerLabel.setFont(font0);
		label_operatePanel.add(workerLabel);

		// �������:���
		sub_operatePanel = new JPanel(new GridLayout(4, 2));
		sub_operatePanel.setBorder(BorderFactory.createEtchedBorder());
		workerIdLabel = new JLabel("Id:");
		workerIdField = new JTextField(6);
		workerNameLabel = new JLabel("Name:");
		workerNameField = new JTextField(6);
		workerTelLabel = new JLabel("Tel:");
		workerTelField = new JTextField(6);
		workerSexLabel = new JLabel("Sex:");
		workerSexField = new JTextField(6);

		JLabel[] workerLabelArray = { workerIdLabel, workerNameLabel,
				workerTelLabel, workerSexLabel };
		JTextField[] workerFieldArray = { workerIdField, workerNameField,
				workerTelField, workerSexField };

		for (int i = 0; i < workerLabelArray.length; i++) {
			sub_operatePanel.add(workerLabelArray[i]);
			sub_operatePanel.add(workerFieldArray[i]);
		}

		return sub_operate();
	}

	// //////////////////////////////////////////////////////////////////////

	// ����
	private JTable charge_display_panel() {
		tableCharge = new JTable(new ChargeTabelModel()){
			
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				if (realColumnIndex == 0) { 
					//����ID��ȡ����
					tip = "The group name is : "
							+ DBGroup.readGroupSingle(Integer
									.parseInt(String.valueOf(getValueAt(rowIndex, colIndex)))).getGroupName();
				} else if (realColumnIndex == 1) {
					tip = "The worker name is :"
							+ DBWorker.readWorkerSingle(Integer
									.parseInt(String.valueOf(getValueAt(rowIndex, colIndex)))).getWorkerName();
				} else if (realColumnIndex == 2) {
					tip = "This activity name is : "
						+ DBActivity.readActivitySingle(Integer
								.parseInt(String.valueOf(getValueAt(rowIndex, colIndex)))).getActivityName();
				} else if (realColumnIndex == 3){
					tip = "The chargetime is :"
						+ getValueAt(rowIndex, colIndex);
				}else {// another column
					// You can omit this part if you know you don't
					// have any renderers that supply their own tool
					// tips.
					tip = super.getToolTipText(e);
				}
				return tip;
			}
		};
		tableCharge.setAutoCreateRowSorter(true);
	    
		sorter = new TableRowSorter(new ChargeTabelModel()); 
	    tableCharge.setRowSorter(sorter); //ΪJTable����������
	    
		tableCharge.add(tableCharge.getTableHeader(), BorderLayout.PAGE_START);
		return tableCharge;
	}

	private JPanel charge_operate_panel() {
		chargeLabel = new JLabel("������Ϣ");
		chargeLabel.setFont(font0);
		label_operatePanel.add(chargeLabel);
		// �������:���
		sub_operatePanel = new JPanel(new GridLayout(4, 2));
		sub_operatePanel.setBorder(BorderFactory.createEtchedBorder());
		charge_groupIdLabel = new JLabel("GroupId:");
		charge_groupIdField = new JTextField(6);
		charge_workerIdLabel = new JLabel("WorkerId:");
		charge_workerIdField = new JTextField(6);
		charge_activityIdLabel = new JLabel("ActivityId:");
		charge_activityIdField = new JTextField(6);
		charge_chargeTimeLabel = new JLabel("Time��");
		charge_chargeTimeField = new JTextField(6);

		JLabel[] chargeLabelArray = { charge_groupIdLabel,
				charge_workerIdLabel, charge_activityIdLabel,
				charge_chargeTimeLabel };
		JTextField[] chargeFieldArray = { charge_groupIdField,
				charge_workerIdField, charge_activityIdField,
				charge_chargeTimeField };

		for (int i = 0; i < chargeLabelArray.length; i++) {
			sub_operatePanel.add(chargeLabelArray[i]);
			sub_operatePanel.add(chargeFieldArray[i]);
		}

		return sub_operate();
	}

	// �洢
	private JTable deposit_display_panel() {
		tableDeposit = new JTable(new DepositTableModel()){
			
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				if (realColumnIndex == 0) { 
					//����ID��ȡ����
					tip = "This material name is : "
							+ DBMaterial.readMaterialSingle(Integer
									.parseInt(String.valueOf(getValueAt(rowIndex, colIndex)))).getMaterialName();
				} else if (realColumnIndex == 1) {
					tip = "This stock place is :"
							+ DBStock.readStockSingle(Integer
									.parseInt(String.valueOf(getValueAt(rowIndex, colIndex)))).getStockPlace();
				} else if (realColumnIndex == 2) {
					tip = "This deposit number is : "
						+ getValueAt(rowIndex, colIndex);
				} else {// another column
					// You can omit this part if you know you don't
					// have any renderers that supply their own tool
					// tips.
					tip = super.getToolTipText(e);
				}
				return tip;
			}
			
		};
		tableDeposit
				.add(tableDeposit.getTableHeader(), BorderLayout.PAGE_START);
		
		sorter = new TableRowSorter(new DepositTableModel()); 
	    tableDeposit.setRowSorter(sorter); //ΪJTable����������
	    
		tableDeposit.setAutoCreateRowSorter(true);
		return tableDeposit;
	}

	private JPanel deposit_operate_panel() {
		depositLabel = new JLabel("�洢��Ϣ");
		depositLabel.setFont(font0);
		label_operatePanel.add(depositLabel);
		// �������:���
		sub_operatePanel = new JPanel(new GridLayout(3, 2));
		sub_operatePanel.setBorder(BorderFactory.createEtchedBorder());

		deposit_materialIdLabel = new JLabel("MaterialId:");
		deposit_materialIdField = new JTextField(6);
		deposit_stockIdLabel = new JLabel("StockId:");
		deposit_stockIdField = new JTextField(6);
		deposit_depositNumLabel = new JLabel("DespositNum:");
		deposit_depositNumField = new JTextField(6);

		JLabel[] depositLabelArray = { deposit_materialIdLabel,
				deposit_stockIdLabel, deposit_depositNumLabel };
		JTextField[] depositFieldArray = { deposit_materialIdField,
				deposit_stockIdField, deposit_depositNumField };

		for (int i = 0; i < depositLabelArray.length; i++) {
			sub_operatePanel.add(depositLabelArray[i]);
			sub_operatePanel.add(depositFieldArray[i]);
		}

		return sub_operate();

	}

	// �ṩ
	private JTable offer_display_panel() {
		tableOffer = new JTable(new OfferTableModel()){
			
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				if (realColumnIndex == 0) { 
					//����ID��ȡ����
					tip = "This supplier's origanization is : "
							+ DBSupplier.readSupplierSingle(Integer
									.parseInt(String.valueOf(getValueAt(rowIndex, colIndex)))).getOriganization();
				} else if (realColumnIndex == 1) {
					tip = "This material's name is :"
							+ DBMaterial.readMaterialSingle(Integer
									.parseInt(String.valueOf(getValueAt(rowIndex, colIndex)))).getMaterialName();
				} else if (realColumnIndex == 2) {
					tip = "The offer number is : "
						+ getValueAt(rowIndex, colIndex);
				} else {// another column
					// You can omit this part if you know you don't
					// have any renderers that supply their own tool
					// tips.
					tip = super.getToolTipText(e);
				}
				return tip;
			}
		};
		tableOffer.add(tableOffer.getTableHeader(), BorderLayout.PAGE_START);
		tableOffer.setAutoCreateRowSorter(true);
		
		sorter = new TableRowSorter(new OfferTableModel()); 
	    tableOffer.setRowSorter(sorter); //ΪJTable����������
	    
		return tableOffer;
	}

	private JPanel offer_operate_panel() {
		offerLabel = new JLabel("�ṩ��Ϣ");
		offerLabel.setFont(font0);
		label_operatePanel.add(offerLabel);
		// �������:���
		sub_operatePanel = new JPanel(new GridLayout(3, 2));
		sub_operatePanel.setBorder(BorderFactory.createEtchedBorder());

		offer_supplierIdLabel = new JLabel("SupplierId:");
		offer_supplierIdField = new JTextField(6);
		offer_materialIdLabel = new JLabel("MaterialId:");
		offer_materialIdField = new JTextField(6);
		offer_offerNumLabel = new JLabel("OfferNum:");
		offer_offerNumField = new JTextField(6);

		JLabel[] offerLabelArray = { offer_supplierIdLabel,
				offer_materialIdLabel, offer_offerNumLabel };
		JTextField[] offerFieldArray = { offer_supplierIdField,
				offer_materialIdField, offer_offerNumField };

		for (int i = 0; i < offerLabelArray.length; i++) {
			sub_operatePanel.add(offerLabelArray[i]);
			sub_operatePanel.add(offerFieldArray[i]);
		}

		return sub_operate();
	}

	// ʹ��

	private JTable used_display_panel() {
		tableUsed = new JTable(new UsedTableModel()){
			
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				if (realColumnIndex == 0) { 
					//����ID��ȡ����
					tip = "The worker name is :"
						+ DBWorker.readWorkerSingle(Integer
								.parseInt(String.valueOf(getValueAt(rowIndex, colIndex)))).getWorkerName();
				} else if (realColumnIndex == 1) {
					tip = "This material's name is :"
						+ DBMaterial.readMaterialSingle(Integer
								.parseInt(String.valueOf(getValueAt(rowIndex, colIndex)))).getMaterialName();
				} else if (realColumnIndex == 2) {
					tip = "This material used number is : "
						+ getValueAt(rowIndex, colIndex);
				} else {// another column
					// You can omit this part if you know you don't
					// have any renderers that supply their own tool
					// tips.
					tip = super.getToolTipText(e);
				}
				return tip;
			}
			
		};
		tableUsed.add(tableUsed.getTableHeader(), BorderLayout.PAGE_START);

		tableUsed.setAutoCreateRowSorter(true);
		
		sorter = new TableRowSorter(new UsedTableModel()); 
	    tableUsed.setRowSorter(sorter); //ΪJTable����������
	    
		return tableUsed;
	}

	private JPanel used_operate_panel() {
		usedLabel = new JLabel("ʹ����Ϣ");
		usedLabel.setFont(font0);
		label_operatePanel.add(usedLabel);
		// �������:���
		sub_operatePanel = new JPanel(new GridLayout(3, 2));
		sub_operatePanel.setBorder(BorderFactory.createEtchedBorder());
		used_workerIdLabel = new JLabel("WorkerId:");
		used_workerIdField = new JTextField(6);
		used_materialIdLabel = new JLabel("MaterialId:");
		used_materialIdField = new JTextField(6);
		used_materialUsedNumLabel = new JLabel("MaterialUsedNum:");
		used_materialUsedNumField = new JTextField(6);

		JLabel[] usedLabelArray = { used_workerIdLabel, used_materialIdLabel,
				used_materialUsedNumLabel };
		JTextField[] usedFieldArray = { used_workerIdField,
				used_materialIdField, used_materialUsedNumField };
		for (int i = 0; i < usedLabelArray.length; i++) {
			sub_operatePanel.add(usedLabelArray[i]);
			sub_operatePanel.add(usedFieldArray[i]);
		}

		return sub_operate();

	}

	// //////////////////////////////////////////////////////////////////////

	/**
	 * operate_panel���Ӳ��֣����ĵ���
	 * 
	 * @return
	 */
	private JPanel sub_operate() {
		button_operatePanel = new JPanel(new FlowLayout());
		button_operatePanel.setBorder(BorderFactory.createEtchedBorder());
		addButton = new JButton("���");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionAddButton();
			}
		});
		delButton = new JButton("ɾ��");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionDelButton();
			}
		});
		queryButton = new JButton("��ѯ");
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionQueryButton();
			}
		});
		updateButton = new JButton("����");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionUpdateButton();
			}
		});
		button_operatePanel.add(addButton);
		button_operatePanel.add(delButton);
		button_operatePanel.add(queryButton);
		button_operatePanel.add(updateButton);

		// ���������
		operatePanel = new JPanel(new BorderLayout());
		operatePanel.setBorder(BorderFactory.createTitledBorder("������Ϣ"));
		operatePanel.add(label_operatePanel, BorderLayout.NORTH);
		operatePanel.add(sub_operatePanel, BorderLayout.CENTER);
		operatePanel.add(button_operatePanel, BorderLayout.SOUTH);

		return operatePanel;

	}

	// /////////////////////////////////////////////////////////////////////

	// ��ʾ����ӡ��ť��Ӧ
	private void actionTablePrint() {
		int s = getSelect();
		switch (s) {
		case GROUP: {
			makePrint(tableGroup);
			break;
		}
		case ACTIVITY: {
			makePrint(tableActivity);
			break;
		}
		case STOCK: {
			makePrint(tableStock);
			break;
		}
		case MATERIAL: {
			makePrint(tableMaterial);
			break;
		}
		case SUPPLIER: {
			makePrint(tableSupplier);
			break;
		}
		case WORKER: {
			makePrint(tableWorker);
			break;
		}
		case CHARGE: {
			makePrint(tableCharge);
			break;
		}
		case DEPOSIT: {
			makePrint(tableDeposit);
			break;
		}
		case USED: {
			makePrint(tableUsed);
			break;
		}
		case OFFER: {
			makePrint(tableOffer);
			break;
		}
		default:
			JOptionPane.showMessageDialog(null, "Print Failed !", "",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// ִ�д�ӡ����
	private void makePrint(JTable table) {
		/*
		 * try { table.print(); } catch (PrinterException e) {
		 * e.printStackTrace(); JOptionPane.showMessageDialog(null, "Print
		 * Failed !","",JOptionPane.INFORMATION_MESSAGE); }
		 */
		try {
			if (!table.print()) {
				System.err.println("User cancelled printing");
			}
		} catch (PrinterException e) {
			JOptionPane.showMessageDialog(null, "Print Failed !", "",
					JOptionPane.INFORMATION_MESSAGE);
			System.err.format("Cannot print %s%n", e.getMessage());
		}
	}

	// ���������Ӱ�ť��Ӧ
	public void actionAddButton() {
		int s = getSelect();
		switch (s) {
		case GROUP: {
			Group group = new Group(Integer.valueOf(groupIdField.getText()),
					groupNameField.getText(), groupDutyField.getText(),
					officeField.getText(), workTimeField.getText());
			if (DBGroup.groupInsert(group)) {
				makeAction(GROUP);
				JOptionPane.showMessageDialog(null, "Insert success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Insert Failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}
			break;
		}
		case ACTIVITY: {
			Activity activity = new Activity(Integer.valueOf(activityIdField
					.getText()), activityNameField.getText(),
					activityPlaceField.getText(), activityTimeField.getText(),
					activityContactField.getText(), Integer
							.valueOf(activityPersonNumField.getText()));
			if (DBActivity.activityInsert(activity)) {
				makeAction(ACTIVITY);
				JOptionPane.showMessageDialog(null, "Insert success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Insert failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}
			break;
		}
		case STOCK: {
			Stock stock = new Stock(Integer.valueOf(stockIdField.getText()),
					stockPlaceField.getText(), stockTelField.getText(),
					stockAreaField.getText());
			if (DBStock.stockInsert(stock)) {
				makeAction(STOCK);
				JOptionPane.showMessageDialog(null, "Insert success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Insert failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}
			break;

		}
		case MATERIAL: {
			Material material = new Material(Integer.valueOf(materialIdField
					.getText()), materialNameField.getText(), materialUsedField
					.getText());
			if (DBMaterial.materialInsert(material)) {
				makeAction(MATERIAL);
				JOptionPane.showMessageDialog(null, "Insert success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Insert failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}
			break;
		}
		case SUPPLIER: {
			Supplier supplier = new Supplier(Integer.valueOf(supplierIdField
					.getText()), origanizationField.getText(), addressField
					.getText(), contactTelField.getText());

			if (DBSupplier.supplierInsert(supplier)) {
				makeAction(SUPPLIER);
				JOptionPane.showMessageDialog(null, "Insert success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Insert failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}
			break;
		}
		case WORKER: {
			Worker worker = new Worker(
					Integer.valueOf(workerIdField.getText()), workerNameField
							.getText(), workerTelField.getText(),
					workerSexField.getText());

			if (DBWorker.workerInsert(worker)) {
				makeAction(WORKER);
				JOptionPane.showMessageDialog(null, "Insert success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Insert failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}

			break;

		}
		case CHARGE: {
			Charge charge = new Charge(Integer.valueOf(charge_groupIdField
					.getText()), Integer
					.valueOf(charge_workerIdField.getText()), Integer
					.valueOf(charge_activityIdField.getText()),
					charge_chargeTimeField.getText());

			if (DBCharge.chargeInsert(charge)) {
				makeAction(CHARGE);
				JOptionPane.showMessageDialog(null, "Insert success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Insert failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}

			break;
		}
		case DEPOSIT: {
			Deposit deposit = new Deposit(Integer
					.valueOf(deposit_materialIdField.getText()), Integer
					.valueOf(deposit_stockIdField.getText()), Integer
					.valueOf(deposit_depositNumField.getText()));

			if (DBDeposit.depositInsert(deposit)) {
				makeAction(DEPOSIT);
				JOptionPane.showMessageDialog(null, "Insert success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Insert failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}

			break;
		}
		case USED: {
			Used used = new Used(Integer.valueOf(used_workerIdField.getText()),
					Integer.valueOf(used_materialIdField.getText()), Integer
							.valueOf(used_materialUsedNumField.getText()));

			if (DBUsed.usedInsert(used)) {
				makeAction(USED);
				JOptionPane.showMessageDialog(null, "Insert success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Insert failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}

			break;
		}
		case OFFER: {
			Offer offer = new Offer(Integer.valueOf(offer_supplierIdField
					.getText()), Integer.valueOf(offer_materialIdField
					.getText()), Integer.valueOf(offer_offerNumField.getText()));

			if (DBOffer.offerInsert(offer)) {
				makeAction(OFFER);
				JOptionPane.showMessageDialog(null, "Insert success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Insert failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}

			break;
		}
		default:
			JOptionPane.showMessageDialog(null, "Insert Failed !", "",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// �������ɾ����ť��Ӧ
	public void actionDelButton() {
		int s = getSelect();
		switch (s) {
		case GROUP: {
			System.out.println(DBGroup.groupDel(Integer.valueOf(groupIdField
					.getText())));
			makeAction(GROUP);
			JOptionPane.showMessageDialog(null, "Delete success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case ACTIVITY: {
			System.out.println(DBActivity.activityDel(Integer
					.valueOf(activityIdField.getText())));
			makeAction(ACTIVITY);
			JOptionPane.showMessageDialog(null, "Delete success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case STOCK: {
	/*		Stock stock = new Stock(Integer.valueOf(stockIdField.getText()),
					stockPlaceField.getText(), stockPlaceField.getText(),
					stockTelField.getText());
	*/		System.out.println(DBStock.stockDel(Integer.valueOf(stockIdField.getText())));
			makeAction(STOCK);
			JOptionPane.showMessageDialog(null, "Delete success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;

		}
		case MATERIAL: {
			System.out.println(DBMaterial.materialDel(Integer
					.valueOf(materialIdField.getText())));
			makeAction(MATERIAL);
			JOptionPane.showMessageDialog(null, "Delete success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case SUPPLIER: {
			System.out.println(DBSupplier.supplierDel(Integer
					.valueOf(supplierIdField.getText())));
			makeAction(SUPPLIER);
			JOptionPane.showMessageDialog(null, "Delete success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case WORKER: {
			System.out.println(DBWorker.workerDel(Integer.valueOf(workerIdField
					.getText())));
			makeAction(WORKER);
			JOptionPane.showMessageDialog(null, "Delete success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;

		}
		case CHARGE: {
			System.out.println(DBCharge.chargeDel(Integer
					.valueOf(charge_groupIdField.getText()), Integer
					.valueOf(charge_workerIdField.getText()), Integer
					.valueOf(charge_activityIdField.getText())));
			makeAction(CHARGE);
			JOptionPane.showMessageDialog(null, "Delete success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case DEPOSIT: {
			System.out.println(DBDeposit.depositDel(Integer
					.valueOf(deposit_materialIdField.getText()), Integer
					.valueOf(deposit_stockIdField.getText())));
			makeAction(DEPOSIT);
			JOptionPane.showMessageDialog(null, "Delete success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case USED: {
			System.out.println(DBUsed.usedDel(Integer
					.valueOf(used_workerIdField.getText()), Integer
					.valueOf(used_materialIdField.getText())));
			makeAction(USED);
			JOptionPane.showMessageDialog(null, "Delete success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case OFFER: {
			System.out.println(DBOffer.offerDel(Integer
					.valueOf(offer_supplierIdField.getText()), Integer
					.valueOf(offer_materialIdField.getText())));
			makeAction(OFFER);
			JOptionPane.showMessageDialog(null, "Delete success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		default:
			JOptionPane.showMessageDialog(null, "Delete Failed !", "",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// ////////////////��
	// ��������ѯ��ť��Ӧ
	public void actionQueryButton() {
		int s = getSelect();
		switch (s) {
		case GROUP: {
			Group group = DBGroup.readGroupSingle(Integer.valueOf(groupIdField
					.getText()));

			makeAction(GROUP);

			groupIdField.setText(String.valueOf(group.getGroupId()));
			groupNameField.setText(group.getGroupName());
			groupDutyField.setText(group.getGroupName());
			officeField.setText(group.getOffice());
			workTimeField.setText(group.getWorkTime());

			JOptionPane.showMessageDialog(null, "Query success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case ACTIVITY: {
			Activity activity = DBActivity.readActivitySingle(Integer
					.valueOf(activityIdField.getText()));

			makeAction(ACTIVITY);

			activityIdField.setText(String.valueOf(activity.getActivityId()));
			activityNameField.setText(activity.getActivityName());
			activityPlaceField.setText(activity.getActivityPlace());
			activityTimeField.setText(activity.getActivityTime());
			activityContactField.setText(activity.getActivityContact());
			activityPersonNumField.setText(String.valueOf(activity
					.getActivityPersonNum()));

			JOptionPane.showMessageDialog(null, "Query success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case STOCK: {

			Stock stock = DBStock.readStockSingle(Integer.valueOf(stockIdField
					.getText()));

			makeAction(STOCK);

			stockIdField.setText(String.valueOf(stock.getStockId()));
			stockPlaceField.setText(stock.getStockPlace());
			stockTelField.setText(stock.getStockTel());
			stockAreaField.setText(stock.getStockArea());

			JOptionPane.showMessageDialog(null, "Query success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;

		}
		case MATERIAL: {
			Material material = DBMaterial.readMaterialSingle(Integer
					.valueOf(materialIdField.getText()));

			makeAction(MATERIAL);

			materialIdField.setText(String.valueOf(material.getMaterialId()));
			materialNameField.setText(material.getMaterialName());
			materialUsedField.setText(material.getMaterialUsed());

			JOptionPane.showMessageDialog(null, "Query success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case SUPPLIER: {
			Supplier supplier = DBSupplier.readSupplierSingle(Integer
					.valueOf(supplierIdField.getText()));

			makeAction(SUPPLIER);

			supplierIdField.setText(String.valueOf(supplier.getSupplierId()));
			origanizationField.setText(supplier.getOriganization());
			addressField.setText(supplier.getAddress());
			contactTelField.setText(supplier.getContactTel());

			JOptionPane.showMessageDialog(null, "Query success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case WORKER: {
			Worker worker = DBWorker.readWorkerSingle(Integer
					.valueOf(workerIdField.getText()));

			makeAction(WORKER);
			workerIdField.setText(String.valueOf(worker.getWorkerId()));
			workerNameField.setText(worker.getWorkerName());
			workerTelField.setText(worker.getWorkerTel());
			workerSexField.setText(worker.getWorkerSex());

			JOptionPane.showMessageDialog(null, "Query success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;

		}
		case CHARGE: {
			Charge charge = DBCharge.readChargeSingle(Integer
					.valueOf(charge_groupIdField.getText()), Integer
					.valueOf(charge_workerIdField.getText()), Integer
					.valueOf(charge_activityIdField.getText()));

			makeAction(CHARGE);
			charge_groupIdField.setText(String.valueOf(charge.getGroupId()));
			charge_workerIdField.setText(String.valueOf(charge.getWorkerId()));
			charge_activityIdField.setText(String.valueOf(charge
					.getActivityId()));
			charge_chargeTimeField.setText(String.valueOf(charge
					.getChargeTime()));

			JOptionPane.showMessageDialog(null, "Query success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case DEPOSIT: {
			Deposit deposit = DBDeposit.readSingle(Integer
					.valueOf(deposit_materialIdField.getText()), Integer
					.valueOf(deposit_stockIdField.getText()));

			makeAction(DEPOSIT);

			deposit_materialIdField.setText(String.valueOf(deposit
					.getMaterialId()));
			deposit_stockIdField.setText(String.valueOf(deposit.getStockId()));
			deposit_depositNumField.setText(String.valueOf(deposit
					.getDepositNum()));

			JOptionPane.showMessageDialog(null, "Query success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case USED: {
			Used used = DBUsed.readSingle(Integer.valueOf(used_workerIdField
					.getText()), Integer
					.valueOf(used_materialIdField.getText()));

			makeAction(USED);

			used_workerIdField.setText(String.valueOf(used.getWorkerId()));
			used_materialIdField.setText(String.valueOf(used.getMaterialId()));
			used_materialUsedNumField.setText(String.valueOf(used
					.getMaterialUsedNum()));

			JOptionPane.showMessageDialog(null, "Query success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		case OFFER: {
			Offer offer = DBOffer.readSingle(Integer
					.valueOf(offer_supplierIdField.getText()), Integer
					.valueOf(offer_materialIdField.getText()));

			makeAction(OFFER);

			offer_supplierIdField
					.setText(String.valueOf(offer.getSupplierId()));
			offer_materialIdField
					.setText(String.valueOf(offer.getMaterialId()));
			offer_offerNumField.setText(String.valueOf(offer.getOfferNum()));

			JOptionPane.showMessageDialog(null, "Query success !", "",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
		default:
			JOptionPane.showMessageDialog(null, "Query Failed !", "",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// ���������°�ť��Ӧ
	public void actionUpdateButton() {
		int s = getSelect();
		switch (s) {
		case GROUP: {
			Group group = new Group(Integer.valueOf(groupIdField.getText()),
					groupNameField.getText(), groupDutyField.getText(),
					officeField.getText(), workTimeField.getText());

			if (DBGroup.groupUpdate(group)) {
				makeAction(GROUP);
				JOptionPane.showMessageDialog(null, "Update success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Update failed !", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		}
		case ACTIVITY: {
			Activity activity = new Activity(Integer.valueOf(activityIdField
					.getText()), activityNameField.getText(),
					activityPlaceField.getText(), activityTimeField.getText(),
					activityContactField.getText(), Integer
							.valueOf(activityPersonNumField.getText()));

			if (DBActivity.activityUpdate(activity)) {
				makeAction(ACTIVITY);
				JOptionPane.showMessageDialog(null, "Update success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Update failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}

			break;
		}
		case STOCK: {
			Stock stock = new Stock(Integer.valueOf(stockIdField.getText()),
					stockPlaceField.getText(), stockPlaceField.getText(),
					stockTelField.getText());

			if (DBStock.stockUpdate(stock)) {
				makeAction(STOCK);
				JOptionPane.showMessageDialog(null, "Update success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Update failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}

			break;

		}
		case MATERIAL: {
			Material material = new Material(Integer.valueOf(materialIdField
					.getText()), materialNameField.getText(), materialUsedField
					.getText());

			if (DBMaterial.materialUpdate(material)) {
				makeAction(MATERIAL);
				JOptionPane.showMessageDialog(null, "Update success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Update failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}

			break;
		}
		case SUPPLIER: {
			Supplier supplier = new Supplier(Integer.valueOf(supplierIdField
					.getText()), origanizationField.getText(), addressField
					.getText(), contactTelField.getText());
			if (DBSupplier.supplierUpdate(supplier)) {
				makeAction(SUPPLIER);
				JOptionPane.showMessageDialog(null, "Update success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Update failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}

			break;
		}
		case WORKER: {
			Worker worker = new Worker(
					Integer.valueOf(workerIdField.getText()), workerNameField
							.getText(), workerTelField.getText(),
					workerSexField.getText());

			if (DBWorker.workerUpdate(worker)) {
				makeAction(WORKER);
				JOptionPane.showMessageDialog(null, "Update success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Update failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}

			break;

		}
		case CHARGE: {
			Charge charge = new Charge(Integer.valueOf(charge_groupIdField
					.getText()), Integer
					.valueOf(charge_workerIdField.getText()), Integer
					.valueOf(charge_activityIdField.getText()),
					charge_chargeTimeField.getText());

			if (DBCharge.chargeUpdate(charge)) {
				makeAction(CHARGE);
				JOptionPane.showMessageDialog(null, "Update success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Update failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}

			break;
		}
		case DEPOSIT: {
			Deposit deposit = new Deposit(Integer
					.valueOf(deposit_materialIdField.getText()), Integer
					.valueOf(deposit_stockIdField.getText()), Integer
					.valueOf(deposit_depositNumField.getText()));

			if (DBDeposit.depositUpdate(deposit)) {
				makeAction(DEPOSIT);
				JOptionPane.showMessageDialog(null, "Update success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Update failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}

			break;
		}
		case USED: {
			Used used = new Used(Integer.valueOf(used_workerIdField.getText()),
					Integer.valueOf(used_materialIdField.getText()), Integer
							.valueOf(used_materialUsedNumField.getText()));

			if (DBUsed.usedUpdate(used)) {
				makeAction(USED);
				JOptionPane.showMessageDialog(null, "Update success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Update failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}
			break;
		}
		case OFFER: {
			Offer offer = new Offer(Integer.valueOf(offer_supplierIdField
					.getText()), Integer.valueOf(offer_materialIdField
					.getText()), Integer.valueOf(offer_offerNumField.getText()));

			if (DBOffer.offerUpdate(offer)) {
				makeAction(OFFER);
				JOptionPane.showMessageDialog(null, "Update success !", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Update failed !", "",
						JOptionPane.WARNING_MESSAGE);
			}
			break;
		}
		default:
			JOptionPane.showMessageDialog(null, "Update Failed !", "",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * �˵�����group��ť����Ӧ
	 */
	private void actionGroupItemButton() {
		makeAction(select);
		// makeAction(GROUP);
	}

	private void actionActivityItmeButton() {
		makeAction(select);
		// makeAction(ACTIVITY);
	}

	private void actionStockItemButton() {
		makeAction(select);
		// makeAction(STOCK);
	}

	private void actionMaterialItemButton() {
		makeAction(select);
		// makeAction(MATERIAL);
	}

	private void actionSupplierItemButton() {
		makeAction(select);
		// makeAction(SUPPLIER);
	}

	private void actionWorkerItemButton() {
		makeAction(select);
		// makeAction(WORKER);
	}

	private void actionChargeItemButton() {
		makeAction(select);
	}

	private void actionDepositItemButton() {
		makeAction(select);
	}

	private void actionUsedItemButton() {
		makeAction(select);
	}

	private void actionOfferItemButton() {
		makeAction(select);
	}

	private void makeAction(int select) {
		finalPanel.setVisible(false);// ////////////////////////////////////////
		finalPanel = display_operate_panel(select);
		finalPanel.setBorder(BorderFactory.createRaisedBevelBorder());// ���߿�
		contentPanel.add(finalPanel, BorderLayout.CENTER);
		finalPanel.setVisible(true);
	}

	/**
	 * ����
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LogisticGui gui = new LogisticGui();
		gui.setVisible(true);
	}
}
