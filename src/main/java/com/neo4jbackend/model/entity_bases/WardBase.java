package com.neo4jbackend.model.entity_bases;

import com.neo4jbackend.model.types.WardType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class WardBase {
    protected WardType type;
    protected int maxCapacity;
}
