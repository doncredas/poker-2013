package chatMultiThread;

public abstract class ChatProtocol {
	  protected ChannelsManager manager;
	  public ChatProtocol(ChannelsManager manager) {
	    this.manager = manager;
	    manager.setChatProtocol(this);
	  }
	  public abstract void startMessage(ThreadChannel ch);
	  public abstract void parserMessage(ThreadChannel channel, String msg);
}