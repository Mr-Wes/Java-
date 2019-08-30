package controller;

public class MessageHandle {

	private static class Holder {
		private static final MessageHandle INSTANCE = new MessageHandle();
	}
	private MessageHandle() {
		
	}
	public static final MessageHandle getInstance() {
		return Holder.INSTANCE;
	}
	
	public void handle(String str) {
		
	}
}
