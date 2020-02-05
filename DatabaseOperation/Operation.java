package DatabaseOperation;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.*;
import java.util.Vector;

public class Operation {
    static Connection conn;
    // 驱动
    static String driver = "com.mysql.jdbc.Driver";
    //static String url = "jdbc:mysql://192.168.124.13:3306/ShopWeb";
    static String url = "jdbc:mysql://127.0.0.1:3306/ShopWeb";
    //static String user = "db";
    static String user = "root";
    static String password = "";

    public static void Connect() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void insertCustomerInfo(String customerID, String nickName,
                                             int age, String gender, Date DoB,
                                             String passWords, String phoneNumber) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "insert into Customer value('" + customerID + "','" + nickName +"',"
                + age +",'" + gender + "','" + DoB +"','" + passWords +"','"+ phoneNumber+"')";
        statement.execute(sql);
    }

    public static void deleteCustomerInfo(String cusID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "DELETE from customer WHERE customerID = "+"'"+cusID+"'";
        statement.execute(sql);
    }

    public static void updateCustomerInfo(String cusID,String attribute,String newInfo) throws SQLException{
        Statement statement = conn.createStatement();
        String sql = "update customer set "+attribute+"='"+newInfo+"' WHERE customerID = '"+cusID+"'";
        statement.execute(sql);
    }

    public static String queryCustomerInfo(String cusID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from Customer where customerID="+"'"+cusID+"'";
        ResultSet rs = statement.executeQuery(sql);
        String nickName="";
        String age="";
        String gender="";
        java.util.Date dateDoB=new java.util.Date();
        String phoneNumber="";

        while (rs.next()) {
            nickName = rs.getString("customerNickName");
            age = rs.getString("customerAge");
            gender = rs.getString("customerGender");
            dateDoB = rs.getDate("customerDoB");
            phoneNumber = rs.getString("customerTelNo");
        }
        rs.close();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String DoB=sdf.format(dateDoB);

        return "CustomerID: "+cusID+","+
                "NickName: "+ nickName+","+
                "Age: "+age+","+
                "Gender: "+ gender+","+
                "DoB: "+ DoB+","+
                "PhoneNumber: "+ phoneNumber;
    }

    public static void insertSellerInfo(String sellerID, String shopName,String sellerAddress, Date shopOpenTime) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "insert into Seller value('" + sellerID + "','" + shopName +"','" + sellerAddress + "','" + shopOpenTime +"')";
        statement.execute(sql);
    }
    public static void insertSellerInfo(String sellerID, String shopName,String sellerAddress, Date shopOpenTime,String pass) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "insert into Seller value('" + sellerID + "','" + shopName +"','" + sellerAddress + "','" + shopOpenTime +"','"+pass+"')";
        statement.execute(sql);
    }

    public static void deleteSellerInfo(String sellerID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "DELETE from seller WHERE sellerID = "+"'"+sellerID+"'";
        statement.execute(sql);
    }

    public static void updateSellerInfo(String sellerID,String attribute,String newInfo) throws SQLException{
        Statement statement = conn.createStatement();
        String sql = "update seller set "+attribute+"='"+newInfo+"' WHERE sellerID = '"+sellerID+"'";
        statement.execute(sql);
    }

    public static String querySellerInfo(String sellerID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from Seller where sellerID="+"'"+sellerID+"'";

        ResultSet rs = statement.executeQuery(sql);
        String shopName="";
        String sellerAddress="";
        java.util.Date shopOpenTimeDate=new java.util.Date();

        while (rs.next()) {
            shopName = rs.getString("shopName");
            sellerAddress = rs.getString("sellerAddress");
            shopOpenTimeDate = rs.getDate("shopOpenTime");
        }
        rs.close();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String shopOpenTime=sdf.format(shopOpenTimeDate);

        return "SellerID: "+sellerID+","+
                "ShopName: "+ shopName+","+
                "SellerAddress: "+sellerAddress+","+
                "ShopOpenTime: "+ shopOpenTime;
    }

    public static void insertProductInfo(String productID,String productName,String categoryID,String firstCategory,
                                         String secondCategory,String thirdCategory,String price,String description,String sellerID) throws SQLException {

        Statement statement = conn.createStatement();
        String sql4 = "insert into FirstCategory value('" + categoryID + "','" + firstCategory +"')";
        statement.execute(sql4);
        String sql3 = "insert into SecondCategory value('" + categoryID + "','" + secondCategory +"')";
        statement.execute(sql3);
        String sql2 = "insert into ThirdCategory value('" + categoryID + "','" + thirdCategory + "')";
        statement.execute(sql2);
        String sql = "insert into Product value('" + productID + "','" + productName +"','" + categoryID+"')";
        statement.execute(sql);
        String sql0= "insert into ProductOnSell value('" + productID + "','" + sellerID +"',"+price+",'" + description+"')";
        statement.execute(sql0);
    }

    public static void deleteProductInfo(String productID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql0 = "select categoryID from Product WHERE productID='"+productID+"'";
        ResultSet rs = statement.executeQuery(sql0);
        rs.next();
        String categoryID = rs.getString(1);
        String sql1 = "DELETE from productOnSell WHERE productID= '"+productID+"'";
        statement.execute(sql1);
        String sql2 = "DELETE from product WHERE productID= '"+productID+"'";
        statement.execute(sql2);
        String sql3 = "DELETE from thirdCategory WHERE categoryID = '"+categoryID+"'";
        statement.execute(sql3);
        String sql4 = "DELETE from secondCategory WHERE categoryID = '"+categoryID+"'";
        statement.execute(sql4);
        String sql5 = "DELETE from FirstCategory WHERE categoryID = '"+categoryID+"'";
        statement.execute(sql5);

    }
    public static void updateOrderInfo(String orderNo) throws SQLException{
        Statement statement = conn.createStatement();
        String sql = "update orders set orderSituation='Delivery'"+"WHERE orderNo = '"+orderNo+"'";
        statement.execute(sql);
    }

    public static void updateProductInfo(String productID,String attribute,String newInfo) throws SQLException{
        Statement statement = conn.createStatement();
        String sql = "update product set "+attribute+"='"+newInfo+"' WHERE productID = '"+productID+"'";
        statement.execute(sql);
    }

    public static void updateProductInfo2(String productID,String attribute,String newInfo) throws SQLException{
        Statement statement = conn.createStatement();
        String sql = "update productOnSell set "+attribute+"='"+newInfo+"' WHERE productID = '"+productID+"'";
        statement.execute(sql);
    }

    public static void updateProductInfo3(String productID,String firstCate,String secondCate,String thirdCate) throws SQLException{
        Statement statement = conn.createStatement();
        String sql0 = "select categoryID from Product WHERE productID = '"+productID+"'";
        ResultSet rs = statement.executeQuery(sql0);
        rs.next();
        String categoryID = rs.getString(1);
        String sql1 = "update FirstCategory set categoryName='"+firstCate+"' WHERE categoryID = '"+categoryID+"'";
        statement.execute(sql1);
        String sql2 = "update SecondCategory set categoryName='"+secondCate+"' WHERE categoryID = '"+categoryID+"'";
        statement.execute(sql1);
        String sql3 = "update ThirdCategory set categoryName='"+thirdCate+"' WHERE categoryID = '"+categoryID+"'";
        statement.execute(sql1);
    }

    public static String queryProductInfo(String productID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select p.productName,p.categoryID,fc.categoryName," +
                "sc.categoryName,tc.categoryName from product p,FirstCategory fc,SecondCategory sc,ThirdCategory tc " +
                "where p.categoryID=tc.categoryID and sc.categoryID=tc.categoryID and sc.categoryID=fc.categoryID " +
                "and productID="+"'"+productID+"'";

        ResultSet rs = statement.executeQuery(sql);
        String productName="";
        String categoryID="";
        String firstCategory="";
        String secondCategory="";
        String thirdCategory="";

        while (rs.next()) {
            productName = rs.getString(1);
            categoryID = rs.getString(2);
            firstCategory = rs.getString(3);
            secondCategory = rs.getString(4);
            thirdCategory = rs.getString(5);
        }
        rs.close();

        return "ProductID: "+productID+","+
                "ProductName: "+ productName+","+
                "CategoryID: "+categoryID+","+
                "FirstCategory: "+ firstCategory+","+
                "SecondCategory: "+secondCategory+","+
                "ThirdCategory: "+ thirdCategory;
    }

    public static Vector queryProductInfoForSeller(String sellerID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select p.productName,p.categoryID,fc.categoryName," +
                "sc.categoryName,tc.categoryName,p.productID from product p,FirstCategory fc,SecondCategory sc,ThirdCategory tc,productOnSell po " +
                "where p.categoryID=tc.categoryID and sc.categoryID=tc.categoryID and sc.categoryID=fc.categoryID and po.productID=p.productID " +
                "and sellerID="+"'"+sellerID+"'";

        ResultSet rs = statement.executeQuery(sql);
        String productID="";
        String productName="";
        String categoryID="";
        String firstCategory="";
        String secondCategory="";
        String thirdCategory="";
        Vector v = new Vector();
        String productInfo="";
        while (rs.next()) {
            productName = rs.getString(1);
            categoryID = rs.getString(2);
            firstCategory = rs.getString(3);
            secondCategory = rs.getString(4);
            thirdCategory = rs.getString(5);
            productID = rs.getString(6);
            productInfo ="ProductID: "+productID+", "+
                    "ProductName: "+ productName+", "+
                    "CategoryID: "+categoryID+", "+
                    "FirstCategory: "+ firstCategory+", "+
                    "SecondCategory: "+secondCategory+", "+
                    "ThirdCategory: "+ thirdCategory;
            v.add(productInfo);
        }
        rs.close();

        return v;
    }
    public static Vector queryOrderInfoForSeller(String sellerID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select o.orderNo,orderSituation,productID," +
                "productNum from orders o, Productinorder po " +
                "where o.orderNo=po.orderNo and ordersituation='Placed' " +
                "and sellerID="+"'"+sellerID+"'";

        ResultSet rs = statement.executeQuery(sql);
        String productID="";
        String productNum="";
        String orderNo="";
        String situation="";
        Vector v = new Vector();
        String orderInfo="";
        while (rs.next()) {
            orderNo = rs.getString(1);
            situation = rs.getString(2);
            productID = rs.getString(3);
            productNum = rs.getString(4);

            orderInfo ="OrderNo: "+orderNo+", "+
                    "ProductID: "+ productID+", "+
                    "ProductNum: "+productNum+", "+
                    "orderSituation: "+ situation;
            v.add(orderInfo);
        }
        rs.close();

        return v;
    }

    public static String getOrderNum(Date startDate,Date endDate) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select count(*) from Orders where orderCreateDate"+">='"+startDate+
                "' And orderCreateDate"+"<='"+endDate+"'";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String orderNum = rs.getString(1);

        rs.close();
        return orderNum;
    }
    public static String getOrderNum(String cateType,String cateName) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select count(distinct orderNo) from ProductInOrder po,Product p,"+cateType+" c " +
                "where c.categoryName='"+cateName+"' and c.categoryID=p.categoryID and p.productID=po.ProductID";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String orderNum = rs.getString(1);

        rs.close();
        return orderNum;
    }
    public static String getOrderNum(String sellerID,String cateType,String cateName) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select count(distinct orderNo) from ProductInOrder po,Product p,"+cateType+" c " +
                "where c.categoryName='"+cateName+"' and c.categoryID=p.categoryID and p.productID=po.ProductID " +
                "and po.sellerID='"+sellerID+"'";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String orderNum = rs.getString(1);

        rs.close();
        return orderNum;
    }
    public static String getOrderNum(String sellerID,Date startDate,Date endDate) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select count(*) from Orders o,ProductInOrder po " +
                "where o.orderNo=po.orderNo and po.sellerID='" +sellerID+"' and "+
                "orderCreateDate"+">='"+startDate+
                "' And orderCreateDate"+"<='"+endDate+"'";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String orderNum = rs.getString(1);

        rs.close();
        return orderNum;
    }

    public static String getTotalPay(Date startDate,Date endDate) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select round(sum(totalPay),2) from Orders where orderCreateDate"+">='"+startDate+
                "' And orderCreateDate"+"<='"+endDate+"'";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String totalPay = rs.getString(1);

        rs.close();
        return totalPay;
    }
    public static String getTotalPay(String cateType,String cateName) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select round(sum(totalPay),2) from Orders o,ProductInOrder po,Product p,"+cateType+" c " +
                "where c.categoryName='"+cateName+"' and c.categoryID=p.categoryID and p.productID=po.ProductID and o.orderNo=po.orderNo";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String totalPay = rs.getString(1);

        rs.close();
        return totalPay;
    }
    public static String getTotalPay(String sellerID,String cateType,String cateName) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select round(sum(totalPay),2) from Orders o,ProductInOrder po,Product p,"+cateType+" c " +
                "where c.categoryName='"+cateName+"' and c.categoryID=p.categoryID and p.productID=po.ProductID and o.orderNo=po.orderNo " +
                "and po.sellerID='"+sellerID+"'";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String totalPay = rs.getString(1);

        rs.close();
        return totalPay;
    }
    public static String getTotalPay(String sellerID,Date startDate,Date endDate) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select round(sum(totalPay),2) from Orders o,ProductInOrder po " +
                "where o.orderNo=po.orderNo and po.sellerID='" +sellerID+"' and "+
                "orderCreateDate"+">='"+startDate+
                "' And orderCreateDate"+"<='"+endDate+"'";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String totalPay = rs.getString(1);

        rs.close();
        return totalPay;
    }

    public static Vector getFirstCate() throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select distinct categoryName from FirstCategory";
        ResultSet rs = statement.executeQuery(sql);
        String category = null;
        Vector v = new Vector();
        while (rs.next()) {
            category = rs.getString("categoryName");
            v.add(category);
        }
        rs.close();
        return v;
    }
    public static Vector getSecondCate() throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select distinct categoryName from SecondCategory";
        ResultSet rs = statement.executeQuery(sql);
        String category = null;
        Vector v = new Vector();
        while (rs.next()) {
            category = rs.getString("categoryName");
            v.add(category);
        }
        rs.close();
        return v;
    }
    public static Vector getSecondCate(String firstCate) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select distinct sc.categoryName from SecondCategory sc,FirstCategory fc " +
                "where sc.categoryID=fc.categoryID and fc.categoryName='"+firstCate+"'";
        ResultSet rs = statement.executeQuery(sql);
        String category = null;
        Vector v = new Vector();
        while (rs.next()) {
            category = rs.getString("categoryName");
            v.add(category);
        }
        rs.close();
        return v;
    }
    public static Vector getThirdCate() throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select distinct categoryName from ThirdCategory";
        ResultSet rs = statement.executeQuery(sql);
        String category = null;
        Vector v = new Vector();
        while (rs.next()) {
            category = rs.getString("categoryName");
            v.add(category);
        }
        rs.close();
        return v;
    }
    public static Vector getThirdCate(String secondCate) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql = "select distinct tc.categoryName from SecondCategory sc,ThirdCategory tc " +
                "where sc.categoryID=tc.categoryID and sc.categoryName='"+secondCate+"'";
        ResultSet rs = statement.executeQuery(sql);
        String category = null;
        Vector v = new Vector();
        while (rs.next()) {
            category = rs.getString("categoryName");
            v.add(category);
        }
        rs.close();
        return v;
    }

    public static Vector getTopProduct(Date startDate,Date endDate) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql ="select count(comment) as commentNum, p.productID, p.productName from Orders o,ProductInOrder po, Product p " +
                "where o.orderNo=po.orderNo and po.productID=p.ProductID and orderCreateDate"+">='"+startDate+
                "' And orderCreateDate"+"<='"+endDate+"' " +
                "GROUP BY p.productID "+
                "ORDER BY commentNum DESC";
        ResultSet rs = statement.executeQuery(sql);
        String productInfo = null;
        Vector v = new Vector();
        for (int i=0;i<10&&rs.next();i++) {
            productInfo = "ProductID: "+rs.getString("productID")
                    +", ProductName: "+rs.getString("productName")
                    +", CommentNum: "+rs.getString("commentNum");
            v.add(productInfo);
        }
        rs.close();
        return v;
    }
    public static Vector getTopProduct(String sellerID,Date startDate,Date endDate) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql ="select count(comment) as commentNum, p.productID, p.productName from Orders o,ProductInOrder po, Product p " +
                "where o.orderNo=po.orderNo and po.productID=p.ProductID and orderCreateDate"+">='"+startDate+
                "' And orderCreateDate"+"<='"+endDate+"' " +
                "and po.sellerID='"+sellerID+"' "+
                "GROUP BY p.productID "+
                "ORDER BY commentNum DESC";
        ResultSet rs = statement.executeQuery(sql);
        String productInfo = null;
        Vector v = new Vector();
        for (int i=0;i<10&&rs.next();i++) {
            productInfo = "ProductID: "+rs.getString("productID")
                    +", ProductName: "+rs.getString("productName")
                    +", CommentNum: "+rs.getString("commentNum");
            v.add(productInfo);
        }
        rs.close();
        return v;
    }
    public static Vector getTopSeller(Date startDate,Date endDate) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql ="select count(o.orderNo) as sellNum, s.sellerID, s.shopName,sellerAddress,shopOpenTime " +
                "from Orders o,ProductInOrder po, Seller s " +
                "where o.orderNo=po.orderNo and po.sellerID=s.sellerID and orderCreateDate"+">='"+startDate+
                "' And orderCreateDate"+"<='"+endDate+"' " +
                "GROUP BY s.sellerID "+
                "ORDER BY sellNum DESC";
        ResultSet rs = statement.executeQuery(sql);
        String sellerInfo = null;
        Vector v = new Vector();
        for (int i=0;i<10&&rs.next();i++) {
            sellerInfo = "SellerID: "+rs.getString("sellerID")
                    +", ShopName: "+rs.getString("shopName")
                    +", SellerAddress: "+rs.getString("sellerAddress")
                    +", ShopOpenTime: "+rs.getString("shopOpenTime")
                    +", SellNum: "+rs.getString("sellNum");
            v.add(sellerInfo);
        }
        rs.close();
        return v;
    }
    public static Vector getTopCustomer(Date startDate,Date endDate) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql ="select sum(totalPay) as amountPrice, c.customerID,customerNickName,customerAge,customerGender,customerDoB,customerTelNo " +
                "from Orders o, Customer c " +
                "where o.customerID=c.customerID and orderCreateDate"+">='"+startDate+
                "' And orderCreateDate"+"<='"+endDate+"' " +
                "GROUP BY c.customerID "+
                "ORDER BY amountPrice DESC";
        ResultSet rs = statement.executeQuery(sql);
        String customerInfo = null;
        Vector v = new Vector();
        for (int i=0;i<10&&rs.next();i++) {
            customerInfo = "CustomerID: "+rs.getString("customerID")
                    +", CustomerNickName: "+rs.getString("customerNickName")
                    +", CustomerAge: "+rs.getString("customerAge")
                    +", CustomerGender: "+rs.getString("customerGender")
                    +", CustomerDoB: "+rs.getString("customerDoB")
                    +", CustomerTelNo: "+rs.getString("customerTelNo")
                    +", AmountPrice: "+rs.getString("amountPrice");
            v.add(customerInfo);
        }
        rs.close();
        return v;
    }

    public static Vector getMostCommentProduct() throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql ="select count(comment) as commentNum, p.productID, p.productName from Orders o,ProductInOrder po, Product p " +
                "where o.orderNo=po.orderNo and po.productID=p.ProductID " +
                "GROUP BY p.productID "+
                "ORDER BY commentNum DESC";
        ResultSet rs = statement.executeQuery(sql);
        String productInfo = null;
        Vector v = new Vector();
        for (int i=0;i<10&&rs.next();i++) {
            productInfo = "ProductID: "+rs.getString("productID")
                    +", ProductName: "+rs.getString("productName")
                    +", CommentNum: "+rs.getString("commentNum");
            v.add(productInfo);
        }
        rs.close();
        return v;
    }
    public static Vector getMostCommentProduct(String sellerID) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql ="select count(comment) as commentNum, p.productID, p.productName from Orders o,ProductInOrder po, Product p " +
                "where o.orderNo=po.orderNo and po.productID=p.ProductID and po.sellerID='" +sellerID+"' "+
                "GROUP BY p.productID "+
                "ORDER BY commentNum DESC";
        ResultSet rs = statement.executeQuery(sql);
        String productInfo = null;
        Vector v = new Vector();
        for (int i=0;i<10&&rs.next();i++) {
            productInfo = "ProductID: "+rs.getString("productID")
                    +", ProductName: "+rs.getString("productName")
                    +", CommentNum: "+rs.getString("commentNum");
            v.add(productInfo);
        }
        rs.close();
        return v;
    }

    public static Vector getBestMarkProduct() throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql ="select round(Avg(productMark),2) as avgMark, p.productID, p.productName from Orders o,ProductInOrder po, Product p " +
                "where o.orderNo=po.orderNo and po.productID=p.ProductID " +
                "and productMark <> 0 "+
                "GROUP BY p.productID "+
                "ORDER BY avgMark DESC ";
        ResultSet rs = statement.executeQuery(sql);
        String productInfo = null;
        Vector v = new Vector();
        for (int i=0;i<10&&rs.next();i++) {
            productInfo = "ProductID: "+rs.getString("productID")
                    +", ProductName: "+rs.getString("productName")
                    +", AvgMark: "+rs.getString("avgMark");
            v.add(productInfo);
        }
        rs.close();
        return v;
    }
    public static Vector getBestMarkProduct(String sellerID) throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String sql ="select round(Avg(productMark),2) as avgMark, p.productID, p.productName from Orders o,ProductInOrder po, Product p " +
                "where o.orderNo=po.orderNo and po.productID=p.ProductID  and po.sellerID='" +sellerID+"' " +
                "and productMark <> 0 "+
                "GROUP BY p.productID "+
                "ORDER BY avgMark DESC ";
        ResultSet rs = statement.executeQuery(sql);
        String productInfo = null;
        Vector v = new Vector();
        for (int i=0;i<10&&rs.next();i++) {
            productInfo = "ProductID: "+rs.getString("productID")
                    +", ProductName: "+rs.getString("productName")
                    +", AvgMark: "+rs.getString("avgMark");
            v.add(productInfo);
        }
        rs.close();
        return v;
    }

    public static Vector getMonthInfo() throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String[] sql =new String[12];
        Vector v = new Vector();
        for(int i=0;i<12;i++){
            sql[i]="select count(orderNo) as orderNum, round(sum(totalPay),2) as totalPay " +
                    "from Orders where month(orderCreateDate)='"+(i+1)+"'";
            ResultSet rs = statement.executeQuery(sql[i]);
            rs.next();
            v.add((i+1)+"月订单数："+rs.getString(1)+", "+(i+1)+"月总金额："+rs.getString(2));
            rs.close();
        }
        return v;
    }

    public static Vector getHourInfo() throws SQLException, UnsupportedEncodingException {
        Statement statement = conn.createStatement();
        String[] sql =new String[24];
        Vector v = new Vector();
        for(int i=0;i<24;i++){
            sql[i]="select count(orderNo) as orderNum " +
                    "from Orders where hour(orderCreateTime)='"+(i)+"'";
            ResultSet rs = statement.executeQuery(sql[i]);
            rs.next();
            v.add(String.format("%2d",(i)).replace(" ", "0")
                    +":00-"+String.format("%2d",(i)).replace(" ", "0")
                    +":59 订单总数："+rs.getString(1));
            rs.close();
        }
        return v;
    }

    public static boolean checkSellerInfo(String sellerID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from Seller where sellerID="+"'"+sellerID+"'";
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            rs.close();
            return true;
        }

        rs.close();
        return false;
    }
    public static int checkSellerInfo(String sellerID,String sellerPassword) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from Seller where sellerID="+"'"+sellerID+"'";
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            if(rs.getString("sellerPassword").equals(sellerPassword)){
                rs.close();
                return 1;
            }else{
                rs.close();
                return 0;
            }
        }
        rs.close();
        return -1;
    }


    public static boolean checkCategoryInfo(String categoryID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from FirstCategory where categoryID="+"'"+categoryID+"'";
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            rs.close();
            return false;
        }

        rs.close();
        return true;
    }

    public static boolean checkCustomerInfo(String customerID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from Customer where customerID="+"'"+customerID+"'";
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            rs.close();
            return false;
        }

        rs.close();
        return true;
    }

    public static boolean checkProductInfo(String productID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from Product where productID="+"'"+productID+"'";
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            rs.close();
            return false;
        }

        rs.close();
        return true;
    }

    public static boolean checkProductOnwer(String sellerID,String productID) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from Product p, ProductOnSell po where " +
                "p.productID=po.productID and sellerID="+"'"+sellerID+"' and "+
                "p.productID='"+productID+"'";
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            rs.close();
            return true;
        }

        rs.close();
        return false;
    }

    public static boolean checkOrderOnwer(String sellerID,String orderNo) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from orders o, ProductInOrder pi where " +
                "o.orderNo=pi.orderNo and sellerID="+"'"+sellerID+"' and "+
                "o.orderNo='"+orderNo+"'";
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            rs.close();
            return true;
        }

        rs.close();
        return false;
    }

    public static int checkAdminInfo(String adminID,String adminPassword) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from Administrator where administratorID="+"'"+adminID+"'";
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            if(rs.getString("administratorPassword").equals(adminPassword)){
                rs.close();
                return 1;
            }else{
                rs.close();
                return 0;
            }

        }
        rs.close();
        return -1;
    }
}
