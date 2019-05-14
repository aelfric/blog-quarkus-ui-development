package org.kabir.quarkus.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/servlet/*"})
public class SampleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        if (path.equals("/make-external-call")) {
            // Fake making an external call without involving the UI
            // e.g. OAuth Authentication Flow will have a few of these, resulting in 
            // receiving the token eventually
            resp.sendRedirect("/servlet/callback");
        } else if (path.equals("/callback")) {
            // Redirect back to a path controlled by the Angular client
            String redirectPath = "/clientCallback";
            resp.sendRedirect(redirectPath);
        } else {
            resp.sendError(404);
        }
    }
}