package Repository;

import models.AuditLog;
import models.Dto.CreateAuditLogDto;
import models.Dto.UpdateAuditLogDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuditLogRepository extends BaseRepository<AuditLog, CreateAuditLogDto, UpdateAuditLogDto> {
    public AuditLogRepository() {
        super("Audit_Log");
    }

    @Override
    protected AuditLog fromResultSet(ResultSet res) throws SQLException {
        return AuditLog.getInstance(res);
    }

    @Override
    public AuditLog create(CreateAuditLogDto createDto) {
        String query = "INSERT INTO Audit_Log (perdorues_id, veprimi) VALUES (?, ?)";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, createDto.getPerdoruesId());
            pstm.setString(2, createDto.getVeprimi());

            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating audit log failed, no rows affected.");
            }

            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1);
                return AuditLog.of(newId, createDto.getPerdoruesId(), createDto.getVeprimi(), null); // data left null
            } else {
                throw new SQLException("Creating audit log failed, no ID obtained.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public AuditLog update(UpdateAuditLogDto updateDto) {
        String query = "UPDATE Audit_Log SET perdorues_id = ?, veprimi = ? WHERE log_id = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, updateDto.getPerdoruesId());
            pstm.setString(2, updateDto.getVeprimi());
            pstm.setInt(3, updateDto.getLogId());

            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 1) {
                return AuditLog.of(updateDto.getLogId(), updateDto.getPerdoruesId(), updateDto.getVeprimi(), null); // data left null
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}