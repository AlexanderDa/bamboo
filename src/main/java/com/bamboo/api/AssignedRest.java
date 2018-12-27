package com.bamboo.api;

import com.bamboo.model.entity.Assigned;
import com.bamboo.model.method.AssignedImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Integer.*;

@WebServlet(name = "AssignedRest", urlPatterns = {"/api/assigned"})
public class AssignedRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final AssignedImpl assignedImpl = new AssignedImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {

            responseJson = gson.toJson(assignedImpl.find());

            if (request.getParameter("beneficiaryId") != null) {
                responseJson = gson.toJson(assignedImpl.findByBeneficiary(
                        Integer.parseInt(request.getParameter("beneficiaryId"))
                ));

                if (request.getParameter("measurerId") != null) {
                    responseJson = gson.toJson(assignedImpl.findById(
                            Integer.parseInt(request.getParameter("beneficiaryId")),
                            Integer.parseInt(request.getParameter("measurerId"))
                    ));

                }

            }

        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
        }
        response.getWriter().write(responseJson);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = null;

        Assigned assigned = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Assigned.class);
        try {
            responseJson = gson.toJson(assignedImpl.save(assigned));

        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
        }
        
        response.getWriter().write(responseJson);
    }
}
