

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yttx.yttxps.mapper.SysOperMapper;
import com.yttx.yttxps.model.SysOper;









public class MyBatisSpringDemo {

	
	private static ApplicationContext ctx;  
	static
	    {  
	        ctx = new ClassPathXmlApplicationContext("beans.xml");  
	    } 
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SysOperMapper sysOperMapper =  (SysOperMapper)ctx.getBean("sysOperMapper");  
		
		
		
		
		SysOper sysOper = new SysOper();
		
		/*sysOper.setAdminType(1);
		sysOper.setDepNo(0);
		sysOper.setPwdStat(1);
		sysOper.setStat(1);*/
		sysOper.setSysOperId("00003");
		sysOper.setSysOperName("测试管理员");
		sysOper.setSysOperPwd("PU8r8H3BvjiyDNbkaUmhBx+dDj0=");
		
		sysOperMapper.insert(sysOper);
		
		
		sysOper = sysOperMapper.findById("00003");
		
		System.out.print(sysOper.toString());
		
		
	
	       


	}

}
