package com.aui;

import com.aui.web.TransactionServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class EcoMoneyTransferApplication {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8090);
        tomcat.getConnector();

        Context context = tomcat.addContext("", null);
        Wrapper servletWrapper = Tomcat.addServlet(context,"transactionServlet", new TransactionServlet());
        servletWrapper.setLoadOnStartup(1);
        servletWrapper.addMapping("/*");

        tomcat.start();
    }
}
