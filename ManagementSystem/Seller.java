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

public class Seller extends JFrame {
    JLabel sellerL = new JLabel("欢迎使用ShopWeb后台管理系统",JLabel.CENTER);
    JLabel sellerLI= new JLabel();
    JButton productB = new JButton("商品管理");
    JButton queryOrderB = new JButton("订单查询");
    JButton manageOrderB = new JButton("订单管理");

    static String sStartDate = null;// 记录String型日期
    static String sEndDate = null;// 记录String型日期
    static String sTempDate = null;// 注意这是临时记录字符串型日期

    static java.util.Date dStartDate = null;
    static java.util.Date dEndDate = null;
    static java.util.Date dTempDate = null;// 注意这是临时记录Date型日期

    public Seller(String sellerID) {
        this.getContentPane().setLayout(new GridLayout(5, 1,5,5));
        sellerLI.setIcon(new ImageIcon("src/image/user1.jpg"));
        sellerL.setFont(new Font("微软雅黑",1, 20));
        productB.setBorder(BorderFactory.createRaisedBevelBorder());
        queryOrderB.setBorder(BorderFactory.createRaisedBevelBorder());
        manageOrderB.setBorder(BorderFactory.createRaisedBevelBorder());

        this.getContentPane().add(sellerLI);
        this.getContentPane().add(sellerL);
        this.getContentPane().add(productB);
        this.getContentPane().add(manageOrderB);
        this.getContentPane().add(queryOrderB);

        this.setTitle("Welcome");
        this.setSize(400, 320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        productB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //声明组件
                JFrame productManagementF = new JFrame("Product Management");
                JPanel productManagementP = new JPanel();
                JLabel productManagementL = new JLabel();
                //用户管理按钮
                JButton addProductB = new JButton("添加商品信息");
                JButton deleteProductB = new JButton("删除商品信息");
                JButton updateProductB = new JButton("更改商品信息");
                JButton queryProductB = new JButton("查询商品信息");
                JButton queryProductInfoB = new JButton("查询本商店所有商品信息");
                //整体布局
                productManagementF.setLayout(new GridLayout(1, 2));
                //设置Label图像
                productManagementL.setIcon(new ImageIcon("src/image/j.jpg"));
                //添加组件
                productManagementF.getContentPane().add(productManagementL);
                productManagementF.getContentPane().add(productManagementP );
                //按钮布局
                productManagementP .setLayout(new GridLayout(5, 1));
                productManagementP .add(addProductB);
                productManagementP .add(deleteProductB);
                productManagementP .add(updateProductB);
                productManagementP .add(queryProductB);
                productManagementP .add(queryProductInfoB);
                //Frame设置
                productManagementF.setSize(400, 250);
                productManagementF.setVisible(true);

                addProductB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame addProductInfoF = new JFrame("Add Product Info");

                        JPanel cateP1 = new JPanel();
                        JPanel cateP2 = new JPanel();
                        JPanel cateP3 = new JPanel();
                        JPanel otherP=new JPanel();
                        JScrollPane scrollPaneListPro1 = new JScrollPane();
                        JScrollPane scrollPaneListPro2 = new JScrollPane();
                        JScrollPane scrollPaneListPro3 = new JScrollPane();
                        JTextField txtCateCon1 = new JTextField("大类名称");
                        JTextField txtCateCon2 = new JTextField("中类别名称");
                        JTextField txtCateCon3 = new JTextField("小类别名称");
                        JButton okB = new JButton("确认");
                        final JTextField txtProductName = new JTextField("商品名称");
                        final JTextField txtProductPrice = new JTextField("商品价格：.00");
                        final JTextField txtProductDescp = new JTextField("商品描述");

                        cateP1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 0),
                                "请选择一个大类属性", TitledBorder.LEFT, TitledBorder.TOP,
                                new java.awt.Font("微软雅黑", 1, 12)));
                        cateP2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 0),
                                "请选择一个中类属性", TitledBorder.LEFT, TitledBorder.TOP,
                                new java.awt.Font("微软雅黑", 1, 12)));
                        cateP3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 0),
                                "请选择一个小类属性", TitledBorder.LEFT, TitledBorder.TOP,
                                new java.awt.Font("微软雅黑", 1, 12)));

                        cateP1.setLayout(new GridLayout(1, 2));
                        cateP1.add(txtCateCon1);
                        cateP1.add(scrollPaneListPro1);
                        cateP2.setLayout(new GridLayout(1, 2));
                        cateP2.add(txtCateCon2);
                        cateP2.add(scrollPaneListPro2);
                        cateP3.setLayout(new GridLayout(1, 2));
                        cateP3.add(txtCateCon3);
                        cateP3.add(scrollPaneListPro3);
                        otherP.setLayout(new GridLayout(4,1));
                        otherP.add(txtProductName);
                        otherP.add(txtProductPrice);
                        otherP.add(txtProductDescp);
                        otherP.add(okB);
                        addProductInfoF.setLayout(new GridLayout(4,1));

                        addProductInfoF.getContentPane().add(cateP1);
                        addProductInfoF.getContentPane().add(cateP2);
                        addProductInfoF.getContentPane().add(cateP3);
                        addProductInfoF.getContentPane().add(otherP);

                        Vector firstCate = new Vector();
                        try {
                            firstCate = Operation.getFirstCate();

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
                        txtCateCon1.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                        listFirst.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent arg0) {
                                txtCateCon1.setText("" + listFirst.getSelectedValue());
                                Vector secondCate = new Vector();
                                try {
                                    secondCate = Operation.getSecondCate(txtCateCon1.getText());
                                }catch (Exception E) {
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

                                JList listSecond = new JList(secondCate);
                                txtCateCon2.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                                listSecond.addListSelectionListener(new ListSelectionListener() {
                                    public void valueChanged(ListSelectionEvent arg0) {
                                        txtCateCon2.setText("" + listSecond.getSelectedValue());
                                        Vector thirdCate = new Vector();

                                        try {
                                            thirdCate = Operation.getThirdCate(txtCateCon2.getText());
                                        }catch (Exception E) {
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

                                        JList listThird = new JList(thirdCate);
                                        txtCateCon3.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                                        listThird.addListSelectionListener(new ListSelectionListener() {
                                            public void valueChanged(ListSelectionEvent arg0) {
                                                txtCateCon3.setText("" + listThird.getSelectedValue());
                                            }
                                        });
                                        scrollPaneListPro3.setViewportView(listThird);
                                    }
                                });
                                scrollPaneListPro2.setViewportView(listSecond);
                            }
                        });
                        scrollPaneListPro1.setViewportView(listFirst);

                        Vector secondCate = new Vector();
                        try {
                            secondCate = Operation.getSecondCate(txtCateCon1.getText());

                        }catch (Exception E) {
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

                        JList listSecond = new JList(secondCate);
                        txtCateCon2.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                        listSecond.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent arg0) {
                                txtCateCon2.setText("" + listSecond.getSelectedValue());
                            }
                        });
                        scrollPaneListPro2.setViewportView(listSecond);

                        Vector thirdCate = new Vector();
                        try {
                            thirdCate = Operation.getThirdCate(txtCateCon2.getText());

                        }catch (Exception E) {
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

                        JList listThird = new JList(thirdCate);
                        txtCateCon3.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                        listThird.addListSelectionListener(new ListSelectionListener() {
                            public void valueChanged(ListSelectionEvent arg0) {
                                txtCateCon3.setText("" + listThird.getSelectedValue());
                            }
                        });
                        scrollPaneListPro3.setViewportView(listThird);

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    final String productName = txtProductName.getText();
                                    final String productPrice = txtProductPrice.getText();
                                    final String productDescp=txtProductDescp.getText();
                                    final String firstCategory = txtCateCon1.getText();
                                    final String secondCategory = txtCateCon2.getText();
                                    final String thirdCategory = txtCateCon3.getText();
                                    String categoryID=""+(int)((Math.random()*9+1)*1000000);
                                    while(Operation.checkCategoryInfo(categoryID)==false)
                                        categoryID =""+(int)((Math.random()*9+1)*100000);

                                    String productID =""+(int)((Math.random()*9+1)*1000000);
                                    while(Operation.checkProductInfo(productID)==false) {
                                        productID = "" + (int) ((Math.random() * 9 + 1) * 1000000);
                                    }
                                    Operation.insertProductInfo(productID,productName,categoryID,firstCategory,
                                           secondCategory,thirdCategory,productPrice,productDescp,sellerID);
                                    JFrame successF = new JFrame("Success.");
                                    successF.setSize(500,150);
                                    successF.repaint();
                                    JPanel successP = new JPanel(new BorderLayout());
                                    JLabel successL=new JLabel("Insert success. Your product's ID is "+ productID,JLabel.CENTER);
                                    successL.setSize(500,50);
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
                        addProductInfoF.setSize(400, 500);
                        addProductInfoF.setVisible(true);
                    }
                });

                deleteProductB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame deleteProductInfoF = new JFrame("Delete Product Info");
                        JButton okB=new JButton("确认");

                        final JTextField txtProductID = new JTextField("请输入删除商品ID");
                        deleteProductInfoF.getContentPane().add(txtProductID,BorderLayout.NORTH);
                        deleteProductInfoF.getContentPane().add(okB,BorderLayout.SOUTH);

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    final String ProductID = txtProductID.getText();
                                    if(Operation.checkProductOnwer(sellerID,ProductID)==false){
                                        JFrame failF = new JFrame("Fail.");
                                        failF.setSize(300,150);
                                        failF.repaint();
                                        JPanel failP = new JPanel(new BorderLayout());
                                        JLabel failL=new JLabel("非本商店商品",JLabel.CENTER);
                                        failL.setSize(200,50);
                                        failL.setFont(new Font("微软雅黑",1, 12));
                                        failP.add(failL,BorderLayout.CENTER);
                                        failF.add(failP);
                                        failF.setVisible(true);
                                    }else{
                                        Operation.deleteProductInfo(ProductID);
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
                                    //e.printStackTrace();
                                }
                            }
                        });
                        deleteProductInfoF.setSize(250, 100);
                        deleteProductInfoF.setVisible(true);
                    }
                });

                updateProductB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        final JFrame updateProductF = new JFrame("Update Product Info");
                        final JLabel updateTitle=new JLabel("请选择一个修改属性",JLabel.CENTER);

                        final JButton proNameB=new JButton("商品名称");
                        final JButton proCateB=new JButton("商品类别");
                        final JButton proPriceB=new JButton("商品价格");
                        final JButton proDescpB=new JButton("商品描述");

                        updateProductF.setLayout(new GridLayout(5,1));
                        updateProductF.getContentPane().add(updateTitle);
                        updateProductF.getContentPane().add(proNameB);
                        updateProductF.getContentPane().add(proCateB);
                        updateProductF.getContentPane().add(proPriceB);
                        updateProductF.getContentPane().add(proDescpB);

                        proNameB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame updateProductInfoF = new JFrame("Update Product Info");
                                JButton okB=new JButton("确认");
                                final JTextField txtProductID = new JTextField("请输入商品ID");
                                final JTextField txtProductInfo = new JTextField("请输入新信息");

                                updateProductInfoF.getContentPane().add(txtProductID,BorderLayout.NORTH);
                                updateProductInfoF.getContentPane().add(txtProductInfo,BorderLayout.CENTER);
                                updateProductInfoF.getContentPane().add(okB,BorderLayout.SOUTH);

                                okB.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        try {
                                            final String ProductID = txtProductID.getText();
                                            if(Operation.checkProductOnwer(sellerID,ProductID)==false){
                                                JFrame failF = new JFrame("Fail.");
                                                failF.setSize(300,150);
                                                failF.repaint();
                                                JPanel failP = new JPanel(new BorderLayout());
                                                JLabel failL=new JLabel("非本商店商品",JLabel.CENTER);
                                                failL.setSize(200,50);
                                                failL.setFont(new Font("微软雅黑",1, 12));
                                                failP.add(failL,BorderLayout.CENTER);
                                                failF.add(failP);
                                                failF.setVisible(true);
                                            }else
                                            {
                                                final String ProductInfo = txtProductInfo.getText();
                                                Operation.updateProductInfo(ProductID,"productName",ProductInfo);
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
                                            //e.printStackTrace();
                                        }
                                    }
                                });
                                updateProductInfoF.setSize(250, 100);
                                updateProductInfoF.setVisible(true);
                            }
                        });

                        proPriceB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame updateProductInfoF = new JFrame("Update Product Info");
                                JButton okB=new JButton("确认");
                                final JTextField txtProductID = new JTextField("请输入商品ID");
                                final JTextField txtProductInfo = new JTextField("请输入新信息");

                                updateProductInfoF.getContentPane().add(txtProductID,BorderLayout.NORTH);
                                updateProductInfoF.getContentPane().add(txtProductInfo,BorderLayout.CENTER);
                                updateProductInfoF.getContentPane().add(okB,BorderLayout.SOUTH);

                                okB.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        try {
                                            final String ProductID = txtProductID.getText();
                                            if(Operation.checkProductOnwer(sellerID,ProductID)==false){
                                                JFrame failF = new JFrame("Fail.");
                                                failF.setSize(300,150);
                                                failF.repaint();
                                                JPanel failP = new JPanel(new BorderLayout());
                                                JLabel failL=new JLabel("非本商店商品",JLabel.CENTER);
                                                failL.setSize(200,50);
                                                failL.setFont(new Font("微软雅黑",1, 12));
                                                failP.add(failL,BorderLayout.CENTER);
                                                failF.add(failP);
                                                failF.setVisible(true);
                                            }else
                                            {
                                                final String ProductInfo = txtProductInfo.getText();
                                                Operation.updateProductInfo2(ProductID,"price",ProductInfo);
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
                                            //e.printStackTrace();
                                        }
                                    }
                                });
                                updateProductInfoF.setSize(250, 100);
                                updateProductInfoF.setVisible(true);
                            }
                        });

                        proDescpB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame updateProductInfoF = new JFrame("Update Product Info");
                                JButton okB=new JButton("确认");
                                final JTextField txtProductID = new JTextField("请输入商品ID");
                                final JTextField txtProductInfo = new JTextField("请输入新信息");

                                updateProductInfoF.getContentPane().add(txtProductID,BorderLayout.NORTH);
                                updateProductInfoF.getContentPane().add(txtProductInfo,BorderLayout.CENTER);
                                updateProductInfoF.getContentPane().add(okB,BorderLayout.SOUTH);

                                okB.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        try {
                                            final String ProductID = txtProductID.getText();
                                            if(Operation.checkProductOnwer(sellerID,ProductID)==false){
                                                JFrame failF = new JFrame("Fail.");
                                                failF.setSize(300,150);
                                                failF.repaint();
                                                JPanel failP = new JPanel(new BorderLayout());
                                                JLabel failL=new JLabel("非本商店商品",JLabel.CENTER);
                                                failL.setSize(200,50);
                                                failL.setFont(new Font("微软雅黑",1, 12));
                                                failP.add(failL,BorderLayout.CENTER);
                                                failF.add(failP);
                                                failF.setVisible(true);
                                            }else
                                            {
                                                final String ProductInfo = txtProductInfo.getText();
                                                Operation.updateProductInfo2(ProductID,"description",ProductInfo);
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
                                            //e.printStackTrace();
                                        }
                                    }
                                });
                                updateProductInfoF.setSize(250, 100);
                                updateProductInfoF.setVisible(true);
                            }
                        });

                        proCateB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JFrame addProductInfoF = new JFrame("Add Product Info");

                                JPanel cateP1 = new JPanel();
                                JPanel cateP2 = new JPanel();
                                JPanel cateP3 = new JPanel();
                                JPanel otherP=new JPanel();
                                JScrollPane scrollPaneListPro1 = new JScrollPane();
                                JScrollPane scrollPaneListPro2 = new JScrollPane();
                                JScrollPane scrollPaneListPro3 = new JScrollPane();
                                JTextField txtCateCon1 = new JTextField("大类名称");
                                JTextField txtCateCon2 = new JTextField("中类别名称");
                                JTextField txtCateCon3 = new JTextField("小类别名称");
                                JButton okB = new JButton("确认");
                                final JTextField txtProductID = new JTextField("商品ID");

                                cateP1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 0),
                                        "请选择一个大类属性", TitledBorder.LEFT, TitledBorder.TOP,
                                        new java.awt.Font("微软雅黑", 1, 12)));
                                cateP2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 0),
                                        "请选择一个中类属性", TitledBorder.LEFT, TitledBorder.TOP,
                                        new java.awt.Font("微软雅黑", 1, 12)));
                                cateP3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 0),
                                        "请选择一个小类属性", TitledBorder.LEFT, TitledBorder.TOP,
                                        new java.awt.Font("微软雅黑", 1, 12)));

                                cateP1.setLayout(new GridLayout(1, 2));
                                cateP1.add(txtCateCon1);
                                cateP1.add(scrollPaneListPro1);
                                cateP2.setLayout(new GridLayout(1, 2));
                                cateP2.add(txtCateCon2);
                                cateP2.add(scrollPaneListPro2);
                                cateP3.setLayout(new GridLayout(1, 2));
                                cateP3.add(txtCateCon3);
                                cateP3.add(scrollPaneListPro3);
                                otherP.setLayout(new GridLayout(4,1));
                                otherP.add(txtProductID);
                                otherP.add(okB);
                                addProductInfoF.setLayout(new GridLayout(4,1));

                                addProductInfoF.getContentPane().add(cateP1);
                                addProductInfoF.getContentPane().add(cateP2);
                                addProductInfoF.getContentPane().add(cateP3);
                                addProductInfoF.getContentPane().add(otherP);

                                Vector firstCate = new Vector();
                                try {
                                    firstCate = Operation.getFirstCate();

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
                                txtCateCon1.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                                listFirst.addListSelectionListener(new ListSelectionListener() {
                                    public void valueChanged(ListSelectionEvent arg0) {
                                        txtCateCon1.setText("" + listFirst.getSelectedValue());
                                        Vector secondCate = new Vector();
                                        try {
                                            secondCate = Operation.getSecondCate(txtCateCon1.getText());
                                        }catch (Exception E) {
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

                                        JList listSecond = new JList(secondCate);
                                        txtCateCon2.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                                        listSecond.addListSelectionListener(new ListSelectionListener() {
                                            public void valueChanged(ListSelectionEvent arg0) {
                                                txtCateCon2.setText("" + listSecond.getSelectedValue());
                                                Vector thirdCate = new Vector();

                                                try {
                                                    thirdCate = Operation.getThirdCate(txtCateCon2.getText());
                                                }catch (Exception E) {
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

                                                JList listThird = new JList(thirdCate);
                                                txtCateCon3.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                                                listThird.addListSelectionListener(new ListSelectionListener() {
                                                    public void valueChanged(ListSelectionEvent arg0) {
                                                        txtCateCon3.setText("" + listThird.getSelectedValue());
                                                    }
                                                });
                                                scrollPaneListPro3.setViewportView(listThird);
                                            }
                                        });
                                        scrollPaneListPro2.setViewportView(listSecond);
                                    }
                                });
                                scrollPaneListPro1.setViewportView(listFirst);

                                Vector secondCate = new Vector();
                                try {
                                    secondCate = Operation.getSecondCate(txtCateCon1.getText());

                                }catch (Exception E) {
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

                                JList listSecond = new JList(secondCate);
                                txtCateCon2.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                                listSecond.addListSelectionListener(new ListSelectionListener() {
                                    public void valueChanged(ListSelectionEvent arg0) {
                                        txtCateCon2.setText("" + listSecond.getSelectedValue());
                                    }
                                });
                                scrollPaneListPro2.setViewportView(listSecond);

                                Vector thirdCate = new Vector();
                                try {
                                    thirdCate = Operation.getThirdCate(txtCateCon2.getText());

                                }catch (Exception E) {
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

                                JList listThird = new JList(thirdCate);
                                txtCateCon3.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                                listThird.addListSelectionListener(new ListSelectionListener() {
                                    public void valueChanged(ListSelectionEvent arg0) {
                                        txtCateCon3.setText("" + listThird.getSelectedValue());
                                    }
                                });
                                scrollPaneListPro3.setViewportView(listThird);

                                okB.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent arg0) {
                                        try {
                                            final String productID = txtProductID.getText();
                                            if(Operation.checkProductOnwer(sellerID,productID)==false){
                                                JFrame failF = new JFrame("Fail.");
                                                failF.setSize(300,150);
                                                failF.repaint();
                                                JPanel failP = new JPanel(new BorderLayout());
                                                JLabel failL=new JLabel("非本商店商品",JLabel.CENTER);
                                                failL.setSize(200,50);
                                                failL.setFont(new Font("微软雅黑",1, 12));
                                                failP.add(failL,BorderLayout.CENTER);
                                                failF.add(failP);
                                                failF.setVisible(true);
                                            }else{
                                                final String firstCategory = txtCateCon1.getText();
                                                final String secondCategory = txtCateCon2.getText();
                                                final String thirdCategory = txtCateCon3.getText();

                                                Operation.updateProductInfo3(productID,firstCategory,secondCategory,thirdCategory);
                                                JFrame successF = new JFrame("Success.");
                                                successF.setSize(200,150);
                                                successF.repaint();
                                                JPanel successP = new JPanel(new BorderLayout());
                                                JLabel successL=new JLabel("Update success.",JLabel.CENTER);
                                                successL.setSize(200,50);
                                                successL.setFont(new Font("微软雅黑",1, 15));
                                                successP.add(successL,BorderLayout.CENTER);
                                                successF.add(successP);
                                                successF.setVisible(true);
                                            }
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
                                addProductInfoF.setSize(400, 500);
                                addProductInfoF.setVisible(true);
                            }
                        });

                        updateProductF.setSize(250, 250);
                        updateProductF.setVisible(true);
                    }
                });

                queryProductB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame queryProductInfoF = new JFrame("Query Product Info");
                        JButton okB=new JButton("确认");
                        final JTextField txtProductID = new JTextField("请输入查询商品ID");

                        queryProductInfoF.getContentPane().add(txtProductID,BorderLayout.NORTH);
                        queryProductInfoF.getContentPane().add(okB,BorderLayout.SOUTH);

                        okB.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                try {
                                    final String ProductID = txtProductID.getText();
                                    String result=Operation.queryProductInfo(ProductID);

                                    JFrame successF = new JFrame("Success.");
                                    successF.setSize(500,300);
                                    successF.repaint();
                                    JPanel successP = new JPanel(new BorderLayout());
                                    successP.setLayout(new GridLayout(6,1));
                                    JLabel[] infoL=new JLabel[6];
                                    JLabel noInfoL=new JLabel("Product do not exist.",JLabel.CENTER);
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
                        queryProductInfoF.setSize(200, 100);
                        queryProductInfoF.setVisible(true);
                    }
                });

                queryProductInfoB.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        try {
                            Vector productInfo = new Vector();
                            productInfo = Operation.queryProductInfoForSeller(sellerID);

                            JList listProductInfo = new JList(productInfo);
                            JFrame successF = new JFrame("Success.");
                            JScrollPane scrollPaneListProduct = new JScrollPane();
                            scrollPaneListProduct.setViewportView(listProductInfo);
                            successF.getContentPane().add(scrollPaneListProduct);

                            successF.setSize(800, 300);
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

                //布局设置
                queryOrderF.getContentPane().setLayout(new GridLayout(1, 2));
                queryOrderP.setLayout(new GridLayout(4, 1));
                //背景设置
                queryOrderL.setIcon(new ImageIcon("src/image/123.jpg"));
                //添加组件
                queryOrderF.getContentPane().add(queryOrderL);
                queryOrderF.getContentPane().add(queryOrderP);
                queryOrderP.add(periodAmountB);
                queryOrderP.add(categoryB);
                queryOrderP.add(periodTopB);
                queryOrderP.add(TopSellB);

                //Frame设置
                queryOrderF.setSize(500, 200);
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
                                    final String orderNum = Operation.getOrderNum(sellerID,startDate, endDate);
                                    final String totalPay = Operation.getTotalPay(sellerID,startDate, endDate);
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
                                        orderNum = Operation.getOrderNum(sellerID,"FirstCategory", txtCateCon.getText());
                                        totalPay = Operation.getTotalPay(sellerID,"FirstCategory", txtCateCon.getText());
                                    }
                                    if (optionB.isSelected()) {
                                        orderNum = Operation.getOrderNum(sellerID,"SecondCategory", txtCateCon.getText());
                                        totalPay = Operation.getTotalPay(sellerID,"SecondCategory", txtCateCon.getText());
                                    }
                                    if (optionC.isSelected()) {
                                        orderNum = Operation.getOrderNum(sellerID,"ThirdCategory", txtCateCon.getText());
                                        totalPay = Operation.getTotalPay(sellerID,"ThirdCategory", txtCateCon.getText());
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
                                    productInfo = Operation.getTopProduct(sellerID,startDate, endDate);

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
                            mostComment = Operation.getMostCommentProduct(sellerID);
                            bestMark = Operation.getBestMarkProduct(sellerID);
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
            }
        });

        manageOrderB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    JTextField txtOrder = new JTextField("输入订单号");
                    JFrame successF = new JFrame("Success.");
                    JScrollPane scrollPaneListOrder = new JScrollPane();
                    JButton okB = new JButton("确认");
                    Vector orderInfo = new Vector();
                    orderInfo = Operation.queryOrderInfoForSeller(sellerID);
                    JList listOrder = new JList( orderInfo);
                    txtOrder.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 12));
                    listOrder.addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent arg0) {
                            txtOrder.setText("" + listOrder.getSelectedValue());
                        }
                    });
                    scrollPaneListOrder.setViewportView(listOrder);

                    successF.getContentPane().add(scrollPaneListOrder);
                    successF.getContentPane().add(txtOrder,BorderLayout.NORTH);
                    successF.getContentPane().add(okB,BorderLayout.SOUTH);

                    okB.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            try {
                                final String orderID = txtOrder.getText();
                                Operation.updateOrderInfo(orderID);
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
                                //e.printStackTrace();
                            }
                        }
                    });
                    successF.setSize(600, 200);
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

    public static void main(String[] args) throws SQLException {
        Seller seller=new Seller("191809");
        Operation.Connect();
    }

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