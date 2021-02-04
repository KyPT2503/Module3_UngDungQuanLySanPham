package controller;

import model.Product;
import service.product.IProductService;
import service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/home")
public class ProductController extends HttpServlet {
    private static final IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        if (action.equals("")) {
            showAll(request, response);
        } else if (action.equals("update")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/product/update.jsp");
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String description = request.getParameter("description");
            request.removeAttribute("action");
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("price", price);
            request.setAttribute("description", description);
            dispatcher.forward(request, response);
        } else if (action.equals("delete")) {
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);
            boolean isDeleted = productService.delete(id);
            if (isDeleted) {
                response.sendRedirect("/home");
            } else {
                response.sendRedirect("/product/error-404/jsp");
            }
        }
    }

    private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("create")) {
            addProduct(request, response);
        } else if (action.equals("update")) {
            updateProduct(request, response);
        } else if (action.equals("search")) {
            System.out.println("search run");
            try {
                String idString = request.getParameter("id");
                int id = Integer.parseInt(idString);
                Product product = productService.findById(id);
                if (product == null) {
                    throw new NumberFormatException();
                }
                request.setAttribute("id", product.getId());
                request.setAttribute("name", product.getName());
                request.setAttribute("price", product.getPrice());
                request.setAttribute("description", product.getDescription());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/product/detail.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                response.sendRedirect("/error-404.jsp");
            }
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            String description = request.getParameter("description");
            productService.update(id, new Product(id, name, price, description));
            response.sendRedirect("/home");
        } catch (NumberFormatException e) {
            response.sendRedirect("/error-404/jsp");
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            String description = request.getParameter("description");
            productService.add(new Product(id, name, price, description));
            try {
                response.sendRedirect("/home");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            try {
                response.sendRedirect("/error-404.jsp");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}