package businesslogic.procedure;

import businesslogic.CatERing;
import businesslogic.user.User;
import persistence.PersistenceManager;

import java.util.ArrayList;

public class Procedure {

    private int id;

    private String name;

    private String instruction;

    private final User owner;

    private String tag;

    private int time;

    private int quantity;

    private String procedureType;

    private ArrayList<Ingredient> ingredients;
    
    /* constructors */

    public Procedure(String name, String instruction, User owner) {
        this.name = name;
        this.instruction = instruction;
        this.owner = owner;
    }

    public Procedure(int id, String name, String instruction, String tag, int time, int quantity, User owner, int procedureType) {
        this.id = id;
        this.name = name;
        this.instruction = instruction;
        this.tag = tag;
        this.time = time;
        this.quantity = quantity;
        this.owner = owner;

        switch (procedureType) {
            case 1 -> this.procedureType = "Recipe";
            case 2 -> this.procedureType = "Preparation";
        }
    }

    public Procedure(String name, String instruction, User owner, String procedureType) {
        this.name = name;
        this.instruction = instruction;
        this.owner = owner;
        this.procedureType = procedureType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public User getOwner() {
        return owner;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getProcedureType() {
        return procedureType;
    }

    public void setProcedureType(String procedureType) {
        this.procedureType = procedureType;
    }

    /* Persistence */

    public static void createProcedure(Procedure p) {
        String procedureName = p.getName();

        String instruction = p.getInstruction();

        int procedureType = 0;

        switch (p.getProcedureType()) {
            case "Recipe" -> procedureType = 1;
            case "Preparation" -> procedureType = 2;
        }

        int userId = CatERing.getInstance().getUserManager().getCurrentUser().getId();

        String insert = "INSERT procedures (name, instruction, owner_id, procedure_type) " +
                "VALUES ('" + procedureName + "', '" + instruction + "' , '" + userId + "', '" + procedureType + "')";

        PersistenceManager.executeUpdate(insert);

        p.setId(PersistenceManager.getLastId());
    }

    public static void removeProcedure(Procedure p) {
        int procedureId = p.getId();

        String query = "DELETE FROM procedures WHERE id = '" + procedureId + "'";

        PersistenceManager.executeUpdate(query);
    }


    @Override
    public String toString() {
        return "\n" + "Sono una " + (this.procedureType.equals("Recipe") ? "Ricetta" : "Preparazione") +
                ", " + "il mio nome e': " + this.name + ", " + "le istruzioni sono: " + this.instruction + ";\n"
                + (this.tag == null ? "Nessun tag associato" : "Il tag associato e' " + this.tag) + ", " + (this.time == 0 ? "Le tempistiche non sono state specificate" : "Il tempo specificato e' di " + this.time + " minuti.") +
                ", " + (this.quantity == 0 ? "La quantita' non e' stata specificata" : "La quantita' specificata e' di " + this.quantity) + "\n";
    }
}
