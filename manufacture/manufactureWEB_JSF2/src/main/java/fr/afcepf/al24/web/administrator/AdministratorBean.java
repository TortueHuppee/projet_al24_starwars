package fr.afcepf.al24.web.administrator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

@ManagedBean(name="mbAdmin")
@SessionScoped
public class AdministratorBean {

	private static Logger log = Logger.getLogger(AdministratorBean.class);
	
	private String rubriqueChoisie;

	public void chooseRubrique(String rubrique)
	{
		rubriqueChoisie = rubrique;
	}
	
	public String getRubriqueChoisie() {
		return rubriqueChoisie;
	}

	public void setRubriqueChoisie(String rubriqueChoisie) {
		this.rubriqueChoisie = rubriqueChoisie;
	}
	
	public static void setLog(Logger log) {
		AdministratorBean.log = log;
	}
}
