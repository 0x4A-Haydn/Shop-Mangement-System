package ManagementSystem;

import DatabaseOperation.Operation;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import DatabaseOperation.Operation;

public class ShopManagement extends  JFrame {

    JPanel settingP,titleP;
    JLabel titleL;

    ButtonGroup option;
    JRadioButton optionA;
    JRadioButton optionB;

    public void init(){

        settingP = new JPanel();
        titleP=new JPanel();

        titleL = new JLabel("欢迎使用ShopWeb后台管理系统",JLabel.CENTER);
        titleL.setFont(new Font("微软雅黑",1, 18));

        option = new ButtonGroup();//Create a button group
        optionA = new JRadioButton("商家模式");
        optionB = new JRadioButton("管理员模式");

        option.add(optionA);
        option.add(optionB);

        settingP.setLayout(new GridLayout(2,1));
        settingP.add(optionA);
        settingP.add(optionB);
        settingP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1),
                "请选择一个系统模式", TitledBorder.LEFT, TitledBorder.TOP,
                new java.awt.Font("微软雅黑", 1, 13)));

        titleP.setLayout(new GridLayout(2,1));
        titleP.add(titleL);
        this.add(titleP,BorderLayout.NORTH);
        this.getContentPane().add(settingP);

        this.setTitle("Shop Management System.");
        this.setSize(400,200);
        this.setLocation(100, 100);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.repaint();

        optionA.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                JFrame signInF = new JFrame("Sign in");
                JButton okB=new JButton("确认");

                JTextField txtSellerID = new JTextField("请输入商家ID");
                JPasswordField pass=new JPasswordField("请输入密码");
                signInF.getContentPane().add(pass);
                signInF.getContentPane().add(txtSellerID,BorderLayout.NORTH);
                signInF.getContentPane().add(okB,BorderLayout.SOUTH);

                okB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        try {
                            String SellerID = txtSellerID.getText();
                            String Pass = pass.getText();
                            int judge = Operation.checkSellerInfo(SellerID,Pass);
                            if (judge == 1) {
                                signInF.setVisible(false);
                                Seller seller= new Seller(SellerID);
                            } else if (judge == -1) {
                                JFrame failF = new JFrame("Fail.");
                                failF.setSize(300, 150);
                                failF.repaint();
                                JPanel failP = new JPanel(new BorderLayout());
                                JLabel failL = new JLabel("Seller do not exist.", JLabel.CENTER);
                                failL.setSize(200, 50);
                                failL.setFont(new Font("微软雅黑", 1, 12));
                                failP.add(failL, BorderLayout.CENTER);
                                failF.add(failP);
                                failF.setVisible(true);
                            } else if (judge == 0) {
                                JFrame failF = new JFrame("Fail.");
                                failF.setSize(300, 150);
                                failF.repaint();
                                JPanel failP = new JPanel(new BorderLayout());
                                JLabel failL = new JLabel("Wrong Password. ", JLabel.CENTER);
                                failL.setSize(200, 50);
                                failL.setFont(new Font("微软雅黑", 1, 12));
                                failP.add(failL, BorderLayout.CENTER);
                                failF.add(failP);
                                failF.setVisible(true);
                            }
                        } catch (Exception e) {
                            JFrame failF = new JFrame("Fail.");
                            failF.setSize(300, 150);
                            failF.repaint();
                            JPanel failP = new JPanel(new BorderLayout());
                            JLabel failL = new JLabel("Error! " + e.getMessage(), JLabel.CENTER);
                            failL.setSize(200, 50);
                            failL.setFont(new Font("微软雅黑", 1, 12));
                            failP.add(failL, BorderLayout.CENTER);
                            failF.add(failP);
                            failF.setVisible(true);
                        }
                    }
                });
                signInF.setSize(250, 100);
                signInF.setVisible(true);
            }
        });
        optionB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                JFrame signInF = new JFrame("Sign in");
                JButton okB=new JButton("确认");
                JPasswordField pass=new JPasswordField("请输入密码");
                JTextField txtAdminID = new JTextField("请输入管理员ID");
                signInF.getContentPane().add(txtAdminID,BorderLayout.NORTH);
                signInF.getContentPane().add(pass);
                signInF.getContentPane().add(okB,BorderLayout.SOUTH);

                okB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        String AdminID = txtAdminID.getText();
                        String Pass = pass.getText();
                        try {
                            final String SellerID = txtAdminID.getText();
                            int judge=Operation.checkAdminInfo(AdminID,Pass);
                            if(judge==1){
                                signInF.setVisible(false);
                                Administrator admin=new Administrator();
                            }
                            else if(judge==-1){
                                JFrame failF = new JFrame("Fail.");
                                failF.setSize(300,150);
                                failF.repaint();
                                JPanel failP = new JPanel(new BorderLayout());
                                JLabel failL=new JLabel("Admin do not exist.",JLabel.CENTER);
                                failL.setSize(200,50);
                                failL.setFont(new Font("微软雅黑",1, 12));
                                failP.add(failL,BorderLayout.CENTER);
                                failF.add(failP);
                                failF.setVisible(true);
                            }
                            else if(judge==0){
                                JFrame failF = new JFrame("Fail.");
                                failF.setSize(300,150);
                                failF.repaint();
                                JPanel failP = new JPanel(new BorderLayout());
                                JLabel failL=new JLabel("Wrong Password. ",JLabel.CENTER);
                                failL.setSize(200,50);
                                failL.setFont(new Font("微软雅黑",1, 12));
                                failP.add(failL,BorderLayout.CENTER);
                                failF.add(failP);
                                failF.setVisible(true);
                            }
                        } catch (Exception e) {
                            JFrame failF = new JFrame("Fail.");
                            failF.setSize(300,150);
                            failF.repaint();
                            JPanel failP = new JPanel(new BorderLayout());
                            JLabel failL=new JLabel("Error! "+e.getMessage(),JLabel.CENTER);
                            failL.setSize(200,50);
                            failL.setFont(new Font("微软雅黑",1, 12));
                            failP.add(failL,BorderLayout.CENTER);
                            failF.add(failP);
                            failF.setVisible(true);
                        }
                    }
                });
                signInF.setSize(250, 100);
                signInF.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        ShopManagement shopManagementSystem=new ShopManagement();
        shopManagementSystem.init();
        Operation.Connect();
    }
}