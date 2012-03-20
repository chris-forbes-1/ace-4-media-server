package com.webserver.chris;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.write.WriteRequestQueue;

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

	@SuppressWarnings("deprecation")
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
		session.write("<b>Test Complete</b>");
		System.out.println(strb.toString());
		
		
//		DataOutputStream DOS = new D 
		session.close();
//		session.write("index.html"); // write the output to the session~ May
										// change this to console display server
										// side.

	}

}
