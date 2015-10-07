package fr.afcepf.al24.qualite;
/**
 * Une classe exemple de mon package.
 * @author yanick
 *
 */
public class Qualite {

    /**
     * attribut 1 exemple.
     */
    private int att;
    /**
     * attribut 2 exemple.
     */
    private String att2;
    /**
     * Constructeur.
     * @param attribut la valeur de att.
     * @param attribut2 la valeur de att2.
     */
    public Qualite(int attribut, String attribut2) {
        super();
        this.att = attribut;
        this.att2 = attribut2;
    }
    /**
     * m�thode 1 exemple.
     * @return une chaine de caract�re.
     */
    public String methode1() {
        return "";
    }
    /**
     * m�thode 2 exemple.
     * @param i la valeur de i.
     * @param j la valeur de j.
     * @return la valeur de {@link Qualite#att}.
     */
    public int methode2(int i, int j) {
        return i % j;
    }
    /**
     * getters / setters.
     * @return la valeur de {@link Qualite#att};
     */
    public int getAtt() {
        return att;
    }
    /**
     * getters / setters.
     * @param attribut la valeur de att.
     */
    public void setAtt(int attribut) {
        this.att = attribut;
    }
    /**
     * @return the att2
     */
    public String getAtt2() {
        return att2;
    }
    /**
     * @param paramAtt2 the att2 to set
     */
    public void setAtt2(String paramAtt2) {
        att2 = paramAtt2;
    }
}
