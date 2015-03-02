package com.chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatServer
 */
public class ChatServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String typeOfOper = request.getParameter("type");
		PrintWriter out = response.getWriter();
		if("participants".equals(typeOfOper)){
			out.print(ChatParams.getParticipants());
		}
		
		else if("send".equals(typeOfOper)){
			// Code to add new chat to Map
			String chatMsg = request.getParameter("chatMsg");
			ChatParams.addChat(request.getSession().getAttribute("id")+" : "+chatMsg);
		}
		
		else if("get".equals(typeOfOper)){
			int msgCount;
			try{
			msgCount = Integer.parseInt(request.getParameter("msgCount"));
			}
			catch(NumberFormatException excp){
				msgCount = 0;
			}
			out.print(ChatParams.getChatString(msgCount));
		}
		
		else if("logout".equals(typeOfOper)){
			ChatParams.removeUsers(request.getSession().getAttribute("id").toString());
			request.getSession().invalidate();
		}
	}

}
