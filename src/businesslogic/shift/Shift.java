package businesslogic.shift;

import businesslogic.user.User;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;

public class Shift {

    private int id;

    private Date date;

    private Time beginTime;

    private Time endTime;

    private boolean full;

    private boolean concluded;

    private ArrayList<User> charged;

    public Shift(int id, Date date, Time beginTime, Time endTime, boolean full, boolean concluded, ArrayList<User> charged) {
        this.id = id;
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.full = full;
        this.concluded = concluded;
        this.charged = charged;
    }

    public Shift() {

    }

    public static Shift loadShiftById(int i) {
        String query = "SELECT * FROM shifts WHERE id = '" + i + "'";

        Shift s = new Shift();

        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {

                do {
                    s.setId(rs.getInt("id"));
                    s.setDate(rs.getDate("Date"));
                    s.setBeginTime(rs.getTime("begin_time"));
                    s.setEndTime(rs.getTime("end_time"));
                    s.setFull(rs.getBoolean("is_full"));
                    s.setConcluded(rs.getBoolean("is_concluded"));
                    s.setCharged(Shift.loadUserByShiftId(rs.getInt("id")));
                } while (rs.next());
            }
        });

        return s;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public void setConcluded(boolean concluded) {
        this.concluded = concluded;
    }

    public ArrayList<User> getCharged() {
        return charged;
    }

    public void setCharged(ArrayList<User> charged) {
        this.charged = charged;
    }

    public boolean isConcluded() {
        return concluded;
    }

    public boolean isFull() {
        return full;
    }

    public void markFullShift() {
        this.full = true;
    }

    public void addNewCharged(User toAdd) {
        this.charged.add(toAdd);
    }

    // PERSISTENCE


    public static ArrayList<User> loadUserByShiftId(int shiftId) {
        ArrayList<User> userLoadedByShiftId = new ArrayList<>();

        String query = "SELECT * FROM usershifts WHERE shift_id = '" + shiftId + "'";

        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {

                do {
                    userLoadedByShiftId.add(
                            User.loadUserById(rs.getInt("user_id"))
                    );
                } while (rs.next());
            }
        });

        return userLoadedByShiftId;
    }

    @Override
    public String toString() {
        return "\nIl turno con id: " + this.id +
                " in data " + this.date + ", tra le ore " + this.beginTime + " e le ore " + this.endTime + "; " +
                (isConcluded() ? " risulta essere concluso" : " non e' concluso") +
                (isFull() ? " ed e' saturo di addetti;" : " e non e' saturo di addetti;") + "\n" +
                (this.charged.isEmpty() ? "Nessun addetto e' disponibile in questo turno" : ("Gli addetto/i disponibile/i in questo turno sono: " + this.charged)) + "\n";
    }
}
