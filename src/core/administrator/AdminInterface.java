package core.administrator;

import core.category.CategoryInterface;
import core.category.CategoryTree;
import core.user.UserInterface;

import java.sql.ResultSet;


public interface AdminInterface {

    public void bannUser(UserInterface user);

    public void bannUser(UserInterface user, int hours);

    public void releaseBann(UserInterface user);

    public ResultSet findUser(String name, String bann, String active, String categoryName);

    public void addCategory(CategoryInterface parentCategory, CategoryInterface category);

    public void setCategoryTree(CategoryTree categoryTree);

    public ResultSet getReports();

    public ResultSet getReports(int days);

    public ResultSet getWantedCategories();

    public ResultSet getWantedCategories(int days);
}
