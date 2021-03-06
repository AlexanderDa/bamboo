/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.api.Rest;

import com.bamboo.api.dto.AssignedDto;
import com.bamboo.api.method.AssignedDtoMethod;
import com.bamboo.api.method.SimpleAssignedDtoMethod;
import com.bamboo.model.entity.Audit;
import com.bamboo.model.method.AuditImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author alexander
 */
@WebServlet(
        name = "AssignedRest",
        urlPatterns = {
                "/api/assigned",
                "/api/assigned/new",
                "/api/assigned/transfer",
                "/api/assigned/beneficiary/*",
                "/api/assigned/beneficiary/unbilled/*",
                "/api/assigned/beneficiary/with/uptakes/*"
        }
)
public class AssignedRest extends HttpServlet {

    private Gson gson = new Gson();
    private final AssignedDtoMethod assignedMtd = new AssignedDtoMethod();
    private final AuditImpl audit = new AuditImpl("Comunidad");


    public AssignedRest() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson = "";
        try {

            switch (request.getServletPath()) {
                case "/api/assigned":
                    if (request.getPathInfo() == null) {
                        responseJson = gson.toJson(assignedMtd.find());
                    } else {
                        response.sendError(404);
                    }
                    break;
                case "/api/assigned/beneficiary":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(assignedMtd.findByBeneficiary(Integer.parseInt(request.getPathInfo().substring(1))));
                    } else {
                        response.sendError(404);
                    }
                    break;
                case "/api/assigned/beneficiary/unbilled":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(assignedMtd.findByBeneficiaryUnbilledUptakes(Integer.parseInt(request.getPathInfo().substring(1))));
                    } else {
                        response.sendError(404);
                    }
                    break;
                case "/api/assigned/beneficiary/with/uptakes":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(assignedMtd.findByBeneficiaryWithUptakes(Integer.parseInt(request.getPathInfo().substring(1))));
                    } else {
                        response.sendError(404);
                    }
                    break;
            }

        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
            responseJson = gson.toJson(map);
        }
        response.getWriter().write(responseJson);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;
        try {
            switch (request.getServletPath()) {
                case "/api/assigned/new":
                    AssignedDto assignedDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), AssignedDto.class);
                    assignedDto = assignedMtd.save(assignedDto);
                    if (assignedDto != null) {
                        map.put("saved", true);
                        map.put("assigned", assignedDto);
                        audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "Beneficiario: " + assignedDto.getBeneficiary().getFullName()));
                    } else {
                        map.put("saved", false);
                    }
                    break;
                case "/api/assigned/transfer":
                    assignedDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), AssignedDto.class);
                    assignedDto = assignedMtd.transfer(assignedDto);
                    if (assignedDto != null) {

                        map.put("saved", true);
                        map.put("assigned", assignedDto);
                        audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "Beneficiario: " + assignedDto.getBeneficiary().getFullName()));
                    } else {
                        map.put("saved", false);
                    }
                    break;

            }
        } catch (Exception ex) {
            response.setStatus(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        if (request.getPathInfo() == null && request.getServletPath().equals("/api/assigned")) {
            try {
                AssignedDto assignedDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), AssignedDto.class);
                assignedDto = assignedMtd.update(assignedDto);
                if (assignedDto!=null) {
                    map.put("updated", true);
                    audit.update(new Audit(Integer.parseInt(request.getHeader("user")), "" ));
                } else {
                    map.put("updated", false);
                }


                map.put("body", assignedDto);
            } catch (Exception ex) {
                response.setStatus(400);
                map.put("error", "err: " + ex.getMessage());
            }
        } else {
            response.sendError(404);


        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
            try {
                /*if (assignedMtd.delete(Integer.parseInt(request.getPathInfo().substring(1)))) {
                    map.put("deleted", true);
                    audit.delete(new Audit(Integer.parseInt(request.getHeader("user")), "id: " + request.getPathInfo().substring(1)));
                } else {
                    map.put("deleted", false);
                }*/
            } catch (Exception ex) {
                response.setStatus(400);
                map.put("error", ex.getMessage());
            }
        } else {
            response.sendError(404);
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

}
