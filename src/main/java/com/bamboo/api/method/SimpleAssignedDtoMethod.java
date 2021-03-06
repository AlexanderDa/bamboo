package com.bamboo.api.method;

import com.bamboo.api.dto.MeasurerDto;
import com.bamboo.api.dto.SimpleAssignedDto;
import com.bamboo.model.entity.Assigned;
import com.bamboo.model.method.AssignedImpl;
import com.bamboo.model.method.MeasurerImpl;

import java.util.ArrayList;
import java.util.List;

public class SimpleAssignedDtoMethod {

    public SimpleAssignedDto save(SimpleAssignedDto simpleAssigned, int beneficiaryId) throws Exception {
        SimpleAssignedDto newSimpleAssigned = null;

        try {
            MeasurerDto newMeasurer = new MeasurerDtoMethod().save(
                    simpleAssigned.getMeasurer()
            );
            simpleAssigned.setMeasurer(newMeasurer);

            if (newMeasurer != null) {
                newSimpleAssigned = getSimpleAssignedDto(
                        new AssignedImpl().save(
                                getAssigned(simpleAssigned, beneficiaryId)
                        )
                );

            }
        } catch (Exception e) {
            throw e;
        }
        return newSimpleAssigned;
    }


    public SimpleAssignedDto transfer(SimpleAssignedDto simpleAssigned, int beneficiaryId) throws Exception {
        SimpleAssignedDto newSimpleAssigned = null;
        AssignedImpl assignedImpl = new AssignedImpl();
        try {
            Assigned assigned = getAssigned(simpleAssigned, beneficiaryId);
            if (assignedImpl.disableByMeasurer(assigned.getMeasurer())) {
                newSimpleAssigned = getSimpleAssignedDto(
                        assignedImpl.save(assigned)
                );
            }

        } catch (Exception e) {
            throw e;
        }
        return newSimpleAssigned;
    }

    public boolean update(SimpleAssignedDto simpleAssigned, int beneficiaryId) throws Exception {
        boolean affected = false;
        AssignedImpl assignedImpl = new AssignedImpl();
        try {
            Assigned assigned = getAssigned(simpleAssigned, beneficiaryId);
            if (simpleAssigned.getMeasurer().getStatus().getId()==4){
                assigned.setStatus("disable");
            }else{
                assigned.setStatus("enable");
            }

            if (new MeasurerDtoMethod().update(simpleAssigned.getMeasurer()))
                if (assignedImpl.update(assigned)) {
                    affected = true;
                }


        } catch (Exception e) {
            throw e;
        }
        return affected;
    }


    public List<SimpleAssignedDto> find() throws Exception {
        List<SimpleAssignedDto> list = new ArrayList<>();
        AssignedImpl assignedImpl = new AssignedImpl();
        try {
            for (Assigned assigned : assignedImpl.find()) {
                SimpleAssignedDto assignedDto = getSimpleAssignedDto(assigned);
                assignedDto.setMeasurer(new MeasurerDtoMethod().findById(assigned.getBeneficiary()));
                list.add(assignedDto);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }


    public List<SimpleAssignedDto> findByBeneficiary(int beneficiaryId) throws Exception {
        List<SimpleAssignedDto> list = new ArrayList<>();
        AssignedImpl assignedImpl = new AssignedImpl();
        try {
            for (Assigned assigned : assignedImpl.findByBeneficiary(beneficiaryId)) {
                SimpleAssignedDto assignedDto = getSimpleAssignedDto(assigned);
                assignedDto.setMeasurer(new MeasurerDtoMethod().findById(assigned.getMeasurer()));
                list.add(assignedDto);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }


    public List<SimpleAssignedDto> findByBeneficiaryWithUptakes(int beneficiaryId) throws Exception {
        List<SimpleAssignedDto> list = new ArrayList<>();
        AssignedImpl assignedImpl = new AssignedImpl();
        try {
            for (Assigned assigned : assignedImpl.findByBeneficiary(beneficiaryId)) {
                SimpleAssignedDto assignedDto = getSimpleAssignedDto(assigned);
                assignedDto.setMeasurer(new MeasurerDtoMethod().findByIdWithUptakes(assigned.getMeasurer()));
                list.add(assignedDto);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public List<SimpleAssignedDto> findByBeneficiaryUnbilledUptakes(int beneficiaryId) throws Exception {
        List<SimpleAssignedDto> list = new ArrayList<>();
        AssignedImpl assignedImpl = new AssignedImpl();
        try {
            for (Assigned assigned : assignedImpl.findByBeneficiary(beneficiaryId)) {
                SimpleAssignedDto assignedDto = getSimpleAssignedDto(assigned);
                assignedDto.setMeasurer(new MeasurerDtoMethod().findByIdUnbilledUptakes(assigned.getMeasurer()));
                list.add(assignedDto);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    private Assigned getAssigned(SimpleAssignedDto simpleAssignedDto, int beneficiaryId) {
        Assigned assigned = new Assigned();
        assigned.setId(simpleAssignedDto.getId());
        assigned.setAssignmentDate(simpleAssignedDto.getAssignmentDate());
        assigned.setStatus(simpleAssignedDto.getStatus());
        assigned.setDebt(simpleAssignedDto.getDebt());
        assigned.setBeneficiary(beneficiaryId);
        assigned.setMeasurer(simpleAssignedDto.getMeasurer().getId());
        return assigned;
    }

    private SimpleAssignedDto getSimpleAssignedDto(Assigned assigned) throws Exception {
        SimpleAssignedDto assignedDto = new SimpleAssignedDto();
        assignedDto.setId(assigned.getId());
        assignedDto.setAssignmentDate(assigned.getAssignmentDate());
        assignedDto.setStatus(assigned.getStatus());
        assignedDto.setDebt(assigned.getDebt());
        return assignedDto;
    }
}