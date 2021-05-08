package run.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.internal.DefaultShellCallback;
import run.override.service.ConfigurationParserOverride;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Generator {
	
	public void generator() throws Exception{

		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
//		File configFile = new File("transaction-generatorConfig.xml"); 
//		File configFile = new File("ads-generatorConfig.xml"); 
//		File configFile = new File("ods-app-generatorConfig.xml"); 
//	    File configFile = new File("elec-visa-generatorConfig.xml"); 
//	    File configFile = new File("crowd_sourcing-generatorConfig.xml"); 
//		File configFile = new File("disc_user_center.xml"); 
//		File configFile = new File("disc_user_center-generatorConfig.xml"); 
//		File configFile = new File("ods_app_api-generatorConfig.xml"); 
//		File configFile = new File("临时乱搞.xml"); 
//		File configFile = new File("stat-show-generatorConfig.xml"); 
//		File configFile = new File("pushinfo-generatorConfig.xml"); 
//		File configFile = new File("elec-arb-auth-generatorConfig.xml");
		/*File configFile = new File("elec-sign-demo-generatorConfig.xml");*/
		File configFile = new File("bcc-generatorConfig.xml");

		
		ConfigurationParserOverride cp = new ConfigurationParserOverride(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		
		myBatisGenerator.generate(null);

	} 
	public static void main(String[] args) throws Exception {
		try {
			Generator generator = new Generator();
			generator.generator();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
