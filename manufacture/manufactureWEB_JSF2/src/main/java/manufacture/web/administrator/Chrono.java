/**
 * 
 */
package manufacture.web.administrator;

import java.util.Date;

/**
 * @author yanick
 *
 */
public class Chrono {

	private Date startDate;
	private Date stopDate;
	/**
	 * 
	 */
	public Chrono() {
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the stopDate
	 */
	public Date getStopDate() {
		return stopDate;
	}
	/**
	 * @param stopDate the stopDate to set
	 */
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Chrono [startDate=" + startDate + ", stopDate=" + stopDate
				+ "]";
	}
	public void start(){
		startDate = new Date();
	}
	
	public void stop() {
		stopDate = new Date();
	}
	
	public long tempsEcoule() {
		return (stopDate.getTime() - startDate.getTime());
	}
}
