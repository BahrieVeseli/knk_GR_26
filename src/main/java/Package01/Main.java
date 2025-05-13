package Package01;
import Database.DBConnector;
import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {

       Connection connection= DBConnector.getConnection();
       Statement statement=connection.createStatement();

        String query1= """
                INSERT INTO Trenat (emri_trenit, tipi_trenit, kapaciteti) VALUES
                ('Adriatik Express', 'Pasagjerë', 200),
                ('Kosova Rail', 'Pasagjerë', 180),
                ('Cargo Nord', 'Mallra', 150),
                ('Balkan Express', 'Pasagjerë', 220),
                ('Prishtina CityTrain', 'Urban', 100),
                ('Dukagjini Cargo', 'Mallra', 130),
                ('Sharri Express', 'Pasagjerë', 160),
                ('Fushë Kosova Loop', 'Urban', 90),
                ('Mitrovica Cargo', 'Mallra', 140),
                ('Train Europe', 'Pasagjerë', 210);
                """;

        String query2= """
                INSERT INTO Destinacionet (emri_stacionit) VALUES
                ('Fushë Kosovë'),
                ('Prishtinë'),
                ('Mitrovicë'),
                ('Pejë'),
                ('Ferizaj'),
                ('Gjakovë'),
                ('Gjilan'),
                ('Skenderaj'),
                ('Hani i Elezit'),
                ('Klinë');
                """;

        String query3= """
    INSERT INTO Perdoruesit (emri_perdoruesit, fjalekalimi, roli) VALUES
    ('admin1', 'admin123', 'admin'),
    ('admin2', 'op123', 'operator'),
    ('admin3', 'op456', 'operator'),
    ('admin4', 'view123', 'shikues'),
    ('admin5', 'view456', 'shikues'),
    ('admin6', 'admin456', 'admin'),
    ('admin7', 'op789', 'operator'),
    ('admin8', 'view789', 'shikues'),
    ('admin9', 'admin789', 'admin'),
    ('admin10', 'op321', 'operator')
    ON CONFLICT (emri_perdoruesit) DO NOTHING;
    """;
      //ON CONFLICT DO NOTHING sepse kemi nje emri_perdoruesit unique dhe mund te shkaktoj error

        String query4= """
                INSERT INTO Orari_Linjave (tren_id, nisja_id, mbrritja_id, koha_nisjes, koha_mberritjes, dita) VALUES
                (1, 1, 2, '08:00', '08:45', 'Hene'),
                (2, 2, 3, '09:15', '10:00', 'Hene'),
                (3, 1, 5, '07:00', '09:00', 'Marte'),
                (4, 5, 1, '10:30', '12:15', 'Merkure'),
                (5, 1, 6, '11:00', '13:00', 'Enjte'),
                (6, 6, 1, '13:30', '15:00', 'Premte'),
                (7, 1, 4, '14:00', '16:00', 'Shtune'),
                (8, 4, 1, '17:00', '18:30', 'Diel'),
                (9, 1, 9, '06:30', '08:30', 'Hene'),
                (10, 1, 10, '15:00', '17:00', 'Enjte');
                
                """;
        String query5= """
                INSERT INTO Statistika (tren_id, data, pasagjere_totale) VALUES
                (1, '2025-05-01', 180),
                (2, '2025-05-01', 160),
                (3, '2025-05-01', 0),
                (4, '2025-05-01', 210),
                (5, '2025-05-01', 95),
                (6, '2025-05-01', 0),
                (7, '2025-05-02', 150),
                (8, '2025-05-02', 85),
                (9, '2025-05-02', 0),
                (10, '2025-05-02', 190);
                
                """;
            String query6 = """
    INSERT INTO Audit_Log (perdorues_id, veprimi) VALUES
    (1, 'Shtoi trenin Adriatik Express'),
    (2, 'Përditësoi orarin për trenin Kosova Rail'),
    (3, 'Fshiu një destinacion të vjetër nga Peja'),
    (4, 'Shikoi statistikat për datën 2025-05-01'),
    (5, 'Shikoi listën e të gjithë trenave'),
    (6, 'Shtoi një orar të ri për trenin Sharri Express'),
    (7, 'Ndryshoi fjalëkalimin e tij personal'),
    (8, 'Shikoi orarin e ditës së enjte'),
    (9, 'Shtoi një përdorues të ri me rol operator'),
    (10, 'Shikoi statistikat mujore për prill 2025');
    """;
            String query7 = """
    INSERT INTO Udhetime (orari_id, data_udhetimit, pasagjere, statusi) VALUES
    (1, '2025-05-10', 175, 'realizuar'),
    (2, '2025-05-10', 160, 'realizuar'),
    (3, '2025-05-11', 0, 'anuluar'),
    (4, '2025-05-11', 200, 'realizuar'),
    (5, '2025-05-12', 90, 'realizuar'),
    (6, '2025-05-12', 0, 'anuluar'),
    (7, '2025-05-13', 140, 'planifikuar'),
    (8, '2025-05-13', 80, 'planifikuar'),
    (9, '2025-05-14', 0, 'planifikuar'),
    (10, '2025-05-14', 190, 'realizuar');
    """;
        String query8 = """
INSERT INTO Rezervime (perdorues_id, orari_id, data_udhetimit, nr_biletave) VALUES
(1, 1, '2025-05-15', 2),
(2, 2, '2025-05-15', 1),
(3, 3, '2025-05-16', 3),
(4, 4, '2025-05-16', 2),
(5, 5, '2025-05-17', 1),
(6, 6, '2025-05-17', 4),
(7, 7, '2025-05-18', 2),
(8, 8, '2025-05-18', 1),
(9, 9, '2025-05-19', 5),
(10, 10, '2025-05-19', 3);
""";


        // statement.execute(query1);
        statement.executeUpdate(query1);
        statement.executeUpdate(query2);
        statement.executeUpdate(query3);
        statement.executeUpdate(query4);
        statement.executeUpdate(query5);
        statement.executeUpdate(query6);
        statement.executeUpdate(query7);
        statement.executeUpdate(query8);


      //  statement.execute("SELECT * FROM Destinacionet WHERE emri_stacionit='Prishtinë'");

 query1= """
         SELECT * FROM Trenat
         """;

        ResultSet results=statement.executeQuery(query1);
             while(results.next()){
            int trenId=results.getInt("tren_id");
            String emriTrenit=results.getString("emri_trenit");
            String tipiTrenit=results.getString("tipi_trenit");
            int kapaciteti=results.getInt("kapaciteti");
            System.out.println("Id: " +trenId+" "+"\nEmri i trenit: "+emriTrenit+" "+"\nTipi i trenit: "+tipiTrenit+"\nKapaciteti: "+kapaciteti);
            System.out.println("----------------------------");
        }
    }
}
