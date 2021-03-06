package etl.cmd.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import bdap.util.HdfsUtil;

public class TestUnpackCmd extends TestETLCmd {
	private static final long serialVersionUID = 1L;
	public static final Logger logger = LogManager.getLogger(TestUnpackCmd.class);
	public static final String testCmdClass="etl.cmd.UnpackCmd";
	public static final String testSeqCmdClass="etl.cmd.testcmd.SampleSequenceFileCmd";

	public String getResourceSubFolder() {
		return "unpack/";
	}

	@Test
	public void testUnpack() throws Exception {
		String remoteCsvInputFolder = "/etltest/unpack/input/";
		String remoteCsvOutputFolder = "/etltest/unpack/output/";
		String unpackProp = "unpack.properties";
		String[] csvFiles = new String[]{"20160409_BB1.zip", "20160410_AA1.tar.gz", "test.csv"};

		getFs().delete(new Path("/etltest/unpack/output/20160409_BB1"), true);
		getFs().delete(new Path("/etltest/unpack/output/20160410_AA1"), true);
		
		List<String> output = super.mapTest(remoteCsvInputFolder, remoteCsvOutputFolder, unpackProp, csvFiles, testCmdClass, true);
		logger.info("Output is:"+output);
		
		List<String> fl = HdfsUtil.listDfsFile(getFs(), "/etltest/unpack/output/20160409_BB1/01");
		//assert
		logger.info(fl);
		assertTrue(fl.contains("A20160409.0000-20160409.0100_E_000000000_262216704_F4D9FB75EA47_13.csv"));
		
		fl = HdfsUtil.listDfsFile(getFs(), "/etltest/unpack/output/20160409_BB1");
		logger.info(fl);
		assertTrue(fl.contains("test.txt"));
		assertTrue(!fl.contains("test.dat"));
		
		fl = HdfsUtil.listDfsFile(getFs(), "/etltest/unpack/output/20160410_AA1/02");
		logger.info(fl);
		assertTrue(fl.contains("A20160410.0000-20160410.0100_E_000000000_262216704_F4D9FB75EA47_13.csv"));
		
		fl = HdfsUtil.listDfsFile(getFs(), "/etltest/unpack/output/20160410_AA1");
		logger.info(fl);
		assertTrue(fl.contains("test2.txt"));
		assertTrue(!fl.contains("test2.dat"));
	}

	@Test
	public void testUnpackSeq() throws Exception {
		String remoteCsvInputFolder = "/etltest/unpack/input/";
		String remoteCsvOutputFolder = "/etltest/unpack/output/";
		String unpackProp = "unpack_seq.properties";
		String[] csvFiles = new String[]{"20160409_BB1.zip", "20160410_AA1.tar.gz", "test.csv"};

		getFs().delete(new Path("/etltest/unpack/output"), true);
		
		List<String> output = super.mapTest(remoteCsvInputFolder, remoteCsvOutputFolder, unpackProp, csvFiles, testCmdClass, true);
		logger.info("Output is:"+output);

		getFs().delete(new Path("/etltest/unpack/seqoutput"), true);

		remoteCsvInputFolder = "/etltest/unpack/output/";
		remoteCsvOutputFolder = "/etltest/unpack/seqoutput/";
		getConf().set("file.pattern", ".*seq");
		output = super.mapTest(remoteCsvInputFolder, remoteCsvOutputFolder, unpackProp, new String[0], testSeqCmdClass, SequenceFileInputFormat.class, "/etltest/unpack/output/*.seq", true);
		logger.info("Output is:"+output);
		assertTrue(output.contains("5,1,0,0,0,0,0,0,0.13,3.00,237.00,1800.00,11.77,459.00,39.00,"));
	}
	
}
