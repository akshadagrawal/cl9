import URLReaderApp.*;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class URLReaderObj extends URLReaderApp.URLReaderPOA {
	private org.omg.CORBA.ORB orb;
	
	public void setORB(org.omg.CORBA.ORB orb2) {
		orb=orb2;
	}
	@Override
	public int urlbytes(String url) {
		URL url2;
		int length=0;
		try {
		  url2 = new URL(url);
		  InputStream is = null;
		  is = url2.openStream();
		  length = is.available();
		  if (is != null)
			  is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return length;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		orb.shutdown(false);
	}

}
