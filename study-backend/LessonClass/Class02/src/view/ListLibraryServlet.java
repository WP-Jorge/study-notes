package view;

import model.DVDItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class ListLibraryServlet extends HttpServlet {
    DVDItem item1 = new DVDItem("java程序开发", "2020", "教育");
    DVDItem item2 = new DVDItem("Python程序开发", "2019", "教育");
    DVDItem item3 = new DVDItem("C/C++程序开发", "2015", "教育");
    DVDItem item4 = new DVDItem("web网页开发", "2018", "教育");
    DVDItem item5 = new DVDItem("web前端基础", "2020", "教育");
    List itemList = new LinkedList();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<ul>");
        Iterator it = itemList.listIterator();
        while (it.hasNext()) {
            DVDItem item = (DVDItem) it.next();
            out.println("<li>书名：" + item.getTitle() + "&nbsp;&nbsp;&nbsp;&nbsp;年份：" + item.getYear() + "&nbsp;&nbsp;&nbsp;&nbsp;类型：" + item.getGenre() + "</li>");
            // out.println("<li>" + item.getYear() + "</li>");
            // out.println("<li>" +  + "</li>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }
}
