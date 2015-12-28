import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.service.ISysService;



@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class) 
@org.springframework.test.context.ContextConfiguration({  
	"file:WebRoot/WEB-INF/springmvc-servlet.xml",
    "file:WebRoot/WEB-INF/beans.xml"
})  

// 添加注释@Transactional 回滚对数据库操作  
@Transactional
public class MyBatisTestCase extends AbstractJUnit4SpringContextTests {
	
	
	 @Resource 
	 ISysService sysService; 
	 


/*	 @Test 
	 public void testSysOper() { 
		 System.out.println(sysService.findOperById("00001")); 
		 


	 } */
	 
	 
	 @Test 
	 public void testSysOperRight() { 
		 SysOper sysOper = sysService.findOperById("00001");
		 
		 sysOper.setStat(1);
		 
		int rs =  sysService.updateSysOper(sysOper);
		
		System.out.print(rs);


	 } 

}
