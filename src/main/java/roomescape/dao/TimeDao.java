package roomescape.dao;

import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.domain.Time;

@Repository
public class TimeDao {

    private final JdbcTemplate jdbcTemplate;

    public TimeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long create(Time time) {
        String sql = "INSERT INTO reservation_time(start_at) VALUES (?)";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, time.getStartAt().toString());
            return ps;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<Time> getAll() {
        String sql = "SELECT id, start_at FROM reservation_time";
        RowMapper<Time> rowMapper = (resultSet, rowNum) -> new Time(
                resultSet.getLong("id"),
                LocalTime.parse(resultSet.getString("start_at"))
        );
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void delete(long id) {
        String sql = "DELETE FROM reservation_time WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
