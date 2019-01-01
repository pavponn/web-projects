package ru.itmo.wp.servlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;



import ru.itmo.wp.util.ImageUtils;

import java.util.Base64;

public class CaptchaFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Random random = new Random();

        HttpSession session = request.getSession(true);

        if (session.getAttribute("CaptchaPassed") != null &&
                session.getAttribute("CaptchaPassed").equals("passed")) {
            chain.doFilter(request, response);
            return;
        }

        if (request.getMethod().equals("GET")) {
            if (session.getAttribute("CaptchaCode") == null || session.getAttribute("CaptchaCode").equals("")) {
                int code = random.nextInt(899) + 100;
                session.setAttribute("CaptchaCode", Integer.toString(code));
            }
            response.setContentType("text/html");
            String img = Base64.getEncoder().encodeToString(
                    ImageUtils.toPng(session.getAttribute("CaptchaCode").toString()));
            byte[] captchaFile = Files.readAllBytes(
                    Paths.get(getServletContext().getRealPath("/static/captcha.html")));
            String code = String.format(new String(captchaFile, "UTF-8"), img);
            write(code, response);
        } else {

            if (request.getParameter("CaptchaUser") != null &&
                    request.getParameter("CaptchaUser").equals(session.getAttribute("CaptchaCode"))) {
                session.setAttribute("CaptchaPassed", "passed");
            }
            response.sendRedirect(request.getRequestURI());
            session.setAttribute("CaptchaCode", "");
        }

    }

    public void write(String str, HttpServletResponse response) throws IOException {
        response.getWriter().write(str);
        response.getWriter().flush();
    }
}

