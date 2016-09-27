package com.juliocalderonuninorte.pokemongodemo;

/**
 * Created by JULIO CALDERON on 20/09/2016.
 */
public class Pokemones {


    public String getHP() {
        return HP;
    }

    public void setHP(String HP) {
        this.HP = HP;
    }

    String id;
    String name;
    String type;
    String total;
    String HP;
    String Attack;
    String Defense;
    String SpAttack;
    String SpDefense;
    String Speed;
    String ImgFront;
    String ev_id;

    public Pokemones(String id, String name, String type, String total, String HP, String Attack, String Defense, String SpAttack, String SpDefense, String Speed, String ImgFront, String ev_id){
        this.id = id;
        this.name = name;
        this.type = type;
        this.total = total;
        this.HP = HP;
        this.Attack = Attack;
        this.Defense = Defense;
        this.SpAttack = SpAttack;
        this.SpDefense = SpDefense;
        this.Speed = Speed;
        this.ImgFront = ImgFront;
        this.ev_id= ev_id;
    }
}
