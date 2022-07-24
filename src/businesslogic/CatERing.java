package businesslogic;

import businesslogic.event.EventManager;
import businesslogic.menu.MenuManager;
import businesslogic.procedure.ProcedureManager;
import businesslogic.recipe.RecipeManager;
import businesslogic.shift.ShiftManager;
import businesslogic.summarySheet.SummarySheetManager;
import businesslogic.user.UserManager;
import persistence.PersistenceMenu;
import persistence.PersistenceProcedure;
import persistence.PersistenceSummarySheet;

public class CatERing {
    private static CatERing singleInstance;

    public static CatERing getInstance() {
        if (singleInstance == null) {
            singleInstance = new CatERing();
        }
        return singleInstance;
    }

    private final MenuManager menuMgr;
    private final RecipeManager recipeMgr;
    private final UserManager userMgr;
    private final EventManager eventMgr;
    private final SummarySheetManager summarySheetManager;
    private final ProcedureManager procedureManager;
    private final ShiftManager shiftManager;


    private CatERing() {
        menuMgr = new MenuManager();
        recipeMgr = new RecipeManager();
        userMgr = new UserManager();
        eventMgr = new EventManager();
        summarySheetManager = new SummarySheetManager();
        procedureManager = new ProcedureManager();
        shiftManager = new ShiftManager();

        PersistenceMenu persistenceMenu = new PersistenceMenu();
        PersistenceSummarySheet persistenceSummarySheet = new PersistenceSummarySheet();
        PersistenceProcedure persistenceProcedure = new PersistenceProcedure();

        menuMgr.addEventReceiver(persistenceMenu);
        summarySheetManager.addEventReceiver(persistenceSummarySheet);
        procedureManager.addEventReceiver(persistenceProcedure);
    }


    public MenuManager getMenuManager() {
        return menuMgr;
    }

    public RecipeManager getRecipeManager() {
        return recipeMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }
    public EventManager getEventManager() { return eventMgr; }
    public SummarySheetManager getSummarySheetManager() {return summarySheetManager; }

    public ProcedureManager getProcedureManager() {
        return procedureManager;
    }

    public ShiftManager getShiftManager() { return shiftManager; }

}
