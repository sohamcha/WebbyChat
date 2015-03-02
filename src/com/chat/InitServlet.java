package com.chat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userName = request.getParameter("name");
		if(ChatParams.getUsers().contains(userName)){
		
		if(userName.equals(request.getSession().getAttribute("id"))){
			request.setAttribute("chatCount", ChatParams.getChatCounter());
			request.getRequestDispatcher("chatHome.jsp").forward(request, response);
		}
		else{
			request.setAttribute("error", "ERR_ID_USED");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		}
		
		else {
			request.getSession().setAttribute("id", userName);
			ChatParams.setUsers(userName);
			request.setAttribute("chatCount", ChatParams.getChatCounter());
			request.getRequestDispatcher("chatHome.jsp").forward(request, response);
		}

		
		
		
		
		
		
	}

}
