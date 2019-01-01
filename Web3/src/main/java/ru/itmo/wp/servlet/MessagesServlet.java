package ru.itmo.wp.servlet;

import com.google.gson.Gson;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class MessagesServlet extends HttpServlet {

    class Message {
        private String user;
        private String text;

        Message(String user, String text) {
            this.user = user;
            this.text = text;
        }

    }

    List<Message> messageList = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        HttpSession session = request.getSession(true);
        String uri = request.getRequestURI();

        if (uri.endsWith("auth")) {
            String userName = request.getParameter("user");
            if (userName == null) {
                userName = "";
            }

            session.setAttribute("user", userName);
            responseWrite(response, userName);
        }

        if (uri.endsWith("add")) {
            String text = request.getParameter("text");
            String user = (String) session.getAttribute("user");
            if (user == null) {
                user = "";
            }
            Message message = new Message(user, text);
            messageList.add(message);
            responseWrite(response, text);
        }

        if (uri.endsWith("findAll")) {
            responseWrite(response, messageList);
        }
    }

    private void responseWrite(HttpServletResponse response, Object object) throws IOException {
        String json = new Gson().toJson(object);
        response.getWriter().print(json);
        response.getWriter().flush();
    }

}

