/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.Objects;

/**
 *
 * @author Ryan Meza
 */
public class Superpower {

    private int superpowerId;
    private String superpowerType;

    public Superpower() {
    }

    public Superpower(int superpowerId) {
        this.superpowerId = superpowerId;
    }

    public int getSuperpowerId() {
        return superpowerId;
    }

    public void setSuperpowerId(int superpowerId) {
        this.superpowerId = superpowerId;
    }

    public String getSuperpowerType() {
        return superpowerType;
    }

    public void setSuperpowerType(String superpowerType) {
        this.superpowerType = superpowerType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.superpowerId;
        hash = 97 * hash + Objects.hashCode(this.superpowerType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Superpower other = (Superpower) obj;
        if (this.superpowerId != other.superpowerId) {
            return false;
        }
        if (!Objects.equals(this.superpowerType, other.superpowerType)) {
            return false;
        }
        return true;
    }

}
