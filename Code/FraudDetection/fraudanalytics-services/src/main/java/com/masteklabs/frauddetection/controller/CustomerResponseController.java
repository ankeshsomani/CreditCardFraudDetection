package com.masteklabs.frauddetection.controller;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masteklabs.fraudanalytics.request.dto.ProcessCustomerResponseRequest;
import com.masteklabs.fraudanalytics.request.dto.SaveTransactionRequest;
import com.masteklabs.fraudanalytics.response.dto.ServiceResponse;
import com.masteklabs.frauddetection.common.CommonUtils;
import com.masteklabs.frauddetection.exception.ObjectMappingException;
import com.masteklabs.frauddetection.service.CustomerResponseService;


@RestController
public class CustomerResponseController {
	static final Logger log = Logger.getLogger(CustomerResponseController.class.getName());
	
	@Autowired
	@Qualifier("customerResponseService")
	CustomerResponseService customerResponseService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/saveTransaction" ,method = { RequestMethod.POST },consumes="application/json",produces="application/json")
    public ServiceResponse saveTransaction(@RequestBody  String jsonRequest) {
    	System.out.println("in approveTransaction controller");
       // model.addAttribute("name", name);
    	ServiceResponse serviceResponse=null;
    	SaveTransactionRequest saveTransactionRequest;
		try {
			saveTransactionRequest = (SaveTransactionRequest) CommonUtils.fromJsonUnchecked(jsonRequest, SaveTransactionRequest.class);
		
    	//System.out.println(saveTransactionRequest.getAccountId());System.out.println(saveTransactionRequest.getTransaction());System.out.println(saveTransactionRequest.getScore());
    	serviceResponse=new ServiceResponse(true,null);
    	customerResponseService.saveTransaction(saveTransactionRequest.getAccountId(), saveTransactionRequest.getTransaction(), saveTransactionRequest.getScore());
		} catch (ObjectMappingException e) {
			serviceResponse=new ServiceResponse(false,"error while converting to object from json");
			e.printStackTrace();
		}
        return  serviceResponse;
    }
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/processCustomerResponse",method = { RequestMethod.POST },consumes="application/json",produces="application/json")
    public ServiceResponse processCustomerResponse(@RequestBody  String jsonRequest) throws ParseException {
    	System.out.println("in processCustomerResponse service");
    	ServiceResponse serviceResponse=null;
    	try {
    	ProcessCustomerResponseRequest processCustResponseRequest=(ProcessCustomerResponseRequest) CommonUtils.fromJsonUnchecked(jsonRequest, ProcessCustomerResponseRequest.class);
       
    	customerResponseService.processCustomerResponse(processCustResponseRequest);
    	
    	
    	serviceResponse=new ServiceResponse(true,null);
    	} catch (ObjectMappingException e) {
			serviceResponse=new ServiceResponse(false,"error while converting to object from json");
			e.printStackTrace();
		}
        return serviceResponse;
    }
}
