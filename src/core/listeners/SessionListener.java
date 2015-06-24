package core.listeners; /**
 * Created by nika on 5/26/15.
 */

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import core.user.WebData;

@WebListener()
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //   httpSessionEvent.getSession().setAttribute("logged in", false);
    	WebData webData = new WebData(null);
    	System.out.println("a");
    	httpSessionEvent.getSession().setAttribute("webData", webData);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}