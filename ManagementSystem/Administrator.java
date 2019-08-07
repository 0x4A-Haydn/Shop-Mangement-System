package ManagementSystem;

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
import java.sql.*;
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

public class Administrator extends JFrame {
    JLabel adminL = new JLabel("欢迎使用ShopWeb后台管理系统",JLabel.CENTER);
    JLabel adminLI= new JLabel();
    JButton userManagementB = new JButton("用户管理");
    JButton sellerManagementB = new JButton("商家管理");
    JButton queryOrderB = new JButton("订单查询");

    static String sStartDate = null;// 记录String型日期
    static String sEndDate = null;// 记录String型日期
    static String sTempDate = null;// 注意这是临时记录字符串型日期

    static java.util.Date dStartDate = null;
    static java.util.Date dEndDate = null;
    static java.util.Date dTempDate = null;// 注意这是临时记录Date型日期

    public Administrator() {
        this.getContentPane().setLayout(new GridLayout(5, 1,5,5));
        adminLI.setIcon(new ImageIcon("src/image/user1.jpg"));
        adminL.setFont(new Font("微软雅黑",1, 20));
        userManagementB.setBorder(BorderFactory.createRaisedBevelBorder());
        sellerManagementB.setBorder(BorderFactory.createRaisedBevelBorder());
        queryOrderB.setBorder(BorderFactory.createRaisedBevelBorder());

        this.getContentPane().add(adminLI);
        this.getContentPane().add(adminL);
        this.getContentPane().add(userManagementB);
        this.getContentPane().add(sellerManagementB);
        this.getContentPane().add(queryOrderB);

        this.setTitle("Welcome");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        userManagementB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //声明组件
                JFrame cusManagementF = new JFrame("Customer Management");
                JPanel cusManagementP = new JPanel();
                JLabel cusManagementL = new JLabel();
                //用户管理按钮
                JButton addCusB = new JButton("添加用户信息");
                JButton deleteCusB = new JButton("删除用户信息");
                JButton updateCusB = new JButton("更改用户信息");
                JButton queryCusB = new JButton("查询用户信息");
                //整体布局
                cusManagementF.setLayout(new GridLayout(1, 2));
                //设置Label图像
                cusManagementL.setIcon(new ImageIcon("src/image/j.jpg"));
                //添加组件
                cusManagementF.getContentPane().add(cusManagementL);
                cusManagementF.getContentPane().add(cusManagementP );
                //按钮布局
                cusManagementP .setLayout(new GridLayout(2, 2,5,5));
                cusManagementP .add(addCusB);
                cusManagementP .add(deleteCusB);
                cusManagementP .add(updateCusB);
                cusManagementP .add(queryCusB);
                //Frame设置
                cusManagementF.setSize(460, 200);
                cusManagementF.setVisible(true);

                addCusB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame addCustomerInfoF = new JFrame("Add Customer Info");
                        addCustomerInfoF.setLayout(new GridLayout(6, 1));
                        JButton okB=new JButton("确认");

                        final JTextField txtNickName = new JTextField("用户名");
                        final JTextField txtAge = new JTextField("年龄");
                        final JTextField txtGender = new JTextField("性别：1-男/2-女");
                        final JTextField txtDoB = new JTextField("出生日期：xxxx-xx-xx");
                        final JTextField txtPhoneNo = new JTextField("手机号：11位");

                        addCustomerInfoF.getContentPane().add(txtNickName);
                        addCustomerInfoF.getContentPane().add(txtAge);
                        addCustomerInfoF.getContentPane().add(txtGender);
                        addCustomerInfoF.getContentPane().add(txtDoB);
                        addCustomerInfoF.getContentPane().add(txtPhoneNo);
                        addCustomerInfoF.getContentPane().add(okB);
                        String passWords=new PasswordGenerator(11, 6).generateRandomPassword();
                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                               try {
                                    String customerID =""+(int)((Math.random()*9+1)*10000000);
                                    while(Operation.checkCustomerInfo(customerID)==false)
                                    {
                                        customerID =""+(int)((Math.random()*9+1)*10000000);
                                    }

                                    final String nickName = txtNickName.getText();
                                    final int age = Integer.parseInt(txtAge.getText());
                                    final String gender = txtGender.getText();
                                    final java.sql.Date DoB =java.sql.Date.valueOf(txtDoB.getText());
                                    final String phoneNumber = txtPhoneNo.getText();

                                    Operation.insertCustomerInfo(customerID,nickName,age,gender,DoB,passWords,phoneNumber);
                                    JFrame successF = new JFrame("Success.");
                                    successF.setSize(400,150);
                                    successF.repaint();
                                    JPanel successP = new JPanel(new BorderLayout());
                                    JLabel successL=new JLabel("Insert success. Customer ID is "+customerID, JLabel.CENTER);
                                    successL.setSize(400,50);
                                    successL.setFont(new Font("微软雅黑",1, 15));
                                    successP.add(successL,BorderLayout.CENTER);
                                    successF.add(successP);
                                    successF.setVisible(true);

                               } catch (Exception e) {
                                   JFrame failF = new JFrame("Fail.");
                                   failF.setSize(300,150);
                                   failF.repaint();
                                   JPanel failP = new JPanel(new BorderLayout());
                                   JLabel failL=new JLabel("Error! "+e.getMessage(),JLabel.CENTER);
                                   failL.setSize(200,50);
                                   failL.setFont(new Font("微软雅黑",1, 15));
                                   failP.add(failL,BorderLayout.CENTER);
                                   failF.add(failP);
                                   failF.setVisible(true);
                                    //e.printStackTrace();
                                }
                            }
                        });
                        addCustomerInfoF.setSize(250, 230);
                        addCustomerInfoF.setVisible(true);
                    }
                });

                deleteCusB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame deleteCustomerInfoF = new JFrame("Delete Customer Info");
                        JButton okB=new JButton("确认");

                        final JTextField txtCustomerID = new JTextField("请输入删除用户ID");
                        deleteCustomerInfoF.getContentPane().add(txtCustomerID,BorderLayout.NORTH);
                        deleteCustomerInfoF.getContentPane().add(okB,BorderLayout.SOUTH);

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    final String customerID = txtCustomerID.getText();

                                    Operation.deleteCustomerInfo(customerID);
                                    JFrame successF = new JFrame("Success.");
                                    successF.setSize(100,150);
                                    successF.repaint();
                                    JPanel successP = new JPanel(new BorderLayout());
                                    JLabel successL=new JLabel("Delete success.",JLabel.CENTER);
                                    successL.setSize(100,50);
                                    successL.setFont(new Font("微软雅黑",1, 15));
                                    successP.add(successL,BorderLayout.CENTER);
                                    successF.add(successP);
                                    successF.setVisible(true);

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
                                    //e.printStackTrace();
                                }
                            }
                        });
                        deleteCustomerInfoF.setSize(250, 100);
                        deleteCustomerInfoF.setVisible(true);
                    }
                });

                updateCusB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        final JFrame updateCustomerInfoF = new JFrame("Update Customer Info");
                        final JPanel updateConP=new JPanel();
                        final JButton okB=new JButton("确认");
                        final JScrollPane scrollPaneListName = new JScrollPane();
                        final JTextField txtAttribute = new JTextField("更改属性");
                        final JTextField txtUpdateCon = new JTextField("更改内容");
                        final JTextField txtCustomerID = new JTextField("请输入更新用户ID");

                        updateConP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,0),
                                "请选择一个更改属性",TitledBorder.LEFT, TitledBorder.TOP,
                                new java.awt.Font("微软雅黑",1,12)));
                        txtCustomerID.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                        final String[] updateCon=new String[]{"customerNickName","customerAge","customerGender",
                                                                "customerDoB","customerTelNo"};
                        final JList listName = new JList(updateCon);
                        txtAttribute.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                        txtUpdateCon.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));

                        listName.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent arg0) {
                                txtAttribute.setText("" + listName.getSelectedValue());
                            }
                        });

                        scrollPaneListName.setViewportView(listName);// 把listName放在可滚动面板scrollPane里面
                        updateConP.setLayout(new GridLayout(3,1,5,5));
                        updateConP.add(scrollPaneListName);
                        updateConP.add(txtAttribute);
                        updateConP.add(txtUpdateCon);

                        updateCustomerInfoF.getContentPane().add(txtCustomerID,BorderLayout.NORTH);
                        updateCustomerInfoF.getContentPane().add(okB,BorderLayout.SOUTH);
                        updateCustomerInfoF.getContentPane().add(updateConP,BorderLayout.CENTER);

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    final String customerID = txtCustomerID.getText();
                                    final String attribute = txtAttribute.getText();
                                    final String newInfo = txtUpdateCon.getText();

                                    Operation.updateCustomerInfo(customerID,attribute,newInfo);
                                    JFrame successF = new JFrame("Success.");
                                    successF.setSize(100,150);
                                    successF.repaint();
                                    JPanel successP = new JPanel(new BorderLayout());
                                    JLabel successL=new JLabel("Update success.",JLabel.CENTER);
                                    successL.setSize(100,50);
                                    successL.setFont(new Font("微软雅黑",1, 15));
                                    successP.add(successL,BorderLayout.CENTER);
                                    successF.add(successP);
                                    successF.setVisible(true);

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
                        updateCustomerInfoF.setSize(300, 280);
                        updateCustomerInfoF.setVisible(true);
                    }
                });

                queryCusB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame queryCustomerInfoF = new JFrame("Query Customer Info");
                        JButton okB=new JButton("确认");
                        final JTextField txtCustomerID = new JTextField("请输入查询用户ID");

                        queryCustomerInfoF.getContentPane().add(txtCustomerID,BorderLayout.NORTH);
                        queryCustomerInfoF.getContentPane().add(okB,BorderLayout.SOUTH);

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    final String customerID = txtCustomerID.getText();
                                    String result=Operation.queryCustomerInfo(customerID);

                                    JFrame successF = new JFrame("Success.");
                                    successF.setSize(400,300);
                                    successF.repaint();
                                    JPanel successP = new JPanel(new BorderLayout());
                                    successP.setLayout(new GridLayout(6,1));
                                    JLabel[] infoL=new JLabel[6];
                                    JLabel noInfoL=new JLabel("Customer do not exist.",JLabel.CENTER);
                                    noInfoL.setSize(300, 150);
                                    noInfoL.setFont(new Font("微软雅黑", 1, 15));

                                    StringTokenizer s=new  StringTokenizer(result,",");
                                    String[] temp=new String[infoL.length];
                                    int flag=0;

                                    for(int i=0;i<infoL.length;i++) {
                                        temp[i]= s.nextToken();
                                        StringTokenizer s2=new  StringTokenizer(temp[i],":");
                                        s2.nextToken();
                                        if (s2.nextToken().equals(" ")) {
                                            successP.setLayout(new GridLayout(1, 1));
                                            successP.add(noInfoL);
                                            successF.setSize(300,150);
                                            flag= 1;
                                        }
                                    }

                                    if(flag==0){
                                        for(int i=0;i<infoL.length;i++) {
                                            infoL[i] = new JLabel(temp[i], JLabel.CENTER);
                                            infoL[i].setSize(300, 40);
                                            infoL[i].setFont(new Font("微软雅黑", 1, 15));
                                            successP.add(infoL[i]);
                                        }
                                    }

                                    successF.add(successP);
                                    successF.setVisible(true);

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
                        queryCustomerInfoF.setSize(250, 100);
                        queryCustomerInfoF.setVisible(true);
                    }
                });
            }
        });

        sellerManagementB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //声明组件
                JFrame sellerManagementF = new JFrame("Seller Management");
                JPanel sellerManagementP = new JPanel();
                JLabel sellerManagementL = new JLabel();
                //用户管理按钮
                JButton addSellerB = new JButton("添加商家信息");
                JButton deleteSellerB = new JButton("删除商家信息");
                JButton updateSellerB = new JButton("更改商家信息");
                JButton querySellerB = new JButton("查询商家信息");
                //整体布局
                sellerManagementF.setLayout(new GridLayout(3, 3));
                sellerManagementF.setLayout(new GridLayout(1, 2));
                //设置Label图像
                sellerManagementL.setIcon(new ImageIcon("src/image/j.jpg"));
                //添加组件
                sellerManagementF.getContentPane().add(sellerManagementL);
                sellerManagementF.getContentPane().add(sellerManagementP );
                //按钮布局
                sellerManagementP .setLayout(new GridLayout(2, 2,5,5));
                sellerManagementP .add(addSellerB);
                sellerManagementP .add(deleteSellerB);
                sellerManagementP .add(updateSellerB);
                sellerManagementP .add(querySellerB);
                //Frame设置
                sellerManagementF.setSize(460, 200);
                sellerManagementF.setVisible(true);

                addSellerB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame addSellerInfoF = new JFrame("Add Seller Info");
                        addSellerInfoF.setLayout(new GridLayout(3, 1));
                        JButton okB=new JButton("确认");

                        final JTextField txtShopName = new JTextField("店铺名称");
                        final JTextField txtSellerAddress = new JTextField("店铺地址");

                        addSellerInfoF.getContentPane().add(txtShopName);
                        addSellerInfoF.getContentPane().add(txtSellerAddress);
                        addSellerInfoF.getContentPane().add(okB);

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    String sellerID =""+(int)((Math.random()*9+1)*100000);
                                    while(Operation.checkSellerInfo(sellerID)==true)
                                        sellerID =""+(int)((Math.random()*9+1)*100000);
                                    final String shopName = txtShopName.getText();
                                    final String shopAddress = txtSellerAddress.getText();
                                    final java.sql.Date shopOpenTime=  new java.sql.Date(System.currentTimeMillis());
                                    String passWords=new PasswordGenerator(11, 6).generateRandomPassword();

                                    Operation.insertSellerInfo(sellerID,shopName,shopAddress,shopOpenTime,passWords);

                                    JFrame successF = new JFrame("Success. ");
                                    successF.setSize(400,150);
                                    successF.repaint();
                                    JPanel successP = new JPanel(new BorderLayout());
                                    JLabel successL=new JLabel("Insert success. Seller's ID is "+sellerID,JLabel.CENTER);
                                    successL.setSize(400,50);
                                    successL.setFont(new Font("微软雅黑",1, 15));
                                    successP.add(successL,BorderLayout.CENTER);
                                    successF.add(successP);
                                    successF.setVisible(true);

                                } catch (Exception e) {
                                    JFrame failF = new JFrame("Fail.");
                                    failF.setSize(300,150);
                                    failF.repaint();
                                    JPanel failP = new JPanel(new BorderLayout());
                                    JLabel failL=new JLabel("Error! "+e.getMessage(),JLabel.CENTER);
                                    failL.setSize(200,50);
                                    failL.setFont(new Font("微软雅黑",1, 15));
                                    failP.add(failL,BorderLayout.CENTER);
                                    failF.add(failP);
                                    failF.setVisible(true);
                                    //e.printStackTrace();
                                }
                            }
                        });
                        addSellerInfoF.setSize(250, 150);
                        addSellerInfoF.setVisible(true);
                    }
                });

                deleteSellerB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame deleteSellerInfoF = new JFrame("Delete Seller Info");
                        JButton okB=new JButton("确认");

                        final JTextField txtCustomerID = new JTextField("请输入删除商家ID");
                        deleteSellerInfoF.getContentPane().add(txtCustomerID,BorderLayout.NORTH);
                        deleteSellerInfoF.getContentPane().add(okB,BorderLayout.SOUTH);

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    final String customerID = txtCustomerID.getText();

                                    Operation.deleteSellerInfo(customerID);
                                    JFrame successF = new JFrame("Success.");
                                    successF.setSize(100,150);
                                    successF.repaint();
                                    JPanel successP = new JPanel(new BorderLayout());
                                    JLabel successL=new JLabel("Delete success.",JLabel.CENTER);
                                    successL.setSize(100,50);
                                    successL.setFont(new Font("微软雅黑",1, 15));
                                    successP.add(successL,BorderLayout.CENTER);
                                    successF.add(successP);
                                    successF.setVisible(true);

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
                                    //e.printStackTrace();
                                }
                            }
                        });
                        deleteSellerInfoF.setSize(250, 100);
                        deleteSellerInfoF.setVisible(true);
                    }
                });

                updateSellerB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        final JFrame updateSellerInfoF = new JFrame("Update Seller Info");
                        final JPanel updateConP=new JPanel();
                        final JButton okB=new JButton("确认");
                        final JScrollPane scrollPaneListName = new JScrollPane();
                        final JTextField txtAttribute = new JTextField("更改属性");
                        final JTextField txtUpdateCon = new JTextField("更改内容");
                        final JTextField txtSellerID = new JTextField("请输入更新商家ID");

                        updateConP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,0),
                                "请选择一个更改属性",TitledBorder.LEFT, TitledBorder.TOP,new java.awt.Font("微软雅黑",1,12)));
                        txtSellerID.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                        final String[] updateCon=new String[]{"shopName"," sellerAddress", "shopOpenTime"};//匹配数据库中属性名
                        final JList listName = new JList(updateCon);
                        txtAttribute.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                        txtUpdateCon.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));

                        listName.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent arg0) {
                                txtAttribute.setText("" + listName.getSelectedValue());
                            }
                        });

                        scrollPaneListName.setViewportView(listName);// 把listName放在可滚动面板scrollPane里面
                        updateConP.setLayout(new GridLayout(3,1,5,5));
                        updateConP.add(scrollPaneListName);
                        updateConP.add(txtAttribute);
                        updateConP.add(txtUpdateCon);

                        updateSellerInfoF.getContentPane().add(txtSellerID,BorderLayout.NORTH);
                        updateSellerInfoF.getContentPane().add(okB,BorderLayout.SOUTH);
                        updateSellerInfoF.getContentPane().add(updateConP,BorderLayout.CENTER);

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    final String customerID = txtSellerID.getText();
                                    final String attribute = txtAttribute.getText();
                                    final String newInfo = txtUpdateCon.getText();

                                    Operation.updateSellerInfo(customerID,attribute,newInfo);
                                    JFrame successF = new JFrame("Success.");
                                    successF.setSize(100,150);
                                    successF.repaint();
                                    JPanel successP = new JPanel(new BorderLayout());
                                    JLabel successL=new JLabel("Update success.",JLabel.CENTER);
                                    successL.setSize(100,50);
                                    successL.setFont(new Font("微软雅黑",1, 15));
                                    successP.add(successL,BorderLayout.CENTER);
                                    successF.add(successP);
                                    successF.setVisible(true);

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
                        updateSellerInfoF.setSize(300, 300);
                        updateSellerInfoF.setVisible(true);
                    }
                });

                querySellerB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame querySellerInfoF = new JFrame("Query Seller Info");
                        JButton okB=new JButton("确认");
                        final JTextField txtSellerID = new JTextField("请输入查询商家ID");

                        querySellerInfoF.getContentPane().add(txtSellerID,BorderLayout.NORTH);
                        querySellerInfoF.getContentPane().add(okB,BorderLayout.SOUTH);

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    final String sellerID = txtSellerID.getText();
                                    String result=Operation.querySellerInfo(sellerID);

                                    JFrame successF = new JFrame("Success.");
                                    successF.setSize(400,300);
                                    successF.repaint();
                                    JPanel successP = new JPanel(new BorderLayout());
                                    successP.setLayout(new GridLayout(4,1));
                                    JLabel[] infoL=new JLabel[4];
                                    JLabel noInfoL=new JLabel("Seller do not exist.",JLabel.CENTER);
                                    noInfoL.setSize(300, 150);
                                    noInfoL.setFont(new Font("微软雅黑", 1, 15));

                                    StringTokenizer s=new  StringTokenizer(result,",");
                                    String[] temp=new String[infoL.length];
                                    int flag=0;

                                    for(int i=0;i<infoL.length;i++) {
                                        temp[i]= s.nextToken();
                                        StringTokenizer s2=new  StringTokenizer(temp[i],":");
                                        s2.nextToken();
                                        if (s2.nextToken().equals(" ")) {
                                            successP.setLayout(new GridLayout(1, 1));
                                            successP.add(noInfoL);
                                            successF.setSize(300,150);
                                            flag= 1;
                                        }
                                    }

                                    if(flag==0){
                                        for(int i=0;i<infoL.length;i++) {
                                            infoL[i] = new JLabel(temp[i], JLabel.CENTER);
                                            infoL[i].setSize(300, 50);
                                            infoL[i].setFont(new Font("微软雅黑", 1, 15));
                                            successP.add(infoL[i]);
                                        }
                                    }

                                    successF.add(successP);
                                    successF.setVisible(true);

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
                                    //e.printStackTrace();
                                }
                            }
                        });
                        querySellerInfoF.setSize(250, 100);
                        querySellerInfoF.setVisible(true);
                    }
                });

            }
        });

        queryOrderB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //声明组件
                JFrame queryOrderF = new JFrame("Query Order");
                JPanel queryOrderP = new JPanel();
                JLabel queryOrderL = new JLabel();
                //查询按钮
                JButton periodAmountB = new JButton("按时间段查询订单总数及总金额");
                JButton categoryB = new JButton("按类别查询订单总数及总金额");
                JButton periodTopB = new JButton("按时间段查询前十名热销商品");
                JButton TopSellB = new JButton("评价数量和评分前十名商品");
                JButton sellerQueryB = new JButton("按商家查询");
                JButton customerTopB = new JButton("按时间段查询成功下单金额前十名用户");
                JButton monthB = new JButton("每月订单数量和金额情况统计");
                JButton hourB = new JButton("24小时订单数量分布情况");
                //布局设置
                queryOrderF.getContentPane().setLayout(new GridLayout(1, 2));
                queryOrderP.setLayout(new GridLayout(8, 1));
                //背景设置
                queryOrderL.setIcon(new ImageIcon("src/image/123.jpg"));
                //添加组件
                queryOrderF.getContentPane().add(queryOrderL);
                queryOrderF.getContentPane().add(queryOrderP);
                queryOrderP.add(periodAmountB);
                queryOrderP.add(categoryB);
                queryOrderP.add(periodTopB);
                queryOrderP.add(TopSellB);
                queryOrderP.add(sellerQueryB);
                queryOrderP.add(customerTopB);
                queryOrderP.add(monthB);
                queryOrderP.add(hourB);
                //Frame设置
                queryOrderF.setSize(520, 400);
                queryOrderF.setVisible(true);

                periodAmountB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame showInfoF = new JFrame("按时间段查询订单总数及总金额");
                        JPanel dateP = new JPanel();
                        final JTextField txtStartDate = new JTextField("查询开始日期");
                        final JTextField txtEndDate = new JTextField("查询结束日期");
                        final JButton btnChooseDate = new JButton("选择开始日期");
                        final JButton btnChooseDate2 = new JButton("选择结束日期");
                        JButton okB = new JButton("确认");

                        dateP.add(txtStartDate);
                        dateP.add(btnChooseDate);
                        dateP.add(txtEndDate);
                        dateP.add(btnChooseDate2);
                        showInfoF.getContentPane().add(dateP);
                        showInfoF.getContentPane().add(okB, BorderLayout.SOUTH);

                        btnChooseDate.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                JFrame frame = new JFrame("选择开始日期");
                                JPanel cp = new JPanel();
                                DateChooserJButton button = new DateChooserJButton();
                                button.setSize(100, 100);
                                cp.add(button);
                                frame.setContentPane(cp);
                                frame.setSize(200, 200);
                                frame.setVisible(true);
                                /*
                                 * 当前窗口为这个窗口就会得到焦点，弹出其他页面就会失去焦点 我在这里给时间文本框赋值
                                 */
                                frame.addWindowFocusListener(new WindowFocusListener() {
                                    public void windowLostFocus(WindowEvent arg0) {
                                        dStartDate = dTempDate;
                                        sStartDate = sTempDate;// 这个数据将被记录到数据库
                                        txtStartDate.setText(sTempDate);
                                    }

                                    public void windowGainedFocus(WindowEvent arg0) {
                                    }
                                });
                            }
                        });

                        btnChooseDate2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                JFrame frame = new JFrame("选择结束日期");
                                JPanel cp = new JPanel();
                                DateChooserJButton button = new DateChooserJButton();
                                button.setSize(100, 100);
                                cp.add(button);
                                frame.setContentPane(cp);
                                frame.setSize(200, 200);
                                frame.setVisible(true);
                                /*
                                 * 当前窗口为这个窗口就会得到焦点，弹出其他页面就会失去焦点 我在这里给时间文本框赋值
                                 */
                                frame.addWindowFocusListener(new WindowFocusListener() {
                                    public void windowLostFocus(WindowEvent arg0) {
                                        dEndDate = dTempDate;
                                        sEndDate = sTempDate;// 这个数据将被记录到数据库
                                        txtEndDate.setText(sTempDate);// 显示选择好的时间
                                    }

                                    public void windowGainedFocus(WindowEvent arg0) {
                                    }
                                });
                            }
                        });

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    java.sql.Date startDate = java.sql.Date.valueOf(sStartDate);
                                    java.sql.Date endDate = java.sql.Date.valueOf(sEndDate);
                                    final String orderNum = Operation.getOrderNum(startDate, endDate);
                                    final String totalPay = Operation.getTotalPay(startDate, endDate);
                                    JFrame successF = new JFrame("Success.");
                                    successF.setSize(300, 150);
                                    successF.repaint();
                                    JPanel successP = new JPanel(new BorderLayout());
                                    successP.setLayout(new GridLayout(2, 1));
                                    JLabel orderNumL = new JLabel("销售总量：" + orderNum, JLabel.CENTER);
                                    orderNumL.setFont(new Font("微软雅黑", 1, 15));
                                    JLabel totalPayL = new JLabel("销售总金额：" + totalPay, JLabel.CENTER);
                                    totalPayL.setFont(new Font("微软雅黑", 1, 15));

                                    successP.add(orderNumL);
                                    successP.add(totalPayL);
                                    successF.add(successP);
                                    successF.setVisible(true);
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

                        showInfoF.setSize(150, 150);
                        showInfoF.setVisible(true);
                    }
                });

                categoryB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame categoryF = new JFrame("Choose Category");
                        JPanel buttonP = new JPanel();
                        JPanel cateP = new JPanel();
                        JScrollPane scrollPaneListName = new JScrollPane();
                        JTextField txtCateCon = new JTextField("类别名称");
                        JButton okB = new JButton("确认");
                        ButtonGroup option = new ButtonGroup();//Create a button group
                        JRadioButton optionA = new JRadioButton("大类");
                        JRadioButton optionB = new JRadioButton("中类");
                        JRadioButton optionC = new JRadioButton("小类");

                        option.add(optionA);
                        option.add(optionB);
                        option.add(optionC);

                        buttonP.setLayout(new GridLayout(3, 1));
                        buttonP.add(optionA);
                        buttonP.add(optionB);
                        buttonP.add(optionC);

                        buttonP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 0),
                                "请选择种类类别", TitledBorder.LEFT, TitledBorder.TOP,
                                new java.awt.Font("微软雅黑", 1, 12)));
                        cateP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 0),
                                "请选择一个类别属性", TitledBorder.LEFT, TitledBorder.TOP,
                                new java.awt.Font("微软雅黑", 1, 12)));

                        Vector firstCate = new Vector();
                        Vector secondCate = new Vector();
                        Vector thirdCate = new Vector();
                        try {
                            firstCate = Operation.getFirstCate();
                            secondCate = Operation.getSecondCate();
                            thirdCate = Operation.getThirdCate();
                        } catch (Exception E) {
                            JFrame failF = new JFrame("Fail.");
                            failF.setSize(300, 150);
                            failF.repaint();
                            JPanel failP = new JPanel(new BorderLayout());
                            JLabel failL = new JLabel("Error! " + E.getMessage(), JLabel.CENTER);
                            failL.setSize(200, 50);
                            failL.setFont(new Font("微软雅黑", 1, 12));
                            failP.add(failL, BorderLayout.CENTER);
                            failF.add(failP);
                            failF.setVisible(true);
                        }

                        JList listFirst = new JList(firstCate);
                        JList listSecond = new JList(secondCate);
                        JList listThird = new JList(thirdCate);
                        txtCateCon.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));

                        listFirst.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent arg0) {
                                txtCateCon.setText("" + listFirst.getSelectedValue());
                            }
                        });
                        listSecond.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent arg0) {
                                txtCateCon.setText("" + listSecond.getSelectedValue());
                            }
                        });
                        listThird.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent arg0) {
                                txtCateCon.setText("" + listThird.getSelectedValue());
                            }
                        });

                        optionA.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                scrollPaneListName.setViewportView(listFirst);
                            }
                        });
                        optionB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                scrollPaneListName.setViewportView(listSecond);
                            }
                        });
                        optionC.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                scrollPaneListName.setViewportView(listThird);
                            }
                        });

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    String orderNum = "", totalPay = "";
                                    if (optionA.isSelected()) {
                                        orderNum = Operation.getOrderNum("FirstCategory", txtCateCon.getText());
                                        totalPay = Operation.getTotalPay("FirstCategory", txtCateCon.getText());
                                    }
                                    if (optionB.isSelected()) {
                                        orderNum = Operation.getOrderNum("SecondCategory", txtCateCon.getText());
                                        totalPay = Operation.getTotalPay("SecondCategory", txtCateCon.getText());
                                    }
                                    if (optionC.isSelected()) {
                                        orderNum = Operation.getOrderNum("ThirdCategory", txtCateCon.getText());
                                        totalPay = Operation.getTotalPay("ThirdCategory", txtCateCon.getText());
                                    }

                                    JFrame successF = new JFrame("Success.");
                                    successF.setSize(300, 150);
                                    successF.repaint();
                                    JPanel successP = new JPanel(new BorderLayout());
                                    successP.setLayout(new GridLayout(2, 1));
                                    JLabel orderNumL = new JLabel("销售总量：" + orderNum, JLabel.CENTER);
                                    orderNumL.setFont(new Font("微软雅黑", 1, 15));
                                    JLabel totalPayL = new JLabel("销售总金额：" + totalPay, JLabel.CENTER);
                                    totalPayL.setFont(new Font("微软雅黑", 1, 15));

                                    successP.add(orderNumL);
                                    successP.add(totalPayL);
                                    successF.add(successP);
                                    successF.setVisible(true);

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

                        cateP.setLayout(new GridLayout(2, 1));
                        cateP.add(txtCateCon);
                        cateP.add(scrollPaneListName);
                        categoryF.getContentPane().add(buttonP, BorderLayout.NORTH);
                        categoryF.getContentPane().add(cateP);
                        categoryF.getContentPane().add(okB, BorderLayout.SOUTH);
                        categoryF.setSize(400, 320);
                        categoryF.setVisible(true);
                    }
                });

                periodTopB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame showInfoF = new JFrame("按时间段查询前十名热销商品");
                        JPanel dateP = new JPanel();
                        final JTextField txtStartDate = new JTextField("查询开始日期");
                        final JTextField txtEndDate = new JTextField("查询结束日期");
                        final JButton btnChooseDate = new JButton("选择开始日期");
                        final JButton btnChooseDate2 = new JButton("选择结束日期");
                        JButton okB = new JButton("确认");

                        dateP.add(txtStartDate);
                        dateP.add(btnChooseDate);
                        dateP.add(txtEndDate);
                        dateP.add(btnChooseDate2);
                        showInfoF.getContentPane().add(dateP);
                        showInfoF.getContentPane().add(okB, BorderLayout.SOUTH);

                        btnChooseDate.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                JFrame frame = new JFrame("选择开始日期");
                                JPanel cp = new JPanel();
                                DateChooserJButton button = new DateChooserJButton();
                                button.setSize(100, 100);
                                cp.add(button);
                                frame.setContentPane(cp);
                                frame.setSize(200, 200);
                                frame.setVisible(true);
                                /*
                                 * 当前窗口为这个窗口就会得到焦点，弹出其他页面就会失去焦点 我在这里给时间文本框赋值
                                 */
                                frame.addWindowFocusListener(new WindowFocusListener() {
                                    public void windowLostFocus(WindowEvent arg0) {
                                        dStartDate = dTempDate;
                                        sStartDate = sTempDate;// 这个数据将被记录到数据库
                                        txtStartDate.setText(sTempDate);
                                    }

                                    public void windowGainedFocus(WindowEvent arg0) {
                                    }
                                });
                            }
                        });

                        btnChooseDate2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                JFrame frame = new JFrame("选择结束日期");
                                JPanel cp = new JPanel();
                                DateChooserJButton button = new DateChooserJButton();
                                button.setSize(100, 100);
                                cp.add(button);
                                frame.setContentPane(cp);
                                frame.setSize(200, 200);
                                frame.setVisible(true);
                                /*
                                 * 当前窗口为这个窗口就会得到焦点，弹出其他页面就会失去焦点 我在这里给时间文本框赋值
                                 */
                                frame.addWindowFocusListener(new WindowFocusListener() {
                                    public void windowLostFocus(WindowEvent arg0) {
                                        dEndDate = dTempDate;
                                        sEndDate = sTempDate;// 这个数据将被记录到数据库
                                        txtEndDate.setText(sTempDate);// 显示选择好的时间
                                    }

                                    public void windowGainedFocus(WindowEvent arg0) {
                                    }
                                });
                            }
                        });

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    Vector productInfo = new Vector();

                                    java.sql.Date startDate = java.sql.Date.valueOf(sStartDate);
                                    java.sql.Date endDate = java.sql.Date.valueOf(sEndDate);
                                    productInfo = Operation.getTopProduct(startDate, endDate);

                                    JList listProductInfo = new JList(productInfo);
                                    JFrame successF = new JFrame("Success.");
                                    JScrollPane scrollPaneListProduct = new JScrollPane();
                                    scrollPaneListProduct.setViewportView(listProductInfo);
                                    successF.getContentPane().add(scrollPaneListProduct);

                                    successF.setSize(800, 250);
                                    successF.repaint();
                                    successF.setVisible(true);
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

                        showInfoF.setSize(150, 150);
                        showInfoF.setVisible(true);
                    }
                });

                TopSellB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Vector mostComment = new Vector();
                            Vector bestMark = new Vector();
                            mostComment = Operation.getMostCommentProduct();
                            bestMark = Operation.getBestMarkProduct();
                            JFrame successF = new JFrame("Success.");
                            JPanel mostCommentP = new JPanel();
                            JPanel bestSellP = new JPanel();
                            JList mostCommentProductInfo = new JList(mostComment);
                            JList bestMarkProductInfo = new JList(bestMark);
                            JScrollPane scrollMostComment = new JScrollPane();
                            JScrollPane scrollBestMark = new JScrollPane();
                            scrollMostComment.setViewportView(mostCommentProductInfo);
                            scrollBestMark.setViewportView(bestMarkProductInfo);

                            mostCommentP.add(scrollMostComment);
                            bestSellP.add(scrollBestMark);
                            mostCommentP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 0),
                                    "评价数量前十商品", TitledBorder.CENTER, TitledBorder.TOP,
                                    new java.awt.Font("微软雅黑", 1, 13)));
                            bestSellP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 0),
                                    "综合评分前十商品", TitledBorder.CENTER, TitledBorder.TOP,
                                    new java.awt.Font("微软雅黑", 1, 13)));
                            successF.setLayout(new GridLayout(2, 1));
                            successF.getContentPane().add(mostCommentP);
                            successF.getContentPane().add(bestSellP);
                            successF.setSize(1000, 500);
                            successF.repaint();
                            successF.setVisible(true);
                        } catch (Exception E) {
                            JFrame failF = new JFrame("Fail.");
                            failF.setSize(300, 150);
                            failF.repaint();
                            JPanel failP = new JPanel(new BorderLayout());
                            JLabel failL = new JLabel("Error! " + E.getMessage(), JLabel.CENTER);
                            failL.setSize(200, 50);
                            failL.setFont(new Font("微软雅黑", 1, 12));
                            failP.add(failL, BorderLayout.CENTER);
                            failF.add(failP);
                            failF.setVisible(true);
                        }
                    }
                });

                sellerQueryB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //声明组件
                        JFrame sellerQueryF = new JFrame("Seller Query");
                        JLabel sellerQueryL = new JLabel();
                        //用户管理按钮
                        JButton sellerAmount = new JButton("按商家统计某段时间订单数和总金额");
                        JButton sellerTop = new JButton("某时间段内，销量排名前十名的商家");

                        sellerQueryF.setLayout(new GridLayout(3, 1));
                        //设置Label图像
                        sellerQueryL.setIcon(new ImageIcon("src/image/timg2.jpg"));
                        //添加组件
                        sellerQueryF.getContentPane().add(sellerQueryL);
                        sellerQueryF.getContentPane().add(sellerAmount);
                        sellerQueryF.getContentPane().add(sellerTop);
                        //Frame设置
                        sellerQueryF.setSize(400, 150);
                        sellerQueryF.setVisible(true);

                        sellerAmount.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame showInfoF = new JFrame("按时间段查询订单总数及总金额");
                                JPanel dateP = new JPanel();
                                final JTextField txtStartDate = new JTextField("查询开始日期");
                                final JTextField txtEndDate = new JTextField("查询结束日期");
                                final JButton btnChooseDate = new JButton("选择开始日期");
                                final JButton btnChooseDate2 = new JButton("选择结束日期");
                                JTextField txtSellerID=new JTextField("请输入查询商家ID");
                                JButton okB = new JButton("确认");

                                dateP.add(txtStartDate);
                                dateP.add(btnChooseDate);
                                dateP.add(txtEndDate);
                                dateP.add(btnChooseDate2);
                                dateP.add(txtSellerID);
                                showInfoF.getContentPane().add(dateP);
                                showInfoF.getContentPane().add(okB, BorderLayout.SOUTH);

                                btnChooseDate.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        JFrame frame = new JFrame("选择开始日期");
                                        JPanel cp = new JPanel();
                                        DateChooserJButton button = new DateChooserJButton();
                                        button.setSize(100, 100);
                                        cp.add(button);
                                        frame.setContentPane(cp);
                                        frame.setSize(200, 200);
                                        frame.setVisible(true);
                                        /*
                                         * 当前窗口为这个窗口就会得到焦点，弹出其他页面就会失去焦点 我在这里给时间文本框赋值
                                         */
                                        frame.addWindowFocusListener(new WindowFocusListener() {
                                            public void windowLostFocus(WindowEvent arg0) {
                                                dStartDate = dTempDate;
                                                sStartDate = sTempDate;// 这个数据将被记录到数据库
                                                txtStartDate.setText(sTempDate);
                                            }

                                            public void windowGainedFocus(WindowEvent arg0) {
                                            }
                                        });
                                    }
                                });

                                btnChooseDate2.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        JFrame frame = new JFrame("选择结束日期");
                                        JPanel cp = new JPanel();
                                        DateChooserJButton button = new DateChooserJButton();
                                        button.setSize(100, 100);
                                        cp.add(button);
                                        frame.setContentPane(cp);
                                        frame.setSize(200, 200);
                                        frame.setVisible(true);
                                        /*
                                         * 当前窗口为这个窗口就会得到焦点，弹出其他页面就会失去焦点 我在这里给时间文本框赋值
                                         */
                                        frame.addWindowFocusListener(new WindowFocusListener() {
                                            public void windowLostFocus(WindowEvent arg0) {
                                                dEndDate = dTempDate;
                                                sEndDate = sTempDate;// 这个数据将被记录到数据库
                                                txtEndDate.setText(sTempDate);// 显示选择好的时间
                                            }

                                            public void windowGainedFocus(WindowEvent arg0) {
                                            }
                                        });
                                    }
                                });

                                okB.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        try {
                                            java.sql.Date startDate = java.sql.Date.valueOf(sStartDate);
                                            java.sql.Date endDate = java.sql.Date.valueOf(sEndDate);
                                            final String orderNum = Operation.getOrderNum(txtSellerID.getText(),startDate, endDate);
                                            final String totalPay = Operation.getTotalPay(txtSellerID.getText(),startDate, endDate);
                                            JFrame successF = new JFrame("Success.");
                                            successF.setSize(300, 150);
                                            successF.repaint();
                                            JPanel successP = new JPanel(new BorderLayout());
                                            successP.setLayout(new GridLayout(2, 1));
                                            JLabel orderNumL = new JLabel("销售总量：" + orderNum, JLabel.CENTER);
                                            orderNumL.setFont(new Font("微软雅黑", 1, 15));
                                            JLabel totalPayL = new JLabel("销售总金额：" + totalPay, JLabel.CENTER);
                                            totalPayL.setFont(new Font("微软雅黑", 1, 15));

                                            successP.add(orderNumL);
                                            successP.add(totalPayL);
                                            successF.add(successP);
                                            successF.setVisible(true);
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

                                showInfoF.setSize(150, 150);
                                showInfoF.setVisible(true);
                            }
                        });

                        sellerTop.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame showInfoF = new JFrame("按时间段查询销量排名前十名的商家");
                                JPanel dateP = new JPanel();
                                final JTextField txtStartDate = new JTextField("查询开始日期");
                                final JTextField txtEndDate = new JTextField("查询结束日期");
                                final JButton btnChooseDate = new JButton("选择开始日期");
                                final JButton btnChooseDate2 = new JButton("选择结束日期");
                                JButton okB = new JButton("确认");

                                dateP.add(txtStartDate);
                                dateP.add(btnChooseDate);
                                dateP.add(txtEndDate);
                                dateP.add(btnChooseDate2);
                                showInfoF.getContentPane().add(dateP);
                                showInfoF.getContentPane().add(okB, BorderLayout.SOUTH);

                                btnChooseDate.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        JFrame frame = new JFrame("选择开始日期");
                                        JPanel cp = new JPanel();
                                        DateChooserJButton button = new DateChooserJButton();
                                        button.setSize(100, 100);
                                        cp.add(button);
                                        frame.setContentPane(cp);
                                        frame.setSize(200, 200);
                                        frame.setVisible(true);
                                        /*
                                         * 当前窗口为这个窗口就会得到焦点，弹出其他页面就会失去焦点 我在这里给时间文本框赋值
                                         */
                                        frame.addWindowFocusListener(new WindowFocusListener() {
                                            public void windowLostFocus(WindowEvent arg0) {
                                                dStartDate = dTempDate;
                                                sStartDate = sTempDate;// 这个数据将被记录到数据库
                                                txtStartDate.setText(sTempDate);
                                            }

                                            public void windowGainedFocus(WindowEvent arg0) {
                                            }
                                        });
                                    }
                                });

                                btnChooseDate2.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        JFrame frame = new JFrame("选择结束日期");
                                        JPanel cp = new JPanel();
                                        DateChooserJButton button = new DateChooserJButton();
                                        button.setSize(100, 100);
                                        cp.add(button);
                                        frame.setContentPane(cp);
                                        frame.setSize(200, 200);
                                        frame.setVisible(true);
                                        /*
                                         * 当前窗口为这个窗口就会得到焦点，弹出其他页面就会失去焦点 我在这里给时间文本框赋值
                                         */
                                        frame.addWindowFocusListener(new WindowFocusListener() {
                                            public void windowLostFocus(WindowEvent arg0) {
                                                dEndDate = dTempDate;
                                                sEndDate = sTempDate;// 这个数据将被记录到数据库
                                                txtEndDate.setText(sTempDate);// 显示选择好的时间
                                            }

                                            public void windowGainedFocus(WindowEvent arg0) {
                                            }
                                        });
                                    }
                                });

                                okB.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        try {
                                            Vector sellerInfo = new Vector();

                                            java.sql.Date startDate = java.sql.Date.valueOf(sStartDate);
                                            java.sql.Date endDate = java.sql.Date.valueOf(sEndDate);
                                            sellerInfo = Operation.getTopSeller(startDate, endDate);

                                            JList listSellerInfo = new JList(sellerInfo);
                                            JFrame successF = new JFrame("Success.");
                                            JScrollPane scrollPaneListSeller = new JScrollPane();
                                            scrollPaneListSeller.setViewportView(listSellerInfo);
                                            successF.getContentPane().add(scrollPaneListSeller);

                                            successF.setSize(700, 250);
                                            successF.repaint();
                                            successF.setVisible(true);
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

                                showInfoF.setSize(150, 150);
                                showInfoF.setVisible(true);
                            }
                        });
                    }
                });

                customerTopB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame showInfoF = new JFrame("按时间段查询成功下单金额前十名用户");
                        JPanel dateP = new JPanel();
                        final JTextField txtStartDate = new JTextField("查询开始日期");
                        final JTextField txtEndDate = new JTextField("查询结束日期");
                        final JButton btnChooseDate = new JButton("选择开始日期");
                        final JButton btnChooseDate2 = new JButton("选择结束日期");
                        JButton okB = new JButton("确认");

                        dateP.add(txtStartDate);
                        dateP.add(btnChooseDate);
                        dateP.add(txtEndDate);
                        dateP.add(btnChooseDate2);
                        showInfoF.getContentPane().add(dateP);
                        showInfoF.getContentPane().add(okB, BorderLayout.SOUTH);

                        btnChooseDate.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                JFrame frame = new JFrame("选择开始日期");
                                JPanel cp = new JPanel();
                                DateChooserJButton button = new DateChooserJButton();
                                button.setSize(100, 100);
                                cp.add(button);
                                frame.setContentPane(cp);
                                frame.setSize(200, 200);
                                frame.setVisible(true);
                                /*
                                 * 当前窗口为这个窗口就会得到焦点，弹出其他页面就会失去焦点 我在这里给时间文本框赋值
                                 */
                                frame.addWindowFocusListener(new WindowFocusListener() {
                                    public void windowLostFocus(WindowEvent arg0) {
                                        dStartDate = dTempDate;
                                        sStartDate = sTempDate;// 这个数据将被记录到数据库
                                        txtStartDate.setText(sTempDate);
                                    }

                                    public void windowGainedFocus(WindowEvent arg0) {
                                    }
                                });
                            }
                        });

                        btnChooseDate2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                JFrame frame = new JFrame("选择结束日期");
                                JPanel cp = new JPanel();
                                DateChooserJButton button = new DateChooserJButton();
                                button.setSize(100, 100);
                                cp.add(button);
                                frame.setContentPane(cp);
                                frame.setSize(200, 200);
                                frame.setVisible(true);
                                /*
                                 * 当前窗口为这个窗口就会得到焦点，弹出其他页面就会失去焦点 我在这里给时间文本框赋值
                                 */
                                frame.addWindowFocusListener(new WindowFocusListener() {
                                    public void windowLostFocus(WindowEvent arg0) {
                                        dEndDate = dTempDate;
                                        sEndDate = sTempDate;// 这个数据将被记录到数据库
                                        txtEndDate.setText(sTempDate);// 显示选择好的时间
                                    }

                                    public void windowGainedFocus(WindowEvent arg0) {
                                    }
                                });
                            }
                        });

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    Vector customerInfo = new Vector();

                                    java.sql.Date startDate = java.sql.Date.valueOf(sStartDate);
                                    java.sql.Date endDate = java.sql.Date.valueOf(sEndDate);
                                    customerInfo = Operation.getTopCustomer(startDate, endDate);

                                    JList listCustomerInfo = new JList(customerInfo);
                                    JFrame successF = new JFrame("Success.");
                                    JScrollPane scrollPaneListCustomer = new JScrollPane();
                                    scrollPaneListCustomer.setViewportView(listCustomerInfo);
                                    successF.getContentPane().add(scrollPaneListCustomer);

                                    successF.setSize(1100, 250);
                                    successF.repaint();
                                    successF.setVisible(true);
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
                        showInfoF.setSize(150, 150);
                        showInfoF.setVisible(true);
                    }
                });

                monthB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        try {
                            Vector monthInfo = new Vector();
                            monthInfo = Operation.getMonthInfo();

                            JList listMonthInfo = new JList(monthInfo);
                            JFrame successF = new JFrame("Success.");
                            JScrollPane scrollPaneListMonth = new JScrollPane();
                            scrollPaneListMonth.setViewportView(listMonthInfo);
                            successF.getContentPane().add(scrollPaneListMonth);

                            successF.setSize(300, 300);
                            successF.repaint();
                            successF.setVisible(true);
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

                hourB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        try {
                            Vector hourInfo = new Vector();
                            hourInfo = Operation.getHourInfo();

                            JList listHourInfo = new JList(hourInfo);
                            JFrame successF = new JFrame("Success.");
                            JScrollPane scrollPaneListHour = new JScrollPane();
                            scrollPaneListHour.setViewportView(listHourInfo);
                            successF.getContentPane().add(scrollPaneListHour);

                            successF.setSize(200, 400);
                            successF.repaint();
                            successF.setVisible(true);
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
            }
        });
    }

    /*public static void main(String[] args) throws SQLException {
        Administrator admin=new Administrator();
        Operation.Connect();
    }*/

    public class PasswordGenerator {
        public final char[] allowedSpecialCharactors = {
                '`', '~', '@', '#', '$', '%', '^', '&',
                '*', '(', ')', '-', '_', '=', '+', '[',
                '{', '}', ']', '\\', '|', ';', ':', '"',
                '\'', ',', '<', '.', '>', '/', '?'};//密码能包含的特殊字符
        private final int letterRange = 26;
        private final int numberRange = 10;
        private final int spCharactorRange = allowedSpecialCharactors.length;
        private  final Random random = new Random();
        private int passwordLength;//密码的长度
        private int minVariousType;//密码包含字符的最少种类

        public PasswordGenerator(int passwordLength, int minVariousType) {
            if (minVariousType > CharactorType.values().length) minVariousType = CharactorType.values().length;
            if (minVariousType > passwordLength) minVariousType = passwordLength;
            this.passwordLength = passwordLength;
            this.minVariousType = minVariousType;
        }

        public String generateRandomPassword() {
            char[] password = new char[passwordLength];
            List<Integer> pwCharsIndex = new ArrayList();
            for (int i = 0; i < password.length; i++) {
                pwCharsIndex.add(i);
            }
            List<CharactorType> takeTypes = new ArrayList(Arrays.asList(CharactorType.values()));
            List<CharactorType> fixedTypes = Arrays.asList(CharactorType.values());
            int typeCount = 0;
            while (pwCharsIndex.size() > 0) {
                int pwIndex = pwCharsIndex.remove(random.nextInt(pwCharsIndex.size()));//随机填充一位密码
                Character c;
                if (typeCount < minVariousType) {//生成不同种类字符
                    c = generateCharacter(takeTypes.remove(random.nextInt(takeTypes.size())));
                    typeCount++;
                } else {//随机生成所有种类密码
                    c = generateCharacter(fixedTypes.get(random.nextInt(fixedTypes.size())));
                }
                password[pwIndex] = c.charValue();
            }
            return String.valueOf(password);
        }

        private Character generateCharacter(CharactorType type) {
            Character c = null;
            int rand;
            switch (type) {
                case LOWERCASE://随机小写字母
                    rand = random.nextInt(letterRange);
                    rand += 97;
                    c = new Character((char) rand);
                    break;
                case UPPERCASE://随机大写字母
                    rand = random.nextInt(letterRange);
                    rand += 65;
                    c = new Character((char) rand);
                    break;
                case NUMBER://随机数字
                    rand = random.nextInt(numberRange);
                    rand += 48;
                    c = new Character((char) rand);
                    break;
                case SPECIAL_CHARACTOR://随机特殊字符
                    rand = random.nextInt(spCharactorRange);
                    c = new Character(allowedSpecialCharactors[rand]);
                    break;
            }
            return c;
        }
    }

    enum CharactorType {
        LOWERCASE,
        UPPERCASE,
        NUMBER,
        SPECIAL_CHARACTOR
    }

    public static class DateChooserJButton extends JButton {

        private DateChooser dateChooser = null;// 继承自面板，为内部类
        private String preLabel = "";

        public DateChooserJButton() {
            this(getNowDate());// 调用其他的构造方法
        }

        public DateChooserJButton(SimpleDateFormat df, String dateString) {
            this();// 干嘛的？
            setText(df, dateString);
        }

        public DateChooserJButton(java.util.Date date) {
            this("", date);
        }

        public DateChooserJButton(String preLabel, java.util.Date date) {
            if (preLabel != null)
                this.preLabel = preLabel;
            setDate(date);
            setBorder(null);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            // 点击按钮，就会弹出那个日期选择器
            super.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (dateChooser == null)
                        dateChooser = new DateChooser();
                    Point p = getLocationOnScreen();
                    p.y = p.y + 30;
                    dateChooser.showDateChooser(p);// 把选择框显示出来
                }
            });
        }

        private static java.util.Date getNowDate() {
            return Calendar.getInstance().getTime();
        }

        /**
         * 2个重要的用法 getDefaultDateFormat().format(date)日期变字符串
         * getDefaultDateFormat().parse(s) 字符串变日期
         * */
        private static SimpleDateFormat getDefaultDateFormat() {
            // return new SimpleDateFormat("yyyy-MM-dd HH:MM:ss"); // 按钮显示的日期格式
            return new SimpleDateFormat("yyyy-MM-dd"); // 按钮显示的日期格式
        }

        /**
         * 该类的构造方法被链式调用，构造方法一出来就会导致public void setDate(Date
         * date)被调用，按钮会显示出当前日期，然后程序静止，等待用户点击按钮 直到按钮被点击就会显示出日期选择器
         * */
        // 覆盖父类的方法
        public void setText(String s) {

            java.util.Date date;
            try {
                date = getDefaultDateFormat().parse(s);
            } catch (ParseException e) {
                date = getNowDate();// 如果获取失败，就默认为当前时间
            }
            // System.out.println("日期"+s);
            setDate(date);
        }

        public void setText(SimpleDateFormat df, String s) {
            java.util.Date date;
            try {
                date = df.parse(s);
            } catch (ParseException e) {
                date = getNowDate(); // 不理解啊！！！！！！！！！！1
            }
            setDate(date);
            // System.out.println("日期" + s);
        }

        public void setDate(java.util.Date date) {
            super.setText(preLabel + getDefaultDateFormat().format(date));// 使按钮显示时间
        }

        public java.util.Date getDate() {
            String dateString = getText();// 我用这个代码代替了下面
            // String dateString =
            // getText().substring(preLabel.length());//getText()表示按钮DateChooserJButton上的文本；
            // 子字符串在指定的字符位置开始并一直到该字符串的末尾。事实上preLabel.length()就是0
            try {
                return getDefaultDateFormat().parse(dateString);
            } catch (ParseException e) {
                return getNowDate();
            }
        }

        // 覆盖父类的方法使之无效
        public void addActionListener(ActionListener listener) {

        }

        /**
         * 日期选择内部类
         * *******************************************************************************************/
        private class DateChooser extends JPanel implements ActionListener,
                ChangeListener {
            int startYear = 1980; // 默认【最小】显示年份
            int lastYear = 2050; // 默认【最大】显示年份
            int width = 200; // 界面宽度
            int height = 200; // 界面高度
            Color backGroundColor = Color.gray; // 底色
            // 月历表格配色----------------//
            Color palletTableColor = Color.white; // 日历表底色
            Color todayBackColor = Color.orange; // 今天背景色
            Color weekFontColor = Color.blue; // 星期文字色
            Color dateFontColor = Color.black; // 日期文字色
            // 控制条配色------------------//
            Color controlLineColor = Color.pink; // 控制条底色
            Color controlTextColor = Color.white; // 控制条标签文字色
            JDialog dialog;
            JSpinner yearSpin;
            JSpinner monthSpin;
            // JSpinner hourSpin;
            JButton[][] daysButton = new JButton[6][7];// 一个月的日期

            DateChooser() {
                setLayout(new BorderLayout());
                setBorder(new LineBorder(backGroundColor, 2));// 神马？
                setBackground(backGroundColor);
                JPanel topYearAndMonth = createYearAndMonthPanal();
                add(topYearAndMonth, BorderLayout.NORTH);
                JPanel centerWeekAndDay = createWeekAndDayPanal();
                add(centerWeekAndDay, BorderLayout.CENTER);
            }

            private JPanel createYearAndMonthPanal() {
                // 获取日历对象
                Calendar c = getCalendar();
                int currentYear = c.get(Calendar.YEAR);
                int currentMonth = c.get(Calendar.MONTH) + 1;// 为什么 +1
                // int currentHour = c.get(Calendar.HOUR_OF_DAY);
                JPanel result = new JPanel();
                result.setLayout(new FlowLayout());
                result.setBackground(controlLineColor);
                // 构造一个具有指定 value、minimum/maximum 边界和 stepSize 的
                // SpinnerNumberModel。
                yearSpin = new JSpinner(new SpinnerNumberModel(currentYear,
                        startYear, lastYear, 1));
                yearSpin.setPreferredSize(new Dimension(48, 20));
                yearSpin.setName("Year");
                yearSpin.setEditor(new JSpinner.NumberEditor(yearSpin, "####"));
                yearSpin.addChangeListener(this);

                result.add(yearSpin);
                JLabel yearLabel = new JLabel("年");
                yearLabel.setForeground(controlTextColor);
                result.add(yearLabel);
                monthSpin = new JSpinner(new SpinnerNumberModel(currentMonth,
                        1, 12, 1));
                monthSpin.setPreferredSize(new Dimension(35, 20));
                monthSpin.setName("Month");
                monthSpin.addChangeListener(this);
                result.add(monthSpin);
                JLabel monthLabel = new JLabel("月");
                monthLabel.setForeground(controlTextColor);
                result.add(monthLabel);
                // hourSpin = new JSpinner(new SpinnerNumberModel(currentHour,
                // 0, 23,
                // 1));
                // hourSpin.setPreferredSize(new Dimension(35, 20));
                // hourSpin.setName("Hour");
                // hourSpin.addChangeListener(this);
                // result.add(hourSpin);
                // //JLabel hourLabel = new JLabel("时");
                // hourLabel.setForeground(controlTextColor);
                // result.add(hourLabel);
                return result;
            }

            private JPanel createWeekAndDayPanal() {
                String colname[] = { "日", "一", "二", "三", "四", "五", "六" };
                JPanel result = new JPanel();
                // 设置固定字体，以免调用环境改变影响界面美观
                result.setFont(new Font("宋体", Font.PLAIN, 12));
                result.setLayout(new GridLayout(7, 7));
                result.setBackground(Color.white);
                JLabel cell;
                for (int i = 0; i < 7; i++) {
                    cell = new JLabel(colname[i]);
                    cell.setHorizontalAlignment(JLabel.RIGHT);
                    cell.setForeground(weekFontColor);
                    result.add(cell);
                }

                int actionCommandId = 0;
                for (int i = 0; i < 6; i++)
                    for (int j = 0; j < 7; j++) {
                        JButton numberButton = new JButton();
                        numberButton.setBorder(null);
                        numberButton
                                .setHorizontalAlignment(SwingConstants.RIGHT);
                        /**
                         * 设置此组件激发的操作事件的命令名称。 如: JButton btnShow = new
                         * JButton(); btnShow.setActionCommand("show");
                         * 当这个按钮被点击的时候,你就可以用 ActionEvent 的
                         * getActionCommand().equals("show") 来辨识是点击了哪个按钮
                         */
                        numberButton.setActionCommand(String
                                .valueOf(actionCommandId));// 获取对应的字符串
                        numberButton.addActionListener(this);
                        numberButton.setBackground(palletTableColor);
                        numberButton.setForeground(dateFontColor);
                        numberButton.setForeground(dateFontColor);
                        daysButton[i][j] = numberButton;
                        result.add(numberButton);
                        actionCommandId++;// 监听时就可以通过actionCommandId来辨识是点击了哪个按钮。
                    }
                return result;
            }

            private JDialog createDialog(Frame owner) {
                JDialog result = new JDialog(owner, "日期时间选择", true);
                result.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                result.getContentPane().add(this, BorderLayout.CENTER);// 这个this指的是本类最大的JPanel，实际上，弹出来的框是包含JPanel的一个JDialog。
                result.pack();// 调整此窗口的大小，以适合其子组件的首选大小和布局
                result.setSize(width, height);
                return result;
            }

            void showDateChooser(Point position) {
                Frame owner = (Frame) SwingUtilities
                        .getWindowAncestor(DateChooserJButton.this);// 返回
                // Component
                // 的第一个
                // Window 祖先
                if (dialog == null || dialog.getOwner() != owner)
                    dialog = createDialog(owner);// 竟然要有一个Dialog???
                dialog.setLocation(getAppropriateLocation(owner, position));
                flushWeekAndDay();
                dialog.show();
            }

            Point getAppropriateLocation(Frame owner, Point position) {
                Point result = new Point(position);
                Point p = owner.getLocation();
                int offsetX = (position.x + width) - (p.x + owner.getWidth());
                int offsetY = (position.y + height) - (p.y + owner.getHeight());
                if (offsetX > 0) {
                    result.x -= offsetX;
                }
                if (offsetY > 0) {
                    result.y -= offsetY;
                }
                return result;
            }

            private Calendar getCalendar() {
                Calendar result = Calendar.getInstance();
                result.setTime(getDate());
                return result;
            }

            private int getSelectedYear() {
                return ((Integer) yearSpin.getValue()).intValue();
            }

            private int getSelectedMonth() {
                return ((Integer) monthSpin.getValue()).intValue();
            }

            // private int getSelectedHour() {
            // return ((Integer) hourSpin.getValue()).intValue();
            // }

            private void dayColorUpdate(boolean isOldDay) {
                Calendar c = getCalendar();
                int day = c.get(Calendar.DAY_OF_MONTH);
                c.set(Calendar.DAY_OF_MONTH, 1);
                int actionCommandId = day - 2 + c.get(Calendar.DAY_OF_WEEK);
                int i = actionCommandId / 7;
                int j = actionCommandId % 7;
                if (isOldDay)
                    daysButton[i][j].setForeground(dateFontColor);
                else
                    daysButton[i][j].setForeground(todayBackColor);
            }

            // 关于如何设置这个月的星期，这段代码很需要想象力了啊
            private void flushWeekAndDay() {
                Calendar c = getCalendar();
                c.set(Calendar.DAY_OF_MONTH, 1);// 时间设置为本月的1号
                int maxDayNo = c.getActualMaximum(Calendar.DAY_OF_MONTH);// 本月天数
                int dayNo = 2 - c.get(Calendar.DAY_OF_WEEK);// c.get(Calendar.DAY_OF_WEEK)表示本月一号是星期几，如果是星期一则返回2，以此类推，这样在for循环里面就可以正确画出月份图
                // System.out.println("星期 "+c.get(Calendar.DAY_OF_WEEK));
                // System.out.println(""+dayNo);
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++) {
                        String s = "";
                        if (dayNo >= 1 && dayNo <= maxDayNo)// 这个很重要
                            s = String.valueOf(dayNo);
                        daysButton[i][j].setText(s);
                        dayNo++;
                    }
                }
                dayColorUpdate(false);
            }

            // 这是那个JSpinner监听事件，有
            public void stateChanged(ChangeEvent e) {
                JSpinner source = (JSpinner) e.getSource();
                Calendar c = getCalendar();
                // if (source.getName().equals("Hour")) {
                // c.set(Calendar.HOUR_OF_DAY, getSelectedHour());
                // setDate(c.getTime());
                // return;
                // }
                dayColorUpdate(true);
                if (source.getName().equals("Year"))
                    c.set(Calendar.YEAR, getSelectedYear());
                else
                    // (source.getName().equals("Month"))
                    c.set(Calendar.MONTH, getSelectedMonth() - 1);
                setDate(c.getTime());// 我等下在这里把时间用2个textvIew显示出来，然后插入数据库
                /**
                 * 注意： 只有调用setDate的时候才会把时间设置在那个按钮DateChooserJButton里面
                 * 这个文件里只有2处setDate方法被调用，
                 * 包括在stateChanged这里选择年，月，还有在actionPerformed里面选择日的时候
                 **/
                dTempDate = c.getTime();// 记录Date型日期
                sTempDate = "" + getDefaultDateFormat().format(c.getTime());// 记录字符串型日期
                flushWeekAndDay();
            }

            // 日期按钮监听
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();// 获取到那个button
                if (source.getText().length() == 0)
                    return;
                dayColorUpdate(true);
                source.setForeground(todayBackColor);
                int newDay = Integer.parseInt(source.getText());// 获取日。
                Calendar c = getCalendar();// 获取到的Calendar 包含了选择过的月和年
                c.set(Calendar.DAY_OF_MONTH, newDay);
                setDate(c.getTime());

                System.out.println(c.getTime());
                dTempDate = c.getTime();// 记录Date型日期
                // 输出Wed Feb 18 22:00:31 CST 2015
                System.out.println(""
                        + getDefaultDateFormat().format(c.getTime()));
                // 输出2015-02-18 22:02:31
                sTempDate = "" + getDefaultDateFormat().format(c.getTime());// 记录字符串型日期
            }
        }// end class DateChooser
    }
}