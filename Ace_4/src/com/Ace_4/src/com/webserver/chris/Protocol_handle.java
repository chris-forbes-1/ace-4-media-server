package com.webserver.chris;

import java.io.File;
import pages.html.*;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

@SuppressWarnings("unused")
public class Protocol_handle extends IoHandlerAdapter {
	@SuppressWarnings("deprecation")
	/*
	 * @Desc is passed an exception writes error code and kills connection.
	 */
	public void EXCEPTION(IoSession session, Throwable reason) {
		System.out
				.println("Error code: 1 EXCEPTION handled, connection killed.");
		session.write("Error Code :1. Connection closing");
		session.close();
	}

	@Override
	public void messageReceived(IoSession session, Object Message) {
		String mes = Message.toString(); // received Object type message (any
											// kind of protocol)
		StringBuilder strb = new StringBuilder(mes.length()); // create a new
																// buffer to
																// store the
																// message in.
		for (int i = 0; i < mes.length() - 1; i++) {
			strb.append(mes.charAt(i)); // remove the char at index i and place
										// it in the buffer
		}
		System.out.println(strb.toString());	
		
		while(session.isConnected())
		{
			session.write(strb);
			session.close();
		}
		
		
		session.write("<b> TestComplete</b>"); // write the output to the session~ May
										// change this to console display server
										// side.
	}

}
