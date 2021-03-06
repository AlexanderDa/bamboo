package com.bamboo.model.contrat;

import com.bamboo.model.entity.Measurer;

import java.util.List;
import java.util.Map;

public interface MeasurerInterface {

    public Measurer save(Measurer measurer) throws Exception;

    public Measurer findById(int id) throws Exception;

    public Measurer findByNumber(String code) throws Exception;

    public List<Measurer> find() throws Exception;

    public boolean update(Measurer measurer) throws Exception;

    public boolean delete(Measurer measurer) throws Exception;

    public List<Measurer> findBySap(int sapId) throws Exception;

    public List<Measurer> findByStatus(int statusId) throws Exception;

    public List<Measurer> findByBeneficiary(int beneficiaryId) throws Exception;

    public List<Map<String, Object>> findMeasurerPerService() throws Exception;

    public List<Map<String, Object>> findMeasurerPerStatus() throws Exception;

}
