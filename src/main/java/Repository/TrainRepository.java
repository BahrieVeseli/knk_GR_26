package Repository;

import models.Dto.CreateTrenatDto;
import models.Dto.UpdateTrenatDto;
import models.Trenat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainRepository extends BaseRepository<Trenat, CreateTrenatDto, UpdateTrenatDto> {
    public TrainRepository(){
        super("trenat");
    }

    Trenat fromResultSet(ResultSet res) throws SQLException{
        return Trenat.getInstance(res);
    }
    public Trenat create(CreateTrenatDto trenatDto){
        String query = """
                INSERT INTO 
                TRENAT (emri_trenit, tipi_trenit, kapaciteti)
                VALUES (?, ?, ?)
                """;
        try{
            PreparedStatement pstm =
                    this.connection.prepareStatement(
                            query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, trenatDto.getEmriTrenti());
            pstm.setString(2, trenatDto.getTipiTrenit());
            pstm.setInt(3, trenatDto.getKapacitei());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if(res.next()){
                int id = res.getInt(1);
                return this.getById(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }



}
