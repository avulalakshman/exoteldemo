package com.ccube.pod.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.ccube.pod.domain.Receiver;

@Repository
public class ReceiverRepositoryImpl implements ReceiverRepository {

	private List<Receiver> receiversList = null;

	public ReceiverRepositoryImpl() {
		receiversList = new ArrayList<>();
		receiversList.add(new Receiver(IdGenerator.getRid(), "Chakra", "+918951586661"));
		receiversList.add(new Receiver(IdGenerator.getRid(), "Sujay", "+919945678529"));
		receiversList.add(new Receiver(IdGenerator.getRid(), "Biju", "+919880892458"));
	}
	@Override
	public Receiver addReceiver(Receiver receiver) {
		if (receiver != null) {
			receiver.setRid(IdGenerator.getRid());
		}
		boolean isAdded = receiversList.add(receiver);
		if (isAdded) {
			return receiver;
		}else{
			throw new IllegalArgumentException("Unable to add the receiver :");
		}
	}

	@Override
	public List<Receiver> findAll() {
		return receiversList;
	}

	@Override
	public Receiver getReceiver(long id) {
		for (Receiver receiver : receiversList) {
			if (receiver.getRid() == id)
				return receiver;
		}
		return null;
	}
	@Override
	public void deleteReceiver(long rid) {
		receiversList.remove(getIndex(rid));
	}
	
	private int getIndex(long rid){
		int i=-1;
		int count=0;
		for (Receiver receiver : receiversList) {
			if (receiver.getRid() == rid){
				i=count;
				return i;
			}
			count++;
		}
		return i;
	}

}
