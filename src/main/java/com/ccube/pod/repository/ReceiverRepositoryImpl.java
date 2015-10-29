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
		receiversList.add(new Receiver(9001, "Lakshman", "09036102111"));
		receiversList.add(new Receiver(9002, "Chakra", "08951586661"));
		receiversList.add(new Receiver(9003, "Pradeep", "09945529337"));
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
