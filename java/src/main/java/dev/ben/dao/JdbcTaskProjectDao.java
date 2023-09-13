package dev.ben.dao;
import dev.ben.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTaskProjectDao implements TaskProjectDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTaskProjectDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Task createTask(Task newTask){
        String sql = "";

        return null;
    }

}
