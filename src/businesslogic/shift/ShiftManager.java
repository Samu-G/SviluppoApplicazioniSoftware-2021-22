package businesslogic.shift;

import businesslogic.UseCaseLogicException;
import businesslogic.user.User;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShiftManager {

    private final ArrayList<ShiftEventReciever> eventReceivers;

    public ShiftManager() {
        this.eventReceivers = new ArrayList<>();
    }

    public ArrayList<Shift> getShiftsBoard() throws UseCaseLogicException {
        ArrayList<Shift> shiftsBoard = new ArrayList<>();

        String query = "SELECT * FROM shifts";

        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                do {
                    shiftsBoard.add(new Shift(
                            rs.getInt("id"),
                            rs.getDate("date"),
                            rs.getTime("begin_time"),
                            rs.getTime("end_time"),
                            rs.getBoolean("is_full"),
                            rs.getBoolean("is_concluded"),
                            getUsersShiftById(rs.getInt("id"))
                    ));
                } while (rs.next());
            }
        });

        if (shiftsBoard.isEmpty()) {
            throw new UseCaseLogicException();
        }

        return shiftsBoard;
    }

    private static ArrayList<User> getUsersShiftById(int shiftId) {

        ArrayList<User> userShifts = new ArrayList<>();

        String userShiftsIdQuery = "SELECT * FROM usershifts WHERE shift_id = '" + shiftId + "'";

        PersistenceManager.executeQuery(userShiftsIdQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                do {
                    userShifts.add(User.loadUserById(rs.getInt("user_id")));
                } while (rs.next());
            }
        });

        return userShifts;
    }

    /*Notify event reciver*/

    public void addEventReceiver(ShiftEventReciever rec) {
        this.eventReceivers.add(rec);
    }

    public void removeEventReceiver(ShiftEventReciever rec) {
        this.eventReceivers.remove(rec);
    }



}
