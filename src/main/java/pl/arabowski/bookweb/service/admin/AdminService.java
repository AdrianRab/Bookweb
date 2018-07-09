package pl.arabowski.bookweb.service.admin;

import org.springframework.web.servlet.ModelAndView;

public interface AdminService {
	
	public ModelAndView deleteBook(long id);
	public ModelAndView editAuthor(long id);
	public ModelAndView deleteAuthor(long id);
	public ModelAndView editPublisher(long id);
	public ModelAndView deletePublisher(long id);
	public void addAdminRights(long id);
	public void removeAdminRights(long id);
	public ModelAndView editUser(long id);
	public ModelAndView deleteUser(long id);
	
}
