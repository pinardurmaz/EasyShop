package org.yearup.data;

import org.yearup.models.Category;
import java.util.List;

public interface CategoryDao {
    List<Category> getAll();
    Category getById(int id);
    void create(Category category);
    void update(Category category);
    void delete(int id);
}