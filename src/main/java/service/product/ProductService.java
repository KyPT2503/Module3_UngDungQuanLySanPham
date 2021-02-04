package service.product;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "250399");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found.");
        } catch (SQLException e) {
            System.out.println("Can't connect");
        }
        return connection;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from mydatabase.product");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                products.add(new Product(id, name, price, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean update(int id, Product product) {
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update mydatabase.product set id=?,name=?,price=?,description=? where id=?");
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setInt(5, id);
            boolean isUpdated = preparedStatement.executeUpdate() > 0;
            if (isUpdated) {
                System.out.println("Product updated.");
                return true;
            } else {
                System.out.println("Product can't update.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void add(Product product) {
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into mydatabase.product (id, name, price, description) value (?,?,?,?)");
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setString(4, product.getDescription());
            boolean isAdded = preparedStatement.executeUpdate() > 0;
            if (isAdded) {
                System.out.println("new product added.");
            } else {
                System.out.println("can't add new product.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) {
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from mydatabase.product where id=?");
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product findById(int id) {
        List<Product> products = this.findAll();
        for (Product product : products) {
            if (id == product.getId()) return product;
        }
        return null;
    }
}
