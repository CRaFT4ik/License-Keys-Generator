package ru.er_log;

import java.net.URI;

public class Util {
    
    public static void openLink(URI uri) {
        try {
            Object o = Class.forName("java.awt.Desktop").getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
            o.getClass().getMethod("browse", new Class[] { URI.class }).invoke(o, new Object[] { uri });
        } catch (Throwable e) {
            System.out.println("Failed to open link " + uri.toString());
        }
    }
}
