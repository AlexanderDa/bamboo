/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.api;

import com.bamboo.model.entity.Audit;
import com.bamboo.model.entity.Village;
import com.bamboo.model.method.AuditImpl;
import com.bamboo.model.method.VillageImpl;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author alexander
 */
@WebServlet(name = "VillageRest", urlPatterns = {"/api/village"})
public class VillageRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final VillageImpl villageImpl = new VillageImpl();
    private Map<String, Object> map = new HashMap<>();
    private final AuditImpl audit = new AuditImpl(Village.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            responseJson = gson.toJson(villageImpl.find());
            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(villageImpl.findById(Integer.parseInt(request.getParameter("id"))));
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
        String responseJson;

        Village village = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Village.class);
        try {
            if (villageImpl.save(village)) {
                map.put("saved", true);
                audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "name: " + village.getName()));
            } else {
                map.put("saved", false);
            }

        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson;

        Village village = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Village.class);
        try {
            if (villageImpl.update(village)) {
                map.put("updated", true);
                audit.update(new Audit(Integer.parseInt(request.getHeader("user")), "id: " + village.getId()));
            } else {
                map.put("updated", false);
            }

        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson;

        try {
            Village village = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Village.class);
            if (villageImpl.delete(village)) {
                map.put("deleted", true);
                audit.delete(new Audit(Integer.parseInt(request.getHeader("user")), "id: " + village.getId()));
            } else {
                map.put("deleted", false);
            }

        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

}
