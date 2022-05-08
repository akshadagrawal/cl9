import URLReaderApp.*;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.*;

public class StartServer {
	public static void main(String args[]) {
		try {
//		init orb
		ORB orb = ORB.init(args,null);
//		reference to POAHelper
		POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
//		activate poamanager
		rootpoa.the_POAManager().activate();
		
//		make servant
		URLReaderObj urlreadobj = new URLReaderObj();
//		register with orb
		urlreadobj.setORB(orb);
		
//		Get object reference from servant
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(urlreadobj);
//		get href ie helper reference
		URLReader href = URLReaderHelper.narrow(ref);
		
//		
		org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
//		
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objref);
		
		NameComponent path[] = ncRef.to_name("ABC");
		ncRef.rebind(path, href);
		
		while(true)
			orb.run();
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
