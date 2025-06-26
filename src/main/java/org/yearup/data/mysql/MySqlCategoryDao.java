package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class MySqlCategoryDao implements CategoryDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlCategoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> getAll() {
        String sql = "SELECT * FROM categories";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    @Override
    public Category getById(int categoryId) {
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, categoryId);
    }

    @Override
    public void create(Category category) {
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
        jdbcTemplate.update(sql, category.getName(), category.getDescription());
    }

    @Override
    public void update(Category category) {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";
        jdbcTemplate.update(sql, category.getName(), category.getDescription(), category.getCategoryId());
    }

    @Override
    public void delete(int categoryId) {
        String sql = "DELETE FROM categories WHERE category_id = ?";
        jdbcTemplate.update(sql, categoryId);
    }

    private Category mapRow(ResultSet row, int rowNum) throws SQLException {
        Category category = new Category();
        category.setCategoryId(row.getInt("category_id"));
        category.setName(row.getString("name"));
        category.setDescription(row.getString("description"));
        return category;
    }
}