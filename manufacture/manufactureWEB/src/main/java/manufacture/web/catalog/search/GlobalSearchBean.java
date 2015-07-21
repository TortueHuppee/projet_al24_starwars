package manufacture.web.catalog.search;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="globalSearchBean")
@SessionScoped
public class GlobalSearchBean {
	private String searchedTerm;

	public String getSearchedTerm() {
		return searchedTerm;
	}

	public void setSearchedTerm(String searchedTerm) {
		this.searchedTerm = searchedTerm;
	}
}
