package core.administrator;

import core.category.CategoryInterface;
import core.category.CategoryTree;
import core.user.UserInterface;

import java.util.List;


public interface AdminInterface {

    public void bannUser(UserInterface user);

    public void bannUser(UserInterface user, int hours);

    public void releaseBann(UserInterface user);

    public List<UserInterface> findUser(String name);

    public List<UserInterface> findUsersExtended(String name, boolean banned, CategoryInterface category);

    public void addCategory(CategoryInterface parentCategory, CategoryInterface category);

    public void setCategoryTree(CategoryTree categoryTree);
}
