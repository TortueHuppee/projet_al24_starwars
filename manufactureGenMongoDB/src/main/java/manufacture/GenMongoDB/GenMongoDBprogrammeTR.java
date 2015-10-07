/**
 * 
 */
package manufacture.GenMongoDB;

import org.apache.log4j.Logger;

/**
 * @author yanick
 *
 */
public class GenMongoDBprogrammeTR {

	private static Logger log = Logger.getLogger(GenMongoDBprogramme.class);
	private static long interval = 1000;
	
	public static void main(String[] args) {
		
		try {
			
			int i =0;
			while (i < 10) {
			Thread.sleep(interval);
			log.info("COUCOU");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
