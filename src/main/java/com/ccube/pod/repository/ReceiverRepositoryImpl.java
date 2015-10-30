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
		receiversList.add(new Receiver(IdGenerator.getRid(), "Lakshman", "09036102111"));
		receiversList.add(new Receiver(IdGenerator.getRid(), "Chakra", "08951586661"));
		receiversList.add(new Receiver(IdGenerator.getRid(), "Pradeep", "09945529337"));
		receiversList.add(new Receiver(IdGenerator.getRid(), "Sujay", "09945678529"));
		receiversList.add(new Receiver(IdGenerator.getRid(), "Biju", "09880892458"));
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

}
