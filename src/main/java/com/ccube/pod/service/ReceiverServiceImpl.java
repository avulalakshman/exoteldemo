package com.ccube.pod.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccube.pod.domain.Receiver;
import com.ccube.pod.repository.ReceiverRepository;
import com.ccube.pod.service.exception.ReceiverException;

@Service
public class ReceiverServiceImpl implements ReceiverService {
	@Autowired
	private ReceiverRepository receiverRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverServiceImpl.class);

	@Override
	public List<Receiver> listAllReceivers() {
		List<Receiver> listReceiver = receiverRepository.findAll();
		if (listReceiver != null && !listReceiver.isEmpty())
			return listReceiver;
		else {
			LOGGER.warn("Receiver list is empty or null");
			throw new ReceiverException(ReceiverException.NO_RECORDS_FOUND,listReceiver.size());
		}

	}

	@Override
	public Receiver getReceiver(long rid) {
		Receiver receiver=receiverRepository.getReceiver(rid);
		if(receiver!=null){
			return receiver;
		}else{
			LOGGER.warn("Receiver no found with the given id :"+rid);
			throw new ReceiverException(ReceiverException.RECORD_NOT_FOUND,rid);
		}
		
	}

	public ReceiverRepository getReceiverRepository() {
		return receiverRepository;
	}

	public void setReceiverRepository(ReceiverRepository receiverRepository) {
		this.receiverRepository = receiverRepository;
	}

}
