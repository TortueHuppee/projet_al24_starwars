package fr.afcepf.al24.qualite;
/**
 * Une classe inutile de mon package.
 * @author Stagiaire
 *
 */

public class Qualite {

    private int att;
    private String att2;

    public Qualite(int att, String att2) {
        super();
		this.att = att;
		this.att2 = att2;
	}

    public String methode1() {
		return "";
	}

    public int methode2(int i, int j) {
		return i % j;
	}


    public int getAtt() {
		return att;
	}

    public void setAtt(int att) {
		this.att = att;
	}

    public String getAtt2() {
		return att2;
	}

    public void setAtt2(String att2) {
		this.att2 = att2;
	}

}
