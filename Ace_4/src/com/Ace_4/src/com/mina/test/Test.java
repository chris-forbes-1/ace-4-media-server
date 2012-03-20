package com.mina.test;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

@SuppressWarnings("unused")
public class Test {

	private static final int PORT = 23452; // port number
	public static void main(String[] args) throws IOException{
		IoAcceptor IOA = new NioSocketAcceptor(); // creates port on a thread
		IOA.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")))); //create How the message is decoded 
		
		IOA.setHandler(new TimeServerHandler()); // how the messages are handled 
		
		IOA.getSessionConfig().setReadBufferSize(2048); // input maximum size
		IOA.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10); // timeout 
		IOA.bind(new InetSocketAddress(PORT)); // bind to port 
		
	}
	
}


