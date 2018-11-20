package com.bamboo.model.contrat.dependent;

import com.bamboo.model.entity.dependent.Measurer;

import java.util.List;
import java.util.Map;

public interface MeasurerInterface  {

    public Measurer save(Measurer measurer) throws Exception;

    public Measurer findById(int id) throws Exception;

    public List<Measurer> find() throws Exception;

    public boolean update(Measurer measurer) throws Exception;

    public boolean delete(Measurer measurer) throws Exception;

    public List<Measurer> findBySap(int  sapId) throws  Exception;

    public List<Measurer> findByStatus(int  statusId) throws  Exception;

    public Map<String, Object> findMeasurerPerService()throws  Exception;

    public Map<String, Object> findMeasurerPerStatus()throws  Exception;

}