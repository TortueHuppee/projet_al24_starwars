package test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="toto")
@SessionScoped
public class TestBean {
	private String toto;
	public String getToto() {
		return "hello";
	}

	public void setToto(String toto) {
		this.toto = toto;
	}
	
}
