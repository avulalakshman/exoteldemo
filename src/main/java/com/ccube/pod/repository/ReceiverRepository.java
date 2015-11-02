package com.ccube.pod.repository;

import java.util.List;

import com.ccube.pod.domain.Receiver;

public interface ReceiverRepository{
		
			public List<Receiver> findAll();
			public Receiver getReceiver(long id);
			Receiver addReceiver(Receiver receiver);
			public void deleteReceiver(long rid);
			
}
