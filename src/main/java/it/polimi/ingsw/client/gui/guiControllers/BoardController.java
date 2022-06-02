package it.polimi.ingsw.client.gui.guiControllers;

import it.polimi.ingsw.controller.message.*;
import it.polimi.ingsw.model.Assistant;
import it.polimi.ingsw.model.PawnColor;
import it.polimi.ingsw.model.SchoolBoard;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class BoardController extends GuiController {

    @FXML ImageView enemyProfessor0; @FXML ImageView enemyProfessor1; @FXML ImageView enemyProfessor2; @FXML ImageView enemyProfessor3; @FXML ImageView enemyProfessor4;
    @FXML ImageView entrance0; @FXML ImageView entrance1; @FXML ImageView entrance2; @FXML ImageView entrance3; @FXML ImageView entrance4; @FXML ImageView entrance5; @FXML ImageView entrance6; @FXML ImageView entrance7; @FXML ImageView entrance8;
    @FXML ImageView enemyStudent00; @FXML ImageView enemyStudent01; @FXML ImageView enemyStudent02; @FXML ImageView enemyStudent03; @FXML ImageView enemyStudent04; @FXML ImageView enemyStudent05; @FXML ImageView enemyStudent06; @FXML ImageView enemyStudent07; @FXML ImageView enemyStudent08; @FXML ImageView enemyStudent09;
    @FXML ImageView enemyStudent10; @FXML ImageView enemyStudent11; @FXML ImageView enemyStudent12; @FXML ImageView enemyStudent13; @FXML ImageView enemyStudent14; @FXML ImageView enemyStudent15; @FXML ImageView enemyStudent16; @FXML ImageView enemyStudent17; @FXML ImageView enemyStudent18; @FXML ImageView enemyStudent19;
    @FXML ImageView enemyStudent20; @FXML ImageView enemyStudent21; @FXML ImageView enemyStudent22; @FXML ImageView enemyStudent23; @FXML ImageView enemyStudent24; @FXML ImageView enemyStudent25; @FXML ImageView enemyStudent26; @FXML ImageView enemyStudent27; @FXML ImageView enemyStudent28; @FXML ImageView enemyStudent29;
    @FXML ImageView enemyStudent30; @FXML ImageView enemyStudent31; @FXML ImageView enemyStudent32; @FXML ImageView enemyStudent33; @FXML ImageView enemyStudent34; @FXML ImageView enemyStudent35; @FXML ImageView enemyStudent36; @FXML ImageView enemyStudent37; @FXML ImageView enemyStudent38; @FXML ImageView enemyStudent39;
    @FXML ImageView enemyStudent40; @FXML ImageView enemyStudent41; @FXML ImageView enemyStudent42; @FXML ImageView enemyStudent43; @FXML ImageView enemyStudent44; @FXML ImageView enemyStudent45; @FXML ImageView enemyStudent46; @FXML ImageView enemyStudent47; @FXML ImageView enemyStudent48; @FXML ImageView enemyStudent49;

    @FXML Button myEntrance0; @FXML Button myEntrance1; @FXML Button myEntrance2; @FXML Button myEntrance3; @FXML Button myEntrance4; @FXML Button myEntrance5; @FXML Button myEntrance6; @FXML Button myEntrance7; @FXML Button myEntrance8;
    @FXML Button student00; @FXML Button student01; @FXML Button student02; @FXML Button student03; @FXML Button student04; @FXML Button student05; @FXML Button student06; @FXML Button student07; @FXML Button student08; @FXML Button student09;
    @FXML Button student10; @FXML Button student11; @FXML Button student12; @FXML Button student13; @FXML Button student14; @FXML Button student15; @FXML Button student16; @FXML Button student17; @FXML Button student18; @FXML Button student19;
    @FXML Button student20; @FXML Button student21; @FXML Button student22; @FXML Button student23; @FXML Button student24; @FXML Button student25; @FXML Button student26; @FXML Button student27; @FXML Button student28; @FXML Button student29;
    @FXML Button student30; @FXML Button student31; @FXML Button student32; @FXML Button student33; @FXML Button student34; @FXML Button student35; @FXML Button student36; @FXML Button student37; @FXML Button student38; @FXML Button student39; @FXML Button student40;
    @FXML Button student41; @FXML Button student42; @FXML Button student43; @FXML Button student44; @FXML Button student45; @FXML Button student46; @FXML Button student47; @FXML Button student48; @FXML Button student49;
    @FXML ImageView myProfessor0; @FXML ImageView myProfessor1; @FXML ImageView myProfessor2; @FXML ImageView myProfessor3; @FXML ImageView myProfessor4;
    @FXML ImageView myEntranceImage0; @FXML ImageView myEntranceImage1; @FXML ImageView myEntranceImage2; @FXML ImageView myEntranceImage3; @FXML ImageView myEntranceImage4; @FXML ImageView myEntranceImage5; @FXML ImageView myEntranceImage6; @FXML ImageView myEntranceImage7; @FXML ImageView myEntranceImage8;
    @FXML ImageView studentImage00; @FXML ImageView studentImage01; @FXML ImageView studentImage02; @FXML ImageView studentImage03; @FXML ImageView studentImage04; @FXML ImageView studentImage05; @FXML ImageView studentImage06; @FXML ImageView studentImage07; @FXML ImageView studentImage08; @FXML ImageView studentImage09;
    @FXML ImageView studentImage10; @FXML ImageView studentImage11; @FXML ImageView studentImage12; @FXML ImageView studentImage13; @FXML ImageView studentImage14; @FXML ImageView studentImage15; @FXML ImageView studentImage16; @FXML ImageView studentImage17; @FXML ImageView studentImage18; @FXML ImageView studentImage19;
    @FXML ImageView studentImage20; @FXML ImageView studentImage21; @FXML ImageView studentImage22; @FXML ImageView studentImage23; @FXML ImageView studentImage24; @FXML ImageView studentImage25; @FXML ImageView studentImage26; @FXML ImageView studentImage27; @FXML ImageView studentImage28; @FXML ImageView studentImage29;
    @FXML ImageView studentImage30; @FXML ImageView studentImage31; @FXML ImageView studentImage32; @FXML ImageView studentImage33; @FXML ImageView studentImage34; @FXML ImageView studentImage35; @FXML ImageView studentImage36; @FXML ImageView studentImage37; @FXML ImageView studentImage38; @FXML ImageView studentImage39;
    @FXML ImageView studentImage40; @FXML ImageView studentImage41; @FXML ImageView studentImage42; @FXML ImageView studentImage43; @FXML ImageView studentImage44; @FXML ImageView studentImage45; @FXML ImageView studentImage46; @FXML ImageView studentImage47; @FXML ImageView studentImage48; @FXML ImageView studentImage49;

    @FXML Button island0; @FXML Button island1; @FXML Button island2; @FXML Button island3; @FXML Button island4; @FXML Button island5; @FXML Button island6; @FXML Button island7; @FXML Button island8; @FXML Button island9; @FXML Button island10; @FXML Button island11;

    @FXML Button player2; @FXML Button player3; @FXML Button player4;

    @FXML Button assistant1; @FXML Button assistant2; @FXML Button assistant3; @FXML Button assistant4; @FXML Button assistant5; @FXML Button assistant6; @FXML Button assistant7; @FXML Button assistant8; @FXML Button assistant9;@FXML Button assistant10;

    @FXML ImageView assistantImage; @FXML ImageView assistant1Image; @FXML ImageView assistant2Image; @FXML ImageView assistant3Image; @FXML ImageView assistant4Image; @FXML ImageView assistant5Image; @FXML ImageView assistant6Image; @FXML ImageView assistant7Image; @FXML ImageView assistant8Image; @FXML ImageView assistant9Image; @FXML ImageView assistant10Image;


    private Map<String, Button> myTables;
    private Map<String, Button> myEntrance;
    private Map<String, ImageView> myEntranceImages;
    private Map<String, ImageView> myProfessors;

    private Map<String, ImageView> myTablesImages;

    private Map<String, ImageView> enemyEntrance;
    private Map<String, ImageView> enemyProfessors;
    private Map<String, ImageView> enemyTables;


    private GameMessage board;
    private int myPlayerId;
    private int studentMoved;
    private int enemyBoardDisplayed;
    private int state;

    public GameMessage getBoard() {
        return board;
    }

    public void setBoard(GameMessage board) {
        this.board = board;
        myPlayerId = board.getPlayerId();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialize");
        myEntrance = new HashMap<>();
        myProfessors = new HashMap<>();
        myTables = new HashMap<>();
        myEntranceImages = new HashMap<>();
        myTablesImages = new HashMap<>();

        enemyEntrance = new HashMap<>();
        enemyProfessors = new HashMap<>();
        enemyTables = new HashMap<>();

        myEntrance.put("myEntrance0", myEntrance0); myEntrance.put("myEntrance1", myEntrance1); myEntrance.put("myEntrance2", myEntrance2); myEntrance.put("myEntrance3", myEntrance3); myEntrance.put("myEntrance4", myEntrance4); myEntrance.put("myEntrance5", myEntrance5); myEntrance.put("myEntrance6", myEntrance6); myEntrance.put("myEntrance7", myEntrance7); myEntrance.put("myEntrance8", myEntrance8);
        myEntranceImages.put("myEntranceImage0", myEntranceImage0); myEntranceImages.put("myEntranceImage1", myEntranceImage1); myEntranceImages.put("myEntranceImage2", myEntranceImage2); myEntranceImages.put("myEntranceImage3", myEntranceImage3); myEntranceImages.put("myEntranceImage4", myEntranceImage4); myEntranceImages.put("myEntranceImage5", myEntranceImage5); myEntranceImages.put("myEntranceImage6", myEntranceImage6); myEntranceImages.put("myEntranceImage7", myEntranceImage7); myEntranceImages.put("myEntranceImage8", myEntranceImage8);
        enemyEntrance.put("entrance0", entrance0); enemyEntrance.put("entrance1", entrance1); enemyEntrance.put("entrance2", entrance2); enemyEntrance.put("entrance3", entrance3); enemyEntrance.put("entrance4", entrance4); enemyEntrance.put("entrance5", entrance5); enemyEntrance.put("entrance6", entrance6); enemyEntrance.put("entrance7", entrance7); enemyEntrance.put("entrance8", entrance8);

        myProfessors.put("myProfessor0", myProfessor0); myProfessors.put("myProfessor1", myProfessor1); myProfessors.put("myProfessor2", myProfessor2); myProfessors.put("myProfessor3", myProfessor3); myProfessors.put("myProfessor4", myProfessor4);
        enemyProfessors.put("myProfessor0", myProfessor0); enemyProfessors.put("myProfessor1", myProfessor1); enemyProfessors.put("myProfessor2", myProfessor2); enemyProfessors.put("myProfessor3", myProfessor3); enemyProfessors.put("myProfessor4", myProfessor4);

        myTables.put("student00", student00); myTables.put("student01", student01); myTables.put("student02", student02); myTables.put("student03", student03); myTables.put("student01", student04); myTables.put("student05", student05); myTables.put("student06", student06); myTables.put("student07", student07); myTables.put("student08", student08); myTables.put("student09", student09);
        myTables.put("student10", student10); myTables.put("student11", student11); myTables.put("student12", student12); myTables.put("student13", student13); myTables.put("student11", student14); myTables.put("student15", student15); myTables.put("student16", student16); myTables.put("student17", student17); myTables.put("student18", student18); myTables.put("student19", student19);
        myTables.put("student20", student20); myTables.put("student21", student21); myTables.put("student22", student22); myTables.put("student23", student23); myTables.put("student21", student24); myTables.put("student25", student25); myTables.put("student26", student26); myTables.put("student27", student27); myTables.put("student28", student28); myTables.put("student29", student29);
        myTables.put("student30", student30); myTables.put("student31", student31); myTables.put("student32", student32); myTables.put("student33", student33); myTables.put("student31", student34); myTables.put("student35", student35); myTables.put("student36", student36); myTables.put("student37", student37); myTables.put("student38", student38); myTables.put("student39", student39);
        myTables.put("student40", student40); myTables.put("student41", student41); myTables.put("student42", student42); myTables.put("student43", student43); myTables.put("student41", student44); myTables.put("student45", student45); myTables.put("student46", student46); myTables.put("student47", student47); myTables.put("student48", student48); myTables.put("student49", student49);

        myTablesImages.put("studentImage00", studentImage00); myTablesImages.put("studentImage01", studentImage01); myTablesImages.put("studentImage02", studentImage02); myTablesImages.put("studentImage03", studentImage03); myTablesImages.put("studentImage01", studentImage04); myTablesImages.put("studentImage05", studentImage05); myTablesImages.put("studentImage06", studentImage06); myTablesImages.put("studentImage07", studentImage07); myTablesImages.put("studentImage08", studentImage08); myTablesImages.put("studentImage09", studentImage09);
        myTablesImages.put("studentImage10", studentImage10); myTablesImages.put("studentImage11", studentImage11); myTablesImages.put("studentImage12", studentImage12); myTablesImages.put("studentImage13", studentImage13); myTablesImages.put("studentImage11", studentImage14); myTablesImages.put("studentImage15", studentImage15); myTablesImages.put("studentImage16", studentImage16); myTablesImages.put("studentImage17", studentImage17); myTablesImages.put("studentImage18", studentImage18); myTablesImages.put("studentImage19", studentImage19);
        myTablesImages.put("studentImage20", studentImage20); myTablesImages.put("studentImage21", studentImage21); myTablesImages.put("studentImage22", studentImage22); myTablesImages.put("studentImage23", studentImage23); myTablesImages.put("studentImage21", studentImage24); myTablesImages.put("studentImage25", studentImage25); myTablesImages.put("studentImage26", studentImage26); myTablesImages.put("studentImage27", studentImage27); myTablesImages.put("studentImage28", studentImage28); myTablesImages.put("studentImage29", studentImage29);
        myTablesImages.put("studentImage30", studentImage30); myTablesImages.put("studentImage31", studentImage31); myTablesImages.put("studentImage32", studentImage32); myTablesImages.put("studentImage33", studentImage33); myTablesImages.put("studentImage31", studentImage34); myTablesImages.put("studentImage35", studentImage35); myTablesImages.put("studentImage36", studentImage36); myTablesImages.put("studentImage37", studentImage37); myTablesImages.put("studentImage38", studentImage38); myTablesImages.put("studentImage39", studentImage39);
        myTablesImages.put("studentImage40", studentImage40); myTablesImages.put("studentImage41", studentImage41); myTablesImages.put("studentImage42", studentImage42); myTablesImages.put("studentImage43", studentImage43); myTablesImages.put("studentImage41", studentImage44); myTablesImages.put("studentImage45", studentImage45); myTablesImages.put("studentImage46", studentImage46); myTablesImages.put("studentImage47", studentImage47); myTablesImages.put("studentImage48", studentImage48); myTablesImages.put("studentImage49", studentImage49);

        enemyTables.put("enemyStudent00", enemyStudent00); enemyTables.put("enemyStudent01", enemyStudent01); enemyTables.put("enemyStudent02", enemyStudent02); enemyTables.put("enemyStudent03", enemyStudent03); enemyTables.put("enemyStudent01", enemyStudent04); enemyTables.put("enemyStudent05", enemyStudent05); enemyTables.put("enemyStudent06", enemyStudent06); enemyTables.put("enemyStudent07", enemyStudent07); enemyTables.put("enemyStudent08", enemyStudent08); enemyTables.put("enemyStudent09", enemyStudent09);
        enemyTables.put("enemyStudent10", enemyStudent10); enemyTables.put("enemyStudent11", enemyStudent11); enemyTables.put("enemyStudent12", enemyStudent12); enemyTables.put("enemyStudent13", enemyStudent13); enemyTables.put("enemyStudent11", enemyStudent14); enemyTables.put("enemyStudent15", enemyStudent15); enemyTables.put("enemyStudent16", enemyStudent16); enemyTables.put("enemyStudent17", enemyStudent17); enemyTables.put("enemyStudent18", enemyStudent18); enemyTables.put("enemyStudent19", enemyStudent19);
        enemyTables.put("enemyStudent20", enemyStudent20); enemyTables.put("enemyStudent21", enemyStudent21); enemyTables.put("enemyStudent22", enemyStudent22); enemyTables.put("enemyStudent23", enemyStudent23); enemyTables.put("enemyStudent21", enemyStudent24); enemyTables.put("enemyStudent25", enemyStudent25); enemyTables.put("enemyStudent26", enemyStudent26); enemyTables.put("enemyStudent27", enemyStudent27); enemyTables.put("enemyStudent28", enemyStudent28); enemyTables.put("enemyStudent29", enemyStudent29);
        enemyTables.put("enemyStudent30", enemyStudent30); enemyTables.put("enemyStudent31", enemyStudent31); enemyTables.put("enemyStudent32", enemyStudent32); enemyTables.put("enemyStudent33", enemyStudent33); enemyTables.put("enemyStudent31", enemyStudent34); enemyTables.put("enemyStudent35", enemyStudent35); enemyTables.put("enemyStudent36", enemyStudent36); enemyTables.put("enemyStudent37", enemyStudent37); enemyTables.put("enemyStudent38", enemyStudent38); enemyTables.put("enemyStudent39", enemyStudent39);
        enemyTables.put("enemyStudent40", enemyStudent40); enemyTables.put("enemyStudent41", enemyStudent41); enemyTables.put("enemyStudent42", enemyStudent42); enemyTables.put("enemyStudent43", enemyStudent43); enemyTables.put("enemyStudent41", enemyStudent44); enemyTables.put("enemyStudent45", enemyStudent45); enemyTables.put("enemyStudent46", enemyStudent46); enemyTables.put("enemyStudent47", enemyStudent47); enemyTables.put("enemyStudent48", enemyStudent48); enemyTables.put("enemyStudent49", enemyStudent49);

        enemyBoardDisplayed = 1;
        //displayBoard(1);
    }

    public void adaptSceneToPlayers() {
        Platform.runLater(() -> {
                if (board.getNumberOfPLayers() == 2) {
                    player2.setVisible(false);
                    player3.setVisible(false);
                    player4.setVisible(false);
                    entrance7.setVisible(false);
                    entrance8.setVisible(false);
                    myEntrance7.setVisible(false);
                    myEntrance8.setVisible(false);
                } else if (board.getNumberOfPLayers() == 3) {
                    player4.setVisible(false);
                } else if (board.getNumberOfPLayers() == 4) {
                    entrance7.setVisible(false);
                    entrance8.setVisible(false);
                    myEntrance7.setVisible(false);
                    myEntrance8.setVisible(false);
                }
                displayBoard(1);
        });
        System.out.println("Partita adattata per " + board.getNumberOfPLayers() + " giocatori");
    }

    /**
     * Per ora setta tutto di colore verdeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
     * @param node
     * @param student
     */
    public void setStudent(ImageView node, Student student) {
        //System.out.println("Colora studenti");
        Platform.runLater(() -> {
            if(node != null) {
                if (student != null) {
                    switch (student.getColor()) {
                        case GREEN:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/green.png"));
                            node.setFitHeight(24);
                            node.setFitWidth(24);
                            break;
                        case RED:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/red.png"));
                            node.setFitHeight(24);
                            node.setFitWidth(24);
                            break;
                        case YELLOW:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/yellow.png"));
                            node.setFitHeight(24);
                            node.setFitWidth(24);
                            break;
                        case PINK:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/pink.png"));
                            node.setFitHeight(30);
                            node.setFitWidth(44);
                            break;
                        case BLUE:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/blue.png"));
                            node.setFitHeight(24);
                            node.setFitWidth(24);
                            break;
                    }
                } else {
                    node.setImage(new Image("/images/island_prova.png"));
                    node.setFitHeight(24);
                    node.setFitWidth(24);
                }
            }
        });
    }

    /**
     * Qua andranno i professoiiriririiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
     * @param node
     * @param color
     */
    public void setProfessor(ImageView node, PawnColor color) {
        //System.out.println("Colora professori");
        Platform.runLater(() -> {
            if(node != null) {
                if (color != null) {
                    switch (color) {
                        case GREEN:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/green.png"));
                            node.setFitHeight(24);
                            node.setFitWidth(24);
                            break;
                        case RED:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/red.png"));
                            node.setFitHeight(24);
                            node.setFitWidth(24);
                            break;
                        case YELLOW:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/yellow.png"));
                            node.setFitHeight(24);
                            node.setFitWidth(24);
                            break;
                        case PINK:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/pink.png"));
                            node.setFitHeight(30);
                            node.setFitWidth(44);
                            break;
                        case BLUE:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/blue.png"));
                            node.setFitHeight(24);
                            node.setFitWidth(24);
                            break;
                    }
                } else {
                    node.setImage(new Image("/images/island_prova.png"));
                    node.setFitHeight(24);
                    node.setFitWidth(24);
                }
            }
        });
    }

    public void displayEnemySchoolboard(int playerOffset) {
        System.out.println("displayEnemySchoolboard");
        System.out.println(playerOffset + " " + board.getNumberOfPLayers());
        if (board != null) {
            if(playerOffset < board.getNumberOfPLayers()) {
                SchoolBoard schoolBoard = board.getGametable().getSchoolBoards()[(myPlayerId + (playerOffset)) % board.getNumberOfPLayers()];
                for (int i = 0; i < schoolBoard.getStudentsFromEntrance().length; i++) {
                    setStudent(enemyEntrance.get(("entrance" + i)), schoolBoard.getStudentsFromEntrance()[i]);
                }
                for (int i = 0; i < PawnColor.values().length; i++) {
                    for (int j = 0; j < schoolBoard.getNumberOfStudentsOnTable(i); j++) {
                        try {
                            setStudent(enemyTables.get(("studentImage" + i + "" + j)), new Student(PawnColor.associateIndexToPawnColor(i)));
                        } catch (InvalidIndexException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                for (int i = 0; i < PawnColor.values().length; i++) {
                    try {
                        if (schoolBoard.getProfessors().contains(PawnColor.associateIndexToPawnColor(i))) {
                            setProfessor(enemyProfessors.get(("enemyProfessor" + i)), PawnColor.associateIndexToPawnColor(i));
                        } else {
                            setProfessor(enemyProfessors.get(("enemyProfessor" + i)), null);
                        }
                    } catch (InvalidIndexException e) {
                        System.out.println(e.getMessage());
                    }
                }
                /*for() {} parte sulle torri*/
                System.out.println("Rendered the enemy schoolboard");
            }
        }
    }

    public void displayMySchoolboard() {
        System.out.println("displayMySchoolboard");
        if (board != null) {
            SchoolBoard schoolBoard = board.getGametable().getSchoolBoards()[myPlayerId];
            for (int i = 0; i < schoolBoard.getStudentsFromEntrance().length; i++) {
                setStudent(myEntranceImages.get(("myEntranceImage" + i)), schoolBoard.getStudentsFromEntrance()[i]);
            }
            for (int i = 0; i < PawnColor.values().length; i++) {
                for (int j = 0; j < schoolBoard.getNumberOfStudentsOnTable(i); j++) {
                    try {
                        setStudent(myTablesImages.get(("studentImages" + i + "" + j)), new Student(PawnColor.associateIndexToPawnColor(i)));
                    } catch (InvalidIndexException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            for (int i = 0; i < PawnColor.values().length; i++) {
                try {
                    if (schoolBoard.getProfessors().contains(PawnColor.associateIndexToPawnColor(i))) {
                        setProfessor(myProfessors.get(("myProfessor" + i)), PawnColor.associateIndexToPawnColor(i));
                    }
                } catch (InvalidIndexException e) {
                    System.out.println(e.getMessage());
                }
            }
            /*for() {} parte sulle torri*/
        }
    }

    public void displayIslands() {
        System.out.println("displayIslands");

    }

    public void displayClouds() {
        System.out.println("displayClouds");

    }

    public void displayBoard(int playerOffset) {
        System.out.println("displayBoard");
        System.out.println("Player: " + board.getPlayerOnTurn());
        System.out.println(board.getPlayerMessage());
        System.out.println(playerOffset);
        displayEnemySchoolboard(playerOffset);
        displayMySchoolboard();
        displayIslands();
        displayClouds();
        displayAssistants();
    }

    public void changeBoard(ActionEvent event) {
        switch(((Button) event.getSource()).getId()) {
            case "player2":
                displayBoard(1);
                break;
            case "player3":
                displayBoard(2);
                break;
            case "player4":
                displayBoard(3);
                break;
        }
    }
    @Override
    public void update(Message message) {
        System.out.println("Arrivato un messaggio alla update");
        if(message instanceof GameMessage && message != null) {
            System.out.println("Il messaggio arrivato è un GameMessage e non è nullo");
            board = (GameMessage) message;
            if (board.getState() == 100) {
                Platform.runLater(new Runnable() {
                                      @Override
                                      public void run() {
                                          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/endgameScene.fxml"));
                                          try {
                                              Parent root = loader.load();
                                              GuiController endgameController = loader.getController();
                                              getClient().removeObserver(BoardController.this);
                                              endgameController.setStage(getStage());
                                              endgameController.setScene(new Scene(root));
                                              endgameController.setRoot(root);
                                              getClient().addObserver(endgameController);
                                              endgameController.setClient(getClient());
                                              endgameController.getStage().setScene(endgameController.getScene());
                                              endgameController.getStage().show();
                                          } catch(IOException e) {
                                              //Errore, spero non capiti
                                          }
                                      }
                                  }
                );
            } else {
                Platform.runLater(() -> displayBoard(enemyBoardDisplayed));
            }
            System.out.println(((GameMessage) message).getPlayerMessage());
        }
    }


    @FXML
    public void assistantClick(MouseEvent event) {
        /*switch(((Button) event.getSource()).getId()) {
            case "myEntrance0":
                studentMoved = 0;
                state = 1;
                break;
            case "myEntrance1":
                studentMoved = 1;
                state = 1;
                break;
            case "myEntrance2":
                studentMoved = 2;
                state = 1;
                break;
            case "myEntrance3":
                studentMoved = 3;
                state = 1;
                break;
            case "myEntrance4":
                studentMoved = 4;
                state = 1;
                break;
            case "myEntrance5":
                studentMoved = 5;
                state = 1;
                break;
            case "myEntrance6":
                studentMoved = 6;
                state = 1;
                break;
            case "myEntrance7":
                studentMoved = 7;
                state = 1;
                break;
            case "myEntrance8":
                studentMoved = 8;
                state = 1;
                break;
        }*/
        int indexCard = 0;
        switch(((Button) event.getSource()).getId()) {
            case "assistant1":
                indexCard = 0;
                break;
            case "assistant2":
                indexCard = 1;
                break;
            case "assistant3":
                indexCard = 2;
                break;
            case "assistant4":
                indexCard = 3;
                break;
            case "assistant5":
                indexCard = 4;
                break;
            case "assistant6":
                indexCard = 5;
                break;
            case "assistant7":
                indexCard = 6;
                break;
            case "assistant8":
                indexCard = 7;
                break;
            case "assistant9":
                indexCard = 8;
                break;
            case "assistant10":
                indexCard = 9;
                break;
        }
        getClient().asyncWriteToSocket(new PlayAssistantMessage(myPlayerId, indexCard));
        System.out.println("Inviato messaggio PlayAssistantMessage");
    }

    public void myEntranceClick(ActionEvent event) {
        System.out.println(((Button) event.getSource()).getId() + " pressed");
        switch(((Button) event.getSource()).getId()) {
            case "myEntrance0":
                studentMoved = 0;
                state = 1;
                break;
            case "myEntrance1":
                studentMoved = 1;
                state = 1;
                break;
            case "myEntrance2":
                studentMoved = 2;
                state = 1;
                break;
            case "myEntrance3":
                studentMoved = 3;
                state = 1;
                break;
            case "myEntrance4":
                studentMoved = 4;
                state = 1;
                break;
            case "myEntrance5":
                studentMoved = 5;
                state = 1;
                break;
            case "myEntrance6":
                studentMoved = 6;
                state = 1;
                break;
            case "myEntrance7":
                studentMoved = 7;
                state = 1;
                break;
            case "myEntrance8":
                studentMoved = 8;
                state = 1;
                break;
        }

        System.out.println("Student");
    }

    public void myTablesClick(ActionEvent event) {
        if (state == 1) {
            getClient().asyncWriteToSocket(new AddStudentOnTableMessage(myPlayerId, studentMoved));
            System.out.println("Inviato messaggio AddStudentOnTableMessage");
            state = 0;
        }
    }

    public void islandClick(ActionEvent event) {
        int islandIndex = -1;
        System.out.println(((Button) event.getSource()).getId() + " pressed");
        switch(((Button) event.getSource()).getId()) {
            case "island0":
                islandIndex = 0;
                break;
            case "island1":
                islandIndex = 1;
                break;
            case "myEntrance2":
                islandIndex = 2;
                break;
            case "myEntrance3":
                islandIndex = 3;
                break;
            case "myEntrance4":
                islandIndex = 4;
                break;
            case "myEntrance5":
                islandIndex = 5;
                break;
            case "myEntrance6":
                islandIndex = 6;
                break;
            case "myEntrance7":
                islandIndex = 7;
                break;
            case "myEntrance8":
                islandIndex = 8;
                break;
            case "myEntrance9":
                islandIndex = 9;
                break;
            case "myEntrance10":
                islandIndex = 10;
                break;
            case "myEntrance11":
                islandIndex = 11;
                break;
        }
        if(islandIndex < 12 && islandIndex > -1) {
            if (state == 1) {
                getClient().asyncWriteToSocket(new AddStudentOnIslandMessage(myPlayerId, studentMoved, islandIndex));
                System.out.println("Inviato messaggio AddStudentOnIslandMessage");
            }
        }
    }

    public void showAssistant(MouseEvent event) {
        String idAssistant = ((Button) event.getSource()).getId();
        String imagePath = "/images/Assistant/";
        switch (idAssistant) {
            case "assistant1":
                imagePath += "assistant0.png";
                break;
            case "assistant2":
                imagePath += "assistant1.png";
                break;
            case "assistant3":
                imagePath += "assistant2.png";
                break;
            case "assistant4":
                imagePath += "assistant3.png";
                break;
            case "assistant5":
                imagePath += "assistant4.png";
                break;
            case "assistant6":
                imagePath += "assistant5.png";
                break;
            case "assistant7":
                imagePath += "assistant6.png";
                break;
            case "assistant8":
                imagePath += "assistant7.png";
                break;
            case "assistant9":
                imagePath += "assistant8.png";
                break;
            case "assistant10":
                imagePath += "assistant9.png";
                break;
        }
        Image imageAssistant = new Image(imagePath);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                assistantImage.setImage(imageAssistant);
                assistantImage.setVisible(true);
            }
        });
    }
    public void removeShowAssistant(MouseEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                assistantImage.setVisible(false);
            }
        });
    }

    private void displayAssistants() {
        List<Integer> assistantNotPlayed = new ArrayList<>();
        for (Assistant as : board.getAssistants()) assistantNotPlayed.add(as.getCardValue());
        for (int i = 1; i <= 10; i++) {
            String imagePath = "/images/Assistant/buttonAssistantB&W";
            Image imageBW;
            if (!assistantNotPlayed.contains(i)) {
                switch (i) {
                    case 1:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                assistant1Image.setImage(imageBW);
                                assistant1.setMouseTransparent(true);
                            }
                        });
                        break;
                    case 2:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                assistant2Image.setImage(imageBW);
                                assistant2.setMouseTransparent(true);
                            }
                        });
                        break;
                    case 3:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                assistant3Image.setImage(imageBW);
                                assistant3.setMouseTransparent(true);
                            }
                        });
                        break;
                    case 4:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                assistant4Image.setImage(imageBW);
                                assistant4.setMouseTransparent(true);
                            }
                        });
                        break;
                    case 5:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                assistant5Image.setImage(imageBW);
                                assistant5.setMouseTransparent(true);
                            }
                        });
                        break;
                    case 6:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                assistant6Image.setImage(imageBW);
                                assistant6.setMouseTransparent(true);
                            }
                        });
                        break;
                    case 7:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                assistant7Image.setImage(imageBW);
                                assistant7.setMouseTransparent(true);
                            }
                        });
                        break;
                    case 8:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                assistant8Image.setImage(imageBW);
                                assistant8.setMouseTransparent(true);
                            }
                        });
                        break;
                    case 9:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                assistant9Image.setImage(imageBW);
                                assistant9.setMouseTransparent(true);
                            }
                        });
                        break;
                    case 10:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                assistant10Image.setImage(imageBW);
                                assistant10.setMouseTransparent(true);
                            }
                        });
                        break;
                }
            }
        }
    }

}
