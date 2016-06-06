package pde.preload.test;

import java.io.File;
import java.security.PrivilegedExceptionAction;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.log4j.Logger;
import org.junit.Test;

import etl.cmd.transform.FileMerger;
import etl.cmd.transform.Preload;
import etl.cmd.transform.PreloadConf;

public class TestPreProcess {
	public static final Logger logger = Logger.getLogger(TestPreProcess.class);
	String pdeCsvProp = "preload.pde.csv.properties";
	String pdeFixProp = "preload.pde.fix.properties";
	
	@Test
	public void testPdeCSV1(){
		String infile = "src\\test\\resources\\input.pde1.csv";
		String outfile = "testoutput\\output.pde1.csv";
		Preload epl = new Preload();
		epl.processFile(pdeCsvProp, new String[]{infile}, outfile, PreloadConf.NO_EVENT_OUTPUT);
	}
	
	@Test
	public void testPdeFix1(){
		String infile = "src\\test\\resources\\input.pde1.fix";
		String outfile = "testoutput\\output.pde1.fix";
		Preload epl = new Preload();
		epl.processFile(pdeFixProp, new String[]{infile}, outfile, PreloadConf.NO_EVENT_OUTPUT);
	}
	
	@Test
	public void testMergeCSVFix1(){
		String pdeCsvFixProp = "pde.csv.fix.merger.properties";
		
		String inCSVFile="src\\test\\resources\\PJ24002A_BBG2.csv";
		String inFixFile="src\\test\\resources\\PJ24002A_BBG2.fix";
		String leftFile="testoutput\\PJ24002A_BBG2.out.csv";
		String rightFile="testoutput\\PJ24002A_BBG2.out.fix";
		String outputFile="testoutput\\PJ24002A_BBG2.out.merge";
		Preload epl = new Preload();
		epl.processFile(pdeCsvProp, new String[]{inCSVFile}, leftFile, PreloadConf.NO_EVENT_OUTPUT);
		epl.processFile(pdeFixProp, new String[]{inFixFile}, rightFile, PreloadConf.NO_EVENT_OUTPUT);
		FileMerger fm = new FileMerger();
		fm.processFile(pdeCsvFixProp, leftFile, rightFile, outputFile);
	}
	
	@Test
	public void testAppendPrependCSVMinLookup(){
		String pdeMINProp = "preload.minlookup.properties";
		String inCSVFile="data\\batchMINlookup.csv";
		String outputCSVFile="data\\batchMINlookupoutput.csv";
		Preload epl = new Preload();
		epl.processFile(pdeMINProp, new String[]{inCSVFile}, outputCSVFile, PreloadConf.NO_EVENT_OUTPUT);
	}
	
	@Test
	public void setupLabETLCfg() {
		setupETLCfg("hdfs://192.85.247.104:19000", "C:\\mydoc\\myprojects\\log.analysis\\pde.analysis\\src\\main\\resources");
	}
	
	public void realSetupEtlCfg(String defaultFs, String localCfgDir) throws Exception{
		Configuration conf = new Configuration();
    	conf.set("fs.defaultFS", defaultFs);
    	FileSystem fs = FileSystem.get(conf);
    	
    	String remoteLibFolder = "/user/dbadmin/pde";
    	Path remoteLibPath = new Path(remoteLibFolder);
    	if (fs.exists(remoteLibPath)){
    		fs.delete(remoteLibPath, true);
    	}
    	//
    	String workflow = localCfgDir + File.separator + "workflow.xml";
		String remoteWorkflow = remoteLibFolder + File.separator + "workflow.xml";
		fs.copyFromLocalFile(new Path(workflow), new Path(remoteWorkflow));
		//
		String jobProperties = localCfgDir + File.separator + "job.properties";
		String remoteJobProperties = remoteLibFolder + File.separator + "job.properties";
		fs.copyFromLocalFile(new Path(jobProperties), new Path(remoteJobProperties));
		//
		String localTargetFolder = "C:\\mydoc\\myprojects\\log.analysis\\pde.analysis\\target\\";
		String libName = "pde-0.1.0-jar-with-dependencies.jar";
		fs.copyFromLocalFile(new Path(localTargetFolder + libName), new Path(remoteLibFolder + "/lib/" +libName));

		//copy etlcfg
		String remoteCfgFolder = "/pde/etlcfg/";
		Path remoteCfgPath = new Path(remoteCfgFolder);
		if (fs.exists(remoteCfgPath)){
			fs.delete(new Path(remoteCfgFolder), true);
		}
		String staticCfg1="pde.genseedinput.properties";
		String staticCfg2="pde.preload.shell.properties";
		fs.copyFromLocalFile(new Path(localCfgDir + File.separator + staticCfg1), new Path(remoteCfgFolder+staticCfg1));
		fs.copyFromLocalFile(new Path(localCfgDir + File.separator + staticCfg2), new Path(remoteCfgFolder+staticCfg2));
	}
	
	public void setupETLCfg(final String defaultFs, final String localCfgDir) {
		try {
			if (defaultFs.contains("127.0.0.1")){
				realSetupEtlCfg(defaultFs, localCfgDir);
			}else{
				UserGroupInformation ugi = UserGroupInformation.createProxyUser("dbadmin", UserGroupInformation.getLoginUser());
			    ugi.doAs(new PrivilegedExceptionAction<Void>() {
			      public Void run() throws Exception {
			    	realSetupEtlCfg(defaultFs, localCfgDir);
					return null;
			      }
			    });
			}
		}catch(Exception e){
			logger.error("", e);
		}
	}
}