package com.ccube.pod.service;

import java.util.List;

import com.ccube.pod.domain.Receiver;

public interface ReceiverService {
		
			public List<Receiver> listAllReceivers();
			public Receiver getReceiver(long rid);
			public Receiver addReceiver(Receiver receiver);
			public void deleteReceiver(long rid);
			
}
