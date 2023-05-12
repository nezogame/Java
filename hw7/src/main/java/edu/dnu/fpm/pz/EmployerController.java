package edu.dnu.fpm.pz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
public class EmployerController extends HttpServlet {
    private EmployerDAO employerDAO;
    private Gson gson = new Gson();

    private String getJsonFromRequest(BufferedReader reader) throws IOException {
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        return buffer.toString();
    }

    //a utility method to send object
    //as JSON response
    private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");

        String res = gson.toJson(obj);

        PrintWriter out = response.getWriter();

        out.print(res);
        out.flush();
    }

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        employerDAO = new EmployerDAO();
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Method doHead");
        resp.sendError(HttpServletResponse.SC_OK);
    }

    // Get models
    // GET/JavaViewer/models/
    // GET/JavaViewer/models/id?=x
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<Employer> employees;
        try {
            employees = employerDAO.read();
        } catch (MyException e) {
            response.setStatus(500);
            out.print(gson.toJson("Failed to read employees table"));
            return;
        }
        String modelId = request.getParameter("id");
        if (modelId == null || modelId.isEmpty()) {
            sendAsJson(response,employees);
            return;
        }
        else{
            try {
                Employer customer = employerDAO.getById(Integer.parseInt(modelId));
                if(customer.getId()==0){
                    sendAsJson(response, null);
                }else{
                    sendAsJson(response, customer);
                }
            } catch (MyException e) {
                //sendStatus
                response.setStatus(404);
                out.print(gson.toJson("Wrong employee id: "+ Integer.parseInt(modelId)));
            }
        }
    }

    // Adds new model in DB
    // POST/JavaViewer/models
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            String jsonFromRequest = getJsonFromRequest(request.getReader());
            Employer employer = null;
            List<Employer> employers = null;

            if (jsonFromRequest.contains("[") && jsonFromRequest.contains("]")) {
                employers = gson.fromJson(jsonFromRequest, new TypeToken<List<Employer>>() {

                }.getType());
            } else {
                employer = gson.fromJson(jsonFromRequest, Employer.class);
            }
            if (employers != null) {
                try {
                    employerDAO.create(employers);
                    sendAsJson(response, employers);
                } catch (IllegalStateException e) {
                    response.setStatus(400);
                    out.println("Failed to create employee(s)");
                } catch (MyException e) {
                    response.setStatus(400);
                    out.println(e.toLog());
                }
            } else if (employer != null) {
                try {
                    employerDAO.create(employer);
                    sendAsJson(response, employer);
                } catch (IllegalStateException e) {
                    response.setStatus(403);
                    out.println(e.toString());
                } catch (MyException e) {
                    response.setStatus(500);
                    out.println(e.toLog());
                }
            } else {
                response.setStatus(400);
            }
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        PrintWriter out = response.getWriter();
        if (pathInfo == null || pathInfo.equals("/")) {
            String jsonFromRequest = getJsonFromRequest(request.getReader());
            Employer employer = null;
            List<Employer> employers = null;

            if (jsonFromRequest.contains("[") && jsonFromRequest.contains("]")) {
                employers = gson.fromJson(jsonFromRequest, new TypeToken<List<Employer>>() {

                }.getType());
            } else {
                employer = gson.fromJson(jsonFromRequest, Employer.class);
            }
            if (employers != null) {
                try {
                    employerDAO.update(employers);
                    sendAsJson(response, employers);
                } catch (IllegalStateException e) {
                    response.setStatus(400);
                    out.println("Failed to update employee(s)");
                } catch (MyException e) {
                    response.setStatus(500);
                    out.println(e.toLog());
                }
        } else if (employer != null) {
                try {
                    employerDAO.update(employer);
                    sendAsJson(response, employer);
                } catch (IllegalStateException e) {
                    response.setStatus(403);
                    out.println(e.toString());
                } catch (MyException e) {
                    response.setStatus(500);
                }
            } else {
                response.setStatus(400);
                out.println("Bad input");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        PrintWriter out = response.getWriter();

        String modelId = request.getParameter("id");

        if (modelId == null || modelId.isEmpty()) {
            response.setStatus(400);
            out.print(gson.toJson("Failed to get id"));
            return;
        }

        try {
            employerDAO.delete(Integer.parseInt(modelId));
        } catch (MyException e) {
            response.setStatus(500);
            out.println(e.toLog());
        }
        catch (IllegalStateException e) {
            response.setStatus(403);
            out.println(e.toString());
        }

    }
}