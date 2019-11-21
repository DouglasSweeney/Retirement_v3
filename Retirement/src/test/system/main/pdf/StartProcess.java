package test.system.main.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StartProcess {
	private transient final String runCommand;
	
    transient Process process;
		     
	public StartProcess(final String runCommand) {
		this.runCommand = runCommand;
	}
	
	public void start() throws InterruptedException, IllegalMonitorStateException, IOException {
	    final ArrayList<String> list = new ArrayList<>();
	    ProcessBuilder processBuilder = null;
	    final StringTokenizer st = new StringTokenizer(runCommand);
	    while (st.hasMoreTokens()) {
	        list.add(st.nextToken());
	    }
	    
		if (process == null) {
		    	processBuilder = new ProcessBuilder(list);
          
		    	process = processBuilder.start();
		    	process.waitFor();
	    }
		else {
		     System.out.println("Can only start one (1) process!");
		 }
	}
}
