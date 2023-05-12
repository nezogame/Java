package edu.dnu.fpm.pz;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class VacancyController extends HttpServlet {
    private VacancyDAO vacancyDAO;
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
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

        vacancyDAO = new VacancyDAO();
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Method doHead");
        resp.sendError(HttpServletResponse.SC_OK);
    }

    // Get models
    // GET/JavaViewer/models/
    // GET/JavaViewer/models/id
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<Vacancy> vacancies;
        try {
            vacancies = vacancyDAO.read();
        } catch (MyException e) {
            response.setStatus(500);
            out.print(gson.toJson("Failed to read employees table"));
            return;
        }
        String modelId = request.getParameter("id");
        if (modelId == null || modelId.isEmpty()) {
            sendAsJson(response,vacancies);
            return;
        }
        else{
            Vacancy vacancy = vacancies.stream()
                    .filter(v -> v.getId() == Integer.parseInt(modelId))
                    .findFirst()
                    .orElse(null);
            if(vacancy==null){
                response.setStatus(404);
                out.print("Vacancy with required id can't be found");
            }else{
                sendAsJson(response, vacancy);
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
            Vacancy vacancy = null;
            List<Vacancy> vacancies = null;

            if (jsonFromRequest.contains("[") && jsonFromRequest.contains("]")) {
                vacancies = gson.fromJson(jsonFromRequest, new TypeToken<List<Vacancy>>() {

                }.getType());
            } else {
                vacancy = gson.fromJson(jsonFromRequest, Vacancy.class);
            }
            if (vacancies != null) {
                try {
                    vacancyDAO.create(vacancies);
                    sendAsJson(response, vacancies);
                } catch (IllegalStateException e) {
                    response.setStatus(400);
                    out.println("Failed to create vacancy(es)");
                } catch (MyException e) {
                    response.setStatus(400);
                    out.println(e.toLog());
                }
            } else if (vacancy != null) {
                try {
                    vacancyDAO.create(vacancy);
                    sendAsJson(response, vacancy);
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
            Vacancy vacancy = null;
            List<Vacancy> vacansies = null;

            if (jsonFromRequest.contains("[") && jsonFromRequest.contains("]")) {
                vacansies = gson.fromJson(jsonFromRequest, new TypeToken<List<Vacancy>>() {

                }.getType());
            } else {
                vacancy = gson.fromJson(jsonFromRequest, Vacancy.class);
            }
            if (vacansies != null) {
                try {
                    vacancyDAO.update(vacansies);
                    sendAsJson(response, vacansies);
                } catch (IllegalStateException e) {
                    response.setStatus(400);
                    out.println("Failed to update vacancy(es)");
                } catch (MyException e) {
                    response.setStatus(500);
                    out.println(e.toLog());
                }
            } else if (vacancy != null) {
                try {
                    vacancyDAO.update(vacancy);
                    sendAsJson(response, vacancy);
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
            vacancyDAO.delete(Integer.parseInt(modelId));
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
