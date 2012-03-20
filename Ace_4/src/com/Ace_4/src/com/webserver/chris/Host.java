package com.webserver.chris;

/*		Revision history 
 * 	
 * Had to cast the the sessionConfig to IoSession
 * 	
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.filter.ssl.*;

@SuppressWarnings({ "unused"})
public class Host {
	public static final int PORT = 22235;

	public static void main(String[] args) {
		
		Protocol_handle protocol = new Protocol_handle();
		IoAcceptor IOA = new NioSocketAcceptor(); // creates port on a thread
		IOA.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8")))); // create How the message is
												// decoded

		IOA.setHandler(protocol); // how the messages are handled

		IOA.getSessionConfig().setReadBufferSize(2048); // input maximum size
		IOA.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10); // timeout
		try {
			IOA.bind(new InetSocketAddress(PORT));
			System.out.println("Listening on port:" + PORT);
			System.out.println("Enter \"127.0.0.1:22235\" into your web browser");
		} catch (IOException e) {
			//
			protocol.EXCEPTION((IoSession) IOA.getSessionConfig(), e);
		} // bind to port
			
	}

}
