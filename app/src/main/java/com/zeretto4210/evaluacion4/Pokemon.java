package com.zeretto4210.evaluacion4;

public class Pokemon {
    private String id, user, name;
    private int atk, hp, def;

    public Pokemon() {
    }

    public Pokemon(String id, String name, String user, int hp, int atk, int def) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.def = def;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user;
    }

    public void setUser_id(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    @Override
    public String toString() {
        return (" Nombre: "+this.name+"\n HP: "+this.hp+"\n ATK: "+this.atk+"\n DEF: "+this.def);
    }
}