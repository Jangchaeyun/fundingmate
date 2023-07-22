package com.fund.fundingmate.domain.payment.service;

import com.fund.fundingmate.domain.payment.dto.InvestPeopleDTO;
import com.fund.fundingmate.domain.payment.entity.InvestPeople;
import com.fund.fundingmate.domain.payment.repository.InvestPeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestPeopleService {
    private final InvestPeopleRepository investPeopleRepository;

    @Autowired
    public InvestPeopleService(InvestPeopleRepository investPeopleRepository) {
        this.investPeopleRepository = investPeopleRepository;
    }

    public  void createInvestPeople(InvestPeopleDTO investPeopleDTO) {
        InvestPeople investPeople = new InvestPeople();
        investPeople.setUser(investPeopleDTO.getUser());
        investPeople.setId(investPeopleDTO.getId());
        investPeople.setName(investPeopleDTO.getName());
        investPeople.setSecuritynumber1(investPeopleDTO.getSecuritynumber1());
        investPeople.setSecuritynumber2(investPeopleDTO.getSecuritynumber2());
        investPeople.setCalltype(investPeopleDTO.getCalltype());
        investPeople.setCallnumber(investPeopleDTO.getCallnumber());

        investPeopleRepository.save(investPeople);
    }
}
