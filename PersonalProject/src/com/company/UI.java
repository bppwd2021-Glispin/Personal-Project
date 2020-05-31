package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UI {

    BufferedImage placeholder, cursor, pointingCur, grabCur, highlight, bottomBar, invBG, invPopup, tilePopup, buildBG, buildPopup, buildInfoTop, buildInfoMid, buildInfoBot, placementBlocked;
    boolean almostInvOpen, invOpen, almostTileMode, tileMode, almostBuildMode, buildMode;
    boolean doInvPopup, doTilePopup, doBuildPopup;
    ArrayList<BuildingPreset> builds = new ArrayList<>();
    ArrayList<BufferedImage> resourceImgs = new ArrayList<>();
    BuildingPreset selectedBuild = null;
    Tile selectedBuildTile = null;
    Building highlightBuilding = null;

    public UI(){
        try {
            this.placeholder = ImageIO.read(new File("Images\\UI\\placeholder.png"));
            this.pointingCur = ImageIO.read(new File("Images\\Mouse\\Pointer.png"));
            this.grabCur = ImageIO.read(new File("Images\\Mouse\\Grab.png"));
            this.highlight = ImageIO.read(new File("Images\\UI\\Highlight.png"));
            this.bottomBar = ImageIO.read(new File("Images\\UI\\BottomBar.png"));
            this.invBG = ImageIO.read(new File("Images\\UI\\inventoryBG.png"));
            this.invPopup = ImageIO.read(new File("Images\\UI\\invButtonPopup.png"));
            this.tilePopup = ImageIO.read(new File("Images\\UI\\tileModeButtonPopup.png"));
            this.buildBG = ImageIO.read(new File("Images\\UI\\buildMenuBG.png"));
            this.buildPopup = ImageIO.read(new File("Images\\UI\\buildMenuButtonPopup.png"));
            this.buildInfoTop = ImageIO.read(new File("Images\\UI\\buildMenuPopupTop.png"));
            this.buildInfoMid = ImageIO.read(new File("Images\\UI\\buildMenuPopupMiddle.png"));
            this.buildInfoBot = ImageIO.read(new File("Images\\UI\\buildMenuPopupBottom.png"));
            this.placementBlocked = ImageIO.read(new File("Images\\Buildings\\placementBlocked.png"));
            for(String resource:Board.resourceTypes){
                resourceImgs.add(ImageIO.read(new File("Images\\Resources\\"+resource+".png")));
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        builds.add(new BuildingPreset("TownHall"));
        builds.add(new BuildingPreset("ResearchCenter"));
        builds.add(new BuildingPreset("LumberYard"));
    }

    public void update(){
        if(MyGame.player.m2){
            cursor=grabCur;
        }
        else{
            cursor=pointingCur;
        }
        doInvPopup= MyGame.mousex > 203 && MyGame.mousex < 247 && MyGame.mousey > 1014 && MyGame.mousey < 1055;
        if(MyGame.mousex>203 && MyGame.mousex<247 && MyGame.mousey>1014 && MyGame.mousey<1055 && MyGame.player.m1 && !almostInvOpen){
            almostInvOpen=true;
        }
        else if(almostInvOpen && !(MyGame.mousex>203 && MyGame.mousex<247 && MyGame.mousey>1014 && MyGame.mousey<1055)){
            almostInvOpen=false;
        }
        else if(almostInvOpen && !MyGame.player.m1){
            invOpen=!invOpen;
            almostInvOpen=false;
            MyGame.player.m1=false;
        }
        doTilePopup= MyGame.mousex > 253 && MyGame.mousex < 295 && MyGame.mousey > 1014 && MyGame.mousey < 1055;
        if(MyGame.mousex>253 && MyGame.mousex<295 && MyGame.mousey>1014 && MyGame.mousey<1055 && MyGame.player.m1 && !almostTileMode){
            almostTileMode=true;
        }
        else if(almostTileMode && !(MyGame.mousex>253 && MyGame.mousex<295 && MyGame.mousey>1014 && MyGame.mousey<1055)){
            almostTileMode=false;
        }
        else if(almostTileMode && !MyGame.player.m1){
            tileMode=!tileMode;
            almostTileMode=false;
            MyGame.player.m1=false;
        }
        doBuildPopup= MyGame.mousex > 297 && MyGame.mousex < 337 && MyGame.mousey > 1014 && MyGame.mousey < 1055;
        if(MyGame.mousex>297 && MyGame.mousex<337 && MyGame.mousey>1014 && MyGame.mousey<1055 && MyGame.player.m1 && !almostBuildMode){
            almostBuildMode=true;
        }
        else if(almostBuildMode && !(MyGame.mousex>297 && MyGame.mousex<337 && MyGame.mousey>1014 && MyGame.mousey<1055)){
            almostBuildMode=false;
        }
        else if(almostBuildMode && !MyGame.player.m1){
            buildMode=!buildMode;
            almostBuildMode=false;
            MyGame.player.m1=false;
        }
        if(buildMode){
            for(int i=0;i<builds.size();i++){
                if(MyGame.mousex>1680+(120*(i%2)) && MyGame.mousex<1780+(120*(i%2)) && MyGame.mousey>25+(120*(i/2)) && MyGame.mousey<125+(120*(i/2))){
                    if(MyGame.player.m1){
                        selectedBuild=builds.get(i);
                        MyGame.player.m1=false;
                    }
                }
            }
            if(selectedBuild!=null){
                boolean leave = false;
                for (int i = 0; i < MyGame.board.grid.size(); i++) {
                    for (int j = MyGame.board.minX; j < MyGame.board.grid.get(i).size(); j++) {
                        if (MyGame.board.basey + MyGame.board.grid.get(i).get(j).offsety > 1080) {
                            leave = true;
                            break;
                        }
                        if (MyGame.board.maxX == j || MyGame.board.basex + MyGame.board.grid.get(i).get(j).offsetx > 1920) {
                            MyGame.board.maxX = j;
                            break;
                        }
                        if(MyGame.mousex>MyGame.board.basex+MyGame.board.grid.get(i).get(j).offsetx && MyGame.mousex<MyGame.board.basex+MyGame.board.grid.get(i).get(j).offsetx+50 && MyGame.mousey>MyGame.board.basey+MyGame.board.grid.get(i).get(j).offsety && MyGame.mousey<MyGame.board.basey+MyGame.board.grid.get(i).get(j).offsety+50){
                            selectedBuildTile = MyGame.board.grid.get(i).get(j);
                        }
                    }
                    if (leave) {
                        break;
                    }
                }
                if(selectedBuildTile!=null && MyGame.player.m1){
                    boolean goodTile=false;
                    for(String tile:selectedBuild.buildOn){
                        if(tile.equals(selectedBuildTile.type)){
                            goodTile=true;
                        }
                    }
                    boolean gotCost=true;
                    for(String[] resourceCost:selectedBuild.cost){
                        if(resourceCost[1].equals("Money")){
                            if(MyGame.player.cash<Integer.parseInt(resourceCost[0])){
                                gotCost=false;
                                break;
                            }
                        }
                        else{
                            boolean foundResource=false;
                            for(int i=0;i<MyGame.player.inventory.size();i++){
                                if(MyGame.player.inventory.get(i).type.equals(resourceCost[1])){
                                    if(MyGame.player.inventoryStack.get(i)>=Integer.parseInt(resourceCost[0])){
                                        foundResource=true;
                                    }
                                }
                            }
                            if(!foundResource){
                                gotCost=false;
                                break;
                            }
                        }
                    }
                    if(goodTile && gotCost){
                        selectedBuildTile.building=selectedBuild.build(selectedBuildTile.offsetx,selectedBuildTile.offsety);
                        for(String[] resourceCost:selectedBuild.cost){
                            if(resourceCost[1].equals("Money")){
                                MyGame.player.cash-=Integer.parseInt(resourceCost[0]);
                            }
                            else{
                                for(int i=0;i<MyGame.player.inventory.size();i++){
                                    if(MyGame.player.inventory.get(i).type.equals(resourceCost[1])){
                                        MyGame.player.inventoryStack.set(i,MyGame.player.inventoryStack.get(i)-Integer.parseInt(resourceCost[0]));
                                        if(MyGame.player.inventoryStack.get(i)==0){
                                            MyGame.player.inventory.remove(i);
                                            MyGame.player.inventoryStack.remove(i);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        selectedBuild=null;
                        selectedBuildTile=null;
                        MyGame.player.m1=false;
                    }
                }
            }
        }
        else{
            selectedBuild = null;
            selectedBuildTile = null;
        }
    }

    public void draw(Graphics pen){
        if(buildMode && selectedBuild!=null && selectedBuildTile!=null && !MyGame.player.m2){
            pen.drawImage(selectedBuild.img, MyGame.board.basex+selectedBuildTile.offsetx, MyGame.board.basey+selectedBuildTile.offsety, 50, 50, null);
            boolean safe = false;
            for(String type:selectedBuild.buildOn){
                if(selectedBuildTile.type.equals(type)){
                    safe=true;
                    break;
                }
            }
            if(!safe){
                pen.drawImage(placementBlocked, MyGame.board.basex+selectedBuildTile.offsetx, MyGame.board.basey+selectedBuildTile.offsety, 50, 50, null);
            }
        }
        if(tileMode){
            MyGame.board.drawOutline(pen);
        }
        if(highlightBuilding!=null){
            pen.drawImage(placeholder,825,495,100,90,null);
        }
        pen.drawImage(bottomBar,0,1007,1920,50,null);
        Font newFont = MyGame.baseFont.deriveFont(MyGame.baseFont.getSize() * 3F);
        pen.setFont(newFont);
        pen.drawString("$"+MyGame.player.cash,10,1047);
        if(almostInvOpen){
            pen.drawImage(highlight,203,1014,44,41,null);
        }
        if(invOpen){
            newFont = MyGame.baseFont.deriveFont(MyGame.baseFont.getSize() * 1.5F);
            pen.setFont(newFont);
            pen.drawImage(invBG,0,707,255,300,null);
            for(int i=0;i<MyGame.player.inventory.size();i++){
                String shorten = "";
                int stack = MyGame.player.inventoryStack.get(i);
                if(MyGame.player.inventoryStack.get(i)>=1000000){
                    shorten="M";
                    stack=stack/1000000;
                }
                else if(MyGame.player.inventoryStack.get(i)>=1000){
                    shorten="K";
                    stack=stack/1000;
                }
                pen.drawImage(MyGame.player.inventory.get(i).img,10+(60*(i%4)),722+(60*(i/4)),50,50,null);
                pen.drawString(stack+shorten,30+(60*(i%4)),767+(60*(i/4)));
            }
        }
        if(almostTileMode){
            pen.drawImage(highlight,253,1014,42,41,null);
        }
        if(almostBuildMode){
            pen.drawImage(highlight,297,1014,40,41,null);
        }
        if(buildMode){
            pen.drawImage(buildBG,1655,0,265,1007,null);
            for(int i=0;i<builds.size();i++){
                pen.drawImage(builds.get(i).img,1680+(120*(i%2)),25+(120*(i/2)),100,100,null);
                if(MyGame.mousex>1680+(120*(i%2)) && MyGame.mousex<1780+(120*(i%2)) && MyGame.mousey>25+(120*(i/2)) && MyGame.mousey<125+(120*(i/2))){
                    newFont = MyGame.baseFont.deriveFont(MyGame.baseFont.getSize() * 1.5F);
                    pen.setFont(newFont);
                    pen.drawImage(buildInfoTop,1455,15+(120*(i/2)),200,10,null);
                    pen.drawImage(buildInfoMid,1455,25+(120*(i/2)),200,30*(((builds.get(i).cost.length+1)/2)+1),null);
                    pen.drawImage(buildInfoBot,1455,25+(120*(i/2))+(30*(((builds.get(i).cost.length+1)/2)+1)),200,10,null);
                    pen.drawString(builds.get(i).name,1470,45+(120*(i/2)));
                    int count = 0;
                    for(String[] cost:builds.get(i).cost){
                        String num = cost[0];
                        if(num.length()>=7){
                            num=num.substring(0,num.length()-6)+"M";
                        }
                        else if(num.length()>=4){
                            num=num.substring(0,num.length()-3)+"K";
                        }
                        int index=0;
                        for(int j=0;j<Board.resourceTypes.length;j++){
                            if(Board.resourceTypes[j].equals(cost[1])){
                                index=j;
                                break;
                            }
                        }
                        pen.drawImage(resourceImgs.get(index),1470+(100*(count%2)),25+(120*(i/2))+(30*((count/2)+1)),30,30,null);
                        pen.drawString(num,1505+(100*(count%2)),47+(120*(i/2))+(30*((count/2)+1)));
                        count++;
                    }
                }
            }
        }

        if(doInvPopup){
            pen.drawImage(invPopup,249,1019,135,30,null);
        }
        if(doTilePopup){
            pen.drawImage(tilePopup,297,1019,150,30,null);
        }
        if(doBuildPopup){
            pen.drawImage(buildPopup,339,1019,160,30,null);
        }
        pen.drawImage(cursor,MyGame.mousex-10,MyGame.mousey,null);
    }
}
