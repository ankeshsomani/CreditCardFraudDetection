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

import com.masteklabs.fraudanalytics.request.dto.AddSuspectedTransactionRequest;
import com.masteklabs.fraudanalytics.request.dto.RefreshMasterDataRequest;
import com.masteklabs.fraudanalytics.response.dto.ServiceResponse;
import com.masteklabs.frauddetection.common.CommonUtils;
import com.masteklabs.frauddetection.entity.SuspectedTransactionEntity;
import com.masteklabs.frauddetection.exception.ObjectMappingException;
import com.masteklabs.frauddetection.service.RefreshMasterDataService;
import com.masteklabs.frauddetection.service.SuspectedTransactionService;

@RestController
public class MasterDataController {
static final Logger log = Logger.getLogger(MasterDataController.class.getName());
	
	@Autowired
	@Qualifier("refreshMasterDataService")
	RefreshMasterDataService refreshMasterDataService;
	
	@Autowired
	@Qualifier("suspectedTransactionService")
	SuspectedTransactionService suspectedTransactionService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/refreshMasterData",method = { RequestMethod.POST },consumes="application/json",produces="application/json")
    public ServiceResponse deleteTransaction(@RequestBody  String jsonRequest) {
		ServiceResponse serviceResponse=null;
    	try {
    	RefreshMasterDataRequest refreshRequest=(RefreshMasterDataRequest) CommonUtils.fromJsonUnchecked(jsonRequest, RefreshMasterDataRequest.class);
	       if(refreshRequest.isRefreshData()){
	    	   serviceResponse=refreshMasterDataService.refreshMasterData();
	       }
	       else{
	    	   serviceResponse=new ServiceResponse(true,"No refresh done as it was not reuqested.");
	       }
    	
    	}
    	catch (ObjectMappingException e) {
			serviceResponse=new ServiceResponse(false,"error while converting to object from json");
			e.printStackTrace();
		}
    	return serviceResponse;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/addSuspectedTransaction",method = { RequestMethod.POST },consumes="application/json",produces="application/json")
	public ServiceResponse addSuspectedTransaction(@RequestBody  String jsonRequest) throws ParseException, ObjectMappingException {
		ServiceResponse serviceResponse=null;
		AddSuspectedTransactionRequest addSuspectedTransactionRequest=(AddSuspectedTransactionRequest) CommonUtils.fromJsonUnchecked(jsonRequest, AddSuspectedTransactionRequest.class);
		SuspectedTransactionEntity suspectedTransaction=convertToSuspectedTransaction(addSuspectedTransactionRequest);
		suspectedTransactionService.addSuspectedTransaction(suspectedTransaction);
		serviceResponse=new ServiceResponse(true,"");
		return serviceResponse;
	}

	private SuspectedTransactionEntity convertToSuspectedTransaction(
			AddSuspectedTransactionRequest addSuspectedTransactionRequest) {
		SuspectedTransactionEntity suspectedTransaction=new SuspectedTransactionEntity();
		suspectedTransaction.setAccountId(addSuspectedTransactionRequest.getAccountid());
		suspectedTransaction.setTransactionId(addSuspectedTransactionRequest.getTransactionid());
		suspectedTransaction.setDate(addSuspectedTransactionRequest.getTransactionDate());
		suspectedTransaction.setCardNumber(addSuspectedTransactionRequest.getCardnumber());
		suspectedTransaction.setAmount(addSuspectedTransactionRequest.getAmount());
		suspectedTransaction.setFraudStatus(addSuspectedTransactionRequest.getFraudStatus());
		
		
		return suspectedTransaction;
	}
	
}
