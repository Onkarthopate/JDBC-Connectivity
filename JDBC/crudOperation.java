package jdbcmodule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class crudOperation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Press 1. Enter to Employee Database.");
        System.out.println("Press 2. Exit");
        int ip = sc.nextInt();

        if (ip == 1) {
            while (true) {
                System.out.println("\n1. Insert Data");
                System.out.println("2. Update Data");
                System.out.println("3. Read Data");
                System.out.println("4. Delete Data");
                System.out.println("5. Exit");

                System.out.println("Enter Your choice:");
                int ip1 = sc.nextInt();

                
                switch (ip1) {
                    case 1:
                        insertData(sc);
                        break;

                    case 2:
                        updateData(sc);
                        break;

                    case 3:
                        readData();
                        break;

                    case 4:
                        deleteData(sc);
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid Input");
                }
            }
        } else {
            System.out.println("Exiting...");
            sc.close();
        }
    }

    private static void insertData(Scanner sc) {
     
		System.out.println("Enter emp id: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.println("Enter emp name: ");
        String name = sc.nextLine();

        System.out.println("Enter emp address: ");
        String address = sc.nextLine();

        System.out.println("Enter emp salary: ");
        int salary = sc.nextInt();

        insertDynamically(id, name, address, salary);
    }

    private static void insertDynamically(int id, String name, String address, int salary) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_name", "DB_Username", "yourDB_UsernamePassword");
            System.out.println("Connection Created");

            // For insertion
            PreparedStatement ps = con.prepareStatement("INSERT INTO Carholder VALUES (?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setInt(4, salary);
            ps.executeUpdate();

            System.out.println("Data Inserted");
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void readData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_name", "DB_Username", "yourDB_UsernamePasswordyourDB_UsernamePassword");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Carholder");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt(1) + " | Name: " + rs.getString(2) + " | Address: " + rs.getString(3) + " | Salary: " + rs.getInt(4));
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateData(Scanner sc) {
        System.out.println("Enter emp id to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline

        // Check if the ID exists before attempting to update
        if (!idExists(id)) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        System.out.println("Enter new emp name: ");
        String name = sc.nextLine();

        System.out.println("Enter new emp address: ");
        String address = sc.nextLine();

        System.out.println("Enter new emp salary: ");
        int salary = sc.nextInt();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_name", "DB_Username", "yourDB_UsernamePassword");
            PreparedStatement ps = con.prepareStatement("UPDATE Carholder SET name=?, address=?, salary=? WHERE id=?");
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setInt(3, salary);
            ps.setInt(4, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data Updated");
            } else {
                System.out.println("No data updated. Employee with ID " + id + " might not exist.");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteData(Scanner sc) {
        System.out.println("Enter emp id to delete: ");
        int id = sc.nextInt();

        // Check if the ID exists before attempting to delete
        if (!idExists(id)) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_name", "DB_Username", "yourDB_UsernamePassword");
            PreparedStatement ps = con.prepareStatement("DELETE FROM Carholder WHERE id=?");
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data Deleted");
            } else {
                System.out.println("No data deleted. Employee with ID " + id + " might not exist.");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
        }
    }

    private static boolean idExists(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_name", "DB_Username", "yourDB_UsernamePassword");
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM Carholder WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Return true if count is greater than 0
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
