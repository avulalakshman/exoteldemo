package com.ccube.pod.repository;

public final class IdGenerator {

		private static long userId=1;
		private static long receiverId=9000;
		private IdGenerator(){
			
		}
		public static long getUid(){
			return userId++;
		}
		
		public static long getRid(){
			return receiverId++;
		}
	
}
