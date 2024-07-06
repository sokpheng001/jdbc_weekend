package view;

import model.Product;
import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import repository.ProductRepository;
import repository.ProductRepositoryImpl;
import repository.UserRepositoryImpl;


import java.util.Scanner;

public class UI {
    private final static ProductRepository productRepository = new ProductRepositoryImpl();
    public static User loginPage(){
        System.out.print("[+] Insert email: ");
        String email = new Scanner(System.in).next();
        System.out.print("[+] Insert password: ");
        String password = new Scanner(System.in).next();
        User user = new UserRepositoryImpl()
                .login(email.trim(),password.trim()); // trim() method used to remove spaces
        return user;
    }
    public static void listAllProductPage(){
        Table table = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        String []pColumnNames = {"ID","PRODUCT NAME","RELEASED DATE","PRICE"};
        for (String col: pColumnNames){
            table.addCell(col, new CellStyle(CellStyle.HorizontalAlign.center),1);
        }
        for(int i=0;i<4;i++){
            table.setColumnWidth(i,25,25);
        }
//        add product contents
        for(Product product: productRepository.listAllProducts()){
            table.addCell(product.getId().toString(),new CellStyle(CellStyle.HorizontalAlign.center),1);
            table.addCell(product.getProductName(),new CellStyle(CellStyle.HorizontalAlign.center),1);
            table.addCell(product.getReleasedDate().toString(),new CellStyle(CellStyle.HorizontalAlign.center),1);
            table.addCell(product.getPrice().toString(),new CellStyle(CellStyle.HorizontalAlign.center),1);
        }
        System.out.println(table.render());
    }
    public static  void thumbnail(){
        System.out.println(""" 
                ----------------------------------
                1. List all products
                2. Search Product by ID
                3. Create new Product
                4. Search Product by Name
                5. Update Product by ID
                6. Delete Product by ID
                0. EXIT
                ----------------------------------
                ....... 99. User - Setting .......""");
    }
    public static void home(){
        System.out.println("=".repeat(46));
        System.out.println("-".repeat(8) + " Welcome to System Management " + "-".repeat(8));
        System.out.println("=".repeat(46));
        if(loginPage()!=null){
            thumbnail();
        }
    }
}
