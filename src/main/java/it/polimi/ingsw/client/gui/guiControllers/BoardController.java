package it.polimi.ingsw.client.gui.guiControllers;

import it.polimi.ingsw.controller.message.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exception.InvalidIndexException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Timer;


public class BoardController extends GuiController {

    @FXML ImageView enemyProfessor0; @FXML ImageView enemyProfessor1; @FXML ImageView enemyProfessor2; @FXML ImageView enemyProfessor3; @FXML ImageView enemyProfessor4;
    @FXML ImageView enemyEntrance0; @FXML ImageView enemyEntrance1; @FXML ImageView enemyEntrance2; @FXML ImageView enemyEntrance3; @FXML ImageView enemyEntrance4; @FXML ImageView enemyEntrance5; @FXML ImageView enemyEntrance6; @FXML ImageView enemyEntrance7; @FXML ImageView enemyEntrance8;
    @FXML ImageView enemyStudent00; @FXML ImageView enemyStudent01; @FXML ImageView enemyStudent02; @FXML ImageView enemyStudent03; @FXML ImageView enemyStudent04; @FXML ImageView enemyStudent05; @FXML ImageView enemyStudent06; @FXML ImageView enemyStudent07; @FXML ImageView enemyStudent08; @FXML ImageView enemyStudent09;
    @FXML ImageView enemyStudent10; @FXML ImageView enemyStudent11; @FXML ImageView enemyStudent12; @FXML ImageView enemyStudent13; @FXML ImageView enemyStudent14; @FXML ImageView enemyStudent15; @FXML ImageView enemyStudent16; @FXML ImageView enemyStudent17; @FXML ImageView enemyStudent18; @FXML ImageView enemyStudent19;
    @FXML ImageView enemyStudent20; @FXML ImageView enemyStudent21; @FXML ImageView enemyStudent22; @FXML ImageView enemyStudent23; @FXML ImageView enemyStudent24; @FXML ImageView enemyStudent25; @FXML ImageView enemyStudent26; @FXML ImageView enemyStudent27; @FXML ImageView enemyStudent28; @FXML ImageView enemyStudent29;
    @FXML ImageView enemyStudent30; @FXML ImageView enemyStudent31; @FXML ImageView enemyStudent32; @FXML ImageView enemyStudent33; @FXML ImageView enemyStudent34; @FXML ImageView enemyStudent35; @FXML ImageView enemyStudent36; @FXML ImageView enemyStudent37; @FXML ImageView enemyStudent38; @FXML ImageView enemyStudent39;
    @FXML ImageView enemyStudent40; @FXML ImageView enemyStudent41; @FXML ImageView enemyStudent42; @FXML ImageView enemyStudent43; @FXML ImageView enemyStudent44; @FXML ImageView enemyStudent45; @FXML ImageView enemyStudent46; @FXML ImageView enemyStudent47; @FXML ImageView enemyStudent48; @FXML ImageView enemyStudent49;
    @FXML ImageView enemyTower0; @FXML ImageView enemyTower1; @FXML ImageView enemyTower2; @FXML ImageView enemyTower3; @FXML ImageView enemyTower4; @FXML ImageView enemyTower5; @FXML ImageView enemyTower6; @FXML ImageView enemyTower7;

    @FXML Button myEntrance0; @FXML Button myEntrance1; @FXML Button myEntrance2; @FXML Button myEntrance3; @FXML Button myEntrance4; @FXML Button myEntrance5; @FXML Button myEntrance6; @FXML Button myEntrance7; @FXML Button myEntrance8;
    @FXML Button student00; @FXML Button student01; @FXML Button student02; @FXML Button student03; @FXML Button student04; @FXML Button student05; @FXML Button student06; @FXML Button student07; @FXML Button student08; @FXML Button student09;
    @FXML Button student10; @FXML Button student11; @FXML Button student12; @FXML Button student13; @FXML Button student14; @FXML Button student15; @FXML Button student16; @FXML Button student17; @FXML Button student18; @FXML Button student19;
    @FXML Button student20; @FXML Button student21; @FXML Button student22; @FXML Button student23; @FXML Button student24; @FXML Button student25; @FXML Button student26; @FXML Button student27; @FXML Button student28; @FXML Button student29;
    @FXML Button student30; @FXML Button student31; @FXML Button student32; @FXML Button student33; @FXML Button student34; @FXML Button student35; @FXML Button student36; @FXML Button student37; @FXML Button student38; @FXML Button student39;
    @FXML Button student40; @FXML Button student41; @FXML Button student42; @FXML Button student43; @FXML Button student44; @FXML Button student45; @FXML Button student46; @FXML Button student47; @FXML Button student48; @FXML Button student49;
    @FXML ImageView myProfessor0; @FXML ImageView myProfessor1; @FXML ImageView myProfessor2; @FXML ImageView myProfessor3; @FXML ImageView myProfessor4;
    @FXML ImageView myEntranceImage0; @FXML ImageView myEntranceImage1; @FXML ImageView myEntranceImage2; @FXML ImageView myEntranceImage3; @FXML ImageView myEntranceImage4; @FXML ImageView myEntranceImage5; @FXML ImageView myEntranceImage6; @FXML ImageView myEntranceImage7; @FXML ImageView myEntranceImage8;
    @FXML ImageView studentImage00; @FXML ImageView studentImage01; @FXML ImageView studentImage02; @FXML ImageView studentImage03; @FXML ImageView studentImage04; @FXML ImageView studentImage05; @FXML ImageView studentImage06; @FXML ImageView studentImage07; @FXML ImageView studentImage08; @FXML ImageView studentImage09;
    @FXML ImageView studentImage10; @FXML ImageView studentImage11; @FXML ImageView studentImage12; @FXML ImageView studentImage13; @FXML ImageView studentImage14; @FXML ImageView studentImage15; @FXML ImageView studentImage16; @FXML ImageView studentImage17; @FXML ImageView studentImage18; @FXML ImageView studentImage19;
    @FXML ImageView studentImage20; @FXML ImageView studentImage21; @FXML ImageView studentImage22; @FXML ImageView studentImage23; @FXML ImageView studentImage24; @FXML ImageView studentImage25; @FXML ImageView studentImage26; @FXML ImageView studentImage27; @FXML ImageView studentImage28; @FXML ImageView studentImage29;
    @FXML ImageView studentImage30; @FXML ImageView studentImage31; @FXML ImageView studentImage32; @FXML ImageView studentImage33; @FXML ImageView studentImage34; @FXML ImageView studentImage35; @FXML ImageView studentImage36; @FXML ImageView studentImage37; @FXML ImageView studentImage38; @FXML ImageView studentImage39;
    @FXML ImageView studentImage40; @FXML ImageView studentImage41; @FXML ImageView studentImage42; @FXML ImageView studentImage43; @FXML ImageView studentImage44; @FXML ImageView studentImage45; @FXML ImageView studentImage46; @FXML ImageView studentImage47; @FXML ImageView studentImage48; @FXML ImageView studentImage49;
    @FXML ImageView myTower0; @FXML ImageView myTower1; @FXML ImageView myTower2; @FXML ImageView myTower3; @FXML ImageView myTower4; @FXML ImageView myTower5; @FXML ImageView myTower6; @FXML ImageView myTower7;

    @FXML AnchorPane islandPane0; @FXML AnchorPane islandPane1; @FXML AnchorPane islandPane2; @FXML AnchorPane islandPane3; @FXML AnchorPane islandPane4; @FXML AnchorPane islandPane5;    @FXML AnchorPane islandPane6; @FXML AnchorPane islandPane7; @FXML AnchorPane islandPane8; @FXML AnchorPane islandPane9; @FXML AnchorPane islandPane10; @FXML AnchorPane islandPane11;
    @FXML ImageView island0; @FXML ImageView island1; @FXML ImageView island2; @FXML ImageView island3; @FXML ImageView island4; @FXML ImageView island5; @FXML ImageView island6; @FXML ImageView island7; @FXML ImageView island8; @FXML ImageView island9; @FXML ImageView island10; @FXML ImageView island11;
    @FXML ImageView student0OnIsland0; @FXML ImageView student0OnIsland1; @FXML ImageView student0OnIsland2; @FXML ImageView student0OnIsland3; @FXML ImageView student0OnIsland4; @FXML ImageView student0OnIsland5; @FXML ImageView student0OnIsland6; @FXML ImageView student0OnIsland7; @FXML ImageView student0OnIsland8; @FXML ImageView student0OnIsland9; @FXML ImageView student0OnIsland10; @FXML ImageView student0OnIsland11;
    @FXML ImageView student1OnIsland0; @FXML ImageView student1OnIsland1; @FXML ImageView student1OnIsland2; @FXML ImageView student1OnIsland3; @FXML ImageView student1OnIsland4; @FXML ImageView student1OnIsland5; @FXML ImageView student1OnIsland6; @FXML ImageView student1OnIsland7; @FXML ImageView student1OnIsland8; @FXML ImageView student1OnIsland9; @FXML ImageView student1OnIsland10; @FXML ImageView student1OnIsland11;
    @FXML ImageView student2OnIsland0; @FXML ImageView student2OnIsland1; @FXML ImageView student2OnIsland2; @FXML ImageView student2OnIsland3; @FXML ImageView student2OnIsland4; @FXML ImageView student2OnIsland5; @FXML ImageView student2OnIsland6; @FXML ImageView student2OnIsland7; @FXML ImageView student2OnIsland8; @FXML ImageView student2OnIsland9; @FXML ImageView student2OnIsland10; @FXML ImageView student2OnIsland11;
    @FXML ImageView student3OnIsland0; @FXML ImageView student3OnIsland1; @FXML ImageView student3OnIsland2; @FXML ImageView student3OnIsland3; @FXML ImageView student3OnIsland4; @FXML ImageView student3OnIsland5; @FXML ImageView student3OnIsland6; @FXML ImageView student3OnIsland7; @FXML ImageView student3OnIsland8; @FXML ImageView student3OnIsland9; @FXML ImageView student3OnIsland10; @FXML ImageView student3OnIsland11;
    @FXML ImageView student4OnIsland0; @FXML ImageView student4OnIsland1; @FXML ImageView student4OnIsland2; @FXML ImageView student4OnIsland3; @FXML ImageView student4OnIsland4; @FXML ImageView student4OnIsland5; @FXML ImageView student4OnIsland6; @FXML ImageView student4OnIsland7; @FXML ImageView student4OnIsland8; @FXML ImageView student4OnIsland9; @FXML ImageView student4OnIsland10; @FXML ImageView student4OnIsland11;
    @FXML ImageView towerOnIsland0; @FXML ImageView towerOnIsland1; @FXML ImageView towerOnIsland2; @FXML ImageView towerOnIsland3; @FXML ImageView towerOnIsland4; @FXML ImageView towerOnIsland5; @FXML ImageView towerOnIsland6; @FXML ImageView towerOnIsland7; @FXML ImageView towerOnIsland8; @FXML ImageView towerOnIsland9; @FXML ImageView towerOnIsland10; @FXML ImageView towerOnIsland11;

    @FXML Button player2; @FXML Button player3; @FXML Button player4;

    @FXML Button assistant1; @FXML Button assistant2; @FXML Button assistant3; @FXML Button assistant4; @FXML Button assistant5; @FXML Button assistant6; @FXML Button assistant7; @FXML Button assistant8; @FXML Button assistant9;@FXML Button assistant10;
    @FXML ImageView assistant1Image; @FXML ImageView assistant2Image; @FXML ImageView assistant3Image; @FXML ImageView assistant4Image; @FXML ImageView assistant5Image; @FXML ImageView assistant6Image; @FXML ImageView assistant7Image; @FXML ImageView assistant8Image; @FXML ImageView assistant9Image; @FXML ImageView assistant10Image;

    @FXML ImageView assistantImage;
    @FXML ImageView imageStudentsCount0; @FXML ImageView imageStudentsCount1; @FXML ImageView imageStudentsCount2; @FXML ImageView imageStudentsCount3; @FXML ImageView imageStudentsCount4;
    @FXML ImageView imageTowersCount;
    @FXML Label studentsCount0; @FXML Label studentsCount1; @FXML Label studentsCount2; @FXML Label studentsCount3; @FXML Label studentsCount4;
    @FXML Label towersCount;

    @FXML ImageView enemyAssistant; @FXML ImageView myAssistant;

    @FXML ImageView character1Image; @FXML ImageView character2Image; @FXML ImageView character3Image; @FXML Button student1Character1; @FXML Button student2Character1; @FXML Button student3Character1; @FXML Button student4Character1; @FXML Button student5Character1; @FXML Button student6Character1; @FXML Button student1Character2; @FXML Button student2Character2; @FXML Button student3Character2;
    @FXML Button student4Character2; @FXML Button student5Character2; @FXML Button student6Character2;@FXML Button student1Character3; @FXML Button student2Character3; @FXML Button student3Character3; @FXML Button student4Character3; @FXML Button student5Character3; @FXML Button student6Character3;

    @FXML AnchorPane centerUpperAnchorPane;
    @FXML ImageView cloud0; @FXML ImageView cloud1; @FXML ImageView cloud2; @FXML ImageView cloud3;
    @FXML AnchorPane cloudPane0; @FXML AnchorPane cloudPane1; @FXML AnchorPane cloudPane2; @FXML AnchorPane cloudPane3;
    @FXML ImageView cloud0Student0; @FXML ImageView cloud0Student1; @FXML ImageView cloud0Student2; @FXML ImageView cloud0Student3; @FXML ImageView cloud1Student0; @FXML ImageView cloud1Student1; @FXML ImageView cloud1Student2; @FXML ImageView cloud1Student3;
    @FXML ImageView cloud2Student0; @FXML ImageView cloud2Student1; @FXML ImageView cloud2Student2; @FXML ImageView cloud2Student3; @FXML ImageView cloud3Student0; @FXML ImageView cloud3Student1; @FXML ImageView cloud3Student2; @FXML ImageView cloud3Student3;

    @FXML Label labelGameMessage;
    //@FXML Label nickAssistantPlayed;
    @FXML Button pawnColor0; @FXML Button pawnColor1; @FXML Button pawnColor2; @FXML Button pawnColor3; @FXML Button pawnColor4;
    @FXML Label coinNumber; @FXML ImageView coinImage;
    @FXML Button table0; @FXML Button table1; @FXML Button table2; @FXML Button table3; @FXML Button table4;

    @FXML ImageView coinTableImage; @FXML ImageView bag; @FXML Label coinTableNumber;

    private Map<String, Button> myTables;
    private Map<String, Button> myEntrance;
    private Map<String, Button> studentsCards;
    private Map<String, ImageView> myEntranceImages;
    private Map<String, ImageView> myProfessors;
    private Map<String, ImageView> myTablesImages;
    private Map<String, ImageView> myTowers;

    private Map<String, ImageView> enemyEntrance;
    private Map<String, ImageView> enemyProfessors;
    private Map<String, ImageView> enemyTables;
    private Map<String, ImageView> enemyTowers;

    private Map<String, AnchorPane> islandPanes;
    private Map<String, ImageView> islands;
    private Map<String, ImageView> studentsOnIslands;
    private Map<String, ImageView> towersOnIslands;
    private Map<String, ImageView> imageStudentsCounts;
    private Map<String, Label> studentsCounts;


    private Map<String, ImageView> clouds;
    private Map<String, ImageView> studentsClouds;

    private GameMessage board;
    private int myPlayerId;
    private int studentMoved;
    private int enemyBoardDisplayed;
    private int state;

    private ImageView motherNature;
    private int indexLastCharacterPlayed;
    private boolean toInitialize = true;

    /**
     * Returns the board sent by the server
     * @return board sent by the server
     */
    public GameMessage getBoard() {
        return board;
    }

    /**
     * Sets the board which is the stuff to show
     * @param board board to show on the graphic user interface
     */
    public void setBoard(GameMessage board) {
        this.board = board;
        myPlayerId = board.getPlayerId();
    }

    /**
     *
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("Initialize");
        myEntrance = new HashMap<>();
        myProfessors = new HashMap<>();
        myTables = new HashMap<>();
        myEntranceImages = new HashMap<>();
        myTablesImages = new HashMap<>();
        myTowers = new HashMap<>();
        studentsCards = new HashMap<>();

        enemyEntrance = new HashMap<>();
        enemyProfessors = new HashMap<>();
        enemyTables = new HashMap<>();
        enemyTowers = new HashMap<>();

        islandPanes = new HashMap<>();
        islands = new HashMap<>();
        studentsOnIslands = new HashMap<>();
        clouds = new HashMap<>();
        studentsClouds = new HashMap<>();
        towersOnIslands = new HashMap<>();
        imageStudentsCounts = new HashMap<>();
        studentsCounts = new HashMap<>();


        enemyProfessors.put("enemyProfessor0", enemyProfessor0); enemyProfessors.put("enemyProfessor1", enemyProfessor1); enemyProfessors.put("enemyProfessor2", enemyProfessor2); enemyProfessors.put("enemyProfessor3", enemyProfessor3); enemyProfessors.put("enemyProfessor4", enemyProfessor4);
        enemyEntrance.put("enemyEntrance0", enemyEntrance0); enemyEntrance.put("enemyEntrance1", enemyEntrance1); enemyEntrance.put("enemyEntrance2", enemyEntrance2); enemyEntrance.put("enemyEntrance3", enemyEntrance3); enemyEntrance.put("enemyEntrance4", enemyEntrance4); enemyEntrance.put("enemyEntrance5", enemyEntrance5); enemyEntrance.put("enemyEntrance6", enemyEntrance6); enemyEntrance.put("enemyEntrance7", enemyEntrance7); enemyEntrance.put("enemyEntrance8", enemyEntrance8);

        enemyTables.put("enemyStudent00", enemyStudent00); enemyTables.put("enemyStudent01", enemyStudent01); enemyTables.put("enemyStudent02", enemyStudent02); enemyTables.put("enemyStudent03", enemyStudent03); enemyTables.put("enemyStudent04", enemyStudent04); enemyTables.put("enemyStudent05", enemyStudent05); enemyTables.put("enemyStudent06", enemyStudent06); enemyTables.put("enemyStudent07", enemyStudent07); enemyTables.put("enemyStudent08", enemyStudent08); enemyTables.put("enemyStudent09", enemyStudent09);
        enemyTables.put("enemyStudent10", enemyStudent10); enemyTables.put("enemyStudent11", enemyStudent11); enemyTables.put("enemyStudent12", enemyStudent12); enemyTables.put("enemyStudent13", enemyStudent13); enemyTables.put("enemyStudent14", enemyStudent14); enemyTables.put("enemyStudent15", enemyStudent15); enemyTables.put("enemyStudent16", enemyStudent16); enemyTables.put("enemyStudent17", enemyStudent17); enemyTables.put("enemyStudent18", enemyStudent18); enemyTables.put("enemyStudent19", enemyStudent19);
        enemyTables.put("enemyStudent20", enemyStudent20); enemyTables.put("enemyStudent21", enemyStudent21); enemyTables.put("enemyStudent22", enemyStudent22); enemyTables.put("enemyStudent23", enemyStudent23); enemyTables.put("enemyStudent24", enemyStudent24); enemyTables.put("enemyStudent25", enemyStudent25); enemyTables.put("enemyStudent26", enemyStudent26); enemyTables.put("enemyStudent27", enemyStudent27); enemyTables.put("enemyStudent28", enemyStudent28); enemyTables.put("enemyStudent29", enemyStudent29);
        enemyTables.put("enemyStudent30", enemyStudent30); enemyTables.put("enemyStudent31", enemyStudent31); enemyTables.put("enemyStudent32", enemyStudent32); enemyTables.put("enemyStudent33", enemyStudent33); enemyTables.put("enemyStudent34", enemyStudent34); enemyTables.put("enemyStudent35", enemyStudent35); enemyTables.put("enemyStudent36", enemyStudent36); enemyTables.put("enemyStudent37", enemyStudent37); enemyTables.put("enemyStudent38", enemyStudent38); enemyTables.put("enemyStudent39", enemyStudent39);
        enemyTables.put("enemyStudent40", enemyStudent40); enemyTables.put("enemyStudent41", enemyStudent41); enemyTables.put("enemyStudent42", enemyStudent42); enemyTables.put("enemyStudent43", enemyStudent43); enemyTables.put("enemyStudent44", enemyStudent44); enemyTables.put("enemyStudent45", enemyStudent45); enemyTables.put("enemyStudent46", enemyStudent46); enemyTables.put("enemyStudent47", enemyStudent47); enemyTables.put("enemyStudent48", enemyStudent48); enemyTables.put("enemyStudent49", enemyStudent49);

        enemyTowers.put("enemyTower0", enemyTower0); enemyTowers.put("enemyTower1", enemyTower1); enemyTowers.put("enemyTower2", enemyTower2); enemyTowers.put("enemyTower3", enemyTower3); enemyTowers.put("enemyTower4", enemyTower4); enemyTowers.put("enemyTower5", enemyTower5); enemyTowers.put("enemyTower6", enemyTower6); enemyTowers.put("enemyTower7", enemyTower7);

        myProfessors.put("myProfessor0", myProfessor0); myProfessors.put("myProfessor1", myProfessor1); myProfessors.put("myProfessor2", myProfessor2); myProfessors.put("myProfessor3", myProfessor3); myProfessors.put("myProfessor4", myProfessor4);
        myEntrance.put("myEntrance0", myEntrance0); myEntrance.put("myEntrance1", myEntrance1); myEntrance.put("myEntrance2", myEntrance2); myEntrance.put("myEntrance3", myEntrance3); myEntrance.put("myEntrance4", myEntrance4); myEntrance.put("myEntrance5", myEntrance5); myEntrance.put("myEntrance6", myEntrance6); myEntrance.put("myEntrance7", myEntrance7); myEntrance.put("myEntrance8", myEntrance8);
        myEntranceImages.put("myEntranceImage0", myEntranceImage0); myEntranceImages.put("myEntranceImage1", myEntranceImage1); myEntranceImages.put("myEntranceImage2", myEntranceImage2); myEntranceImages.put("myEntranceImage3", myEntranceImage3); myEntranceImages.put("myEntranceImage4", myEntranceImage4); myEntranceImages.put("myEntranceImage5", myEntranceImage5); myEntranceImages.put("myEntranceImage6", myEntranceImage6); myEntranceImages.put("myEntranceImage7", myEntranceImage7); myEntranceImages.put("myEntranceImage8", myEntranceImage8);

        myTables.put("student00", student00); myTables.put("student01", student01); myTables.put("student02", student02); myTables.put("student03", student03); myTables.put("student04", student04); myTables.put("student05", student05); myTables.put("student06", student06); myTables.put("student07", student07); myTables.put("student08", student08); myTables.put("student09", student09);
        myTables.put("student10", student10); myTables.put("student11", student11); myTables.put("student12", student12); myTables.put("student13", student13); myTables.put("student14", student14); myTables.put("student15", student15); myTables.put("student16", student16); myTables.put("student17", student17); myTables.put("student18", student18); myTables.put("student19", student19);
        myTables.put("student20", student20); myTables.put("student21", student21); myTables.put("student22", student22); myTables.put("student23", student23); myTables.put("student24", student24); myTables.put("student25", student25); myTables.put("student26", student26); myTables.put("student27", student27); myTables.put("student28", student28); myTables.put("student29", student29);
        myTables.put("student30", student30); myTables.put("student31", student31); myTables.put("student32", student32); myTables.put("student33", student33); myTables.put("student34", student34); myTables.put("student35", student35); myTables.put("student36", student36); myTables.put("student37", student37); myTables.put("student38", student38); myTables.put("student39", student39);
        myTables.put("student40", student40); myTables.put("student41", student41); myTables.put("student42", student42); myTables.put("student43", student43); myTables.put("student44", student44); myTables.put("student45", student45); myTables.put("student46", student46); myTables.put("student47", student47); myTables.put("student48", student48); myTables.put("student49", student49);

        myTablesImages.put("studentImage00", studentImage00); myTablesImages.put("studentImage01", studentImage01); myTablesImages.put("studentImage02", studentImage02); myTablesImages.put("studentImage03", studentImage03); myTablesImages.put("studentImage04", studentImage04); myTablesImages.put("studentImage05", studentImage05); myTablesImages.put("studentImage06", studentImage06); myTablesImages.put("studentImage07", studentImage07); myTablesImages.put("studentImage08", studentImage08); myTablesImages.put("studentImage09", studentImage09);
        myTablesImages.put("studentImage10", studentImage10); myTablesImages.put("studentImage11", studentImage11); myTablesImages.put("studentImage12", studentImage12); myTablesImages.put("studentImage13", studentImage13); myTablesImages.put("studentImage14", studentImage14); myTablesImages.put("studentImage15", studentImage15); myTablesImages.put("studentImage16", studentImage16); myTablesImages.put("studentImage17", studentImage17); myTablesImages.put("studentImage18", studentImage18); myTablesImages.put("studentImage19", studentImage19);
        myTablesImages.put("studentImage20", studentImage20); myTablesImages.put("studentImage21", studentImage21); myTablesImages.put("studentImage22", studentImage22); myTablesImages.put("studentImage23", studentImage23); myTablesImages.put("studentImage24", studentImage24); myTablesImages.put("studentImage25", studentImage25); myTablesImages.put("studentImage26", studentImage26); myTablesImages.put("studentImage27", studentImage27); myTablesImages.put("studentImage28", studentImage28); myTablesImages.put("studentImage29", studentImage29);
        myTablesImages.put("studentImage30", studentImage30); myTablesImages.put("studentImage31", studentImage31); myTablesImages.put("studentImage32", studentImage32); myTablesImages.put("studentImage33", studentImage33); myTablesImages.put("studentImage34", studentImage34); myTablesImages.put("studentImage35", studentImage35); myTablesImages.put("studentImage36", studentImage36); myTablesImages.put("studentImage37", studentImage37); myTablesImages.put("studentImage38", studentImage38); myTablesImages.put("studentImage39", studentImage39);
        myTablesImages.put("studentImage40", studentImage40); myTablesImages.put("studentImage41", studentImage41); myTablesImages.put("studentImage42", studentImage42); myTablesImages.put("studentImage43", studentImage43); myTablesImages.put("studentImage44", studentImage44); myTablesImages.put("studentImage45", studentImage45); myTablesImages.put("studentImage46", studentImage46); myTablesImages.put("studentImage47", studentImage47); myTablesImages.put("studentImage48", studentImage48); myTablesImages.put("studentImage49", studentImage49);

        myTowers.put("myTower0", myTower0); myTowers.put("myTower1", myTower1); myTowers.put("myTower2", myTower2); myTowers.put("myTower3", myTower3); myTowers.put("myTower4", myTower4); myTowers.put("myTower5", myTower5); myTowers.put("myTower6", myTower6); myTowers.put("myTower7", myTower7);

        islands.put("island0", island0); islands.put("island1", island1); islands.put("island2", island2); islands.put("island3", island3); islands.put("island4", island4); islands.put("island5", island5); islands.put("island6", island6); islands.put("island7", island7); islands.put("island8", island8); islands.put("island9", island9); islands.put("island10", island10); islands.put("island11", island11);
        islandPanes.put("islandPane0", islandPane0); islandPanes.put("islandPane1", islandPane1); islandPanes.put("islandPane2", islandPane2); islandPanes.put("islandPane3", islandPane3); islandPanes.put("islandPane4", islandPane4); islandPanes.put("islandPane5", islandPane5); islandPanes.put("islandPane6", islandPane6); islandPanes.put("islandPane7", islandPane7); islandPanes.put("islandPane8", islandPane8); islandPanes.put("islandPane9", islandPane9); islandPanes.put("islandPane10", islandPane10); islandPanes.put("islandPane11", islandPane11);
        studentsOnIslands.put("student0OnIsland0", student0OnIsland0);  studentsOnIslands.put("student0OnIsland1", student0OnIsland1);  studentsOnIslands.put("student0OnIsland2", student0OnIsland2);  studentsOnIslands.put("student0OnIsland3", student0OnIsland3);  studentsOnIslands.put("student0OnIsland4", student0OnIsland4);  studentsOnIslands.put("student0OnIsland5", student0OnIsland5);  studentsOnIslands.put("student0OnIsland6", student0OnIsland6);  studentsOnIslands.put("student0OnIsland7", student0OnIsland7);  studentsOnIslands.put("student0OnIsland8", student0OnIsland8);  studentsOnIslands.put("student0OnIsland9", student0OnIsland9);  studentsOnIslands.put("student0OnIsland10", student0OnIsland10);  studentsOnIslands.put("student0OnIsland11", student0OnIsland11);
        studentsOnIslands.put("student1OnIsland0", student1OnIsland0);  studentsOnIslands.put("student1OnIsland1", student1OnIsland1);  studentsOnIslands.put("student1OnIsland2", student1OnIsland2);  studentsOnIslands.put("student1OnIsland3", student1OnIsland3);  studentsOnIslands.put("student1OnIsland4", student1OnIsland4);  studentsOnIslands.put("student1OnIsland5", student1OnIsland5);  studentsOnIslands.put("student1OnIsland6", student1OnIsland6);  studentsOnIslands.put("student1OnIsland7", student1OnIsland7);  studentsOnIslands.put("student1OnIsland8", student1OnIsland8);  studentsOnIslands.put("student1OnIsland9", student1OnIsland9);  studentsOnIslands.put("student1OnIsland10", student1OnIsland10);  studentsOnIslands.put("student1OnIsland11", student1OnIsland11);
        studentsOnIslands.put("student2OnIsland0", student2OnIsland0);  studentsOnIslands.put("student2OnIsland1", student2OnIsland1);  studentsOnIslands.put("student2OnIsland2", student2OnIsland2);  studentsOnIslands.put("student2OnIsland3", student2OnIsland3);  studentsOnIslands.put("student2OnIsland4", student2OnIsland4);  studentsOnIslands.put("student2OnIsland5", student2OnIsland5);  studentsOnIslands.put("student2OnIsland6", student2OnIsland6);  studentsOnIslands.put("student2OnIsland7", student2OnIsland7);  studentsOnIslands.put("student2OnIsland8", student2OnIsland8);  studentsOnIslands.put("student2OnIsland9", student2OnIsland9);  studentsOnIslands.put("student2OnIsland10", student2OnIsland10);  studentsOnIslands.put("student2OnIsland11", student2OnIsland11);
        studentsOnIslands.put("student3OnIsland0", student3OnIsland0);  studentsOnIslands.put("student3OnIsland1", student3OnIsland1);  studentsOnIslands.put("student3OnIsland2", student3OnIsland2);  studentsOnIslands.put("student3OnIsland3", student3OnIsland3);  studentsOnIslands.put("student3OnIsland4", student3OnIsland4);  studentsOnIslands.put("student3OnIsland5", student3OnIsland5);  studentsOnIslands.put("student3OnIsland6", student3OnIsland6);  studentsOnIslands.put("student3OnIsland7", student3OnIsland7);  studentsOnIslands.put("student3OnIsland8", student3OnIsland8);  studentsOnIslands.put("student3OnIsland9", student3OnIsland9);  studentsOnIslands.put("student3OnIsland10", student3OnIsland10);  studentsOnIslands.put("student3OnIsland11", student3OnIsland11);
        studentsOnIslands.put("student4OnIsland0", student4OnIsland0);  studentsOnIslands.put("student4OnIsland1", student4OnIsland1);  studentsOnIslands.put("student4OnIsland2", student4OnIsland2);  studentsOnIslands.put("student4OnIsland3", student4OnIsland3);  studentsOnIslands.put("student4OnIsland4", student4OnIsland4);  studentsOnIslands.put("student4OnIsland5", student4OnIsland5);  studentsOnIslands.put("student4OnIsland6", student4OnIsland6);  studentsOnIslands.put("student4OnIsland7", student4OnIsland7);  studentsOnIslands.put("student4OnIsland8", student4OnIsland8);  studentsOnIslands.put("student4OnIsland9", student4OnIsland9);  studentsOnIslands.put("student4OnIsland10", student4OnIsland10);  studentsOnIslands.put("student4OnIsland11", student4OnIsland11);
        towersOnIslands.put("towerOnIsland0", towerOnIsland0); towersOnIslands.put("towerOnIsland1", towerOnIsland1); towersOnIslands.put("towerOnIsland2", towerOnIsland2); towersOnIslands.put("towerOnIsland3", towerOnIsland3); towersOnIslands.put("towerOnIsland4", towerOnIsland4); towersOnIslands.put("towerOnIsland5", towerOnIsland5); towersOnIslands.put("towerOnIsland6", towerOnIsland6); towersOnIslands.put("towerOnIsland7", towerOnIsland7); towersOnIslands.put("towerOnIsland8", towerOnIsland8); towersOnIslands.put("towerOnIsland9", towerOnIsland9); towersOnIslands.put("towerOnIsland10", towerOnIsland10); towersOnIslands.put("towerOnIsland11", towerOnIsland11);
        imageStudentsCounts.put("imageStudentsCount0", imageStudentsCount0); imageStudentsCounts.put("imageStudentsCount1", imageStudentsCount1); imageStudentsCounts.put("imageStudentsCount2", imageStudentsCount2); imageStudentsCounts.put("imageStudentsCount3", imageStudentsCount3); imageStudentsCounts.put("imageStudentsCount4", imageStudentsCount4);
        studentsCounts.put("studentsCount0", studentsCount0); studentsCounts.put("studentsCount1", studentsCount1); studentsCounts.put("studentsCount2", studentsCount2); studentsCounts.put("studentsCount3", studentsCount3); studentsCounts.put("studentsCount4", studentsCount4);

        studentsCards.put("student1Character1", student1Character1); studentsCards.put("student2Character1", student2Character1); studentsCards.put("student3Character1", student3Character1); studentsCards.put("student4Character1", student4Character1); studentsCards.put("student5Character1", student5Character1); studentsCards.put("student6Character1", student6Character1);
        studentsCards.put("student1Character2", student1Character2); studentsCards.put("student2Character2", student2Character2); studentsCards.put("student3Character2", student3Character2); studentsCards.put("student4Character2", student4Character2); studentsCards.put("student5Character2", student5Character2); studentsCards.put("student6Character2", student6Character2);
        studentsCards.put("student1Character3", student1Character3); studentsCards.put("student2Character3", student2Character3); studentsCards.put("student3Character3", student3Character3); studentsCards.put("student4Character3", student4Character3); studentsCards.put("student5Character3", student5Character3); studentsCards.put("student6Character3", student6Character3);

        clouds.put("cloud0", cloud0); clouds.put("cloud1", cloud1); clouds.put("cloud2", cloud2); clouds.put("cloud3", cloud3);
        studentsClouds.put("cloud0Student0", cloud0Student0); studentsClouds.put("cloud0Student1", cloud0Student1); studentsClouds.put("cloud0Student2", cloud0Student2); studentsClouds.put("cloud0Student3", cloud0Student3); studentsClouds.put("cloud1Student0", cloud1Student0); studentsClouds.put("cloud1Student1", cloud1Student1); studentsClouds.put("cloud1Student2", cloud1Student2); studentsClouds.put("cloud1Student3", cloud1Student3);
        studentsClouds.put("cloud2Student0", cloud2Student0); studentsClouds.put("cloud2Student1", cloud2Student1); studentsClouds.put("cloud2Student2", cloud2Student2); studentsClouds.put("cloud2Student3", cloud2Student3); studentsClouds.put("cloud3Student0", cloud3Student0); studentsClouds.put("cloud3Student1", cloud3Student1); studentsClouds.put("cloud3Student2", cloud3Student2);

        indexLastCharacterPlayed = 0;
        enemyBoardDisplayed = 1;
    }

    /**
     *
     */
    private void adaptCloud() {
        cloud0Student0.setLayoutX(6.0);
        cloud0Student0.setLayoutY(45.0);
        cloud0Student1.setLayoutX(33.0);
        cloud0Student1.setLayoutY(20.0);
        cloud0Student2.setLayoutX(58.0);
        cloud0Student2.setLayoutY(45.0);

        cloud1Student0.setLayoutX(6.0);
        cloud1Student0.setLayoutY(45.0);
        cloud1Student1.setLayoutX(33.0);
        cloud1Student1.setLayoutY(20.0);
        cloud1Student2.setLayoutX(58.0);
        cloud1Student2.setLayoutY(45.0);

        cloud2Student0.setLayoutX(6.0);
        cloud2Student0.setLayoutY(45.0);
        cloud2Student1.setLayoutX(33.0);
        cloud2Student1.setLayoutY(20.0);
        cloud2Student2.setLayoutX(58.0);
        cloud2Student2.setLayoutY(45.0);
    }

    /**
     *
     */
    private void adaptTowers() {
        for (int i = 0; i < 6; i++) {
            myTowers.get("myTower" + i).setLayoutX(myTowers.get("myTower" + i).getLayoutX() + 45.0);
            enemyTowers.get("enemyTower" + i).setLayoutX(enemyTowers.get("enemyTower" + i).getLayoutX() + 45.0);
        }
        myTowers.get("myTower6").setVisible(false);
        myTowers.get("myTower7").setVisible(false);
        enemyTowers.get("enemyTower6").setVisible(false);
        enemyTowers.get("enemyTower7").setVisible(false);
    }

    /**
     *
     */
    public void adaptSceneToPlayers() {
        Platform.runLater(() -> {
                if (board.getNumberOfPLayers() == 2) {
                    player2.setVisible(false);
                    player3.setVisible(false);
                    player4.setVisible(false);
                    enemyEntrance7.setVisible(false);
                    enemyEntrance8.setVisible(false);
                    myEntrance7.setVisible(false);
                    myEntrance8.setVisible(false);
                    cloud2.setVisible(false);
                    cloud3.setVisible(false);
                    cloudPane2.setVisible(false);
                    cloudPane3.setVisible(false);
                    for (int i = 0; i < 4; i++)
                        studentsClouds.get("cloud2Student" + i).setVisible(false);
                    for (int i = 0; i < 3; i++)
                        studentsClouds.get("cloud3Student" + i).setVisible(false);
                    cloudPane0.setTranslateX(100);
                    cloudPane1.setTranslateX(100);
                } else if (board.getNumberOfPLayers() == 3) {
                    player4.setVisible(false);
                    cloud3.setVisible(false);
                    cloudPane3.setVisible(false);
                    cloudPane0.setTranslateX(50);
                    cloudPane1.setTranslateX(50);
                    cloudPane2.setTranslateX(50);
                    for (int i = 0; i < 3; i++)
                        studentsClouds.get("cloud3Student" + i).setVisible(false);
                    centerUpperAnchorPane.setPrefWidth(300);
                    adaptCloud();
                    adaptTowers();
                } else if (board.getNumberOfPLayers() == 4) {
                    enemyEntrance7.setVisible(false);
                    enemyEntrance8.setVisible(false);
                    myEntrance7.setVisible(false);
                    myEntrance8.setVisible(false);
                }
                displayBoard(1);
        });
        //System.out.println("Partita adattata per " + board.getNumberOfPLayers() + " giocatori");
        motherNature = new ImageView(new Image("images/Board/Islands/motherNature.png", 25, 25,  true, false));
        String pane = "islandPane" + board.getGametable().getMotherNaturePosition();
        islandPanes.get(pane).getChildren().add(motherNature);
        motherNature.setX(50);
        motherNature.setY(40);
    }

    /**
     * Sets the student image on the node selecting the color of the student passed as parameter
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
                            node.setFitHeight(30);
                            node.setFitWidth(30);
                            break;
                        case RED:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/red.png"));
                            node.setFitHeight(30);
                            node.setFitWidth(30);
                            break;
                        case YELLOW:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/yellow.png"));
                            node.setFitHeight(30);
                            node.setFitWidth(30);
                            break;
                        case PINK:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/pink.png"));
                            node.setFitHeight(30);
                            node.setFitWidth(30);
                            break;
                        case BLUE:
                            node.setImage(new Image("/images/Board/Schoolboards/Students/blue.png"));
                            node.setFitHeight(30);
                            node.setFitWidth(30);
                            break;
                    }
                } else {
                    node.setImage(new Image("/images/Board/Schoolboards/circle.png"));
                    node.setFitHeight(30.0);
                    node.setFitWidth(30.0);
                }
                node.setPreserveRatio(true);
            }
        });
    }

    /**
     * Sets the professor image on the node selecting the color passed as parameter
     * @param node
     * @param color
     */
    public void setProfessor(ImageView node, PawnColor color) {
        //System.out.println("Colora professori");
        Platform.runLater(() -> {
            double x = 0;
            if(node != null) {
                if (color != null) {
                    switch (color) {
                        case GREEN:
                            node.setImage(new Image("/images/Board/Schoolboards/Professors/green.png"));
                            x = 19.0;
                            break;
                        case RED:
                            node.setImage(new Image("/images/Board/Schoolboards/Professors/red.png"));
                            x = 61.0;
                            break;
                        case YELLOW:
                            node.setImage(new Image("/images/Board/Schoolboards/Professors/yellow.png"));
                            x = 102.0;
                            break;
                        case PINK:
                            node.setImage(new Image("/images/Board/Schoolboards/Professors/pink.png"));
                            x = 144.0;
                            break;
                        case BLUE:
                            node.setImage(new Image("/images/Board/Schoolboards/Professors/blue.png"));
                            x = 184.0;
                            break;
                    }
                    node.setPreserveRatio(true);
                    node.setFitHeight(60.0);
                    node.setFitWidth(60.0);
                    node.setLayoutX(x);
                    node.setLayoutY(139.0);
                } else {
                    node.setImage(new Image("/images/Board/Schoolboards/circle.png"));
                    node.setFitHeight(30.0);
                    node.setFitWidth(30.0);
                }
                node.setPreserveRatio(true);
            }
        });
    }

    /**
     *
     * @param node
     * @param color
     */
    public void setTower(ImageView node, TowerColor color) {
        Platform.runLater(() -> {
            if(node != null) {
                if (color != null) {
                    switch (color) {
                        case WHITE:
                            node.setImage(new Image("/images/Board/Schoolboards/Towers/whiteTower.png"));
                            node.setFitHeight(40);
                            node.setFitWidth(40);
                            break;
                        case BLACK:
                            node.setImage(new Image("/images/Board/Schoolboards/Towers/blackTower.png"));
                            node.setFitHeight(40);
                            node.setFitWidth(40);
                            break;
                        case GREY:
                            node.setImage(new Image("/images/Board/Schoolboards/Towers/greyTower.png"));
                            node.setFitHeight(40);
                            node.setFitWidth(40);
                            break;
                    }
                } else {
                    node.setImage(new Image("/images/Board/Schoolboards/circle.png"));
                    node.setFitHeight(40);
                    node.setFitWidth(40);
                }
                node.setPreserveRatio(true);
            }
        });
    }

    public void displayEnemySchoolboard() {
        displayEnemySchoolboard(enemyBoardDisplayed);
    }

    /**
     *
     * @param playerOffset
     */
    public void displayEnemySchoolboard(int playerOffset) {
        int dist = 30;
        if (board != null) {
            if(playerOffset < board.getNumberOfPLayers()) {
                SchoolBoard schoolBoard = board.getGametable().getSchoolBoards()[(myPlayerId + (playerOffset)) % board.getNumberOfPLayers()];
                for (int i = 0; i < schoolBoard.getStudentsFromEntrance().length; i++) {
                    setStudent(enemyEntrance.get(("enemyEntrance" + i)), schoolBoard.getStudentsFromEntrance()[i]);
                }
                for (int i = 0; i < PawnColor.values().length; i++) {
                    for (int j = 0; j < schoolBoard.getNumberOfStudentsOnTable(i); j++) {
                        try {
                            //System.out.println("Stampo tavolo enemy" + PawnColor.associateIndexToPawnColor(i));
                            setStudent(enemyTables.get(("enemyStudent" + i + "" + j)), new Student(PawnColor.associateIndexToPawnColor(i)));
                        } catch (InvalidIndexException e) {
                            e.printStackTrace();
                            //System.out.println(e.getMessage());
                        }
                    }
                    for (int j = 0; j < 10; j++) enemyTables.get("enemyStudent" + i + j).setLayoutX(dist);
                    dist += 42;
                    for (int j = schoolBoard.getNumberOfStudentsOnTable(i); j < 10; j++) {
                        setStudent(enemyTables.get(("enemyStudent" + i + "" + j)), null);
                    }
                }
                for (int i = 0; i < PawnColor.values().length; i++) {
                    try {
                        if (schoolBoard.getProfessors().contains(PawnColor.associateIndexToPawnColor(i))) {
                            //System.out.println("Stampo prof enemy" + i + " " + schoolBoard.getProfessors().contains(PawnColor.associateIndexToPawnColor(i)));
                            setProfessor(enemyProfessors.get(("enemyProfessor" + i)), PawnColor.associateIndexToPawnColor(i));
                        } else {
                            //System.out.println("Stampo prof enemy vuoto");
                            setProfessor(enemyProfessors.get(("enemyProfessor" + i)), null);
                        }
                    } catch (InvalidIndexException e) {
                        //System.out.println(e.getMessage());
                    }
                }
                //System.out.println("Enemy" + schoolBoard.getTowers().size());
                //displayEnemyAssistant(playerOffset);
            }
        }
    }

    public void displayEnemyAssistant() {
        displayEnemyAssistant(enemyBoardDisplayed);
    }

    public void displayEnemyAssistant(int playerOffset) {
        if (board.getPlayedAssistants()[(myPlayerId + (playerOffset)) % board.getNumberOfPLayers()] != null) {
            enemyAssistant.setVisible(true);
            enemyAssistant.setImage(new Image("/images/Assistant/assistant" + (board.getPlayedAssistants()[(myPlayerId + (playerOffset)) % board.getNumberOfPLayers()].getAssistant().getCardValue() - 1) + ".png"));
        } else {
            enemyAssistant.setVisible(false);
        }
    }

    public void displayEnemyTowers() {
        displayEnemyTowers(enemyBoardDisplayed);
    }
    public void displayEnemyTowers(int playerOffset) {
        SchoolBoard schoolBoard = board.getGametable().getSchoolBoards()[(myPlayerId + (playerOffset)) % board.getNumberOfPLayers()];
        for (int i = 0; i < schoolBoard.getTowers().size(); i++)
            setTower(enemyTowers.get(("enemyTower" + i)), schoolBoard.getTowers().get(i).getColor());
        for (int i = schoolBoard.getTowers().size(); i < (board.getNumberOfPLayers() == 3 ? 6 : 8); i++)
            setTower(enemyTowers.get(("enemyTower" + i)), null);

    }

    /**
     * Displays the schoolboard of the actual player
     */
    public void displayMySchoolboard() {

        int dist = 22;
        if (board != null) {
            SchoolBoard schoolBoard = board.getGametable().getSchoolBoards()[myPlayerId];
            for (int i = 0; i < schoolBoard.getStudentsFromEntrance().length; i++) {
                setStudent(myEntranceImages.get(("myEntranceImage" + i)), schoolBoard.getStudentsFromEntrance()[i]);
            }
            for (int i = 0; i < PawnColor.values().length; i++) {
                for (int j = 0; j < schoolBoard.getNumberOfStudentsOnTable(i); j++) {
                    try {
                        setStudent(myTablesImages.get(("studentImage" + i + "" + j)), new Student(PawnColor.associateIndexToPawnColor(i)));;
                    } catch (InvalidIndexException e) {
                        //System.out.println(e.getMessage());
                    }
                }
                for (int j = 0; j < 10; j++) myTables.get("student" + i + "" + j).setLayoutX(dist);
                dist += 42;
                for (int j = schoolBoard.getNumberOfStudentsOnTable(i); j < 10; j++) {
                    setStudent(myTablesImages.get(("studentImage" + i + "" + j)), null);
                }
            }
            for (int i = 0; i < PawnColor.values().length; i++) {
                try {
                    if (schoolBoard.getProfessors().contains(PawnColor.associateIndexToPawnColor(i))) {
                        setProfessor(myProfessors.get(("myProfessor" + i)), PawnColor.associateIndexToPawnColor(i));
                    }
                    else {
                        setProfessor(myProfessors.get(("myProfessor" + i)), null);
                        myProfessors.get(("myProfessor" + i)).setLayoutX(30.0 + (i*42.0));
                        myProfessors.get(("myProfessor" + i)).setLayoutY(147.0);
                    }
                } catch (InvalidIndexException e) {
                    //System.out.println(e.getMessage());
                }
            }
            //System.out.println("My" + schoolBoard.getTowers().size());
            displayMyTowers();
            if (board.getGameMode() == GameMode.EXPERT) coinNumber.setText(String.valueOf(board.getPlayesCoins(myPlayerId)));
            //displayMyAssistant();
        }
    }

    public void displayMyAssistant() {
        if (board.getPlayedAssistants()[myPlayerId] != null) {
            myAssistant.setVisible(true);
            myAssistant.preserveRatioProperty();
            myAssistant.setImage(new Image("/images/Assistant/assistant" + (board.getPlayedAssistants()[myPlayerId].getAssistant().getCardValue() - 1) + ".png"));
        } else {
            myAssistant.setVisible(false);
        }
    }
    public void displayMyTowers() {
        SchoolBoard schoolBoard = board.getGametable().getSchoolBoards()[myPlayerId];
        for (int i = 0; i < schoolBoard.getTowers().size(); i++) {
            setTower(myTowers.get(("myTower" + i)), schoolBoard.getTowers().get(i).getColor());
        }
        for (int i = schoolBoard.getTowers().size(); i < 8; i++) {
            setTower(myTowers.get(("myTower" + i)), null);
        }
    }

    private void displayCoin() {
        Platform.runLater(() -> {
            coinImage.setVisible(true);
            coinNumber.setVisible(true);
            coinTableImage.setVisible(true);
            coinTableImage.setImage(new Image("/images/coin.png"));
            coinTableNumber.setVisible(true);
            coinTableNumber.setText(String.valueOf(board.getTableCoins()));
        });
    }

    /**
     * Displays the islands, rendering students, towers and mother nature
     */
    public void displayIslands() {
        //System.out.println("displayIslands");
        // problema con il postman
        if(board != null && board.getGametable() != null && board.getGametable().getIslands() != null) {
            Platform.runLater(() -> {
                if (board.getState() == 2 && board.getPlayerOnTurn() == myPlayerId) {
                    for (int i = 0; i < board.getNumberOfPLayers(); i++) {
                        if (board.getPlayedAssistants()[i].getPlayerIndex() == 1) {
                            Assistant playedAssistant = board.getPlayedAssistants()[i].getAssistant();
                            for (int j = 0; j < playedAssistant.getMotherNatureMoves(); j++) {
                                islands.get("island" + ((board.getGametable().getMotherNaturePosition() + 1 + j) % 12)).setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.GOLD, 30, 0.5, 0, 0));
                            }
                        }
                    }

                } else {
                    for (int i = 0; i < 12; i++) {
                        islands.get("island" + i).setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.CORNFLOWERBLUE, 30, 0.5, 0, 0));
                    }
                }
                for (int i = 0; i < 12; i++) {
                    String pane = "islandPane" + i;
                    if (islandPanes.get(pane).getChildren().contains(motherNature))
                        islandPanes.get(pane).getChildren().remove(motherNature);
                }
                islandPanes.get("islandPane" + board.getGametable().getMotherNaturePosition()).getChildren().add(motherNature);
                motherNature.setX(50);
                motherNature.setY(40);

                for (int i = 0; i < 12; i++) {
                    List<Student> students = board.getGametable().getIslands().get(i).getStudents();
                    List<PawnColor> colors = new ArrayList<>();
                    for (Student student : students)
                        if (student != null)
                            colors.add(student.getColor());
                    int j = 0;
                    for (PawnColor color : PawnColor.values()) {
                        if (colors.contains(color)) {
                            setStudent(studentsOnIslands.get("student" + j + "OnIsland" + i), new Student(color));
                            j++;
                        }
                    }
                }

                for (int i = 0; i < board.getGametable().getIslands().size(); i++) {
                    /*if(board.getGametable().getIslands().get(i).getTowers().size() <= 0)
                        System.out.println("Isola " + i + " vuota");
                    else*/
                    if(board.getGametable().getIslands().get(i).getTowers().size() > 0)
                        setTower(towersOnIslands.get("towerOnIsland" + i), board.getGametable().getIslands().get(i).getTowers().get(0).getColor());
                }
                //System.out.println("Uscito");
            });
        }
    }

    /**
     *
     */
    public void displayClouds() {
        if (board != null) {
            for(int i = 0; i < board.getNumberOfPLayers(); i++) {
                if (board.getClouds()[i].getStudents().size() != 0) {
                    for (int j = 0; j < board.getClouds()[i].getStudents().size(); j++) {
                        studentsClouds.get("cloud" + i + "Student" + j).setVisible(true);
                        setStudent(studentsClouds.get("cloud" + i + "Student" + j) , board.getClouds()[i].getStudents().get(j));
                    }
                } else {
                    for (int j = 0; j < 4; j++) {
                        studentsClouds.get("cloud" + i + "Student" + j).setVisible(false);
                    }
                }

            }
        }

    }

    /**
     *
     * @param playerOffset
     */
    public void displayBoard(int playerOffset) {
        //System.out.println(board.getPlayerMessageCli());
        displayEnemySchoolboard(playerOffset);
        displayEnemyTowers(playerOffset);
        displayMySchoolboard();
        displayMyTowers();
        displayIslands();
        displayClouds();
        displayAssistants();
        showGameMessage();
        if (board.getGameMode() == GameMode.EXPERT) {
            showButtonCenter();
            displayCharacter();
            displayCoin();
        }
    }

    /**
     *
     * @param event
     */
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

    /**
     *
     * @param message
     */
    @Override
    public void update(Message message) {
        //System.out.println("Arrivato un messaggio alla update");
        if(message instanceof GameMessage && message != null) {
            //System.out.println("Il messaggio arrivato è un GameMessage e non è nullo");
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
                if (toInitialize)
                    displayBoard(enemyBoardDisplayed);
                else
                    board.renderWhatNeeded(this);
            }
            //System.out.println(((GameMessage) message).getPlayerMessageCli());
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    public void assistantClick(MouseEvent event) {
        int indexCard = Integer.valueOf(((Button) event.getSource()).getId().substring(9,10));

        getClient().asyncWriteToSocket(new PlayAssistantMessage(myPlayerId, indexCard));
        //System.out.println("inviato messaggio PlayAssistantMessage");
        myEntrance.get("myEntrance" + studentMoved).setEffect(null);
    }

    /**
     *
     * @param event
     */
    public void myEntranceClick(ActionEvent event) {
        int indexStudent = Integer.valueOf(((Button) event.getSource()).getId().substring(10));
        state = 1;
        if (indexStudent != studentMoved) {
            myEntrance.get("myEntrance" + studentMoved).setEffect(null);
        }
        myEntrance.get("myEntrance" + indexStudent).setEffect(new Glow(0.8));
        studentMoved = indexStudent;
        if (board.getGameMode() == GameMode.EXPERT) {
            if (board.getCharacters()[indexLastCharacterPlayed].getID() == 7 && board.getState() == 6){
                getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, indexStudent));
            } else if (board.getCharacters()[indexLastCharacterPlayed].getID() == 10 && board.getState() == 5) {
                getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, indexStudent));
            }
        }
    }

    /**
     *
     * @param event
     */
    public void myTablesClick(ActionEvent event) {
        if (state == 1) {
            getClient().asyncWriteToSocket(new AddStudentOnTableMessage(myPlayerId, studentMoved));
            myEntrance.get("myEntrance" + studentMoved).setEffect(null);
            state = 0;
        } else if (board.getGameMode() == GameMode.EXPERT && board.getCharacters()[indexLastCharacterPlayed].getID() == 10 && board.getState() == 6) {
            String button = ((Button) event.getSource()).getId();
            int indexTable = Integer.parseInt(button.substring(5,6));
            //System.out.println(indexTable);
            getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, indexTable));
        }
        myEntrance.get("myEntrance" + studentMoved).setEffect(null);
    }

    public void showStuffOnIslands(MouseEvent event) {
        int islandIndex = Integer.valueOf(((ImageView) event.getSource()).getId().substring(6));
        if(islandIndex < 12 && islandIndex > -1) {
            Island island = board.getGametable().getIslands().get(islandIndex);
            if (island != null) {
                Platform.runLater(() -> {
                    for (int i = 0; i < 5; i++) {
                        imageStudentsCounts.get("imageStudentsCount" + i).setVisible(true);
                        studentsCounts.get("studentsCount" + i).setVisible(true);
                        try {
                            studentsCounts.get("studentsCount" + i).setText("x" + island.getNumberOfStudentsForColor(PawnColor.associateIndexToPawnColor(i)));
                        } catch (InvalidIndexException e) {
                            // It is never thrown
                        }
                    }
                });
                Platform.runLater(() -> {
                    imageTowersCount.setVisible(true);
                    towersCount.setVisible(true);
                    towersCount.setText("x" + String.valueOf(island.getTowers().size()));
                    assistantImage.setImage(new Image("images/Board/Islands/parchment.jpg", 80,130, false, false));
                    assistantImage.setVisible(true);
                    assistantImage.setTranslateY(-25.0);
                });
            }
        }
    }

    public void hideStuffOnIslands(MouseEvent event) {
        int islandIndex = Integer.valueOf(((ImageView) event.getSource()).getId().substring(6));
        if(islandIndex < 12 && islandIndex > -1) {
            Island island = board.getGametable().getIslands().get(islandIndex);
            if (island != null) {
                Platform.runLater(() -> {
                    for (int i = 0; i < 5; i++) {
                        imageStudentsCounts.get("imageStudentsCount" + i).setVisible(false);
                        studentsCounts.get("studentsCount" + i).setVisible(false);
                    }
                });
                Platform.runLater(() -> {
                    imageTowersCount.setVisible(false);
                    towersCount.setVisible(false);
                    assistantImage.setVisible(false);
                    assistantImage.setTranslateY(25.0);
                });
            }
        }
    }
    /**
     *
     * @param event
     */
    public void islandClick(MouseEvent event) {
        int islandIndex = Integer.valueOf(((ImageView) event.getSource()).getId().substring(6));
        if(islandIndex < 12 && islandIndex > -1) {
            if (state == 1) {
                getClient().asyncWriteToSocket(new AddStudentOnIslandMessage(myPlayerId, studentMoved, islandIndex));
                state = 0;
            } else if (state == 2) {
                    getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, islandIndex));
                    state = 0;
            } else if (board.getState() == 4) {
                getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, islandIndex));
            } else if (board.getState() == 2) {
                getClient().asyncWriteToSocket(new ChangeMotherNaturePositionMessage(myPlayerId, islandIndex));
            } else if (board.getState() == 4 && board.getCharacters()[indexLastCharacterPlayed].getID() == 5) {
                getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, islandIndex));
            }
        }
        myEntrance.get("myEntrance" + studentMoved).setEffect(null);
    }

    public void playStudentCard(MouseEvent event) {
        String button = ((Button) event.getSource()).getId();
        int indexStudent = Integer.parseInt(button.substring(7,8));
        if (board.getCharacters()[indexLastCharacterPlayed].getID() == 1) {
            getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, indexStudent-1));
            state = 2;
        }
        if ((board.getCharacters()[indexLastCharacterPlayed].getID() == 7 || board.getCharacters()[indexLastCharacterPlayed].getID() == 7) && board.getState() == 5) {
            getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, indexStudent-1));
        }
        if (board.getCharacters()[indexLastCharacterPlayed].getID() == 11) {
            getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, indexStudent-1));
        }
    }

    public void showAssistant(MouseEvent event) {
        int idAssistant = Integer.parseInt(((Button) event.getSource()).getId().substring(9,10));
        idAssistant--;
        String imagePath = "/images/Assistant/assistant" + idAssistant + ".png";
        Image imageAssistant = new Image(imagePath);
        Platform.runLater(() -> {
                assistantImage.setImage(imageAssistant);
                assistantImage.setVisible(true);
                assistantImage.setTranslateY(-8.0);
        });
    }
    public void removeShowAssistant(MouseEvent event) {
        Platform.runLater(() -> {
                assistantImage.setVisible(false);
                assistantImage.setTranslateY(8.0);
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
                        Platform.runLater(() -> {
                                assistant1Image.setImage(imageBW);
                                assistant1.setMouseTransparent(true);

                        });
                        break;
                    case 2:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(() -> {
                            assistant2Image.setImage(imageBW);
                            assistant2.setMouseTransparent(true);
                        });
                        break;
                    case 3:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(() -> {
                                assistant3Image.setImage(imageBW);
                                assistant3.setMouseTransparent(true);
                        });
                        break;
                    case 4:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(() -> {
                                assistant4Image.setImage(imageBW);
                                assistant4.setMouseTransparent(true);
                        });
                        break;
                    case 5:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(() -> {
                                assistant5Image.setImage(imageBW);
                                assistant5.setMouseTransparent(true);
                        });
                        break;
                    case 6:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(() -> {
                                assistant6Image.setImage(imageBW);
                                assistant6.setMouseTransparent(true);
                        });
                        break;
                    case 7:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(() -> {
                                assistant7Image.setImage(imageBW);
                                assistant7.setMouseTransparent(true);
                        });
                        break;
                    case 8:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(() -> {
                                assistant8Image.setImage(imageBW);
                                assistant8.setMouseTransparent(true);
                        });
                        break;
                    case 9:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(() -> {
                                assistant9Image.setImage(imageBW);
                                assistant9.setMouseTransparent(true);
                        });
                        break;
                    case 10:
                        imagePath += i + ".png";
                        imageBW = new Image(imagePath);
                        Platform.runLater(() -> {
                                assistant10Image.setImage(imageBW);
                                assistant10.setMouseTransparent(true);
                        });
                        break;
                }
            }
        }
    }

    /*public void selectStudentFromCard(MouseEvent event) {
        String studentButton = ((Button) event.getSource()).getId();
    }*/
    public void cloudClick(MouseEvent event) {
        int cloudIndex = Integer.parseInt(((ImageView) event.getSource()).getId().substring(((ImageView) event.getSource()).getId().length() - 1));
        getClient().asyncWriteToSocket(new GetStudentsFromCloudsMessage(myPlayerId, cloudIndex));
        //System.out.println(cloudIndex);
        myEntrance.get("myEntrance" + studentMoved).setEffect(null);
    }

    public void setStudentCharacter(Button node, Student student) {
        Platform.runLater(() -> {
            if(node != null) {
                if (student != null) {
                    switch (student.getColor()) {
                        case GREEN:
                            ImageView imageView1 = new ImageView(new Image("/images/Board/Schoolboards/Students/green.png"));
                            imageView1.setFitHeight(30);
                            imageView1.setFitWidth(30);
                            node.setGraphic(imageView1);
                            break;
                        case RED:
                            ImageView imageView2 = new ImageView(new Image("/images/Board/Schoolboards/Students/red.png"));
                            imageView2.setFitHeight(30);
                            imageView2.setFitWidth(30);
                            node.setGraphic(imageView2);
                            break;
                        case YELLOW:
                            ImageView imageView3 = new ImageView(new Image("/images/Board/Schoolboards/Students/yellow.png"));
                            imageView3.setFitHeight(30);
                            imageView3.setFitWidth(30);
                            node.setGraphic(imageView3);
                            break;
                        case PINK:
                            ImageView imageView4 = new ImageView(new Image("/images/Board/Schoolboards/Students/pink.png"));
                            imageView4.setFitHeight(30);
                            imageView4.setFitWidth(30);
                            node.setGraphic(imageView4);
                            break;
                        case BLUE:
                            ImageView imageView5 = new ImageView(new Image("/images/Board/Schoolboards/Students/blue.png"));
                            imageView5.setFitHeight(30);
                            imageView5.setFitWidth(30);
                            node.setGraphic(imageView5);
                            break;
                    }
                } else {
                    ImageView imageView5 = new ImageView(new Image("/images/Board/Schoolboards/circle.png"));
                    imageView5.setFitHeight(30);
                    imageView5.setFitWidth(30);
                    node.setGraphic(imageView5);
                }
            }
        });
    }

    private void displayCharacter() {
        if (board.getGameMode() == GameMode.EXPERT) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    character1Image.setVisible(true);
                    character2Image.setVisible(true);
                    character3Image.setVisible(true);

                    for (int i = 0; i < 3; i++) {
                        String path = "/images/Character/character" + board.getCharacters()[i].getID() + ".jpg";
                        Image image = new Image(path);
                        switch (i) {
                            case 0:
                                if (board.getCharacters()[i] instanceof Monk || board.getCharacters()[i] instanceof Princess) {
                                    for (int j = 0; j < 4; j++) {
                                        setStudentCharacter(studentsCards.get("student" + (j+1) + "Character" + (i+1)), ((CharacterWithStudent) board.getCharacters()[i]).getStudentsOnCard()[j]);
                                        studentsCards.get("student" + (j+1) + "Character" + (i+1)).setVisible(true);
                                    }
                                } else if (board.getCharacters()[i] instanceof Jester) {
                                    for (int j = 0; j < 6; j++) {
                                        setStudentCharacter(studentsCards.get("student" + (j+1) + "Character" + (i+1)), ((CharacterWithStudent) board.getCharacters()[i]).getStudentsOnCard()[j]);
                                        studentsCards.get("student" + (j+1) + "Character" + (i+1)).setVisible(true);
                                    }
                                } else if (board.getCharacters()[i] instanceof Healer) {
                                    for (int j = 0; j < 4; j++) {
                                        if (j >= ((Healer) board.getCharacters()[i]).getNumberOfProibitionCard()) {
                                            studentsCards.get("student" + (j+1) + "Character" + (i+1)).setVisible(false);
                                        } else {
                                            Image image2 = new Image("/images/Character/prohibition.png");
                                            ImageView imageView = new ImageView(image2);
                                            imageView.setFitHeight(30);
                                            imageView.setFitWidth(30);
                                            studentsCards.get("student" + (j+1) + "Character" + (i+1)).setGraphic(imageView);
                                            studentsCards.get("student" + (j+1) + "Character" + (i+1)).setVisible(true);
                                        }

                                    }
                                }
                                character1Image.setImage(image);
                                break;
                            case 1:
                                if (board.getCharacters()[i] instanceof Monk || board.getCharacters()[i] instanceof Princess) {
                                    for (int j = 0; j < 4; j++) {
                                        setStudentCharacter(studentsCards.get("student" + (j+1) + "Character" + (i+1)), ((CharacterWithStudent) board.getCharacters()[i]).getStudentsOnCard()[j]);
                                        studentsCards.get("student" + (j+1) + "Character" + (i+1)).setVisible(true);
                                    }
                                } else if (board.getCharacters()[i] instanceof Jester) {
                                    for (int j = 0; j < 6; j++) {
                                        setStudentCharacter(studentsCards.get("student" + (j+1) + "Character" + (i+1)), ((CharacterWithStudent) board.getCharacters()[i]).getStudentsOnCard()[j]);
                                        studentsCards.get("student" + (j+1) + "Character" + (i+1)).setVisible(true);
                                    }
                                } else if (board.getCharacters()[i] instanceof Healer) {
                                    for (int j = 0; j < 4; j++) {
                                        if (j >= ((Healer) board.getCharacters()[i]).getNumberOfProibitionCard()) {
                                            studentsCards.get("student" + (j+1) + "Character" + (i+1)).setVisible(false);
                                        } else {
                                            Image image2 = new Image("/images/Character/prohibition.png");
                                            ImageView imageView = new ImageView(image2);
                                            imageView.setFitHeight(30);
                                            imageView.setFitWidth(30);
                                            studentsCards.get("student" + (j+1) + "Character" + (i+1)).setGraphic(imageView);
                                            studentsCards.get("student" + (j+1) + "Character" + (i+1)).setVisible(true);
                                        }

                                    }
                                }
                                character2Image.setImage(image);
                                break;
                            case 2:
                                if (board.getCharacters()[i] instanceof Monk || board.getCharacters()[i] instanceof Princess) {
                                    for (int j = 0; j < 4; j++) {
                                        setStudentCharacter(studentsCards.get("student" + (j+1) + "Character" + (i+1)), ((CharacterWithStudent) board.getCharacters()[i]).getStudentsOnCard()[j]);
                                        studentsCards.get("student" + (j+1) + "Character" + (i+1)).setVisible(true);
                                    }
                                } else if (board.getCharacters()[i] instanceof Jester) {
                                    for (int j = 0; j < 6; j++) {
                                        setStudentCharacter(studentsCards.get("student" + (j+1) + "Character" + (i+1)), ((CharacterWithStudent) board.getCharacters()[i]).getStudentsOnCard()[j]);
                                        studentsCards.get("student" + (j+1) + "Character" + (i+1)).setVisible(true);
                                    }
                                } else if (board.getCharacters()[i] instanceof Healer) {
                                    for (int j = 0; j < 4; j++) {
                                        if (j >= ((Healer) board.getCharacters()[i]).getNumberOfProibitionCard()) {
                                            studentsCards.get("student" + (j+1) + "Character" + (i+1)).setVisible(false);
                                        } else {
                                            Image image2 = new Image("/images/Character/prohibition.png");
                                            ImageView imageView = new ImageView(image2);
                                            imageView.setFitHeight(30);
                                            imageView.setFitWidth(30);
                                            studentsCards.get("student" + (j+1) + "Character" + (i+1)).setGraphic(imageView);
                                            studentsCards.get("student" + (j+1) + "Character" + (i+1)).setVisible(true);
                                        }

                                    }
                                }
                                character3Image.setImage(image);
                                break;
                        }
                    }
                }
            });
        }
    }

    public void sendButtonValue(MouseEvent event) {
        String button = ((Button) event.getSource()).getId();
        int index = -1;
        switch (button) {
            case "pawnColor0":
                index = 0;
                break;
            case "pawnColor1":
                index = 1;
                break;
            case "pawnColor2":
                index = 2;
                break;
            case "pawnColor3":
                index = 3;
                break;
            case "pawnColor4":
                index = 4;
                break;
        }
        if (board.getState() == 4) {
            if (board.getCharacters()[indexLastCharacterPlayed].getID() == 12 || board.getCharacters()[indexLastCharacterPlayed].getID() == 9) getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, index));
            else if (board.getCharacters()[indexLastCharacterPlayed].getID() == 7) getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, index+1));
            else if (board.getCharacters()[indexLastCharacterPlayed].getID() == 10) {
                if (index == 3) getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, 1));
                else getClient().asyncWriteToSocket(new DoYourJobMessage(myPlayerId, 2));
            }

            Platform.runLater( () -> {
                pawnColor0.setVisible(false);
                pawnColor1.setVisible(false);
                pawnColor2.setVisible(false);
                pawnColor3.setVisible(false);
                pawnColor4.setVisible(false);
            });
        }
    }


    private void showButtonCenter() {
        if (board.getState() == 4) {
            if ((board.getCharacters()[indexLastCharacterPlayed].getID() == 12 || board.getCharacters()[indexLastCharacterPlayed].getID() == 9)) {
                Platform.runLater( () -> {
                    pawnColor0.setText("GREEN");
                    pawnColor1.setText("RED");
                    pawnColor2.setText("YELLOW");
                    pawnColor3.setText("PINK");
                    pawnColor4.setText("BLUE");
                    pawnColor0.setVisible(true);
                    pawnColor1.setVisible(true);
                    pawnColor2.setVisible(true);
                    pawnColor3.setVisible(true);
                    pawnColor4.setVisible(true);
                });
            } else if (board.getCharacters()[indexLastCharacterPlayed].getID() == 7) {
                Platform.runLater( () -> {
                    pawnColor0.setText("1");
                    pawnColor1.setText("2");
                    pawnColor2.setText("3");
                    pawnColor0.setVisible(true);
                    pawnColor1.setVisible(true);
                    pawnColor2.setVisible(true);
                });
            } else if (board.getCharacters()[indexLastCharacterPlayed].getID() == 10) {
                Platform.runLater( () -> {
                    pawnColor3.setText("1");
                    pawnColor4.setText("2");
                    pawnColor3.setVisible(true);
                    pawnColor4.setVisible(true);
                });
            }
        }

    }
    private String returnImagePathStudentFromColor(PawnColor color) {
        switch (color) {
            case BLUE:
                return "/images/Board/Schoolboards/Students/blue.png";
            case GREEN:
                return "/images/Board/Schoolboards/Students/green.png";
            case PINK:
                return "/images/Board/Schoolboards/Students/pink.png";
            case RED:
                return "/images/Board/Schoolboards/Students/red.png";
            case YELLOW:
                return "/images/Board/Schoolboards/Students/yellow.png";
        }
        return "";
    }


    public void showGameMessage(){
        String playerMessage = board.getPlayerMessageGui();
        Platform.runLater(() -> {
                    labelGameMessage.setVisible(true);
                    labelGameMessage.setText(playerMessage);
        });

        Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        labelGameMessage.setVisible(false);
                    });
                }
            }, 50 * 1000);

    }

    public void playCharacter(MouseEvent event) {
        int indexCard = -1;
        switch(((ImageView) event.getSource()).getId()) {
            case "character1Image":
                indexCard = 0;
                break;
            case "character2Image":
                indexCard = 1;
                break;
            case "character3Image":
                indexCard = 2;
                break;
        }
        getClient().asyncWriteToSocket(new ActivateEffectMessage(myPlayerId, indexCard));
        indexLastCharacterPlayed = indexCard;
        //System.out.println("Character played " + indexCard);
    }
}
